package com.example.moatasemabdallatif.math_quiz_app;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {

        private ArrayList <Question> mQuestionBank;
        public int mcurrentIndex;

        int grade;
        int Answers;
        boolean Is_Cheater;

        TextView QuestionText;
        TextView Cheater_Text;
        Button True_Button;
        Button False_Button;



        public static final String TAG = "main activity";
        public static final String KEY_INDEX = "index";
        public static final String KEY_Cheater = "Cheater";
        public static final String KEY_Questions = "Questions";


    @Override
    protected void onCreate(Bundle SavedInstanceState) {

            super.onCreate(SavedInstanceState);
            Log.d(TAG , "saved Instance state");



        if (SavedInstanceState != null){
            mcurrentIndex = SavedInstanceState.getInt(KEY_INDEX ,0 );
            Is_Cheater = SavedInstanceState.getBoolean(KEY_Cheater);
            mQuestionBank =new ArrayList<>();
            mQuestionBank = SavedInstanceState.getParcelableArrayList(KEY_Questions);



        }
        else {

            Is_Cheater = getIntent().getBooleanExtra(KEY_Cheater, false);
            mQuestionBank = new ArrayList<Question>();
            mQuestionBank.add(new Question(R.string.Q1 , true));
            mQuestionBank.add( new Question(R.string.Q2 , false));
            mQuestionBank.add(  new Question(R.string.Q3 , false));
            mQuestionBank.add(new Question(R.string.Q4 , true));


        }

        setContentView(R.layout.activity_main);



        // adding the Buttons
         True_Button = new Button(this);
         False_Button = new Button(this);
        ImageButton Next_Button = new ImageButton(this);
        ImageButton previous_Button = new ImageButton(this);
        Button Cheat_Button = new Button(this);

        //the textview
         QuestionText = new TextView(this);

        //the questions





        //linking the controller  with the View
        True_Button = findViewById(R.id.t_button);
        False_Button = findViewById(R.id.f_button);
        Next_Button = findViewById(R.id.nextButton);
        previous_Button = findViewById(R.id.previous_Button);
        QuestionText = findViewById(R.id.Quistiontext);
        Cheat_Button = findViewById(R.id.Cheat_Button);
        Cheater_Text = findViewById(R.id.Cheater_Text);

        Cheater_Check();
        UpdateQuistiontext();



        //Event Listeners

        True_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {

            if(CheckAnswer(true) ){
                grade +=1;

            }
                nextQuestion();
                Answers+=1;

            }

            });


            False_Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                if(CheckAnswer(false)){
                    grade+=1;

                }
                    nextQuestion();
                Answers+=1;
                }
            });

            Next_Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   nextQuestion();
                }
            });

            previous_Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mcurrentIndex<1){
                        mcurrentIndex= mQuestionBank.size()-1;
                        UpdateQuistiontext();

                    }
                    else{
                        mcurrentIndex= mcurrentIndex-1;
                        UpdateQuistiontext();
                    }
                }
            });

            QuestionText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nextQuestion();
                }
            });


        Cheat_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Intent intent = new Intent(MainActivity.this , Cheat_Activity.class);

                Intent intent = Cheat_Activity.newIntent(MainActivity.this , mQuestionBank.get(mcurrentIndex).getQAnswer());
                startActivity(intent);
                Log.d(TAG , "Cheat Button clicked" , new Exception()) ;
            }
        });



        Log.d(TAG , "Activity created");

    }


    // methods
    private void UpdateQuistiontext(){
        if(Answers== mQuestionBank.size()-1){
            Toast.makeText(MainActivity.this,"your Grade is "+ grade +"out of "+(Answers+1), Toast.LENGTH_LONG ).show();
        }
        if (!mQuestionBank.get(mcurrentIndex).getAnsweredTrue()){
            True_Button.setClickable(true);
            False_Button.setClickable(true);
        }
        else {
            True_Button.setClickable(false);
            False_Button.setClickable(false);
        }
        int qNumber = mQuestionBank.get(mcurrentIndex).getQID();
        QuestionText.setText(qNumber);}

    private  boolean CheckAnswer(boolean AnswerButton) {
        int resID = 0;
        boolean trueAnswer = mQuestionBank.get(mcurrentIndex).getQAnswer();
        if (trueAnswer == AnswerButton) {
            Toast.makeText(MainActivity.this, R.string.Correct_Toast, Toast.LENGTH_SHORT).show();
            mQuestionBank.get(mcurrentIndex).setAnsweredTrue(true);
        return true;
        } else {
            Toast.makeText(MainActivity.this, R.string.Incorrect_Toast, Toast.LENGTH_SHORT).show();
            return  false;
        }

    }

    private void nextQuestion(){
        mcurrentIndex = (mcurrentIndex+1) % mQuestionBank.size();
        UpdateQuistiontext();
    }

    private void  Cheater_Check(){
        if(Is_Cheater){
        Cheater_Text.setText(R.string.Is_cheater);
    }
    else {
        Cheater_Text.setText(R.string.Not_cheater);
    }}




    @Override
    public void onSaveInstanceState(Bundle SavedInstanceState){
        super.onSaveInstanceState(SavedInstanceState);
        Log.d(TAG , "ON instance State");
        SavedInstanceState.putInt(KEY_INDEX , mcurrentIndex);
        SavedInstanceState.putBoolean(KEY_Cheater , Is_Cheater);
        SavedInstanceState.putParcelableArrayList(KEY_Questions , mQuestionBank);
    }


    public static Intent newIntwent(Context context , boolean Ischeater){
        Intent intent = new Intent(context ,  MainActivity.class);
        intent.putExtra(KEY_Cheater , Ischeater);
        return intent;
    }





}



