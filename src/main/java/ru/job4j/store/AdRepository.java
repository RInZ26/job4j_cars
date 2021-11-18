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
import ru.job4j.entity.Image;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

public class AdRepository implements Store {

    private static final AdRepository INSTANCE = new AdRepository();

    private final StandardServiceRegistry registry;
    private final SessionFactory sf;

    private AdRepository() {
        this.registry = new StandardServiceRegistryBuilder()
                .configure().build();
        this.sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
    }

    public static AdRepository getInst() {
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
    public Collection<Advert> findAdvertsByYesterDay() {
        Date yesterday = Date.from(Instant.now().minus(1, ChronoUnit.DAYS));
        return performTx(s -> s.createQuery("from Advert a where a.publishDate >= :yesterday ")
                .setParameter("yesterday", yesterday)
                .list());
    }

    @Override
    public Collection<Advert> findAdvertsWithImage() {
        return performTx(s -> s.createQuery("select distinct a from Advert a "
                + "join fetch a.images").list());
    }

    @Override
    public Collection<Advert> findAdvertsWithModel(CarModel model) {
        return performTx(s -> s.createQuery("select distinct a from Advert a "
                        + "join fetch a.cars c where c.carModel = :model")
                .setParameter("model", model).list());
    }

    @Override
    public void save(Object entity) {
        performTx(s -> s.save(entity));
    }

    @Override
    public Car findCarById(int id) {
        return (Car) performTx(s -> s.createQuery("from Car c where c.id = :id")
                .setParameter("id", id).uniqueResult());
    }

    @Override
    public Image findImageById(int id) {
        return (Image) performTx(s -> s.createQuery("from Image i where i.id = :id")
                .setParameter("id", id)
                .uniqueResult());

    }

    @Override
    public Advert findAdvertById(int id) {
        return (Advert) performTx(s -> s.createQuery("from Advert a "
                        + "join fetch a.cars c join fetch a.images i "
                        + "where  a.id = :id")
                .setParameter("id", id)
                .uniqueResult());

    }
}
