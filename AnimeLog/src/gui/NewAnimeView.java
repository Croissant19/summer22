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
		btnSave.setBounds(269, 262, 89, 23);
		add(btnSave);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(90, 262, 89, 23);
		add(btnBack);
		
		JPanel pnlFields = new JPanel();
		pnlFields.setBounds(10, 11, 430, 232);
		add(pnlFields);
		pnlFields.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle.setBounds(10, 10, 99, 14);
		pnlFields.add(lblTitle);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYear.setBounds(10, 34, 99, 14);
		pnlFields.add(lblYear);
		
		JLabel lblCount = new JLabel("Number Watched:");
		lblCount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCount.setBounds(10, 58, 99, 14);
		pnlFields.add(lblCount);
		
		JLabel lblLanguage = new JLabel("Language:");
		lblLanguage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLanguage.setBounds(10, 82, 99, 14);
		pnlFields.add(lblLanguage);
		
		JLabel lblType = new JLabel("Content Type:");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblType.setBounds(10, 106, 99, 14);
		pnlFields.add(lblType);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatus.setBounds(10, 130, 99, 14);
		pnlFields.add(lblStatus);
		
		JLabel lblDirector = new JLabel("Director:");
		lblDirector.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDirector.setBounds(10, 154, 99, 14);
		pnlFields.add(lblDirector);
		
		JLabel lblNotes = new JLabel("You can add notes or comments to this entry on the main window.");
		lblNotes.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotes.setBounds(10, 178, 410, 36);
		pnlFields.add(lblNotes);
		
		textField = new JTextField();
		textField.setBounds(119, 7, 224, 20);
		pnlFields.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(119, 31, 55, 20);
		pnlFields.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(119, 55, 55, 20);
		pnlFields.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(119, 151, 224, 20);
		pnlFields.add(textField_3);
		
		JRadioButton rdbtnSub = new JRadioButton("Sub");
		rdbtnSub.setBounds(115, 78, 55, 23);
		pnlFields.add(rdbtnSub);
		
		JRadioButton rdbtnDub = new JRadioButton("Dub");
		rdbtnDub.setBounds(172, 78, 55, 23);
		pnlFields.add(rdbtnDub);
		
		JRadioButton rdbtnOther = new JRadioButton("Other");
		rdbtnOther.setBounds(229, 78, 55, 23);
		pnlFields.add(rdbtnOther);
		
		JCheckBox chckbxFinished = new JCheckBox("Finished");
		chckbxFinished.setBounds(115, 126, 70, 23);
		pnlFields.add(chckbxFinished);
		
		JCheckBox chckbxDropped = new JCheckBox("Dropped");
		chckbxDropped.setBounds(187, 126, 70, 23);
		pnlFields.add(chckbxDropped);
		
		JRadioButton rdbtnSeries = new JRadioButton("Series");
		rdbtnSeries.setBounds(115, 102, 70, 23);
		pnlFields.add(rdbtnSeries);
		
		JRadioButton rdbtnSpecial = new JRadioButton("Special");
		rdbtnSpecial.setBounds(189, 102, 70, 23);
		pnlFields.add(rdbtnSpecial);

	}
}
