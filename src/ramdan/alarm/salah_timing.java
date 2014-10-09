package ramdan.alarm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class salah_timing extends Activity {
	/** Called when the activity is first created. */
	Context ctx;
	TextView times;
	TextView names;
	double longitude;
	double latitude;
	PrayTime obj_pr;
	ArrayList<String> time_array;
	ArrayList<String> prayer_names;

	double time_zone = 0;

	boolean network_enabled;
	List<String> configuration_list;
	public TableLayout tl;
	TextView month_name;
	UserDataSource obj = new UserDataSource(this);
	Calendar c;
	TextView welcome_date;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.salah_view);
		ctx = this;
		final Button settings = (Button) findViewById(R.id.settings);
		final Button month_view = (Button) findViewById(R.id.month);
		final Button home_view = (Button) findViewById(R.id.home);
		final RelativeLayout rl = (RelativeLayout) findViewById(R.id.r222);
		welcome_date = (TextView) findViewById(R.id.welcome_date);
		final Button dua_view = (Button) findViewById(R.id.about);
		final Button salah_view = (Button) findViewById(R.id.salah_time);

		final Button next_day = (Button) findViewById(R.id.next_day);
		final Button previous_day = (Button) findViewById(R.id.previous_day);

		c = Calendar.getInstance();

		// month_name = (TextView) findViewById(R.id.month_name);

		obj.open();
		if (obj.configure_table_rows_count() != 0) {
			configuration_list = obj.getConfiguration();
			for (int i = 0; i < configuration_list.size(); i++) {
				Log.e("ahan", configuration_list.get(i));
			}

		}
		obj.close();
		tl = (TableLayout) findViewById(R.id.maintable);
		obj_pr = new PrayTime();
		if (configuration_list.get(3).equals("12")) {
			obj_pr.setTimeFormat(obj_pr.Time12);
		}
		if (configuration_list.get(3).equals("24")) {
			obj_pr.setTimeFormat(obj_pr.Time24);
		}
		if (configuration_list.get(0).equals("Shafii")) {
			obj_pr.setAsrJuristic(obj_pr.Shafii);
		}
		if (configuration_list.get(0).equals("Hanafi")) {
			obj_pr.setAsrJuristic(obj_pr.Hanafi);
		}
		if (configuration_list.get(1).contains("Ithna")) {
			obj_pr.setCalcMethod(obj_pr.Jafari);
		}
		if (configuration_list.get(1).contains("Karachi")) {
			obj_pr.setCalcMethod(obj_pr.Karachi);
		}
		if (configuration_list.get(1).contains("ISNA")) {
			obj_pr.setCalcMethod(obj_pr.ISNA);
		}
		if (configuration_list.get(1).contains("MWL")) {
			obj_pr.setCalcMethod(obj_pr.MWL);
		}
		if (configuration_list.get(1).contains("Makkah")) {
			obj_pr.setCalcMethod(obj_pr.Makkah);
		}
		if (configuration_list.get(1).contains("Egyptian")) {
			obj_pr.setCalcMethod(obj_pr.Egypt);
		}
		if (configuration_list.get(1).contains("Tehran")) {
			obj_pr.setCalcMethod(obj_pr.Tehran);
		}
		if (configuration_list.get(2).equals("GMT -12:00")) {
			time_zone = -12;
		}
		if (configuration_list.get(2).equals("GMT -11:00")) {
			time_zone = -11;
		}
		if (configuration_list.get(2).equals("GMT -10:00")) {
			time_zone = -10;
		}
		if (configuration_list.get(2).equals("GMT -9:00")) {
			time_zone = -9;
		}
		if (configuration_list.get(2).equals("GMT -8:00")) {
			time_zone = -8;
		}
		if (configuration_list.get(2).equals("GMT -7:00")) {
			time_zone = -7;
		}
		if (configuration_list.get(2).equals("GMT -6:00")) {
			time_zone = -6;
		}
		if (configuration_list.get(2).equals("GMT -5:00")) {
			time_zone = -5;
		}
		if (configuration_list.get(2).equals("GMT -4:30")) {
			time_zone = -4.3;
		}
		if (configuration_list.get(2).equals("GMT -4:00")) {
			time_zone = -4;
		}
		if (configuration_list.get(2).equals("GMT -3:30")) {
			time_zone = -3.3;
		}
		if (configuration_list.get(2).equals("GMT -3:00")) {
			time_zone = -3;
		}
		if (configuration_list.get(2).equals("GMT -2:00")) {
			time_zone = -2;
		}
		if (configuration_list.get(2).equals("GMT -1:00")) {
			time_zone = -1;
		}
		if (configuration_list.get(2).equals("GMT 0:00")) {
			time_zone = 0;
		}
		if (configuration_list.get(2).equals("GMT 1:00")) {
			time_zone = 1;
		}
		if (configuration_list.get(2).equals("GMT 2:00")) {
			time_zone = 2;
		}
		if (configuration_list.get(2).equals("GMT 3:00")) {
			time_zone = 3;
		}
		if (configuration_list.get(2).equals("GMT 3:30")) {
			time_zone = 3.3;
		}
		if (configuration_list.get(2).equals("GMT 4:00")) {
			time_zone = 4;
		}
		if (configuration_list.get(2).equals("GMT 4:30")) {
			time_zone = 4.3;
		}
		if (configuration_list.get(2).equals("GMT 5:00")) {
			time_zone = 5;
		}
		if (configuration_list.get(2).equals("GMT 5:30")) {
			time_zone = 5.3;
		}
		if (configuration_list.get(2).equals("GMT 6:00")) {
			time_zone = 6;
		}
		if (configuration_list.get(2).equals("GMT 7:00")) {
			time_zone = 7;
		}
		if (configuration_list.get(2).equals("GMT 8:00")) {
			time_zone = 8;
		}
		if (configuration_list.get(2).equals("GMT 9:00")) {
			time_zone = 9;
		}
		if (configuration_list.get(2).equals("GMT 9:30")) {
			time_zone = 9.3;
		}
		if (configuration_list.get(2).equals("GMT 10:00")) {
			time_zone = 10;
		}
		if (configuration_list.get(2).equals("GMT 11:00")) {
			time_zone = 11;
		}
		if (configuration_list.get(2).equals("GMT 12:00")) {
			time_zone = 12;
		}
		longitude = Double.parseDouble(configuration_list.get(4));
		latitude = Double.parseDouble(configuration_list.get(5));

		table(0);

		settings.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(salah_timing.this,
						timing_configuration_screen.class);
				finish();
				startActivity(spinneract);
			}
		});
		month_view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(salah_timing.this,
						month_timings.class);
				finish();
				startActivity(spinneract);
			}
		});
		home_view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(salah_timing.this, ramdan.class);
				finish();
				startActivity(spinneract);
			}
		});
		dua_view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(salah_timing.this, dua.class);
				finish();
				startActivity(spinneract);
			}
		});
		salah_view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(salah_timing.this,
						salah_timing.class);
				finish();
				startActivity(spinneract);
			}
		});

		next_day.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				tl.removeAllViews();
				table(1);
			}
		});
		previous_day.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				tl.removeAllViews();
				table(-1);
			}
		});
		// times.append(Integer.toString(longitude) + "\n");

	}

	private Double roundoff(double value) {
		return (double) Math.round(value * 10000) / 10000;
	}

	public void table(int a) {
		if (a == 1) {

			c.add(Calendar.DATE, 1);
			c.add(Calendar.DATE, -7);
		}
		if (a == -1) {
			c.add(Calendar.DATE, -1);
			c.add(Calendar.DATE, 7);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		String dayOfTheWeek = sdf.format(c.getTime());
		SimpleDateFormat month_date = new SimpleDateFormat("MMMMMMMMM");
		String month_namev = month_date.format(c.getTime());
		welcome_date.setText(dayOfTheWeek + " , " + month_namev + " "
				+ c.get(Calendar.DATE) + " , " + c.get(Calendar.YEAR));

		// SimpleDateFormat month_date = new SimpleDateFormat("MMMMMMMMM");
		// String month_n = month_date.format(c.getTime());
		// month_name.setText(month_n);
		// c.set(Calendar.DAY_OF_MONTH, 1);
		time_array = obj_pr.getPrayerTimes(c, roundoff(latitude),
				roundoff(longitude), time_zone);
		prayer_names = obj_pr.getTimeNames();
		int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		Log.e("Days", Integer.toString(monthMaxDays));
		TableRow th = new TableRow(this);
		// tr.setId(100 + current);
		th.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));

		// Create a TextView to house the name of the province
		TextView th_t = new TextView(this);
		// labelTV.setId(200 + current);
		th_t.setText("Prayer Name");
		th_t.setTypeface(null, Typeface.BOLD);
		th_t.setTextColor(Color.WHITE);
		th_t.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		th_t.setBackgroundResource(R.drawable.cells_bg);

		th.addView(th_t);

		TextView th_s = new TextView(this);
		// labeTVP.setId(300 + current);
		th_s.setText("Time");
		th_s.setTextColor(Color.WHITE);
		th_s.setTypeface(null, Typeface.BOLD);
		th_s.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		th_s.setBackgroundResource(R.drawable.cells_bg);

		th.addView(th_s);

		// Add the TableRow to the TableLayout
		tl.addView(th, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));

		for (int current = 0; current < prayer_names.size(); current++) {
			// Create a TableRow and give it an ID
			TableRow tr = new TableRow(this);
			// tr.setId(100 + current);
			tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			// Create a TextView to house the name of the province
			TextView labelTV = new TextView(this);
			// labelTV.setId(200 + current);
			int m = c.get(Calendar.MONTH) + 1;
			labelTV.setText(prayer_names.get(current));
			labelTV.setTextColor(Color.BLACK);
			if (current % 2 == 0) {
				// labelTV.setBackgroundColor(getResources().getColor(R.color.));
				labelTV.setBackgroundResource(R.drawable.cells_bg3);
			} else {
				// labelTV.setBackgroundColor(80FFFFFF);
				labelTV.setBackgroundResource(R.drawable.cells_bg2);
			}
			labelTV.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			tr.addView(labelTV);

			TextView labeTVP = new TextView(this);
			// labeTVP.setId(300 + current);
			labeTVP.setText(time_array.get(current));
			labeTVP.setTextColor(Color.BLACK);
			labeTVP.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			if (current % 2 == 0) {
				labeTVP.setBackgroundResource(R.drawable.cells_bg3);
			} else {
				labeTVP.setBackgroundResource(R.drawable.cells_bg2);
			}
			tr.addView(labeTVP);

			// Create a TextView to house the value of the after-tax income

			// Add the TableRow to the TableLayout
			tl.addView(tr, new TableLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			c.add(Calendar.DATE, 1);

		}

	}

	@Override
	public void onBackPressed() {

		finish();
		moveTaskToBack(true);
	}

}