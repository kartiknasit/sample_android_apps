package com.kartik.sampleapps.androidinterviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ToughQuestionsActivity extends AppCompatActivity implements View.OnClickListener{

    String[] tough_questions, tough_answers;
    TextView question_textView, answer_textView, current_question_count_textView, total_question_count_textView;
    Button left_arrow_btn, right_arrow_btn, show_answer_btn;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        tough_questions = getResources().getStringArray(R.array.tough_questions_array);
        tough_answers = getResources().getStringArray(R.array.tough_answers_array);

        question_textView = (TextView) findViewById(R.id.question_textView);
        answer_textView = (TextView) findViewById(R.id.answer_textView);
        current_question_count_textView = (TextView) findViewById(R.id.current_question_count_textView);
        total_question_count_textView = (TextView) findViewById(R.id.total_question_count_textView);

        left_arrow_btn = (Button) findViewById(R.id.left_arrow_btn);
        right_arrow_btn = (Button) findViewById(R.id.right_arrow_btn);
        show_answer_btn = (Button) findViewById(R.id.show_answer_btn);

        left_arrow_btn.setOnClickListener(this);
        right_arrow_btn.setOnClickListener(this);
        show_answer_btn.setOnClickListener(this);

        index = 0;

        question_textView.setText(tough_questions[index]);
        answer_textView.setText("Press A for Answer.");

        current_question_count_textView.setText(String.valueOf(index + 1));
        total_question_count_textView.setText("/"+String.valueOf(tough_questions.length));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_arrow_btn:
                index = index==0?tough_questions.length-1:index - 1;
                answer_textView.setText("Press A for Answer.");
                question_textView.setText(tough_questions[index]);
                current_question_count_textView.setText(String.valueOf(index + 1));
                break;
            case R.id.right_arrow_btn:
                index = index==tough_questions.length-1?0:index + 1;
                answer_textView.setText("Press A for Answer.");
                question_textView.setText(tough_questions[index]);
                current_question_count_textView.setText(String.valueOf(index + 1));
                break;
            case R.id.show_answer_btn:
                answer_textView.setText(tough_answers[index]);
                break;
            default:
                break;
        }
    }
}
