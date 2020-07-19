package org.glsid.cinema.cinema;

import org.glsid.cinema.cinema.entities.Film;
import org.glsid.cinema.cinema.entities.Salle;
import org.glsid.cinema.cinema.service.CinemaInitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.io.File;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {
    @Autowired
    RepositoryRestConfiguration restConfiguration;
    @Autowired
    CinemaInitServiceImpl cinemaInitService;

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        restConfiguration.exposeIdsFor(Film.class);
        restConfiguration.exposeIdsFor(Salle.class);
        cinemaInitService.initVilles();
        cinemaInitService.initCinemas();
        cinemaInitService.initSalles();
        cinemaInitService.initPlaces();
        cinemaInitService.initCategories();
        cinemaInitService.initFilms();
        cinemaInitService.initSeances();
        cinemaInitService.initProjections();
        cinemaInitService.initTickets();
        System.out.println(System.getProperty("user.home"));

    }
}
