/**
 * 
 */
package com.solutions.andriod.tcpipapp;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author farouk
 * 
 */
public class UserSettingsActivity extends PreferenceActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.preferences);

	}

}
