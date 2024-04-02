package com.carte.tresor.domain;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Carte {

    private Coordonnees taille;
    private Set<Coordonnees> montagnes;
    private Map<Coordonnees, Integer> tresors;
    private List<Aventurier> adventuriers;

    @Builder(builderMethodName = "carteBuilder")
    public Carte() {
        this.montagnes = new HashSet<>();
        this.tresors = new HashMap<>();
        this.adventuriers = new ArrayList<>();
    }
}
