package cz.uhk.zpravy.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import cz.uhk.zpravy.model.ZasobnikZprav;
import cz.uhk.zpravy.model.Zprava;

public class HlavniOkno extends JFrame {

	private ZasobnikZprav zasobnikZprav;
	private ZpravyTableModel model;
	private JTable tabZpravy;

	public HlavniOkno() {
		super("Animace zprav");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initData();
		
		model = new ZpravyTableModel(zasobnikZprav);
		
		tabZpravy = new JTable(model);
		
		add(new JScrollPane(tabZpravy),BorderLayout.CENTER);
		
		JToolBar tb = new JToolBar();
		add(tb,BorderLayout.NORTH);
		
		JButton btPridat = new JButton("P�idat");
		tb.add(btPridat);
		
		JButton btSmazat = new JButton("Smazat");
		tb.add(btSmazat);

		JButton btAnimace = new JButton("Animovat");
		tb.add(btAnimace);

		btSmazat.addActionListener( (event) -> {
			int indexVyberu = tabZpravy.getSelectedRow();
			if (indexVyberu != -1) {
				zasobnikZprav.smazat(indexVyberu);
				model.fireTableDataChanged();
			}
		});
		
		//setSize(800, 600);
		pack();
	}
	
	private void initData() {
		zasobnikZprav = new ZasobnikZprav();
		
		zasobnikZprav.pridat(new Zprava("Venku hust� sn��", 500));
		zasobnikZprav.pridat(new Zprava("Hasi�i k�c� venku stromy", 100));
	}

	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			
//			@Override
//			public void run() {
//				new HlavniOkno().setVisible(true);
//			}
//		});
		
		SwingUtilities.invokeLater( ()->new HlavniOkno().setVisible(true) );
	}

}
