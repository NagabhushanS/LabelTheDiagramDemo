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
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	// define variables
	private TextView switchOne, cell, bulb; // switchOne is used because switch
											// is a key word.
	private ImageView diagram, icon1, icon2, icon3, playPause;
	private TextView scoreTextView, timeCount;
	private DisplayMetrics metrics;
	private int densityDpi;
	private static int score = 0, flag1 = 0, flag2 = 0, flag3 = 0;
	private int x = 20;
	private int pauseInt = 1;
	private int delayFlag=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// method for initialization
		initialize();

		// set the image of playPause as pause initially
		playPause.setImageDrawable(getResources().getDrawable(
				R.drawable.ic_action_pause_over_video1));

		// define listener for implementation of hints and pause
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
				case R.id.playPause:
					// handle pause and play actions

					if (playPause
							.getDrawable()
							.getConstantState()
							.equals(getResources().getDrawable(
									R.drawable.ic_action_play1)
									.getConstantState())) {

						playPause.setImageDrawable(getResources().getDrawable(
								R.drawable.ic_action_pause_over_video1));

					} else {

						playPause.setImageDrawable(getResources().getDrawable(
								R.drawable.ic_action_play1));
					}
					break;

				}

			}
		};

		// set on click listeners
		icon1.setOnClickListener(list);
		icon2.setOnClickListener(list);
		icon3.setOnClickListener(list);
		playPause.setOnClickListener(list);

		// setup the on drag listener
		diagram.setOnDragListener(new View.OnDragListener() {

			// override the method
			@Override
			public boolean onDrag(View v, DragEvent event) {
				int action = event.getAction();
				float x = 0, y = 0;

				// handle events according to action
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
					// when action is drop, get the x and y coordinates and
					// check for truthness
					x = (float) (((float) event.getX()) / ((float) densityDpi) * 160.00);
					y = (float) (((float) event.getY()) / ((float) densityDpi) * 160.00);
					TextView draggedView = (TextView) event.getLocalState();
					String s = draggedView.getText().toString();
					switch (s) {
					case "Bulb":

						// case 1: if label dragged is bulb
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
						// case 2: if label dragged is cell
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
						// case 3: if label dragged is switch
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
					// show dialog
					AlertDialog.Builder builder = new AlertDialog.Builder(
							MainActivity.this);
					builder.setTitle("Hurray!!!");
					builder.setMessage("You have successfully labelled the diagram!");
					builder.setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// start StatActivity to show the game scores
							Intent statIntent = new Intent(MainActivity.this, StatActivity.class);
							statIntent.putExtra("score", score);
							statIntent.putExtra("time", 20-Integer.parseInt(timeCount.getText().toString()));
							statIntent.putExtra("sucessBoolean", true);
							startActivity(statIntent);
							
						}
					});
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

		// start the thread
		countTheTime(1);

	}

	@Override
	protected void onPause() {

		// on pausing finish the activity since we are counting the game time
		// and we don't want to user to pause the activity
		super.onPause();
		finish();

	}

	// extracted method for implementing the count in a background thread
	public void countTheTime(int flag) {
		Thread timer = new Thread() {
			@Override
			public void run() {
				{

					super.run();

					while (x > 0 && score < 3) {
						// if paused state
						int i=0;
						for( i=0; i<1000; i++){

						if (playPause
								.getDrawable()
								.getConstantState()
								.equals(getResources().getDrawable(
										R.drawable.ic_action_pause_over_video1)
										.getConstantState())) {
							try {
								sleep(1);
								delayFlag=1;
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							pauseInt = 1;
						}
						else
							break;
						}
						if(delayFlag==1 && i>980)
							x--;
						// if resumed(play) state
						if (playPause
								.getDrawable()
								.getConstantState()
								.equals(getResources().getDrawable(
										R.drawable.ic_action_play1)
										.getConstantState())) {
							delayFlag=0;
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							if (pauseInt == 1) {
								runOnUiThread(new Runnable() {

									@Override
									public void run() {
										AlertDialog.Builder builder = new AlertDialog.Builder(
												MainActivity.this);
										builder.setTitle("Easy!!!");
										builder.setMessage("Paused!!");
										builder.setPositiveButton(
												"Resume",
												new DialogInterface.OnClickListener() {

													@Override
													public void onClick(
															DialogInterface dialog,
															int which) {
														playPause
																.setImageDrawable(getResources()
																		.getDrawable(
																				R.drawable.ic_action_pause_over_video1));
													}
												});

										AlertDialog dialog = builder.create();
										if (!isFinishing()) {
											dialog.show();
										}

									}
								});

							}
							pauseInt++;

						} else {

						}
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
								// show Dialog
								AlertDialog.Builder builder = new AlertDialog.Builder(
										MainActivity.this);
								builder.setTitle("Ooops!!!");
								builder.setMessage("Time up!");
								builder.setNegativeButton(android.R.string.ok,
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// start StatActivity to show the game scores
												Intent statIntent = new Intent(MainActivity.this, StatActivity.class);
												statIntent.putExtra("score", score);
												statIntent.putExtra("time", 20-x);
												statIntent.putExtra("sucessBoolean", false);
												startActivity(statIntent);
											}
										});
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
								if (!isFinishing()) {
									dialog.show();
								}

							}

						}
					});

				}
			}

		};
		if (flag == 1)
			timer.start();

	}

	// extracted method for initializing the variables.
	public void initialize() {
		// initialize
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
		playPause = (ImageView) findViewById(R.id.playPause);
	}

	// on long click listener implementation for the label text views

	private class MyLongClickListener implements View.OnLongClickListener {

		@Override
		public boolean onLongClick(View v) {

			// no need for explicit clip data as we are passing the text view in
			// startDrag() method
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
		// set all the variables to their initial values
		score = flag1 = flag2 = flag3 = 0;
		timeCount.setText("" + 20);
		x = 20;
		countTheTime(1);
		scoreTextView.setText("Score: 0");
	}

}
