// auteurs: Maud El-Hachem
// 2015
package drugware_v15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Fichiers {

	// paramètres: la liste des clients à remplir
	// ouvre le fichier des clients et remplit la liste
	// Format des données dans le fichier: Nom Prenom NAM
	public void lireClients(List<Client> lesClients) {
		try {
			//!!!-----------------------------
			//					BUG
			//!!!-----------------------------
			//BUG ICI - IL FAUT SETTER LA LISTE A NOUVELLE
			lesClients.clear();
			File monFichier = new File("Clients.txt");
			FileReader lecture = new FileReader(monFichier);
			BufferedReader reader = new BufferedReader(lecture);

			String ligne = null;
			while ((ligne = reader.readLine()) != null) {
				String infos[] = ligne.split(" ");
				String nom = infos[0];
				String prenom = infos[1];
				String NAM = infos[2];
				Client nouveauClient = new Client(NAM, nom, prenom);
				lesClients.add(nouveauClient);
			}
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	// paramètres: la liste des médicaments à remplir
	// ouvre le fichier des médicaments et remplit la liste
	// Format des données dans le fichier: 
	// Nom du médicament
	// Nom de la molécule
	// liste des usages séparés par des points-virgules
	// liste des doses possibles séparées par des points-virgules
	// unités
	// liste des molécules pouvant avoir une interaction séparées par des points-virgules
	public void lireMedicaments(List<Medicament> lesMedicaments) {
		try {
			File monFichier = new File("Medicaments.txt");
			FileReader lecture = new FileReader(monFichier);
			BufferedReader reader = new BufferedReader(lecture);

			String ligne = null;
			while ((ligne = reader.readLine()) != null) {
				Medicament medicament = new Medicament();
				// sur la 1re ligne du fichier on trouvait le nom de la marque
				medicament.setNomMarque(ligne);
				// sur la 2e ligne du fichier on trouvait le nom de la molecule
				ligne = reader.readLine();
				medicament.setNomMolecule(ligne);
				// sur la 3e ligne du fichier on trouvait les usages séparés par
				// des point-virgules
				ligne = reader.readLine();
				medicament.setUsages(ligne.split(";"));
				// sur la 4e ligne du fichier on trouvait les doses séparés par
				// des point-virgules
				ligne = reader.readLine();
				String[] dosesLues = ligne.split(";");
				for (int j = 0; j < dosesLues.length; j++)
					medicament.getDosesPossibles()[j] = Double
							.parseDouble(dosesLues[j]);
				// sur la 5e ligne du fichier on le format de la dose
				ligne = reader.readLine();
				medicament.setUnite(ligne);
				// sur la 6e ligne du fichier on trouvait les molecules qui
				// causeraient des interactions
				ligne = reader.readLine();
				medicament.setInteractions(ligne.split(";"));
				//!!!-----------------------------
				//					BUG
				//!!!-----------------------------
				//Bug ici - il faut rajouter l'objet à la liste
				lesMedicaments.add(medicament);
			}
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// paramètres: la liste des prescriptions à remplir
	// ouvre le fichier des prescriptions et remplit la liste des prescriptions pour chaque client
	// Format des données dans le fichier: NAM medicament dose renouvellements
	public void lirePrescriptions(List<Client> lesClients) {
		try {
			File monFichier = new File("prescriptions.txt");
			FileReader lecture = new FileReader(monFichier);
			BufferedReader reader = new BufferedReader(lecture);

			String ligne = null;
			while ((ligne = reader.readLine()) != null) {
				String infos[] = ligne.split(" ");
				String client = infos[0];
				String medicament = infos[1];
				double dose = Double.parseDouble(infos[2]);
				int renouvellements = Integer.parseInt(infos[3]);
				for (Iterator<Client> it = lesClients.iterator(); it.hasNext();) {
					Client itClient = it.next();
					if (itClient.getNAM().equalsIgnoreCase(client)) {
						Prescription nouvellePrescription = new Prescription(
								medicament, dose, renouvellements);
						itClient.getPrescriptions().add(nouvellePrescription);
					}
				}
			}
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
	
	// paramètres: la liste des clients
	// ouvre le fichier des clients et y écrit les infos à partir de la liste
	// Format des données dans le fichier: Nom Prenom NAM
	public void ecrireClients(List<Client> lesClients) {
		String newLine = System.getProperty("line.separator");
		try {
			FileWriter writer = new FileWriter("Clients.txt");

			for (Iterator<Client> it = lesClients.iterator(); it.hasNext();) {
				Client itClient = it.next();

				String texte = "";
				texte += itClient.getNom() + " " + itClient.getPrenom() + " "
						+ itClient.getNAM();
				texte += newLine;
				writer.write(texte);
			}
			writer.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	// paramètres: la liste des prescriptions
	// ouvre le fichier des prescriptions et y écrit les infos à partir de la liste
	// Format des données dans le fichier: NAM medicament dose renouvellements
	public void ecrirePrescriptions(List<Client> lesClients) {
		String newLine = System.getProperty("line.separator");
		try {
			FileWriter writer = new FileWriter("Prescriptions.txt");

			for (Iterator<Client> it = lesClients.iterator(); it.hasNext();) {
				Client itClient = it.next();

				for (Iterator<Prescription> it2 = itClient.getPrescriptions()
						.iterator(); it2.hasNext();) {
					Prescription courante = it2.next();
					String texte = "";
					texte += itClient.getNAM() + " "
							+ courante.getMedicamentAPrendre() + " "
							+ courante.getDose() + " "
							+ courante.getRenouvellements();
					
					texte += newLine;
					writer.write(texte);
				}
			}

			writer.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}
