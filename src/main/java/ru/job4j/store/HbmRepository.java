package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.entity.Advert;
import ru.job4j.entity.Car;
import ru.job4j.entity.CarModel;
import ru.job4j.entity.User;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

public class HbmRepository implements Store {

    private static final HbmRepository INSTANCE = new HbmRepository();

    private final StandardServiceRegistry registry;
    private final SessionFactory sf;

    private HbmRepository() {
        this.registry = new StandardServiceRegistryBuilder()
                .configure().build();
        this.sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
    }

    public static HbmRepository getInst() {
        return INSTANCE;
    }

    private <T> T performTx(Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void save(Object entity) {
        performTx(s -> s.save(entity));
    }

    //****** ADVERT

    @Override
    public Collection<Advert> findAllAdverts() {
        return performTx(s -> s.createQuery("select distinct a from Advert a "
                + " left join fetch a.car c left join fetch a.images i "
                + "left join fetch a.author ").list());
    }

    @Override
    public Collection<Advert> findAdvertsByYesterDay() {
        Date yesterday = Date.from(Instant.now().minus(1, ChronoUnit.DAYS));
        return performTx(s -> s.createQuery("from Advert a where a.publishDate >= :yesterday")
                .setParameter("yesterday", yesterday)
                .list());
    }

    @Override
    public Collection<Advert> findAdvertsWithModel(CarModel model) {
        return performTx(s -> s.createQuery("select distinct a from Advert a "
                        + "join fetch a.car c where c.carModel = :model")
                .setParameter("model", model).list());
    }

    @Override
    public Advert findAdvertById(int id) {
        return (Advert) performTx(s -> s.createQuery("from Advert a "
                        + "left join fetch a.car "
                        + "where  a.id = :id")
                .setParameter("id", id)
                .uniqueResult());

    }

    //****** CAR

    @Override
    public Car findCarById(int id) {
        return (Car) performTx(s -> s.createQuery("from Car c where c.id = :id")
                .setParameter("id", id).uniqueResult());
    }

    //****** USER
    @Override
    public User findUserByEmail(String email) {
        return (User) performTx(s -> s.createQuery("from User u join u.account a"
                        + " where a.email = :email")
                .setParameter("email", email)
                .uniqueResult());
    }
}
