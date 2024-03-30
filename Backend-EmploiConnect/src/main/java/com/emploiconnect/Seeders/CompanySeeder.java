package com.emploiconnect.Seeders;


import com.emploiconnect.entity.Company;
import com.emploiconnect.repository.CompanyRepository;
import com.emploiconnect.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class CompanySeeder implements CommandLineRunner {
    private final CompanyRepository companyRepository;
    private final CompanyService companyService;
    @Override
    public void run(String... args) {
        if (companyRepository.count() == 0) {
            seedCompanies();
        }
    }

    private void seedCompanies(){
        Company defaultCompany= Company.builder()
                .name("Default Company")
                .sector("Technology")
                .location("Anytown, USA")
                .foundationDate(LocalDate.of(2000, 1, 1))
                .specializations("Software Development, IT Services")
                .description("This is the default company used for examples.")
                .isDefault(true)
                .build();
        Company sofrecomMaroc = Company.builder()
                .name("Sofrecom Maroc")
                .sector("Services et conseil en informatique")
                .location("Sala Al Jadida, Maroc")
                .foundationDate(LocalDate.of(2000, 1, 1))
                .specializations("Développement & Intégration de services, Bureau d'études Très Haut Débit, Digital, Centre de Services et Ingénierie")
                .description("Depuis sa création en 2000, Sofrecom Maroc s’est imposé localement comme acteur incontournable des nouvelles technologies de l’information et de la communication à travers ses activités d’ingénierie logicielle")
                .isDefault(false)
                .build();
        Company itRoadConsulting = Company.builder()
                .name("IT Road Consulting")
                .sector("Services et conseil en informatique")
                .location("Casablanca, Grand Casablanca")
                .foundationDate(LocalDate.of(2012, 1, 1))
                .specializations("Conseil et Audit SI, Services d'Ingénierie Informatique, Digital Market, Développement Web/Mobile, Business Intelligence, Edition SI et Formation IT")
                .description("Acteur innovant dans le domaine des systèmes d’information, IT Road Consulting a mobilisé les meilleures compétences pour répondre aux impératifs de performance de ses clients. Taille de l’entreprise : 51-200 employés. Siège social : Casablanca, Grand Casablanca.")
                .isDefault(false)
                .build();
        Company cegedimMaroc = Company.builder()
                .name("Cegedim Maroc")
                .sector("Services et conseil en informatique")
                .location("Agadir,Maroc")
                .foundationDate(LocalDate.of(2008, 1, 1))
                .specializations("Gestion des flux numériques de l’écosystème santé et BtoB")
                .description("Taille de l’entreprise : 5 001-10 000 employés. Cegedim Maroc est un acteur majeur dans le secteur des services et du conseil en informatique, spécialisé dans la gestion des flux numériques de l’écosystème santé et BtoB, ainsi que dans la conception de logiciels métier destinés aux professionnels de santé et de l’assurance.")
                .isDefault(false)
                .build();
        Company intelciaITSolutions = Company.builder()
                .name("Intelcia IT Solutions")
                .sector("Services et conseil en informatique")
                .location("Casablanca, Maroc")
                .foundationDate(LocalDate.of(2020, 1, 1))
                .specializations(" IT Services, Consulting.")
                .description("Intelcia IT Solutions est le pôle d’expertise IT du groupe Intelcia avec 4 piliers de services : - Services managés - Solutions Applicatives - Business Intelligence - Consulting")
                .isDefault(false)
                .build();

        companyService.save(defaultCompany,true);
        companyService.save(sofrecomMaroc,true);
        companyService.save(itRoadConsulting,true);
        companyService.save(cegedimMaroc,true);
        companyService.save(intelciaITSolutions,true);
    }



}
