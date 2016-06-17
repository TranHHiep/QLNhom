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
import com.example.monkeyd.qlnhom.Main.Data_Object.DTO_DuAn;
import com.example.monkeyd.qlnhom.R;

public class ThemDuAn extends AppCompatActivity {
    Button btnthem, btnthoat;
    EditText tenduan, ngaybatdau,ngayketthuc;
    //______________________________________________
    private Bussiness bussiness;


    //______________________________________________
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_du_an);
        anhxa();
        bussiness = new Bussiness(ThemDuAn.this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Thêm dự án");

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    int id=0;
                    String tenda = tenduan.getText().toString();
                    String ngaybd = ngaybatdau.getText().toString();
                    String ngaykt = ngayketthuc.getText().toString();
                    String tiendo = "";
                    Intent it = getIntent();
                    int manhom2 = it.getIntExtra("manhom",0);

                    if(bussiness._insertDuAn(new DTO_DuAn(id,tenda,ngaybd,ngaykt,tiendo,manhom2)))
                    {
                        Toast.makeText(ThemDuAn.this,"Thêm dự án  "+tenda+" thành công !",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(ThemDuAn.this,"Thêm dự án  "+tenda+ " thất bại !",Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex)
                {
                    Toast.makeText(ThemDuAn.this,"Thêm dự án thất bại !",Toast.LENGTH_SHORT).show();
                }
                finally {
                    resetcontrol();
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
        btnthem = (Button)findViewById(R.id.btnThemDuAn);
        btnthoat = (Button)findViewById(R.id.btnThoatDuAn);
        tenduan = (EditText)findViewById(R.id.edTenDuAn);
        ngaybatdau = (EditText)findViewById(R.id.edNgayBatDau);
        ngayketthuc = (EditText)findViewById(R.id.edNgayKetThuc);
    }
    private void resetcontrol()
    {
        tenduan.setText("");
        ngaybatdau.setText("");
        ngayketthuc.setText("");
    }
}
