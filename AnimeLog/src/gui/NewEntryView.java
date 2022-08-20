package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import data.Anime;
import data.Anime.Language;
import data.Manga;
import data.Media.Type;
import data.Media.MediaType;
import manager.Manager;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

/**
 * Defines the GUI components for creating a new Anime
 * @author Hunter Pruitt
 */
public class NewEntryView extends JPanel {
	
	/** Default text at top of view, instructing how to use the page when making an Anime, uses html because newline characters cannot be displayed */
	private static final String DEFAULT_TEXT_ANIME = "<html>Enter the information below to create a new anime.<br>Bolded fields are required.</html>";
	
	/** Default text at top of view, instructing how to use the page when making a Manga, uses html because newline characters cannot be displayed */
	private static final String DEFAULT_TEXT_MANGA = "<html>Enter the information below to create a new manga.<br>Bolded fields are required.</html>";
	
	/** Text that appears in the box if an anime has already been added since opening this view */
	private String secondaryText;
	
	/** Pointer to the main GUI component, used for accessing mediaMode and other functions */
	private GUI mainGUI;
	

	private JTextField txtFldTitleAnime;
	private JTextField txtFldYearAnime;
	private JTextField txtFldCountAnime;
	private JTextField txtFldDirectorAnime;
	private JTextField txtFldStudioAnime;

	private JRadioButton rdBtnSubAnime;
	private JRadioButton rdBtnDubAnime;
	private JRadioButton rdBtnOtherAnime;
	private JRadioButton rdBtnSeriesAnime;
	private JRadioButton rdBtnSpecialAnime;
	private JCheckBox chckBxFinishedAnime;
	private JCheckBox chckBxDroppedAnime;
	
	private JTextField txtFldTitleManga;
	private JTextField txtFldYearManga;
	private JTextField txtFldCountManga;
	private JTextField txtFldAuthorManga;
	private JTextField txtFldPublisherManga;

	private JRadioButton rdBtnSeriesManga;
	private JRadioButton rdBtnSpecialManga;
	private JCheckBox chckBxFinishedManga;
	private JCheckBox chckBxDroppedManga;
	private JCheckBox chckBxOngoingManga;

	private JButton btnSave;
	private JLabel lblTopInfoAnime;
	private JLabel lblTopInfoManga;
	private JPanel cardLayout;
	private JPanel pnlFieldsManga;

	/**
	 * Create the panel.
	 * @param gui pointer to main gui so that data can be updated
	 */
	public NewEntryView(GUI gui) {
		mainGUI = gui;

		setLayout(null);
		
		btnSave = new JButton("Add");
		btnSave.setBounds(168, 341, 89, 23);
		add(btnSave);
		
		//Initialize components of card layouts
		cardLayout = new JPanel();
		cardLayout.setBounds(10, 11, 405, 319);
		add(cardLayout);
		cardLayout.setLayout(new CardLayout(0, 0));


		initAnimeCard();
		initMangaCard();

		createEvents();
	}
	
