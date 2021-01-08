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

        //Number of matches
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
        new CountDownTimer(16000, 1000) {

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

//
//                if (imageView.getDrawable() != getResources().getDrawable(R.drawable.stop))
//                {
//                    imageView.setImageResource(R.drawable.stop);
//                }
//                else if (imageView.getDrawable() == getResources().getDrawable(R.drawable.stop))
//                {
//                    imageView.setImageResource(R.drawable.snore);
//                }


                Toast.makeText(getApplicationContext(), "Click " + mThumbIds2[position], Toast.LENGTH_SHORT).show();
            }
        });



    }
}