package ramdan.alarm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.hijri.HijriCalTest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import ramdan.alarm.PrayTime;

public class month_timings extends Activity {
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
	boolean gps_enabled;
	boolean network_enabled;
	List<String> configuration_list;
	public TableLayout tl;
	TextView month_name;
	UserDataSource obj = new UserDataSource(this);
	Calendar c;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.month_view);
		ctx = this;
		final Button settings = (Button) findViewById(R.id.settings);
		final Button month_view = (Button) findViewById(R.id.month);
		final Button home_view = (Button) findViewById(R.id.home);
		final Button next = (Button) findViewById(R.id.next_month);
		final Button previous = (Button) findViewById(R.id.previous_month);
		final RelativeLayout rl = (RelativeLayout) findViewById(R.id.r222);
		final Button dua_view = (Button) findViewById(R.id.about);
		final Button salah_view = (Button) findViewById(R.id.salah_time);

		c = Calendar.getInstance();

		month_name = (TextView) findViewById(R.id.month_name);

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
				Intent spinneract = new Intent(month_timings.this,
						timing_configuration_screen.class);
				finish();
				startActivity(spinneract);
			}
		});
		month_view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(month_timings.this,
						month_timings.class);
				finish();
				startActivity(spinneract);
			}
		});
		home_view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(month_timings.this, ramdan.class);
				finish();
				startActivity(spinneract);
			}
		});
		next.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				tl.removeAllViews();
				table(1);
			}
		});
		previous.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				tl.removeAllViews();
				table(-1);
			}
		});
		dua_view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(month_timings.this, dua.class);
				finish();
				startActivity(spinneract);
			}
		});
		salah_view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(month_timings.this,
						salah_timing.class);
				finish();
				startActivity(spinneract);
			}
		});
		// times.append(Integer.toString(longitude) + "\n");

	}

	private Double roundoff(double value) {
		return (double) Math.round(value * 10000) / 10000;
	}

	public void table(int a) {
		if (a == 1) {
			c.set(Calendar.MONTH, c.get(Calendar.MONTH));
		}
		if (a == -1) {
			c.add(Calendar.MONTH, -2);
		}
		SimpleDateFormat month_date = new SimpleDateFormat("MMMMMMMMM");
		String month_n = month_date.format(c.getTime());
		month_name.setText(month_n);
		c.set(Calendar.DAY_OF_MONTH, 1);
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
		th_t.setText("Date");
		th_t.setTypeface(null, Typeface.BOLD);
		th_t.setTextColor(Color.WHITE);
		th_t.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		th_t.setBackgroundResource(R.drawable.cells_bg);

		th.addView(th_t);

		TextView th_s = new TextView(this);
		// labeTVP.setId(300 + current);
		th_s.setText("Sehar");
		th_s.setTextColor(Color.WHITE);
		th_s.setTypeface(null, Typeface.BOLD);
		th_s.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		th_s.setBackgroundResource(R.drawable.cells_bg);

		th.addView(th_s);

		// Create a TextView to house the value of the after-tax income
		TextView th_i = new TextView(this);
		// valueTV.setId(current);
		th_i.setText("Iftar");
		th_i.setBackgroundResource(R.drawable.cells_bg);

		th_i.setTypeface(null, Typeface.BOLD);
		th_i.setTextColor(Color.WHITE);
		th_i.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		th.addView(th_i);

		// Add the TableRow to the TableLayout
		tl.addView(th, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		HijriCalTest hct = new HijriCalTest();
		for (int current = 0; current < monthMaxDays; current++) {
			// Create a TableRow and give it an ID
			time_array = obj_pr.getPrayerTimes(c, roundoff(latitude),
					roundoff(longitude), time_zone);
			TableRow tr = new TableRow(this);
			// tr.setId(100 + current);
			tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));

			// Create a TextView to house the name of the province
			TextView labelTV = new TextView(this);
			// labelTV.setId(200 + current);
			int m = c.get(Calendar.MONTH) + 1;
			labelTV.setText(c.get(Calendar.DATE) + "/" + m + "/"
					+ c.get(Calendar.YEAR));
			labelTV.append(" "
					+ hct.Islamicdate(c.get(Calendar.DATE), m,
							c.get(Calendar.YEAR)));
			labelTV.setTextColor(Color.BLACK);
			if (current % 2 == 0) {
				labelTV.setBackgroundResource(R.drawable.icells_bg3);
			} else {
				labelTV.setBackgroundResource(R.drawable.icells_bg2);
			}
			labelTV.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			tr.addView(labelTV);

			TextView labeTVP = new TextView(this);
			// labeTVP.setId(300 + current);
			labeTVP.setText(obj_pr.getPrayerTimes(c, roundoff(latitude),
					roundoff(longitude), time_zone).get(0));
			labeTVP.setTextColor(Color.BLACK);
			labeTVP.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			if (current % 2 == 0) {
				labeTVP.setBackgroundResource(R.drawable.icells_bg3);
			} else {
				labeTVP.setBackgroundResource(R.drawable.icells_bg2);
			}
			tr.addView(labeTVP);

			// Create a TextView to house the value of the after-tax income
			TextView valueTV = new TextView(this);
			// valueTV.setId(current);
			valueTV.setText(obj_pr.getPrayerTimes(c, roundoff(latitude),
					roundoff(longitude), time_zone).get(5));
			valueTV.setTextColor(Color.BLACK);
			valueTV.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));
			if (current % 2 == 0) {
				valueTV.setBackgroundResource(R.drawable.icells_bg3);
			} else {
				valueTV.setBackgroundResource(R.drawable.icells_bg2);
			}
			tr.addView(valueTV);

			// Add the TableRow to the TableLayout
			tl.addView(tr, new TableLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			c.add(Calendar.DATE, 1);

		}

	}

	@Override
	public void onBackPressed() {
		turnGPSOff();
		finish();
		moveTaskToBack(true);
	}

	public void turnGPSOff() {
		String provider = Settings.Secure.getString(ctx.getContentResolver(),
				Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		if (provider.contains("gps")) { // if gps is enabled
			final Intent poke = new Intent();
			poke.setClassName("com.android.settings",
					"com.android.settings.widget.SettingsAppWidgetProvider");
			poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
			poke.setData(Uri.parse("3"));
			this.ctx.sendBroadcast(poke);
		}
	}

}