package com.example.labelthediagram;

import static com.example.labelthediagram.DimentionConstants.*;
import android.app.AlertDialog;
import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	TextView ammeter, voltmeter, load, battery;
	ImageView diagram;
	TextView scoreTextView;
	DisplayMetrics metrics;
	int densityDpi;
	static int score = 0;
	static int flag1 = 0, flag2 = 0, flag3 = 0, flag4 = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		metrics = getResources().getDisplayMetrics();
		densityDpi = (int) (metrics.density * 160f);
		ammeter = (TextView) findViewById(R.id.aId);
		voltmeter = (TextView) findViewById(R.id.vId);
		load = (TextView) findViewById(R.id.lId);
		battery = (TextView) findViewById(R.id.bId);
		diagram = (ImageView) findViewById(R.id.imageView1);
		scoreTextView = (TextView) findViewById(R.id.score);
		MyLongClickListener longListener = new MyLongClickListener();
		ammeter.setOnLongClickListener(longListener);
		voltmeter.setOnLongClickListener(longListener);
		battery.setOnLongClickListener(longListener);
		load.setOnLongClickListener(longListener);
		diagram.setOnDragListener(new View.OnDragListener() {

			@Override
			public boolean onDrag(View v, DragEvent event) {
				int action = event.getAction();
				float x = 0, y = 0;

				switch (action) {
				case DragEvent.ACTION_DRAG_STARTED:
					break;
				case DragEvent.ACTION_DRAG_ENTERED:

					break;
				case DragEvent.ACTION_DRAG_LOCATION:
					x = (float) (((float) event.getX()) / ((float) densityDpi) * 160.00);
					y = (float) (((float) event.getY()) / ((float) densityDpi) * 160.00);

					break;
				case DragEvent.ACTION_DRAG_EXITED:
					x = 0;
					y = 0;
					break;
				case DragEvent.ACTION_DROP:
					x = (float) (((float) event.getX()) / ((float) densityDpi) * 160.00);
					y = (float) (((float) event.getY()) / ((float) densityDpi) * 160.00);
					TextView draggedView = (TextView) event.getLocalState();
					String s = draggedView.getText().toString();
					switch (s) {
					case "Ammeter":

						if (x > ablx && x < atrx && y > ably && y < atry
								&& flag1 == 0) {
							score++;
							scoreTextView.setText("Your Score: " + score);
							flag1++;

							Toast.makeText(getApplicationContext(), "Correct",
									Toast.LENGTH_SHORT).show();

						} else if (flag1 != 0) {
							Toast.makeText(getApplicationContext(),
									"Move ahead!!!", Toast.LENGTH_SHORT).show();
						} else
							Toast.makeText(getApplicationContext(),
									"Incorrect", Toast.LENGTH_SHORT).show();

						break;

					case "Voltmeter":
						if (x > vblx && x < vtrx && y > vbly && y < vtry
								&& flag2 == 0) {
							score++;
							flag2++;
							scoreTextView.setText("Your Score: " + score);

							Toast.makeText(getApplicationContext(), "Correct",
									Toast.LENGTH_SHORT).show();

						} else if (flag2 != 0) {
							Toast.makeText(getApplicationContext(),
									"Move ahead!!!", Toast.LENGTH_SHORT).show();
						} else
							Toast.makeText(getApplicationContext(),
									"Incorrect", Toast.LENGTH_SHORT).show();

						break;
					case "Load":
						if (x > lblx && x < ltrx && y > lbly && y < ltry
								&& flag3 == 0) {
							score++;
							flag3++;
							scoreTextView.setText("Your Score: " + score);

							Toast.makeText(getApplicationContext(), "Correct",
									Toast.LENGTH_SHORT).show();

						} else if (flag3 != 0) {
							Toast.makeText(getApplicationContext(),
									"Move ahead!!!", Toast.LENGTH_SHORT).show();
						} else
							Toast.makeText(getApplicationContext(),
									"Incorrect", Toast.LENGTH_SHORT).show();

						break;
					case "Battery":
						if (x > bblx && x < btrx && y > bbly && y < btry
								&& flag4 == 0) {
							score++;
							flag4++;
							scoreTextView.setText("Your Score: " + score);

							Toast.makeText(getApplicationContext(), "Correct",
									Toast.LENGTH_SHORT).show();

						} else if (flag4 != 0) {
							Toast.makeText(getApplicationContext(),
									"Move ahead!!!", Toast.LENGTH_SHORT).show();
						} else
							Toast.makeText(getApplicationContext(),
									"Incorrect", Toast.LENGTH_SHORT).show();

						break;

					}
					break;
				case DragEvent.ACTION_DRAG_ENDED:
					
					return true;

				}
				
				if (score == 4) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							MainActivity.this);
					builder.setTitle("Hurray!!!");
					builder.setMessage("You have successfully labelled the diagram!");
					builder.setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();
				}
				return true;
			}
		});

	}

	private class MyLongClickListener implements View.OnLongClickListener {

		@Override
		public boolean onLongClick(View v) {

			ClipData dragData = ClipData.newPlainText("", "");

			View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);

			// Starts the drag

			v.startDrag(dragData, // the data to be dragged
					myShadow, // the drag shadow builder
					v, // no need to use local data
					0 // flags (not currently used, set to 0)
			);
			return true;
		}

	}
	
	public void onClick(View v){
		score=flag1=flag2=flag3=flag4=0;
		scoreTextView.setText("Your Score: 0");
	}

}
