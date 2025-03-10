package com.imcapp.services;

import com.imcapp.entities.HistorialIMC;
import com.imcapp.entities.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/historial")
public class HistorialIMCService {

    @PersistenceContext(unitName = "IMCAppPU")
    private EntityManager em;

    @GET
    @Path("/{usuarioId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HistorialIMC> getHistorial(@PathParam("usuarioId") int usuarioId) {
        return em.createQuery("SELECT h FROM HistorialIMC h WHERE h.usuario.id = :usuarioId", HistorialIMC.class)
                 .setParameter("usuarioId", usuarioId)
                 .getResultList();
    }
}
