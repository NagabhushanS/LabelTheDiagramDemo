//@author: Nagabhushan S B

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
	//define variables 
	private TextView switchOne, cell, bulb;
	private ImageView diagram, icon1, icon2, icon3;
	private TextView scoreTextView, timeCount;
	private DisplayMetrics metrics;
	private int densityDpi;
	private static int score = 0, flag1 = 0, flag2 = 0, flag3 = 0;
	private int x = 20;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// method for initialization
		initialize();

		// define listener for implementation of hints
		View.OnClickListener list = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				int id = v.getId();
				switch (id) {
				case R.id.hint1:
					// display hint or label
					if (bulb.getText().equals("Bulb")) {

						bulb.setText("Source of light");
						bulb.setTextSize(20);
					} else {
						bulb.setText("Bulb");
						bulb.setTextSize(30);

					}
					break;
				case R.id.hint2:
					// display hint or label
					if (switchOne.getText().equals("Switch")) {
						switchOne.setText("Electrical Connection");
						switchOne.setTextSize(20);
					} else {
						switchOne.setText("Switch");
						switchOne.setTextSize(30);
					}

					break;
				case R.id.hint3:
					// display hint or label
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
		
		// set on click listeners
		icon1.setOnClickListener(list);
		icon2.setOnClickListener(list);
		icon3.setOnClickListener(list);
		
		// setup the on drag listener
		diagram.setOnDragListener(new View.OnDragListener() {

			//override the method
			@Override
			public boolean onDrag(View v, DragEvent event) {
				int action = event.getAction();
				float x = 0, y = 0;

				//handle events according to action
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
					//when action is drop, get the x and y coordinates and check for truthness
					x = (float) (((float) event.getX()) / ((float) densityDpi) * 160.00);
					y = (float) (((float) event.getY()) / ((float) densityDpi) * 160.00);
					TextView draggedView = (TextView) event.getLocalState();
					String s = draggedView.getText().toString();
					switch (s) {
					case "Bulb":

						//case 1: if label dragged is bulb
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
						//case 2: if label dragged is cell
						if (x > cblx && x < ctrx && y > cbly && y < ctry
								&& flag2 == 0) {
							score++;
							flag2++;
							scoreTextView.setText("Score: " + score);

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
						//case 3: if label dragged is switch
						if (x > sblx && x < strx && y > sbly && y < stry
								&& flag3 == 0) {
							score++;
							flag3++;
							scoreTextView.setText("Score: " + score);

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

				// if successfully labelled, then display alert dialog
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

	// in on resume, implement the time count system
	@Override
	protected void onResume() {
		super.onResume();

		countTheTime();

	}
	
	
	
    // extracted method for implementing the count in a background thread
	public void countTheTime() {
		Thread timer = new Thread() {
			@Override
			public void run() {
				super.run();
				while (x > 0 && score < 3) {
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					x--;
					final int z = x;
					
					// set the text of score in ui thread
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							if (z < 10) {
								timeCount.setText("  " + z);
							} else {
								timeCount.setText("" + z);
							}

						}
					});

				}
				// display alertDialog in ui thread
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						if (score != 3) {
							AlertDialog.Builder builder = new AlertDialog.Builder(
									MainActivity.this);
							builder.setTitle("Ooops!!!");
							builder.setMessage("Time up!");
							builder.setNegativeButton(android.R.string.ok, null);
							builder.setPositiveButton("Restart",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											restart();

										}
									});
							AlertDialog dialog = builder.create();
							dialog.show();
						} else {
							// don nothing

						}

					}
				});

			}

		};
		timer.start();
	}

	
	// extracted method for initializing the variables.
	public void initialize() {
		score = flag1 = flag2 = flag3 = 0;
		metrics = getResources().getDisplayMetrics();
		densityDpi = (int) (metrics.density * 160f);
		bulb = (TextView) findViewById(R.id.bulb);
		cell = (TextView) findViewById(R.id.cell);
		switchOne = (TextView) findViewById(R.id.switchOne);
		diagram = (ImageView) findViewById(R.id.diagram);
		scoreTextView = (TextView) findViewById(R.id.scoreTextView);
		timeCount = (TextView) findViewById(R.id.timeCount);
		MyLongClickListener longListener = new MyLongClickListener();
		bulb.setOnLongClickListener(longListener);
		cell.setOnLongClickListener(longListener);
		switchOne.setOnLongClickListener(longListener);
		icon1 = (ImageView) findViewById(R.id.hint1);
		icon2 = (ImageView) findViewById(R.id.hint2);
		icon3 = (ImageView) findViewById(R.id.hint3);
	}
	
	//on long click listener implementation for the label text views

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

	
	// restart method
	public void restart() {
		score = flag1 = flag2 = flag3 = 0;
		timeCount.setText("" + 20);
		x = 20;
		countTheTime();
		scoreTextView.setText("Score: 0");
	}

}
