package ramdan.alarm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String table_configure = "configure_data";

	public static final String col_asar_angle = "asarangle";
	public static final String col_cal_method = "calmethod";
	public static final String col_time_zone = "timezone";
	public static final String col_time_formate = "timeformate";
	public static final String col_lat = "lat";
	public static final String col_long = "long";
	

	private static final String DATABASE_NAME = "ramdanalarm.db";
	private static final int DATABASE_VERSION = 50;

	public static final String create_table_configure = "create table" + " "
			+ table_configure + "(" + col_asar_angle + " text, "
			+ col_cal_method + " text, " + col_time_zone + " text, "
			+ col_time_formate + " text,"+ col_lat + " text,"+ col_long + " text);";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(create_table_configure);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + table_configure);
		onCreate(db);

	}

}
