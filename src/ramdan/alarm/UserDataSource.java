package ramdan.alarm;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserDataSource {

	private static SQLiteDatabase db;
	private MySQLiteHelper mh;

	private String[] allColumns_configure_data = { "asarangle", "calmethod",
			"timezone", "timeformate","lat","long" };

	public UserDataSource(Context context) {
		mh = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		db = mh.getWritableDatabase();
	}

	public void close() {
		mh.close();
	}

	public long insert_configure(timing_configuration_model configure_var) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.col_asar_angle, configure_var.getAsar_angle());
		values.put(MySQLiteHelper.col_cal_method, configure_var.getCal_method());
		values.put(MySQLiteHelper.col_time_zone, configure_var.getTime_zone());
		values.put(MySQLiteHelper.col_time_formate,
				configure_var.getTime_formate());
		values.put(MySQLiteHelper.col_lat,
				configure_var.getLat());
		values.put(MySQLiteHelper.col_long,
				configure_var.getLongi());
		long insertId = db.insert(MySQLiteHelper.table_configure, null, values);
		return insertId;
	}

	public int configure_table_rows_count() {
		Cursor cursor = db.query(MySQLiteHelper.table_configure,
				allColumns_configure_data, null, null, null, null, null);
		Log.d("<<<< count >>>>", "" + cursor.getCount());
		cursor.close();
		return cursor.getCount();
	}

	public List<String> getConfiguration() {
		List<String> configuration = new ArrayList<String>();
		// message_list.add("None");
		Cursor cursor = db.query(MySQLiteHelper.table_configure,
				allColumns_configure_data, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			timing_configuration_model activity = cursortoconfigure(cursor);
			configuration.add(activity.getAsar_angle().toString());
			configuration.add(activity.getCal_method().toString());
			configuration.add(activity.getTime_zone().toString());
			configuration.add(activity.getTime_formate().toString());
			configuration.add(activity.getLat().toString());
			configuration.add(activity.getLongi().toString());
			open();
			cursor.moveToNext();
		}
		cursor.close();
		return configuration;
	}

	private timing_configuration_model cursortoconfigure(Cursor cur) {
		timing_configuration_model con_ob = new timing_configuration_model();
		try {

			// cursor.moveToFirst();
			// p-String firstColumn = cursor.getString(0);
			con_ob.setAsar_angle(cur.getString(0));

			con_ob.setCal_method(cur.getString(1));
			con_ob.setTime_zone(cur.getString(2));
			con_ob.setTime_formate(cur.getString(3));
			con_ob.setLat(cur.getString(4));
			con_ob.setLongi(cur.getString(5));
			

		} catch (Exception ex) {
			con_ob = null;
		}
		// cursor.close();
		return con_ob;
	}

	public static void reset_configure_table() {

		db.execSQL("DROP TABLE " + "configure_data");

		db.execSQL(MySQLiteHelper.create_table_configure);
	}

}
