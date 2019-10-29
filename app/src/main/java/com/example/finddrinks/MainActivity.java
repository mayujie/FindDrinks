package com.example.finddrinks;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int counter;
    int mycounter;
    //handler for myTimer with button open close
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize to 0
        counter = 0;
        //get a reference to timer text view. define as final cuz you won't change it
        final TextView timer = (TextView) findViewById(R.id.timer);
        Toast.makeText(getApplicationContext(), "Welcome to FindDrinks app.\nClick Start button will start timer!", Toast.LENGTH_LONG).show();
        //get a reference to start button
        Button sel_button = (Button) findViewById(R.id.start);
        //set a event listener to start button
        sel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //run specific action,  define as final cuz you won't change it
                //handler for button start
                final Handler handler = new Handler();
                //put the code you want to run when handler is used
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        // counter is int , need to be converted to string
                        timer.setText(String.valueOf(counter));
                        counter++;
                        //set delay each 1s , this means use runnable here
                        handler.postDelayed(this, 1000);
                    }
                };
                handler.post(runnable);
            }
        });
    }
/*need to figure out Thread and kill about reset timer*/
    public void onClickFindDrink(View view) {
        //get a reference to the text view
        TextView brands = (TextView) findViewById(R.id.brands);
        //get a reference to the spinner
        Spinner color = (Spinner) findViewById(R.id.color);
        //get the selected item in the spinner
        String drinkType = String.valueOf(color.getSelectedItem());
        //display the selected item
        brands.setText(drinkType);
        //factory method
        Toast.makeText(MainActivity.this, "You selected " + drinkType + ". Enjoy!!!" , Toast.LENGTH_SHORT).show();
    }

    public void clearDrink(View view) {
        //get a reference to the text view
        TextView brands_select = (TextView) findViewById(R.id.brands);
        String NoDrink = "No drink selected";
        //display the no selected item
        brands_select.setText(NoDrink);
        Toast.makeText(MainActivity.this, NoDrink + " Please select!" , Toast.LENGTH_SHORT).show();
    }
//start a thread
    public void startTimer(View view) {
        mycounter = 0;
        mTimerRunnable.run();
    }
//remove the started thread
    public void closeTimer(View view) {
        counter = 0;
        mycounter = 0;
        mHandler.removeCallbacks(mTimerRunnable);
        TextView myTimer = (TextView) findViewById(R.id.mytimer);
        myTimer.setText(String.valueOf("My Timer(OPEN and ClOSE)"));
    }
//thread set myTimer each 1 second
    private Runnable mTimerRunnable = new Runnable() {
        @Override
        public void run() {
            TextView myTimer = (TextView) findViewById(R.id.mytimer);
            myTimer.setText(String.valueOf(mycounter));
            mycounter++;
//            Toast.makeText(MainActivity.this, "open", Toast.LENGTH_SHORT).show();
            mHandler.postDelayed(this, 1000);
        }
    };

}
