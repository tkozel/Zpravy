package cz.uhk.zpravy.ui;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	protected JLabel labText;
	private MojeVlakno vlakno;

	public HlavniOkno() {
		super("Animace zprav");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initData();
		
		model = new ZpravyTableModel(zasobnikZprav);
		
		tabZpravy = new JTable(model);
		
		add(new JScrollPane(tabZpravy),BorderLayout.CENTER);
		
		JToolBar tb = new JToolBar();
		add(tb,BorderLayout.NORTH);
		
		JButton btPridat = new JButton("Pøidat");
		tb.add(btPridat);
		btPridat.addActionListener( (event)->{
			ZpravaDlg dlg = new ZpravaDlg(HlavniOkno.this);
			dlg.setVisible(true); //cekame na zavreni dialogu
			Zprava z = dlg.getZprava();  //TODO muze vzniknout vyjimka kvuli chybejici rychlosti
			zasobnikZprav.pridat(z);
			model.fireTableDataChanged();
		});
		
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
		
		btAnimace.addActionListener( (event) -> spustitAnimaci() );
		
		labText = new JLabel();
		add(labText,BorderLayout.SOUTH);
		
		tb.addSeparator();
		
		JButton btNacist = new JButton("Naèíst");
		tb.add(btNacist);
		btNacist.addActionListener((e)->nacist());
		
		JButton btUlozit = new JButton("Uložit");
		tb.add(btUlozit);
		btUlozit.addActionListener((e)->ulozit());
		
		
		pack();
	}
	
	private void ulozit() {
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream("zpravy.csv"));
			for(Zprava z : zasobnikZprav.getZpravy()) {
				out.printf("%s;%d\n", z.getText(), z.getRychlost());
			}
			out.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "Chyba:"+e.getLocalizedMessage());
		}
	}

	private void nacist() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("zpravy.csv"));
			zasobnikZprav.getZpravy().clear();
			String radek;
			while ((radek = in.readLine()) != null) {
				//rozdelit radek podle ;
				String[] polozky = radek.split(";");
				Zprava z = new Zprava(polozky[0], Integer.valueOf(polozky[1]));
				zasobnikZprav.pridat(z);
			}
			model.fireTableDataChanged();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Chyba:"+e.getLocalizedMessage());
		}
	}

	private void spustitAnimaci() {
		int index = tabZpravy.getSelectedRow();
		
		if (index != -1) {
			if (vlakno != null) vlakno.konec();
			vlakno = new MojeVlakno(zasobnikZprav.ziskat(index));
			vlakno.start();
		}
		
	}

	private void initData() {
		zasobnikZprav = new ZasobnikZprav();
		
		zasobnikZprav.pridat(new Zprava("Venku hustì snìží", 500));
		zasobnikZprav.pridat(new Zprava("Hasièi kácí venku stromy", 100));
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

	class MojeVlakno extends Thread {
		boolean stop = false;
		Zprava zprava;
		
		public MojeVlakno(Zprava z) {
			zprava = z;
		}
		
		void konec() {
			stop =true;
		}
		
		@Override
		public void run() {
			
			String animText = zprava.getText() + "                         ";
			while (!stop) {
				labText.setText(animText);
				animText = animText.substring(1)+animText.charAt(0);
				try {
					Thread.sleep(zprava.getRychlost());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
