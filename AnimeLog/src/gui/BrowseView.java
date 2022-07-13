package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import anime.Anime;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
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

	private JPanel imgPanel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField notesField;
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

	/**
	 * Create the panel.
	 */
	public BrowseView() {
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
		textField_3.setBounds(119, 151, 172, 20);
		pnlFields.add(textField_3);
		
		rdBtnSub = new JRadioButton("Sub");
		rdBtnSub.setBounds(115, 78, 55, 23);
		pnlFields.add(rdBtnSub);
		
		rdBtnDub = new JRadioButton("Dub");
		rdBtnDub.setBounds(172, 78, 55, 23);
		pnlFields.add(rdBtnDub);
		
		rdBtnOther = new JRadioButton("Other/TBD");
		rdBtnOther.setBounds(229, 78, 77, 23);
		pnlFields.add(rdBtnOther);
		
		chckBxFinished = new JCheckBox("Finished");
		chckBxFinished.setBounds(115, 126, 70, 23);
		pnlFields.add(chckBxFinished);
		
		chckBxDropped = new JCheckBox("Dropped");
		chckBxDropped.setBounds(187, 126, 70, 23);
		pnlFields.add(chckBxDropped);
		
		rdBtnSeries = new JRadioButton("Series");
		rdBtnSeries.setBounds(115, 102, 70, 23);
		pnlFields.add(rdBtnSeries);
		
		rdBtnSpecial = new JRadioButton("Special");
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
		
		notesField = new JTextField();
		notesField.setBounds(10, 205, 274, 103);
		pnlFields.add(notesField);
		notesField.setColumns(10);
		
		JLabel lblNotes = new JLabel("Notes:");
		lblNotes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNotes.setBounds(10, 190, 70, 14);
		pnlFields.add(lblNotes);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(167, 341, 89, 23);
		add(btnEdit);

		
		createEvents();
	}

	private void createEvents() {
		imgPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				imagePopup();
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
	 * Fills the page with information about a selected anime
	 * @param a Anime to fill fields with
	 */
	public void loadData(Anime a) {
		
	}
	
	
	/**
	 * Handles process when user clicks on image in BrowseView
	 */
	private void imagePopup() {
		// TODO Auto-generated method stub
		//TODO: JFrame for image work
		JOptionPane.showMessageDialog(null, "");
	}
}