	/**
	 * Initializes components for the Manga card of the AddView
	 */
	private void initMangaCard() {
		pnlFieldsManga = new JPanel();
		cardLayout.add(pnlFieldsManga, "addManga");
		pnlFieldsManga.setLayout(null);
		
		lblTopInfoManga = new JLabel(DEFAULT_TEXT_MANGA, SwingConstants.CENTER);
		lblTopInfoManga.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTopInfoManga.setBounds(10, 5, 385, 52);
		pnlFieldsManga.add(lblTopInfoManga);

		
		JLabel lblTitleManga = new JLabel("Title:");
		lblTitleManga.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitleManga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitleManga.setBounds(10, 65, 99, 14);
		pnlFieldsManga.add(lblTitleManga);
		
		JLabel lblYearManga = new JLabel("Year:");
		lblYearManga.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblYearManga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYearManga.setBounds(10, 90, 99, 14);
		pnlFieldsManga.add(lblYearManga);
		
		JLabel lblCountManga = new JLabel("Number Watched:");
		lblCountManga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCountManga.setBounds(10, 115, 99, 14);
		pnlFieldsManga.add(lblCountManga);
		
		JLabel lblTypeManga = new JLabel("Content Type:");
		lblTypeManga.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTypeManga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTypeManga.setBounds(10, 168, 99, 14);
		pnlFieldsManga.add(lblTypeManga);
		
		JLabel lblStatusManga = new JLabel("Status:");
		lblStatusManga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatusManga.setBounds(10, 197, 99, 14);
		pnlFieldsManga.add(lblStatusManga);
		
		JLabel lblAuthorManga = new JLabel("Author(s):");
		lblAuthorManga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthorManga.setBounds(10, 143, 99, 14);
		pnlFieldsManga.add(lblAuthorManga);

		JLabel lblPublisherManga = new JLabel("Publisher:");
		lblPublisherManga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPublisherManga.setBounds(10, 222, 99, 14);
		pnlFieldsManga.add(lblPublisherManga);

		JLabel lblNotesManga = new JLabel("You can add notes/comments to this entry on the main viewer.");
		lblNotesManga.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotesManga.setBounds(10, 271, 385, 36);
		pnlFieldsManga.add(lblNotesManga);
		
		txtFldTitleManga = new JTextField();
		txtFldTitleManga.setBounds(119, 62, 224, 20);
		pnlFieldsManga.add(txtFldTitleManga);
		txtFldTitleManga.setColumns(10);
		
		txtFldYearManga = new JTextField();
		txtFldYearManga.setColumns(10);
		txtFldYearManga.setBounds(119, 87, 55, 20);
		pnlFieldsManga.add(txtFldYearManga);
		
		txtFldCountManga = new JTextField();
		txtFldCountManga.setColumns(10);
		txtFldCountManga.setBounds(119, 112, 55, 20);
		pnlFieldsManga.add(txtFldCountManga);
		
		txtFldAuthorManga = new JTextField();
		txtFldAuthorManga.setColumns(10);
		txtFldAuthorManga.setBounds(119, 140, 224, 20);
		pnlFieldsManga.add(txtFldAuthorManga);
		
		txtFldPublisherManga = new JTextField();
		txtFldPublisherManga.setColumns(10);
		txtFldPublisherManga.setBounds(119, 222, 224, 20);
		pnlFieldsManga.add(txtFldPublisherManga);
		
		chckBxFinishedManga = new JCheckBox("Finished");
		chckBxFinishedManga.setBounds(115, 193, 70, 23);
		pnlFieldsManga.add(chckBxFinishedManga);

		chckBxDroppedManga = new JCheckBox("Dropped");
		chckBxDroppedManga.setBounds(189, 193, 70, 23);
		pnlFieldsManga.add(chckBxDroppedManga);
		
		chckBxOngoingManga = new JCheckBox("Ongoing");
		chckBxOngoingManga.setBounds(263, 193, 70, 23);
		pnlFieldsManga.add(chckBxOngoingManga);
		
		rdBtnSeriesManga = new JRadioButton("Series");
		rdBtnSeriesManga.setBounds(115, 165, 70, 23);
		pnlFieldsManga.add(rdBtnSeriesManga);
		
		rdBtnSpecialManga = new JRadioButton("Special");
		rdBtnSpecialManga.setBounds(182, 164, 70, 23);
		pnlFieldsManga.add(rdBtnSpecialManga);	
	}

