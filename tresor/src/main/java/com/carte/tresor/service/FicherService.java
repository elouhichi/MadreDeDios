package com.carte.tresor.service;

import com.carte.tresor.domain.Carte;

import java.util.List;

public interface FicherService {

    List<String> lectureFichier(String inputFile);
    void ecritureFichier(Carte carte);
}
