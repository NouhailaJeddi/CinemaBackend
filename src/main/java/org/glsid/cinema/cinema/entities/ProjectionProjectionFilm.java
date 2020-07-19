package org.glsid.cinema.cinema.entities;


import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name="projectionfilm",types={ProjectionFilm.class})
public interface ProjectionProjectionFilm {
    public Date getDateProjection();
    public double getPrix();
    public Salle getSalle();
    public Film getFilm();
    public Ticket getTickets();
    public Seance getSeance();
}
