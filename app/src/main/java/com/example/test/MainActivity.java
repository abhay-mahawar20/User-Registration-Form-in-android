package com.example.test;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText name, mob, add;
    private TextView resultTextView;
    private Button btn;
    private RadioGroup rg;
    private ImageButton dpd;
    private LinearLayout resultContainer;
    private String selectedDate = "";
    private CheckBox ch1, ch2, ch3, ch4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Views
        name = findViewById(R.id.name);
        mob = findViewById(R.id.mob);
        add = findViewById(R.id.add);
        rg = findViewById(R.id.rg);
        dpd = findViewById(R.id.dpd);
        btn = findViewById(R.id.btn);
        
        ch1 = findViewById(R.id.ch1);
        ch2 = findViewById(R.id.ch2);
        ch3 = findViewById(R.id.ch3);
        ch4 = findViewById(R.id.ch4);

        resultContainer = findViewById(R.id.resultContainer);
        resultTextView = findViewById(R.id.resultTextView);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        dpd.setOnClickListener(v -> {
            DatePickerDialog dp = new DatePickerDialog(MainActivity.this, (view, year1, month1, day1) -> {
                selectedDate = day1 + "/" + (month1 + 1) + "/" + year1;
            }, year, month, day);
            dp.show();
        });

        btn.setOnClickListener(v -> {
            // Collect Hobbies
            StringBuilder hobbies = new StringBuilder();
            if (ch1.isChecked()) hobbies.append(ch1.getText());
            if (ch2.isChecked()) {
                if (hobbies.length() > 0) hobbies.append(", ");
                hobbies.append(ch2.getText());
            }
            if (ch3.isChecked()) {
                if (hobbies.length() > 0) hobbies.append(", ");
                hobbies.append(ch3.getText());
            }
            if (ch4.isChecked()) {
                if (hobbies.length() > 0) hobbies.append(", ");
                hobbies.append(ch4.getText());
            }

            // Get Gender
            String gender = "Not Selected";
            int selectedGenderId = rg.getCheckedRadioButtonId();
            if (selectedGenderId != -1) {
                RadioButton rb = findViewById(selectedGenderId);
                gender = rb.getText().toString();
            }

            // Create Full Summary String
            String summary = "Full Name: " + name.getText().toString() + "\n" +
                             "Mobile: " + mob.getText().toString() + "\n" +
                             "Address: " + add.getText().toString() + "\n" +
                             "Gender: " + gender + "\n" +
                             "DOB: " + (selectedDate.isEmpty() ? "Not Selected" : selectedDate) + "\n" +
                             "Hobbies: " + (hobbies.length() > 0 ? hobbies.toString() : "None");

            // Display in the single result text view
            resultTextView.setText(summary);

            // Show the result container
            resultContainer.setVisibility(View.VISIBLE);
        });
    }
}
