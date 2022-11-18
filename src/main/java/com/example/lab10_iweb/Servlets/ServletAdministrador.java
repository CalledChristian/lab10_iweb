package com.example.lab10_iweb.Servlets;

import com.example.lab10_iweb.Beans.Cliente;
import com.example.lab10_iweb.Beans.Credentials;
import com.example.lab10_iweb.Daos.DaoCliente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@WebServlet(name = "ServletAdministrador", value = "/ServletAdministrador")
public class ServletAdministrador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Credentials credentialsLogueado = (Credentials) session.getAttribute("usuarioLogueado");
        if (credentialsLogueado == null) {
            response.sendRedirect(request.getContextPath());
        }
        else {
            String action = request.getParameter("action");
            action = (action == null) ? "mostrarForm" : action;
            DaoCliente daoCliente = new DaoCliente();
            RequestDispatcher view ;

            switch (action) {
                case "mostrarForm":
                    ArrayList<Cliente> listaClientesNoReg =daoCliente.listarClientes();

                    ArrayList<Cliente> listaClientes =daoCliente.listarClientes();

                    ArrayList<Credentials> listaClientesRegist = daoCliente.listarCredentials();
                    for (Cliente cliente : listaClientes) {
                        int x = 1;
                        for (Credentials credentials : listaClientesRegist){
                            if (Objects.equals(cliente.getNumeroDocumento(), credentials.getNumeroDocumento())){
                                x = 2;
                            }
                        }
                        if (x == 1){
                            listaClientesNoReg.add(cliente);
                        }
                    }

                    request.setAttribute("listaclientesNoReg", listaClientesRegist);
                    view = request.getRequestDispatcher("InicioAdmin.jsp");
                    view.forward(request,response);

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*switch (action){
            case "mostrarInicio":
                //request.setAttribute("lista", daoClientes.listarClientes());

                view = request.getRequestDispatcher("InicioCliente.jsp");
                view.forward(request, response);
            case "crear":
                requestDispatcher = request.getRequestDispatcher("clientes/formCrear.jsp");
                requestDispatcher.forward(request,response);
        }
    }*/
    }
}
