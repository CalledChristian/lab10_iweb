package com.example.lab10_iweb.Servlets;

import com.example.lab10_iweb.Beans.*;
import com.example.lab10_iweb.Daos.DaoCliente;
import com.example.lab10_iweb.Daos.DaoCredentials;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletLogin", urlPatterns = {"","/ServletLogin"} )
public class ServletLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "loginform" : request.getParameter("action");

        RequestDispatcher view;

        switch (action) {
            case "loginform":
                Credentials usuario = (Credentials) request.getSession().getAttribute("usuarioSession") ;

                //&& usuario.getNumeroDocumento() != null
                if(usuario != null ){
                    if(usuario.getTipoUsuario()==1){
                        response.sendRedirect(request.getContextPath() + "/ServletAdministrador");
                    }else{
                        response.sendRedirect(request.getContextPath() + "/ServletCliente");
                    }
                }
                else {
                    view = request.getRequestDispatcher("index.jsp");
                    view.forward(request, response);
                    break;
                }

            case "logout":
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect(request.getContextPath());
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoCredentials daoCredentials = new DaoCredentials();

        //liente cliente = new Cliente();

        String username = request.getParameter("numDocumento");
        String password = request.getParameter("contrasena");

        Credentials credentials = daoCredentials.buscarUsuario(username, password);

        if(credentials != null){
            HttpSession session = request.getSession();
            session.setAttribute("usuarioSession", credentials);

            response.sendRedirect(request.getContextPath());
        }else{
            response.sendRedirect(request.getContextPath() + "/ServletLogin?error");
        }

    }
}
