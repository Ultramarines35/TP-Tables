package fr.istic.prav.tables.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import fr.istic.prav.tables.SocialNetwork;
import istic.prg.table_ens.Set;

public class TestSocialNetwork {
	public SocialNetwork initExemple() {
		// retourne la table SocialNetwork correspondant à l'exemple du TD
		SocialNetwork exGraphe = new SocialNetwork();
		Set<Integer> successeurs1= new Set<Integer>();
		successeurs1.add(2); successeurs1.add(3); successeurs1.add(5);
		exGraphe.addValue(1, successeurs1);
		Set<Integer> successeurs3= new Set<Integer>(); successeurs3.add(6); 
		exGraphe.addValue(3, successeurs3);
		Set<Integer> successeurs5= new Set<Integer>(); successeurs5.add(5);  successeurs5.add(7); 
		exGraphe.addValue(5, successeurs5);
		Set<Integer> successeurs6= new Set<Integer>(); successeurs6.add(2); successeurs6.add(5);  successeurs6.add(7); 
		exGraphe.addValue(6, successeurs6);
		Set<Integer> successeurs7= new Set<Integer>(); successeurs7.add(6);
		exGraphe.addValue(7, successeurs7);
		System.out.println(exGraphe);
		return exGraphe;
	}
	
	
	@Test
	public void testIsDefined1() {
		SocialNetwork net = initExemple();
		boolean resulV = net.isDefined(1,2);
		boolean resulF = net.isDefined(2,1);
		assertTrue(resulV);
		assertFalse(resulF);
	
	}
	
	@Test
	public void testNumberbOfYs1() {
		SocialNetwork net = initExemple();
		int resul = net.numberOfYs(1);
		assertEquals(resul,3);
	}
	@Test
	public void testaddRelation1() {
		SocialNetwork net = initExemple();
		net.addRelation(2, 1);
		boolean test = net.isDefined(2,1);
		assertTrue(test);
	
	}
	
	@Test
	public void testaddRelation2() {
		SocialNetwork net = initExemple();
		net.addRelation(1, 6);
		boolean test = net.isDefined(1,6);
		assertTrue(test);
	
	}
	
	@Test
	public void testaddRelation3() {
		SocialNetwork net = initExemple();
		net.addRelation(6, 1);
		boolean test = net.isDefined(6,1);
		assertTrue(test);
	
	}
	
	@Test
	public void testRemoveRelation1() {
		SocialNetwork net = initExemple();
		net.removeRelation(2,1);
		boolean test = net.isDefined(2,1);
		assertFalse(test);
	}
	
	
	@Test
	public void testRemoveRelation2() {
		SocialNetwork net = initExemple();
		net.removeRelation(3, 6);
		boolean test =  net.isDefined(3,6);
		assertFalse(test);
	}
	
	
	@Test
	public void testRemoveRelation3() {
		SocialNetwork net = initExemple();
		net.removeRelation(6, 5);
		boolean test = !net.isDefined(6,5);
		assertTrue(test);
	
	}
	
	@Test
	public void testEquals1() {
		SocialNetwork net1 = initExemple();
		SocialNetwork net2 = initExemple();
		boolean resul = net1.equals(net2);
		assertTrue(resul);
	}
	
	
	@Test
	public void testEquals2() {
		SocialNetwork net1 = initExemple();
		SocialNetwork net2 = initExemple();
		Set successeurs2 = new Set<Integer>();
		successeurs2.add(3);
		net2.addValue(2,successeurs2);
		boolean resul = net1.equals(net2);
		assertFalse(resul);
	}
	@Test
	public void testEquals3() {
		SocialNetwork net1 = initExemple();
		SocialNetwork net2 = initExemple();
		Set successeurs2 = new Set<Integer>();
		successeurs2.add(3);
		net2.addValue(2,successeurs2);
		net2.removeValue(7);
		boolean resul = net1.equals(net2);
		assertFalse(resul);
	}
	
	@Test
	public void Join() {
		SocialNetwork net1 = initExemple();
		SocialNetwork net2 = initExemple();
		int test = net1.join(net2);
		boolean resul = test==11;
		assertFalse(resul);
	}
	
	@Test
	public void Symetric() {
		SocialNetwork net1 = initExemple();
		SocialNetwork net2 = net1.symmetricRelation();
		SocialNetwork net3 = net2.symmetricRelation();
		assert(net1.equals(net3));
		
	}
	
	@Test
	public void isReflexive() {
		SocialNetwork net1 = new SocialNetwork();
		net1.addValue(1,new Set<Integer>());
		net1.addValue(2,new Set<Integer>());
		net1.addValue(3,new Set<Integer>());
		net1.addRelation(1, 1);
		net1.addRelation(2, 2);
		net1.addRelation(3, 3);
		assertTrue(net1.isReflexive());
	}
	
	@Test
	public void isIncluedInGen() {
		// Cas général
		SocialNetwork net1 = initExemple();
		SocialNetwork net2 = initExemple();
		assert(net1.isIncludedIn(net2));
	}
	
	@Test
	public void isIncluedInVide() {
		//Ensemble Vide
		SocialNetwork net1 = initExemple();
		SocialNetwork net2 = new SocialNetwork();
		assertFalse(!net1.isIncludedIn(net2));
		assertTrue(net2.isIncludedIn(net1));
	}
	
	@Test
	public void IntersectionGen1() {
		// Cas général
		SocialNetwork net1 = initExemple();
		SocialNetwork net2 = initExemple();
		net1.addRelation(1, 1);
		net1.addRelation(2, 2);
		net1.addRelation(3, 3);
		SocialNetwork net = net1.intersection(net2);
		SocialNetwork net3 = new SocialNetwork();
		assertTrue(net.isIncludedIn(net2));
		assertTrue(net.isIncludedIn(net1));
		assertFalse(net.equals(net3));
	}
	
	@Test
	public void IntersectionGen2() {
		// Cas général
		SocialNetwork net1 = initExemple();
		SocialNetwork net2 = initExemple();
		net1.addRelation(1, 1);
		net1.addRelation(2, 2);
		net1.addRelation(3, 3);
		SocialNetwork net = net1.intersection(net2);
		assertFalse(net.equals(net1));
		assertTrue(net.equals(net2));
	}
	
	
	@Test
	public void IntersectionBisGen1() {
		// Cas général
		SocialNetwork net1 = initExemple();
		SocialNetwork net2 = initExemple();
		net1.addRelation(1, 1);
		net1.addRelation(2, 2);
		net1.addRelation(3, 3);
		net1.intersectionBis(net2);
		SocialNetwork net3 = new SocialNetwork();
		assertTrue(net1.isIncludedIn(net2));
		assertFalse(net1.equals(net3));
	}
	
	@Test
	public void IntersectionBisGen2() {
		// Cas général
		SocialNetwork net1 = initExemple();
		SocialNetwork net2 = initExemple();
		net1.intersectionBis(net2);
		assertTrue(net1.equals(net2));
	}
	
}