	/**
	 * Initializes components for the Anime card of the AddView
	 */
	private void initAnimeCard() {
		JPanel pnlFieldsAnime = new JPanel();
		cardLayout.add(pnlFieldsAnime, "addAnime");
		pnlFieldsAnime.setLayout(null);

		lblTopInfoAnime = new JLabel(DEFAULT_TEXT_ANIME, SwingConstants.CENTER);
		lblTopInfoAnime.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTopInfoAnime.setBounds(10, 5, 385, 52);
		pnlFieldsAnime.add(lblTopInfoAnime);

		
		JLabel lblTitleAnime = new JLabel("Title:");
		lblTitleAnime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTitleAnime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitleAnime.setBounds(10, 65, 99, 14);
		pnlFieldsAnime.add(lblTitleAnime);
		
		JLabel lblYearAnime = new JLabel("Year:");
		lblYearAnime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblYearAnime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYearAnime.setBounds(10, 90, 99, 14);
		pnlFieldsAnime.add(lblYearAnime);
		
		JLabel lblCountAnime = new JLabel("Number Watched:");
		lblCountAnime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCountAnime.setBounds(10, 115, 99, 14);
		pnlFieldsAnime.add(lblCountAnime);
		
		JLabel lblLanguageAnime = new JLabel("Language:");
		lblLanguageAnime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLanguageAnime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLanguageAnime.setBounds(10, 140, 99, 14);
		pnlFieldsAnime.add(lblLanguageAnime);
		
		JLabel lblTypeAnime = new JLabel("Content Type:");
		lblTypeAnime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTypeAnime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTypeAnime.setBounds(10, 168, 99, 14);
		pnlFieldsAnime.add(lblTypeAnime);
		
		JLabel lblStatusAnime = new JLabel("Status:");
		lblStatusAnime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatusAnime.setBounds(10, 197, 99, 14);
		pnlFieldsAnime.add(lblStatusAnime);
		
		JLabel lblDirectorAnime = new JLabel("Director:");
		lblDirectorAnime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDirectorAnime.setBounds(10, 224, 99, 14);
		pnlFieldsAnime.add(lblDirectorAnime);

		JLabel lblStudioAnime = new JLabel("Studio:");
		lblStudioAnime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudioAnime.setBounds(10, 246, 99, 14);
		pnlFieldsAnime.add(lblStudioAnime);

		JLabel lblNotesAnime = new JLabel("You can add notes/comments to this entry on the main viewer.");
		lblNotesAnime.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotesAnime.setBounds(10, 271, 385, 36);
		pnlFieldsAnime.add(lblNotesAnime);
		
		txtFldTitleAnime = new JTextField();
		txtFldTitleAnime.setBounds(119, 62, 224, 20);
		pnlFieldsAnime.add(txtFldTitleAnime);
		txtFldTitleAnime.setColumns(10);
		
		txtFldYearAnime = new JTextField();
		txtFldYearAnime.setColumns(10);
		txtFldYearAnime.setBounds(119, 87, 55, 20);
		pnlFieldsAnime.add(txtFldYearAnime);
		
		txtFldCountAnime = new JTextField();
		txtFldCountAnime.setColumns(10);
		txtFldCountAnime.setBounds(119, 112, 55, 20);
		pnlFieldsAnime.add(txtFldCountAnime);
		
		txtFldDirectorAnime = new JTextField();
		txtFldDirectorAnime.setColumns(10);
		txtFldDirectorAnime.setBounds(119, 221, 224, 20);
		pnlFieldsAnime.add(txtFldDirectorAnime);
		
		txtFldStudioAnime = new JTextField();
		txtFldStudioAnime.setColumns(10);
		txtFldStudioAnime.setBounds(119, 246, 224, 20);
		pnlFieldsAnime.add(txtFldStudioAnime);
		
		rdBtnSubAnime = new JRadioButton("Sub");	
		rdBtnSubAnime.setBounds(115, 137, 55, 23);
		pnlFieldsAnime.add(rdBtnSubAnime);
		
		rdBtnDubAnime = new JRadioButton("Dub");
		rdBtnDubAnime.setBounds(172, 136, 55, 23);
		pnlFieldsAnime.add(rdBtnDubAnime);
		
		rdBtnOtherAnime = new JRadioButton("Other/TBD");
		rdBtnOtherAnime.setBounds(230, 136, 82, 23);
		pnlFieldsAnime.add(rdBtnOtherAnime);
		
		chckBxFinishedAnime = new JCheckBox("Finished");
		chckBxFinishedAnime.setBounds(115, 193, 70, 23);
		pnlFieldsAnime.add(chckBxFinishedAnime);
		
		chckBxDroppedAnime = new JCheckBox("Dropped");
		chckBxDroppedAnime.setBounds(189, 193, 70, 23);
		pnlFieldsAnime.add(chckBxDroppedAnime);
		
		rdBtnSeriesAnime = new JRadioButton("Series");
		rdBtnSeriesAnime.setBounds(115, 165, 70, 23);
		pnlFieldsAnime.add(rdBtnSeriesAnime);
		
		rdBtnSpecialAnime = new JRadioButton("Special");
		rdBtnSpecialAnime.setBounds(182, 164, 70, 23);
		pnlFieldsAnime.add(rdBtnSpecialAnime);
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
					JOptionPane.showMessageDialog(getRootPane(), e1.getMessage());
				}
			}
		});

		
		//Events for ensuring only one radio button of each type can be selected at once
		//Language
		rdBtnSubAnime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtnSubAnime.isSelected()) {
					rdBtnDubAnime.setSelected(false);
					rdBtnOtherAnime.setSelected(false);
				}
			
			}
		});
		rdBtnDubAnime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtnDubAnime.isSelected()) {
					rdBtnSubAnime.setSelected(false);
					rdBtnOtherAnime.setSelected(false);
				}
			
			}
		});
		rdBtnOtherAnime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtnOtherAnime.isSelected()) {
					rdBtnSubAnime.setSelected(false);
					rdBtnDubAnime.setSelected(false);
				}
			
			}
		});

		//Type
		rdBtnSeriesAnime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtnSeriesAnime.isSelected()) {
					rdBtnSpecialAnime.setSelected(false);
				}
			
			}
		});
		rdBtnSpecialAnime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtnSpecialAnime.isSelected()) {
					rdBtnSeriesAnime.setSelected(false);
				}
			
			}
		});

	
	
	}
	
	//TODO: clear fields and reset card togetger. Test!!
	
	/**
	 * Resets the fields to blank after an entry is successfully added
	 */
	private void clearFields() {
		//Clear Anime fields
		txtFldTitleAnime.setText("");
		txtFldYearAnime.setText("");
		txtFldCountAnime.setText("");
		txtFldDirectorAnime.setText("");
		txtFldStudioAnime.setText("");
		rdBtnSubAnime.setSelected(false);
		rdBtnDubAnime.setSelected(false);
		rdBtnOtherAnime.setSelected(false);
		rdBtnSeriesAnime.setSelected(false);
		rdBtnSpecialAnime.setSelected(false);
		chckBxFinishedAnime.setSelected(false);
		chckBxDroppedAnime.setSelected(false);
		
		//Clear Manga fields
		txtFldTitleManga.setText("");
		txtFldYearManga.setText("");
		txtFldCountManga.setText("");
		txtFldAuthorManga.setText("");
		txtFldPublisherManga.setText("");
		rdBtnSeriesManga.setSelected(false);
		rdBtnSpecialManga.setSelected(false);
		chckBxFinishedManga.setSelected(false);
		chckBxDroppedManga.setSelected(false);
		chckBxOngoingManga.setSelected(false);
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
		String studio = "";
		
		//Check fields are acceptable
		try {

			//Required fields
			title = txtFldTitleAnime.getText();
			if (txtFldTitleAnime.getText().isBlank()) {
				throw new IllegalArgumentException("Entry must have a title.");
			}
			
			year = Integer.parseInt(txtFldYearAnime.getText());

			if (rdBtnSubAnime.isSelected()) {
				lan = Language.SUB;
			} else if (rdBtnDubAnime.isSelected()) {
				lan = Language.DUB;
			} else if (rdBtnOtherAnime.isSelected()) {
				lan = Language.OTHER;
			} else {
				//In case nothing is selected
				throw new IllegalArgumentException("Language not indicated.");
			}
			
			if (rdBtnSeriesAnime.isSelected()) {
				type = Type.SERIES;
			} else if (rdBtnSpecialAnime.isSelected()) {
				type = Type.SPECIAL;
			} else {
				//In case neither is selected
				throw new IllegalArgumentException("Type not indicated.");
			}

			
			
			//Test not required fields if filled out		
			if (!txtFldCountAnime.getText().isBlank()) {
				count = Integer.parseInt(txtFldCountAnime.getText());	
			}
			
			fin = chckBxFinishedAnime.isSelected();
			drop = chckBxDroppedAnime.isSelected();			
			if (fin && drop) {
				throw new IllegalArgumentException("Show cannot be finished and dropped.");
			}
			
			if (!txtFldDirectorAnime.getText().isBlank()) {
				director = txtFldDirectorAnime.getText();	
			}
			
			if (!txtFldStudioAnime.getText().isBlank()) {
				studio = txtFldStudioAnime.getText();	
			}
			
			//Create the anime
			Anime a = new Anime(title, year, count, lan, type, fin, drop, director, studio, "");
			Manager.getInstance().addAnime(a);
			mainGUI.updateData(MediaType.ANIME);
		} catch (Exception e) {
			
			if (e instanceof NumberFormatException) {
				throw new IllegalArgumentException("Cannot understand some numerical input.");	
			} else {
				throw new IllegalArgumentException(e.getMessage());
			}
		}

		//By here, was successful so can adjust top text to indicate so.
		secondaryText = "<html><em>" + title + "</em><br>was created successfully. Feel free to add another!</html>";
		lblTopInfoAnime.setText(secondaryText);
	}

	/**
	 * Called when the user clicks the add button, checks values on the card panel 
	 * and tries to create a Manga from them.
	 * @throws IllegalArgumentException if bad values, construction fails, or required data not provided by user
	 */
	public void makeNewManga() {
		//Declare fields for constructor
		String title;
		int year;
		int count = 0;
		Type type;
		boolean fin;
		boolean drop;
		boolean ongoing;
		String author = "";
		String publisher = "";
		
		//Check fields are acceptable
		try {

			//Required fields
			title = txtFldTitleManga.getText();
			if (txtFldTitleManga.getText().isBlank()) {
				throw new IllegalArgumentException("Entry must have a title.");
			}
			
			year = Integer.parseInt(txtFldYearManga.getText());
			
			if (rdBtnSeriesManga.isSelected()) {
				type = Type.SERIES;
			} else if (rdBtnSpecialManga.isSelected()) {
				type = Type.SPECIAL;
			} else {
				//In case neither is selected
				throw new IllegalArgumentException("Type not indicated.");
			}

			//Test not required fields if filled out		
			if (!txtFldCountManga.getText().isBlank()) {
				count = Integer.parseInt(txtFldCountManga.getText());	
			}
			
			fin = chckBxFinishedManga.isSelected();
			drop = chckBxDroppedManga.isSelected();
			ongoing = chckBxOngoingManga.isSelected();
			if (fin && drop) {
				throw new IllegalArgumentException("Show cannot be finished and dropped.");
			}
			
			if (!txtFldAuthorManga.getText().isBlank()) {
				author = txtFldAuthorManga.getText();	
			}
			
			if (!txtFldPublisherManga.getText().isBlank()) {
				publisher = txtFldPublisherManga.getText();	
			}
			
			//Create the Manga
			Manga m = new Manga(title, year, count, author, publisher, type, fin, drop, ongoing, "");
			Manager.getInstance().addManga(m);
			mainGUI.updateData(MediaType.MANGA);
		} catch (Exception e) {
			
			if (e instanceof NumberFormatException) {
				throw new IllegalArgumentException("Cannot understand some numerical input.");	
			} else {
				throw new IllegalArgumentException(e.getMessage());
			}
		}

		//By here, was successful so can adjust top text to indicate so.
		secondaryText = "<html><em>" + title + "</em><br>was created successfully. Feel free to add another!</html>";
		lblTopInfoManga.setText(secondaryText);
	}
	
	/**
	 * Clears the card by resetting instruction text at the top of the panel to the default and clearing all fields..
	 * Called with every card change.
	 */
	public void resetCard() {
		lblTopInfoAnime.setText(DEFAULT_TEXT_ANIME);
		lblTopInfoManga.setText(DEFAULT_TEXT_MANGA);
		
		clearFields();
	}
	
}