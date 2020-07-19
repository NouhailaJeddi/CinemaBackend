package org.glsid.cinema.cinema.entities;
import org.springframework.data.rest.core.config.Projection;


    @Projection(name = "ticket", types=Ticket.class)
    public interface TicketProjection {
        public Long getId();
        public String getnomclient();
        public double getPrix();
        public Integer getCodePayement();
        public boolean getReserve();
        public Place getPlace();
    }

