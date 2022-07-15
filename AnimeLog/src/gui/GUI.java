package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import manager.Manager;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JFileChooser;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.awt.CardLayout;
import javax.swing.UIManager;
import java.awt.Dimension;

public class GUI extends JFrame {

	/** Data headers for the data table */
	private static final String[] COLUMN_NAMES= {"Year",
            "Title",
            "Count"};
	
	
	private JPanel contentPane;
	private JTable table;
	private JPanel cardPanel;


	private JComboBox<String> fileOptions;

	private JButton btnHome;
	private JButton btnAdd;
	private JButton btnEdit;
	
	private HomeView homeView = new HomeView();
	private BrowseView browseView = new BrowseView();
	private NewAnimeView newAnimeView = new NewAnimeView();


	private JScrollPane scrollPane;
	
	
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
		//TODO: remove test procedure
		testStartUp();
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
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		
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
		
		
		//Create cards
		cardPanel.setLayout(new CardLayout(0, 0));
				
		cardPanel.add(homeView, "homeView");
		cardPanel.add(newAnimeView, "addView");
		cardPanel.add(browseView, "browseView");

		fileOptions = new JComboBox<String>();


		//Header and options for the fileOptions dropdown
		fileOptions.addItem("File");
		fileOptions.addItem("Load");
		fileOptions.addItem("Save");

		toolBar.add(fileOptions);
		
		btnHome = new JButton("Home");
		btnHome.setRequestFocusEnabled(false);
		toolBar.add(btnHome);
		
		btnAdd = new JButton("Add");
		btnAdd.setRequestFocusEnabled(false);
		toolBar.add(btnAdd);
		
		btnEdit = new JButton("Edit");
		btnEdit.setRequestFocusEnabled(false);
		toolBar.add(btnEdit);

		//Disable home button to indicate that is where you start
		toggleToolbarButtons(btnHome);
		//TODO: fix auto selection/ highlight when a button is disabled

		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setRequestFocusEnabled(false);
		toolBar.add(btnRemove);

		//Declare table model and override isCellEditable so that no cells are editable
		table = new JTable(new DefaultTableModel(null, COLUMN_NAMES) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		});
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
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
					String filename;
					if (fileOptions.getSelectedItem().equals("Load")) {
						//Get filename and watch for errors
						try {
							filename = getFilename(true);	
							Manager.getInstance().processFile(filename);
							updateTable();
							homeView.updateStats();
						} catch (IllegalArgumentException iae) {
							JOptionPane.showMessageDialog(null, iae.getMessage());
						} catch (IllegalStateException ise) {
							//Do nothing
						}
						
						//Reset selection of dropdown and update as necessary
						fileOptions.setSelectedIndex(0);						
						
						
					} else if (fileOptions.getSelectedItem().equals("Save")) {

						try {
							filename = getFilename(false);
							Manager.getInstance().saveFile(filename);
						} catch (IllegalArgumentException iae) {
							JOptionPane.showMessageDialog(null, iae.getMessage());
						} catch (IllegalStateException ise) {
							//Do nothing
						}
						
						//Reset selection of dropdown
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
				toggleToolbarButtons(btnHome);
			}
		});

		
		//Add new Anime button on toolbar
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)cardPanel.getLayout();
				cl.show(cardPanel, "addView");
				toggleToolbarButtons(btnAdd);

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

	/**
	 * Re-enables all toolbar buttons and selectively disables the most recently selected one
	 * @param selected button linking to the card the user is currently on
	 */
	private void toggleToolbarButtons(JButton selected) {
		//Enable all buttons
		btnHome.setEnabled(true);
		btnAdd.setEnabled(true);
		btnEdit.setEnabled(true);
		//Disable the selected button
		selected.setEnabled(false);
	}

	/**
	 * Gets a file location on the user's system via JFileChooser to save at or load information from.
	 * @param boolean indicator of if JFileChooser needs to handle load or save procedure, true for load
	 * @return filename for loading or saving a file
	 * @throws IllegalStateException if user does not select a file
	 */
	private String getFilename(boolean load) {
		//TODO: note: "./" sets to currect working directory, is necessary? works good blank too...
		JFileChooser fc = new JFileChooser("./");
		int returnVal;
		//Show user prompts
		if (load) {
			returnVal = fc.showOpenDialog(fc);
		} else {
			returnVal = fc.showSaveDialog(null);
		}
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			throw new IllegalStateException();
		}
		
		//Get and return path from user selection
		File logFile = fc.getSelectedFile();
		return logFile.getAbsolutePath();
	}
	
	/**
	 * Updates the table with information from the user's anime log data
	 */
	private void updateTable() {
		
		int numRows = Manager.getInstance().getAnimeList().size();
		Object[][] rowVals = Manager.getInstance().getAllAnimeAsArray();

		DefaultTableModel tm = (DefaultTableModel) table.getModel();
		tm.setRowCount(0);
		Object[] row = new Object[3];
		
		for (int i = 0; i < numRows; i++) {
			row = rowVals[i];
			tm.addRow(row);
		}
		//TODO: sort by options, filter
		
		
		//TODO: Investigate jtable models to better current system
		//problems: text not wrap in case of long title, highlight weird
	}
	
	
	//////////////////////////////
	
	private void testStartUp() {
		Manager.getInstance().processFile("./test-files/ThreeWorkingImports.txt");
		updateTable();
		homeView.updateStats();
	}
	
}
