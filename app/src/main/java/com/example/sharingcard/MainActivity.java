package com.example.sharingcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MySharingCard sharingCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharingCard=findViewById(R.id.my_sharing_card);
        init();
    }

    private void init() {
        sharingCard.getSocialBtn1().setOnClickListener(this);
        sharingCard.getSocialBtn2().setOnClickListener(this);
        sharingCard.getSocialBtn3().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id= v.getId();
        if(id==sharingCard.socialBtn1.getId() ||
                id==sharingCard.socialBtn2.getId() ||
                id==sharingCard.socialBtn3.getId() ){
            Toast.makeText(this  , ((Button)v).getText(),Toast.LENGTH_SHORT).show();

        }
    }
}
