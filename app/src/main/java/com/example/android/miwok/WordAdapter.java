package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int backgroundResource;
    //Constructor created for WordAdapter which takes
    //in an Activity and an ArrayList of "Words"
    //Custom Adapter created so ListView can be be show with two different words and given a resource

    public WordAdapter(Context context, ArrayList<Word> words, int backgroundResId) {

        super(context, 0, words);
        backgroundResource = backgroundResId;
    }


    // Used getView to define a positon , create a listItemView long as it is not void

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }


        //Word class used to get current word position
        //Gets  {@link Word} object located at this position in the list

        Word currentWord = getItem(position);

        //Locate the current word and give default translation
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getmDefaultTranslation());


        //Locate the current word and give Miwok translation
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getmMiwokTranslation());



        //Locate current imageview.

        ImageView defaultImageView = (ImageView) listItemView.findViewById(R.id.default_icon);


        if(currentWord.hasImage()) {
            defaultImageView.setImageResource(currentWord.getmImageResourceId());

            //Make view visible if there is an image
            defaultImageView.setVisibility(View.VISIBLE);

        }
        else
        {
            //Hide image if there is meant to be hidden
            defaultImageView.setVisibility(View.GONE);
        }


        //Set theme color
        View textBox = listItemView.findViewById(R.id.text_box);

        // find the color that hte resource ID maps to
        int color = ContextCompat.getColor(getContext(), backgroundResource);

        //Set the background of the text container
        textBox.setBackgroundColor(color);


        //if all requirements met return the ListView item for all views
        return listItemView;
    }
}