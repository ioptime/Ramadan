package ramdan.alarm;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ImageView;

public class welcome extends Activity {
	public static String ip, port, id;
	private UserDataSource obj = new UserDataSource(this);

	timing_configuration_model obj2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
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
		//
		// WifiManager wifiManager = (WifiManager) this
		// .getSystemService(Context.WIFI_SERVICE);
		// if (!wifiManager.isWifiEnabled()) {
		// wifiManager.setWifiEnabled(true);
		// }

		/** set time to splash out */
		Bundle extras = getIntent().getExtras();
		ImageView iv = (ImageView) findViewById(R.id.image);
		final int welcomeScreenDisplay = 3000;
		/** create a thread to show splash up to splash time */
		Thread welcomeThread = new Thread() {

			int wait = 0;

			@Override
			public void run() {
				try {
					super.run();
					/**
					 * use while to get the splash time. Use sleep() to increase
					 * the wait variable for every 100L.
					 */
					while (wait < welcomeScreenDisplay) {
						sleep(100);
						wait += 100;
					}
				} catch (Exception e) {
					System.out.println("EXc=" + e);
				} finally {
					/**
					 * Called after splash times up. Do some action after splash
					 * times up. Here we moved to another main activity class
					 */
				}
				obj.open();
				if (obj.configure_table_rows_count() == 0) {

					Intent spinneract = new Intent(welcome.this,
							timing_configuration_screen.class);
					finish();
					startActivity(spinneract);
				}
				if (obj.configure_table_rows_count() != 0) {

					Intent spinneract = new Intent(welcome.this, ramdan.class);
					finish();
					startActivity(spinneract);

				}
				obj.close();

			}
		};
		welcomeThread.start();

	}
}