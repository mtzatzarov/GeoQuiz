package com.example.mincho.geoquiz;


import android.app.ActionBar;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends ActionBarActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private boolean isCheater;

    private Button trueButton;
    private Button falseButton;
    private Button cheatButton;
    private ImageButton nextButton;
    private ImageButton backButton;
    private TextView questionTextView;
    // An array of Questions
    private TrueFalse[] questionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_more, false, false),
            new TrueFalse(R.string.question_planina, true, false),
            new TrueFalse(R.string.question_plovdiv, true, false),
            new TrueFalse(R.string.question_reka, true, false),
            new TrueFalse(R.string.question_stolica, true, false),
    };

    private int currentIndex=0;
    // Current question is shown
    private  void updateQuestion(){

        int question = questionBank[currentIndex].getmQuestion();
        questionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {

        boolean answerIsTrue = questionBank[currentIndex].ismTrueQuestion();

        int messageResId = 0;
        // if isCheater or if he ever cheated requirements are met, return judjement toast, else - shown if the answer is correct ot not.
        if (isCheater || questionBank[currentIndex].ismIsCheated()) {
            messageResId = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data == null) {
            return;
        }
            questionBank[currentIndex].setmIsCheated(true);
            isCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate(Bundle) called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }

        questionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        trueButton = (Button)findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);
        cheatButton = (Button) findViewById(R.id.cheat_button);
        nextButton = (ImageButton) findViewById(R.id.next_button);
        backButton = (ImageButton) findViewById(R.id.back_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex +1) % questionBank.length;
                isCheater = false;
                updateQuestion();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentIndex == 0){
                    currentIndex =  questionBank.length -1 ;
                    //Toast.makeText(getApplicationContext(),R.string.message_first,Toast.LENGTH_SHORT).show()
                }
                else {
                    currentIndex = (currentIndex - 1) % questionBank.length;

                }
                isCheater = false;
                updateQuestion();

        }});

        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start cheatActivity

                Intent i = new Intent(QuizActivity.this, CheatActivity.class);


                boolean answerIsTrue = questionBank[currentIndex].ismTrueQuestion();
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE,answerIsTrue);

                startActivityForResult(i,0);

            }
        });

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
    }

    @Override
    public void onSaveInstanceState (Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSavedInstanceState");
        savedInstanceState.putInt(KEY_INDEX, currentIndex);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
