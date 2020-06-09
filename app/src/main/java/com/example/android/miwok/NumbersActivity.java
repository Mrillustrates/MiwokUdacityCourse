package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new NumbersFragment())
                .commit();
    }
}















//Log to display ArrayList for testing purposes
        //Log.v("NumbersActivity","Word at index 0: " + words);

        /**
         * For adding Textviews via Java. First, create Linear Layout var then TextView var, set text to view and addView to Linear Layout
         */


        //LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
        /** while loop for practice for indices of TextViews
         int i = 0;
         while(i< words.size()){
         TextView wordView = new TextView(this);
         wordView.setText(words.get(i));
         rootView.addView(wordView);
         i++;
         }
         */

        /**
         * For loop to index through the ArrayList so that TextViews can be iterated through one at a time SECTION 17.


        for (int i = 0; i < words.size(); i++) {
            TextView wordView = new TextView(this);
            wordView.setText(words.get(i));
            rootView.addView(wordView);
             */


