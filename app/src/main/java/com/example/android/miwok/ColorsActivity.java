package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        final ArrayList<Word> colors = new ArrayList<Word>();
        colors.add(new Word("red", "wetetti", R.drawable.color_red, R.raw.color_red));
        colors.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green ));
        colors.add(new Word("brown", "takaakki", R.drawable.color_brown, R.raw.color_brown));
        colors.add(new Word("gray", "topoppi", R.drawable.color_gray, R.raw.color_gray));
        colors.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        colors.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        colors.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colors.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));


      WordAdapter adapter = new WordAdapter(this, colors, R.color.category_colors);


        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word color = colors.get(i);

                mediaPlayer= MediaPlayer.create(ColorsActivity.this, color.getmAudioId());
                mediaPlayer.start();
            }
        });

    }
}
