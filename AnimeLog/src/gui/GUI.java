package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import data.Media.MediaType;
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
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;

/**
 * Main container for user interface, holding a JMediaTable and several JPanels in a Card Layout
 * @author Hunter Pruitt
 */
public class GUI extends JFrame {

	/** Warning in case user tries to start browsing without any media added */
	private static final String BROWSE_WARNING = "To start browsing, you need to add at least one entry to your list.";

	/** Notice that some media needs to be selected to be removed */
	private static final String REMOVE_INTRUCTIONS = "You must select an entry to remove it.";
	
	/** Warning asking if user is sure they want to remove the entry, when used, the title of the media is placed following */
	private static final String REMOVE_WARNING = "Are you sure you want to remove ";
	
	/** Used to prevent table row selection events from firing when table is being rebuilt and such */
	private boolean engageTableListener = true;
	
	/** Indicates which type of Media is being worked with, set at import and whenever changed*/
	private MediaType mediaMode = MediaType.ANIME;
	
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JMediaTable table;
	private JPanel cardPanel;

	private JToolBar toolBar;
	private JComboBox<String> fileOptions;

	private JButton btnModeAnime;
	private JButton btnModeManga;
	private JButton btnHome;
	private JButton btnBrowse;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnOptions;
	
	private HomeView homeView = new HomeView(this);
	private BrowseView browseView = new BrowseView(this);
	private NewEntryView addView = new NewEntryView(this);
	private OptionsView optionsView = new OptionsView(this);
	private Component strutModeL;
	private Component strutModeR;
	private Component strutViewR;
	private Component strutViewL;
		
	//TODO: change remove functionality to go by browse's current, not the table's current, fix room for error and existing bug
	//TODO: implement change mode buttons
	
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
		setResizable(false);
		setTitle("Anime Log");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		
		initToolbar();

		//Create cards
		cardPanel = new JPanel();
		cardPanel.setMaximumSize(new Dimension(450, 300));

		cardPanel.setLayout(new CardLayout(0, 0));
				
		cardPanel.add(homeView, "homeView");
		cardPanel.add(addView, "addView");
		cardPanel.add(browseView, "browseView");
		cardPanel.add(optionsView, "optionsView");
		
