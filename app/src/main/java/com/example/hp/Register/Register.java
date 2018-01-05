package com.example.hp.Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hp.rg.MainPage;
import com.example.hp.rg.R;

/**
 * Created by Rashi on 20-07-2016.
 */
public class Register extends AppCompatActivity {
    Button register;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        register  = (Button)findViewById(R.id.go);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, RegisterVehicle.class);
                startActivity(i);
            }
        });
    }
}
