package com.example.aquib.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button gobtn;

    int location;
    TextView txt5;
    int add[]=new int[4];
    RelativeLayout game;

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button playAgn;
    TextView txt6;

    TextView txt;

    TextView txt3;
    int score=0;
    int noQuestion=0;

    boolean gameactiv=true;
    CountDownTimer count;


    public void getAns(View view){

        if(gameactiv) {

            txt5 = (TextView) findViewById(R.id.textView5);
            txt3 = (TextView) findViewById(R.id.textView3);


            if (view.getTag().toString().equals(Integer.toString(location))) {

                txt5.setVisibility(View.VISIBLE);
                txt5.setText("Correct!!");
                score++;

            } else {

                txt5.setVisibility(View.VISIBLE);
                txt5.setText("Wrong!!");

            }
            noQuestion++;
            txt3.setText(String.valueOf(score) + "/" + String.valueOf(noQuestion));
            generateQuestion();

        }
    }

    public void generateQuestion(){

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        txt.setText(String.valueOf(a) + "+" + String.valueOf(b));

        location = rand.nextInt(4);

        for (int i = 0; i < 4; i++) {

            if (location == i) {

                add[i] = a + b;

            } else {

                int wrongAns = rand.nextInt(60);

                if (wrongAns == a + b) {

                    int rand1 = rand.nextInt(60);
                    wrongAns = rand1;

                }
                add[i] = wrongAns;
            }
        }

        btn1.setText(String.valueOf(add[0]));
        btn2.setText(String.valueOf(add[1]));
        btn3.setText(String.valueOf(add[2]));
        btn4.setText(String.valueOf(add[3]));

    }

    public void Go(View view){

        game.setVisibility(View.VISIBLE);
        gobtn.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gobtn = (Button) findViewById(R.id.button);
        playAgn =(Button)findViewById(R.id.button5);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        txt = (TextView) findViewById(R.id.textView4);
        txt6 = (TextView)findViewById(R.id.textView6);
        game = (RelativeLayout)findViewById(R.id.game);

        count = new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                txt6.setText(String.valueOf(millisUntilFinished/1000) +"s");

                Log.i("onTick: ",String.valueOf(millisUntilFinished/1000));

            }

            @Override
            public void onFinish() {

                playAgn.setVisibility(View.VISIBLE);
                txt6.setText("0s");
                txt5.setText("Your Score is "+String.valueOf(score)+"/"+String.valueOf(noQuestion));
                gameactiv=false;

            }

        }.start();

        generateQuestion();

        }

        public void playAgn(View view){

            txt5.setText("");
            generateQuestion();
            score=0;
            gameactiv=true;
            noQuestion=0;
            txt3.setText(String.valueOf(score)+"/"+String.valueOf(noQuestion));
            count.start();
            playAgn.setVisibility(View.INVISIBLE);

        }
}
