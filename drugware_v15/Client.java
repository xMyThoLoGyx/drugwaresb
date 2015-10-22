// auteurs: Maud El-Hachem
// 2015
package drugware_v15;

import java.util.ArrayList;
import java.util.List;

public class Client {

private String nom;		// nom du client
private String prenom;	// prénom du client
private String NAM;		// numéro de la RAMQ du client
private List<Prescription> prescriptions;	// la liste des prescriptions de ce client

public Client(String nAM, String nom, String prenom) {
	
	this.nom = nom;
	this.prenom = prenom;
	this.NAM = nAM;
	this.prescriptions = new ArrayList<>();
}

/**
 * @return the nom
 */
public String getNom() {
	return nom;
}
/**
 * @param nom the nom to set
 */
public void setNom(String nom) {
	this.nom = nom;
}
/**
 * @return the prenom
 */
public String getPrenom() {
	return prenom;
}
/**
 * @param prenom the prenom to set
 */
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
/**
 * @return the nAM
 */
public String getNAM() {
	return NAM;
}
/**
 * @param nAM the nAM to set
 */
public void setNAM(String nAM) {
	NAM = nAM;
}

public List<Prescription> getPrescriptions() {
	return prescriptions;
}

public void setPrescriptions(List<Prescription> prescriptions) {
	this.prescriptions = prescriptions;
}

//Nouveau - on rajoute une prescription
public void ajouterPrescription(Prescription prep)
{
	this.prescriptions.add(prep);
}

//retourne un String contenant les caractéristiques du client
public String afficherClient(){
		return new String(this.NAM + " " + this.nom + " "
			+ this.prenom);
}
}
