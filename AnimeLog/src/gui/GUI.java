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
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.UIManager;
import java.awt.Dimension;

public class GUI extends JFrame {

	/** Data headers for the data table */
	private static final String[] COLUMN_NAMES= {"Year",
            "Title",
            "Latest episode seen"};
	
	
	private JPanel contentPane;
	private JTable table;
	private JPanel cardPanel;


	private JComboBox<String> fileOptions;
	private JTextField textField;

	private JButton btnHome;
	private JButton btnAdd;
	private JButton btnEdit;

	//TODO: attribute logo
	//https://www.flaticon.com/premium-icon/anime_2314736?term=anime&related_id=2314736#
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
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
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		cardPanel = new JPanel();
		cardPanel.setMaximumSize(new Dimension(450, 300));

		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 679, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cardPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(scrollPane, 0, 0, Short.MAX_VALUE)
						.addComponent(cardPanel, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		cardPanel.setLayout(new CardLayout(0, 0));
		
		//Create default view
		
		
		
		JPanel homeView = new HomeView();
		cardPanel.add(homeView, "homeView");
		JPanel addView = new NewAnimeView();
		cardPanel.add(addView, "addView");
		JPanel browseView = new BrowseView();
		cardPanel.add(browseView, "browseView");

		fileOptions = new JComboBox<String>();


		//Header and options for the fileOptions dropdown
		fileOptions.addItem("File");
		fileOptions.addItem("Load");
		fileOptions.addItem("Save");

		toolBar.add(fileOptions);
		
		btnHome = new JButton("Home");
		toolBar.add(btnHome);
		
		btnAdd = new JButton("Add");
		toolBar.add(btnAdd);
		
		btnEdit = new JButton("Edit");
		toolBar.add(btnEdit);
		
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
		//File import and export options
		fileOptions.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (fileOptions.getSelectedItem().equals("Load")) {
						//TODO: Explore JFileChooser
						JOptionPane.showInputDialog(null, "FIXME");

						fileOptions.setSelectedIndex(0);
						
					} else if (fileOptions.getSelectedItem().equals("Save")) {
						//TODO: load popup
						JOptionPane.showMessageDialog(null, "FIXME");
						fileOptions.setSelectedIndex(0);
					}
				}
			}
		});

		//Add new Anime button on toolbar
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)cardPanel.getLayout();
				cl.show(cardPanel, "homeView");

			}
		});

		
		//Add new Anime button on toolbar
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)cardPanel.getLayout();
				cl.show(cardPanel, "addView");

			}
		});

		//Edit button for editing the selected Anime
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)cardPanel.getLayout();
				cl.show(cardPanel, "browseView");
			}
		});

	}	
}
