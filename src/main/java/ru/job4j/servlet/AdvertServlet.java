package ru.job4j.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.controller.AdvertController;
import ru.job4j.entity.Advert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

public class AdvertServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json; charset=utf-8");
        Set<Advert> adverts = new HashSet<>(AdvertController.getAllAdverts());
        String json = GSON.toJson(adverts);

        try (Writer output = resp.getWriter()) {
            output.write(json);
            output.flush();
        }
    }
}