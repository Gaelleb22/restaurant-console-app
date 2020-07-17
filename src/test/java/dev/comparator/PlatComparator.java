package dev.comparator;

import java.util.Comparator;

import dev.entite.Plat;

public class PlatComparator implements Comparator<Plat>{

	@Override
	public int compare(Plat p1, Plat p2) {
		int result = p1.getNom().compareTo(p2.getNom());
		return result;
	}
	
}