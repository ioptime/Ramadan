package ramdan.alarm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hijri.HijriCalTest;

public class ramdan extends Activity {
	/** Called when the activity is first created. */

	TextView times;
	TextView names;

	ArrayList<String> time_array;
	ArrayList<String> prayer_names;
	Context ctx;
	double time_zone = 0;
	double longitude;
	double latitude;

	boolean network_enabled;
	List<String> configuration_list;
	UserDataSource obj = new UserDataSource(this);

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ctx = this;
		final Button settings = (Button) findViewById(R.id.settings);
		final Button month_view = (Button) findViewById(R.id.month);
		final Button home_view = (Button) findViewById(R.id.home);
		final TextView welcome_date = (TextView) findViewById(R.id.welcome_date);
		final Button dua_view = (Button) findViewById(R.id.about);
		final Button salah_view = (Button) findViewById(R.id.salah_time);

		// //for ads/////

		// IMAdView imAdView = (IMAdView) findViewById(R.id.imAdviewmain);
		// imAdView.setRefreshInterval(20);
		// IMAdRequest adRequest = new IMAdRequest();
		// adRequest.setTestMode(false);
		// imAdView.setIMAdRequest(adRequest);

		obj.open();
		if (obj.configure_table_rows_count() != 0) {
			configuration_list = obj.getConfiguration();
			for (int i = 0; i < configuration_list.size(); i++) {
				Log.e("ahan", configuration_list.get(i));
			}
		}
		obj.close();

		times = (TextView) findViewById(R.id.sehar);
		names = (TextView) findViewById(R.id.iftar);
		PrayTime obj_pr = new PrayTime();
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
		// Log.e("naye wala lat", Integer.toString( (int)
		// Double.parseDouble(configuration_list.get(4))));
		// Log.e("naye wala long", Integer.toString( (int)
		// Double.parseDouble(configuration_list.get(5))));
		longitude = Double.parseDouble(configuration_list.get(4));
		latitude = Double.parseDouble(configuration_list.get(5));
		Calendar c2 = Calendar.getInstance();
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		String dayOfTheWeek = sdf.format(c2.getTime());
		SimpleDateFormat month_date = new SimpleDateFormat("MMMMMMMMM");
		String month_name = month_date.format(c2.getTime());
		int m = c.get(Calendar.MONTH) + 1;
		HijriCalTest hct = new HijriCalTest();
		welcome_date.setText(dayOfTheWeek + " , " + month_name + " "
				+ c.get(Calendar.DATE) + " , " + c.get(Calendar.YEAR));
		welcome_date
				.append("\n"
						+ hct.Islamicdate(c.get(Calendar.DATE), m,
								c.get(Calendar.YEAR)));
		Log.d("--lat long--", "--" + roundoff(latitude) + "--"
				+ roundoff(longitude));
		time_array = obj_pr.getPrayerTimes(c, roundoff(latitude),
				roundoff(longitude), time_zone);
		prayer_names = obj_pr.getTimeNames();
		// AnalogClock ac = (AnalogClock) findViewById(R.id.analogClock1);
		// for (int i = 0; i < time_array.size(); i++) {
		// }
		times.append("Sehar " + time_array.get(0).toString());
		names.append("Iftar " + time_array.get(4).toString());

		settings.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(ramdan.this,
						timing_configuration_screen.class);
				finish();
				startActivity(spinneract);
			}
		});
		month_view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(ramdan.this, month_timings.class);
				finish();
				startActivity(spinneract);
			}
		});
		home_view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(ramdan.this, ramdan.class);
				finish();
				startActivity(spinneract);
			}
		});
		dua_view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(ramdan.this, dua.class);
				finish();
				startActivity(spinneract);
			}
		});
		salah_view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(ramdan.this, salah_timing.class);
				finish();
				startActivity(spinneract);
			}
		});
		// times.append(Integer.toString(longitude) + "\n");

	}

	private Double roundoff(double value) {
		return (double) Math.round(value * 10000) / 10000;
	}

	@Override
	public void onBackPressed() {

		finish();
		moveTaskToBack(true);
	}

}