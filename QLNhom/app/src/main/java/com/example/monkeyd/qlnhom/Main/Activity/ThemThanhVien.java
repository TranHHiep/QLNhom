package com.example.monkeyd.qlnhom.Main.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.monkeyd.qlnhom.Main.Bussiness.Bussiness;
import com.example.monkeyd.qlnhom.Main.Data_Object.DTO_ThanhVien;
import com.example.monkeyd.qlnhom.R;

public class ThemThanhVien extends AppCompatActivity {
    //_____________________________________________________
    Button btnthem, btnthoat;
    EditText ten,lop,dienthoai,sothich,sotruong,sodoan;
    //_____________________________________________________
    private Bussiness buss;
    //_____________________________________________________


    //_____________________________________________________
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_thanh_vien);
        //Chú ý no không cho insert thì là thiếu cái này
        buss= new Bussiness(ThemThanhVien.this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Thêm thành viên");
        anhxa();
//Nhận dữ liệu________________________________________________________________________________________________________

//_____________________________________________________________________________________________________________________
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
               {
                int id = 0;
                String hoten = ten.getText().toString();
                String mlop = lop.getText().toString();
                String dt = dienthoai.getText().toString();
                String sthich = sothich.getText().toString();
                String struong = sotruong.getText().toString();
                String sdoan = sodoan.getText().toString();
                Intent it = getIntent();
                int manhom2 = it.getIntExtra("manhom",0);
                if(buss._insertThanhVien(new DTO_ThanhVien(id,hoten,mlop,dt,sthich,struong,sdoan,manhom2)))
                {
                    Toast.makeText(ThemThanhVien.this,"thanh cong",Toast.LENGTH_LONG).show();
                }

                }
                catch(Exception ex)
                {
                    Toast.makeText(ThemThanhVien.this, ""+ex ,Toast.LENGTH_LONG).show();
                }
            }
        });
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
    private void anhxa()
    {
        btnthem = (Button)findViewById(R.id.btnThemThanhVien);
        btnthoat =(Button)findViewById(R.id.btnThoatThemThanhVien);
        ten = (EditText)findViewById(R.id.edTenThanhVien);
        lop = (EditText)findViewById(R.id.edLop);
        dienthoai = (EditText)findViewById(R.id.edDienThoai);
        sothich  = (EditText)findViewById(R.id.edSoThich);
        sotruong = (EditText)findViewById(R.id.edSoTruong);
        sodoan = (EditText)findViewById(R.id.edSoDoan);
    }
}
