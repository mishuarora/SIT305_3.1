package com.example.generalknowledge;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    TextView questionText, greeting;
    Button[] optionButtons = new Button[3];
    Button submitButton;
    ProgressBar progressBar;

    ArrayList<Question> questions = new ArrayList<>();
    int currentQuestionIndex = 0;
    int selectedAnswerIndex = -1;
    int score = 0;
    String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        greeting = findViewById(R.id.greeting);
        questionText = findViewById(R.id.question_text);
        optionButtons[0] = findViewById(R.id.option1);
        optionButtons[1] = findViewById(R.id.option2);
        optionButtons[2] = findViewById(R.id.option3);
        submitButton = findViewById(R.id.submit_button);
        progressBar = findViewById(R.id.progress_bar);

        userName = getIntent().getStringExtra("userName");
        greeting.setText("Hi, " + userName);

        loadQuestions();
        showQuestion();

        for (int i = 0; i < optionButtons.length; i++) {
            int index = i;
            optionButtons[i].setOnClickListener(v -> {
                resetOptionColors();
                selectedAnswerIndex = index;
                optionButtons[index].setBackgroundColor(Color.parseColor("#FFA500"));
            });
        }

        submitButton.setOnClickListener(v -> {
            if (selectedAnswerIndex == -1) return;

            int correctIndex = questions.get(currentQuestionIndex).getCorrectAnswerIndex();
            if (selectedAnswerIndex == correctIndex) {
                score++;
                optionButtons[correctIndex].setBackgroundColor(Color.GREEN);
            } else {
                optionButtons[selectedAnswerIndex].setBackgroundColor(Color.RED);
                optionButtons[correctIndex].setBackgroundColor(Color.GREEN);
            }

            currentQuestionIndex++;
            selectedAnswerIndex = -1;

            submitButton.postDelayed(() -> {
                if (currentQuestionIndex < questions.size()) {
                    showQuestion();
                } else {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("score", score);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        });
    }

    private void loadQuestions() {
        questions.add(new Question("What is the Capital of India?",
                new String[]{"Delhi", "Mumbai", "Punjab"}, 0));
        questions.add(new Question("How many alphabets are in total?",
                new String[]{"22", "25", "26"}, 2));
        questions.add(new Question("How many planets are in solar system?",
                new String[]{"7", "8", "9"}, 1));
        questions.add(new Question("Who is America's Prime Minister?",
                new String[]{"Joe Biden", "Donald Trump", "Barack Obama"}, 0));
        questions.add(new Question("Who is Prime Minister of India?",
                new String[]{"Narendra Modi", "Rahul Gandhi", "Arvind Kejriwal"}, 0));
    }

    private void showQuestion() {
        resetOptionColors();
        Question q = questions.get(currentQuestionIndex);
        questionText.setText(q.getQuestion());
        String[] options = q.getOptions();
        for (int i = 0; i < options.length; i++) {
            optionButtons[i].setText(options[i]);
        }

        progressBar.setProgress((int)(((double)(currentQuestionIndex + 1) / questions.size()) * 100));
    }

    private void resetOptionColors() {
        for (Button b : optionButtons) {
            b.setBackgroundColor(Color.parseColor("#FFB6C1"));
        }
    }
}
