package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;

/**
 * Defines the GUI components for creating a new Anime
 * @author Hunter Pruitt
 */
public class HomeView extends JPanel {
	
	/** Default text at top of view, instructing how to use the page, uses html because newline characters cannot be displayed */
	private static final String DEFAULT_TEXT = "<html>Welcome blahblahblah</html>";
	
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
		
		JLabel lblInstructions = new JLabel(DEFAULT_TEXT);
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions.setBounds(10, 11, 430, 56);
		add(lblInstructions);
		
		JPanel statsPanel = new JPanel();
		statsPanel.setBounds(20, 78, 409, 198);
		add(statsPanel);
		statsPanel.setLayout(null);
		
		JLabel lblQ1 = new JLabel("Entry Count:");
		lblQ1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ1.setBounds(10, 23, 117, 20);
		statsPanel.add(lblQ1);
		
		txtA1 = new JTextField();
		txtA1.setText("0");
		txtA1.setEditable(false);
		txtA1.setBounds(137, 23, 61, 20);
		statsPanel.add(txtA1);
		txtA1.setColumns(10);
		
		JLabel lblQ4 = new JLabel("Episode Count:");
		lblQ4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ4.setBounds(10, 152, 117, 20);
		statsPanel.add(lblQ4);
		
		txtA4 = new JTextField();
		txtA4.setText("0");
		txtA4.setEditable(false);
		txtA4.setColumns(10);
		txtA4.setBounds(137, 152, 61, 20);
		statsPanel.add(txtA4);
		
		JLabel lblQ6 = new JLabel("Percent Finished:");
		lblQ6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ6.setBounds(211, 66, 117, 20);
		statsPanel.add(lblQ6);
		
		txtA6 = new JTextField();
		txtA6.setText("0%");
		txtA6.setEditable(false);
		txtA6.setColumns(10);
		txtA6.setBounds(338, 66, 61, 20);
		statsPanel.add(txtA6);
		
		txtA7 = new JTextField();
		txtA7.setText("0%");
		txtA7.setEditable(false);
		txtA7.setColumns(10);
		txtA7.setBounds(338, 109, 61, 20);
		statsPanel.add(txtA7);
		
		JLabel lblQ7 = new JLabel("Percent Dropped:");
		lblQ7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ7.setBounds(211, 109, 117, 20);
		statsPanel.add(lblQ7);
		
		JLabel lblQ2 = new JLabel("Number Series:");
		lblQ2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ2.setBounds(10, 66, 117, 20);
		statsPanel.add(lblQ2);
		
		txtA2 = new JTextField();
		txtA2.setText("0");
		txtA2.setEditable(false);
		txtA2.setColumns(10);
		txtA2.setBounds(137, 66, 61, 20);
		statsPanel.add(txtA2);
		
		JLabel lblQ3 = new JLabel("Number Specials:");
		lblQ3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ3.setBounds(10, 109, 117, 20);
		statsPanel.add(lblQ3);
		
		txtA3 = new JTextField();
		txtA3.setText("0");
		txtA3.setEditable(false);
		txtA3.setColumns(10);
		txtA3.setBounds(137, 109, 61, 20);
		statsPanel.add(txtA3);
		
		JLabel lblQ5 = new JLabel("Favored Language:");
		lblQ5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ5.setBounds(211, 23, 117, 20);
		statsPanel.add(lblQ5);
		
		txtA5 = new JTextField();
		txtA5.setText("? (%)");
		txtA5.setEditable(false);
		txtA5.setColumns(10);
		txtA5.setBounds(338, 23, 61, 20);
		statsPanel.add(txtA5);
		
	}
	
	/**
	 * Updates the statistics on the HomeView after loading a file or editing the anime list stored in Manager
	 */
	public void updateStats() {
		//TODO:
	}
}
