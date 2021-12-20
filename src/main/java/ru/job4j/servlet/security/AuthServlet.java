package ru.job4j.servlet.security;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.job4j.controller.AuthController;
import ru.job4j.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

public class AuthServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = AuthController.validateAuth(email, password);

        JsonObject servletAnswer = new JsonObject();

        if (Objects.nonNull(user)) {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user);
            servletAnswer.addProperty("url", req.getContextPath() + "/main.do");
        } else {
            servletAnswer.addProperty("errorMsg", "Неверный email или пароль");
        }
        String servletAnswerJson = GSON.toJson(servletAnswer);

        try (Writer output = resp.getWriter()) {
            output.write(servletAnswerJson);
            output.flush();
        }
    }
}