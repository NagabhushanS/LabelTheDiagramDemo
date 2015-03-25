package com.example.labelthediagram;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class StatActivity extends Activity {
	TextView timeScore, labelScore, topTimeScore, totalGames;
	private int topTimeScoreInt, totalGamesInt = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stat);

		intialize();

		SharedPreferences pref = getSharedPreferences("statsPref", MODE_PRIVATE);
		topTimeScoreInt = pref.getInt("topTimeScore", 20);
		totalGamesInt = pref.getInt("totalGames", 1);

		Intent i = getIntent();
		int score = i.getIntExtra("score", 0);
		int timeTaken = i.getIntExtra("time", 0);
		if (timeTaken < topTimeScoreInt) {
			topTimeScoreInt = timeTaken;
		}

		setTheValues(score, timeTaken);

	}

	private void setTheValues(int score, int timeTaken) {
		timeScore.setText("Time: " + timeTaken + " sec");
		labelScore.setText("Labels: " + score);
		totalGames.setText("Total Games: " + totalGamesInt);
		topTimeScore.setText("Top time: " + topTimeScoreInt + " sec");

	}

	private void intialize() {
		timeScore = (TextView) findViewById(R.id.timeScore);
		labelScore = (TextView) findViewById(R.id.labelScore);
		totalGames = (TextView) findViewById(R.id.totalGames);
		topTimeScore = (TextView) findViewById(R.id.topTime);

	}

	@Override
	protected void onStop() {
		totalGamesInt++;
		SharedPreferences pref = getSharedPreferences("statsPref", MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		editor.putInt("topTimeScore", topTimeScoreInt);
		editor.putInt("totalGames", totalGamesInt);
		editor.commit();

		super.onStop();
	}

}
