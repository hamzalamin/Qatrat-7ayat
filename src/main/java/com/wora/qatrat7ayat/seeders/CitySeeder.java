package com.wora.qatrat7ayat.seeders;

import com.wora.qatrat7ayat.models.entities.City;
import com.wora.qatrat7ayat.repositories.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
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
            System.out.println("Cities (regions) seeded successfully.");
        } else {
            System.out.println("Cities already exist, skipping seeding.");
        }
    }
}
