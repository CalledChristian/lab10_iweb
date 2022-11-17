package com.example.lab10_iweb.Servlets;

import com.example.lab10_iweb.Beans.*;
import com.example.lab10_iweb.Daos.DaoCredentials;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletLogin", value = "/ServletLogin")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ?
                "loginform" : request.getParameter("action");

        RequestDispatcher view;

        switch (action) {
            case "loginform":
                view = request.getRequestDispatcher("login/InicioSesion.jsp");
                view.forward(request, response);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoCredentials credentialsDaos = new DaoCredentials();

        String username = request.getParameter("inputUsuario");
        String password = request.getParameter("inputPassword");

        Credentials credentials = DaoCredentials.validarUsuarioPassword(username, password);

        if(credentials != null){
            HttpSession session = request.getSession();
            session.setAttribute("usuarioSession", credentials);

            response.sendRedirect(request.getContextPath());
        }else{
            response.sendRedirect(request.getContextPath() + "/LoginServlet?error");
        }

    }
}
