package com.solutions.andriod.tcpipapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	}

	public void onClick(View view) {

		EditText email = (EditText) findViewById(R.id.email);
		EditText password = (EditText) findViewById(R.id.password);

		if (!email.getText().toString().equals("7elol")
				|| !password.getText().toString().equals("1234")) {

			Toast.makeText(this, "Please enter a valid username and password",
					Toast.LENGTH_LONG).show();
			return;
		}

		System.out.print("here before changind activity");
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.settings, menu);
	// return true;
	// }

}
