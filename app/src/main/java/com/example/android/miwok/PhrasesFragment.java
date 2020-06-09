package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PhrasesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PhrasesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhrasesFragment extends Fragment {


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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PhrasesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhrasesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhrasesFragment newInstance(String param1, String param2) {
        PhrasesFragment fragment = new PhrasesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.words_list, container, false);


        //Gets Activity object instance first by getting Activity instance then get System service
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


        //Create a list of phrases



        final ArrayList<Word> phrases = new ArrayList<Word>();
        phrases.add(new Word("Where are you going?", "minto wuksus",
                R.raw.phrase_where_are_you_going));
        phrases.add(new Word("What is your name?", "tinnә oyaase'nә",
                R.raw.phrase_what_is_your_name));
        phrases.add(new Word("My name is...", "oyaaset...",
                R.raw.phrase_my_name_is));
        phrases.add(new Word("How are you feeling?", "michәksәs?",
                R.raw.phrase_how_are_you_feeling));
        phrases.add(new Word("I'm feeling good.", "kuchi achit",
                R.raw.phrase_im_feeling_good));
        phrases.add(new Word("Are you coming?", "әәnәs'aa?",
                R.raw.phrase_are_you_coming));
        phrases.add(new Word("Yes, I’m coming.", "hәә’ әәnәm",
                R.raw.phrase_yes_im_coming));
        phrases.add(new Word("I’m coming.", "әәnәm",
                R.raw.phrase_im_coming));
        phrases.add(new Word("Let's go.", "yoowutis",
                R.raw.phrase_lets_go));
        phrases.add(new Word("Come here", "әnni'nem",
                R.raw.phrase_come_here));





        WordAdapter adapter = new WordAdapter(getActivity(), phrases, R.color.category_phrases);


        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word phrase = phrases.get(i);
                releaseMediaPlayer();



                //System method used to initiate audio focus request using .requestAudioFocus

                int result = audioManager.requestAudioFocus(mAudioListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);


                //Audio focus given!
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // Media player created that points to the image resource that the position and starts player once clicked
                    mediaPlayer = MediaPlayer.create(getActivity(), phrase.getmAudioId());

                    //Starts media file
                    mediaPlayer.start();

                    // Stops and releases media player once sound finishes
                    mediaPlayer.setOnCompletionListener(mCompletionListener);


                }


            }
        });

        return rootView;

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    **/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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