package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

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
    private MediaPlayer.OnCompletionListener mCompletionListener= new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mediaPlayer){
            releaseMediaPlayer();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);



        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //TODO: Add Arraylist and add strings to Arraylist from 1-10
        //Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();


        //words.add("one");
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));


        //Creating a ListView here provides a scrollable list view that saves memory on device
        //Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        //ArrayAdapter can hold onto a ArrayList or list
        //R.layout.simple_list-item_1 is a built in list and "words" is a reference to ArrayList


        //Created the custom class adapter reference here

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);


        ListView listView = (ListView) findViewById(R.id.list);


        /**
         * Use setOnItemClickListener to set up an item click response for a ListView - this is used to add sound when view is clicked
         */


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // get the Word object at the given position the user clicked on
                Word word = words.get(i);

                releaseMediaPlayer();


                //System method used to initiate audio focus request using .requestAudioFocus

                int result = audioManager.requestAudioFocus(mAudioListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);


                //Audio focus given!
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // Media player created that points to the image resource that the position and starts player once clicked
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmAudioId());

                    //Starts media file
                    mediaPlayer.start();

                    // Stops and releases media player once sound finishes
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

        }





