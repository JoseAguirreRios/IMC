<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calcular IMC - IMC App</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
        body {
            background-image: url('resources/images/imc.jpg'); /* Ruta de la imagen */
            background-size: cover; /* Cubre toda la pantalla */
            background-position: center; /* Centra la imagen */
            background-repeat: no-repeat; /* Evita que se repita la imagen */
            background-attachment: fixed; /* Fija la imagen al desplazarse */
            color: white; /* Cambia el color del texto para que sea legible */
        }

        .card {
            background-color: rgba(0, 0, 0, 0.7); /* Fondo semitransparente para la tarjeta */
            color: white; /* Texto blanco dentro de la tarjeta */
        }

        .navbar {
            background-color: rgba(0, 0, 0, 0.8); /* Fondo semitransparente para la barra de navegación */
        }
    </style>
</head>
<body>
    <!-- Barra de navegación -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="index.html">IMC App</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp">Iniciar Sesión</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="registro.jsp">Registrarse</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Contenido principal -->
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h3 class="text-center">Calcular IMC</h3>
                    </div>
                    <div class="card-body">
                        <!-- Mostrar mensaje de error -->
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger" role="alert">
                                ${error}
                            </div>
                        </c:if>

                        <!-- Formulario para calcular el IMC -->
                        <form action="calculoIMC" method="post">
                            <div class="mb-3">
                                <label for="masaCorporal" class="form-label">Masa Corporal (kg)</label>
                                <input type="number" step="0.01" class="form-control" id="masaCorporal" name="masaCorporal" required>
                            </div>
                            <button type="submit" class="btn btn-primary w-100">Calcular IMC</button>
                        </form>
                        <p class="text-center mt-3"><a href="historial.jsp">Ver historial de IMC</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS (opcional, solo si necesitas funcionalidades como modales, tooltips, etc.) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
