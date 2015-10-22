// auteurs: Maud El-Hachem
// 2015
package drugware_v15;

public class Prescription {

	private String nomMedicament; // le médicament prescrit
	private double dose; // a quelle dose
	private int renouvellementsRestants; // le nombre de renouvellementsRestants
											// possibles

	public Prescription(String nomMedicament, double dose,
			int renouvellementsRestants) {

		this.nomMedicament = nomMedicament;
		this.dose = dose;
		this.renouvellementsRestants = renouvellementsRestants;
	}

	/**
	 * @return the nomMedicament
	 */
	public String getMedicamentAPrendre() {
		return nomMedicament;
	}

	/**
	 * @param nomMedicament
	 *            the nomMedicament to set
	 */
	public void setMedicamentAPrendre(String nomMedicament) {
		this.nomMedicament = nomMedicament;
	}

	/**
	 * @return the dose
	 */
	public double getDose() {
		return dose;
	}

	/**
	 * @param dose
	 *            the dose to set
	 */
	public void setDose(double dose) {
		this.dose = dose;
	}

	/**
	 * @return the renouvellementsRestants
	 */
	public int getRenouvellements() {
		return renouvellementsRestants;
	}

	/**
	 * @param renouvellementsRestants
	 *            the renouvellementsRestants to set
	 */
	public void setRenouvellements(int renouvellementsRestants) {
		this.renouvellementsRestants = renouvellementsRestants;
	}

	// retourne un String contenant les caractéristiques de la prescription
	public String afficherPrescription() {
		return new String(this.nomMedicament + " " + this.dose + " Renouv: "
				+ (this.renouvellementsRestants));
	}
}
