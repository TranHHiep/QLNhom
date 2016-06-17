package com.example.monkeyd.qlnhom.Main.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monkeyd.qlnhom.Main.Bussiness.Bussiness;
import com.example.monkeyd.qlnhom.Main.Data_Object.DTO_DuAn;
import com.example.monkeyd.qlnhom.R;

public class HienThongTinDuAn extends AppCompatActivity {

    TextView ten, ngaybatdau, ngayketthuc,thongtinduan;
    EditText tiendo;
    Button capnhat, xoa;
    //
    int mada,manhom;
    String tenduan,ngaybd,ngaykt,tdo;
    //
    private Bussiness bussiness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thong_tin_du_an);
        bussiness = new Bussiness(HienThongTinDuAn.this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        anhxa();
        Nhandulieu();
        setTitle(tenduan);
        hienthidulieu();
        capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = getIntent();
                int mmmaduan = it.getIntExtra("maduan",0);
                String mmtenduan = ten.getText().toString();
                String mmngaybatdau = ngaybatdau.getText().toString();
                String mmngayketthuc = ngayketthuc.getText().toString();
                String tiendocu = thongtinduan.getText().toString();
                String mmtiendo = tiendocu + "\n" + tiendo.getText().toString();
                int mmmanhom = it.getIntExtra("manhom",0);

                if(bussiness._updateDuAn(new DTO_DuAn(mmmaduan,mmtenduan,mmngaybatdau,mmngayketthuc,mmtiendo,mmmanhom)))
                {
                    Toast.makeText(HienThongTinDuAn.this,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bussiness._deleteDuAn(new DTO_DuAn(mada,tenduan,ngaybd,ngaykt,tdo,manhom)))
                {
                    finish();
                }
            }
        });
    }
    private void hienthidulieu()
    {
        ten.setText(tenduan);
        ngaybatdau.setText("Ngày bắt đầu : " +ngaybd);
        ngayketthuc.setText("Ngày kết thúc : "+ngaykt);
        thongtinduan.setText("" +tdo);
    }
    private void anhxa()
    {
        ten = (TextView)findViewById(R.id.hientenduan);
        ngaybatdau = (TextView)findViewById(R.id.hienngaybatdau);
        ngayketthuc = (TextView)findViewById(R.id.hienngayketthuc);
        tiendo = (EditText)findViewById(R.id.hientiendo);
        capnhat = (Button)findViewById(R.id.Capnhattiendo);
        xoa = (Button)findViewById(R.id.XoaDuAn);
        thongtinduan = (TextView)findViewById(R.id.tiendoduan);


    }
    private void Nhandulieu(){

        Intent it = getIntent();
        mada = it.getIntExtra("maduan",0);
        tenduan= it.getStringExtra("tenduan");
        ngaybd = it.getStringExtra("ngaybatdau");
        ngaykt = it.getStringExtra("ngayketthuc");
        tdo = it.getStringExtra("tiendo");
        manhom = it.getIntExtra("manhom",0);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
