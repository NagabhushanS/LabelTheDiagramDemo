package com.example.labelthediagram;

import static com.example.labelthediagram.DimentionConstants.bblx;
import static com.example.labelthediagram.DimentionConstants.bbly;
import static com.example.labelthediagram.DimentionConstants.btrx;
import static com.example.labelthediagram.DimentionConstants.btry;
import static com.example.labelthediagram.DimentionConstants.cblx;
import static com.example.labelthediagram.DimentionConstants.cbly;
import static com.example.labelthediagram.DimentionConstants.ctrx;
import static com.example.labelthediagram.DimentionConstants.ctry;
import static com.example.labelthediagram.DimentionConstants.sblx;
import static com.example.labelthediagram.DimentionConstants.sbly;
import static com.example.labelthediagram.DimentionConstants.strx;
import static com.example.labelthediagram.DimentionConstants.stry;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	TextView switchOne, cell, bulb;
	ImageView diagram;
	TextView scoreTextView;
	DisplayMetrics metrics;
	int densityDpi;
	static int score = 0;
	static int flag1 = 0, flag2 = 0, flag3 = 0;
	ImageView icon1, icon2, icon3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		score = flag1 = flag2 = flag3 = 0;
		metrics = getResources().getDisplayMetrics();
		densityDpi = (int) (metrics.density * 160f);
		bulb = (TextView) findViewById(R.id.bulb);
		cell = (TextView) findViewById(R.id.cell);
		switchOne = (TextView) findViewById(R.id.switchOne);
		diagram = (ImageView) findViewById(R.id.diagram);
		scoreTextView = (TextView) findViewById(R.id.scoreTextView);
		MyLongClickListener longListener = new MyLongClickListener();
		bulb.setOnLongClickListener(longListener);
		cell.setOnLongClickListener(longListener);
		switchOne.setOnLongClickListener(longListener);
		icon1 = (ImageView) findViewById(R.id.hint1);
		icon2 = (ImageView) findViewById(R.id.hint2);
		icon3 = (ImageView) findViewById(R.id.hint3);

		View.OnClickListener list = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				int id = v.getId();
				switch (id) {
				case R.id.hint1:
					if (bulb.getText().equals("Bulb")) {

						bulb.setText("Source of light");
						bulb.setTextSize(20);
					} else {
						bulb.setText("Bulb");
						bulb.setTextSize(30);

					}
					break;
				case R.id.hint2:
					if (switchOne.getText().equals("Switch")) {
						switchOne.setText("Electrical Connection");
						switchOne.setTextSize(20);
					} else {
						switchOne.setText("Switch");
						switchOne.setTextSize(30);
					}

					break;
				case R.id.hint3:
					if (cell.getText().equals("Cell")) {
						cell.setText("Voltage Source");
						cell.setTextSize(20);
					} else {
						cell.setText("Cell");
						cell.setTextSize(30);
					}
					break;
				}

			}
		};
		icon1.setOnClickListener(list);
		icon2.setOnClickListener(list);
		icon3.setOnClickListener(list);
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
					case "Bulb":

						if (x > bblx && x < btrx && y > bbly && y < btry
								&& flag1 == 0) {
							score++;
							scoreTextView.setText("Score: " + score);
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

					case "Cell":
						if (x > cblx && x < ctrx && y > cbly && y < ctry
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
					case "Switch":
						if (x > sblx && x < strx && y > sbly && y < stry
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

					}
					break;
				case DragEvent.ACTION_DRAG_ENDED:

					return true;

				}

				if (score == 3) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							MainActivity.this);
					builder.setTitle("Hurray!!!");
					builder.setMessage("You have successfully labelled the diagram!");
					builder.setNegativeButton(android.R.string.ok, null);
					builder.setPositiveButton("Restart",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									restart();

								}
							});
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

			View.DragShadowBuilder myShadow = new MyDragShadowBuilder(v);

			// Starts the drag

			v.startDrag(dragData, // the data to be dragged
					myShadow, // the drag shadow builder
					v, // no need to use local data
					0 // flags (not currently used, set to 0)
			);
			return true;
		}

	}

	public void restart() {
		score = flag1 = flag2 = flag3 = 0;
		scoreTextView.setText("Your Score: 0");
	}

}
