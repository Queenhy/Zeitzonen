package com.example.d064989.zeitzonen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    TextView time;
    TextView text;
    String currentTime;

    Intent intentBack;

    SharedPreferences prefs;
    SharedPreferences.Editor e;

    HashMap originalTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.time);
        text = findViewById(R.id.text);

        originalTimes = new HashMap<String, Integer>();
        originalTimes.put("tokyo", 8);
        originalTimes.put("hanoi", 6);
        originalTimes.put("nyc", -6);
        originalTimes.put("paris", 0);
        originalTimes.put("london", -1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        intentBack = getIntent();

        if (getIntent().getExtras() != null) {
            intentBack.getIntExtra("tokyo", 0);
            intentBack.getIntExtra("hanoi", 0);
            intentBack.getIntExtra("nyc", 0);
            intentBack.getIntExtra("paris", 0);
            intentBack.getIntExtra("london", 0);
        }
    }

    public void onClick(View aView) {
        switch (aView.getId()) {
            case R.id.c1:
                setCurrentTime("GMT+9", "tokyo");
                break;
            case R.id.c2:
                setCurrentTime("GMT+7", "hanoi");
                break;
            case R.id.c3:
                setCurrentTime("EST", "nyc");
                break;
            case R.id.c4:
                setCurrentTime("CET", "paris");
                break;
            case R.id.c5:
                setCurrentTime("GMT", "london");
                break;
            case R.id.w:
                openWartung();
                break;
        }
    }

    public void openWartung() {
        Intent intent = new Intent(this, timeSet.class);

        if (getIntent().getExtras() != null) {
            intent.putExtra("tokyo", intentBack.getIntExtra("tokyo", 0));
            intent.putExtra("hanoi", intentBack.getIntExtra("hanoi", 0));
            intent.putExtra("nyc", intentBack.getIntExtra("nyc", 0));
            intent.putExtra("paris", intentBack.getIntExtra("paris", 0));
            intent.putExtra("london", intentBack.getIntExtra("london", 0));
        } else {
            intent.putExtra("tokyo", (int) originalTimes.get("tokyo"));
            intent.putExtra("hanoi", (int) originalTimes.get("hanoi"));
            intent.putExtra("nyc", (int) originalTimes.get("nyc"));
            intent.putExtra("paris", (int) originalTimes.get("paris"));
            intent.putExtra("london", (int) originalTimes.get("london"));
        }

            startActivity(intent);
    }

    public void setCurrentTime(String timezone, String city) {

        TimeZone tz = java.util.TimeZone.getTimeZone(timezone);
        Calendar c = java.util.Calendar.getInstance(tz);

        currentTime = c.get(java.util.Calendar.HOUR_OF_DAY) + ":" + c.get(java.util.Calendar.MINUTE) + ":" + c.get(java.util.Calendar.SECOND);

        int originalTimeshift = (int) originalTimes.get(city);
        Integer plusHours = getIntent().getIntExtra(city, 0);

        if (plusHours != 0) {
            plusHours = plusHours - originalTimeshift;
            c.add(Calendar.HOUR_OF_DAY, plusHours);
            currentTime = c.get(java.util.Calendar.HOUR_OF_DAY) + ":" + c.get(java.util.Calendar.MINUTE) + ":" + c.get(java.util.Calendar.SECOND);
        }

        text.setText("Zeit in " + city + ":");
        time.setText(currentTime);
    }


}
