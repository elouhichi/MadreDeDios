package com.carte.tresor.service;

import com.carte.tresor.domain.Carte;

import java.util.List;

public interface CarteService {

    Carte creationCarte(List<String> lignes);

}
