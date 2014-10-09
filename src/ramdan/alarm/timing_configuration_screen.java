package ramdan.alarm;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class timing_configuration_screen extends Activity {
	UserDataSource obj = new UserDataSource(this);
	timing_configuration_model obj2 = new timing_configuration_model();
	List<String> configuration_list;
	String lat;
	String d_lat;
	String longi;
	String d_longi;
	Context ctx;
	MyLocation myLocation;
	String address;
	List<Address> addresses;
	Location location;
	Boolean wireless = false;
	ImageView done;
	CheckBox time_formate_checkbox = null;
	public TextView asar_angle_text;
	public TextView cal_method_text;
	public TextView time_zone_tex;

	public TextView save_text;

	public Spinner asar_angle_spinner;
	public Spinner time_zone_spinner;
	public Spinner cal_method_spinner;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.timing_configuration_screen);
		ctx = this;
		/* Use the LocationManager class to obtain GPS locations */
		done = (ImageView) findViewById(R.id.doneview);
		save_text = (TextView) findViewById(R.id.save_text);
		String provider = Settings.Secure.getString(getContentResolver(),
				Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

		// if (!provider.contains("gps")) { // if gps is disabled
		// final Intent poke = new Intent();
		// poke.setClassName("com.android.settings",
		// "com.android.settings.widget.SettingsAppWidgetProvider");
		// poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
		// poke.setData(Uri.parse("3"));
		// sendBroadcast(poke);
		// }
		Log.e("TAG", "HAS GPS");
		// ContentResolver cr = this.getContentResolver();
		// boolean wifiEnabled = Settings.Secure.isLocationProviderEnabled(cr,
		// LocationManager.NETWORK_PROVIDER);

		// if (!isWifiLocationEnabled(this)) {
		// buildAlertMessageNoLocation();
		//
		// }
		// done.setVisibility(View.VISIBLE);
		// save_text.setVisibility(View.VISIBLE);

		TimeZone timezone = TimeZone.getDefault();

		String TimeZoneName = timezone.getDisplayName();

		float TimeZoneOffset = (-1) * (float) new Date().getTimezoneOffset()
				/ 60;

		TimeZone tz = TimeZone.getDefault();

		Log.e("time zone",
				"My Time Zone\n" + "" + (float) new Date().getTimezoneOffset()
						/ 60);
		// TimeZoneName + " : " + String.valueOf(TimeZoneOffset));

		// final Button settings = (Button) findViewById(R.id.settings);
		// final Button month_view = (Button) findViewById(R.id.month);
		// final Button home_view = (Button) findViewById(R.id.home);
		// final Button dua_view = (Button) findViewById(R.id.about);
		// final Button salah_view = (Button) findViewById(R.id.salah_time);
		//
		// final TextView home_text = (TextView)
		// findViewById(R.id.home_text);
		// final TextView salah_text = (TextView)
		// findViewById(R.id.salah_text);
		// final TextView month_text = (TextView)
		// findViewById(R.id.month_text);
		// final TextView dua_text = (TextView)
		// findViewById(R.id.about_text);
		// final TextView settings_text = (TextView)
		// findViewById(R.id.setings_text);
		// showGpsOptions();
		asar_angle_text = (TextView) findViewById(R.id.asar_angle_textd);
		cal_method_text = (TextView) findViewById(R.id.cal_method_textd);
		time_zone_tex = (TextView) findViewById(R.id.time_zone_textd);

		// settings.setVisibility(View.GONE);
		// salah_view.setVisibility(View.GONE);
		// home_view.setVisibility(View.GONE);
		// dua_view.setVisibility(View.GONE);
		// month_view.setVisibility(View.GONE);
		//
		// settings_text.setVisibility(View.GONE);
		// salah_text.setVisibility(View.GONE);
		// home_text.setVisibility(View.GONE);
		// dua_text.setVisibility(View.GONE);
		// month_text.setVisibility(View.GONE);

		// LocationManager mlocManager = (LocationManager)
		// getSystemService(Context.LOCATION_SERVICE);
		// Criteria locationCritera = new Criteria();
		// locationCritera.setAccuracy(Criteria.ACCURACY_FINE);
		// locationCritera.setAltitudeRequired(false);
		// locationCritera.setBearingRequired(false);
		// locationCritera.setCostAllowed(true);
		// locationCritera.setPowerRequirement(Criteria.NO_REQUIREMENT);
		//
		// LocationListener mlocListener = new MyLocationListener();
		//
		// mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
		// 0,
		// 0, mlocListener);

		myLocation = new MyLocation(ctx);

		// String providerName = mlocManager
		// .getBestProvider(locationCritera, true);
		// location = mlocManager.getLastKnownLocation(providerName);

		final Button asar_angle_button = (Button) findViewById(R.id.asar_angle_button);
		final Button cal_metod_button = (Button) findViewById(R.id.cal_method_button);
		final Button time_zone_button = (Button) findViewById(R.id.time_zone_button);

		asar_angle_spinner = (Spinner) findViewById(R.id.asar_angle_spinner);

		ArrayAdapter<CharSequence> asar_angle_spinner_adapter = ArrayAdapter
				.createFromResource(this, R.array.Asar_Angle_Array,
						android.R.layout.simple_spinner_item);
		asar_angle_spinner_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		asar_angle_spinner.setAdapter(asar_angle_spinner_adapter);
		// asar_angle_spinner

		// .setOnItemSelectedListener(new asar_angle_item_selected());
		asar_angle_spinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {
						Log.e("ffffffffffgggfffffffffffff",
								"ffffffffgggfffffffffffff");
						String selected = parentView
								.getItemAtPosition(position).toString();
						if (selected.equals("Shafii")) {
							Log.e("fffffffffgggfffffffffffffff",
									"fffffffggffffffffffffff");
							asar_angle_spinner.setSelection(0);
							asar_angle_text.setText("Shafii");
							asar_angle_spinner.setVisibility(View.GONE);

						}
						if (selected.equals("Hanafi")) {
							Log.e("fffffffffffggfffffffffff",
									"ffffffffffffffffffffffff");

							asar_angle_spinner.setSelection(1);
							asar_angle_text.setText("Hanafi");
							// asar_angle_spinner.setVisibility(View.GONE);

						}

					}

					public void onNothingSelected(AdapterView<?> parentView) {
					}
				});

		cal_method_spinner = (Spinner) findViewById(R.id.cal_method_spinner);
		cal_method_spinner
				.setOnItemSelectedListener(new cal_method_item_selected());
		ArrayAdapter<CharSequence> cal_method_spinner_adapter = ArrayAdapter
				.createFromResource(this, R.array.Cal_Method_Array,
						android.R.layout.simple_spinner_item);
		cal_method_spinner_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cal_method_spinner.setAdapter(cal_method_spinner_adapter);

		time_zone_spinner = (Spinner) findViewById(R.id.time_zone_spinner);
		time_zone_spinner
				.setOnItemSelectedListener(new time_zone_item_selected());

		ArrayAdapter<CharSequence> time_zone_spinner_adapter = ArrayAdapter
				.createFromResource(this, R.array.Time_Zone_Array,
						android.R.layout.simple_spinner_item);
		time_zone_spinner_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		time_zone_spinner.setAdapter(time_zone_spinner_adapter);

		time_formate_checkbox = (CheckBox) findViewById(R.id.time_formate_box);
		obj.open();
		if (obj.configure_table_rows_count() != 0) {
			configuration_list = obj.getConfiguration();
			d_longi = configuration_list.get(4);
			d_lat = configuration_list.get(5);

			// settings.setVisibility(View.VISIBLE);
			// salah_view.setVisibility(View.VISIBLE);
			// home_view.setVisibility(View.VISIBLE);
			// dua_view.setVisibility(View.VISIBLE);
			// month_view.setVisibility(View.VISIBLE);
			//
			// settings_text.setVisibility(View.VISIBLE);
			// salah_text.setVisibility(View.VISIBLE);
			// home_text.setVisibility(View.VISIBLE);
			// dua_text.setVisibility(View.VISIBLE);
			// month_text.setVisibility(View.VISIBLE);

			if (configuration_list.get(0).equals("Shafii")) {
				asar_angle_spinner.setSelection(0);
				asar_angle_text.setText("Shafii");
			}
			if (configuration_list.get(0).equals("Hanafi")) {
				asar_angle_spinner.setSelection(1);
				asar_angle_text.setText("Hanafi");

			}
			if (configuration_list.get(1).contains("Ithna")) {
				cal_method_spinner.setSelection(0);
				cal_method_text.setText("Ithna Ashari..");
			}
			if (configuration_list.get(1).contains("Karachi")) {
				cal_method_spinner.setSelection(1);
				cal_method_text.setText("Karachi...");

			}
			if (configuration_list.get(1).contains("ISNA")) {
				cal_method_spinner.setSelection(2);
				cal_method_text.setText("ISNA...");

			}
			if (configuration_list.get(1).contains("MWL")) {
				cal_method_spinner.setSelection(3);
				cal_method_text.setText("MWL...");

			}
			if (configuration_list.get(1).contains("Makkah")) {
				cal_method_spinner.setSelection(4);
				cal_method_text.setText("Makkah...");

			}
			if (configuration_list.get(1).contains("Egyptian")) {
				cal_method_spinner.setSelection(5);
				cal_method_text.setText("Egyptian...");

			}
			if (configuration_list.get(1).contains("Tehran")) {
				cal_method_spinner.setSelection(6);
				cal_method_text.setText("Tehran...");

			}
			if (configuration_list.get(2).equals("GMT -12:00")) {
				time_zone_spinner.setSelection(0);
				time_zone_tex.setText("GMT -12:00");
			}
			if (configuration_list.get(2).equals("GMT -11:00")) {
				time_zone_spinner.setSelection(1);
				time_zone_tex.setText("GMT -11:00");

			}
			if (configuration_list.get(2).equals("GMT -10:00")) {
				time_zone_spinner.setSelection(2);
				time_zone_tex.setText("GMT -10:00");

			}
			if (configuration_list.get(2).equals("GMT -9:00")) {
				time_zone_spinner.setSelection(3);
				time_zone_tex.setText("GMT -9:00");

			}
			if (configuration_list.get(2).equals("GMT -8:00")) {
				time_zone_spinner.setSelection(4);
				time_zone_tex.setText("GMT -8:00");

			}
			if (configuration_list.get(2).equals("GMT -7:00")) {
				time_zone_spinner.setSelection(5);
				time_zone_tex.setText("GMT -7:00");

			}
			if (configuration_list.get(2).equals("GMT -6:00")) {
				time_zone_spinner.setSelection(6);
				time_zone_tex.setText("GMT -6:00");

			}
			if (configuration_list.get(2).equals("GMT -5:00")) {
				time_zone_spinner.setSelection(7);
				time_zone_tex.setText("GMT -5:00");

			}
			if (configuration_list.get(2).equals("GMT -4:30")) {
				time_zone_spinner.setSelection(8);
				time_zone_tex.setText("GMT -4:30");

			}
			if (configuration_list.get(2).equals("GMT -4:00")) {
				time_zone_spinner.setSelection(9);
				time_zone_tex.setText("GMT -4:00");

			}
			if (configuration_list.get(2).equals("GMT -3:30")) {
				time_zone_spinner.setSelection(10);
				time_zone_tex.setText("GMT -3:30");

			}
			if (configuration_list.get(2).equals("GMT -3:00")) {
				time_zone_spinner.setSelection(11);
				time_zone_tex.setText("GMT -3:00");

			}
			if (configuration_list.get(2).equals("GMT -2:00")) {
				time_zone_spinner.setSelection(12);
				time_zone_tex.setText("GMT -2:00");

			}
			if (configuration_list.get(2).equals("GMT -1:00")) {
				time_zone_spinner.setSelection(13);
				time_zone_tex.setText("GMT -1:00");

			}
			if (configuration_list.get(2).equals("GMT 0:00")) {
				time_zone_spinner.setSelection(14);
				time_zone_tex.setText("GMT 0:00");

			}
			if (configuration_list.get(2).equals("GMT 1:00")) {
				time_zone_spinner.setSelection(15);
				time_zone_tex.setText("GMT 1:00");

			}
			if (configuration_list.get(2).equals("GMT 2:00")) {
				time_zone_spinner.setSelection(16);
				time_zone_tex.setText("GMT 2:00");

			}
			if (configuration_list.get(2).equals("GMT 3:00")) {
				time_zone_spinner.setSelection(17);
				time_zone_tex.setText("GMT 3:00");

			}
			if (configuration_list.get(2).equals("GMT 3:30")) {
				time_zone_spinner.setSelection(18);
				time_zone_tex.setText("GMT 3:30");

			}
			if (configuration_list.get(2).equals("GMT 4:00")) {
				time_zone_spinner.setSelection(19);
				time_zone_tex.setText("GMT 4:00");

			}
			if (configuration_list.get(2).equals("GMT 4:30")) {
				time_zone_spinner.setSelection(20);
				time_zone_tex.setText("GMT 4:30");

			}
			if (configuration_list.get(2).equals("GMT 5:00")) {
				time_zone_spinner.setSelection(21);
				time_zone_tex.setText("GMT 5:00");

			}
			if (configuration_list.get(2).equals("GMT 5:30")) {
				time_zone_spinner.setSelection(22);
				time_zone_tex.setText("GMT 5:30");

			}
			if (configuration_list.get(2).equals("GMT 6:00")) {
				time_zone_spinner.setSelection(23);
				time_zone_tex.setText("GMT 6:00");

				;
			}
			if (configuration_list.get(2).equals("GMT 7:00")) {
				time_zone_spinner.setSelection(24);
				time_zone_tex.setText("GMT 7:00");

			}
			if (configuration_list.get(2).equals("GMT 8:00")) {
				time_zone_spinner.setSelection(25);
				time_zone_tex.setText("GMT 8:00");

			}
			if (configuration_list.get(2).equals("GMT 9:00")) {
				time_zone_spinner.setSelection(26);
				time_zone_tex.setText("GMT 9:00");

			}
			if (configuration_list.get(2).equals("GMT 9:30")) {
				time_zone_spinner.setSelection(27);
				time_zone_tex.setText("GMT 9:30");

			}
			if (configuration_list.get(2).equals("GMT 10:00")) {
				time_zone_spinner.setSelection(28);
				time_zone_tex.setText("GMT 10:00");

			}
			if (configuration_list.get(2).equals("GMT 11:00")) {
				time_zone_spinner.setSelection(29);
				time_zone_tex.setText("GMT 11:00");

			}
			if (configuration_list.get(2).equals("GMT 12:00")) {
				time_zone_spinner.setSelection(30);
				time_zone_tex.setText("GMT 12:00");

			}
			if (configuration_list.get(3).equals("24")) {
				time_formate_checkbox.setChecked(true);
			}
			if (configuration_list.get(3).equals("12")) {
				time_formate_checkbox.setChecked(false);
			}
		}
		if (android.text.format.DateFormat
				.is24HourFormat(timing_configuration_screen.this)
				&& obj.configure_table_rows_count() == 0) {
			time_formate_checkbox.setChecked(true);
		}
		if (obj.configure_table_rows_count() == 0) {
			cal_method_text.setText("Ithna Ashari..");
			asar_angle_text.setText("Shafi");
			if (String.valueOf(TimeZoneOffset).equals("-12")
					|| String.valueOf(TimeZoneOffset).equals("-12.0")) {
				time_zone_spinner.setSelection(0);
				time_zone_tex.setText("GMT -12:00");
			}
			if (String.valueOf(TimeZoneOffset).equals("-11")
					|| String.valueOf(TimeZoneOffset).equals("-11.0")) {
				time_zone_spinner.setSelection(1);
				time_zone_tex.setText("GMT -11:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("-10")
					|| String.valueOf(TimeZoneOffset).equals("-10.0")) {
				time_zone_spinner.setSelection(2);
				time_zone_tex.setText("GMT -10:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("-9")
					|| String.valueOf(TimeZoneOffset).equals("-9.0")) {
				time_zone_spinner.setSelection(3);
				time_zone_tex.setText("GMT -9:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("-8")
					|| String.valueOf(TimeZoneOffset).equals("-8.0")) {
				time_zone_spinner.setSelection(4);
				time_zone_tex.setText("GMT -8:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("-7")
					|| String.valueOf(TimeZoneOffset).equals("-7.0")) {
				time_zone_spinner.setSelection(5);
				time_zone_tex.setText("GMT -7:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("-6")
					|| String.valueOf(TimeZoneOffset).equals("-6.0")) {
				time_zone_spinner.setSelection(6);
				time_zone_tex.setText("GMT -6:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("-5")
					|| String.valueOf(TimeZoneOffset).equals("-5.0")) {
				time_zone_spinner.setSelection(7);
				time_zone_tex.setText("GMT -5:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("-4.50")
					|| String.valueOf(TimeZoneOffset).equals("-4.5")) {
				time_zone_spinner.setSelection(8);
				time_zone_tex.setText("GMT -4:30");

			}
			if (String.valueOf(TimeZoneOffset).equals("-4")
					|| String.valueOf(TimeZoneOffset).equals("-4.0")) {
				time_zone_spinner.setSelection(9);
				time_zone_tex.setText("GMT -4:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("-3.5")
					|| String.valueOf(TimeZoneOffset).equals("-3.50")) {
				time_zone_spinner.setSelection(10);
				time_zone_tex.setText("GMT -3:30");

			}
			if (String.valueOf(TimeZoneOffset).equals("-3")
					|| String.valueOf(TimeZoneOffset).equals("-3.0")) {
				time_zone_spinner.setSelection(11);
				time_zone_tex.setText("GMT -3:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("-2")
					|| String.valueOf(TimeZoneOffset).equals("-2.0")) {
				time_zone_spinner.setSelection(12);
				time_zone_tex.setText("GMT -2:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("-1")
					|| String.valueOf(TimeZoneOffset).equals("-1.0")) {
				time_zone_spinner.setSelection(13);
				time_zone_tex.setText("GMT -1:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("0")
					|| String.valueOf(TimeZoneOffset).equals("0.0")) {
				time_zone_spinner.setSelection(14);
				time_zone_tex.setText("GMT 0:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("1")
					|| String.valueOf(TimeZoneOffset).equals("1.0")) {
				time_zone_spinner.setSelection(15);
				time_zone_tex.setText("GMT 1:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("2")
					|| String.valueOf(TimeZoneOffset).equals("2.0")) {
				time_zone_spinner.setSelection(16);
				time_zone_tex.setText("GMT 2:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("3")
					|| String.valueOf(TimeZoneOffset).equals("3.0")) {
				time_zone_spinner.setSelection(17);
				time_zone_tex.setText("GMT 3:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("3.5")
					|| String.valueOf(TimeZoneOffset).equals("3.50")) {
				time_zone_spinner.setSelection(18);
				time_zone_tex.setText("GMT 3:30");

			}
			if (String.valueOf(TimeZoneOffset).equals("4")
					|| String.valueOf(TimeZoneOffset).equals("4.0")) {
				time_zone_spinner.setSelection(19);
				time_zone_tex.setText("GMT 4:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("4.5")
					|| String.valueOf(TimeZoneOffset).equals("4.50")) {
				time_zone_spinner.setSelection(20);
				time_zone_tex.setText("GMT 4:30");

			}
			if (String.valueOf(TimeZoneOffset).equals("5")
					|| String.valueOf(TimeZoneOffset).equals("5.0")) {
				time_zone_spinner.setSelection(21);
				time_zone_tex.setText("GMT 5:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("5.5")
					|| String.valueOf(TimeZoneOffset).equals("5.50")) {
				time_zone_spinner.setSelection(22);
				time_zone_tex.setText("GMT 5:30");

			}
			if (String.valueOf(TimeZoneOffset).equals("6")
					|| String.valueOf(TimeZoneOffset).equals("6.0")) {
				time_zone_spinner.setSelection(23);
				time_zone_tex.setText("GMT 6:00");
			}
			if (String.valueOf(TimeZoneOffset).equals("7")
					|| String.valueOf(TimeZoneOffset).equals("7.0")) {
				time_zone_spinner.setSelection(24);
				time_zone_tex.setText("GMT 7:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("8")
					|| String.valueOf(TimeZoneOffset).equals("8.0")) {
				time_zone_spinner.setSelection(25);
				time_zone_tex.setText("GMT 8:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("9")
					|| String.valueOf(TimeZoneOffset).equals("9.0")) {
				time_zone_spinner.setSelection(26);
				time_zone_tex.setText("GMT 9:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("9.5")
					|| String.valueOf(TimeZoneOffset).equals("9.50")) {
				time_zone_spinner.setSelection(27);
				time_zone_tex.setText("GMT 9:30");

			}
			if (String.valueOf(TimeZoneOffset).equals("10")
					|| String.valueOf(TimeZoneOffset).equals("10.0")) {
				time_zone_spinner.setSelection(28);
				time_zone_tex.setText("GMT 10:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("11")
					|| String.valueOf(TimeZoneOffset).equals("11.0")) {
				time_zone_spinner.setSelection(29);
				time_zone_tex.setText("GMT 11:00");

			}
			if (String.valueOf(TimeZoneOffset).equals("12")
					|| String.valueOf(TimeZoneOffset).equals("12.0")) {
				time_zone_spinner.setSelection(30);
				time_zone_tex.setText("GMT 12:00");

			}
		}
		asar_angle_spinner.setVisibility(View.GONE);
		cal_method_spinner.setVisibility(View.GONE);

		obj.close();

		asar_angle_button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				asar_angle_text.setText("");
				cal_method_text.setText("");
				time_zone_tex.setText("");
				// asar_angle_spinner.setVisibility(View.VISIBLE);
				findViewById(R.id.asar_angle_spinner).performClick();
			}
		});
		cal_metod_button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				asar_angle_text.setText("");
				cal_method_text.setText("");
				time_zone_tex.setText("");
				findViewById(R.id.cal_method_spinner).performClick();
			}
		});
		time_zone_button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				asar_angle_text.setText("");
				cal_method_text.setText("");
				time_zone_tex.setText("");

				findViewById(R.id.time_zone_spinner).performClick();
			}
		});
		// settings.setOnClickListener(new View.OnClickListener() {
		//
		// public void onClick(View v) {
		// Intent spinneract = new Intent(
		// timing_configuration_screen.this,
		// timing_configuration_screen.class);
		// finish();
		// startActivity(spinneract);
		// }
		// });
		// month_view.setOnClickListener(new View.OnClickListener() {
		//
		// public void onClick(View v) {
		// Intent spinneract = new Intent(
		// timing_configuration_screen.this, month_timings.class);
		// finish();
		// startActivity(spinneract);
		// }
		// });
		// home_view.setOnClickListener(new View.OnClickListener() {
		//
		// public void onClick(View v) {
		// Intent spinneract = new Intent(
		// timing_configuration_screen.this, ramdan.class);
		// finish();
		// startActivity(spinneract);
		// }
		// });
		// dua_view.setOnClickListener(new View.OnClickListener() {
		//
		// public void onClick(View v) {
		// Intent spinneract = new Intent(
		// timing_configuration_screen.this, dua.class);
		// finish();
		// startActivity(spinneract);
		// }
		// });
		// salah_view.setOnClickListener(new View.OnClickListener() {
		//
		// public void onClick(View v) {
		// Intent spinneract = new Intent(
		// timing_configuration_screen.this, salah_timing.class);
		// finish();
		// startActivity(spinneract);
		// }
		// });

	}

	public class asar_angle_item_selected implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			Log.e("kiaaa", "kiaaa");
			String selected = parent.getItemAtPosition(pos).toString();
			if (selected.equals("Shafii")) {
				asar_angle_spinner.setSelection(0);
				asar_angle_text.setText("Shafii");
			}
			if (selected.equals("Hanafi")) {
				asar_angle_spinner.setSelection(1);
				asar_angle_text.setText("Hanafi");

			}

		}

		public void onNothingSelected(AdapterView parent) {
			// Do nothing.
		}

	}

	public class cal_method_item_selected implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			String selected = parent.getItemAtPosition(pos).toString();
			if (configuration_list.get(1).contains("Ithna")) {
				cal_method_spinner.setSelection(0);
				cal_method_text.setText("Ithna Ashari..");
			}
			if (configuration_list.get(1).contains("Karachi")) {
				cal_method_spinner.setSelection(1);
				cal_method_text.setText("Karachi...");

			}
			if (configuration_list.get(1).contains("ISNA")) {
				cal_method_spinner.setSelection(2);
				cal_method_text.setText("ISNA...");

			}
			if (configuration_list.get(1).contains("MWL")) {
				cal_method_spinner.setSelection(3);
				cal_method_text.setText("MWL...");

			}
			if (configuration_list.get(1).contains("Makkah")) {
				cal_method_spinner.setSelection(4);
				cal_method_text.setText("Makkah...");

			}
			if (configuration_list.get(1).contains("Egyptian")) {
				cal_method_spinner.setSelection(5);
				cal_method_text.setText("Egyptian...");

			}
			if (configuration_list.get(1).contains("Tehran")) {
				cal_method_spinner.setSelection(6);
				cal_method_text.setText("Tehran...");

			}
		}

		public void onNothingSelected(AdapterView parent) {
			// Do nothing.
		}
	}

	public class time_zone_item_selected implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			String selected = parent.getItemAtPosition(pos).toString();

			if (configuration_list.get(2).equals("GMT -12:00")) {
				time_zone_spinner.setSelection(0);
				time_zone_tex.setText("GMT -12:00");
			}
			if (configuration_list.get(2).equals("GMT -11:00")) {
				time_zone_spinner.setSelection(1);
				time_zone_tex.setText("GMT -11:00");

			}
			if (configuration_list.get(2).equals("GMT -10:00")) {
				time_zone_spinner.setSelection(2);
				time_zone_tex.setText("GMT -10:00");

			}
			if (configuration_list.get(2).equals("GMT -9:00")) {
				time_zone_spinner.setSelection(3);
				time_zone_tex.setText("GMT -9:00");

			}
			if (configuration_list.get(2).equals("GMT -8:00")) {
				time_zone_spinner.setSelection(4);
				time_zone_tex.setText("GMT -8:00");

			}
			if (configuration_list.get(2).equals("GMT -7:00")) {
				time_zone_spinner.setSelection(5);
				time_zone_tex.setText("GMT -7:00");

			}
			if (configuration_list.get(2).equals("GMT -6:00")) {
				time_zone_spinner.setSelection(6);
				time_zone_tex.setText("GMT -6:00");

			}
			if (configuration_list.get(2).equals("GMT -5:00")) {
				time_zone_spinner.setSelection(7);
				time_zone_tex.setText("GMT -5:00");

			}
			if (configuration_list.get(2).equals("GMT -4:30")) {
				time_zone_spinner.setSelection(8);
				time_zone_tex.setText("GMT -4:30");

			}
			if (configuration_list.get(2).equals("GMT -4:00")) {
				time_zone_spinner.setSelection(9);
				time_zone_tex.setText("GMT -4:00");

			}
			if (configuration_list.get(2).equals("GMT -3:30")) {
				time_zone_spinner.setSelection(10);
				time_zone_tex.setText("GMT -3:30");

			}
			if (configuration_list.get(2).equals("GMT -3:00")) {
				time_zone_spinner.setSelection(11);
				time_zone_tex.setText("GMT -3:00");

			}
			if (configuration_list.get(2).equals("GMT -2:00")) {
				time_zone_spinner.setSelection(12);
				time_zone_tex.setText("GMT -2:00");

			}
			if (configuration_list.get(2).equals("GMT -1:00")) {
				time_zone_spinner.setSelection(13);
				time_zone_tex.setText("GMT -1:00");

			}
			if (configuration_list.get(2).equals("GMT 0:00")) {
				time_zone_spinner.setSelection(14);
				time_zone_tex.setText("GMT 0:00");

			}
			if (configuration_list.get(2).equals("GMT 1:00")) {
				time_zone_spinner.setSelection(15);
				time_zone_tex.setText("GMT 1:00");

			}
			if (configuration_list.get(2).equals("GMT 2:00")) {
				time_zone_spinner.setSelection(16);
				time_zone_tex.setText("GMT 2:00");

			}
			if (configuration_list.get(2).equals("GMT 3:00")) {
				time_zone_spinner.setSelection(17);
				time_zone_tex.setText("GMT 3:00");

			}
			if (configuration_list.get(2).equals("GMT 3:30")) {
				time_zone_spinner.setSelection(18);
				time_zone_tex.setText("GMT 3:30");

			}
			if (configuration_list.get(2).equals("GMT 4:00")) {
				time_zone_spinner.setSelection(19);
				time_zone_tex.setText("GMT 4:00");

			}
			if (configuration_list.get(2).equals("GMT 4:30")) {
				time_zone_spinner.setSelection(20);
				time_zone_tex.setText("GMT 4:30");

			}
			if (configuration_list.get(2).equals("GMT 5:00")) {
				time_zone_spinner.setSelection(21);
				time_zone_tex.setText("GMT 5:00");

			}
			if (configuration_list.get(2).equals("GMT 5:30")) {
				time_zone_spinner.setSelection(22);
				time_zone_tex.setText("GMT 5:30");

			}
			if (configuration_list.get(2).equals("GMT 6:00")) {
				time_zone_spinner.setSelection(23);
				time_zone_tex.setText("GMT 6:00");

				;
			}
			if (configuration_list.get(2).equals("GMT 7:00")) {
				time_zone_spinner.setSelection(24);
				time_zone_tex.setText("GMT 7:00");

			}
			if (configuration_list.get(2).equals("GMT 8:00")) {
				time_zone_spinner.setSelection(25);
				time_zone_tex.setText("GMT 8:00");

			}
			if (configuration_list.get(2).equals("GMT 9:00")) {
				time_zone_spinner.setSelection(26);
				time_zone_tex.setText("GMT 9:00");

			}
			if (configuration_list.get(2).equals("GMT 9:30")) {
				time_zone_spinner.setSelection(27);
				time_zone_tex.setText("GMT 9:30");

			}
			if (configuration_list.get(2).equals("GMT 10:00")) {
				time_zone_spinner.setSelection(28);
				time_zone_tex.setText("GMT 10:00");

			}
			if (configuration_list.get(2).equals("GMT 11:00")) {
				time_zone_spinner.setSelection(29);
				time_zone_tex.setText("GMT 11:00");

			}
			if (configuration_list.get(2).equals("GMT 12:00")) {
				time_zone_spinner.setSelection(30);
				time_zone_tex.setText("GMT 12:00");

			}
		}

		public void onNothingSelected(AdapterView parent) {
			// Do nothing.
		}
	}

	/* Class My Location Listener */
	public class MyLocationListener implements LocationListener {

		public void onLocationChanged(Location loc) {

			lat = Double.toString(loc.getLatitude());
			longi = Double.toString(loc.getLongitude());
			/*
			 * Geocoder gcd = new Geocoder(getBaseContext(),
			 * Locale.getDefault());
			 * 
			 * try { addresses = gcd.getFromLocation(loc.getLatitude(),
			 * loc.getLongitude(), 1); } catch (IOException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); } if
			 * (addresses.size() > 0) {
			 * Log.d("--addresses--",addresses.get(0).getLocality());
			 * address=addresses.get(0).getLocality(); } String Text =
			 * "My current location is: " + "Latitud = " + loc.getLatitude() +
			 * "Longitud = " + loc.getLongitude(); Log.d("--location change--",
			 * "" + Text); // Toast.makeText(getApplicationContext(), Text,
			 * Toast.LENGTH_SHORT) // .show();
			 */
		}

		public void onProviderDisabled(String provider) {
			// Toast.makeText(getApplicationContext(), "Gps Disabled",
			// Toast.LENGTH_SHORT).show();
		}

		public void onProviderEnabled(String provider) {
			// Toast.makeText(getApplicationContext(), "Gps Enabled",
			// Toast.LENGTH_SHORT).show();
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

	}

	private static boolean isWifiLocationEnabled(Context context) {
		ContentResolver cr = context.getContentResolver();
		String enabledProviders = Settings.Secure.getString(cr,
				Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		if (!TextUtils.isEmpty(enabledProviders)) {
			// not the fastest way to do that :)
			String[] providersList = TextUtils.split(enabledProviders, ",");
			for (String provider : providersList) {
				if (LocationManager.NETWORK_PROVIDER.equals(provider)) {
					return true;
				}
			}
		}
		return false;
	}

	private void buildAlertMessageNoLocation() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(
				"You should Enable 'Use Wireless Network' for better prediction of timing,Enable now")
				.setCancelable(false)
				.setPositiveButton("Enable",
						new DialogInterface.OnClickListener() {
							public void onClick(
									@SuppressWarnings("unused") final DialogInterface dialog,
									@SuppressWarnings("unused") final int id) {
								Intent intent = new Intent(
										Settings.ACTION_LOCATION_SOURCE_SETTINGS);
								startActivity(intent);
							}
						});
		final AlertDialog alert = builder.create();
		alert.show();
	}

	private boolean haveNetworkConnection() {
		boolean haveConnectedWifi = false;
		boolean haveConnectedMobile = false;

		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected())
					haveConnectedWifi = true;
			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected())
					haveConnectedMobile = true;
		}
		return haveConnectedWifi || haveConnectedMobile;
	}

	@TargetApi(Build.VERSION_CODES.FROYO)
	public void done(View v) {

		if (haveNetworkConnection()) {
			ContentResolver cr = ctx.getContentResolver();
			Boolean wifiEnabled = Settings.Secure.isLocationProviderEnabled(cr,
					LocationManager.NETWORK_PROVIDER);
			if (wifiEnabled) {
				myLocation = new MyLocation(ctx);
				lat = Double.toString(myLocation.latitude);
				longi = Double.toString(myLocation.longitude);
				String asar_angle = asar_angle_spinner.getSelectedItem()
						.toString();
				String cal_method = cal_method_spinner.getSelectedItem()
						.toString();
				String time_zone = time_zone_spinner.getSelectedItem()
						.toString();
				String time_formate = null;
				if (time_formate != null || time_formate != "") {
					if (time_formate_checkbox.isChecked() == true) {
						time_formate = "24";
					} else {
						time_formate = "12";
					}
				}
				if (time_formate == null || time_formate == "") {
					if (android.text.format.DateFormat
							.is24HourFormat(timing_configuration_screen.this)) {
						time_formate = "24";
					} else {
						time_formate = "12";
					}
				}

				obj.open();
				UserDataSource.reset_configure_table();
				obj2.setAsar_angle(asar_angle);
				obj2.setCal_method(cal_method);
				obj2.setTime_zone(time_zone);
				obj2.setTime_formate(time_formate);

				if (longi == null || lat == null) {
					if (d_lat != null && d_longi != null) {
						obj2.setLat(d_longi);
						obj2.setLongi(d_lat);
					} else if (d_lat == null && d_longi == null) {

						obj2.setLat("39.6667");
						obj2.setLongi("24.4527");
						obj2.setTime_zone("GMT 3:00");
						AlertDialog.Builder builder = new AlertDialog.Builder(
								this);
						builder.setMessage(
								"There was a problem with your GPS/WIFI connectivity. Your timing are set to Madinah. Refresg settings soon to get local time")
								.setCancelable(false)
								.setPositiveButton("OK",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int id) {
												Intent spinneract = new Intent(
														timing_configuration_screen.this,
														ramdan.class);
												finish();
												obj.insert_configure(obj2);
												obj.close();
												startActivity(spinneract);

											}
										});
						final AlertDialog alert = builder.create();
						alert.show();

					}

				} else {
					obj2.setLat(longi);
					obj2.setLongi(lat);
				}

				// obj2.setLat(longi);
				// obj2.setLongi(lat);

				obj.insert_configure(obj2);
				obj.close();
				// going to other screen
				Intent spinneract = new Intent(
						timing_configuration_screen.this, ramdan.class);
				finish();
				startActivity(spinneract);

				// ((Spinner)
				// findViewById(R.id.asar_angle_spinner)).performClick();
			} else {
				buildAlertMessageNoLocation();
			}
		}

		else

		{
			showSettingsAlert();
		}
	}

	public void showSettingsAlertWIFI_in_Location() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);

		// Setting Dialog Message
		alertDialog
				.setMessage("Please enable WIFI and mobile network location/Google Location services in access to my location settings.");

		// On pressing Settings button
		alertDialog.setNegativeButton("Settings",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(
								Settings.ACTION_LOCATION_SOURCE_SETTINGS);
						ctx.startActivity(intent);
					}
				});

		alertDialog.setPositiveButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

		alertDialog.show();
	}

	@Override
	public void onBackPressed() {

		finish();
		moveTaskToBack(true);
	}

	public void showSettingsAlert() {

		AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(ctx);

		// Setting Dialog Message
		alertDialog2
				.setMessage("Please turn on your WIFI or Mobile Data in Settings.");

		// On pressing Settings button
		alertDialog2.setNegativeButton("Settings",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(
								Settings.ACTION_WIRELESS_SETTINGS);
						ctx.startActivity(intent);
					}
				});

		// on pressing cancel button
		alertDialog2.setPositiveButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

		// Showing Alert Message
		alertDialog2.show();
	}
	/*
	 * private void turnGPSOff() {
	 * 
	 * AlertDialog.Builder builder = new AlertDialog.Builder(this);
	 * builder.setMessage("You should disable GPS now to save battery life")
	 * .setCancelable(false) .setPositiveButton("Yes", new
	 * DialogInterface.OnClickListener() { public void onClick(DialogInterface
	 * dialog, int id) { Intent intent = new Intent(
	 * Settings.ACTION_LOCATION_SOURCE_SETTINGS); startActivityForResult(intent,
	 * 11); } }) .setNegativeButton("No", new DialogInterface.OnClickListener()
	 * { public void onClick(DialogInterface dialog, int id) {
	 * 
	 * dialog.cancel(); Intent spinneract = new Intent(
	 * timing_configuration_screen.this, ramdan.class); finish();
	 * startActivity(spinneract);
	 * 
	 * } }); final AlertDialog alert = builder.create(); alert.show();
	 * 
	 * }
	 * 
	 * protected void onActivityResult(int requestCode, int resultCode, Intent
	 * data) { Log.d("finished", "in fun");
	 * 
	 * switch (requestCode) { case 11: { Log.d("finished", "in request"); Intent
	 * spinneract = new Intent(timing_configuration_screen.this, ramdan.class);
	 * finish(); startActivity(spinneract);
	 * 
	 * } } }
	 */
}