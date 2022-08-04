package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import data.Anime;
import data.Anime.Language;
import data.Anime.Type;
import manager.Manager;
import util.SortedAnimeList;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Defines the GUI components for viewing anime
 * @author Hunter Pruitt
 */
public class BrowseView extends JPanel {

	private static final String WARNING_MESSAGE = "You have unsaved changes. Are you sure you want to leave?";
	private GUI mainGUI;
	private JTextField txtFldTitle;
	private JTextField txtFldYear;
	private JTextField txtFldCount;
	private JTextField txtFldDirector;
	private JTextField txtFldStudio;
	private JTextArea txtAreaNotes;
	private JRadioButton rdBtnSub;
	private JRadioButton rdBtnDub;
	private JRadioButton rdBtnOther;
	private JRadioButton rdBtnSpecial;
	private JRadioButton rdBtnSeries;
	private JCheckBox chckBxFinished;
	private JCheckBox chckBxDropped;
	private JButton btnEdit;
	private JButton btnNext;
	private JButton btnPrevious;

	private boolean inEditMode = false;
	
	private Anime currentAnime;
	
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
		
		JPanel pnlFields = new JPanel();
		pnlFields.setBounds(10, 11, 405, 326);
		add(pnlFields);
		pnlFields.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle.setBounds(10, 8, 99, 14);
		pnlFields.add(lblTitle);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblYear.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYear.setBounds(10, 33, 99, 14);
		pnlFields.add(lblYear);
		
		JLabel lblCount = new JLabel("Number Watched:");
		lblCount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCount.setBounds(10, 57, 99, 14);
		pnlFields.add(lblCount);
		
		JLabel lblLanguage = new JLabel("Language:");
		lblLanguage.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLanguage.setBounds(10, 84, 99, 14);
		pnlFields.add(lblLanguage);
		
		JLabel lblType = new JLabel("Content Type:");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblType.setBounds(10, 112, 99, 14);
		pnlFields.add(lblType);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatus.setBounds(10, 140, 99, 14);
		pnlFields.add(lblStatus);
		
		JLabel lblDirector = new JLabel("Director:");
		lblDirector.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDirector.setBounds(10, 167, 99, 14);
		pnlFields.add(lblDirector);
		
		JLabel lblStudio = new JLabel("Studio:");
		lblStudio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudio.setBounds(10, 192, 99, 14);
		pnlFields.add(lblStudio);
		
		JLabel lblNotes = new JLabel("Notes:");
		lblNotes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNotes.setBounds(10, 200, 70, 14);
		pnlFields.add(lblNotes);

				
		txtFldTitle = new JTextField();
		txtFldTitle.setEditable(false);
		txtFldTitle.setBounds(119, 5, 224, 20);
		txtFldTitle.setColumns(10);
		pnlFields.add(txtFldTitle);

		
		txtFldYear = new JTextField();
		txtFldYear.setEditable(false);
		txtFldYear.setColumns(10);
		txtFldYear.setBounds(119, 30, 55, 20);
		pnlFields.add(txtFldYear);
		
		txtFldCount = new JTextField();
		txtFldCount.setEditable(false);
		txtFldCount.setColumns(10);
		txtFldCount.setBounds(119, 55, 55, 20);
		pnlFields.add(txtFldCount);
		
		txtFldDirector = new JTextField();
		txtFldDirector.setEditable(false);
		txtFldDirector.setColumns(10);
		txtFldDirector.setBounds(119, 164, 172, 20);
		pnlFields.add(txtFldDirector);
		
		txtFldStudio = new JTextField();
		txtFldStudio.setEditable(false);
		txtFldStudio.setColumns(10);
		txtFldStudio.setBounds(119, 189, 172, 20);
		pnlFields.add(txtFldStudio);
		
		txtAreaNotes = new JTextArea();
		txtAreaNotes.setEditable(false);
		txtAreaNotes.setBounds(10, 219, 385, 96);
		pnlFields.add(txtAreaNotes);
		txtAreaNotes.setColumns(10);
		txtAreaNotes.setLineWrap(true);
		

		rdBtnSub = new JRadioButton("Sub");
		rdBtnSub.setEnabled(false);
		rdBtnSub.setBounds(115, 80, 55, 23);
		pnlFields.add(rdBtnSub);
		
		rdBtnDub = new JRadioButton("Dub");
		rdBtnDub.setEnabled(false);
		rdBtnDub.setBounds(172, 80, 55, 23);
		pnlFields.add(rdBtnDub);
		
		rdBtnOther = new JRadioButton("Other/TBD");
		rdBtnOther.setEnabled(false);
		rdBtnOther.setBounds(229, 80, 77, 23);
		pnlFields.add(rdBtnOther);
		
		chckBxFinished = new JCheckBox("Finished");
		chckBxFinished.setEnabled(false);
		chckBxFinished.setBounds(115, 136, 70, 23);
		pnlFields.add(chckBxFinished);
		
