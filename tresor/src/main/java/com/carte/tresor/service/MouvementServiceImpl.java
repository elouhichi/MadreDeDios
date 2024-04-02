package com.carte.tresor.service;

import com.carte.tresor.domain.Aventurier;
import com.carte.tresor.domain.Carte;
import com.carte.tresor.domain.Coordonnees;
import com.carte.tresor.domain.Orientation;

import org.springframework.stereotype.Service;


import java.util.Map;

import static com.carte.tresor.domain.Orientation.E;
import static com.carte.tresor.domain.Orientation.N;
import static com.carte.tresor.domain.Orientation.O;
import static com.carte.tresor.domain.Orientation.S;

@Service
public class MouvementServiceImpl implements MouvementService {


    @Override
    public Carte simulationMouvement(Carte carte) {

        for (Aventurier aventurier : carte.getAdventuriers()) {
            for (char action : aventurier.getMouvements().toCharArray()) {
                if (action == 'A') {
                    Coordonnees newCoord = avancer(aventurier, carte);
                    if(!newCoord.equals(aventurier.getCoordonnees()) && isTresor(newCoord , carte.getTresors()) ){
                        aventurier.setTresorsObtenus(aventurier.getTresorsObtenus() + 1);
                    }
                    aventurier.setCoordonnees(newCoord);

                } else if (action == 'G') {
                    aventurier.setOrientation(tournerGauche(aventurier));
                } else if (action == 'D') {
                    aventurier.setOrientation(tournerDroite(aventurier));
                }
            }
        }
        return carte;
    }

    private boolean isValidMouvement(Coordonnees coordonnees, Carte carte) {
        int x = coordonnees.getX();
        int y = coordonnees.getY();
        return x >= 0 && x < carte.getTaille().getX() &&
                y >= 0 && y < carte.getTaille().getY() &&
                !isMontagne(carte, coordonnees);
    }

    private boolean isMontagne(Carte carte, Coordonnees coordonnees) {
        return carte.getMontagnes().contains(coordonnees);
    }

    private Coordonnees avancer(Aventurier aventurier, Carte carte) {
        int X = aventurier.getCoordonnees().getX();
        int Y = aventurier.getCoordonnees().getY();
        switch (aventurier.getOrientation()) {
            case N:
                Y--;
                break;
            case E:
                X++;
                break;
            case S:
                Y++;
                break;
            case O:
                X--;
                break;
        }
        Coordonnees coord = Coordonnees.builder().x(X).y(Y).build();
        // Vérification des limites de la carte et des obstacles
        if (isValidMouvement(coord, carte)) {
            return coord;
        }
        return aventurier.getCoordonnees();
    }

    private Orientation tournerGauche(Aventurier aventurier) {

        return switch (aventurier.getOrientation()) {
            case N -> O;
            case E -> N;
            case S -> E;
            case O -> S;
        };
    }

    private Orientation tournerDroite(Aventurier aventurier) {

        return switch (aventurier.getOrientation()) {
            case N -> E;
            case E -> S;
            case S -> O;
            case O -> N;
        };
    }

    private boolean isTresor(Coordonnees coordonnees , Map<Coordonnees, Integer> tresors){
        return tresors.keySet().stream().anyMatch(c -> c.getX() == coordonnees.getX() && c.getY() == coordonnees.getY());
    }
}
