package com.example.android.miwok;

public class Word {

    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Miwok translation for the word */
    private String mMiwokTranslation;

    /** Image resource id for images in app**/
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    /** Audio resource id for audio **/
    private int mAudioId;


    public Word(String defaultTranslation, String miwokTranslation, int audioId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioId = audioId;
;
    }


    //Constructor with image ID

    public Word(String defaultTranslation, String miwokTranslation , int imageResourceId , int audioId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mAudioId = audioId;
    }

    /**
     * Get the default translation
     */

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get Miwok translation
     */
    public String getmMiwokTranslation(){
        return mMiwokTranslation;
    }

    /**
     * return image Image resource
     * @return
     */

    public int getmImageResourceId(){
    return mImageResourceId;
    }

    public int getmAudioId() {
        return mAudioId;
    }

    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}