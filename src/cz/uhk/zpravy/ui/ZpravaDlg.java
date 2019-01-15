package cz.uhk.zpravy.ui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cz.uhk.zpravy.model.Zprava;

public class ZpravaDlg extends JDialog {
	private JTextField tfText = new JTextField(30);
	private JTextField tfRychlost = new JTextField(4);
	
	public ZpravaDlg(JFrame vlastnik) {
		super(vlastnik);
		setModal(true);
		setTitle("Zadání zprávy");
		
		setLayout(new FlowLayout());
		JLabel lbText = new JLabel("Text zprávy");
		add(lbText);
		add(tfText);
		
		JLabel lbRychlost = new JLabel("Rychlost");
		add(lbRychlost);
		add(tfRychlost);
		
		JButton btOK = new JButton("OK");
		add(btOK);
		btOK.addActionListener( (e)->dispose() );
		
		pack();
	}
	
	public Zprava getZprava() {
		return new Zprava(tfText.getText(), Integer.valueOf(tfRychlost.getText()));
	}
}
