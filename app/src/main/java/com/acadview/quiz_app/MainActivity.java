package com.acadview.quiz_app;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    // SCORE LIST VARIABLE DECLARATION
    private static final String TAG = "MainActivity";
    DatabaseHelper mDb;


    // COUNT DOWN TIMER DECLARATION
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private TextView countdownView;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;


    // REQUIRED VARIABLES DECLARATION
    private int num1, num2, min, max, index;
    private String option, option1, a, b, c, d;

    // QUESTION SCORE DECLARATION
    private int correct = 0;
    private int wrong = 0;
    private int questionNumber = 0;
    private int attempt;
    private float scorePer;
    private String score;

    // XML COMPONENT DECLARATION
    private TextView scorePtrView;
    private TextView resultView;
    private TextView questionView;

    private Button a_view;
    private Button b_view;
    private Button c_view;
    private Button d_view;
    private Button start_view;
    private Button scoreListView;
    private android.widget.Toast Toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        countdownView = (TextView) findViewById(R.id.timer);

        scorePtrView = (TextView) findViewById(R.id.score);
        questionView = (TextView) findViewById(R.id.question);
        resultView = (TextView) findViewById(R.id.result);

        a_view = (Button) findViewById(R.id.a);
        b_view = (Button) findViewById(R.id.b);
        c_view = (Button) findViewById(R.id.c);
        d_view = (Button) findViewById(R.id.d);

        start_view = (Button) findViewById(R.id.start);

        scoreListView = (Button) findViewById(R.id.scoreList);

        // Opens the Score List Intent
        scoreListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ScoreActivity.class);
                startActivity(intent);

            }
        });


        //Initiating the Question
        setQuestion();
        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        updateCountDownText();


        //Start of Button Listener for Button1
        a_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My logic for Button goes in here

                startCountDown();
                if (a_view.getText() == option) {
                    correct = correct + 1;
                    setQuestion();

                } else {
                    wrong = wrong + 1;
                    setQuestion();
                }
            }
        });

        //Start of Button Listener for Button2
        b_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My logic for Button goes in here

                startCountDown();
                if (b_view.getText() == option) {
                    correct = correct + 1;
                    setQuestion();

                } else {
                    wrong = wrong + 1;
                    setQuestion();
                }
            }
        });

        //Start of Button Listener for Button3
        c_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My logic for Button goes in here

                startCountDown();
                if (c_view.getText() == option) {
                    correct = correct + 1;
                    setQuestion();

                } else {
                    wrong = wrong + 1;
                    setQuestion();
                }
            }
        });

        //Start of Button Listener for Button4
        d_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My logic for Button goes in here

                startCountDown();
                if (d_view.getText() == option) {
                    correct = correct + 1;
                    setQuestion();

                } else {
                    wrong = wrong + 1;
                    setQuestion();
                }
            }
        });



        //Reset the game by Button
        start_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                correct = 0;
                wrong = 0;
                questionNumber = 0;
                setQuestion();
                resetTimer();
                resultView.setText("");
                start_view.setVisibility(View.INVISIBLE);
                a_view.setEnabled(true);
                b_view.setEnabled(true);
                c_view.setEnabled(true);
                d_view.setEnabled(true);


            }
        });


        mDb = new DatabaseHelper(this);
    }

    public void setQuestion(){
        Random ran1 = new Random();
        Random ran2 = new Random();
        Random ran4 = new Random();



        num1 = ran1.nextInt(100);
        num2 = ran2.nextInt(100);

        // Question and correct answer logic
        Random ran3 = new Random();
        int operator= ran3.nextInt(3);
        if(operator == 0) {
            questionView.setText(num1 + " + " + num2);
            option = Integer.toString(num1 + num2);
            //questionView.setText(option);
        }

        else if(operator == 1) {
            questionView.setText(num1 + " - " + num2);
            option = Integer.toString(num1 - num2);
            //questionView.setText(option);


        }

        else if(operator == 2) {
            questionView.setText(num1 + " * " + num2);
            option = Integer.toString(num1 * num2);
            //questionView.setText(option);

        }
        else
        {
            questionView.setText(num1 + " / " + num2);
            option = Integer.toString(num1 / num2);
            //questionView.setText(option);

        }



        min = Integer.parseInt(option) - 10;
        max = Integer.parseInt(option) + 10;



        ArrayList<String> choices = new ArrayList<String>();
        choices.add(option);
        while (choices.size() <= 4 ) {
            option1 = Integer.toString(ran4.nextInt((max - min) + 1) + min);
            if (!choices.contains(option1)) {
                choices.add(option1);
            }
        }
        //final String[] choices = {option,option1,option2,option3};

        //buttons to store multiple choice answer with random function.
        index = ran2.nextInt(choices.size()-1);
        a = choices.get(index);
        a_view.setText(a);
        choices.remove(index);

        index = ran2.nextInt(choices.size()-1);
        b = choices.get(index);
        if(choices.contains(b) && b != a) {
            b_view.setText(b);
            choices.remove(index);
        }

        index = ran2.nextInt(choices.size()-1);
        c = choices.get(index);
        if(choices.contains(c) && c != a && c != b){
            c_view.setText(c);
            choices.remove(index);
        }

        index = ran2.nextInt(choices.size()-1);
        d = choices.get(index);
        if(choices.contains(d) && d != a && d != b && d != c){
            d_view.setText(d);
            choices.remove(index);
        }


        // Updating the question Number
        scorePtrView.setText(correct + "/" + questionNumber);
        questionNumber++;

    }


    //STARTING THE COUNTDOWN
    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                updateScore("" + scorePtrView.getText());
                start_view.setVisibility(View.VISIBLE);
                scoreListView.setVisibility(View.VISIBLE);
                a_view.setEnabled(false);
                b_view.setEnabled(false);
                c_view.setEnabled(false);
                d_view.setEnabled(false);

                attempt = questionNumber-1;
                scorePer = (correct*100)/attempt;
                score = correct + "/" + attempt;

                add_Data(correct, wrong, score , scorePer, attempt);
            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        countdownView.setText(timeFormatted);
    }

    // Update the resultView
    private void updateScore(String point) {
        resultView.setText("Score : " + point);
    }


    // Resetting the Count Down
    private void resetTimer() {
        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        updateCountDownText();
    }


    // ADDING THE DATA IN DATABASE
    public void add_Data(int new_a, int new_b, String new_c, float new_d, int new_e) {
        mDb.addData(new_a, new_b, new_c, new_d, new_e);
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}

