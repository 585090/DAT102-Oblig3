package no.hvl.dat102;

import java.util.Iterator;

import no.hvl.dat102.adt.BSTreADT;

//********************************************************************
// KjedetBinærSøkeTre.java        
//
//********************************************************************

public class KjedetBSTre<T extends Comparable<T>> implements BSTreADT<T>,Iterable<T> {

	private int antall;
	/**
	 * @return the rot
	 */
	public BinaerTreNode<T> getRot() {
		return rot;
	}

	/**
	 * @param rot the rot to set
	 */
	public void setRot(BinaerTreNode<T> rot) {
		this.rot = rot;
	}

	private BinaerTreNode<T> rot;

	/******************************************************************
	 * Oppretter et tomt binært søketre.
	 ******************************************************************/
	public KjedetBSTre() {
		antall = 0;
		rot = null;
	}

	/******************************************************************
	 * Oppretter et binært søketre med en node..
	 ******************************************************************/
	public KjedetBSTre(T element) {
		rot = new BinaerTreNode<T>(element);
		antall = 1;
	}

	/*****************************************************************
	 * Returnerer sann hvis dette binære trett er tomt og usann ellers.
	 *****************************************************************/
	@Override
	public int antall() {
		return antall;
	}

	/*****************************************************************
	 * Returnerer sann hvis dette binære treet er tom og usann ellers.
	 *****************************************************************/
	@Override
	public boolean erTom() {
		return (antall == 0);
	}
	
	
	/**********************************************************************
	 * Legger det spesifiserte elementet på passende plass i BS-treet. Like
	 * elementer blir lagt til høyre. Bruk av rekursiv hjelpemetode.
	 ********************************************************************/
	@Override
	public void leggTil(T element) {
		rot = leggTilRek(rot, element);
		antall++;
	}

	private BinaerTreNode<T> leggTilRek(BinaerTreNode<T> p, T element) {
		
		if (p == null) {
			 p = new BinaerTreNode<T> (element);
		}
		
		else if (element.compareTo(p.getElement()) < 0) {
			p.setVenstre(leggTilRek(p.getVenstre(), element));
		}
		
		else {
			p.setHoyre(leggTilRek(p.getHoyre(), element));
		}
		
		return p;
		
	}

	/******************************************************************
	 * Fjerner noden med minste verdi fra dette binære søketreet.
	 *********************************************************************/
	@Override
	public T fjernMin() {
		return fjernMinRek(rot);
	}//
	
	private T fjernMinRek(BinaerTreNode<T> p) {
		
		T element = null;
		
		if (p == null) {
			return null;
		}
		
		if (p.getVenstre() == null) {
			element = p.getElement();
			rot = rot.getHoyre();
		}
		
		else if (p.getVenstre().getVenstre() != null) {
			return fjernMinRek(p.getVenstre());
		}
		
		if (p.getVenstre().getHoyre() != null) {
			element = p.getVenstre().getElement();
			p.setVenstre(p.getVenstre().getHoyre());
		}
		
		else {
			element = p.getVenstre().getElement();
			p.setVenstre(null);
		}
		
		return element;
		
	}
	

	/******************************************************************
	 * Fjerner noden med største verdi fra dette binære søketreet.
	 ******************************************************************/
	@Override
	public T fjernMaks() {
		return fjernMaksRek(rot);
	}//

	private T fjernMaksRek(BinaerTreNode<T> p) {
		
		T element = null;
		
		if (p == null) {
			return null;
		}
		
		if (p.getHoyre() == null) {
			element = p.getElement();
			rot = rot.getVenstre();
		}
		
		else if (p.getHoyre().getHoyre() != null) {
			return fjernMaksRek(p.getVenstre());
		}
		
		if (p.getHoyre().getVenstre() != null) {
			element = p.getHoyre().getElement();
			p.setHoyre(p.getHoyre().getVenstre());
		}
		
		else {
			element = p.getHoyre().getElement();
			p.setVenstre(null);
		}
		
		return element;
		
	}
	
	/******************************************************************
	 * Returnerer det minste elementet i dette binære søketreet.
	 ******************************************************************/
	@Override
	public T finnMin() {
		
		if (rot != null) {
		return finn(rot.getVenstre().getElement());
		}
		else {
			return null;
		}
	}//

	/******************************************************************
	 * Returnerer det største elementet i dette binære søketreet.
	 ******************************************************************/
	@Override
	public T finnMaks() {
		
		if (rot != null) {
		return finn(rot.getHoyre().getElement());
		}
		else {
			return null;
		}
	}//

	/*******************************************************************************
	 * Returnerer en referanse til det spesifiserte elementet hvis det finst i dette
	 * BS-treet, null ellers. Bruk av rekursjon /
	 ******************************************************************************/
	@Override
	public T finn(T element) {
		return finnRek(element, rot);
	}

	// Den rekursive hjelpemetoden for søking 
	/************************************************************************
	 * Returnerer en referanse til det spesifiserte elementet hvis det fins i dette
	 * BS-treet, null ellers. Uten bruk av rekursjon. /
	 ************************************************************************/
	public T finnRek(T element, BinaerTreNode<T> p) {
		
		T svar = null;
		
		if (p != null) {
			int find = element.compareTo(p.getElement());
		
		if (find == 0) {
			svar = p.getElement();
			}
		else if (find < 0) {
			svar = finnRek(element, p.getVenstre());
			}
		else {
			svar = finnRek(element, p.getHoyre());
			}
		}
		return svar;
	}
	
	public void visInorden() {
		visInorden(rot);
		System.out.println();
	}

	private void visInorden(BinaerTreNode<T> p) {
		
		if (p != null) {
			visInorden(p.getVenstre());
			System.out.print(" " + p.getElement());
			visInorden(p.getHoyre());
		}  
	}

	@Override
	public Iterator<T> iterator() {
		return new InordenIterator<T>(rot);
		
	}
	
	public int hoyde() {
		return hoydeRek(rot);
	}
	
	
	private int hoydeRek(BinaerTreNode<T> p) {
		if(p == null) {
			return 0;
		}
		
		else {
		int venstreHoyde = hoydeRek(p.getVenstre());
		int hoyreHoyde = hoydeRek(p.getHoyre());
		
		return Math.max(venstreHoyde, hoyreHoyde) + 1;
		}
	}
	
}// class
