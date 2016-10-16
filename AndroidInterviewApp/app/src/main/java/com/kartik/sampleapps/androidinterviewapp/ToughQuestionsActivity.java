package com.kartik.sampleapps.androidinterviewapp;

import android.app.ActionBar;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class ToughQuestionsActivity extends AppCompatActivity implements View.OnClickListener{

    String[] tough_questions, tough_answers;
    TextView question_textView, answer_textView, title_bar_textView,
            current_question_count_textView, total_question_count_textView;
    Button left_arrow_btn, right_arrow_btn, show_answer_btn,
            speak_btn, mute_btn;
    int index;
    TextToSpeech textToSpeech;
    int result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        textToSpeech =
                new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            result = textToSpeech.setLanguage(Locale.US);
                        } else {
                            Toast.makeText(ToughQuestionsActivity.this,
                                    "Operation not Suuported for your device.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        LinearLayout title_bar = (LinearLayout) findViewById(R.id.question_title_bar_id);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.questions_title_bar);

        speak_btn = (Button) findViewById(R.id.speak_btn);
        mute_btn = (Button) findViewById(R.id.mute_btn);
        title_bar_textView = (TextView) findViewById(R.id.title_bar_textViews);

        speak_btn.setOnClickListener(this);
        mute_btn.setOnClickListener(this);
        title_bar_textView.setText("Tough Questions");

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
            case R.id.speak_btn:
                if(result == TextToSpeech.LANG_NOT_SUPPORTED
                        || result == TextToSpeech.LANG_MISSING_DATA) {
                    Toast.makeText(ToughQuestionsActivity.this,
                            "Operation not Suuported for your device.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    textToSpeech.speak(tough_answers[index],
                            TextToSpeech.QUEUE_FLUSH,
                            null);
                }

                break;
            case R.id.mute_btn:
                if(textToSpeech != null) {
                    textToSpeech.stop();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(textToSpeech!=null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
