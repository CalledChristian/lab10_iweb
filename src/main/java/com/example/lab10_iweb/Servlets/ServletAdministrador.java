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

        HttpSession session = (HttpSession) request.getSession();
        Credentials credentialsLogueado = (Credentials) session.getAttribute("usuarioLogueado");
        if (credentialsLogueado == null) {
            response.sendRedirect(request.getContextPath());
        }
        else {
            String action = request.getParameter("action");
            action = (action == null) ? "pantallaCliente" : action;
            DaoCliente daoClientes = new DaoCliente();

            switch (action) {
                case "pantallaCliente":
                    ArrayList<Cliente> listaClientesNoReg =daoClientes.listarClientes();

                    ArrayList<Cliente> listaClientes =daoClientes.listarClientes();

                    ArrayList<Credentials> listaClientesRegist = daoClientes.listarCredentials();
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
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("InicioAdmin.jsp");
                    requestDispatcher.forward(request,response);

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
