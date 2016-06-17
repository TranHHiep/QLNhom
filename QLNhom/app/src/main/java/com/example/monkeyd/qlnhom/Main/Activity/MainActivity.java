package com.example.monkeyd.qlnhom.Main.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.monkeyd.qlnhom.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnshow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnshow = (Button)findViewById(R.id.button);
        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itsv = new Intent(MainActivity.this,Nhom.class);
                startActivity(itsv);
        }
        });
    }
}
