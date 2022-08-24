package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import data.Anime;
import data.Anime.Language;
import data.Media.MediaType;
import data.Media.Type;
import data.Manga;
import data.Media;
import manager.Manager;
import util.SortedMediaList;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import javax.swing.JScrollPane;

/**
 * Defines the GUI components for viewing media entries
 * @author Hunter Pruitt
 */
public class BrowseView extends JPanel {

	private static final String WARNING_MESSAGE = "You have unsaved changes. Are you sure you want to leave?";

	/** Pointer to the main GUI component, used for accessing mediaMode and other functions */
	private GUI mainGUI;

	/** Indicates if user is able to edit data components, acts as a flag to prevent leaving unsaved data and such */
	private boolean inEditMode = false;

	/** The Media object currently being displayed */
	private Media currentEntry;
	
	private JTextField txtFldTitleAnime;
	private JTextField txtFldYearAnime;
	private JTextField txtFldCountAnime;
	private JTextField txtFldDirectorAnime;
	private JTextField txtFldStudioAnime;
	private JTextArea txtAreaNotesAnime;
	private JRadioButton rdBtnSubAnime;
	private JRadioButton rdBtnDubAnime;
	private JRadioButton rdBtnOtherAnime;
	private JRadioButton rdBtnSpecialAnime;
	private JRadioButton rdBtnSeriesAnime;
	private JCheckBox chckBxFinishedAnime;
	private JCheckBox chckBxDroppedAnime;

	private JTextField txtFldTitleManga;
	private JTextField txtFldYearManga;
	private JTextField txtFldCountManga;
	private JTextField txtFldAuthorManga;
	private JTextField txtFldPublisherManga;
	private JTextArea txtAreaNotesManga;
	private JRadioButton rdBtnSpecialManga;
	private JRadioButton rdBtnSeriesManga;
	private JCheckBox chckBxFinishedManga;
	private JCheckBox chckBxDroppedManga;
	private JCheckBox chckBxOngoingManga;
	
	private JButton btnEdit;
	private JButton btnNext;
	private JButton btnPrevious;

	private JPanel cardLayout;
	private JScrollPane scrollPaneNotesAnime;
	private JScrollPane scrollPaneNotesManga;
	
	/**
	 * Create the panel.
	 * @param gui pointer to main part of the GUI so that this card which is inside it can access it's public methods
	 */
	public BrowseView(GUI gui) {
		//Attach reference to main GUI class
		mainGUI = gui;
		setLayout(null);


		btnNext = new JButton("Next");
		btnNext.setBounds(295, 341, 89, 23);
		add(btnNext);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(167, 341, 89, 23);
		add(btnEdit);
		
		btnPrevious = new JButton("Previous");
		btnPrevious.setBounds(39, 341, 89, 23);
		add(btnPrevious);
		
		//Initialize components of card layouts
		cardLayout = new JPanel();
		cardLayout.setBounds(10, 11, 405, 326);
		add(cardLayout);
		cardLayout.setLayout(new CardLayout(0, 0));

		initAnimeCard();
		initMangaCard();

		//Events
		createEvents();
	}

