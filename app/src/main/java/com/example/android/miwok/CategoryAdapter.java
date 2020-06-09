package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import  android.support.v4.app.Fragment;

public class CategoryAdapter extends FragmentPagerAdapter {



    private Context mContext;
    //private String tabTitles [] = new String []{"Numbers", "Family", "Colors", "Phrases"};
    public CategoryAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;

    }


    @Override
    public int getCount() {
        return 4;
    }


    /**
     * Used to get Item position for Fragment based on swipes and show appropriate fragment
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new NumbersFragment();

            case 1:
                return new FamilyFragment();
            case 2:
                return new ColorsFragment();
            case 3:
                return new PhrasesFragment();
            default:
                return null;
        }
    }


    //Used to add tabs based on position
     @Override
    public CharSequence getPageTitle(int position) {

        if(position == 0) {

            return mContext.getString(R.string.category_numbers);

        }
        else if(position == 1) {
            return mContext.getString(R.string.category_family);
        }
         else if (position == 2) {
            return mContext.getString(R.string.category_colors);
        }
        else {
                return mContext.getString(R.string.category_phrases);
     }

         }

    }


