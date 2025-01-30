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
                    new City(null, "Tanger-Tétouan-Al Hoceima", null, null),
                    new City(null, "Oriental", null, null),
                    new City(null, "Fès-Meknès", null, null),
                    new City(null, "Rabat-Salé-Kénitra", null, null),
                    new City(null, "Béni Mellal-Khénifra", null, null),
                    new City(null, "Casablanca-Settat", null, null),
                    new City(null, "Marrakech-Safi", null, null),
                    new City(null, "Drâa-Tafilalet", null, null),
                    new City(null, "Souss-Massa", null, null),
                    new City(null, "Guelmim-Oued Noun", null, null),
                    new City(null, "Laâyoune-Sakia El Hamra", null, null),
                    new City(null, "Dakhla-Oued Ed-Dahab", null, null)
            );

            cityRepository.saveAll(cities);
            System.out.println("Cities (regions) seeded successfully.");
        } else {
            System.out.println("Cities already exist, skipping seeding.");
        }
    }
}
