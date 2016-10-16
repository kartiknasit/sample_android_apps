package com.kartik.sampleapps.androidinterviewapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FrontPage extends AppCompatActivity implements View.OnClickListener{

    Button simple_qustions_btn, tough_questions_btn, see_other_apps_btn, rate_app_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_page);

        simple_qustions_btn = (Button) findViewById(R.id.simple_questions_btn);
        tough_questions_btn = (Button) findViewById(R.id.tough_questions_btn);
        see_other_apps_btn = (Button) findViewById(R.id.see_our_other_apps_btn);
        rate_app_btn = (Button) findViewById(R.id.rate_app_btn);

        simple_qustions_btn.setOnClickListener(this);
        tough_questions_btn.setOnClickListener(this);
        see_other_apps_btn.setOnClickListener(this);
        rate_app_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.simple_questions_btn:
                Intent simple_questions_activity = new Intent(this, SimpleQuestionsActivity.class);
                startActivity(simple_questions_activity);
                break;
            case R.id.tough_questions_btn:
                Intent tough_questions_activity = new Intent(this, ToughQuestionsActivity.class);
                startActivity(tough_questions_activity);
                break;
            case R.id.see_our_other_apps_btn:
                Uri otherAppUri = null;
                Intent otherAppIntent = null;
                try {
                    otherAppUri = Uri.parse("market://developer?id=Scoompa");
                    otherAppIntent = new Intent(Intent.ACTION_VIEW, otherAppUri);
                    startActivity(otherAppIntent);
                } catch (ActivityNotFoundException activityNotFound) {
                    otherAppUri = Uri.parse("https://play.google.com/store/apps/developer?id=Scoompa");
                    otherAppIntent = new Intent(Intent.ACTION_VIEW, otherAppUri);
                    startActivity(otherAppIntent);
                }
                break;
            case R.id.rate_app_btn:
                Uri appUri = null;
                Intent rateApp = null;
                try {
                    appUri = Uri.parse("market://details?id=net.mustafaozcan.setcontactphoto");
                    rateApp = new Intent(Intent.ACTION_VIEW, appUri);
                    startActivity(rateApp);
                } catch (ActivityNotFoundException activityNotFound) {
                    appUri = Uri.parse("https://play.google.com/store/apps/details?id=net.mustafaozcan.setcontactphoto");
                    rateApp = new Intent(Intent.ACTION_VIEW, appUri);
                    startActivity(rateApp);
                }
                break;
            default:
                break;
        }

    }
}
