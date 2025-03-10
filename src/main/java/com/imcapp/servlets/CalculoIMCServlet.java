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
import java.util.Date;

@WebServlet("/calculoIMC")
public class CalculoIMCServlet extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        // Inicializar el EntityManagerFactory
        emf = Persistence.createEntityManagerFactory("IMCAppPU");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el usuario de la sesión
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Debes iniciar sesión primero");
            return;
        }

        // Obtener la masa corporal del formulario
        String masaCorporalStr = request.getParameter("masaCorporal");

        // Validar que la masa corporal no sea nula o vacía
        if (masaCorporalStr == null || masaCorporalStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Masa corporal inválida");
            return;
        }

        float masaCorporal;
        try {
            masaCorporal = Float.parseFloat(masaCorporalStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Masa corporal inválida");
            return;
        }

        if (masaCorporal <= 0) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Masa corporal inválida");
            return;
        }

        // Calcular el IMC
        float imc = masaCorporal / (usuario.getEstatura() * usuario.getEstatura());

        // Guardar el cálculo en el historial
        EntityManager em = emf.createEntityManager();
        try {
            HistorialIMC historial = new HistorialIMC();
            historial.setUsuario(usuario);
            historial.setMasaCorporal(masaCorporal);
            historial.setImc(imc);
            historial.setFechaCalculo(new Date());

            em.getTransaction().begin();
            em.persist(historial);
            em.getTransaction().commit();

            // Redirigir al historial
            response.sendRedirect("historial");
        } catch (Exception e) {
            throw new ServletException("Error al guardar el cálculo", e);
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





