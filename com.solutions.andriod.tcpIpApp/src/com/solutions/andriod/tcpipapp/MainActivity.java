package com.solutions.andriod.tcpipapp;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.solutions.protocols.ConnectionProtocol;
import com.solutions.protocols.TcpConnection;

public class MainActivity extends Activity {

	public static final String PREFS_NAME = "MyPrefsFile";
	public static final String IP_VALUE = "IP";
	public static final String PORT_VALUE = "PORT";
	private static final int RESULT_SETTINGS = 1;
	static ConnectionProtocol conn = null;
	EditText text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		text = (EditText) findViewById(R.id.Data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menu_settings:
			Intent i = new Intent(this, UserSettingsActivity.class);
			startActivityForResult(i, RESULT_SETTINGS);
			break;

		}

		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SETTINGS:
			showUserSettings();
			break;

		}

	}

	private void showUserSettings() {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		StringBuilder builder = new StringBuilder();

		builder.append("\n IP: "
				+ sharedPrefs.getString(IP_VALUE,
						getResources().getString(R.string.ip)));

		builder.append("\n PORT: test "
				+ sharedPrefs.getString(PORT_VALUE,
						getResources().getString(R.string.port)));

	}

	public void onClick(View view) throws NumberFormatException, IOException {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		switch (view.getId()) {
		case R.id.Connect:
			System.out.println("Trying to connect");

			System.out.println("connected");
			break;
		case R.id.Send:
			System.out.println("Test2");
			String ip = sharedPrefs.getString(IP_VALUE, getResources()
					.getString(R.string.ip));
			String port = sharedPrefs.getString(PORT_VALUE, getResources()
					.getString(R.string.port));
			if (conn == null)
				conn = new TcpConnection(ip, Integer.parseInt(port));
			if (conn.isClose()) {
				conn = new TcpConnection(ip, Integer.parseInt(port));
			}

			conn.sendData(text.getText().toString());
			conn.closeConnection();
			break;
		}
	}
}
