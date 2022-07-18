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
import util.SortedList;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Defines the GUI components for viewing anime
 * @author Hunter Pruitt
 */
public class BrowseView extends JPanel {

	private static final String WARNING_MESSAGE = "You have unsaved changes. Are you sure you want to leave?";
	private GUI mainGUI;
	private JPanel imgPanel;
	private JTextField txtFldTitle;
	private JTextField txtFldYear;
	private JTextField txtFldCount;
	private JTextField txtFldDirector;
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
		
		btnPrevious = new JButton("Previous");
		btnPrevious.setBounds(39, 341, 89, 23);
		add(btnPrevious);
		
		JPanel pnlFields = new JPanel();
		pnlFields.setBounds(10, 11, 405, 319);
		add(pnlFields);
		pnlFields.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle.setBounds(10, 10, 99, 14);
		pnlFields.add(lblTitle);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 11));
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
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 11));
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
		
		txtFldTitle = new JTextField();
		txtFldTitle.setEditable(false);
		txtFldTitle.setBounds(119, 7, 224, 20);
		pnlFields.add(txtFldTitle);
		txtFldTitle.setColumns(10);
		
		txtFldYear = new JTextField();
		txtFldYear.setEditable(false);
		txtFldYear.setColumns(10);
		txtFldYear.setBounds(119, 31, 55, 20);
		pnlFields.add(txtFldYear);
		
		txtFldCount = new JTextField();
		txtFldCount.setEditable(false);
		txtFldCount.setColumns(10);
		txtFldCount.setBounds(119, 55, 55, 20);
		pnlFields.add(txtFldCount);
		
		txtFldDirector = new JTextField();
		txtFldDirector.setEditable(false);
		txtFldDirector.setColumns(10);
		txtFldDirector.setBounds(119, 151, 172, 20);
		pnlFields.add(txtFldDirector);
		
		rdBtnSub = new JRadioButton("Sub");
		rdBtnSub.setEnabled(false);
		rdBtnSub.setBounds(115, 78, 55, 23);
		pnlFields.add(rdBtnSub);
		
		rdBtnDub = new JRadioButton("Dub");
		rdBtnDub.setEnabled(false);
		rdBtnDub.setBounds(172, 78, 55, 23);
		pnlFields.add(rdBtnDub);
		
		rdBtnOther = new JRadioButton("Other/TBD");
		rdBtnOther.setEnabled(false);
		rdBtnOther.setBounds(229, 78, 77, 23);
		pnlFields.add(rdBtnOther);
		
		chckBxFinished = new JCheckBox("Finished");
		chckBxFinished.setEnabled(false);
		chckBxFinished.setBounds(115, 126, 70, 23);
		pnlFields.add(chckBxFinished);
		
		chckBxDropped = new JCheckBox("Dropped");
		chckBxDropped.setEnabled(false);
		chckBxDropped.setBounds(187, 126, 70, 23);
		pnlFields.add(chckBxDropped);
		
		rdBtnSeries = new JRadioButton("Series");
		rdBtnSeries.setEnabled(false);
		rdBtnSeries.setBounds(115, 102, 70, 23);
		pnlFields.add(rdBtnSeries);
		
		rdBtnSpecial = new JRadioButton("Special");
		rdBtnSpecial.setEnabled(false);
		rdBtnSpecial.setBounds(189, 102, 70, 23);
		pnlFields.add(rdBtnSpecial);
		
		imgPanel = new JPanel();
		imgPanel.setBounds(296, 176, 99, 132);
		pnlFields.add(imgPanel);
		imgPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));


		
		JLabel lblIcon = new JLabel("");
		//TODO: attribute image icon from
		//flaticon.com/premium-icon/new_4131729?term=add%20image&page=1&position=52&page=1&position=52&related_id=4131729&origin=style#
