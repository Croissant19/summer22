package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import anime.Anime;
import anime.Anime.Language;
import anime.Anime.Type;
import manager.Manager;
import util.SortedList;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * Defines the GUI components for changing program settings
 * @author Hunter Pruitt
 */
public class OptionsView extends JPanel {

	private static final String NOTICE = "<html>Preferences may be set and will be saved automatically along with other data in the program.</html>";
	
	private GUI mainGUI;
	private JButton btnApply;
	
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
		
		JRadioButton rdbtnAlphabet = new JRadioButton("Title");
		rdbtnAlphabet.setBounds(49, 83, 74, 23);
		pnlFields.add(rdbtnAlphabet);
		
		JRadioButton rdbtnYear = new JRadioButton("Year");
		rdbtnYear.setBounds(125, 83, 74, 23);
		pnlFields.add(rdbtnYear);
		
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
		
		JRadioButton rdbtnNoColors = new JRadioButton("No highlights");
		rdbtnNoColors.setBounds(49, 132, 120, 23);
		pnlFields.add(rdbtnNoColors);
		
		JRadioButton rdbtnColorFinDrop = new JRadioButton("Finished and Dropped highlights");
		rdbtnColorFinDrop.setBounds(49, 158, 197, 23);
		pnlFields.add(rdbtnColorFinDrop);
		
		JRadioButton rdbtnColorSeriesSpecial = new JRadioButton("Series and Special highlights");
		rdbtnColorSeriesSpecial.setBounds(49, 186, 197, 23);
		pnlFields.add(rdbtnColorSeriesSpecial);
		
		JRadioButton rdbtnColorLanguage = new JRadioButton("Sub or Dub highlights");
		rdbtnColorLanguage.setBounds(49, 212, 197, 23);
		pnlFields.add(rdbtnColorLanguage);
		
		JButton btnColor1 = new JButton("Color 1");
		btnColor1.setBounds(287, 132, 74, 23);
		pnlFields.add(btnColor1);
		
		JButton btnColor2 = new JButton("Color 2");
		btnColor2.setBounds(287, 166, 74, 23);
		pnlFields.add(btnColor2);
		
		
		btnApply = new JButton("Apply");
		btnApply.setBounds(167, 341, 89, 23);
		add(btnApply);

		
		createEvents();
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


	}
	
	
	private void applyChanges() {
		//TODO:
		
	}
}