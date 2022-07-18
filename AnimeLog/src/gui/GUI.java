package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

	/** Warning in case user tries to start browsing without any anime added */
	private static final String BROWSE_WARNING = "To start browsing, you need to add at least one anime in your list.";

	/** Warning that an anime needs to be selected to remove it */
	private static final String REMOVE_WARNING = "You must select an anime to remove it.";
	
	
	private JPanel contentPane;
	private JTable table;
	private JPanel cardPanel;


	private JComboBox<String> fileOptions;

	private JButton btnHome;
	private JButton btnBrowse;
	private JButton btnAdd;
	private JButton btnRemove;
	//TODO: browse button starting at index 0?
	//TODO: settings button for graph sort by? graph colors?
	
	private HomeView homeView = new HomeView();
	private BrowseView browseView = new BrowseView(this);
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
		
		btnBrowse = new JButton("Browse");
		btnBrowse.setRequestFocusEnabled(false);
		toolBar.add(btnBrowse);
		
		btnAdd = new JButton("Add");
		btnAdd.setRequestFocusEnabled(false);
		toolBar.add(btnAdd);
		
		//Disable home button to indicate that is where you start
		toggleToolbarButtons(btnHome);


		btnRemove = new JButton("Remove");
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
							JOptionPane.showMessageDialog(rootPane, iae.getMessage());
						} catch (IllegalStateException ise) {
							//Do nothing
						}
						
						//Reset selection of dropdown and update as necessary
						fileOptions.setSelectedIndex(0);						
						
						
					} else if (fileOptions.getSelectedItem().equals("Save")) {
						//In case user is in browse mode and has unsaved changes
						if (!browseView.canLeave()) {
							fileOptions.setSelectedIndex(0);
							return;
						}
						try {
							File file = new File(getFilename(false) + ".txt");
							
							//If file exists ask if ok to overwrite?
							if (file.exists()) {
								int ans = JOptionPane.showConfirmDialog(rootPane, file.getName() + " already exists. Is it okay to overwrite it?");
								if (ans != JOptionPane.YES_OPTION) {
									fileOptions.setSelectedIndex(0);
									return;
								}
							}

							
							Manager.getInstance().saveFile(file);
						} catch (IllegalArgumentException iae) {
							JOptionPane.showMessageDialog(rootPane, iae.getMessage());
						} catch (IllegalStateException ise) {
							//Do nothing
						}
						
						//Reset selection of dropdown
						fileOptions.setSelectedIndex(0);

						
					}
				}
			}
		});

		//Add functionality to Home button
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	//In case user is editing in browse mode
				if (browseView.canLeave()) {

					table.clearSelection();
					setCard("homeView");
					toggleToolbarButtons(btnHome);

					browseView.setCurrentAnime(null);
				} else {
					return;
				}
				
			}
		});

		//Add functionality to Browse button
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If list is empty issue a warning that at least one anime needs to be added for this to work.
				if (Manager.getInstance().getAnimeList() == null ||
						Manager.getInstance().getAnimeList().size() == 0) {
					JOptionPane.showMessageDialog(rootPane, BROWSE_WARNING);
					return;
				}
				
				//Enter browse view at index 0
				//This method will trigger the action listener which handles the card change and such
				setTableSelected(0);
			}
		});
		
		//Add functionality to Add button
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        	//In case user is editing in browse mode
				if (browseView.canLeave()) {

					table.clearSelection();
					setCard("addView");
					toggleToolbarButtons(btnAdd);
					browseView.setCurrentAnime(null);
				} else {
					return;
				}
				
			}
		});

		//Add functionality to Remove button
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selected = table.getSelectedRow();
				if (selected == -1) {
					JOptionPane.showMessageDialog(rootPane, REMOVE_WARNING);
					return;
				} else {
					//Remove the indicated element from Manager's master copy of the list
					Manager.getInstance().removeAnime(selected);
					//Reload data
					toggleToolbarButtons(btnHome);
					updateData();
					setCard("homeView");
				}
			}
		});

		

		//Table Events
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {

	        	//Do nothing if selecting anime that is already being viewed
	        	//May seem pointless, but protects against recursion when program has to counter user selection
	        	//in situations where user accidently leaves unsaved data and decides to return in the warning prompt
	        	int browseIdx = Manager.getInstance().getAnimeList().indexOf(browseView.getCurrentAnime());
	        	if (browseView.getCurrentAnime() != null && table.getSelectedRow() == browseIdx) {
	        		return;
	        	}
	        	
	        	//In case user is editing in browse mode
	        	if (browseView.canLeave()) {
		        	//Get index and pass corresponding anime to BrowseView
		        	int idx = table.getSelectedRow();
		        	//idx is -1 when table row is deselected
		        	if (idx != -1) {
			        	browseView.setCurrentAnime(Manager.getInstance().getAnimeList().get(idx));	
		        	}	        	
		        	//Change card
		        	toggleToolbarButtons(btnBrowse);
		        	setCard("browseView");
	
	        	} else {
	        		setTableSelected(browseIdx);
	        		return;
	        	}
	        }
	    });
		
		
		
	}

	
	/**
	 * Sets the view of the card panel to a requested page
	 * @param view identifying text unique to the desired view
	 */
	private void setCard(String view) {
    	CardLayout cl = (CardLayout) cardPanel.getLayout();
    	cl.show(cardPanel, view);		
	}

	/**
	 * Re-enables all toolbar buttons and selectively disables the most recently selected one.
	 * You can enable all buttons by passing null
	 * @param selected button linking to the card the user is currently on
	 */
	private void toggleToolbarButtons(JButton selected) {
		//Enable all buttons
		btnHome.setEnabled(true);
		btnBrowse.setEnabled(true);
		btnAdd.setEnabled(true);

		//Disable the selected button
		if (selected != null) {
			selected.setEnabled(false);	
		}
	}

	/**
	 * Gets a file location on the user's system via JFileChooser to save at or load information from.
	 * @param boolean indicator of if JFileChooser needs to handle load or save procedure, true for load
	 * @return filename for loading or saving a file
	 * @throws IllegalStateException if user does not select a file
	 */
	private String getFilename(boolean load) {
		//TODO: note: "./" sets to current working directory, is necessary? works good blank too...
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
	 * Updates the table and statistics on the home view
	 */
	public void updateData() {
		updateTable();
		homeView.updateStats();
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
		//problems: text not wrap in case of long title
	}
	
	/**
	 * Used to set the selected table row from BrowseView, so that Next and Previous buttons work
	 * @param idx index of the table to select
	 */
	public void setTableSelected(int idx) {
    	table.setRowSelectionInterval(idx, idx);		
	}

	
	//////////////////////////////
	
	private void testStartUp() {
		Manager.getInstance().processFile("./test-files/ThreeWorkingImports.txt");
		updateTable();
		homeView.updateStats();
	}
}
