package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import manager.Manager;

import javax.swing.JTextField;
import java.awt.Font;

/**
 * Defines the GUI components for creating a new Anime
 * @author Hunter Pruitt
 */
public class HomeView extends JPanel {
	
	/** Default text at top of view, instructing how to use the page, uses html because newline characters cannot be displayed */
	private static final String LOAD_TEXT = "<html>To load a pre-existing file, click the <em>File</em> dropdown and select <em>Load</em>. "
			+ "Otherwise, click add to begin creating your own anime watch log!</html>";
	
	private static final String REMINDER = "<html>Don't forget to save before you leave in the <em>File</em> dropdown!</html>";
	
	//Stats to answer the lblQX series of JLabels
	private JTextField txtA1;
	private JTextField txtA2;
	private JTextField txtA3;
	private JTextField txtA4;
	private JTextField txtA5;
	private JTextField txtA6;
	private JTextField txtA7;

	/**
	 * Create the panel.
	 */
	public HomeView() {
		setLayout(null);
		
		JLabel lblInstructions = new JLabel(LOAD_TEXT);
		lblInstructions.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions.setBounds(43, 11, 364, 56);
		add(lblInstructions);
		
		JPanel statsPanel = new JPanel();
		statsPanel.setBounds(10, 78, 405, 198);
		add(statsPanel);
		statsPanel.setLayout(null);
		
		JLabel lblQ1 = new JLabel("Entry Count:");
		lblQ1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ1.setBounds(10, 23, 117, 20);
		statsPanel.add(lblQ1);
		
		txtA1 = new JTextField();
		txtA1.setEditable(false);
		txtA1.setBounds(121, 23, 61, 20);
		statsPanel.add(txtA1);
		txtA1.setColumns(10);
		
		JLabel lblQ2 = new JLabel("Number Series:");
		lblQ2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ2.setBounds(10, 66, 117, 20);
		statsPanel.add(lblQ2);
		
		txtA2 = new JTextField();
		txtA2.setEditable(false);
		txtA2.setColumns(10);
		txtA2.setBounds(121, 66, 61, 20);
		statsPanel.add(txtA2);
		
		JLabel lblQ3 = new JLabel("Number Specials:");
		lblQ3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ3.setBounds(10, 109, 117, 20);
		statsPanel.add(lblQ3);
		
		txtA3 = new JTextField();
		txtA3.setEditable(false);
		txtA3.setColumns(10);
		txtA3.setBounds(121, 109, 61, 20);
		statsPanel.add(txtA3);
		
		JLabel lblQ4 = new JLabel("Episode Count:");
		lblQ4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ4.setBounds(10, 152, 117, 20);
		statsPanel.add(lblQ4);
		
		txtA4 = new JTextField();
		txtA4.setEditable(false);
		txtA4.setColumns(10);
		txtA4.setBounds(121, 152, 61, 20);
		statsPanel.add(txtA4);
		
		JLabel lblQ5 = new JLabel("Favored Language:");
		lblQ5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ5.setBounds(207, 23, 117, 20);
		statsPanel.add(lblQ5);
		
		txtA5 = new JTextField();
		txtA5.setEditable(false);
		txtA5.setColumns(10);
		txtA5.setBounds(323, 23, 61, 20);
		statsPanel.add(txtA5);

		JLabel lblQ6 = new JLabel("Percent Finished:");
		lblQ6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ6.setBounds(207, 66, 117, 20);
		statsPanel.add(lblQ6);
		
		txtA6 = new JTextField();
		txtA6.setEditable(false);
		txtA6.setColumns(10);
		txtA6.setBounds(323, 66, 61, 20);
		statsPanel.add(txtA6);
		
		txtA7 = new JTextField();
		txtA7.setEditable(false);
		txtA7.setColumns(10);
		txtA7.setBounds(323, 109, 61, 20);
		statsPanel.add(txtA7);
		
		JLabel lblQ7 = new JLabel("Percent Dropped:");
		lblQ7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ7.setBounds(207, 109, 117, 20);
		statsPanel.add(lblQ7);
		
		JLabel lblSaveReminder = new JLabel(REMINDER);
		lblSaveReminder.setBounds(61, 287, 308, 14);
		add(lblSaveReminder);
		
		
		updateStats();
	}
	
	/**
	 * Updates the statistics on the HomeView after loading a file or editing the anime list stored in Manager
	 */
	public void updateStats() {
		//Otherwise calculate the stats as one typically would
		txtA1.setText(Manager.getInstance().getEntryCount());
		txtA2.setText(Manager.getInstance().getNumSeries());
		txtA3.setText(Manager.getInstance().getNumSpecial());
		txtA4.setText(Manager.getInstance().getCountSum());
		txtA5.setText(Manager.getInstance().getFavoredLanguageAndPercent());
		txtA6.setText(Manager.getInstance().getPercentFinished());
		txtA7.setText(Manager.getInstance().getPercentDropped());
	}
}
