package cz.uhk.zpravy.ui;

import javax.swing.table.AbstractTableModel;

import cz.uhk.zpravy.model.ZasobnikZprav;
import cz.uhk.zpravy.model.Zprava;

public class ZpravyTableModel extends AbstractTableModel {
	private ZasobnikZprav zasobnikZprav;
	
	public ZpravyTableModel() {}

	public ZpravyTableModel(ZasobnikZprav zasobnikZprav) {
		this.zasobnikZprav = zasobnikZprav;
	}

	public void setZasobnikZprav(ZasobnikZprav zasobnikZprav) {
		this.zasobnikZprav = zasobnikZprav;
	}
	
	public ZasobnikZprav getZasobnikZprav() {
		return zasobnikZprav;
	}
	
	@Override
	public int getRowCount() {
		return zasobnikZprav.pocet();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Zprava z = zasobnikZprav.ziskat(rowIndex);
		
		switch (columnIndex) {
			case 0: return z.getText();
			case 1: return z.getRychlost();
		}
		
		return null;
	}


}
