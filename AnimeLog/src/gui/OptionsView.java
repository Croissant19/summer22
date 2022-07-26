package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import manager.Manager;
import manager.Preferences.SortFocus;
import util.SortedAnimeList;

import javax.swing.JRadioButton;
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
	
	private GUI mainGUI;
	private JButton btnApply;
	private JRadioButton rdBtnAlphabet;
	private JRadioButton rdBtnNumeric;
	private JRadioButton rdBtnNoColors;
	private JRadioButton rdBtnColorFinDrop;
	private JRadioButton rdBtnColorSeriesSpecial;
	private JRadioButton rdBtnColorLanguage;
	private JButton btnColor2;
	private JButton btnColor1;
	
	//TODO: in anime io have setting data as an optional preceding delimiter
	
	/**
	 * Create the panel.
	 * @param gui pointer to main part of the GUI so that this card which is inside it can access it's public methods
	 */
	public OptionsView(GUI gui) {
		//Attach reference to main GUI class
		mainGUI = gui;

		setLayout(null);
		
		JPanel pnlFields = new JPanel();
		pnlFields.setBounds(10, 11, 405, 319);
		add(pnlFields);
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
		
		rdBtnNoColors = new JRadioButton("No highlights");
		rdBtnNoColors.setBounds(49, 132, 120, 23);
		pnlFields.add(rdBtnNoColors);
		
		rdBtnColorFinDrop = new JRadioButton("Finished and Dropped highlights");
		rdBtnColorFinDrop.setBounds(49, 158, 197, 23);
		pnlFields.add(rdBtnColorFinDrop);
		
		rdBtnColorSeriesSpecial = new JRadioButton("Series and Special highlights");
		rdBtnColorSeriesSpecial.setBounds(49, 186, 197, 23);
		pnlFields.add(rdBtnColorSeriesSpecial);
		
		rdBtnColorLanguage = new JRadioButton("Sub or Dub highlights");
		rdBtnColorLanguage.setBounds(49, 212, 197, 23);
		pnlFields.add(rdBtnColorLanguage);
		
		//TODO: explore JColorChooser
		btnColor1 = new JButton("Color 1");
		btnColor1.setBounds(287, 132, 74, 23);
		pnlFields.add(btnColor1);
		
		btnColor2 = new JButton("Color 2");
		btnColor2.setBounds(287, 166, 74, 23);
		pnlFields.add(btnColor2);
		
		
		btnApply = new JButton("Apply");
		btnApply.setBounds(167, 341, 89, 23);
		add(btnApply);

		
		createEvents();
		displayCurrentSelection();		
	}

	/**
	 * Creates events for components inside the card panel, so that fields can be edited/saved and interactables behave correctly
	 */
	private void createEvents() {		
		//Events pertaining to large buttons, edit/save, next, previous
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				applyChanges();
			}
		});

		//Events for ensuring only one radio button of each type can be selected at once
		//Sort method
		rdBtnAlphabet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtnAlphabet.isSelected()) {
					rdBtnNumeric.setSelected(false);
				}
			
			}
		});
		rdBtnNumeric.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtnNumeric.isSelected()) {
					rdBtnAlphabet.setSelected(false);
				}
			
			}
		});


		//Color options
		rdBtnNoColors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtnNoColors.isSelected()) {
					rdBtnColorFinDrop.setSelected(false);
					rdBtnColorSeriesSpecial.setSelected(false);
					rdBtnColorLanguage.setSelected(false);
				}			
			}
		});
		rdBtnColorFinDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtnColorFinDrop.isSelected()) {
					rdBtnNoColors.setSelected(false);
					rdBtnColorSeriesSpecial.setSelected(false);
					rdBtnColorLanguage.setSelected(false);
				}
			
			}
		});
		rdBtnColorSeriesSpecial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtnColorSeriesSpecial.isSelected()) {
					rdBtnNoColors.setSelected(false);
					rdBtnColorFinDrop.setSelected(false);
					rdBtnColorLanguage.setSelected(false);
				}
			
			}
		});
		rdBtnColorLanguage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtnColorLanguage.isSelected()) {
					rdBtnNoColors.setSelected(false);
					rdBtnColorFinDrop.setSelected(false);
					rdBtnColorSeriesSpecial.setSelected(false);
				}
			
			}
		});

		
		//Color choosers
		
		
		btnColor1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnColor2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});


	}
	
//	/*@throws IllegalArgumentException is no sorting button or color button is selected
	private void applyChanges() {
		String sortBy;
		//Get sortBy
		if (rdBtnAlphabet.isSelected()) {
			sortBy = SortFocus.ALPHABETICAL.formattedName;
		} else if (rdBtnNumeric.isSelected()) {
			sortBy = SortFocus.NUMERICAL.formattedName;
		} else {
			throw new IllegalArgumentException("You must select a sorting preference.");
		}
		
		//TODO:
		//Get color preference
		//TODO: fix bug where changing from browse view to options and apply button causes to switch to browse view
		//Report selection to Manager
		Manager.getInstance().setSortMethod(sortBy);
//		Manager.getInstance().setColorSettings(colorSettings);
		mainGUI.updateData();
	}

	/**
	 * Indicates the current preferences by selecting the proper JRadioButtons
	 * @throws IllegalArgumentException if the user has somehow stored an invalid selection
	 */
	public void displayCurrentSelection() {
		SortFocus sortBy = Manager.getInstance().getSortMethod();
		if (sortBy == SortFocus.ALPHABETICAL) {
			rdBtnAlphabet.setSelected(true);
			rdBtnNumeric.setSelected(false);
		} else if (sortBy == SortFocus.NUMERICAL) {
			rdBtnNumeric.setSelected(true);
			rdBtnAlphabet.setSelected(false);
		} else {
			throw new IllegalArgumentException("Error with discovering user preferences");
		}
		
		//TODO: color stuff
		
	}

}