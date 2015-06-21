package com.example.mincho.geoquiz;

/**
 * Created by Mincho on 6/18/2015.
 */
public class TrueFalse {
    private int mQuestion;
    private boolean mTrueQuestion;
    private boolean mIsCheated;


    public boolean ismIsCheated() {
        return mIsCheated;
    }

    public void setmIsCheated(boolean mIsCheated) {
        this.mIsCheated = mIsCheated;
    }


    public boolean ismTrueQuestion() {
        return mTrueQuestion;
    }

    public void setmTrueQuestion(boolean mTrueQuestion) {
        this.mTrueQuestion = mTrueQuestion;
    }

    public int getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public TrueFalse (int question, boolean trueQuestion, boolean cheatedQuestion){
        mQuestion = question;
        mTrueQuestion = trueQuestion;
        mIsCheated = cheatedQuestion;
    }
}
