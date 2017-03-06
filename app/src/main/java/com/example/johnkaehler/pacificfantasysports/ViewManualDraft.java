package com.example.johnkaehler.pacificfantasysports;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewManualDraft extends AppCompatActivity implements View.OnClickListener{

    Button bRandomDraftOrder, bAssignedDraftOrder;
    TextView tvGoBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_manual_draft);

        bRandomDraftOrder = (Button)findViewById(R.id.RandomDraftOrder);
        bAssignedDraftOrder = (Button)findViewById(R.id.AssignedDraftOrder);
        tvGoBack = (TextView)findViewById(R.id.tvGoBack);

        bRandomDraftOrder.setOnClickListener(this);
        bAssignedDraftOrder.setOnClickListener(this);
        tvGoBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    //TODO: pull all athletes from database for particular school


    //TODO: Pull all members for league
}