	/**
	 * Initializes components for the Anime card of the Browse View
	 */
	private void initAnimeCard() {
		JPanel pnlFieldsAnime = new JPanel();
		cardLayout.add(pnlFieldsAnime, "browseAnime");
		pnlFieldsAnime.setLayout(null);

		JLabel lblTitleAnime = new JLabel("Title:");
		lblTitleAnime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTitleAnime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitleAnime.setBounds(10, 8, 99, 14);
		pnlFieldsAnime.add(lblTitleAnime);
		
		JLabel lblYearAnime = new JLabel("Year:");
		lblYearAnime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblYearAnime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYearAnime.setBounds(10, 33, 99, 14);
		pnlFieldsAnime.add(lblYearAnime);
		
		JLabel lblCountAnime = new JLabel("Number Watched:");
		lblCountAnime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCountAnime.setBounds(10, 57, 99, 14);
		pnlFieldsAnime.add(lblCountAnime);
		
		JLabel lblLanguageAnime = new JLabel("Language:");
		lblLanguageAnime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLanguageAnime.setBounds(10, 84, 99, 14);
		pnlFieldsAnime.add(lblLanguageAnime);
		
		JLabel lblTypeAnime = new JLabel("Content Type:");
		lblTypeAnime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTypeAnime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTypeAnime.setBounds(10, 112, 99, 14);
		pnlFieldsAnime.add(lblTypeAnime);
		
		JLabel lblStatusAnime = new JLabel("Status:");
		lblStatusAnime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatusAnime.setBounds(10, 140, 99, 14);
		pnlFieldsAnime.add(lblStatusAnime);
		
		JLabel lblDirectorAnime = new JLabel("Director:");
		lblDirectorAnime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDirectorAnime.setBounds(10, 167, 99, 14);
		pnlFieldsAnime.add(lblDirectorAnime);
		
		JLabel lblStudioAnime = new JLabel("Studio:");
		lblStudioAnime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudioAnime.setBounds(10, 192, 99, 14);
		pnlFieldsAnime.add(lblStudioAnime);
		
		JLabel lblNotesAnime = new JLabel("Notes:");
		lblNotesAnime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNotesAnime.setBounds(10, 200, 70, 14);
		pnlFieldsAnime.add(lblNotesAnime);


		txtFldTitleAnime = new JTextField();
		txtFldTitleAnime.setEditable(false);
		txtFldTitleAnime.setBounds(119, 5, 224, 20);
		txtFldTitleAnime.setColumns(10);
		pnlFieldsAnime.add(txtFldTitleAnime);

		txtFldYearAnime = new JTextField();
		txtFldYearAnime.setEditable(false);
		txtFldYearAnime.setColumns(10);
		txtFldYearAnime.setBounds(119, 30, 55, 20);
		pnlFieldsAnime.add(txtFldYearAnime);
		
		txtFldCountAnime = new JTextField();
		txtFldCountAnime.setEditable(false);
		txtFldCountAnime.setColumns(10);
		txtFldCountAnime.setBounds(119, 55, 55, 20);
		pnlFieldsAnime.add(txtFldCountAnime);
		
		txtFldDirectorAnime = new JTextField();
		txtFldDirectorAnime.setEditable(false);
		txtFldDirectorAnime.setColumns(10);
		txtFldDirectorAnime.setBounds(119, 164, 172, 20);
		pnlFieldsAnime.add(txtFldDirectorAnime);
		
		txtFldStudioAnime = new JTextField();
		txtFldStudioAnime.setEditable(false);
		txtFldStudioAnime.setColumns(10);
		txtFldStudioAnime.setBounds(119, 189, 172, 20);
		pnlFieldsAnime.add(txtFldStudioAnime);
		
		scrollPaneNotesAnime = new JScrollPane();
		scrollPaneNotesAnime.setBounds(10, 219, 385, 96);
		pnlFieldsAnime.add(scrollPaneNotesAnime);
		
		txtAreaNotesAnime = new JTextArea();
		txtAreaNotesAnime.setWrapStyleWord(true);
		scrollPaneNotesAnime.setViewportView(txtAreaNotesAnime);
		txtAreaNotesAnime.setEditable(false);
		txtAreaNotesAnime.setColumns(10);
		txtAreaNotesAnime.setLineWrap(true);
		
		rdBtnSubAnime = new JRadioButton("Sub");
		rdBtnSubAnime.setEnabled(false);
		rdBtnSubAnime.setBounds(115, 80, 55, 23);
		pnlFieldsAnime.add(rdBtnSubAnime);
		
		rdBtnDubAnime = new JRadioButton("Dub");
		rdBtnDubAnime.setEnabled(false);
		rdBtnDubAnime.setBounds(172, 80, 55, 23);
		pnlFieldsAnime.add(rdBtnDubAnime);
		
		rdBtnOtherAnime = new JRadioButton("Other/TBD");
		rdBtnOtherAnime.setEnabled(false);
		rdBtnOtherAnime.setBounds(229, 80, 77, 23);
		pnlFieldsAnime.add(rdBtnOtherAnime);
		
		chckBxFinishedAnime = new JCheckBox("Finished");
		chckBxFinishedAnime.setEnabled(false);
		chckBxFinishedAnime.setBounds(115, 136, 70, 23);
		pnlFieldsAnime.add(chckBxFinishedAnime);
		
		chckBxDroppedAnime = new JCheckBox("Dropped");
		chckBxDroppedAnime.setEnabled(false);
		chckBxDroppedAnime.setBounds(187, 136, 70, 23);
		pnlFieldsAnime.add(chckBxDroppedAnime);
		
		rdBtnSeriesAnime = new JRadioButton("Series");
		rdBtnSeriesAnime.setEnabled(false);
		rdBtnSeriesAnime.setBounds(115, 108, 70, 23);
		pnlFieldsAnime.add(rdBtnSeriesAnime);
		
		rdBtnSpecialAnime = new JRadioButton("Special");
		rdBtnSpecialAnime.setEnabled(false);
		rdBtnSpecialAnime.setBounds(182, 108, 70, 23);
		pnlFieldsAnime.add(rdBtnSpecialAnime);
	}

