package com.example.hp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.hp.rg.R;

/**
 * Created by Rashi on 17-01-2017.
 */

public class FuelList extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fuel_list);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.one);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FuelList.this,FuelStatus.class);
                startActivity(i);
            }
        });
    }
}
