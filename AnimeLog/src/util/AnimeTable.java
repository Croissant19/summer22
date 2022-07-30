package util;


import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import data.Anime;
import data.Anime.Language;
import data.Anime.Type;
import data.Preferences.ColorMethod;
import manager.Manager;

/**
 * @author Hunter
 *
 */
public class AnimeTable extends JTable {

	/** Data headers for the data table */
	private static final String[] COLUMN_NAMES= {"Year",
            "Title",
            "Count"};
	
	private static TableModel m = new DefaultTableModel(null, COLUMN_NAMES) {
		@Override
	    public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }
	};
	
	public AnimeTable(TableModel tm, ColorMethod colorBy) {
		super(m);
		super.setDefaultRenderer(getClass(), new ColorRenderer(colorBy));
	}

	
	private class ColorRenderer extends DefaultTableCellRenderer {

		private ColorMethod colorBy;
		
		/**
		 * Creates the renderer and sets the coloring criteria
		 * @param colorBy criteria for coloring rows
		 */
		public ColorRenderer(ColorMethod colorBy) {
			super();
			this.colorBy = colorBy;
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean hasFocus, int row, int col) {
			
			super.getTableCellRendererComponent(table, value, selected, hasFocus, row, col);
			
			//Get Anime object represented by row
			Anime a = Manager.getInstance().getAnimeList().get(row);
			
			//See if row meets status one
			if (meetsStatusOne(a)) {
				setBackground(Manager.getInstance().getPreferences().getColor1());
			}
			
			//See if row meets status two
			if (meetsStatusTwo(a)) {
				setBackground(Manager.getInstance().getPreferences().getColor2());
			}
			
			//TODO: maybe code for resetting to default bg color?
			
			return this;
		}

		/**
		 * Indicates is the Anime should be colored according to the TableRenderer's ColorMethod.
		 * Note: The ColorFocus options are in order, so that finished, series, and sub are status ones, 
		 * while dropped, special, and dub are status twos.
		 * @param anime to be investigated
		 * @return boolean indicator as to if the Anime meets the qualifier for coloring
		 */
		private boolean meetsStatusOne(Anime a) {
			boolean meetsStatus = false;
			if (colorBy == ColorMethod.FIN_DROP) {
				if (a.isFinished()) {
					meetsStatus = true;
				}
			} else if (colorBy == ColorMethod.SERIES_SPECIAL) {
				if (a.getType() == Type.SERIES.formattedName) {
					meetsStatus = true;
				}
			} else if (colorBy == ColorMethod.SUB_DUB) {
				if (a.getLanguage() == Language.SUB.formattedName) {
					meetsStatus = true;
				}
			}
			return meetsStatus;
		}

		/**
		 * Indicates is the Anime should be colored according to the TableRenderer's ColorMethod.
		 * Note: The ColorFocus options are in order, so that finished, series, and sub are status ones, 
		 * while dropped, special, and dub are status twos.
		 * @param anime to be investigated
		 * @return boolean indicator as to if the Anime meets the qualifier for coloring
		 */
		private boolean meetsStatusTwo(Anime a) {
			boolean meetsStatus = false;
			if (colorBy == ColorMethod.FIN_DROP) {
				if (a.isDropped()) {
					meetsStatus = true;
				}
			} else if (colorBy == ColorMethod.SERIES_SPECIAL) {
				if (a.getType() == Type.SPECIAL.formattedName) {
					meetsStatus = true;
				}
			} else if (colorBy == ColorMethod.SUB_DUB) {
				if (a.getLanguage() == Language.DUB.formattedName) {
					meetsStatus = true;
				}
			}
			return meetsStatus;
		}

		
	}
	
}
