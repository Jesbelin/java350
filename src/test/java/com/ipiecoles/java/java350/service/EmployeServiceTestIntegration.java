package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeServiceTestIntegration {
    @Autowired
    EmployeService employeService;

    @Autowired
    EmployeRepository employeRepository;

    @BeforeEach
    @AfterEach
    public void cleanUp(){
        employeRepository.deleteAll();
    }

    @Test
    void embaucheEmployePleinTempsManagerIngenieur() throws Exception{
        // Given
        String nom = "Jean";
        String prenom = "Aurore";
        Poste poste = Poste.MANAGER;
        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
        Double tempsPartiel = 1d;

        //When -> avec appel des vraies méthodes de repository...
        employeService.embaucheEmploye(nom, prenom, poste, niveauEtude,tempsPartiel);

        //Then -> avec de vraies vérifications...
        Employe employe = employeRepository.findByMatricule("M00001");
        //Pour ne pas avoir de NullPointerException, on vérifie que l'employé existe
        Assertions.assertThat(employe).isNotNull();
        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo(LocalDate.now());
        Assertions.assertThat(employe.getMatricule()).isEqualTo("M00001");
        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(tempsPartiel);
        //1521.22 * 1.6
        Assertions.assertThat(employe.getSalaire()).isEqualTo(2433.95d);
    }
}