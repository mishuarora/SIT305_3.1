package com.example.generalknowledge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nameInput;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.name_input);
        startButton = findViewById(R.id.start_button);

        startButton.setOnClickListener(v -> {
            String userName = nameInput.getText().toString().trim();
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra("userName", userName);
            startActivity(intent);
        });
    }
}
