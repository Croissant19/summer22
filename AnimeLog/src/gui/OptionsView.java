package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import manager.Manager;

import javax.swing.JRadioButton;
import javax.swing.colorchooser.AbstractColorChooserPanel;

import data.Media.MediaType;
import data.Preferences.ColorMethod;
import data.Preferences.SortFocus;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Defines the GUI components for changing program settings
 * @author Hunter Pruitt
 */
public class OptionsView extends JPanel {

	/** Notice on the preferences informing the user that they can be saved */
	private static final String NOTICE = "<html>Preferences may be set here and will be saved automatically along with other data in the program.</html>";
	
	/** Title for JOptionPane that appears when user wants to select a color */
	private static final String COLOR_DIALOG_TITLE = "Select A Color";
	
	/** Color Chooser Panel model with a grid of colors */
	private AbstractColorChooserPanel swatch;

	/** JColorChooser pointer */
	private JColorChooser colorChooser;
	
	private GUI mainGUI;
	private JRadioButton rdBtnAlphabet;
	private JRadioButton rdBtnNumeric;
	private JRadioButton rdBtnNoColor;
	private JRadioButton rdBtnColorFinDrop;
	private JRadioButton rdBtnColorSeriesSpecial;
	private JRadioButton rdBtnColorLanguage;
	private JButton btnColor1;
	private JButton btnColor2;
	private JPanel panel;
	
	
	/**
	 * Create the panel.
	 * @param gui pointer to main part of the GUI so that this card which is inside it can access it's public methods
	 */
	public OptionsView(GUI gui) {
		//Attach reference to main GUI class
		mainGUI = gui;

		setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 405, 319);
		add(panel);
		panel.setLayout(null);
		
		JPanel pnlFields = new JPanel();
		pnlFields.setBounds(0, 0, 405, 319);
		panel.add(pnlFields);
		pnlFields.setLayout(null);
		
		rdBtnAlphabet = new JRadioButton("Title");
		rdBtnAlphabet.setBounds(49, 83, 74, 23);
		pnlFields.add(rdBtnAlphabet);
		
		rdBtnNumeric = new JRadioButton("Year");
		rdBtnNumeric.setBounds(125, 83, 74, 23);
		pnlFields.add(rdBtnNumeric);
		
		JLabel lblSortBy = new JLabel("Sort by:");
		lblSortBy.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSortBy.setBounds(33, 68, 64, 14);
		pnlFields.add(lblSortBy);
		
		JLabel lblInstructions = new JLabel(NOTICE);
		lblInstructions.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInstructions.setBounds(33, 11, 350, 45);
		pnlFields.add(lblInstructions);
		
		JLabel lblColors = new JLabel("Colors:");
		lblColors.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblColors.setBounds(33, 117, 64, 14);
		pnlFields.add(lblColors);
		
		rdBtnNoColor = new JRadioButton("No highlights");
		rdBtnNoColor.setBounds(49, 132, 120, 23);
		pnlFields.add(rdBtnNoColor);
		
		rdBtnColorFinDrop = new JRadioButton("Finished and Dropped highlights");
		rdBtnColorFinDrop.setBounds(49, 158, 197, 23);
		pnlFields.add(rdBtnColorFinDrop);
		
		rdBtnColorSeriesSpecial = new JRadioButton("Series and Special highlights");
		rdBtnColorSeriesSpecial.setBounds(49, 186, 197, 23);
		pnlFields.add(rdBtnColorSeriesSpecial);
		
		rdBtnColorLanguage = new JRadioButton("Sub and Dub highlights");
		rdBtnColorLanguage.setBounds(49, 212, 197, 23);
		pnlFields.add(rdBtnColorLanguage);
		
		btnColor1 = new JButton("Color 1");
		btnColor1.setBounds(287, 132, 74, 23);
		btnColor1.setRequestFocusEnabled(false);
		pnlFields.add(btnColor1);
		
		btnColor2 = new JButton("Color 2");
		btnColor2.setBounds(287, 166, 74, 23);
		btnColor2.setRequestFocusEnabled(false);
		pnlFields.add(btnColor2);