	/**
	 * Initializes components for the Manga card of the Browse View
	 */
	private void initMangaCard() {
		JPanel pnlFieldsManga = new JPanel();
		cardLayout.add(pnlFieldsManga, "browseManga");
		pnlFieldsManga.setLayout(null);
		
		JLabel lblTitleManga = new JLabel("Title:");
		lblTitleManga.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTitleManga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitleManga.setBounds(10, 8, 99, 14);
		pnlFieldsManga.add(lblTitleManga);
		
		JLabel lblYearManga = new JLabel("Year:");
		lblYearManga.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblYearManga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYearManga.setBounds(10, 33, 99, 14);
		pnlFieldsManga.add(lblYearManga);
		
		JLabel lblCountManga = new JLabel("Number Read:");
		lblCountManga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCountManga.setBounds(10, 57, 99, 14);
		pnlFieldsManga.add(lblCountManga);
				
		JLabel lblTypeManga = new JLabel("Content Type:");
		lblTypeManga.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTypeManga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTypeManga.setBounds(10, 112, 99, 14);
		pnlFieldsManga.add(lblTypeManga);
		
		JLabel lblStatusManga = new JLabel("Status:");
		lblStatusManga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatusManga.setBounds(10, 140, 99, 14);
		pnlFieldsManga.add(lblStatusManga);
		
		JLabel lblAuthorManga = new JLabel("Author(s):");
		lblAuthorManga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthorManga.setBounds(10, 85, 99, 14);
		pnlFieldsManga.add(lblAuthorManga);
		
		JLabel lblPublisherManga = new JLabel("Publisher:");
		lblPublisherManga.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPublisherManga.setBounds(10, 168, 99, 14);
		pnlFieldsManga.add(lblPublisherManga);
		
		JLabel lblNotesManga = new JLabel("Notes:");
		lblNotesManga.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNotesManga.setBounds(10, 200, 70, 14);
		pnlFieldsManga.add(lblNotesManga);

				
		txtFldTitleManga = new JTextField();
		txtFldTitleManga.setEditable(false);
		txtFldTitleManga.setBounds(119, 5, 224, 20);
		txtFldTitleManga.setColumns(10);
		pnlFieldsManga.add(txtFldTitleManga);

		
		txtFldYearManga = new JTextField();
		txtFldYearManga.setEditable(false);
		txtFldYearManga.setColumns(10);
		txtFldYearManga.setBounds(119, 30, 55, 20);
		pnlFieldsManga.add(txtFldYearManga);
		
		txtFldCountManga = new JTextField();
		txtFldCountManga.setEditable(false);
		txtFldCountManga.setColumns(10);
		txtFldCountManga.setBounds(119, 55, 55, 20);
		pnlFieldsManga.add(txtFldCountManga);
		
		txtFldAuthorManga = new JTextField();
		txtFldAuthorManga.setEditable(false);
		txtFldAuthorManga.setColumns(10);
		txtFldAuthorManga.setBounds(119, 82, 172, 20);
		pnlFieldsManga.add(txtFldAuthorManga);
		
		txtFldPublisherManga = new JTextField();
		txtFldPublisherManga.setEditable(false);
		txtFldPublisherManga.setColumns(10);
		txtFldPublisherManga.setBounds(119, 165, 172, 20);
		pnlFieldsManga.add(txtFldPublisherManga);
		
		scrollPaneNotesManga = new JScrollPane();
		scrollPaneNotesManga.setBounds(10, 219, 385, 96);
		pnlFieldsManga.add(scrollPaneNotesManga);
		
		txtAreaNotesManga = new JTextArea();
		txtAreaNotesManga.setLineWrap(true);
		txtAreaNotesManga.setWrapStyleWord(true);
		scrollPaneNotesManga.setViewportView(txtAreaNotesManga);
		txtAreaNotesManga.setEditable(false);
		txtAreaNotesManga.setColumns(10);
		
		chckBxFinishedManga = new JCheckBox("Finished");
		chckBxFinishedManga.setEnabled(false);
		chckBxFinishedManga.setBounds(115, 136, 70, 23);
		pnlFieldsManga.add(chckBxFinishedManga);
		
		chckBxDroppedManga = new JCheckBox("Dropped");
		chckBxDroppedManga.setEnabled(false);
		chckBxDroppedManga.setBounds(187, 136, 70, 23);
		pnlFieldsManga.add(chckBxDroppedManga);
		
		chckBxOngoingManga = new JCheckBox("Ongoing");
		chckBxOngoingManga.setEnabled(false);
		chckBxOngoingManga.setBounds(259, 136, 70, 23);
		pnlFieldsManga.add(chckBxOngoingManga);
		
		rdBtnSeriesManga = new JRadioButton("Series");
		rdBtnSeriesManga.setEnabled(false);
		rdBtnSeriesManga.setBounds(115, 108, 70, 23);
		pnlFieldsManga.add(rdBtnSeriesManga);
		
		rdBtnSpecialManga = new JRadioButton("Special");
		rdBtnSpecialManga.setEnabled(false);
		rdBtnSpecialManga.setBounds(182, 108, 70, 23);
		pnlFieldsManga.add(rdBtnSpecialManga);
	}

