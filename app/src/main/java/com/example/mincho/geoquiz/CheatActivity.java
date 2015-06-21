package com.example.mincho.geoquiz;

import android.content.Intent;
import android.graphics.AvoidXfermode;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Mincho on 6/19/2015.
 */


public class CheatActivity extends ActionBarActivity{

    public static final String EXTRA_ANSWER_SHOWN = "com.example.mincho.geoquiz.answer_shown";
    public static final String EXTRA_ANSWER_IS_TRUE = "com.example.mincho,geoquiz.answer_is_true";
    private boolean answerIsTrue;
    private TextView answerTextView;
    private Button showAnswer;

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK,data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        // Answer will not be shown until the user presses the button

        setAnswerShownResult(false);
        answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        answerTextView = (TextView) findViewById(R.id.answerTextView);
        showAnswer = (Button) findViewById(R.id.showAnswerButton);
        // not so clear about AnswerIsTrue need to review again
        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answerIsTrue) {
                    answerTextView.setText(R.string.true_button_text);
                }
                else{
                    answerTextView.setText(R.string.false_button_text);
                }
                // If the "Show answer is pressed it will return that the answer has shownt to the user
                setAnswerShownResult(true);
            }
        });
    }
}