//		lblIcon.setIcon(new ImageIcon(BrowseView.class.getResource("/resources/addImgIcon_128.png")));
		lblIcon.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resources/addImgPoster.png")).getImage().getScaledInstance(imgPanel.getWidth(), imgPanel.getHeight(), Image.SCALE_SMOOTH)));
		imgPanel.add(lblIcon);
		
		txtAreaNotes = new JTextArea();
		txtAreaNotes.setEditable(false);
		txtAreaNotes.setBounds(10, 205, 274, 103);
		pnlFields.add(txtAreaNotes);
		txtAreaNotes.setColumns(10);
		
		JLabel lblNotes = new JLabel("Notes:");
		lblNotes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNotes.setBounds(10, 190, 70, 14);
		pnlFields.add(lblNotes);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(167, 341, 89, 23);
		add(btnEdit);

		
		createEvents();
	}

	/**
	 * Creates events for components inside the card panel, so that fields can be edited/saved and interactables behave correctly
	 */
	private void createEvents() {
		
		//TODO: responds to right and middle clicks too, not good...
		imgPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				imagePopup();
			}
		});

		
		//Events pertaining to large buttons, edit/save, next, previous
		//Edit/Save button
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Manage inputs in edit mode
				if (inEditMode) {
						try {				
							if (changeOccurred()) {	
								//Try to construct new Anime
								//TODO: probably should combine makeNeAnime from here and NewAnimeView into one method...!
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
							//TODO: test that dialog is centered
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
	 * 
	 * @param a anime to load
	 * @throws NullPointerException is passed a null Anime
	 */
	public void setCurrentAnime(Anime a) {
		if (a == null) {
			throw new NullPointerException("Somehow selected null anime");
		}
		this.currentAnime = a;
		loadData();
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
		//TODO: handle when mandatory fields blank
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
		//TODO: next and previous keep text boxes editable, save prompt?
		//TODO: handle disable buttons if last/first/only entry
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
		txtAreaNotes.setText(currentAnime.getNotes());

		
		//Disable next and/or previous buttons if such anime do not exist

		SortedList<Anime> list = Manager.getInstance().getAnimeList(); 
		int currentIdx = list.indexOf(currentAnime);

		btnNext.setEnabled(currentIdx < list.size() - 1);
		btnPrevious.setEnabled(currentIdx > 0);

	}

	/**
	 * Resets the fields to blank after an Anime is successfully added
	 */
	private void clearFields() {
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
		txtAreaNotes.setText("");
	}

	/**
	 * Retrieves following anime in sequence and reloads page data
	 */
	private void displayNext() {
		//Ensure it's okay to continue

		if (inEditMode && changeOccurred()) {
			//Show a warning if there is danger of unsaved changes
			//TODO: test that dialog is centered
			int proceed = JOptionPane.showConfirmDialog(getRootPane(), WARNING_MESSAGE);
			//If user wants to proceed, do so
			//Otherwise stop the operation
			if (proceed == JOptionPane.YES_OPTION) {
				changeMode();				
			} else {
				return;
			}
		}
		
		//Handle the data change
		SortedList<Anime> list = Manager.getInstance().getAnimeList(); 
		int idx = list.indexOf(currentAnime) + 1;
		mainGUI.setTableSelected(idx);
		}
	
	/**
	 * Retrieves previous anime in sequence and reloads page data
	 */
	private void displayPrev() {
		//Ensure it's okay to continue
		if (inEditMode && changeOccurred()) {
			//Show a warning if there is danger of unsaved changes
			//TODO: test that dialog is centered
			int proceed = JOptionPane.showConfirmDialog(getRootPane(), WARNING_MESSAGE);
			//If user wants to proceed, do so
			//Otherwise stop the operation
			if (proceed == JOptionPane.YES_OPTION) {
				changeMode();				
			} else {
				return;
			}
		}
		
		//Handle the data change
		SortedList<Anime> list = Manager.getInstance().getAnimeList(); 
		int idx = list.indexOf(currentAnime) - 1;
		mainGUI.setTableSelected(idx);
	}

	
	/**
	 * Handles process when user clicks on image in BrowseView
	 */
	private void imagePopup() {
		// TODO Auto-generated method stub
		//TODO: JFrame for image work
		//TODO: test that dialog is centered
		JOptionPane.showMessageDialog(getRootPane(), "");
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
		String notes = "";
		
		//Check fields are acceptable
		try {

			//Required fields
			title = txtFldTitle.getText();
			if (title.isBlank()) {
				throw new IllegalArgumentException("Title not indicated.");
			}
			if (txtFldYear.getText().isBlank()) {
				throw new IllegalArgumentException("Year not indicated.");
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
			if (!txtAreaNotes.getText().isBlank()) {
				notes = txtAreaNotes.getText();	
			}
			
			
			//Create the anime
			return new Anime(title, year, count, lan, type, fin, drop, director, notes);
		} catch (Exception e) {
			
			//TODO: ensure correct
			if (e instanceof NumberFormatException) {
				throw new IllegalArgumentException("Cannot understand some numerical input.");	
			} else {
				throw new IllegalArgumentException(e.getMessage());
			}
		}
	}



}
