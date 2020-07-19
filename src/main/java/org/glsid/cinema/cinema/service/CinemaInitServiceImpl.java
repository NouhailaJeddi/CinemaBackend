package org.glsid.cinema.cinema.service;

import org.glsid.cinema.cinema.ICinemaInitService;
import org.glsid.cinema.cinema.dao.*;
import org.glsid.cinema.cinema.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService {
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ProjectionFilmRepository projectionFilmRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private TicketRepository TicketRepo;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");


    @Override
    public void initVilles() {
        Stream.of("Casablanca","Marrakech","Rabat","Nador").forEach(nomville->{
        Ville ville=new Ville();
        ville.setName(nomville);
        villeRepository.save(ville);
    });
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(ville -> {
            Stream.of("Megarama","Imax","Colisee","Chahrazad","ChoCho").forEach(nomCinema->{
        Cinema cinema=new Cinema();
        cinema.setName(nomCinema);
        cinema.setNbsalles(3+(int)(Math.random()*10));
        cinema.setAltitude(3+(int)(Math.random()*10)+Math.random()*10);
        cinema.setLatitude(3+(int)(Math.random()*10)+Math.random()*10);
        cinema.setLongtitude(3+(int)(Math.random()*10)+Math.random()*10);
        cinema.setVille(ville);
        cinemaRepository.save(cinema);
    });
});
    }

    @Override
    public void initSalles() {
    cinemaRepository.findAll().forEach(cinema -> {
        Stream.of("Salle1","Salle2","Salle3","Salle4").forEach(salle_name->{
            Salle s=new Salle();
            s.setCinema(cinema);
            s.setName(salle_name);
            s.setNbplace(7+(int)(Math.random()*10));
            salleRepository.save(s);
        });

    });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for (int i = 0; i < salle.getNbplace(); i++)
            {
                Place p = new Place();
                p.setNum(i);
                p.setAltitude(1 + (int) (Math.random() * 10) + Math.random() * 10);
                p.setLatitude(1 + (int) (Math.random() * 10) + Math.random() * 10);
                p.setLongtitude(1 + (int) (Math.random() * 10) + Math.random() * 10);
                p.setSalle(salle);

                placeRepository.save(p);

            }

        });
    }

    @Override
    public void initSeances() {
        DateFormat dateFormat= new SimpleDateFormat("HH:mm");
        Stream.of("12:00","15:00","17:00","19:00","21:00").forEach(s->{
            Seance seance =new Seance();
            try {
                seance.setHeureDebut(dateFormat.parse(s));
                seanceRepository.save(seance);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

    }

    @Override
    public void initCategories() {
        Stream.of("Histoire","Romance","Fiction","Drame").forEach(cat->{
            Categorie categorie = new Categorie();
            categorie.setName(cat);
            categorieRepository.save(categorie);

        });

    }

    @Override
    public void initFilms() {
        double[] durees= new double[] {1,1.5,2.5,3};
        List<Categorie> categories = categorieRepository.findAll();
        // TODO Auto-generated method stub
        Stream.of("A fall from grace","Ishaqzaade","The stonning of Soraya","Blood Diamond","oteki taraf","Prison Break","UnaBomber","Inception","Shutter Island","the great gatsby")
                .forEach(titreFilm->{
                    Film film =new Film();
                    film.setTitre(titreFilm);
                    film.setDuree(durees[new Random().nextInt(durees.length)]);
                    film.setPhoto(titreFilm.replaceAll(" ","")+".jpg");
                    film.setCategorie(categories.get(new Random().nextInt(categories.size())));
                    filmRepository.save(film);
                });
    }

    @Override
    public void initProjections() {
        double [] prices = new double [] {30,50,60,70,90,100};
        List<Film> films= filmRepository.findAll();

        villeRepository.findAll().forEach(ville->{
            ville.getCinemas().forEach(cinema->{
                cinema.getSalles().forEach(salle->{
                    int index = new Random().nextInt(films.size());
                    Film film=films.get(index);
                    seanceRepository.findAll().forEach(seance->{
                        ProjectionFilm projection = new ProjectionFilm();
                        projection.setDateProjection(new Date());
                        projection.setFilm(film);
                        projection.setPrix(prices[new Random().nextInt(prices.length)]);
                        projection.setSalle(salle);
                        projection.setSeance(seance);
                        projectionFilmRepository.save(projection);
                    });
                });
            });
        });
    }

    @Override
    public void initTickets() {
        projectionFilmRepository.findAll().forEach(p->{
            p.getSalle().getPlaces().forEach(place->{
                Ticket ticket =new Ticket();
                ticket.setPlace(place);
                ticket.setPrix(p.getPrix());
                ticket.setProjectionFilm(p);
                ticket.setReserve(false);
                TicketRepo.save(ticket);
            });
        });



    }
}
