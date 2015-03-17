package com.example.labelthediagram;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

public class MyDragShadowBuilder extends View.DragShadowBuilder {
	private static Drawable shadow;

	public MyDragShadowBuilder(View v) {
		super(v);
		shadow = new ColorDrawable(Color.RED);
	}

	@Override
	public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
		super.onProvideShadowMetrics(shadowSize, shadowTouchPoint);

		int height, width;

		height = getView().getHeight();
		width = getView().getWidth()/4;

		shadow.setBounds(0, 0, width, height);

		shadowSize.set(width, height);

		shadowTouchPoint.set(width / 2, height / 2);
	}

	@Override
	public void onDrawShadow(Canvas canvas) {

		// Draws the ColorDrawable in the Canvas passed in from the system.
		shadow.draw(canvas);
	}

}
