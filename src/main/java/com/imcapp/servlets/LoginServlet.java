package com.imcapp.servlets;

import com.imcapp.entities.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        // Inicializar el EntityManagerFactory
        emf = Persistence.createEntityManagerFactory("IMCAppPU");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasena = request.getParameter("contrasena");

        // Validar que los parámetros no sean nulos o vacíos
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty() || 
            contrasena == null || contrasena.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos incompletos");
            return;
        }

        // Buscar el usuario en la base de datos
        EntityManager em = emf.createEntityManager();
        try {
            Usuario usuario = em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario AND u.contrasena = :contrasena", Usuario.class)
                                .setParameter("nombreUsuario", nombreUsuario)
                                .setParameter("contrasena", contrasena)
                                .getSingleResult();

            // Guardar el usuario en la sesión
            request.getSession().setAttribute("usuario", usuario);

            // Redirigir a la página para ingresar la masa corporal
            response.sendRedirect("calculoIMC.jsp");
        } catch (Exception e) {
            // Credenciales inválidas
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Credenciales inválidas");
        } finally {
            em.close();
        }
    }

    @Override
    public void destroy() {
        // Cerrar el EntityManagerFactory al detener la aplicación
        if (emf != null) {
            emf.close();
        }
    }
}