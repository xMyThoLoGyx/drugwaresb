// auteurs: Maud El-Hachem
// 2015
package drugware_v15;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Fenetre extends JFrame {
	private JMenuBar menuBar;
	private JMenu menuFic;
	private JMenu menuClients;
	private JMenu menuPresc;
	private JMenu menuMedic;

	private JMenuItem itemFic1;
	private JMenuItem itemFic2;
	private JMenuItem itemFic3;

	private JMenuItem itemClients1;
	private JMenuItem itemClients2;

	private JMenuItem itemPresc1;
	private JMenuItem itemPresc2;
	//Nouveau
	private JMenuItem itemPresc3;

	private JMenuItem itemMedic1;
	private JMenuItem itemMedic2;

	private Pharmacie pharma;
	
	private boolean chargementFait = false;

	public Fenetre() {
		this.setSize(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		menuBar = new JMenuBar();
		menuFic = new JMenu("Fichier");
		menuClients = new JMenu("Clients");
		menuPresc = new JMenu("Prescriptions");
		menuMedic = new JMenu("Médicaments");

		itemFic1 = new JMenuItem("Charger les fichiers");
		itemFic2 = new JMenuItem("Mettre à jour les fichiers");
		itemFic3 = new JMenuItem("Quitter");

		itemClients1 = new JMenuItem("Inscrire un nouveau client");
		itemClients2 = new JMenuItem("Afficher tous les clients");

		itemPresc1 = new JMenuItem("Afficher les prescriptions d'un client");
		itemPresc2 = new JMenuItem("Servir une prescription");
		//Nouveauté ici - on va donner une prescription
		itemPresc3 = new JMenuItem("Créer une prescription");
		

		itemMedic1 = new JMenuItem("Afficher tous les médicaments");
		itemMedic2 = new JMenuItem("Afficher si interaction");

		pharma = new Pharmacie();

	}

	public void initMenus() {

		// Menu fichier
		itemFic1.addActionListener(new BoutonFic1Listener());
		this.menuFic.add(itemFic1);
		itemFic2.addActionListener(new BoutonFic2Listener());
		this.menuFic.add(itemFic2);

		// Ajout d'un séparateur
		this.menuFic.addSeparator();
		// si quitter
		itemFic3.addActionListener(new BoutonFic3Listener());
		this.menuFic.add(itemFic3);

		// Menu Clients
		itemClients1.addActionListener(new BoutonClient1Listener());
		//!!!-----------------------------
		//					BUG
		//!!!-----------------------------
		//Bug ici - il faut donner un listener au itemClients2
		itemClients2.addActionListener(new BoutonClient2Listener());
		
		
		this.menuClients.add(itemClients1);
		this.menuClients.add(itemClients2);

		// Menu Prescriptions
		itemPresc1.addActionListener(new BoutonPresc1Listener());
		this.menuPresc.add(itemPresc1);
		itemPresc2.addActionListener(new BoutonPresc2Listener());
		this.menuPresc.add(itemPresc2);
		//Nouveaute
		itemPresc3.addActionListener(new BoutonPresc3Listener());
		this.menuPresc.add(itemPresc3);

		// Menu Médicaments
		itemMedic1.addActionListener(new BoutonMedic1Listener());
		this.menuMedic.add(itemMedic1);
		itemMedic2.addActionListener(new BoutonMedic2Listener());
		this.menuMedic.add(itemMedic2);

		this.menuBar.add(menuFic);
		this.menuBar.add(menuClients);
		this.menuBar.add(menuPresc);
		this.menuBar.add(menuMedic);
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		
		
		//On va disable quelques options avant de charger
		this.itemFic2.setEnabled(false);
		this.itemClients2.setEnabled(false);
		this.itemPresc1.setEnabled(false);
		this.itemPresc2.setEnabled(false);
		this.itemMedic1.setEnabled(false);
		this.itemMedic2.setEnabled(false);

	}

	public class BoutonFic1Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pharma.lireClients();
			pharma.lirePrescriptions();
			pharma.lireMedicaments();
			chargementFait = true;
			
			//On va enablequelques options avant de charger
			itemFic2.setEnabled(true);
			itemClients2.setEnabled(true);
			itemPresc1.setEnabled(true);
			itemPresc2.setEnabled(true);
			itemMedic1.setEnabled(true);
			itemMedic2.setEnabled(true);
		}
	}

	public class BoutonFic2Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pharma.ecrireClients();
			pharma.ecrirePrescriptions();
		}
	}

	public class BoutonFic3Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
	}

	public class BoutonClient1Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String nom, prenom;
			String NAM = JOptionPane.showInputDialog(null,
					"Entre le numéro d'assurance maladie",
					"Numéro d'assurance maladie", JOptionPane.QUESTION_MESSAGE);
			if (NAM != null && NAM.length() > 0)
				if (pharma.siClientExiste(NAM))
					JOptionPane.showMessageDialog(null,
							"Ce numéro d'assurance maladie existe déjà",
							"Problème", JOptionPane.INFORMATION_MESSAGE);
				else {
					nom = JOptionPane.showInputDialog(null, "Entre le nom",
							"Nom", JOptionPane.QUESTION_MESSAGE);
					prenom = JOptionPane.showInputDialog(null,
							"Entre le prenom", "Prénom",
							JOptionPane.QUESTION_MESSAGE);
					if (nom != null && nom.length() > 0 && prenom != null
							&& prenom.length() > 0)
						pharma.ajouterClient(NAM, nom, prenom);
				}
		}
	}

	public class BoutonClient2Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JFrame nouvelle = new JFrame("Clients");
			int nbClients = pharma.getLesClients().size();
			JPanel panneau = new JPanel();
			panneau.setLayout(new GridLayout(nbClients, 1));
			panneau.setBackground(Color.white);
			for (Iterator<Client> it = pharma.getLesClients().iterator(); it.hasNext();) {
				Client courant = it.next();
				JLabel label = new JLabel();
				label.setText("<html><p style='font-size:14px'>"
						+ courant.afficherClient() + "</p></html>");
				panneau.add(label);
			}
			nouvelle.add(panneau);
			nouvelle.setSize(400, nbClients*100);
			nouvelle.setVisible(true);
			nouvelle.setLocationRelativeTo(null);

		}
	}

	public class BoutonPresc1Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String NAM = JOptionPane.showInputDialog(null,
					"Entre le numéro d'assurance maladie",
					"Numéro d'assurance maladie", JOptionPane.QUESTION_MESSAGE);
			if (NAM != null && NAM.length() > 0) {
				List<Prescription> liste = pharma.getPrescriptionsClient(NAM);
				if (liste != null) {
					JFrame nouvelle = new JFrame("Prescription du client");
					int nbPresc = liste.size();
					JPanel panneau = new JPanel();
					panneau.setLayout(new GridLayout(nbPresc, 1));
					panneau.setBackground(Color.white);
					for (Iterator<Prescription> it = liste.iterator(); it
							.hasNext();) {
						Prescription courant = it.next();
						JLabel label = new JLabel();
						label.setText("<html><p style='font-size:14px'>"
								+ courant.afficherPrescription() + "</p></html>");
						panneau.add(label);
					}
					//!!!-----------------------------
					//					BUG
					//!!!-----------------------------
					//Bug ici - il manquait nouvelle.add(panneau) donc on ne voyait rien
					nouvelle.add(panneau);
					nouvelle.setSize(400, nbPresc*100);
					nouvelle.setVisible(true);
					nouvelle.setLocationRelativeTo(null);
				}

			}
		}
	}

	public class BoutonPresc2Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String NAM = JOptionPane.showInputDialog(null,
					"Entre le numéro d'assurance maladie",
					"Prescription", JOptionPane.QUESTION_MESSAGE);
			String medicament = JOptionPane.showInputDialog(null,
					"Entre le nom du médicament",
					"Prescription", JOptionPane.QUESTION_MESSAGE);
			
			
			
		if (pharma.servirPrescription(NAM, medicament)){
			
			
			String message = "";
			
			
			double doses = 0;
			String dosesMessage[] = new String[10];
			double [] dosesPossibles = new double[10];
			
			for(int i = 0; i < pharma.getLesMedicaments().size(); i++){					
				if(pharma.getLesMedicaments().get(i).getNomMarque().matches(medicament)){					
					dosesPossibles = pharma.getLesMedicaments().get(i).getDosesPossibles();
				}
			
			}
			
			for(int i = 0; i < pharma.getPrescriptionsClient(NAM).size(); i++){		
				if(pharma.getPrescriptionsClient(NAM).get(i).getMedicamentAPrendre().matches(medicament)){
					doses = pharma.getPrescriptionsClient(NAM).get(i).getDose();
				}
			
			}
			
		
		int index = dosesPossibles.length - 1;
		int j = 0; 	
		boolean dose1trouve = false;
		
		if(dosesPossibles[0] > doses){
			dosesMessage[j] = String.valueOf(dosesPossibles[0]);
			message = "Il serait suggéré de donner une dose de " + dosesMessage[j] + " mg."; 				
		}				
		
		for(int i = index ; i >= 0 ; i--){
		
			if(dosesPossibles[i] != 0 && (doses - dosesPossibles[i]) >= 0){	
				dosesMessage[j] = String.valueOf(dosesPossibles[i]);
				doses = doses - dosesPossibles[i];
				j++;
			}

		}
		
		message = "Il serait suggéré de donner une dose de " + dosesMessage[0] + " mg."; 
	
		
		int indice = 1;
			do{	
				
				if(dosesMessage[indice] != null){
					message = message + " Avec une autre dose de " + dosesMessage[indice] + " mg.";
					indice++;
				}
		
			}while(dosesMessage[indice] != null);
		
		
			
			
		JOptionPane.showMessageDialog(null, message, "Prescription",JOptionPane.INFORMATION_MESSAGE);
			
			
			
			
			
			
				
				
				JOptionPane.showMessageDialog(null,
						"Prescription servie!",
						"Prescription", JOptionPane.INFORMATION_MESSAGE);
		}else 
				JOptionPane.showMessageDialog(null,
						"Il n'est pas possible de servir la prescription",
						"Prescription", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	//Nouveauté - on va rajouter une prescription avec la liste 
	public class BoutonPresc3Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			String NAM = JOptionPane.showInputDialog(null,
					"Entre le numéro d'assurance maladie",
					"Prescription", JOptionPane.QUESTION_MESSAGE);
			Object[] optionsNoms = pharma.getMedicamentsString().toArray();
			
			String medicament = (String) JOptionPane.showInputDialog(null,
					"Entre le nom du médicament",
					"Prescription", JOptionPane.QUESTION_MESSAGE, null,  optionsNoms, null );
			
			
			//Ici avec les doses suggérés il faut faire attention
			//pour que l'item reçu soit vraiment un double
			//!!! NULL POINTER EXCEPTION SI RIEN RENTRE!
			double Dose = Double.parseDouble(JOptionPane.showInputDialog(null,
					"Entre le dosage",
					"Prescription", JOptionPane.QUESTION_MESSAGE));
			
			int prescriptionsRestantes = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Entre le nombre de renouvellements", 
					"Prescription", JOptionPane.QUESTION_MESSAGE, null, 
					new Object[] {"1", "2", "3", "4", "5", "6", "7","8","9","10"}, null));
			
			//Créer un object prescription
			Prescription nouvellePrescription = new Prescription(medicament, Dose, prescriptionsRestantes);
			if(pharma.setPrescriptionClient(NAM, nouvellePrescription))
			{
				JOptionPane.showMessageDialog(null,
						"Prescription crée!",
						"Prescription", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Impossible de créer la prescription!",
						"Prescription", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public class BoutonMedic1Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JFrame nouvelle = new JFrame("Médicaments");
			int nbMedic = pharma.getLesMedicaments().size();
			JPanel panneau = new JPanel();
			panneau.setLayout(new GridLayout(nbMedic, 1));
			panneau.setBackground(Color.white);
			for (Iterator<Medicament> it = pharma.getLesMedicaments()
					.iterator(); it.hasNext();) {
				Medicament courant = it.next();
				JLabel label = new JLabel();
				label.setText("<html><p style='font-size:14px'>"
						+ courant.getNomMolecule() + " "
						+ courant.getNomMarque() + "</p></html>");
				panneau.add(label);
			}
			nouvelle.add(panneau);
			nouvelle.setSize(400, nbMedic*100);
			nouvelle.setVisible(true);
			nouvelle.setLocationRelativeTo(null);

		}
	}

	public class BoutonMedic2Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String medicament1 = JOptionPane.showInputDialog(null,
					"Entre le nom de la molécule ou du médicament no 1",
					"Interactions", JOptionPane.QUESTION_MESSAGE);
			String medicament2 = JOptionPane.showInputDialog(null,
					"Entre le nom de la molécule ou du médicament no 2",
					"Interactions", JOptionPane.QUESTION_MESSAGE);
			if (pharma.trouverInteraction(medicament1, medicament2))
				JOptionPane.showMessageDialog(null,
						"Interaction trouvée! Faites attention!",
						"Interactions", JOptionPane.INFORMATION_MESSAGE);
			else 
				JOptionPane.showMessageDialog(null,
						"Aucune interaction trouvée!",
						"Interactions", JOptionPane.INFORMATION_MESSAGE);
		}
	}



}