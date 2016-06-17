package com.example.monkeyd.qlnhom.Main.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.monkeyd.qlnhom.Main.Bussiness.Bussiness;
import com.example.monkeyd.qlnhom.Main.Data_Object.DTO_Nhom;
import com.example.monkeyd.qlnhom.R;

public class ThemNhom extends AppCompatActivity {

    EditText tennhom, truongnhom, soluong;
    Button btnThoat,btnThem;
    private Bussiness buss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buss= new Bussiness(ThemNhom.this);
        setContentView(R.layout.activity_them_nhom);
        //Anh xa
        btnThoat = (Button)findViewById(R.id.btnThoat);
        btnThem = (Button)findViewById(R.id.btnThemNhom);
        tennhom = (EditText)findViewById(R.id.edTenNhom);
        truongnhom = (EditText)findViewById(R.id.edNhomTruong);
        soluong = (EditText)findViewById(R.id.edSoLuong);
        //
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id= 0;
                String ten = tennhom.getText().toString();
                String truong = truongnhom.getText().toString();
                int sl = Integer.valueOf( soluong.getText().toString());
                if(buss._insertLop(new DTO_Nhom(id,ten,truong,sl)))
                    Toast.makeText(ThemNhom.this,"Thêm nhóm "+ten+ " thành công !.",Toast.LENGTH_SHORT).show();
                else  Toast.makeText(ThemNhom.this,"Thêm nhóm "+ten+ " thất bại !.",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
