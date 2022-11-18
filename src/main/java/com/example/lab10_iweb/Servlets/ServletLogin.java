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
        HttpSession session = request.getSession();
        RequestDispatcher view;

        switch (action){
            case "login":
                Credentials credentials = (Credentials) session.getAttribute("usuarioLogueado");

                if(credentials != null){
                    response.sendRedirect(request.getContextPath());
                }else{
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);
                }
                break;
            case "logout":
                session.invalidate();
                response.sendRedirect(request.getContextPath());
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoCredentials credentialsDaos = new DaoCredentials();

        String username = request.getParameter("inputUsuario");
        String password = request.getParameter("inputPassword");

        Credentials credentials = credentialsDaos.validarUsuarioPassword(username, password);

        if(credentials != null){
            HttpSession session = request.getSession();
            session.setAttribute("usuarioSession", credentials);
            if (credentials.getTipoUsuario() ==1)
                    response.sendRedirect(request.getContextPath());
            else
            response.sendRedirect(request.getContextPath());
        }else{
            response.sendRedirect(request.getContextPath() + "/LoginServlet?error");
        }

    }
}