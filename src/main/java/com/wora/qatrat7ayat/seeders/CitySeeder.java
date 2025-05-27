package com.wora.qatrat7ayat.seeders;

import com.wora.qatrat7ayat.models.entities.City;
import com.wora.qatrat7ayat.repositories.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CitySeeder implements CommandLineRunner {
    private final CityRepository cityRepository;


    @Override
    public void run(String... args) throws Exception {
        seedCities();
    }

    private void seedCities(){
        if (cityRepository.count() == 0) {
            List<City> cities = List.of(
                    new City( "Tanger-Tétouan-Al Hoceima"),
                    new City( "Oriental"),
                    new City( "Fès-Meknès"),
                    new City( "Rabat-Salé-Kénitra"),
                    new City( "Béni Mellal-Khénifra"),
                    new City( "Casablanca-Settat"),
                    new City( "Marrakech-Safi"),
                    new City( "Drâa-Tafilalet"),
                    new City( "Souss-Massa"),
                    new City( "Guelmim-Oued Noun"),
                    new City( "Laâyoune-Sakia El Hamra"),
                    new City( "Dakhla-Oued Ed-Dahab")
            );

            cityRepository.saveAll(cities);
            log.info("Cities (regions) seeded successfully.");
        } else {
            log.info("Cities already exist, skipping seeding.");
        }
    }
}
