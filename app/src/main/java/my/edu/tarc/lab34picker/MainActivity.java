package my.edu.tarc.lab34picker;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Calendar cal = Calendar.getInstance();
    int age = 0;
    double minSaving = 0, userBalance, eligible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),getString(R.string.datapicker));
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = ( day_string+
                "/" + month_string + "/" + year_string);

        TextView textViewAgeButton = findViewById(R.id.buttonDOB);
        textViewAgeButton.setText(dateMessage);

        Toast.makeText(this, getString(R.string.date) + dateMessage,
                Toast.LENGTH_SHORT).show();

        age = cal.get(Calendar.YEAR) - year;
        TextView textViewAge = findViewById(R.id.textViewAge);
        textViewAge.setText("Age: " + Integer.toString(age));
    }

    public void processEligibleAmount(View view){
        EditText editTextAccountBalance = findViewById(R.id.editTextAccountBalance);
        userBalance = Double.parseDouble(editTextAccountBalance.getText().toString());

        if(age >= 16 && age <= 20){
            minSaving = 5000;
        }else if(age >= 21 && age <= 25){
            minSaving = 14000;
        }else if(age >= 26 && age <= 30){
            minSaving = 29000;
        }else if(age >= 31 && age <= 35){
            minSaving = 50000;
        }else if(age >= 16 && age <= 40){
            minSaving = 78000;
        }else if(age >= 41 && age <= 45){
            minSaving = 116000;
        }else if(age >= 46 && age <= 50){
            minSaving = 165000;
        }else if(age >= 51 && age <= 55){
            minSaving = 228000;
        }else
            minSaving = 0;

        TextView textViewEligibleAmount = findViewById(R.id.textViewEligibleAmount);
        if(userBalance > minSaving && age >= 16) {
            eligible = (userBalance - minSaving) * 0.3;
            textViewEligibleAmount.setText("Eligible Amount: RM" + Double.toString(eligible));
        } else{
            textViewEligibleAmount.setText("Eligible Amount: RM0(Not Eligible)");
        }
    }

    public void reset(View view){
        EditText editTextAccountBalance = findViewById(R.id.editTextAccountBalance);
        editTextAccountBalance.setText("");

        TextView textViewAge = findViewById(R.id.textViewAge);
        textViewAge.setText("Age");

        TextView textViewEA = findViewById(R.id.textViewEligibleAmount);
        textViewEA.setText("Eligible Amount");

        Button BOD = findViewById(R.id.buttonDOB);
        BOD.setText("Select date of birth");
    }
}
