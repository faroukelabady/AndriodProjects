package com.solutions.andriod.tcpipapp;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.solutions.protocols.ConnectionProtocol;
import com.solutions.protocols.TcpConnection;

public class MainActivity extends Activity {

	public static final String PREFS_NAME = "MyPrefsFile";
	public static final String IP_VALUE = "IP";
	public static final String PORT_VALUE = "PORT";
	public static final String AUTO_DETECT = "AUTO_DETECT";
	public static final String EDIT_BEFORE_SEND = "EDIT_BEFORE_SEND";
	protected static final int RESULT_SPEECH = 1;
	static ConnectionProtocol conn = null;
	EditText text;
	GridLayout floorOne;
	GridLayout floorTwo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		text = (EditText) findViewById(R.id.Data);
		floorOne = (GridLayout) findViewById(R.id.firstFloor);
		floorTwo = (GridLayout) findViewById(R.id.secondFloor);
		floorOne.setVisibility(View.GONE);
		floorTwo.setVisibility(View.GONE);
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
			startActivity(i);
			break;

		}

		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);

		switch (requestCode) {
		case RESULT_SPEECH:
			if (resultCode == RESULT_OK && null != data) {

				boolean editBefore = sharedPrefs.getBoolean(EDIT_BEFORE_SEND,
						false);

				ArrayList<String> text = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

				if (editBefore) {
					this.text.setText(text.get(0));
				} else {

					String ip = sharedPrefs.getString(IP_VALUE, getResources()
							.getString(R.string.ip));
					String port = sharedPrefs.getString(PORT_VALUE,
							getResources().getString(R.string.port));

					try {
						new TcpConnection().execute(ip, port, text.get(0));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			break;

		}

	}

	public void onClick(View view) throws NumberFormatException, IOException {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		try {

			switch (view.getId()) {
			case R.id.Send:
				System.out.println("Test2");
				String ip = sharedPrefs.getString(IP_VALUE, getResources()
						.getString(R.string.ip));
				String port = sharedPrefs.getString(PORT_VALUE, getResources()
						.getString(R.string.port));

				new TcpConnection()
						.execute(ip, port, text.getText().toString());
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void lightControl(View view) {

		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		ToggleButton lightToggle = null;
		String ip = sharedPrefs.getString(IP_VALUE,
				getResources().getString(R.string.ip));
		String port = sharedPrefs.getString(PORT_VALUE, getResources()
				.getString(R.string.port));

		try {

			switch (view.getId()) {
			case R.id.light11:
				lightToggle = (ToggleButton) findViewById(R.id.light11);
				if (lightToggle.isChecked()) {
					new TcpConnection().execute(ip, port, "Home open light 11");
				} else {
					new TcpConnection()
							.execute(ip, port, "Home close light 11");
				}
				break;
			case R.id.light12:
				lightToggle = (ToggleButton) findViewById(R.id.light12);
				if (lightToggle.isChecked()) {
					new TcpConnection().execute(ip, port, "Home open light 12");
				} else {
					new TcpConnection()
							.execute(ip, port, "Home close light 12");
				}
				break;
			case R.id.light13:
				lightToggle = (ToggleButton) findViewById(R.id.light13);
				if (lightToggle.isChecked()) {
					new TcpConnection().execute(ip, port, "Home open light 13");
				} else {
					new TcpConnection()
							.execute(ip, port, "Home close light 13");
				}
				break;
			case R.id.light21:
				lightToggle = (ToggleButton) findViewById(R.id.light21);
				if (lightToggle.isChecked()) {
					new TcpConnection().execute(ip, port, "Home open light 21");
				} else {
					new TcpConnection()
							.execute(ip, port, "Home close light 21");
				}
				break;
			case R.id.light22:
				lightToggle = (ToggleButton) findViewById(R.id.light22);
				if (lightToggle.isChecked()) {
					new TcpConnection().execute(ip, port, "Home open light 22");
				} else {
					new TcpConnection()
							.execute(ip, port, "Home close light 22");
				}
				break;
			case R.id.light23:
				lightToggle = (ToggleButton) findViewById(R.id.light23);
				if (lightToggle.isChecked()) {
					new TcpConnection().execute(ip, port, "Home open light 23");
				} else {
					new TcpConnection()
							.execute(ip, port, "Home close light 23");
				}
				break;
			}

		} catch (Exception e) {
			Toast.makeText(this, "cannot connect", Toast.LENGTH_LONG).show();
			return;
		}
	}

	public void showFloors(View view) {
		switch (view.getId()) {
		case R.id.toggleFloorOne:
			Switch toggleFloorOne = (Switch) findViewById(R.id.toggleFloorOne);
			if (toggleFloorOne.isChecked()) {
				floorOne.setVisibility(View.VISIBLE);
			} else {
				floorOne.setVisibility(View.GONE);
			}
			break;
		case R.id.toggleFloorTwo:
			Switch toggleFloorTwo = (Switch) findViewById(R.id.toggleFloorTwo);
			if (toggleFloorTwo.isChecked()) {
				floorTwo.setVisibility(View.VISIBLE);
			} else {
				floorTwo.setVisibility(View.GONE);
			}
			break;
		}

	}

	public void speakBtn(View view) {

		switch (view.getId()) {
		case R.id.btnSpeak:
			Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

			intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

			try {
				startActivityForResult(intent, RESULT_SPEECH);
				text.setText("");
			} catch (ActivityNotFoundException a) {
				Toast t = Toast.makeText(getApplicationContext(),
						"Opps! Your device doesn't support Speech to Text",
						Toast.LENGTH_SHORT);
				t.show();
			}
			break;
		}
	}
}
