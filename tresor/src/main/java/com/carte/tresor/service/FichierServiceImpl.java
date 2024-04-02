package com.carte.tresor.service;

import com.carte.tresor.domain.Aventurier;
import com.carte.tresor.domain.Carte;
import com.carte.tresor.domain.Coordonnees;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.micrometer.common.util.StringUtils;

@Service
public class FichierServiceImpl implements FicherService{
    @Override
    public List<String> lectureFichier(String fichierInput) throws IOException {
        List<String> lignes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fichierInput));
        String ligne;
        while ((ligne = reader.readLine()) != null) {
            if (!StringUtils.isEmpty(ligne) && !ligne.startsWith("#")) {
                lignes.add(ligne);
            }
        }
        reader.close();
        return lignes;
    }

    @Override
    public void ecritureFichier(Carte carte) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("fichierSortie.txt"))) {
            writer.write("C - " + carte.getTaille().getX()+ " - " + carte.getTaille().getY() + "\n");
            for (Coordonnees  m : carte.getMontagnes()) {
                writer.write("M - " + m.getX() + " - " + m.getY() + "\n");
            }
            for (Coordonnees coordonnees : carte.getTresors().keySet()) {
                int nbTresors = carte.getTresors().get(coordonnees);
                writer.write("T - " + coordonnees.getX() + " - " + coordonnees.getY() + " - " + nbTresors + "\n");

            }
            for (Aventurier aventurier : carte.getAdventuriers()) {
                writer.write("A - " + aventurier.getNom() + " - " + aventurier.getCoordonnees().getX() + " - " + aventurier.getCoordonnees().getY() + " - "
                        + aventurier.getOrientation() + " - " + aventurier.getTresorsObtenus() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
