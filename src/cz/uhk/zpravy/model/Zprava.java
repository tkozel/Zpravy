package cz.uhk.zpravy.model;

/**
 * Reprezentace textové zprávy pro animaèní systém 
 *
 */
public class Zprava {
	/**
	 * text zprávy
	 */
	private String text;
	/**
	 * rychlost animace = prodleva mezi kroky animace
	 */
	private int rychlost;

	public Zprava(String text, int rychlost) {
		super();
		this.text = text;
		this.rychlost = rychlost;
	}

	public Zprava() {
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getRychlost() {
		return rychlost;
	}

	public void setRychlost(int rychlost) {
		this.rychlost = rychlost;
	}

}
