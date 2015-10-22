package drugware_v15;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class PharmacieTest {


	private Pharmacie unePharmacie;
	
	@Before
	public void setUp() throws Exception {
		unePharmacie = new Pharmacie();
	}

	@After
	public void tearDown() throws Exception {
		unePharmacie = null;
	}

	@Test
	public void testPharmacie() {
		assertNotNull(unePharmacie);
	}

	@Test
	public void testGetLesClients() {
		assertTrue(unePharmacie.getLesClients() instanceof ArrayList);
	}

	@Test
	public void testSetLesClients() {
		ArrayList<Client> listeClients = new ArrayList<Client>();
		listeClients.add(new Client("1", "premier", "deuxieme"));
		listeClients.add(new Client("2", "premier", "deuxieme"));
		unePharmacie.setLesClients(listeClients);
		assertEquals(listeClients, unePharmacie.getLesClients());
	}

	@Test
	public void testGetLesMedicaments() {
		assertTrue(unePharmacie.getLesMedicaments() instanceof ArrayList);
	}

	@Test
	public void testSetLesMedicaments() {
		ArrayList<Medicament> listeMedicaments = new ArrayList<Medicament>();
		listeMedicaments.add(new Medicament());
		listeMedicaments.add(new Medicament());
		unePharmacie.setLesMedicaments(listeMedicaments);
		assertEquals(listeMedicaments, unePharmacie.getLesMedicaments());
	}

	@Test
	public void testLireClients() {
		unePharmacie.setLesClients(new ArrayList<Client>());
		this.lireClientsStub(unePharmacie.getLesClients());
		assertEquals(3, unePharmacie.getLesClients().size());
	}

	@Test
	public void testLireMedicaments() {
		unePharmacie.setLesMedicaments(new ArrayList<Medicament>());
		this.lireMedicamentsStub(unePharmacie.getLesMedicaments());
		assertEquals(3, unePharmacie.getLesMedicaments().size());
	}

	@Test
	public void testLirePrescriptions() {
		
	}

	@Test
	public void testSiClientExiste() {
		ArrayList<Client> listeClients = new ArrayList<Client>();
		listeClients.add(new Client("1", "premier", "deuxieme"));
		unePharmacie.setLesClients(listeClients);
		assertTrue(unePharmacie.siClientExiste("1"));
	}

	@Test
	public void testGetPrescriptionsClient() {
		ArrayList<Client> listeClients = new ArrayList<Client>();
		ArrayList<Prescription> prescript = new ArrayList<Prescription>();
		Prescription pres = new Prescription("Chocolat", 2, 1);
		prescript.add(pres);
		listeClients.add(new Client("1", "premier", "deuxieme"));
		unePharmacie.setLesClients(listeClients);
		unePharmacie.getLesClients().get(0).setPrescriptions(prescript);
		assertNotNull(unePharmacie.getLesClients().get(0).getPrescriptions());
	}

	@Test
	public void testServirPrescription() {
		ArrayList<Client> listeClients = new ArrayList<Client>();
		ArrayList<Prescription> prescript = new ArrayList<Prescription>();
		Prescription pres = new Prescription("Chocolat", 2, 1);
		prescript.add(pres);
		listeClients.add(new Client("1", "premier", "deuxieme"));
		unePharmacie.setLesClients(listeClients);
		unePharmacie.servirPrescription("1", "Chocolat");
		assertNotNull(unePharmacie.getLesClients().get(0).getPrescriptions());
		
	}

	@Test
	public void testTrouverInteraction() {
		ArrayList<Medicament> listeMedicament = new ArrayList<Medicament>();
		
		Medicament med = new Medicament();
		Medicament med2 = new Medicament();
		
		med.setNomMolecule("Chocolat");
		med2.setNomMolecule("Coconut");
		
		med.setNomMarque("Coconut");
		med2.setNomMarque("Chocolat");
		
		String interaction[] = new String [1];
		String interaction2[] = new String [1];
		
		interaction[0] = med.getNomMolecule();
		interaction2[0] = med2.getNomMarque();

		med.setInteractions(interaction2);
		med2.setInteractions(interaction);
		
		listeMedicament.add(med);
		listeMedicament.add(med2);
		 
		
		unePharmacie.setLesMedicaments(listeMedicament);
		
		assertTrue(unePharmacie.trouverInteraction("Coconut", "Chocolat"));
		 
	}
	@Test
	public void testEcrireClients() {
		
	}

	@Test
	public void testEcrirePrescriptions() {
		
	}
	
	
	//--------------------------------------------------------
	//On va mettre les méthodes stub ici (2 au total)
	
	private void lireClientsStub(List<Client> listeClients)
	{
		listeClients.add(new Client("1", "test1", "test2"));
		listeClients.add(new Client("2", "test3", "test4"));
		listeClients.add(new Client("3", "test5", "test6"));
		
	}
	
	private void lireMedicamentsStub(List<Medicament> listeMedicaments)
	{
		listeMedicaments.add(new Medicament());
		listeMedicaments.add(new Medicament());
		listeMedicaments.add(new Medicament());
		
	}
	
	

}
