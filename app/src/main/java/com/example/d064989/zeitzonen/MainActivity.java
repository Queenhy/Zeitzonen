package com.example.d064989.zeitzonen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    TextView time;
    String currentTime;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.time);
    }

    @Override
    protected void onStop(){
        super.onStop();

        SharedPreferences.Editor e = prefs.edit();
        e.putString("time", time.getText().toString());
        e.commit();
    }

    @Override
    protected  void onResume(){
        super.onResume();

        prefs = getPreferences(MODE_PRIVATE);
        time.setText(prefs.getString("time", ""));
    }

    public void onClick(View aView) {
        switch (aView.getId())
        {
            case R.id.c1:
                setCurrentTime("GMT+9", "Tokyo");
                break;
            case R.id.c2:
                setCurrentTime("GMT+7", "Hanoi");
                break;
            case R.id.c3:
                setCurrentTime("EST", "New York");
                break;
            case R.id.c4:
                setCurrentTime("CET", "Paris");
                break;
            case R.id.c5:
                setCurrentTime("GMT", "London");
                break;
            case R.id.w:
                openWartung();
                break;
        }
    }

    public void openWartung() {
        Intent intent = new Intent(this, timeSet.class);
        currentTime = time.getText().toString();

        if (!currentTime.isEmpty()) {
            intent.putExtra("time", currentTime);
            startActivity(intent);
        }
        else Toast.makeText(getApplicationContext(), "Bitte wählen Sie eine Zeitzone aus!",
                Toast.LENGTH_SHORT).show();

    }

    public void setCurrentTime(String timezone, String city) {

        TimeZone tz = java.util.TimeZone.getTimeZone(timezone);
        Calendar c = java.util.Calendar.getInstance(tz);

        currentTime = c.get(java.util.Calendar.HOUR_OF_DAY)+":"+c.get(java.util.Calendar.MINUTE)+":"+c.get(java.util.Calendar.SECOND);
        time.setText("Zeit in " + city + ": " + currentTime);
    }
}
