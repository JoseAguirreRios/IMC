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

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        // Inicializar el EntityManagerFactory
        emf = Persistence.createEntityManagerFactory("IMCAppPU");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreCompleto = request.getParameter("nombreCompleto");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String sexo = request.getParameter("sexo");
        float estatura = Float.parseFloat(request.getParameter("estatura"));
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasena = request.getParameter("contrasena");

        // Validaciones
        if (edad < 15 || estatura < 1 || estatura > 2.5) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos inválidos");
            return;
        }

        // Crear y guardar el usuario
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Usuario usuario = new Usuario();
            usuario.setNombreCompleto(nombreCompleto);
            usuario.setEdad(edad);
            usuario.setSexo(sexo);
            usuario.setEstatura(estatura);
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setContrasena(contrasena);

            em.persist(usuario);
            em.getTransaction().commit();

            response.sendRedirect("login.jsp"); // Redirigir al login después del registro
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new ServletException("Error al registrar el usuario", e);
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