package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import data.Media.MediaType;
import manager.Manager;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.CardLayout;

/**
 * Defines the GUI components for the home view, with custom statistics for different Media Types
 * @author Hunter Pruitt
 */
public class HomeView extends JPanel {
	
	/** Default text at top of view, instructing how to use the page, uses html because newline characters cannot be displayed */
	private static final String LOAD_TEXT = "<html>To load a pre-existing file, click the <em>File</em> dropdown and select <em>Load</em>. "
			+ "Otherwise, click add to begin creating your own anime watch log!</html>";
	
	private static final String REMINDER = "<html>Don't forget to save before you leave in the <em>File</em> dropdown!</html>";
	
	/** Pointer to the main GUI component, used for accessing mediaMode and other functions */
	private GUI mainGUI;
	
	private JPanel cardLayout;
	
	//Stats to answer the lblQX series of JLabels for Anime data
	private JTextField txtA1Anime;
	private JTextField txtA2Anime;
	private JTextField txtA3Anime;
	private JTextField txtA4Anime;
	private JTextField txtA5Anime;
	private JTextField txtA6Anime;
	private JTextField txtA7Anime;

	//Stats to answer the lblQX series of JLabels for Manga data
	private JTextField txtA1Manga;
	private JTextField txtA2Manga;
	private JTextField txtA3Manga;
	private JTextField txtA4Manga;
	private JTextField txtA5Manga;
	private JTextField txtA6Manga;
	private JTextField txtA7Manga;
	private JTextField txtA8Manga;
	
	/**
	 * Create the panel.
	 */
	public HomeView(GUI gui) {
		//Set pointer to mainGUI
		this.mainGUI = gui;
		
		setLayout(null);
		
		JLabel lblInstructions = new JLabel(LOAD_TEXT);
		lblInstructions.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions.setBounds(43, 11, 364, 56);
		add(lblInstructions);
		
		JLabel lblSaveReminder = new JLabel(REMINDER);
		lblSaveReminder.setBounds(61, 287, 308, 14);
		add(lblSaveReminder);

		cardLayout = new JPanel();
		cardLayout.setBounds(10, 78, 405, 198);
		add(cardLayout);
		cardLayout.setLayout(new CardLayout(0, 0));
		

		initAnimeCard();
		initMangaCard();
		
		updateStats();
	}
	
