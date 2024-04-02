package com.carte.tresor;

import com.carte.tresor.domain.Carte;
import com.carte.tresor.domain.Coordonnees;
import com.carte.tresor.service.CarteService;
import com.carte.tresor.service.FicherService;
import com.carte.tresor.service.MouvementService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TresorTests extends TresorApplicationTests {

    @Autowired
    FicherService fichierService;

    @Autowired
    MouvementService mouvementService;

    @Autowired
    CarteService carteService;

    private Carte carte;
    List<String> lignes = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        lignes.add("C - 3 - 4");
        lignes.add("M - 1 - 0");
        lignes.add("M - 2 - 1");
        lignes.add("T - 0 - 3 - 2");
        lignes.add("T - 1 - 3 - 3");
        lignes.add("A - Lara - 1 - 1 - S - AADADAGGA");
        carte = carteService.creationCarte(lignes);
    }

    @Test
    void lectureFichierEntreeTest() throws IOException {
        List<String> lignes = fichierService.lectureFichier("fichierEntree.txt");
        assertEquals(6, lignes.size());
    }


    @Test
    void creationCarteTest_OK() {
        Carte carte = carteService.creationCarte(lignes);
        Coordonnees expectedTaille = Coordonnees.builder().x(3).y(4).build();
        assertEquals(carte.getMontagnes().size(), 2);
        assertEquals(carte.getTresors().size(), 2);
        assertEquals(carte.getTaille().getX(), expectedTaille.getX());
        assertEquals(carte.getTaille().getY(), expectedTaille.getY());
        assertEquals(carte.getAdventuriers().size(), 1);
    }


    @Test
    void simulationMouvementTest() {
        mouvementService.simulationMouvement(carte);
        Coordonnees coordFinalLaura = Coordonnees.builder().x(0).y(3).build();
        assertEquals(carte.getAdventuriers().get(0).getCoordonnees().getX(), coordFinalLaura.getX());
        assertEquals(carte.getAdventuriers().get(0).getCoordonnees().getY(), coordFinalLaura.getY());
        assertEquals(carte.getAdventuriers().get(0).getTresorsObtenus() , 3);
    }

    @Test
    void ecritureFichierSortie() throws IOException {
        fichierService.ecritureFichier(carte);
        List<String> lignes = fichierService.lectureFichier("fichierSortie.txt");
        assertEquals(6, lignes.size());

    }
}
