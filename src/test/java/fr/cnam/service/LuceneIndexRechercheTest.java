package fr.cnam.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.cnam.SpringBootWebApplication;
import fr.cnam.model.Motif;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringBootWebApplication.class)
public class LuceneIndexRechercheTest {

	@Autowired
	private LuceneIndexRecherche service;
	
	// Test libelle a partir de 2 caracteres.
	@Test
	public void testLibelleSansErreur() {
		List<Motif> resultat = service.rechercher("fr");
		Assert.assertTrue(resultat.size() == 6);
		Assert.assertTrue(resultat.get(0).getLibelle().equalsIgnoreCase("Fracture de la clavicule ou de la scapula"));	
	}

	// Test libelle avec erreurs dans l'ordre.
	@Test
	public void testLibelleAvecErreur1() {
		List<Motif> resultat = service.rechercher("D�collement");
		Assert.assertTrue(resultat.size() == 1);
		Assert.assertTrue(resultat.get(0).getLibelle().equalsIgnoreCase("D�collement et d�chirure de la r�tine"));	
	}
	
	// Test libelle avec erreurs dans le d�sordre.
	@Test
	public void testLibelleAvecErreur2() {
		List<Motif> resultat = service.rechercher("dechirure d�coement");
		Assert.assertTrue(resultat.size() == 1);
		Assert.assertTrue(resultat.get(0).getLibelle().equalsIgnoreCase("D�collement et d�chirure de la r�tine"));	
	}
	
	// Test passant sur un acronyme
	@Test
	public void testAcronymePassant(){
		List<Motif> resultat = service.rechercher("DNID");
		Assert.assertTrue(resultat.size() == 1);
		Assert.assertTrue(resultat.get(0).getLibelle().equalsIgnoreCase("Diab�te"));
	}
	
	// Test passant sur un acronyme
	@Test
	public void testAcronymePassant2(){
		List<Motif> resultat = service.rechercher("DES");
		Assert.assertTrue(resultat.size() == 1);
		Assert.assertTrue(resultat.get(0).getLibelle().equalsIgnoreCase("Diab�te"));
	}
	
	// Test nont passant sur un acronyme
	@Test
	public void testAcronymeNonPassant(){
		List<Motif> resultat = service.rechercher("DNIDs");
		Assert.assertTrue(resultat.size() == 1);
	}
	
	// Test synonyme avec pluriel
	@Test
	public void testSynonyme(){
		List<Motif> resultat = service.rechercher("DNIDs");
		Assert.assertTrue(resultat.size() == 1);
		Assert.assertTrue(resultat.get(0).getLibelle().equalsIgnoreCase("Diab�te"));
	}
	
	/**
	 * 
	 */
	@Test
	public void testGeneriquePassant(){
		
	}
}
