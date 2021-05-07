package com.ipiecoles.java.java350.repository;

import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS) pour faire marcher le @BeforeAll avec Spring
class EmployeRepositoryTest {
    @Autowired
    private EmployeRepository employeRepository;

    @AfterEach
    @BeforeEach
    public void cleanUp(){
        employeRepository.deleteAll();
    }

    //3 Employés avec matricules différents
    @Test
    void testFindLastMatricule3Employes(){
        //Given
        employeRepository.save(new Employe("Doe", "John", "C11032",
                LocalDate.now(), Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));
        employeRepository.save(new Employe("Doe", "Jane", "M12345",
                LocalDate.now(), Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));
        employeRepository.save(new Employe("Doe", "Jim", "T12000",
                LocalDate.now(), Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));
        //When
        String lastMatricule = employeRepository.findLastMatricule();
        //Then
        Assertions.assertThat(lastMatricule).isEqualTo("12345");
    }

    //3 Employés avec matricules différents
    @Test
    void testAvgPerformanceManager(){
        //Given
        employeRepository.save(new Employe("Doe", "John", "M11032",
                LocalDate.now(), Entreprise.SALAIRE_BASE, 2, 1.0));
        employeRepository.save(new Employe("Doe", "Jane", "M12345",
                LocalDate.now(), Entreprise.SALAIRE_BASE, 1, 1.0));
        employeRepository.save(new Employe("Doe", "Jim", "M12000",
                LocalDate.now(), Entreprise.SALAIRE_BASE, 6, 1.0));
        employeRepository.save(new Employe("Doe", "Jim", "C12000",
                LocalDate.now(), Entreprise.SALAIRE_BASE, 10, 1.0));
        employeRepository.save(new Employe("Doe", "Jim", "T12000",
                LocalDate.now(), Entreprise.SALAIRE_BASE, 1, 1.0));

        String premiereLettreMatricule = "M";
        //When avec appel des vraies méthodes de repository...
        Double performance = employeRepository.avgPerformanceWhereMatriculeStartsWith(premiereLettreMatricule);

        //Then avec de vraies vérifications...
        Assertions.assertThat(performance).isEqualTo(3);
    }
}