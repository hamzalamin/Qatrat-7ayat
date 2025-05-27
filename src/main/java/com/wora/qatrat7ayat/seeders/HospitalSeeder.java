package com.wora.qatrat7ayat.seeders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wora.qatrat7ayat.models.entities.Hospital;
import com.wora.qatrat7ayat.repositories.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class HospitalSeeder implements CommandLineRunner {
    private final HospitalRepository hospitalRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        seedHospital();
    }

    private void seedHospital() throws IOException {
        if (hospitalRepository.count() == 0) {
            File file = new File("src/main/resources/JSON/hospitals.json");
            List<Hospital> hospitals = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Hospital.class));
            hospitalRepository.saveAll(hospitals);
            log.info("Hospitals seeded successfully.");

        } else {
            log.info("Hospitals already exist, skipping seeding.");
        }
    }
}
