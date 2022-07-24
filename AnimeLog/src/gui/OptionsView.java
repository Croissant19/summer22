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

	}



}