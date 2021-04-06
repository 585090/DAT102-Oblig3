package no.hvl.dat102.klient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import no.hvl.dat102.KjedetBSTre;

public class HoydeKlient {

	public static void main(String[] args) {
	
		//Kjøring av programmet
		randomTre(1024, 100);
		
	}
	
	private static void randomTre(int antallNoder, int antallTre) {
	
		int minhoyde = antallNoder-1;
		int makshoyde = 0;
		int avgHoyde = 0;
		for (int i = 0; i < antallTre; i++) {
			KjedetBSTre bstre = new KjedetBSTre();
			
			for (int j = 0; j < antallNoder; j++) {	
				bstre.leggTil(terning.nextInt());
			}
			
			int hoyde = bstre.hoyde();
			avgHoyde = avgHoyde + hoyde;
			
			if(minhoyde > hoyde) {
				minhoyde = hoyde;
			}
			
			if(makshoyde < hoyde) {
				makshoyde = hoyde;
			}
		}
		double doubleAvgHoyde = avgHoyde/(double)antallTre;
		
		System.out.println("Antall noder: " + antallNoder);
		System.out.println("\nTeoretisk minimal høyde: " + MinHoyde(antallNoder));
		System.out.println("Teoretisk maksimal høyde: " + (antallNoder -1));
		System.out.println("\nMinimal høyde etter kjøring: " + minhoyde);
		System.out.println("Maksimal høyde etter kjøring: " + makshoyde);
		System.out.println("\nGjennomsnittlig høyde etter kjøring: " + doubleAvgHoyde);
		
	}

	static Random terning = new Random();
	
	private static int MinHoyde(int antall) {
		
		if (antall == 0) {
			return -1;
		}
		int hoyde = -1;
		int noder = antall;
		while (noder != 0) {
			hoyde++;
			noder = noder / 2;
		}
		return hoyde;
	}
}