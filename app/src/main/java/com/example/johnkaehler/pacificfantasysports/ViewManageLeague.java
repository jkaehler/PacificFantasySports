package com.example.johnkaehler.pacificfantasysports;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewManageLeague extends AppCompatActivity implements View.OnClickListener {

    TextView tvGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_manage_league);

        tvGoBack = (TextView) findViewById(R.id.tvGoBack);
        tvGoBack.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

    }
}
