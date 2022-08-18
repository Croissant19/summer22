package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import data.Anime;
import data.Anime.Language;
import data.Media;
import data.Media.Type;
import data.Preferences.ColorMethod;
import manager.Manager;

/**
 * Defines a JTable type class specifically for displaying Media.
 * Special functionality includes uniform non-editable cells and
 * row coloring depending on contents and user-set conditions
 * @author Hunter Pruitt
 */
public class JMediaTable extends JTable {

	/** Data headers for the data table */
	private static final String[] COLUMN_NAMES= {"Year",
            "Title",
            "Count"};
	
	/**
	 * Defines an adjusted DefaultTableModel by removing the ability for all cells to be editable.
	 */
	private static final TableModel NO_EDIT_MODEL = new DefaultTableModel(null, COLUMN_NAMES) {

		@Override
	    public boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }
	};
	
	/** pointer to the table's ColorRenderer so that the color method therein can be changed */
	private ColorRenderer renderer;

	/**
	 * Constructor for JMediaTable. Creates a JTable using the JTable(TableModel) constructor with this class's custom TableModel
	 * and setting the default renderer to a ColorRenderer
	 */
	public JMediaTable() {
		super(NO_EDIT_MODEL);
		renderer = new ColorRenderer();
		super.setDefaultRenderer(Object.class, renderer);
		//Set header to bold
		getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));
	}

	/**
	 * Returns the ColorRenderer so that it can be adjusted for the user's changes in ColorMethod
	 * @return the renderer
	 */
	public ColorRenderer getRenderer() {
		return renderer;
	}

	/**
	 * Defines the rendering class used in JMediaTables.
	 * Contains functionality to color rows as necessary depending on user's color method selection
	 * and the represented Media values.
	 * 
	 * Created with help from 
	 * <a href = "https://stackoverflow.com/questions/24848314/change-background-color-of-jtable-row-based-on-column-value">
	 * https://stackoverflow.com/questions/24848314/change-background-color-of-jtable-row-based-on-column-value</a>
	 */
	public class ColorRenderer extends DefaultTableCellRenderer {

		/** ColorMethod used for coloring the table, set to NO_COLOR until something is specified otherwise */
		private ColorMethod colorBy = ColorMethod.NO_COLOR;
		
		/**
		 * Sets the color rendering method.
		 * @param colorBy ColorMethod to render with
		 */
		public void setRenderer(ColorMethod colorBy) {
			this.colorBy = colorBy;
		}
		
		/**
		 * Overrides DefaultTableCellRenderer's getTableCellRendererComponent so that all cells, regardless of data type
		 * will be colored appropriately if their row is deemed to be colored by the user specifications
		 */
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean hasFocus, int row, int col) {

			super.getTableCellRendererComponent(table, value, selected, hasFocus, row, col);

			//TODO: check both possible implementations, one by using currentMediaMode field in Manager, and other by usingunlikely work below
			//Get Media object represented by row
			Anime a = Manager.getInstance().getList().get(row);
			//TODO: unlikely work
			Media m = Manager.getInstance().getMangaList().get(row);
			
			//See if row meets status one
			if (meetsStatusOne(a)) {
				setBackground(Manager.getInstance().getAnimePreferences().getColor1());
			} 
			//See if row meets status two
			else if (meetsStatusTwo(a)) {
				setBackground(Manager.getInstance().getAnimePreferences().getColor2());
			} else {
				setBackground(Color.WHITE);
				setForeground(Color.BLACK);
			}

			return this;
		}

		/**
		 * Indicates if the Media should be colored according to the TableRenderer's ColorMethod.
		 * Note: The ColorFocus options are in order, so that finished, series, and sub are status ones, 
		 * while dropped, special, and dub are status twos.
		 * @param media to be investigated
		 * @return boolean indicator as to if the Media meets the qualifier for coloring
		 */
		private boolean meetsStatusOne(Media m) {
			boolean meetsStatus = false;
			if (colorBy == ColorMethod.FIN_DROP) {
				if (m.isFinished()) {
					meetsStatus = true;
				}
			} else if (colorBy == ColorMethod.SERIES_SPECIAL) {
				if (m.getType() == Type.SERIES.formattedName) {
					meetsStatus = true;
				}
			} else if (colorBy == ColorMethod.SUB_DUB && m instanceof Anime) {
				//Only Anime can be colored by language
				Anime a = (Anime) m;
				if (a.getLanguage() == Language.SUB.formattedName) {
					meetsStatus = true;
				}
			}
			return meetsStatus;
		}

		/**
		 * Indicates if the Media should be colored according to the TableRenderer's ColorMethod.
		 * Note: The ColorFocus options are in order, so that finished, series, and sub are status ones, 
		 * while dropped, special, and dub are status twos.
		 * @param media to be investigated
		 * @return boolean indicator as to if the Media meets the qualifier for coloring
		 */
		private boolean meetsStatusTwo(Media m) {
			boolean meetsStatus = false;
			if (colorBy == ColorMethod.FIN_DROP) {
				if (m.isDropped()) {
					meetsStatus = true;
				}
			} else if (colorBy == ColorMethod.SERIES_SPECIAL) {
				if (m.getType() == Type.SPECIAL.formattedName) {
					meetsStatus = true;
				}
			} else if (colorBy == ColorMethod.SUB_DUB && m instanceof Anime) {
				//Only Anime can be colored by language
				Anime a = (Anime) m;
				if (a.getLanguage() == Language.DUB.formattedName) {
					meetsStatus = true;
				}
			}
			return meetsStatus;
		}

	}
}