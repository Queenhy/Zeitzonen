package com.example.d064989.zeitzonen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class timeSet extends AppCompatActivity {

    String currentTime;
    Intent intent;
    TextView time;
    String newTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_set);

        intent = getIntent();
        currentTime = intent.getStringExtra("time");
        time = findViewById(R.id.time);
        time.setText(currentTime);
    }

    public void onClick(View button) throws ParseException {
        switch (button.getId()){
            case R.id.minus:
                calculation("minus");
                break;
            case R.id.plus:
                calculation("plus");
                break;
        }
    }

    public void calculation(String calc) throws ParseException {
        currentTime = time.getText().toString();
        SimpleDateFormat sdfkk = new SimpleDateFormat("HH:mm:ss");
        Date cTime = sdfkk.parse(currentTime);
        Calendar instance = Calendar.getInstance();
        instance.setTime(cTime);

        if (Objects.equals(calc, "minus")) {
            instance.add(Calendar.MINUTE, -60);
        }
        else instance.add(Calendar.MINUTE, 60);

        newTime = instance.get(java.util.Calendar.HOUR_OF_DAY)+":"+instance.get(java.util.Calendar.MINUTE)+":"+instance.get(java.util.Calendar.SECOND);
        time.setText(newTime);
    }
}
