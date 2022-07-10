package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JToolBar;
import javax.swing.JEditorPane;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {

	/** Data headers for the data table */
	private static final String[] COLUMN_NAMES= {"Year",
            "Title",
            "Latest episode seen"};
	
	
	private JPanel contentPane;
	private JTable table;
	private JTextField txtYourTxtHere;


	private JComboBox<String> fileOptions;

	//TODO: attribute logo
	//https://www.flaticon.com/premium-icon/anime_2314736?term=anime&related_id=2314736#
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor for the Graphical User Interface
	 */
	public GUI() {
		initComponents();
		createEvents();
	}
	
	/**
	 * Code for initializing components
	 */
	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/resources/icon_24.png")));
		setResizable(false);
		setTitle("Anime Log");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel imgPanel = new JPanel();
		
		JLabel lblNotes = new JLabel("Notes:");
		lblNotes.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JEditorPane txtNotes = new JEditorPane();
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		txtYourTxtHere = new JTextField();
		txtYourTxtHere.setText("your txt here");
		txtYourTxtHere.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtYourTxtHere.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 679, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtNotes, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(imgPanel, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblTitle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNotes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtYourTxtHere, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtYourTxtHere, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNotes)
									.addGap(8)
									.addComponent(txtNotes, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
								.addComponent(imgPanel, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		fileOptions = new JComboBox<String>();


		//Header and options for the fileOptions dropdown
		fileOptions.addItem("File");
		fileOptions.addItem("Load");
		fileOptions.addItem("Save");

		toolBar.add(fileOptions);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				NewAnimeView newEntry = new NewAnimeView();
				contentPane.setVisible(false);
				setContentPane(newEntry);
				newEntry.setVisible(true);
			}
		});
		toolBar.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		toolBar.add(btnEdit);
		
		JButton btnImg = new JButton("Image");
		toolBar.add(btnImg);
		
		JButton btnRemove = new JButton("Remove");
		toolBar.add(btnRemove);
		//TODO: add rowData, COLUMN_NAMES
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);

	}
	
	/**
	 * Code for creating events
	 */
	private void createEvents() {
		fileOptions.addItemListener(new ItemListener() {

			//For loading and saving files
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (fileOptions.getSelectedItem().equals("Load")) {
						//TODO: Save popup
						//TODO: Explore JFileChooser
						JOptionPane.showInputDialog(null, "FIXME");
						//JOptionPane.showInputDialog()

						fileOptions.setSelectedIndex(0);
						
					} else if (fileOptions.getSelectedItem().equals("Save")) {
						//TODO: load popup
						JOptionPane.showMessageDialog(null, "FIXME");
						fileOptions.setSelectedIndex(0);
					}
				}
			}

		});
	}	
}
