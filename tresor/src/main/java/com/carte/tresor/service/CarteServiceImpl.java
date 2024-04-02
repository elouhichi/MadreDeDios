package com.carte.tresor.service;

import com.carte.tresor.domain.Aventurier;
import com.carte.tresor.domain.Carte;
import com.carte.tresor.domain.Coordonnees;
import com.carte.tresor.domain.Orientation;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarteServiceImpl implements CarteService{

    Carte carte;

    @Override
    public Carte creationCarte(List<String> lignes) {
        carte = Carte.carteBuilder().build();
        for( String ligne : lignes){
            String[] splitedLigne = ligne.split(" - ");
            switch (splitedLigne[0]) {
                case "C" -> {
                    Coordonnees taille = Coordonnees.builder()
                            .x(Integer.parseInt(splitedLigne[1]))
                            .y(Integer.parseInt(splitedLigne[2]))
                            .build();
                    carte.setTaille(taille);
                }
                case "M" -> {
                    Coordonnees montagne = Coordonnees.builder()
                            .x(Integer.parseInt(splitedLigne[1]))
                            .y(Integer.parseInt(splitedLigne[2]))
                            .build();
                    this.ajoutMontagne(montagne);
                }
                case "T" -> {
                    Coordonnees tresorCoordonnes = Coordonnees.builder()
                            .x(Integer.parseInt(splitedLigne[1]))
                            .y(Integer.parseInt(splitedLigne[2]))
                            .build();
                    int nbTresor = Integer.parseInt(splitedLigne[3]);
                    this.ajoutTresor(tresorCoordonnes, nbTresor);
                }
                case "A" -> {
                    Coordonnees aventurierCoord = Coordonnees.builder()
                            .x(Integer.parseInt(splitedLigne[2]))
                            .y(Integer.parseInt(splitedLigne[3]))
                            .build();
                    Aventurier aventurier = Aventurier.builder().nom(splitedLigne[1])
                            .coordonnees(aventurierCoord)
                            .orientation(Orientation.valueOf(splitedLigne[4]))
                            .mouvements(splitedLigne[5])
                            .tresorsObtenus(0).build();
                    this.ajoutAventurier(aventurier);
                }
            }

        }
        return carte;
        }

    private void ajoutMontagne(Coordonnees coordonnees) {
       carte.getMontagnes().add(coordonnees);
    }


    private void ajoutTresor(Coordonnees coordonnees, int nbTresors) {
      carte.getTresors().put(coordonnees,nbTresors);
    }

    private void ajoutAventurier( Aventurier aventurier) {
        carte.getAdventuriers().add(aventurier);
    }

}
