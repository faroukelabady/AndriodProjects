/**
 * 
 */
package com.solutions.andriod.tcpipapp;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * @author faroukElabady
 * 
 */
public class NonFocusedScrollView extends ScrollView {

	public NonFocusedScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public NonFocusedScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NonFocusedScrollView(Context context) {
		super(context);
	}

	@Override
	public ArrayList<View> getFocusables(int direction) {
		return new ArrayList<View>();
	}
}