		//Set swatch, the AbstractColorChooserPanel which will be used for selecting colors
		//Cant's set it as a final var as it doesn't seem to be defined anywhere I can access, so this could definitely be improved
		colorChooser = new JColorChooser();
		AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
		for (AbstractColorChooserPanel accp : panels) {
            if (accp.getDisplayName().equals("Swatches")) {
            	swatch = accp;
            }
        }

		
		createEvents();
		displayCurrentSelection();		
	}

	/**
	 * Creates events for components inside the card panel, so that fields can be edited/saved and interactables behave correctly
	 */
	private void createEvents() {		
		//Events for ensuring only one radio button of each type can be selected at once
		//Sort method
		rdBtnAlphabet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Toggle other JRadioButton
				if (rdBtnAlphabet.isSelected()) {
					rdBtnNumeric.setSelected(false);
				}
			
				//Update data
				applySelectedSortMethod();
				mainGUI.updateData(null);
			}
		});
		rdBtnNumeric.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Toggle other JRadioButton
				if (rdBtnNumeric.isSelected()) {
					rdBtnAlphabet.setSelected(false);
				}

				//Update data
				applySelectedSortMethod();
				mainGUI.updateData(null);
			}
		});


		//Color options
		rdBtnNoColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Toggle other JRadioButtons
				if (rdBtnNoColor.isSelected()) {
					rdBtnColorFinDrop.setSelected(false);
					rdBtnColorSeriesSpecial.setSelected(false);
					rdBtnColorLanguage.setSelected(false);
				}
				
				//Update data
				applySelectedColorMethod();
				mainGUI.updateData(null);
			}
		});
		rdBtnColorFinDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Toggle other JRadioButtons
				if (rdBtnColorFinDrop.isSelected()) {
					rdBtnNoColor.setSelected(false);
					rdBtnColorSeriesSpecial.setSelected(false);
					rdBtnColorLanguage.setSelected(false);
				}
			
				//Update data
				applySelectedColorMethod();
				mainGUI.updateData(null);
			}
		});
		rdBtnColorSeriesSpecial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Toggle other JRadioButtons
				if (rdBtnColorSeriesSpecial.isSelected()) {
					rdBtnNoColor.setSelected(false);
					rdBtnColorFinDrop.setSelected(false);
					rdBtnColorLanguage.setSelected(false);
				}

				//Update data
				applySelectedColorMethod();
				mainGUI.updateData(null);
			}
		});
		rdBtnColorLanguage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Toggle other JRadioButtons
				if (rdBtnColorLanguage.isSelected()) {
					rdBtnNoColor.setSelected(false);
					rdBtnColorFinDrop.setSelected(false);
					rdBtnColorSeriesSpecial.setSelected(false);
				}
			
				//Update data
				applySelectedColorMethod();
				mainGUI.updateData(null);
			}
		});

		
		//Color choosers
		btnColor1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Get old color
				Color oldColor;
				if (mainGUI.getMediaMode() == MediaType.ANIME) {
					oldColor = Manager.getInstance().getAnimePreferences().getColor1();
				} else {
					oldColor = Manager.getInstance().getMangaPreferences().getColor1();
				}
				//Apply changes
				btnColor1.setBackground(getColorDialog(oldColor));
				Manager.getInstance().setColor(mainGUI.getMediaMode(), "Color1", btnColor1.getBackground());
				mainGUI.updateData(null);
			}
		});
		btnColor2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Get old color
				Color oldColor;
				if (mainGUI.getMediaMode() == MediaType.ANIME) {
					oldColor = Manager.getInstance().getAnimePreferences().getColor2();
				} else {
					oldColor = Manager.getInstance().getMangaPreferences().getColor2();
				}
				//Apply changes
				btnColor2.setBackground(getColorDialog(oldColor));
				Manager.getInstance().setColor(mainGUI.getMediaMode(), "Color2", btnColor2.getBackground());
				mainGUI.updateData(null);
			}
		});


	}
	
	/**
	 * Shows a dialog to get a color from the user for display in the gui
	 * @param oldColor previous user color selection, in case of close or cancel
	 * @return Color selected by the user in the JColorChooser, the oldColor if nothing passed
	 */
	private Color getColorDialog(Color oldColor) {
		Color c = oldColor;
		int answer = JOptionPane.showConfirmDialog(getRootPane(), swatch, COLOR_DIALOG_TITLE, JOptionPane.OK_CANCEL_OPTION);

    	if (answer == JOptionPane.YES_OPTION) {
        	c = colorChooser.getColor();
    	}
    	return c;
	}
	
	/**
	 * Applies ColorMethod selection to data stored in the Manager.
	 * @throws IllegalArgumentException is no color button is selected
	 */
	private void applySelectedColorMethod() {
		ColorMethod colorBy;
		
		//Get color preference
		if (this.rdBtnNoColor.isSelected()) {
			colorBy = ColorMethod.NO_COLOR;
		} else if (rdBtnColorFinDrop.isSelected()) {
			colorBy = ColorMethod.FIN_DROP;
		} else if (rdBtnColorSeriesSpecial.isSelected()) {
			colorBy = ColorMethod.SERIES_SPECIAL;
		} else if (rdBtnColorLanguage.isSelected()) {
			colorBy = ColorMethod.SUB_DUB;
		} else {
			throw new IllegalArgumentException("You must select a color method preference.");
		}
		
		//Report selection to Manager
		Manager.getInstance().setColorMethod(mainGUI.getMediaMode(), colorBy);		
	}
	
	/**
	 * Applies SortFocus selection to data stored in the Manager.
	 * @throws IllegalArgumentException is no sorting button is selected
	 */
	private void applySelectedSortMethod() {
		SortFocus sortBy;
		
		//Get sortBy
		if (rdBtnAlphabet.isSelected()) {
			sortBy = SortFocus.ALPHABETICAL;
		} else if (rdBtnNumeric.isSelected()) {
			sortBy = SortFocus.NUMERICAL;
		} else {
			throw new IllegalArgumentException("You must select a sorting preference.");
		}

		//Report selection to Manager
		Manager.getInstance().setSortMethod(mainGUI.getMediaMode(), sortBy);
	}

	/**
	 * Indicates the current preferences by selecting the proper JRadioButtons
	 * @throws IllegalArgumentException if the user has somehow stored an invalid selection
	 */
	public void displayCurrentSelection() {
		SortFocus sortBy = Manager.getInstance().getAnimePreferences().getSortMethod();
		if (sortBy == SortFocus.ALPHABETICAL) {
			rdBtnAlphabet.setSelected(true);
			rdBtnNumeric.setSelected(false);
		} else if (sortBy == SortFocus.NUMERICAL) {
			rdBtnNumeric.setSelected(true);
			rdBtnAlphabet.setSelected(false);
		} else {
			throw new IllegalArgumentException("Error with discovering user preferences");
		}
		
		
		ColorMethod colorBy = Manager.getInstance().getAnimePreferences().getColorMethod();		
		if (colorBy == ColorMethod.NO_COLOR) {
			rdBtnNoColor.setSelected(true);
			rdBtnColorFinDrop.setSelected(false);
			rdBtnColorSeriesSpecial.setSelected(false);
			rdBtnColorLanguage.setSelected(false);
		} else if (colorBy == ColorMethod.FIN_DROP) {
			rdBtnNoColor.setSelected(false);
			rdBtnColorFinDrop.setSelected(true);
			rdBtnColorSeriesSpecial.setSelected(false);
			rdBtnColorLanguage.setSelected(false);
		} else if (colorBy == ColorMethod.SERIES_SPECIAL) {
			rdBtnNoColor.setSelected(false);
			rdBtnColorFinDrop.setSelected(false);
			rdBtnColorSeriesSpecial.setSelected(true);
			rdBtnColorLanguage.setSelected(false);
		} else if (colorBy == ColorMethod.SUB_DUB) {
			rdBtnNoColor.setSelected(false);
			rdBtnColorFinDrop.setSelected(false);
			rdBtnColorSeriesSpecial.setSelected(false);
			rdBtnColorLanguage.setSelected(true);
		} else {
			throw new IllegalArgumentException("Error with discovering user preferences");
		}

		rdBtnColorLanguage.setVisible(true);
		
		switch (mainGUI.getMediaMode()) {
		case ANIME: 
			btnColor1.setBackground(Manager.getInstance().getAnimePreferences().getColor1());
			btnColor2.setBackground(Manager.getInstance().getAnimePreferences().getColor2());
			break;
		case MANGA:
			btnColor1.setBackground(Manager.getInstance().getMangaPreferences().getColor1());
			btnColor2.setBackground(Manager.getInstance().getMangaPreferences().getColor2());

			//Hide language color options if current mode is Manga
			rdBtnColorLanguage.setVisible(false);
			break;
		}
		


	}

}