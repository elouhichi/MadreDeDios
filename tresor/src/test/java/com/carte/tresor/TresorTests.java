package com.carte.tresor;

import com.carte.tresor.domain.Aventurier;
import com.carte.tresor.domain.Carte;
import com.carte.tresor.domain.Coordonnees;
import com.carte.tresor.domain.Orientation;
import com.carte.tresor.service.CarteService;
import com.carte.tresor.service.FicherService;
import com.carte.tresor.service.MouvementService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TresorTests extends TresorApplicationTests{

    @Autowired
    FicherService fichierService ;

    @Autowired
    MouvementService mouvementService;

    @Autowired
    CarteService carteService;

    private Carte carte;

    @BeforeEach
    void beforeEach(){
        List<String> lignes = new ArrayList<>();
        lignes.add("C - 3 - 4");
        lignes.add("M - 1 - 0");
        lignes.add("M - 2 - 1");
        lignes.add("T - 0 - 3 - 2");
        lignes.add("T - 1 - 3 - 3");
        lignes.add("Lara - 1 - 1 - S - AADADAGGA");
        carte = carteService.creationCarte(lignes);
    }

    @Test
    void lectureFichierEntreeTest(){
        List<String> lignes = fichierService.lectureFichier("fichierEntree.txt");
        assertEquals(5, lignes.size());
    }


    @Test
    void creationCarteTest_OK(){

        Assertions.assertEquals(carte.getMontagnes().size() , 2);
        Assertions.assertEquals(carte.getTresors().size() , 2);
        Assertions.assertEquals(carte.getTaille(), Coordonnees.builder().x(3).y(4).build());
        Assertions.assertEquals(carte.getAdventuriers().size(), 1);
    }

    @Test
    void creationCarteTest_KO(){

    }

    @Test
    void ajoutMontagneTest(){

        Coordonnees coordonnees = Coordonnees.builder().x(3).y(2).build();
        carteService.ajoutMontagne(carte , coordonnees);
        Set<Coordonnees> montagnes = carte.getMontagnes();
        Assertions.assertEquals(montagnes.size() , 3);
        Assertions.assertTrue(montagnes.contains(coordonnees));
    }

    @Test
    void ajoutTresorTest(){
        Coordonnees coordonnees = Coordonnees.builder().x(2).y(2).build();
        carteService.ajoutTresor(carte , coordonnees , 2);
        Map<Coordonnees, Integer> tresors = carte.getTresors();
        Assertions.assertEquals(tresors.size() , 3);
        Assertions.assertTrue(tresors.containsKey(coordonnees));
    }

    @Test
    void ajoutAventurierTest(){
        Coordonnees coordonnees = Coordonnees.builder().x(2).y(2).build();
        Aventurier aventurier = Aventurier.builder().coordonnees(coordonnees).nom("fabien").orientation(Orientation.SUD).mouvements("GGDAGA").build();
        carteService.ajoutAventurier(carte , aventurier);
        List<Aventurier> aventuriers = carte.getAdventuriers();
        Assertions.assertEquals(aventuriers.size() , 2);
        Assertions.assertTrue(aventuriers.contains(aventurier));
    }

    @Test
    void simulationMouvementTest(){
     mouvementService.simulationMouvement(carte);
     Coordonnees coordFinalLaura = Coordonnees.builder().x(0).y(3).build();
     Assertions.assertEquals(carte.getAdventuriers().get(0).getCoordonnees(), coordFinalLaura);
    }

    @Test
    void ecritureFichierSortie(){
      fichierService.ecritureFichier(carte);
      List<String> lignes = fichierService.lectureFichier("fichierSortie.txt");
      assertEquals(5, lignes.size());

    }

}
