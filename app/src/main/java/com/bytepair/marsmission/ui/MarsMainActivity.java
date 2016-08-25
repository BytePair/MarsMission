package com.bytepair.marsmission.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bytepair.marsmission.R;

public class MarsMainActivity extends AppCompatActivity {

    // Member Variables
    public static final String TAG = MarsMainActivity.class.getSimpleName();
    private EditText mNameField;
    private Button mStartButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mars_main);

        mNameField = (EditText) findViewById(R.id.nameEditText);
        mStartButton = (Button) findViewById(R.id.startButton);

        // on click listener for the start button
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get text as a string from name field
                String name = mNameField.getText().toString();

                // check if name was entered
                if (name.length() > 0) {
                    startStory(name);
                } else {
                    Toast.makeText(MarsMainActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "No name entered..." + name);
                }

            }
        });
    }

    // functions outside of onCreate
    private void startStory(String name) {
        // create intent - context: this - package context(what to start): StoryActivity.class
        Intent intent = new Intent(this, StoryActivity.class);
        // putExtra to send extra stuff with the intent
        // key is saved in strings.xml as key_name
        intent.putExtra(getString(R.string.key_name), name);
        startActivity(intent);
    }

    /* (OPTIONAL) on resume reset the name field
    @Override
    protected void onResume() {
        super.onResume();
        mNameField.setText("");
    }
    */
}
