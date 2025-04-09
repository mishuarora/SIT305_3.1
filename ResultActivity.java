package com.example.generalknowledge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView resultText;
    Button retryButton, finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.result_text);
        retryButton = findViewById(R.id.retry_button);
        finishButton = findViewById(R.id.finish_button);

        int score = getIntent().getIntExtra("score", 0);
        String userName = getIntent().getStringExtra("userName");
        resultText.setText(userName + ", your score is: " + score);

        retryButton.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
            intent.putExtra("userName", userName);
            startActivity(intent);
            finish();
        });

        finishButton.setOnClickListener(v -> finishAffinity());
    }
}
