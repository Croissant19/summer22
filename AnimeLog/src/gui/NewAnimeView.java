package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import anime.Anime;
import anime.Anime.Language;
import anime.Anime.Type;
import manager.Manager;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Defines the GUI components for creating a new Anime
 * @author Hunter Pruitt
 */
public class NewAnimeView extends JPanel {
	
	/** Default text at top of view, instructing how to use the page, uses html because newline characters cannot be displayed */
	private static final String DEFAULT_TEXT = "<html>Enter the information below to create a new entry.<br>Bolded fields are required.</html>";
	
	/** Text that appears in the box if an anime has already been added since opening this view */
	private String secondaryText;
	
	private JTextField txtFldTitle;
	private JTextField txtFldYear;
	private JTextField txtFldCount;
	private JTextField txtFldDirector;

	private JRadioButton rdBtnSub;
	private JRadioButton rdBtnDub;
	private JRadioButton rdBtnOther;
	private JRadioButton rdBtnSeries;
	private JRadioButton rdBtnSpecial;

	private JCheckBox chckBxFinished;
	private JCheckBox chckBxDropped;

	private JButton btnSave;
	private JLabel lblTopInfo;

	/**
	 * Create the panel.
	 */
	public NewAnimeView() {
		setLayout(null);
		
		btnSave = new JButton("Add");
		btnSave.setBounds(253, 341, 89, 23);
		add(btnSave);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(82, 341, 89, 23);
		add(btnBack);
		
		JPanel pnlFields = new JPanel();
		pnlFields.setBounds(10, 11, 405, 319);
		add(pnlFields);
		pnlFields.setLayout(null);
		
		lblTopInfo = new JLabel(DEFAULT_TEXT, SwingConstants.CENTER);
		lblTopInfo.setBounds(10, 11, 385, 52);
		pnlFields.add(lblTopInfo);

		
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
		lblLanguage.setFont(new Font("Tahoma", Font.BOLD, 11));
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
		
		txtFldTitle = new JTextField();
		txtFldTitle.setBounds(119, 70, 224, 20);
		pnlFields.add(txtFldTitle);
		txtFldTitle.setColumns(10);
		
		txtFldYear = new JTextField();
		txtFldYear.setColumns(10);
		txtFldYear.setBounds(119, 94, 55, 20);
		pnlFields.add(txtFldYear);
		
		txtFldCount = new JTextField();
		txtFldCount.setColumns(10);
		txtFldCount.setBounds(119, 118, 55, 20);
		pnlFields.add(txtFldCount);
		
		txtFldDirector = new JTextField();
		txtFldDirector.setColumns(10);
		txtFldDirector.setBounds(119, 214, 224, 20);
		pnlFields.add(txtFldDirector);
		
		rdBtnSub = new JRadioButton("Sub");
		//TODO: make other buttons be unselected when one is clicked.
	

		rdBtnSub.setBounds(115, 141, 55, 23);
		pnlFields.add(rdBtnSub);
		
		rdBtnDub = new JRadioButton("Dub");
		rdBtnDub.setBounds(172, 141, 55, 23);
		pnlFields.add(rdBtnDub);
		
		rdBtnOther = new JRadioButton("Other/TBD");
		rdBtnOther.setBounds(229, 141, 82, 23);
		pnlFields.add(rdBtnOther);
		
		chckBxFinished = new JCheckBox("Finished");
		chckBxFinished.setBounds(115, 189, 70, 23);
		pnlFields.add(chckBxFinished);
		
		chckBxDropped = new JCheckBox("Dropped");
		chckBxDropped.setBounds(187, 189, 70, 23);
		pnlFields.add(chckBxDropped);
		
		rdBtnSeries = new JRadioButton("Series");
		rdBtnSeries.setBounds(115, 165, 70, 23);
		pnlFields.add(rdBtnSeries);
		
		rdBtnSpecial = new JRadioButton("Special");
		rdBtnSpecial.setBounds(189, 165, 70, 23);
		pnlFields.add(rdBtnSpecial);
		
		createEvents();
	}
	
