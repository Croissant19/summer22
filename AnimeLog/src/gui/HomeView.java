package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Font;

/**
 * Defines the GUI components for creating a new Anime
 * @author Hunter Pruitt
 */
public class HomeView extends JPanel {
	
	/** Default text at top of view, instructing how to use the page, uses html because newline characters cannot be displayed */
	private static final String DEFAULT_TEXT = "<html>Enter the information below to create a new entry.<br>Bolded fields are required.</html>";
	
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txtA1;
	private JTextField txtA2;
	private JTextField txtA3;
	private JTextField txtA4;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Create the panel.
	 */
	public HomeView() {
		setLayout(null);
		
		JLabel lblInstructions = new JLabel("Welcome blahblahblah");
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
		
		txtA2 = new JTextField();
		txtA2.setText("0");
		txtA2.setEditable(false);
		txtA2.setColumns(10);
		txtA2.setBounds(137, 152, 61, 20);
		statsPanel.add(txtA2);
		
		JLabel lblQ6 = new JLabel("Percent Finsihed:");
		lblQ6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ6.setBounds(211, 66, 117, 20);
		statsPanel.add(lblQ6);
		
		txtA3 = new JTextField();
		txtA3.setText("0%");
		txtA3.setEditable(false);
		txtA3.setColumns(10);
		txtA3.setBounds(338, 66, 61, 20);
		statsPanel.add(txtA3);
		
		txtA4 = new JTextField();
		txtA4.setText("0%");
		txtA4.setEditable(false);
		txtA4.setColumns(10);
		txtA4.setBounds(338, 109, 61, 20);
		statsPanel.add(txtA4);
		
		JLabel lblQ7 = new JLabel("Percent Dropped:");
		lblQ7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ7.setBounds(211, 109, 117, 20);
		statsPanel.add(lblQ7);
		
		JLabel lblQ2 = new JLabel("Number Series:");
		lblQ2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ2.setBounds(10, 66, 117, 20);
		statsPanel.add(lblQ2);
		
		textField_4 = new JTextField();
		textField_4.setText("0");
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(137, 66, 61, 20);
		statsPanel.add(textField_4);
		
		JLabel lblQ3 = new JLabel("Number Specials:");
		lblQ3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ3.setBounds(10, 109, 117, 20);
		statsPanel.add(lblQ3);
		
		textField_5 = new JTextField();
		textField_5.setText("0");
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(137, 109, 61, 20);
		statsPanel.add(textField_5);
		
		JLabel lblQ5 = new JLabel("Favored Language:");
		lblQ5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ5.setBounds(211, 23, 117, 20);
		statsPanel.add(lblQ5);
		
		textField_6 = new JTextField();
		textField_6.setText("? (%)");
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(338, 23, 61, 20);
		statsPanel.add(textField_6);
		
	}
}
