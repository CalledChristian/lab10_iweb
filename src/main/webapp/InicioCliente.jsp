<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String currentPage = request.getParameter("currentPage"); %>
<jsp:useBean id="usuarioSession" type="com.example.lab10_iweb.Beans.Credentials" scope="session" class="com.example.lab10_iweb.Beans.Credentials"/>
<html>
  <head>
    <title>Inicio</title>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark" aria-label="Main navigation">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Inicio Cliente</a>
        <button class="navbar-toggler p-0 border-0" type="button" id="navbarSideCollapse" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="navbar-collapse offcanvas-collapse" id="navbarsExampleDefault">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/ServletCliente?action=mostrarDatos">Mis Datos</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/ServletCliente?action=mostrarContratos">Mis Contratos</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/ServletCliente?action=mostrarContratosPorEstado">Mis Contratos Por Estado</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<%=request.getContextPath()%>/ServletCliente?action=mostrarMaxExpectedLoss">Mostrar Puntaje Máximo de Expeceted Loss por Contrato</a>
            </li>

          </ul>
          <form class="d-flex" role="search">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
          </form>
        </div>
      </div>
    </nav>

    <br>
    <br>

    <div class="d-flex align-items-center p-3 my-3 text-white bg-purple rounded shadow-sm">
      <p class="font-monospace" style="color:white">Bienvenido  <%=usuarioSession.getNumeroDocumento()%></p>
    </div>



  </body>
</html>

