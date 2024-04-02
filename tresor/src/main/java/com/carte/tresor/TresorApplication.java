package com.carte.tresor;

import com.carte.tresor.domain.Carte;
import com.carte.tresor.service.CarteService;
import com.carte.tresor.service.FicherService;
import com.carte.tresor.service.MouvementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TresorApplication implements CommandLineRunner {

	@Autowired
	FicherService ficherService;

	@Autowired
	CarteService carteService;

	@Autowired
	MouvementService mouvementService;

	public static void main(String[] args) {
		SpringApplication.run(TresorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String inputFile = "fichierEntree.txt";
		List<String> lignes = ficherService.lectureFichier(inputFile);
		if (lignes.isEmpty()) {
			System.out.println("Le fichier d'entrée est vide.");
		} else {
			Carte carte = carteService.creationCarte(lignes);
			Carte carteFinal = mouvementService.simulationMouvement(carte);
			ficherService.ecritureFichier(carteFinal);
		}
	}

}
