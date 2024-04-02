package com.carte.tresor.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Aventurier  {

    private Coordonnees coordonnees;
    private String nom;
    private Orientation orientation;
    private String mouvements;
    private int tresorsObtenus;



}
