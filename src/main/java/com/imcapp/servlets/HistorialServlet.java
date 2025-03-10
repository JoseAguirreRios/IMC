package com.imcapp.servlets;

import com.imcapp.entities.HistorialIMC;
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
import java.util.List;

@WebServlet("/historial")
public class HistorialServlet extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        // Inicializar el EntityManagerFactory
        emf = Persistence.createEntityManagerFactory("IMCAppPU");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el usuario de la sesión
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Debes iniciar sesión primero");
            return;
        }

        // Buscar el historial del usuario en la base de datos
        EntityManager em = emf.createEntityManager();
        try {
            List<HistorialIMC> historial = em.createQuery("SELECT h FROM HistorialIMC h WHERE h.usuario.id = :usuarioId ORDER BY h.fechaCalculo DESC", HistorialIMC.class)
                                            .setParameter("usuarioId", usuario.getId())
                                            .getResultList();

            // Pasar el historial a la página JSP
            request.setAttribute("historial", historial);
            request.getRequestDispatcher("historial.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al recuperar el historial", e);
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

