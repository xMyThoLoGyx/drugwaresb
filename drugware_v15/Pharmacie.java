// auteurs: Maud El-Hachem
// 2015
package drugware_v15;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Pharmacie {
	private List<Client> lesClients;
	private List<Medicament> lesMedicaments;

	public Pharmacie() {
		this.lesMedicaments = new ArrayList<>();
		this.lesClients = new ArrayList<>();
	}

	/**
	 * @return the lesClients
	 */
	public List<Client> getLesClients() {
		return lesClients;
	}
	//Nouvelle fonction: get les noms des medicaments
	public ArrayList<String> getMedicamentsString()
	{
		ArrayList<String> temp = new ArrayList<String>();
		for(int i=0; i<lesMedicaments.size(); i++)
		{
			temp.add(lesMedicaments.get(i).getNomMarque());
		}
		
		return temp;
	}
	
	

	/**
	 * @param lesClients
	 *            the lesClients to set
	 */
	public void setLesClients(List<Client> lesClients) {
		this.lesClients = lesClients;
	}

	/**
	 * @return the lesMedicaments
	 */
	public List<Medicament> getLesMedicaments() {
		return lesMedicaments;
	}

	/**
	 * @param lesMedicaments
	 *            the lesMedicaments to set
	 */
	public void setLesMedicaments(List<Medicament> lesMedicaments) {
		this.lesMedicaments = lesMedicaments;
	}

	public void lireClients() {
		Fichiers fichier = new Fichiers();
		fichier.lireClients(lesClients);
	}

	public void lireMedicaments() {
		Fichiers fichier = new Fichiers();
		fichier.lireMedicaments(lesMedicaments);
	}

	public void lirePrescriptions() {
		Fichiers fichier = new Fichiers();
		fichier.lirePrescriptions(lesClients);
	}

	public boolean siClientExiste(String NAM) {
		for (Iterator<Client> it = lesClients.iterator(); it.hasNext();) {
			Client itClient = it.next();
			if (itClient.getNAM().equals(NAM)) {
				return true;
			}
		}
		return false;
	}

	public void ajouterClient(String NAM, String nom, String prenom) {
		this.lesClients.add(new Client(NAM, nom, prenom));
	}
	//!!!-----------------------------
	//					BUG
	//!!!-----------------------------
	//Bug ici - il faut sauvegarder dans un temp l'objet du Iterator
	//pour qu'on puisse y acceder

	public List<Prescription> getPrescriptionsClient(String NAM) {
		for (Iterator<Client> it = lesClients.iterator(); it.hasNext();) {
			Client temp = it.next();
			if (temp.getNAM().equals(NAM)) {
				return temp.getPrescriptions();
			}
		}
		return null;
	}
	
	//Nouveauté: on crée une nouvelle prescription
	public boolean setPrescriptionClient(String NAM, Prescription prescription)
	{
		for (Iterator<Client> it = lesClients.iterator(); it.hasNext();) {
			Client temp = it.next();
			if (temp.getNAM().equals(NAM)) {
				temp.ajouterPrescription(prescription);
				return true;
			}
		}
		return false;
	}

	public boolean servirPrescription(String NAM, String medicament) {
		boolean delivree = false;
		for (Iterator<Client> it = lesClients.iterator(); it.hasNext();) {
			Client itClient = it.next();
			if (itClient.getNAM().equals(NAM)) {
				for (Iterator<Prescription> it2 = itClient.getPrescriptions()
						.iterator(); it2.hasNext();) {
					Prescription courante = it2.next();
					if (courante.getMedicamentAPrendre().equalsIgnoreCase(
							medicament))
						if (courante.getRenouvellements() >= 1) {
							courante.setRenouvellements(courante
									.getRenouvellements() - 1);
							delivree = true;
						}
				}
			}
		}
		return delivree;
	}

	public boolean trouverInteraction(String medicament1, String medicament2) {
		for (Iterator<Medicament> it = lesMedicaments.iterator(); it.hasNext();) {
			Medicament courant = it.next();
			if (courant.getNomMolecule().equalsIgnoreCase(medicament1))
				for (int i = 0; i < courant.getInteractions().length; i++)
					if (courant.getInteractions()[i]
							.equalsIgnoreCase(medicament2))
						return true;
			if (courant.getNomMolecule().equalsIgnoreCase(medicament2))
				for (int i = 0; i < courant.getInteractions().length; i++)
					if (courant.getInteractions()[i]
							.equalsIgnoreCase(medicament2))
						return true;
		}//Bug ici - il faut retourner false si on ne voit pas d'interaction
		return false;
	}

	public void ecrireClients() {
		Fichiers fichier = new Fichiers();
		fichier.ecrireClients(lesClients);
	}

	//!!!-----------------------------
	//					BUG
	//!!!-----------------------------
	//Il faut faire ecrirePrescriptions
	public void ecrirePrescriptions() {
		Fichiers fichier = new Fichiers();
		fichier.ecrirePrescriptions(lesClients);
	}
}
