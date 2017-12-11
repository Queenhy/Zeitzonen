package com.example.d064989.zeitzonen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;

public class timeSet extends AppCompatActivity {

    Intent intent;
    TextView text;
    int zeitverschiebung = 0;

    RadioGroup radioGroup;
    SharedPreferences prefsMain;
    SharedPreferences.Editor e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_set);

        intent = getIntent();
        text = findViewById(R.id.text);

        prefsMain = getPreferences(MODE_PRIVATE);
        e = prefsMain.edit();
        e.putInt("tokyo", intent.getIntExtra("tokyo",0));
        e.putInt("hanoi", intent.getIntExtra("hanoi",0));
        e.putInt("nyc", intent.getIntExtra("nyc",0));
        e.putInt("paris", intent.getIntExtra("paris",0));
        e.putInt("london", intent.getIntExtra("london",0));
    }

    @Override
    protected void onStop() {
        super.onStop();

        e.putInt("tokyo", prefsMain.getInt("tokyo", 0));
        e.putInt("hanoi", prefsMain.getInt("hanoi", 0));
        e.putInt("nyc", prefsMain.getInt("nyc", 0));
        e.putInt("paris", prefsMain.getInt("paris", 0));
        e.putInt("london", prefsMain.getInt("london", 0));
        e.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        prefsMain.getInt("tokyo", 0);
        prefsMain.getInt("hanoi", 0);
        prefsMain.getInt("nyc", 0);
        prefsMain.getInt("paris", 0);
        prefsMain.getInt("london", 0);
    }

    @Override
    public void onBackPressed() {
        e.putInt("tokyo", prefsMain.getInt("tokyo", 0));
        e.putInt("hanoi", prefsMain.getInt("hanoi", 0));
        e.putInt("nyc", prefsMain.getInt("nyc", 0));
        e.putInt("paris", prefsMain.getInt("paris", 0));
        e.putInt("london", prefsMain.getInt("london", 0));
        e.commit();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("tokyo", prefsMain.getInt("tokyo", 0));
        intent.putExtra("hanoi", prefsMain.getInt("hanoi", 0));
        intent.putExtra("nyc", prefsMain.getInt("nyc", 0));
        intent.putExtra("paris", prefsMain.getInt("paris", 0));
        intent.putExtra("london", prefsMain.getInt("london", 0));
        startActivity(intent);
    }

    public void onClick(View button) throws ParseException {
        int selectedId = checkRadioButton();

        if (!(selectedId == 0)) {
            switch (button.getId()) {
                case R.id.minus:
                    preCalculation("minus", selectedId);
                    break;
                case R.id.plus:
                    preCalculation("plus", selectedId);
                    break;
            }
        }
    }

    private void preCalculation(String calc, int selectedId) throws ParseException {
        switch (selectedId) {
            case R.id.tokyo:
                zeitverschiebung = prefsMain.getInt("tokyo", 0);
                calculation(calc, "tokyo");
                break;
            case R.id.hanoi:
                zeitverschiebung = prefsMain.getInt("hanoi", 0);
                calculation(calc, "hanoi");
                break;
            case R.id.newyork:
                zeitverschiebung = prefsMain.getInt("nyc", 0);
                calculation(calc, "nyc");
                break;
            case R.id.paris:
                zeitverschiebung = prefsMain.getInt("paris", 0);
                calculation(calc, "paris");
                break;
            case R.id.london:
                zeitverschiebung = prefsMain.getInt("london", 0);
                calculation(calc, "london");
                break;
        }
    }

    public void calculation(String calc, String city) throws ParseException {

        if (calc == "minus") {
            zeitverschiebung -= 1;
        } else {
            zeitverschiebung += 1;
        }
        text.setText("Die Zeitverschiebung zu Berlin beträgt: " + zeitverschiebung);
        e.putInt(city, zeitverschiebung);
        e.commit();
    }

    public void onRadioButtonClicked(View rb) {
        int rbId = rb.getId();
        switch (rbId) {
            case R.id.tokyo:
                text.setText("Die Zeitverschiebung zu Berlin beträgt " + prefsMain.getInt("tokyo", 0));
                break;
            case R.id.hanoi:
                text.setText("Die Zeitverschiebung zu Berlin beträgt " + prefsMain.getInt("hanoi", 0));
                break;
            case R.id.newyork:
                text.setText("Die Zeitverschiebung zu Berlin beträgt " + prefsMain.getInt("nyc", 0));
                break;
            case R.id.paris:
                text.setText("Die Zeitverschiebung zu Berlin beträgt " + prefsMain.getInt("paris", 0));
                break;
            case R.id.london:
                text.setText("Die Zeitverschiebung zu Berlin beträgt " + prefsMain.getInt("london", 0));
                break;
        }

    }

    private int checkRadioButton() {
        int selectedId = 0;
        radioGroup = findViewById(R.id.radiogroup);
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), "Please select a timezone!", Toast.LENGTH_SHORT).show();

        } else {
            selectedId = radioGroup.getCheckedRadioButtonId();
        }
        return selectedId;
    }
}
