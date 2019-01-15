package cz.uhk.zpravy.model;

import java.util.ArrayList;
import java.util.List;

public class ZasobnikZprav {
	private List<Zprava> zpravy = new ArrayList<>();

	public int pocet() {
		return zpravy.size();
	}

	public boolean pridat(Zprava z) {
		return zpravy.add(z);
	}

	public Zprava ziskat(int index) {
		return zpravy.get(index);
	}

	public Zprava smazat(int index) {
		return zpravy.remove(index);
	}

	public List<Zprava> getZpravy() {
		return zpravy;
	}
	
}