	private void createEvents() {

		//Bottom button	
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Try to construct the anime, catching exceptions is something is amiss
				try {
					makeNewAnime();
					clearFields();
				} catch (Exception e1) {
					//Show user a prompt indicating the issue
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});

		
		//Events for ensuring only one radio button of each type can be selected at once
		//Language
		rdBtnSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtnSub.isSelected()) {
					rdBtnDub.setSelected(false);
					rdBtnOther.setSelected(false);
				}
			
			}
		});
		rdBtnDub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtnDub.isSelected()) {
					rdBtnSub.setSelected(false);
					rdBtnOther.setSelected(false);
				}
			
			}
		});
		rdBtnOther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtnOther.isSelected()) {
					rdBtnSub.setSelected(false);
					rdBtnDub.setSelected(false);
				}
			
			}
		});

		//Type
		rdBtnSeries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtnSeries.isSelected()) {
					rdBtnSpecial.setSelected(false);
				}
			
			}
		});
		rdBtnSpecial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtnSpecial.isSelected()) {
					rdBtnSeries.setSelected(false);
				}
			
			}
		});

	
	
	}
	
	/**
	 * Resets the fields to blank after an Anime is successfully added
	 */
	private void clearFields() {
		//TODO: TEST
		txtFldTitle.setText("");;
		txtFldYear.setText("");;
		txtFldCount.setText("");;
		txtFldDirector.setText("");;
		rdBtnSub.setSelected(false);
		rdBtnDub.setSelected(false);
		rdBtnOther.setSelected(false);
		rdBtnSeries.setSelected(false);
		rdBtnSpecial.setSelected(false);
		chckBxFinished.setSelected(false);
		chckBxDropped.setSelected(false);
	}

	/**
	 * Called when the user clicks the add button, checks values on the card panel 
	 * and tries to create an Anime from them.
	 * @throws IllegalArgumentException if bad values, construction fails, or required data not provided by user
	 */
	public void makeNewAnime() {
		//Declare fields for constructor
		String title;
		int year;
		int count = 0;
		Type type;
		Language lan;
		boolean fin;
		boolean drop;
		String director = "";
		
		//Check fields are acceptable
		try {

			//Required fields
			title = txtFldTitle.getText();
			year = Integer.parseInt(txtFldYear.getText());

			if (rdBtnSub.isSelected()) {
				lan = Language.SUB;
			} else if (rdBtnDub.isSelected()) {
				lan = Language.DUB;
			} else if (rdBtnOther.isSelected()) {
				lan = Language.OTHER;
			} else {
				//In case nothing is selected
				throw new IllegalArgumentException("Language not indicated.");
			}
			
			if (rdBtnSeries.isSelected()) {
				type = Type.SERIES;
			} else if (rdBtnSpecial.isSelected()) {
				type = Type.SPECIAL;
			} else {
				//In case neither is selected
				throw new IllegalArgumentException("Type not indicated.");
			}

			
			
			//Test not required fields if filled out		
			if (!txtFldCount.getText().isBlank()) {
				count = Integer.parseInt(txtFldCount.getText());	
			}
			
			fin = chckBxFinished.isSelected();
			drop = chckBxDropped.isSelected();			
			if (fin && drop) {
				throw new IllegalArgumentException("Show cannot be finished and dropped.");
			}
			
			if (!txtFldDirector.getText().isBlank()) {
				director = txtFldDirector.getText();	
			}
			
			
			//Create the anime
			Anime a = new Anime(title, year, count, lan, type, fin, drop, director, "");
			Manager.getInstance().addAnime(a);
		} catch (Exception e) {
			
			//TODO: ensure correct
			if (e instanceof NumberFormatException) {
				throw new IllegalArgumentException("Cannot understant some input.");	
			} else {
				throw new IllegalArgumentException(e.getMessage());
			}
		}

		//TODO: TESTTT
		//By here, was successful so can adjust top text to indicate so.
		//TODO: make toptext reset after leaving 
		secondaryText = title + " was created successfully. Feel free to add another!";
		lblTopInfo.setText(secondaryText);
	}
}