		chckBxDropped = new JCheckBox("Dropped");
		chckBxDropped.setEnabled(false);
		chckBxDropped.setBounds(187, 136, 70, 23);
		pnlFields.add(chckBxDropped);
		
		rdBtnSeries = new JRadioButton("Series");
		rdBtnSeries.setEnabled(false);
		rdBtnSeries.setBounds(115, 108, 70, 23);
		pnlFields.add(rdBtnSeries);
		
		rdBtnSpecial = new JRadioButton("Special");
		rdBtnSpecial.setEnabled(false);
		rdBtnSpecial.setBounds(182, 108, 70, 23);
		pnlFields.add(rdBtnSpecial);
		
		createEvents();
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
								//Try to construct new Anime
								Anime updatedAnime = makeNewAnime();
	
								//Update and regenerate data
								Manager manager = Manager.getInstance();
								manager.removeAnime(manager.getAnimeList().indexOf(currentAnime));
								manager.addAnime(updatedAnime);
								mainGUI.updateData();
								setCurrentAnime(updatedAnime);
							}
						} catch (Exception e1) {
							//Show warning dialog if necessary
							JOptionPane.showMessageDialog(getRootPane(), e1.getMessage());
							return;
						}
					
				}
				//Regardless of mode, if make it this far, change to other mode
				changeMode();
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
	 * Sets the current anime to an anime on the list or null if changing view from browse view
	 * @param a anime to load
	 */
	public void setCurrentAnime(Anime a) {
		this.currentAnime = a;
		if (a != null) {
			loadData();
		}

	}
	
	/**
	 * Returns the current Anime. Used by the main gui to reselect proper table rows if user tries to leave unsaved changes
	 * @return anime which is currently displayed in this view
	 */
	public Anime getCurrentAnime() {
		return currentAnime;
	}
	
	
	/**
	 * Indicates if a change occurred while the user was in edit mode
	 * If an IllegalArgumentException is thrown (when a type or language isn't selected) false is returned.
	 * @return boolean indicator if a value has changed since enabling the field components
	 */
	private boolean changeOccurred() {
		//Transform int fields into Strings comparable to what is in the text fields
		String oldYear = "" + currentAnime.getYear();
		String oldCount = "" + currentAnime.getCount();
		//Return statements to compare each field until either a contrast is found or all match
		try {
			return !currentAnime.getTitle().equals(txtFldTitle.getText()) 
					|| !oldYear.equals(txtFldYear.getText())
					|| !oldCount.equals(txtFldCount.getText())
					|| !currentAnime.getLanguage().equals(getSelectedLanguage())
					|| !currentAnime.getType().equals(getSelectedType())
					|| currentAnime.isFinished() != chckBxFinished.isSelected()
					|| currentAnime.isDropped() != chckBxDropped.isSelected()
					|| !currentAnime.getDirector().equals(txtFldDirector.getText())
					|| !currentAnime.getStudio().equals(txtFldStudio.getText())
					|| !currentAnime.getNotes().equals(txtAreaNotes.getText());
			
		} catch (IllegalArgumentException e) {
			//If something is wrong (likely the radio buttons) where things were previously right,
			//it is another indicator that a change has occurred
			return true;
		}

	}

	/**
	 * Indicates which type classification the user selected among the relevant JRadioButtons
	 * @return String indicator as to what type is currently selected
	 * @throws IllegalArgumentException if no type is indicated
	 */
	private String getSelectedType() {
		String type;
		if (rdBtnSeries.isSelected()) {
			type = Type.SERIES.formattedName;
		} else if (rdBtnSpecial.isSelected()) {
			type = Type.SPECIAL.formattedName;
		} else {
			//In case neither is selected
			throw new IllegalArgumentException("Type not indicated.");
		}
		return type;
	}
	
	/**
	 * Selects the correct JRadioButton depending on what type is passed
	 * @param type String indicator of an anime type classification
	 * @throws IllegalArgumentException if type is not an accepted Type
	 */
	private void setSelectedType(String type) {
		if (type.equals(Type.SERIES.formattedName)) {
			rdBtnSeries.setSelected(true);
		} else if (type.equals(Type.SPECIAL.formattedName)) {
			rdBtnSpecial.setSelected(true);
		} else {
			throw new IllegalArgumentException("Invalid type");
		}
	}


	/**
	 * Selects the correct JRadioButton depending on what language is passed
	 * @param lan String indicator of an anime language classification
	 * @throws IllegalArgumentException if lan is not an accepted Language */
	private void setSelectedLanguage (String lan) {
		if (lan.equals(Language.SUB.formattedName)) {
			rdBtnSub.setSelected(true);
		} else if (lan.equals(Language.DUB.formattedName)) {
			rdBtnDub.setSelected(true);
		} else if (lan.equals(Language.OTHER.formattedName)) {
			rdBtnOther.setSelected(true);
		} else {
			throw new IllegalArgumentException("Invalid language");
		}
	}

	
	/**
	 * Indicates which language the user selected among the relevant JRadioButtons
	 * @return String indicator as to what language is currently selected
	 * @throws IllegalArgumentException if no language is indicated
	 */
	private String getSelectedLanguage() {
		String lan;
		
		if (rdBtnSub.isSelected()) {
			lan = Language.SUB.formattedName;
		} else if (rdBtnDub.isSelected()) {
			lan = Language.DUB.formattedName;
		} else if (rdBtnOther.isSelected()) {
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
	private void changeMode() {

		//Toggle booleans for editable-ness
		txtFldTitle.setEditable(!txtFldTitle.isEditable());
		txtFldYear.setEditable(!txtFldYear.isEditable());
		txtFldCount.setEditable(!txtFldCount.isEditable());
		txtFldDirector.setEditable(!txtFldDirector.isEditable());
		txtFldStudio.setEditable(!txtFldStudio.isEditable());
		txtAreaNotes.setEditable(!txtAreaNotes.isEditable());
		rdBtnSub.setEnabled(!rdBtnSub.isEnabled());
		rdBtnDub.setEnabled(!rdBtnDub.isEnabled());
		rdBtnOther.setEnabled(!rdBtnOther.isEnabled());
		rdBtnSpecial.setEnabled(!rdBtnSpecial.isEnabled());
		rdBtnSeries.setEnabled(!rdBtnSeries.isEnabled());
		chckBxFinished.setEnabled(!chckBxFinished.isEnabled());
		chckBxDropped.setEnabled(!chckBxDropped.isEnabled());

		this.inEditMode = !inEditMode;

		//Change the text on the button
		if (inEditMode) {
			btnEdit.setText("Save");
		} else {
			btnEdit.setText("Edit");
		}
	}

	/**
	 * Fills the page with information about a selected anime based on currentAnime field
	 */
	private void loadData() {
		//Empty current data first to be safe
		clearFields();
		
		//Load anime data
		txtFldTitle.setText(currentAnime.getTitle());
		txtFldYear.setText("" + currentAnime.getYear());
		txtFldCount.setText("" + currentAnime.getCount());
		setSelectedType(currentAnime.getType());
		setSelectedLanguage(currentAnime.getLanguage());
		if (currentAnime.isFinished()) {
			chckBxFinished.setSelected(true);
		} else if (currentAnime.isDropped()) {
			chckBxDropped.setSelected(true);
		}
		
		
		txtFldDirector.setText(currentAnime.getDirector());
		txtFldStudio.setText(currentAnime.getStudio());
		txtAreaNotes.setText(currentAnime.getNotes());
		
		//Reset the caret position to show the beginning of text fields
		txtFldTitle.setCaretPosition(0);
		txtFldDirector.setCaretPosition(0);
		txtFldStudio.setCaretPosition(0);
		
		//Disable next and/or previous buttons if such anime do not exist

		SortedAnimeList list = Manager.getInstance().getAnimeList(); 
		int currentIdx = list.indexOf(currentAnime);

		btnNext.setEnabled(currentIdx < list.size() - 1);
		btnPrevious.setEnabled(currentIdx > 0);

	}

	
	/**
	 * Resets the fields to blank after an Anime is successfully added
	 */
	private void clearFields() {
		txtFldTitle.setText("");
		txtFldYear.setText("");
		txtFldCount.setText("");
		txtFldDirector.setText("");
		txtFldStudio.setText("");
		rdBtnSub.setSelected(false);
		rdBtnDub.setSelected(false);
		rdBtnOther.setSelected(false);
		rdBtnSeries.setSelected(false);
		rdBtnSpecial.setSelected(false);
		chckBxFinished.setSelected(false);
		chckBxDropped.setSelected(false);
		txtAreaNotes.setText("");
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
				changeMode();
				leave = true;
			} else {
				leave = false;
			}
		} else if (inEditMode) {
			//Leave edit mode if user tries to leave but there were no changes
			changeMode();
		}

		return leave;
	}

	
	/**
	 * Retrieves following anime in sequence and reloads page data
	 */
	private void displayNext() {
		//Ensure it's okay to continue
		if (canLeave()) {
			//Handle the data change
			SortedAnimeList list = Manager.getInstance().getAnimeList(); 
			int idx = list.indexOf(currentAnime) + 1;
			mainGUI.setTableSelected(idx);	
		} else {
			return;
		}

	}
	
	
	/**
	 * Retrieves previous anime in sequence and reloads page data
	 */
	private void displayPrev() {
		//Ensure it's okay to continue
		if (canLeave()) {
			//Handle the data change
			SortedAnimeList list = Manager.getInstance().getAnimeList(); 
			int idx = list.indexOf(currentAnime) - 1;
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
			title = txtFldTitle.getText();
			if (title.isBlank()) {
				throw new IllegalArgumentException("Entry must have a title.");
			}
			if (txtFldYear.getText().isBlank()) {
				throw new IllegalArgumentException("Entry must have a year.");
			}
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
			if (!txtFldStudio.getText().isBlank()) {
				studio = txtFldStudio.getText();	
			}
			if (!txtAreaNotes.getText().isBlank()) {
				notes = txtAreaNotes.getText();	
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

}
