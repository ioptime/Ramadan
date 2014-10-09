package ramdan.alarm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class dua extends Activity {
	Context ctx;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dua);
		ctx = this;
		final Button settings = (Button) findViewById(R.id.settings);
		final Button month_view = (Button) findViewById(R.id.month);
		final Button home_view = (Button) findViewById(R.id.home);
		final Button dua_view = (Button) findViewById(R.id.about);
		final Button salah_view = (Button) findViewById(R.id.salah_time);

		settings.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(dua.this,
						timing_configuration_screen.class);
				finish();
				startActivity(spinneract);
			}
		});
		month_view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(dua.this, month_timings.class);
				finish();
				startActivity(spinneract);
			}
		});
		home_view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(dua.this, ramdan.class);
				finish();
				startActivity(spinneract);
			}
		});
		dua_view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(dua.this, dua.class);
				finish();
				startActivity(spinneract);
			}
		});
		salah_view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent spinneract = new Intent(dua.this, salah_timing.class);
				finish();
				startActivity(spinneract);
			}
		});
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
