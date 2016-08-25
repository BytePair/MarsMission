package com.bytepair.marsmission.model;

public class Choice {

    // Member Variables
    private String mText;
    private int mNextPage;


    // Constructor
    public Choice (String text, int nextPage) {
        mText = text;
        mNextPage = nextPage;
    }


    // Getters and Setters
    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public int getNextPage() {
        return mNextPage;
    }

    public void setNextPage(int imageId) {
        mNextPage = imageId;
    }
}
