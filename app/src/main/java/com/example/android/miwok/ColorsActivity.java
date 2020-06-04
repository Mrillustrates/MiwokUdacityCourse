package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;


    //Create an Audio Focus listener to be triggered when focus changes
    private AudioManager.OnAudioFocusChangeListener mAudioListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            /**
             * If audio focus is paused it will pause the player and when app resumes play sound
             */
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            // If audio focus is lost release media player to free up resources
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }

            //If audio focus is gained start Media Player
            if(focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.words_list);


            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

            final ArrayList<Word> colors = new ArrayList<Word>();
            colors.add(new Word("red", "wetetti", R.drawable.color_red, R.raw.color_red));
            colors.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
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

                    //Release media player if currently exists because about to play a different audio file
                    releaseMediaPlayer();


                    //System method used to initiate audio focus request using .requestAudioFocus
                    int result = audioManager.requestAudioFocus(mAudioListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                    //Audio focus given!
                    if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                        mediaPlayer = MediaPlayer.create(ColorsActivity.this, color.getmAudioId());
                        mediaPlayer.start();

                        //Set up an onCompletionListener to stop and  release resource after playing
                        mediaPlayer.setOnCompletionListener(mCompletionListener);
                    }
                }
            });

        }

    /**
     * Best practices is to release resources by  using Activity methods such as onStop when user goes home as this immediately stops
     * the app and releases player with releaseMediaPlayer()
     */

    @Override
    protected void onStop(){
        super.onStop();
        releaseMediaPlayer();
    }

        private final void releaseMediaPlayer() {
            // If the media player is not null, then it may be currently playing a sound.
            if (mediaPlayer != null) {
                // Regardless of the current state of the media player, release its resources
                // because we no longer need it.
                mediaPlayer.release();

                // Set the media player back to null. For our code, we've decided that
                // setting the media player to null is an easy way to tell that the media player
                // is not configured to play an audio file at the moment.
                mediaPlayer = null;

                audioManager.abandonAudioFocus(mAudioListener);
            }
        }
    }