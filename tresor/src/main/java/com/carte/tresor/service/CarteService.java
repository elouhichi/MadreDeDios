package com.carte.tresor.service;

import com.carte.tresor.domain.Aventurier;
import com.carte.tresor.domain.Carte;
import com.carte.tresor.domain.Coordonnees;

import java.util.List;

public interface CarteService {

    Carte creationCarte(List<String> lignes);
    void ajoutMontagne(Carte carte , Coordonnees coordonnees);
    void ajoutTresor(Carte carte , Coordonnees coordonnees , int nbTresors);
    void ajoutAventurier(Carte carte , Aventurier aventurier);

}