	/**
	 * Creates events for components inside the card panel, so that fields can be edited/saved and interactables behave correctly
	 */
	private void createEvents() {		
		//Events pertaining to large buttons, edit/save, next, previous
		//Edit/Save button
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Manage inputs in edit mode
				if (inEditMode) {
						try {				
							if (changeOccurred()) {	
								Manager manager = Manager.getInstance();
								Media updatedEntry = null;
								switch (mainGUI.getMediaMode()) {
								case ANIME:
									//Try to construct new Anime
									updatedEntry = makeNewAnime();
		
									//Update and regenerate data
									manager.removeAnime(currentEntry);
									manager.addAnime(updatedEntry);
									break;
								case MANGA:
									//Try to construct new Manga
									updatedEntry = makeNewManga();
		
									//Update and regenerate data
									manager.removeManga(currentEntry);
									manager.addManga(updatedEntry);
									break;
								}
								
								mainGUI.updateData(null);
								setCurrentEntry(updatedEntry);
								
							}
						} catch (Exception e1) {
							//Show warning dialog if necessary
							JOptionPane.showMessageDialog(getRootPane(), e1.getMessage());
							return;
						}
					
				}
				//Regardless of mode, if make it this far, change to other mode
				changeEditMode();
			}
		});

		
		//Next button
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayNext();
			}
		});

		//Previous button
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayPrev();
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
		rdBtnSeriesManga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtnSeriesManga.isSelected()) {
					rdBtnSpecialManga.setSelected(false);
				}
			}
		});
		rdBtnSpecialManga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtnSpecialManga.isSelected()) {
					rdBtnSeriesManga.setSelected(false);
				}
			}
		});
	}

	/**
	 * Sets the current entry to an entry on the list or null if changing view from browse view
	 * @param m Media to load
	 */
	public void setCurrentEntry(Media m) {		
		this.currentEntry = m;
		if (m != null) {
			loadData();
		}

	}
	
	/**
	 * Returns the current entry. Used by the main gui to reselect proper table rows if user tries to leave unsaved changes
	 * @return media object which is currently displayed in this view
	 */
	public Media getCurrentEntry() {
		return currentEntry;
	}
	
	
	/**
	 * Indicates if a change occurred while the user was in edit mode
	 * If an IllegalArgumentException is thrown (when a type or language isn't selected) false is returned.
	 * @return boolean indicator if a value has changed since enabling the field components
	 * @throws IllegalArgumentException if MediaMode is not an acceptable value
	 */
	private boolean changeOccurred() {
		//Transform int fields into Strings comparable to what is in the text fields
		String oldYear = "" + currentEntry.getYear();
		String oldCount = "" + currentEntry.getCount();
		//Return statements to compare each field until either a contrast is found or all match
		try {
			switch (mainGUI.getMediaMode()) {
			case ANIME:
				Anime currentAnime = (Anime) currentEntry;
				return !currentAnime.getTitle().equals(txtFldTitleAnime.getText()) 
						|| !oldYear.equals(txtFldYearAnime.getText())
						|| !oldCount.equals(txtFldCountAnime.getText())
						|| !currentAnime.getLanguage().equals(getSelectedLanguage())
						|| !currentAnime.getType().equals(getSelectedType())
						|| currentAnime.isFinished() != chckBxFinishedAnime.isSelected()
						|| currentAnime.isDropped() != chckBxDroppedAnime.isSelected()
						|| !currentAnime.getDirector().equals(txtFldDirectorAnime.getText())
						|| !currentAnime.getStudio().equals(txtFldStudioAnime.getText())
						|| !currentAnime.getNotes().equals(txtAreaNotesAnime.getText());
			case MANGA:
				Manga currentManga = (Manga) currentEntry;
				return !currentManga.getTitle().equals(txtFldTitleManga.getText()) 
						|| !oldYear.equals(txtFldYearManga.getText())
						|| !oldCount.equals(txtFldCountManga.getText())
						|| !currentManga.getAuthor().equals(txtFldAuthorManga.getText())
						|| !currentManga.getPublisher().equals(txtFldPublisherManga.getText())
						|| !currentManga.getType().equals(getSelectedType())
						|| currentManga.isFinished() != chckBxFinishedManga.isSelected()
						|| currentManga.isDropped() != chckBxDroppedManga.isSelected()
						|| currentManga.isOngoing() != chckBxOngoingManga.isSelected()						
						|| !currentManga.getNotes().equals(txtAreaNotesManga.getText());
			default:
				throw new IllegalArgumentException("Can't parse media mode.");
			}

		} catch (IllegalArgumentException e) {
			//If something is wrong (likely no selection on the radio buttons) where things were previously right,
			//it is another indicator that a change has occurred
			return true;
		}

	}

	/**
	 * Indicates which type classification the user selected among the relevant JRadioButtons
	 * @return String indicator as to what type is currently selected
	 * @throws IllegalArgumentException if no type is indicated or if MediaMode is not an acceptable value
	 */
	private String getSelectedType() {
		String type;
		switch (mainGUI.getMediaMode()) {
		case ANIME:
			if (rdBtnSeriesAnime.isSelected()) {
				type = Type.SERIES.formattedName;
			} else if (rdBtnSpecialAnime.isSelected()) {
				type = Type.SPECIAL.formattedName;
			} else {
				//In case neither is selected
				throw new IllegalArgumentException("Type not indicated.");
			}
			break;
		case MANGA: 
			if (rdBtnSeriesManga.isSelected()) {
				type = Type.SERIES.formattedName;
			} else if (rdBtnSpecialManga.isSelected()) {
				type = Type.SPECIAL.formattedName;
			} else {
				//In case neither is selected
				throw new IllegalArgumentException("Type not indicated.");
			}
			break;
		default:
			throw new IllegalArgumentException("Can't parse media mode.");
		}

		return type;
	}
	
	/**
	 * Selects the correct JRadioButton depending on what type is passed
	 * @param type String indicator of a media type classification
	 * @throws IllegalArgumentException if type is not an accepted Type
	 */
	private void setSelectedType(String type) {
		switch (mainGUI.getMediaMode()) {
		case ANIME:
			if (type.equals(Type.SERIES.formattedName)) {
				rdBtnSeriesAnime.setSelected(true);
			} else if (type.equals(Type.SPECIAL.formattedName)) {
				rdBtnSpecialAnime.setSelected(true);
			} else {
				throw new IllegalArgumentException("Invalid type");
			}
			break;
		case MANGA:
			if (type.equals(Type.SERIES.formattedName)) {
				rdBtnSeriesManga.setSelected(true);
			} else if (type.equals(Type.SPECIAL.formattedName)) {
				rdBtnSpecialManga.setSelected(true);
			} else {
				throw new IllegalArgumentException("Invalid type");
			}
		}
	}


	/**
	 * Selects the correct JRadioButton depending on what language is passed.
	 * Should only be called when dealing with Anime objects
	 * @param lan String indicator of an anime language classification
	 * @throws IllegalArgumentException if lan is not an accepted Language */
	private void setSelectedLanguage (String lan) {
		if (lan.equals(Language.SUB.formattedName)) {
			rdBtnSubAnime.setSelected(true);
		} else if (lan.equals(Language.DUB.formattedName)) {
			rdBtnDubAnime.setSelected(true);
		} else if (lan.equals(Language.OTHER.formattedName)) {
			rdBtnOtherAnime.setSelected(true);
		} else {
			throw new IllegalArgumentException("Invalid language");
		}
	}
	
	/**
	 * Indicates which language the user selected among the relevant JRadioButtons.
	 * Should only be called when dealing with Anime objects
	 * @return String indicator as to what language is currently selected
	 * @throws IllegalArgumentException if no language is indicated
	 */
	private String getSelectedLanguage() {
		String lan;
		
		if (rdBtnSubAnime.isSelected()) {
			lan = Language.SUB.formattedName;
		} else if (rdBtnDubAnime.isSelected()) {
			lan = Language.DUB.formattedName;
		} else if (rdBtnOtherAnime.isSelected()) {
			lan = Language.OTHER.formattedName;
		} else {
			//In case nothing is selected
			throw new IllegalArgumentException("Language not indicated.");
		}
		
		return lan;
	}

	/**
	 * Toggles text fields and buttons abilities to be interacted with by the user, so that info can or cannot be edited
	 */
	private void changeEditMode() {
		//Toggle booleans for editable-ness
		switch (mainGUI.getMediaMode()) {
		case ANIME:
			txtFldTitleAnime.setEditable(!txtFldTitleAnime.isEditable());
			txtFldYearAnime.setEditable(!txtFldYearAnime.isEditable());
			txtFldCountAnime.setEditable(!txtFldCountAnime.isEditable());
			txtFldDirectorAnime.setEditable(!txtFldDirectorAnime.isEditable());
			txtFldStudioAnime.setEditable(!txtFldStudioAnime.isEditable());
			txtAreaNotesAnime.setEditable(!txtAreaNotesAnime.isEditable());
			rdBtnSubAnime.setEnabled(!rdBtnSubAnime.isEnabled());
			rdBtnDubAnime.setEnabled(!rdBtnDubAnime.isEnabled());
			rdBtnOtherAnime.setEnabled(!rdBtnOtherAnime.isEnabled());
			rdBtnSpecialAnime.setEnabled(!rdBtnSpecialAnime.isEnabled());
			rdBtnSeriesAnime.setEnabled(!rdBtnSeriesAnime.isEnabled());
			chckBxFinishedAnime.setEnabled(!chckBxFinishedAnime.isEnabled());
			chckBxDroppedAnime.setEnabled(!chckBxDroppedAnime.isEnabled());	
			break;
		case MANGA:
			txtFldTitleManga.setEditable(!txtFldTitleManga.isEditable());
			txtFldYearManga.setEditable(!txtFldYearManga.isEditable());
			txtFldCountManga.setEditable(!txtFldCountManga.isEditable());
			txtFldAuthorManga.setEditable(!txtFldAuthorManga.isEditable());
			txtFldPublisherManga.setEditable(!txtFldPublisherManga.isEditable());
			txtAreaNotesManga.setEditable(!txtAreaNotesManga.isEditable());
			rdBtnSpecialManga.setEnabled(!rdBtnSpecialManga.isEnabled());
			rdBtnSeriesManga.setEnabled(!rdBtnSeriesManga.isEnabled());
			chckBxFinishedManga.setEnabled(!chckBxFinishedManga.isEnabled());
			chckBxDroppedManga.setEnabled(!chckBxDroppedManga.isEnabled());
			chckBxOngoingManga.setEnabled(!chckBxOngoingManga.isEnabled());
			break;
		}

		this.inEditMode = !inEditMode;

		//Change the text on the button
		if (inEditMode) {
			btnEdit.setText("Save");
		} else {
			btnEdit.setText("Edit");
		}
	}

	/**
	 * Fills the page with information about a selected entry based on currentEntry field
	 */
	private void loadData() {
		//Empty current data first to be safe
		clearFields();
		
		//Load data
		switch (mainGUI.getMediaMode()) {
		case ANIME:
			toggleMediaCards(MediaType.ANIME);
			
			Anime currentAnime = (Anime) currentEntry;
			txtFldTitleAnime.setText(currentAnime.getTitle());
			txtFldYearAnime.setText("" + currentAnime.getYear());
			txtFldCountAnime.setText("" + currentAnime.getCount());
			setSelectedType(currentAnime.getType());
			setSelectedLanguage(currentAnime.getLanguage());
			if (currentAnime.isFinished()) {
				chckBxFinishedAnime.setSelected(true);
			} else if (currentAnime.isDropped()) {
				chckBxDroppedAnime.setSelected(true);
			}
			
			
			txtFldDirectorAnime.setText(currentAnime.getDirector());
			txtFldStudioAnime.setText(currentAnime.getStudio());
			txtAreaNotesAnime.setText(currentAnime.getNotes());
			
			//Reset the caret position to show the beginning of text fields
			txtFldTitleAnime.setCaretPosition(0);
			txtFldDirectorAnime.setCaretPosition(0);
			txtFldStudioAnime.setCaretPosition(0);
			break;
		case MANGA:
			toggleMediaCards(MediaType.MANGA);
			
			Manga currentManga = (Manga) currentEntry;
			txtFldTitleManga.setText(currentManga.getTitle());
			txtFldYearManga.setText("" + currentManga.getYear());
			txtFldCountManga.setText("" + currentManga.getCount());
			setSelectedType(currentManga.getType());
			if (currentManga.isFinished()) {
				chckBxFinishedManga.setSelected(true);
			} else if (currentManga.isDropped()) {
				chckBxDroppedManga.setSelected(true);
			}
			if (currentManga.isOngoing()) {
				chckBxOngoingManga.setSelected(true);
			}

			txtFldAuthorManga.setText(currentManga.getAuthor());
			txtFldPublisherManga.setText(currentManga.getPublisher());
			txtAreaNotesManga.setText(currentManga.getNotes());
			
			//Reset the caret position to show the beginning of text fields
			txtFldTitleManga.setCaretPosition(0);
			txtFldAuthorManga.setCaretPosition(0);
			txtFldPublisherManga.setCaretPosition(0);
			break;
		}

		//Disable next and/or previous buttons if such entries do not exist

		SortedMediaList list = Manager.getInstance().getList(); 
		int currentIdx = list.indexOf(currentEntry);

		btnNext.setEnabled(currentIdx < list.size() - 1);
		btnPrevious.setEnabled(currentIdx > 0);
	}

	
	/**
	 * Resets the fields to blank after an entry is successfully added
	 */
	private void clearFields() {
		//Clear Anime Fields
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
		txtAreaNotesAnime.setText("");
		
		//Clear Manga Fields
		txtFldTitleManga.setText("");
		txtFldYearManga.setText("");
		txtFldCountManga.setText("");
		txtFldAuthorManga.setText("");
		txtFldPublisherManga.setText("");
		txtAreaNotesManga.setText("");
		rdBtnSpecialManga.setSelected(false);
		rdBtnSeriesManga.setSelected(false);
		chckBxFinishedManga.setSelected(false);
		chckBxDroppedManga.setSelected(false);
		chckBxOngoingManga.setSelected(false);		
	}

	
	/**
	 * Shows the user a warning JOptionDialog if they are trying to leave unsaved changes.
	 * Depending on user action and circumstance, the method caller is 
	 * informed if it is okay to proceed with the leave operation
	 * Public so that it can be called when components in main GUI are clicked.
	 * 
	 * @return boolean indicator of if it's ok to handle the leave operation, 
	 * such as if a user wants to change views or look at another anime
	 */
	public boolean canLeave() {
		boolean leave = true;

		if (inEditMode && changeOccurred()) {
			//Show a warning if there is danger of unsaved changes
			int proceed = JOptionPane.showConfirmDialog(getRootPane(), WARNING_MESSAGE);
			//If user wants to proceed, do so
			//Otherwise stop the operation
			if (proceed == JOptionPane.YES_OPTION) {
				changeEditMode();
				leave = true;
			} else {
				leave = false;
			}
		} else if (inEditMode) {
			//Leave edit mode if user tries to leave but there were no changes
			changeEditMode();
		}

		return leave;
	}
	
	/**
	 * Toggles which card on the cardLayout Panel is visible, therefore 
	 * rendering the components expected for the user's mediaMode visible
	 * @param mediaMode MediaType currently being used in the program
	 */
	private void toggleMediaCards(MediaType mediaMode) {
    	CardLayout cl = (CardLayout) cardLayout.getLayout();
		if (mediaMode == MediaType.ANIME) {
	    	cl.show(cardLayout, "browseAnime");
		} else if (mediaMode == MediaType.MANGA) {
	    	cl.show(cardLayout, "browseManga");
		}
	}


	
	/**
	 * Retrieves following entry in sequence and reloads page data
	 */
	private void displayNext() {
		//Ensure it's okay to continue
		if (canLeave()) {
			//Handle the data change
			SortedMediaList list = Manager.getInstance().getList(); 
			int idx = list.indexOf(currentEntry) + 1;
			mainGUI.setTableSelected(idx);	
		} else {
			return;
		}

	}
	
	
	/**
	 * Retrieves previous entry in sequence and reloads page data
	 */
	private void displayPrev() {
		//Ensure it's okay to continue
		if (canLeave()) {
			//Handle the data change
			SortedMediaList list = Manager.getInstance().getList(); 
			int idx = list.indexOf(currentEntry) - 1;
			mainGUI.setTableSelected(idx);	
		} else {
			return;
		}
		
	}

	
	/**
	 * Uses the components in BrowseView to synthesize a new Anime, letting you know if something is wrong.
	 * @return Anime with updated information as a new Anime object
	 * @throws IllegalArgumentException if bad values, construction fails, or required data not provided by user
	 */
	private Anime makeNewAnime() {
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
		String notes = "";
		
		//Check fields are acceptable
		try {

			//Required fields
			title = txtFldTitleAnime.getText();
			if (title.isBlank()) {
				throw new IllegalArgumentException("Entry must have a title.");
			}
			if (txtFldYearAnime.getText().isBlank()) {
				throw new IllegalArgumentException("Entry must have a year.");
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
			if (!txtAreaNotesAnime.getText().isBlank()) {
				notes = txtAreaNotesAnime.getText();	
			}
			
			
			//Create the anime
			return new Anime(title, year, count, lan, type, fin, drop, director, studio, notes);
		} catch (Exception e) {

			if (e instanceof NumberFormatException) {
				throw new IllegalArgumentException("Cannot understand some numerical input.");	
			} else {
				throw new IllegalArgumentException(e.getMessage());
			}
		}
	}
	
	/**
	 * Uses the components in BrowseView to synthesize a new Manga, letting you know if something is wrong.
	 * @return Manga with updated information as a new Manga object
	 * @throws IllegalArgumentException if bad values, construction fails, or required data not provided by user
	 */
	private Manga makeNewManga() {
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
		String notes = "";
		
		//Check fields are acceptable
		try {

			//Required fields
			title = txtFldTitleManga.getText();
			if (title.isBlank()) {
				throw new IllegalArgumentException("Entry must have a title.");
			}
			if (txtFldYearManga.getText().isBlank()) {
				throw new IllegalArgumentException("Entry must have a year.");
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
				throw new IllegalArgumentException("Entry cannot be finished and dropped.");
			}
			if (!txtFldAuthorManga.getText().isBlank()) {
				author = txtFldAuthorManga.getText();	
			}
			if (!txtFldPublisherManga.getText().isBlank()) {
				publisher = txtFldPublisherManga.getText();	
			}
			if (!txtAreaNotesManga.getText().isBlank()) {
				notes = txtAreaNotesManga.getText();	
			}
			
			
			//Create the manga
			return new Manga(title, year, count, author, publisher, type, fin, drop, ongoing, notes);
		} catch (Exception e) {

			if (e instanceof NumberFormatException) {
				throw new IllegalArgumentException("Cannot understand some numerical input.");	
			} else {
				throw new IllegalArgumentException(e.getMessage());
			}
		}
		
	}
}
