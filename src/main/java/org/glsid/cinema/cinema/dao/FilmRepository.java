package org.glsid.cinema.cinema.dao;

import org.glsid.cinema.cinema.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestController
@CrossOrigin("*")
public interface FilmRepository extends JpaRepository<Film,Long> {
}
