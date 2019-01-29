package com.example.moatasemabdallatif.math_quiz_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Cheat_Activity extends AppCompatActivity {
    Button Cheat_Button;
    TextView Confirm_text;
    boolean mAnswerIsTure;
    boolean IsCheater;
    String KEY_Cheater= "Cheater";

    private String TAG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat_);


        //adjusting widgets
        Cheat_Button = findViewById(R.id.Cheat_Button);
        Confirm_text = findViewById(R.id.Confirm_text);
        mAnswerIsTure = getIntent().getBooleanExtra("Extra_Is_Answer_True", false);


        //methods

        Cheat_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: Cheat_Button");
                IsCheater = true;
                Toast.makeText(Cheat_Activity.this , "the Answer Is "+mAnswerIsTure , Toast.LENGTH_LONG).show();
                Intent intent = MainActivity.newIntwent(Cheat_Activity.this ,IsCheater);
                intent.putExtra(KEY_Cheater ,IsCheater);
                startActivity(intent);

                


            }
        });
    }

    public static Intent newIntent(Context context , boolean Is_Answer_True){
        String Extra_Is_Answer_True = "Extra_Is_Answer_True";
        Intent intent = new Intent(context , Cheat_Activity.class);
        intent.putExtra(Extra_Is_Answer_True , Is_Answer_True);
        return intent;

    }
}
