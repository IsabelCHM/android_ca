package iss.nus.androidca;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GamePlay extends AppCompatActivity {

    String totMatches = "/6";
    int numberMatches = 0;
    int timesPressed = 0;
    int firstImagePressed = 0;

    public Integer[] mThumbIds = {
            R.drawable.afraid, R.drawable.full,
            R.drawable.hug, R.drawable.laugh,
            R.drawable.no_way, R.drawable.peep,
            R.drawable.afraid, R.drawable.full,
            R.drawable.hug, R.drawable.laugh,
            R.drawable.no_way, R.drawable.peep,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        //Number of matches to 0 at start
        TextView numMatches = findViewById(R.id.numMatches);
        if (numMatches != null)
        {
            numMatches.setText("0" + totMatches);
        }

        //Countdown timer time's up alert
        String title = "Time's up!";
        String msg = "Total number of matches: " + totMatches;
        AlertDialog.Builder dlg = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("Play Again",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int which) {
                                finish();
                                startActivity(getIntent());
                            }
                        })
                .setNegativeButton("Return to Title",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                .setIcon(android.R.drawable.ic_dialog_alert);



        TextView Countdown = findViewById(R.id.countdown);

        //Countdown Timer
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished <= 11000)
                {
                    if (Countdown != null)
                    {
                        Countdown.setText("Time Left: " + millisUntilFinished / 1000);
                        Countdown.setTextColor(Color.parseColor("#FA0C0C"));
                    }
                }
                else {


                    if (Countdown != null) {
                        Countdown.setText("Time Left: " + millisUntilFinished / 1000);

                    }
                }

            }

            public void onFinish() {
                //mTextField.setText("done!");
            dlg.show();
            }

        }.start();

        //Images
        GridView gridview = (GridView) findViewById(R.id.gridview);
        Integer[] mThumbIds2 = new Integer[12];
        List<Integer> imageList = Arrays.asList(mThumbIds);
        Collections.shuffle(imageList);
        imageList.toArray(mThumbIds2);
        gridview.setAdapter(new ImageAdaptor(this,mThumbIds2));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                    ImageView imageView = (ImageView)view;

                    int picId = Integer.parseInt(imageView.getTag().toString());
                    System.out.println(picId);
                    System.out.println(R.drawable.stop);


                if (picId != R.drawable.stop)
                {
                    imageView.setImageResource(R.drawable.stop);
                    imageView.setTag(R.drawable.stop);
                }
                if (picId == R.drawable.stop)
                {
                    timesPressed += 1;
                    System.out.println(timesPressed);
                    imageView.setImageResource(mThumbIds2[position]);
                    imageView.setTag(mThumbIds2[position]);
                }

                /*
                //Later need to edit such that this checking method is outside onClick
                //2 images opened
                if (timesPressed == 2)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //correct match
                    if (picId == firstImagePressed)
                    {
                        TextView numMatches = findViewById(R.id.numMatches);
                        numberMatches += 1;
                        if (numMatches != null)
                        {
                            numMatches.setText(numberMatches + totMatches);
                        }
                    }
                    else
                    {
                        imageView.setImageResource(R.drawable.stop);
                        imageView.setTag(R.drawable.stop);
                    }
                    timesPressed = 0;
                    firstImagePressed = 0;


                }

                //remembering previous image pressed
                else if (timesPressed == 1)
                {
                    firstImagePressed = mThumbIds2[position];
                }*/


                Toast.makeText(getApplicationContext(), "Click " + mThumbIds2[position], Toast.LENGTH_SHORT).show();
            }
        });



    }
}