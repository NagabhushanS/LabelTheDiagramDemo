//@author: Nagabhushan S B

package com.example.labelthediagram;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;


// class for customizing the drag shadow
public class MyDragShadowBuilder extends View.DragShadowBuilder {
	
	// define drawable for the shadow of the label
	private static Drawable shadow;

	//constructor
	public MyDragShadowBuilder(View v) {
		super(v);
		shadow = new ColorDrawable(Color.RED);
	}

	//overrided method
	@Override
	public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
		super.onProvideShadowMetrics(shadowSize, shadowTouchPoint);

		//define the variables
		int height, width;

		//obtain height and width
		height = getView().getHeight();
		width = getView().getWidth()/4;  // divided by 4 to reduce the width
		
		
        // define the bounds of the color drawable representing the shadow of the label to be labelled
		shadow.setBounds(0, 0, width, height);

		// define the size of shadow of the label
		shadowSize.set(width, height);

		//define the location of touch point to be at the center of the label shadow
		shadowTouchPoint.set(width / 2, height / 2);
	}

	@Override
	public void onDrawShadow(Canvas canvas) {

		// Draws the ColorDrawable in the Canvas passed in from the system.
		shadow.draw(canvas);
	}

}
