package com.carte.tresor.service;

import com.carte.tresor.domain.Carte;

import java.io.IOException;
import java.util.List;

public interface FicherService {

    List<String> lectureFichier(String fichierInput) throws IOException;
    void ecritureFichier(Carte carte);
}
