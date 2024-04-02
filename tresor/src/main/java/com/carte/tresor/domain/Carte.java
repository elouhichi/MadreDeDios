package com.carte.tresor.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Carte {

    private Coordonnees taille;
    private Set<Coordonnees> montagnes;
    private Map<Coordonnees, Integer> tresors;
    private List<Aventurier> adventuriers;


}