	/**
	 * Initializes components for the Anime card of the Home View
	 */
	private void initAnimeCard() {
		JPanel pnlStatsAnime = new JPanel();
		cardLayout.add(pnlStatsAnime, "statsAnime");
		pnlStatsAnime.setLayout(null);
		
		JLabel lblQ1Anime = new JLabel("Entry Count:");
		lblQ1Anime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ1Anime.setBounds(10, 23, 117, 20);
		pnlStatsAnime.add(lblQ1Anime);
		
		txtA1Anime = new JTextField();
		txtA1Anime.setEditable(false);
		txtA1Anime.setBounds(121, 23, 61, 20);
		pnlStatsAnime.add(txtA1Anime);
		txtA1Anime.setColumns(10);
		
		JLabel lblQ2Anime = new JLabel("Number Series:");
		lblQ2Anime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ2Anime.setBounds(10, 66, 117, 20);
		pnlStatsAnime.add(lblQ2Anime);
		
		txtA2Anime = new JTextField();
		txtA2Anime.setEditable(false);
		txtA2Anime.setColumns(10);
		txtA2Anime.setBounds(121, 66, 61, 20);
		pnlStatsAnime.add(txtA2Anime);
		
		JLabel lblQ3Anime = new JLabel("Number Specials:");
		lblQ3Anime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ3Anime.setBounds(10, 109, 117, 20);
		pnlStatsAnime.add(lblQ3Anime);
		
		txtA3Anime = new JTextField();
		txtA3Anime.setEditable(false);
		txtA3Anime.setColumns(10);
		txtA3Anime.setBounds(121, 109, 61, 20);
		pnlStatsAnime.add(txtA3Anime);
		
		JLabel lblQ4Anime = new JLabel("Episode Count:");
		lblQ4Anime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ4Anime.setBounds(10, 152, 117, 20);
		pnlStatsAnime.add(lblQ4Anime);
		
		txtA4Anime = new JTextField();
		txtA4Anime.setEditable(false);
		txtA4Anime.setColumns(10);
		txtA4Anime.setBounds(121, 152, 61, 20);
		pnlStatsAnime.add(txtA4Anime);
		
		JLabel lblQ5Anime = new JLabel("Favored Language:");
		lblQ5Anime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ5Anime.setBounds(207, 23, 117, 20);
		pnlStatsAnime.add(lblQ5Anime);
		
		txtA5Anime = new JTextField();
		txtA5Anime.setEditable(false);
		txtA5Anime.setColumns(10);
		txtA5Anime.setBounds(323, 23, 61, 20);
		pnlStatsAnime.add(txtA5Anime);

		JLabel lblQ6Anime = new JLabel("Percent Finished:");
		lblQ6Anime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ6Anime.setBounds(207, 66, 117, 20);
		pnlStatsAnime.add(lblQ6Anime);
		
		txtA6Anime = new JTextField();
		txtA6Anime.setEditable(false);
		txtA6Anime.setColumns(10);
		txtA6Anime.setBounds(323, 66, 61, 20);
		pnlStatsAnime.add(txtA6Anime);
		
		JLabel lblQ7Anime = new JLabel("Percent Dropped:");
		lblQ7Anime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ7Anime.setBounds(207, 109, 117, 20);
		pnlStatsAnime.add(lblQ7Anime);		

		txtA7Anime = new JTextField();
		txtA7Anime.setEditable(false);
		txtA7Anime.setColumns(10);
		txtA7Anime.setBounds(323, 109, 61, 20);
		pnlStatsAnime.add(txtA7Anime);
	}
	
	/**
	 * Initializes components for the Manga card of the Home View
	 */
	private void initMangaCard() {
		JPanel pnlStatsManga = new JPanel();
		cardLayout.add(pnlStatsManga, "statsManga");
		pnlStatsManga.setLayout(null);
		
		JLabel lblQ1Manga = new JLabel("Entry Count:");
		lblQ1Manga.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ1Manga.setBounds(10, 23, 117, 20);
		pnlStatsManga.add(lblQ1Manga);
		
		txtA1Manga = new JTextField();
		txtA1Manga.setEditable(false);
		txtA1Manga.setBounds(121, 23, 61, 20);
		pnlStatsManga.add(txtA1Manga);
		txtA1Manga.setColumns(10);
		
		JLabel lblQ2Manga = new JLabel("Number Series:");
		lblQ2Manga.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ2Manga.setBounds(10, 66, 117, 20);
		pnlStatsManga.add(lblQ2Manga);
		
		txtA2Manga = new JTextField();
		txtA2Manga.setEditable(false);
		txtA2Manga.setColumns(10);
		txtA2Manga.setBounds(121, 66, 61, 20);
		pnlStatsManga.add(txtA2Manga);
		
		JLabel lblQ3Manga = new JLabel("Number Specials:");
		lblQ3Manga.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ3Manga.setBounds(10, 109, 117, 20);
		pnlStatsManga.add(lblQ3Manga);
		
		txtA3Manga = new JTextField();
		txtA3Manga.setEditable(false);
		txtA3Manga.setColumns(10);
		txtA3Manga.setBounds(121, 109, 61, 20);
		pnlStatsManga.add(txtA3Manga);
		
		JLabel lblQ4Manga = new JLabel("Chapter Count:");
		lblQ4Manga.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ4Manga.setBounds(10, 152, 117, 20);
		pnlStatsManga.add(lblQ4Manga);
		
		txtA4Manga = new JTextField();
		txtA4Manga.setEditable(false);
		txtA4Manga.setColumns(10);
		txtA4Manga.setBounds(121, 152, 61, 20);
		pnlStatsManga.add(txtA4Manga);
		
		JLabel lblQ5Manga = new JLabel("Favored Publisher:");
		lblQ5Manga.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ5Manga.setBounds(207, 145, 131, 20);
		pnlStatsManga.add(lblQ5Manga);
		
		txtA5Manga = new JTextField();
		txtA5Manga.setEditable(false);
		txtA5Manga.setColumns(10);
		txtA5Manga.setBounds(207, 167, 177, 20);
		pnlStatsManga.add(txtA5Manga);

		JLabel lblQ6Manga = new JLabel("Percent Finished:");
		lblQ6Manga.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ6Manga.setBounds(207, 23, 117, 20);
		pnlStatsManga.add(lblQ6Manga);
		
		txtA6Manga = new JTextField();
		txtA6Manga.setEditable(false);
		txtA6Manga.setColumns(10);
		txtA6Manga.setBounds(323, 23, 61, 20);
		pnlStatsManga.add(txtA6Manga);

		JLabel lblQ7Manga = new JLabel("Percent Dropped:");
		lblQ7Manga.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ7Manga.setBounds(207, 66, 117, 20);
		pnlStatsManga.add(lblQ7Manga);		

		txtA7Manga = new JTextField();
		txtA7Manga.setEditable(false);
		txtA7Manga.setColumns(10);
		txtA7Manga.setBounds(323, 66, 61, 20);
		pnlStatsManga.add(txtA7Manga);
		
		JLabel lblQ8Manga = new JLabel("Percent Ongoing:");
		lblQ8Manga.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQ8Manga.setBounds(207, 109, 117, 20);
		pnlStatsManga.add(lblQ8Manga);		

		txtA8Manga = new JTextField();
		txtA8Manga.setEditable(false);
		txtA8Manga.setColumns(10);
		txtA8Manga.setBounds(323, 109, 61, 20);
		pnlStatsManga.add(txtA8Manga);
	}

