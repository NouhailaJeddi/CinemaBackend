package org.glsid.cinema.cinema.dao;

import org.glsid.cinema.cinema.entities.ProjectionFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestController
@CrossOrigin("*")
public interface ProjectionFilmRepository extends JpaRepository<ProjectionFilm,Long> {
}
