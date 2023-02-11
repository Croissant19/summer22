package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/***
 * Class defining the Dialog that happens when the user clicks the ? button in the OptionsView.
 * @author Hunter Pruitt
 */
public class SurpriseDialog extends JDialog {

	/** Used in the JLabel lblText1 */
	private static final String SURPRISE_TEXT_P1 = "\"Don't you see I'm workin' here!";
	
	/** Used in the JLabel lblText2 */
	private static final String SURPRISE_TEXT_P2 = "Unless you brought me something to eat, I'm gonna have to ask you to get out...\"";

	private JPanel contentPanel;
	private JButton btnBye;
	private JLabel lblText1;
	private JLabel lblText2;
	private JLabel lblImage;

	/**
	 * Create the dialog.
	 * @param mainGUI pointer to the main GUI from which this dialog originates
	 */
	public SurpriseDialog(GUI mainGUI) {
		//Initialize surprise frame
		super(mainGUI, "Dev Corner");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(mainGUI.getX() + 150, mainGUI.getY() + 60, 450, 375); //+150 and +60 to center the dialog

		//Create panel and add components
		addComponents();
		addBtnEvent();
		addWindowEvents(mainGUI);

		//Set visible
		this.setVisible(true);
	}

	/**
	 * Initializes the main components of the dialog, being the image JLabel, the text JLabel, 
	 * the JButton, and the JPanel which holds them.
	 */
	private void addComponents() {
		contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		//Place image
		Component strut0 = Box.createVerticalStrut(10);
		contentPanel.add(strut0);
		//Try-catch in case image is missing
		try {
			lblImage = new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/resources/dev.png"))));
			lblImage.setAlignmentX(CENTER_ALIGNMENT);
		} catch (IOException e) {
			lblImage = new JLabel("ERROR: MISSING /resource/dev.png");
		}
		contentPanel.add(lblImage);


		//Place text
		Component strut2 = Box.createVerticalStrut(10);
		contentPanel.add(strut2);

		lblText1 = new JLabel(SURPRISE_TEXT_P1);
		lblText1.setAlignmentX(CENTER_ALIGNMENT);
		contentPanel.add(lblText1);

		lblText2 = new JLabel(SURPRISE_TEXT_P2);
		lblText2.setAlignmentX(CENTER_ALIGNMENT);
		contentPanel.add(lblText2);

		
		//Create close button
		Component strut1 = Box.createVerticalStrut(15);
		contentPanel.add(strut1);

		btnBye = new JButton("Bye Bye!");
		btnBye.setActionCommand("OK");
		getRootPane().setDefaultButton(btnBye);
		btnBye.setAlignmentX(CENTER_ALIGNMENT);
		contentPanel.add(btnBye);
		
		
		getContentPane().add(contentPanel);
	}

	/**
	 * Configures the dialog dispose to be called when the "Bye Bye!" button is clicked
	 */
	private void addBtnEvent() {
		btnBye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});		
	}

	/***
	 * Adds a custom WindowListener which manages user access to the main window while
	 * the SurpriseDialog exists.
	 * @param pointer to the main GUI so that it can be enabled/disabled when the window closes/opens
	 */
	private void addWindowEvents(GUI mainGUI) {
		addWindowListener(new WindowListener() {

            public void windowOpened(WindowEvent arg0) {
                //Disable the mainGUI
				mainGUI.setEnabled(false);
            }
            public void windowClosed(WindowEvent arg0) {
            	//Re-enable the main GUI
            	mainGUI.setEnabled(true);
            }
            public void windowClosing(WindowEvent arg0) {
                //Do nothing
            }
			public void windowActivated(WindowEvent arg0) {
				//Do nothing
			}
            public void windowDeactivated(WindowEvent arg0) {
                //Do nothing
            }
            public void windowDeiconified(WindowEvent arg0) {
                //Do nothing
            }
            public void windowIconified(WindowEvent arg0) {
                //Do nothing
            }
		});		
	}

}
