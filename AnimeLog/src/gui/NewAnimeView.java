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
public class NewAnimeView extends JPanel {
	
	/** Default text at top of view, instructing how to use the page, uses html because newline characters cannot be displayed */
	private static final String DEFAULT_TEXT = "<html>Enter the information below to create a new entry.<br>Bolded fields are required.</html>";
	
	/** Text that appears in the box if an anime has already been added since opening this view */
	private String secondaryText;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public NewAnimeView() {
		setLayout(null);
		
		JButton btnSave = new JButton("Add");
		btnSave.setBounds(253, 341, 89, 23);
		add(btnSave);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(82, 341, 89, 23);
		add(btnBack);
		
		JPanel pnlFields = new JPanel();
		pnlFields.setBounds(10, 11, 405, 319);
		add(pnlFields);
		pnlFields.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(DEFAULT_TEXT, SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 385, 52);
		pnlFields.add(lblNewLabel);

		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle.setBounds(10, 73, 99, 14);
		pnlFields.add(lblTitle);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYear.setBounds(10, 97, 99, 14);
		pnlFields.add(lblYear);
		
		JLabel lblCount = new JLabel("Number Watched:");
		lblCount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCount.setBounds(10, 121, 99, 14);
		pnlFields.add(lblCount);
		
		JLabel lblLanguage = new JLabel("Language:");
		lblLanguage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLanguage.setBounds(10, 145, 99, 14);
		pnlFields.add(lblLanguage);
		
		JLabel lblType = new JLabel("Content Type:");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblType.setBounds(10, 169, 99, 14);
		pnlFields.add(lblType);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatus.setBounds(10, 193, 99, 14);
		pnlFields.add(lblStatus);
		
		JLabel lblDirector = new JLabel("Director:");
		lblDirector.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDirector.setBounds(10, 217, 99, 14);
		pnlFields.add(lblDirector);
		
		JLabel lblNotes = new JLabel("You can add notes/comments or an image to this entry on the main viewer.");
		lblNotes.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotes.setBounds(10, 258, 385, 36);
		pnlFields.add(lblNotes);
		
		textField = new JTextField();
		textField.setBounds(119, 70, 224, 20);
		pnlFields.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(119, 94, 55, 20);
		pnlFields.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(119, 118, 55, 20);
		pnlFields.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(119, 214, 224, 20);
		pnlFields.add(textField_3);
		
		JRadioButton rdbtnSub = new JRadioButton("Sub");
		rdbtnSub.setBounds(115, 141, 55, 23);
		pnlFields.add(rdbtnSub);
		
		JRadioButton rdbtnDub = new JRadioButton("Dub");
		rdbtnDub.setBounds(172, 141, 55, 23);
		pnlFields.add(rdbtnDub);
		
		JRadioButton rdbtnOther = new JRadioButton("Other");
		rdbtnOther.setBounds(229, 141, 55, 23);
		pnlFields.add(rdbtnOther);
		
		JCheckBox chckbxFinished = new JCheckBox("Finished");
		chckbxFinished.setBounds(115, 189, 70, 23);
		pnlFields.add(chckbxFinished);
		
		JCheckBox chckbxDropped = new JCheckBox("Dropped");
		chckbxDropped.setBounds(187, 189, 70, 23);
		pnlFields.add(chckbxDropped);
		
		JRadioButton rdbtnSeries = new JRadioButton("Series");
		rdbtnSeries.setBounds(115, 165, 70, 23);
		pnlFields.add(rdbtnSeries);
		
		JRadioButton rdbtnSpecial = new JRadioButton("Special");
		rdbtnSpecial.setBounds(189, 165, 70, 23);
		pnlFields.add(rdbtnSpecial);
		
	}
}
