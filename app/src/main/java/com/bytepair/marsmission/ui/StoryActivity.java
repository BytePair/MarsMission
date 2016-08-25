package com.bytepair.marsmission.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bytepair.marsmission.R;
import com.bytepair.marsmission.model.Page;
import com.bytepair.marsmission.model.Story;


public class StoryActivity extends AppCompatActivity {

    public static final String TAG = StoryActivity.class.getSimpleName();

    private ImageView mImageView;
    private TextView mTextView;
    private Button mChoice1;
    private Button mChoice2;

    private String mName;

    private Page mCurrentPage;

    // The control - story activity
    private Story mStory = new Story();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        // get the intent used to start this activity
        Intent intent = getIntent();
        // get the string extra called "name"
        mName = intent.getStringExtra(getString(R.string.key_name));

        if (mName == null) {
            mName = "John Doe";
        }

        mImageView = (ImageView) findViewById(R.id.storyImageView);
        mTextView = (TextView) findViewById(R.id.storyTextView);
        mChoice1 = (Button) findViewById(R.id.choiceButton1);
        mChoice2 = (Button) findViewById(R.id.choiceButton2);

        // load the page
        loadPage(0);
    }

    // Loads a new story page
    private void loadPage(int choice) {
        mCurrentPage = mStory.getPage(choice);

        // dynamically change the image
        Drawable drawable =  getResources().getDrawable(mCurrentPage.getImageId());
        mImageView.setImageDrawable(drawable);

        // format string so that mName replaces %1$s (1 string)
        String pageText = mCurrentPage.getText();
        pageText = String.format(pageText, mName);

        // change text
        mTextView.setText(pageText);

        // check if we are on the last page
        if (mCurrentPage.isFinal()) {

            // hide 1 button and set other button text
            mChoice1.setVisibility(View.INVISIBLE);
            mChoice2.setText("PLAY AGAIN");

            // return to parent activity with finish()A
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

        // else statement will set on click listeners for each of the buttons when
        // loadPage() is called
        } else  {
            // choices
            mChoice1.setText(mCurrentPage.getChoice1().getText());
            mChoice2.setText(mCurrentPage.getChoice2().getText());

            // when user clicked the first button
            mChoice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int nextPage = mCurrentPage.getChoice1().getNextPage();
                    loadPage(nextPage);
                }
            });

            // when user clicked the second button
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int nextPage = mCurrentPage.getChoice2().getNextPage();
                    loadPage(nextPage);
                }
            });
        }
    }

}