		//Group layout info
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cardPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, 0, 0, Short.MAX_VALUE)
						.addComponent(cardPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		

		//Disable home button to indicate that is where you start
		toggleToolbarButtons(btnHome);

		//Instantiate table and set metadata
		table = new JMediaTable(this);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);


		//Logo from
		//https://www.flaticon.com/premium-icon/anime_2314736?term=anime&related_id=2314736#
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/resources/icon_24.png")));
	}
	
	/**
	 * Creates and adds the components for the toolbar
	 */
	private void initToolbar() {
		toolBar = new JToolBar();
		toolBar.setFloatable(false);

		fileOptions = new JComboBox<String>();

		//Header and options for the fileOptions dropdown
		fileOptions.addItem("File");
		fileOptions.addItem("Load");
		fileOptions.addItem("Save");

		toolBar.add(fileOptions);
		
		strutModeL = Box.createHorizontalStrut(20);
		toolBar.add(strutModeL);
		
		JLabel lblMode = new JLabel("Mode:");
		lblMode.setFont(new Font("Tahoma", Font.BOLD, 11));
		toolBar.add(lblMode);
		
		strutModeR = Box.createHorizontalStrut(20);
		strutModeR.setPreferredSize(new Dimension(5, 0));
		toolBar.add(strutModeR);
		
		btnModeAnime = new JButton("Anime");
		toolBar.add(btnModeAnime);
		
		btnModeManga = new JButton("Manga");
		toolBar.add(btnModeManga);
		
		strutViewL = Box.createHorizontalStrut(20);
		strutViewL.setPreferredSize(new Dimension(15, 0));
		toolBar.add(strutViewL);
		
		JLabel lblView = new JLabel("View:");
		lblView.setFont(new Font("Tahoma", Font.BOLD, 11));
		toolBar.add(lblView);
		
		strutViewR = Box.createHorizontalStrut(20);
		strutViewR.setPreferredSize(new Dimension(5, 0));
		toolBar.add(strutViewR);
		
		btnHome = new JButton("Home");
		btnHome.setRequestFocusEnabled(false);
		toolBar.add(btnHome);
		
		btnBrowse = new JButton("Browse");
		btnBrowse.setRequestFocusEnabled(false);
		toolBar.add(btnBrowse);
		
		btnAdd = new JButton("Add");
		btnAdd.setRequestFocusEnabled(false);
		toolBar.add(btnAdd);

		btnRemove = new JButton("Remove");
		btnRemove.setRequestFocusEnabled(false);
		toolBar.add(btnRemove);
		
		btnOptions = new JButton("Options");
		toolBar.add(btnOptions);		
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
						setCard("homeView");
						toggleToolbarButtons(btnHome);
						
						
					} else if (fileOptions.getSelectedItem().equals("Save")) {
						//In case user is in browse mode and has unsaved changes
						if (!browseView.canLeave()) {
							fileOptions.setSelectedIndex(0);
							return;
						}
						try {
							File file = new File(getFilename(false));

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

					browseView.setCurrentEntry(null);
				} else {
					return;
				}
				
			}
		});

		//Add functionality to Browse button
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If list is empty issue a warning that at least one anime needs to be added for this to work.
				if (Manager.getInstance().getList() == null ||
						Manager.getInstance().getList().size() == 0) {
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
					browseView.setCurrentEntry(null);
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
					JOptionPane.showMessageDialog(rootPane, REMOVE_INTRUCTIONS);
					return;
				} else {

					//Ask user if they are sure they want to remove the anime
					int ans = JOptionPane.showConfirmDialog(rootPane, REMOVE_WARNING + browseView.getCurrentEntry().getTitle() + "?");
					if (ans != JOptionPane.YES_OPTION) {
						//If not yes, stop operation
						return;
					} else {  //Otherwise
						//Remove the indicated element from Manager's master copy of the list
						Manager.getInstance().removeAnime(selected);
						//Reload data
						updateData(null);
						toggleToolbarButtons(btnHome);
						setCard("homeView");
						
					}
				}

			}
		});

		//Add functionality to Options button
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (browseView.canLeave()) {
					table.clearSelection();
					setCard("optionsView");
					toggleToolbarButtons(btnOptions);
					browseView.setCurrentEntry(null);

				} else {
					return;
				}
			}
		});

		

		//Table Events
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {

	        	//engageTableListener variable used so that when reloading table data, the program doesn't
	        	//switch into browse mode for no reason. This was a previous bug...
	        	if (!engageTableListener) {
	        		return;
	        	}

	        	//Do nothing if selecting anime that is already being viewed
	        	//May seem pointless, but protects against recursion when program has to counter user selection
	        	//in situations where user accidently leaves unsaved data and decides to return in the warning prompt
	        	int browseIdx = Manager.getInstance().getList().indexOf(browseView.getCurrentEntry());
	        	if (browseView.getCurrentEntry() != null && table.getSelectedRow() == browseIdx) {
	        		return;
	        	}
	        	
	        	//In case user is editing in browse mode
	        	if (browseView.canLeave()) {
		        	//Get index and pass corresponding anime to BrowseView
		        	int idx = table.getSelectedRow();
		        	//idx is -1 when table row is deselected
		        	if (idx != -1) {
			        	browseView.setCurrentEntry(Manager.getInstance().getList().get(idx));	
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
    	// In case the user is leaving the NewAnimeView, reset the top text instructions and fields.
    	addView.resetCard();
    	//In case the user is leaving the OptionsView, reset selected to current preferences
    	optionsView.displayCurrentSelection();
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
		btnOptions.setEnabled(true);
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
		//Note: "./" sets to current working directory, and blank parameters set it to Documents folder
		JFileChooser fc = new JFileChooser("./");
		int returnVal;
		//Show user prompts
		if (load) {
			returnVal = fc.showOpenDialog(rootPane);
		} else {
			returnVal = fc.showSaveDialog(rootPane);
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
	 * @param mt MediaType to be displayed, null if media type is not changed
	 */
	public void updateData(MediaType mt) {
		if (mt != null) {
			Manager.getInstance().setCurrentList(mt);
		}
		updateTable();
		homeView.updateStats();
	}
	
	/**
	 * Updates the table with information from the user's anime log data
	 */
	private void updateTable() {
		engageTableListener = false;
		int numRows = Manager.getInstance().getList().size();
		Object[][] rowVals;
		DefaultTableModel tm;
		Object[] row;
		
		//Get sorted table using proper procedure
		switch (mediaMode) {
			case ANIME:
				table.getRenderer().setRenderer(Manager.getInstance().getAnimePreferences().getColorMethod());
				rowVals = Manager.getInstance().getAllAnimeAsArray();

				tm = (DefaultTableModel) table.getModel();
				tm.setRowCount(0);
				row = new Object[3];
				
				for (int i = 0; i < numRows; i++) {
					row = rowVals[i];
					tm.addRow(row);
				}
				
				
				
				break;
			case MANGA:
				table.getRenderer().setRenderer(Manager.getInstance().getMangaPreferences().getColorMethod());
				rowVals = Manager.getInstance().getAllMangaAsArray();
				
				tm = (DefaultTableModel) table.getModel();
				tm.setRowCount(0);
				row = new Object[3];
				
				for (int i = 0; i < numRows; i++) {
					row = rowVals[i];
					tm.addRow(row);
				}
		}

		//Re-engage table listener
		engageTableListener = true;
	}
	
	/**
	 * Used to set the selected table row from BrowseView, so that Next and Previous buttons work
	 * @param idx index of the table to select
	 */
	public void setTableSelected(int idx) {
    	table.setRowSelectionInterval(idx, idx);		
	}
	
	/**
	 * Indicates which type of media is being worked with
	 * @return MediaType enum for the type on entries the user can see/interact with
	 */
	public MediaType getMediaMode() {
		return this.mediaMode;
	}
}
