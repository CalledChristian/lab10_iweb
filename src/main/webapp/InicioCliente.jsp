<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String currentPage = request.getParameter("currentPage"); %>
<jsp:useBean id="usuarioLogueado" type="com.example.lab10_iweb.Beans.Credentials" scope="session" class="com.example.lab10_iweb.Beans.Credentials"/>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Inicio Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
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

        </div>
      </div>
    </nav>

    <br>
    <br>

    <div class="d-flex align-items-center p-3 my-3 text-white bg-purple rounded shadow-sm">
      <p class="font-monospace" style="color:black">Bienvenido  <%=usuarioLogueado.getNumeroDocumento()%> </p>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
  </body>
</html>