	/**
	 * Updates the statistics on the HomeView after loading a file or editing the anime list stored in Manager
	 */
	public void updateStats() {
		toggleMediaCards(mainGUI.getMediaMode());
		
		switch (mainGUI.getMediaMode()) {
		case ANIME:
			txtA1Anime.setText(Manager.getInstance().getEntryCount(MediaType.ANIME));
			txtA2Anime.setText(Manager.getInstance().getNumSeries(MediaType.ANIME));
			txtA3Anime.setText(Manager.getInstance().getNumSpecial(MediaType.ANIME));
			txtA4Anime.setText(Manager.getInstance().getCountSum(MediaType.ANIME));
			txtA5Anime.setText(Manager.getInstance().getFavoredLanguageAndPercent());		
			txtA6Anime.setText(Manager.getInstance().getPercentFinished(MediaType.ANIME));
			txtA7Anime.setText(Manager.getInstance().getPercentDropped(MediaType.ANIME));
			break;
		case MANGA:
			txtA1Manga.setText(Manager.getInstance().getEntryCount(MediaType.MANGA));
			txtA2Manga.setText(Manager.getInstance().getNumSeries(MediaType.MANGA));
			txtA3Manga.setText(Manager.getInstance().getNumSpecial(MediaType.MANGA));
			txtA4Manga.setText(Manager.getInstance().getCountSum(MediaType.MANGA));
			txtA5Manga.setText(Manager.getInstance().getFavoredPublisher());		
			txtA6Manga.setText(Manager.getInstance().getPercentFinished(MediaType.MANGA));
			txtA7Manga.setText(Manager.getInstance().getPercentDropped(MediaType.MANGA));
			txtA8Manga.setText(Manager.getInstance().getPercentOngoing());
		}
		
	}

	/**
	 * Toggles which card on the cardLayout Panel is visible, therefore 
	 * rendering the components expected for the user's mediaMode visible
	 * @param mediaMode MediaType currently being used in the program
	 */
	private void toggleMediaCards(MediaType mediaMode) {
    	CardLayout cl = (CardLayout) cardLayout.getLayout();
		if (mediaMode == MediaType.ANIME) {
	    	cl.show(cardLayout, "statsAnime");
		} else if (mediaMode == MediaType.MANGA) {
	    	cl.show(cardLayout, "statsManga");
		}

	}
}
