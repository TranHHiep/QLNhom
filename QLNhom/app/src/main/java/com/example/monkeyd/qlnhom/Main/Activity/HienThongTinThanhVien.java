package com.example.monkeyd.qlnhom.Main.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.monkeyd.qlnhom.Main.Bussiness.Bussiness;
import com.example.monkeyd.qlnhom.Main.Data_Object.DTO_ThanhVien;
import com.example.monkeyd.qlnhom.R;

public class HienThongTinThanhVien extends AppCompatActivity {

    TextView txtten,txtlop,txtdienthoai,txtsothich,txtsotruong,txtsodoan;
    String tenthanhvien, lop,dienthoai,sothich,sotruong,sodoan;
    private Bussiness bussiness;
    int mathanhvien;
    Button btnxoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bussiness = new Bussiness(HienThongTinThanhVien.this);
        setContentView(R.layout.activity_hien_thong_tin_thanh_vien);
        layextra();
        anhxa();
        setTitle("Thông tin thành viên");
        hienthi();
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent id = getIntent();
                mathanhvien = id.getIntExtra("id",0);
                int manhom = id.getIntExtra("manhom",0);
                if(bussiness._deleteThanhVien(new DTO_ThanhVien(mathanhvien,tenthanhvien,lop,dienthoai,sotruong,sothich,sodoan,manhom)))
                {
                    finish();
                }
            }
        });
    }

    private void laygiatri()
    {

    }
    private void hienthi()
    {
        txtten.setText(tenthanhvien);
        txtlop.setText("Lớp : "+lop);
        txtsotruong.setText("Sở trường : "+sotruong);
        txtsothich.setText("Sở thích : "+sothich);
        txtdienthoai.setText("Điện thoại : "+dienthoai);
        txtsodoan.setText("Sở đoản : "+sodoan);
    }
    private void layextra()
    {
        Intent it = getIntent();
         tenthanhvien = it.getStringExtra("tenthanhvien");
         lop = it.getStringExtra("lop");
         dienthoai = it.getStringExtra("dienthoai");
         sothich = it.getStringExtra("sothich");
         sotruong = it.getStringExtra("sotruong");
         sodoan = it.getStringExtra("sodoan");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void anhxa()
    {
        txtdienthoai = (TextView)findViewById(R.id.hiendienthoai);
        txtlop = (TextView)findViewById(R.id.hienlop);
        txtsodoan = (TextView)findViewById(R.id.hiensodoan);
        txtsothich = (TextView)findViewById(R.id.hiensothich);
        txtsotruong = (TextView)findViewById(R.id.hiensotruong);
        txtten = (TextView)findViewById(R.id.hiententhanhvien);
        btnxoa = (Button)findViewById(R.id.btnXoaThanhVien);
    }
}
