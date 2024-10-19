package fr.istic.prav.tables;

import java.util.Iterator;

import istic.prg.table_ens.Set;
import istic.prg.table_ens.Table;

public class SocialNetwork extends Table<Integer, Set<Integer>> {
	// constructeur
	public SocialNetwork() {
		super();
	}

	/**
	* @return true si la relation (x,y) appartient à this, false sinon
	*/
	public boolean isDefined(Integer x, Integer y) {
		// TODO: écrire isDefined(x, y)
		if(this.getValue(x) == null) {
			return false;
		}
		return (this.getValue(x).contains(y));
	}

	/**
	* @return nombre d’entiers y tels que (x, y) appartient à this.
	*/
	public int numberOfYs(Integer x) {
		// TODO: numberOfYs(x)
		if (this.getValue(x) == null) {
			return 0;
		}
			return this.getValue(x).size();
	}

	/**
	* @return nombre total de doublets (x,y) dans this
	*/
	public int numberOfPairs() {
		// TODO: écrire numberOfPairs()
		int res = 0;
		Iterator<Integer> it = this.iterator();
		while(it.hasNext()) {
			Integer eltCourant = it.next();
			res += this.getValue(eltCourant).size();
			
		}
		return res;
	}
	
	/**
	* @return true si this est identique à net, false sinon
	*/
	public boolean equals(SocialNetwork net) {
		// TODO: écrire equals(net)
		if(this.size() != net.size()) {
			return false;
		}
		Iterator<Integer> i1 = this.iterator();
		Iterator<Integer> i2 = net.iterator();
		while(i1.hasNext() && i2.hasNext()) {
			Integer elthis = i1.next();
			Integer eltnet = i2.next();
			if(!this.getValue(elthis).equals(net.getValue(eltnet))) {
				return false;
			}
		}
		return true;
	}

	/**
	* Ajouter la relation (x,y) à this (sans effet si (x, y) est
	* déjà présent)
	*/
	public void addRelation(Integer x, Integer y) {
		// TODO: écrire addRelation(x, y)
		if(this.getValue(x) == null) {
			this.addValue(x, new Set<Integer>());
			this.getValue(x).add(y);
		}
		else if(!this.getValue(x).contains(y)) {
			this.getValue(x).add(y);
		}
		else {
			return;
		}
	}

	/**
	* Supprimer la relation (x,y) de this (sans effet si (x, y) n’est
	* pas présent).
	*/
	public void removeRelation(Integer x, Integer y) {
		// TODO: écrire removeRelation(x, y)
		if (!this.isDefined(x, y)) {
			return;
		}
		else if(this.getValue(x).contains(y)) {
			if(this.getValue(x).size() == 1) {
				this.getValue(x).remove(y);
				this.removeValue(x);
			}
			else {
				this.getValue(x).remove(y);
			}
		}
	}

	/**
	* @return nombre de triplets (x, y, z) tels que (x,y) appartient
	* à this et (y,z) appartient à net
	*/
	public int join(SocialNetwork net) {
		// TODO: écrire join(net)
		int res = 0;
		Iterator<Integer> ithis = this.iterator();
		while(ithis.hasNext()) {
			Integer elthis = ithis.next();
			Iterator<Integer> test = this.getValue(elthis).iterator();
			while(test.hasNext()) {
				Integer eltest = test.next();
				res += net.numberOfYs(eltest);
			}
			
		}
		return res;

	}

	/**
	* @return relation symétrique de this, la relation constituée des
	* (y, x) tels que (x, y) appartient à this.
	*/
	public SocialNetwork symmetricRelation() {
		// TODO: écrire symetricRelation()
		SocialNetwork res = new SocialNetwork();
		
		Iterator<Integer> ithis = this.iterator();
		while(ithis.hasNext()) {
			Integer elthis = ithis.next();
			Iterator<Integer> itSet = this.getValue(elthis).iterator();
			while(itSet.hasNext()) {
				Integer eltSet = itSet.next();
				res.addRelation(eltSet, elthis);
			}
		}
		return res;
	}

	/**
	* @return true si this est une relation réflexive (i.e. si pour
	* toute entrée x de this, (x, x) appartient à this), false sinon
	*/
	public boolean isReflexive() {
		// TODO: écrire isReflexive()
		Iterator<Integer> ithis = this.iterator();
		while(ithis.hasNext()) {
			Integer elthis = ithis.next();
				if(!this.getValue(elthis).contains(elthis)) {
					return false;
				}
		}
		return true;
	}

	/**
	* @return true si this est inclus dans net, false sinon
	*/
	public boolean isIncludedIn(SocialNetwork net) {
		// TODO: écrire isIncludedIn(net)
		Iterator<Integer> ithis = this.iterator();
		while(ithis.hasNext()) {
			Integer elthis = ithis.next();
				if(this.getValue(elthis)==null) {
					return false;
				}
		}
		return true;
	}

	/**
	* @return intersection de this et net
	*/
	public SocialNetwork intersection(SocialNetwork net) {
		// TODO: écrire intersection(net)
		SocialNetwork res = new SocialNetwork();
		Iterator<Integer> i1 = this.iterator();
		while(i1.hasNext()) {
			Integer elthis = i1.next();
			Iterator<Integer> itSet = this.getValue(elthis).iterator();
			while(itSet.hasNext()) {
				Integer eltSet = itSet.next();
				if(net.isDefined(elthis, eltSet)) {
					res.addRelation(elthis, eltSet);
				}
			}
		}
		return res;
	}

	/**
	* this devient l’intersection de this et net.
	*/
	public void intersectionBis(SocialNetwork net) {
		// TODO: écrire intersectionBis(net)
		Iterator<Integer> it = this.iterator();
		while(it.hasNext()) {
			Integer elthis = it.next();
			Iterator<Integer> itSet = this.getValue(elthis).iterator();
			while(itSet.hasNext()) {
				Integer eltSet = itSet.next();
				if(!net.isDefined(elthis, eltSet)) {
					System.out.println(this.toString() +" "+ eltSet.toString()+" "+ elthis.toString());
					//Integer tmp = eltSet;
					it.remove();
					
				}
			}
		}
		
		return ;
	}
	/*
	 * 
	if (!this.isDefined(x, y)) {
			return;
		}
		else if(this.getValue(x).contains(y)) {
			if(this.getValue(x).size() == 1) {
				this.getValue(x).remove(y);
				this.removeValue(x);
			}
			else {
				this.getValue(x).remove(y);
			}
		}
	}
	 * */
	
	

	/**
	* this devient l’union de this et net.
	*/
	public void union(SocialNetwork net) {
		// TODO: écrire union(net)
		Iterator<Integer> it = net.iterator();
		while(it.hasNext()) {
			Integer elthis = it.next();
			Iterator<Integer> itSet = this.getValue(elthis).iterator();
			while(itSet.hasNext()) {
				Integer eltSet = itSet.next();
				System.out.println(this.toString() + eltSet.toString());
				if(!this.isDefined(elthis, eltSet)) {
					this.addRelation(elthis, eltSet);
				}
			}
		}
		return ;
	}
}

