package com.stop.loveam.temp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.stop.loveam.R;

public class HomeWorkSelectTimeActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button ok;
    Button back;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_work_select_time);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back = findViewById(R.id.button_back);
        back.setOnClickListener(v -> {
            finish();
        });

        Intent resultIntent = new Intent();
        resultIntent.putExtra("time", "2021-2022-1");
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            String text = radioButton.getText().toString();
            resultIntent.putExtra("time", text);
        });

        ok = findViewById(R.id.button_ok);
        ok.setOnClickListener(v -> {
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

}