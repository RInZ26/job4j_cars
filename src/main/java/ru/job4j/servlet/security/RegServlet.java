package ru.job4j.servlet.security;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.job4j.controller.RegController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.StringJoiner;

public class RegServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String login = req.getParameter("login");

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String patronymic = req.getParameter("patronymic");

        String firstPass = req.getParameter("firstPassword");
        String secondPass = req.getParameter("secondPassword");

        JsonObject servletAnswer = new JsonObject();

        StringJoiner errorStrJoiner = new StringJoiner(System.lineSeparator());

        if (!RegController.validateMail(email)) {
            errorStrJoiner.add("Пользователь с такой почтой уже существует");
        }

        if (!RegController.validatePasswords(firstPass, secondPass)) {
            errorStrJoiner.add("Пароли не совпадают");
        }

        if (errorStrJoiner.length() > 0) {
            servletAnswer.addProperty("errorMsg", errorStrJoiner.toString());
        } else {
            RegController.saveUser(email, login, name, surname, patronymic, firstPass);
            servletAnswer.addProperty("url", req.getContextPath() + "/auth.do");
        }

        String servletAnswerJson = GSON.toJson(servletAnswer);

        try (Writer output = resp.getWriter()) {
            output.write(servletAnswerJson);
            output.flush();
        }
    }
}
