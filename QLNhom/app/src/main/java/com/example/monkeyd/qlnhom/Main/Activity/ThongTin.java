package com.example.monkeyd.qlnhom.Main.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.monkeyd.qlnhom.Main.Adapter.AdaperForThanhVien;
import com.example.monkeyd.qlnhom.Main.Adapter.AdapterForDuAn;
import com.example.monkeyd.qlnhom.Main.Bussiness.Bussiness;
import com.example.monkeyd.qlnhom.Main.Data_Object.DTO_DuAn;
import com.example.monkeyd.qlnhom.Main.Data_Object.DTO_Nhom;
import com.example.monkeyd.qlnhom.Main.Data_Object.DTO_ThanhVien;
import com.example.monkeyd.qlnhom.Main.RoundImage.RoundImage;
import com.example.monkeyd.qlnhom.R;

import java.util.List;

public class ThongTin extends AppCompatActivity {
    ListView lwthanhvien, lwduan;
    ImageView imghinhanh;
    RoundImage roundImage;
    Button btnThemThanhVien, btnThemDuAn, btnXoaNhom;
    //____________________________________
    private Bussiness bussiness;
    private DTO_ThanhVien _thanhvien;
    private DTO_DuAn _duan;
    //_______________
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

        anhxa();
        lamchohinhnotron();
        Intent it = getIntent();
        String chuoi = it.getStringExtra("tennhom");
        final int manhom1 = it.getIntExtra("manhom",0);
        setTitle("Thông tin nhóm "+chuoi);




        //this.showLogoOnActionBar(this);
        //...
        //LAm cho listview thanhvien
        bussiness = new Bussiness(ThongTin.this);
        final List<DTO_ThanhVien> dto_thanhViens = bussiness.layTHANHVIENALLbyMANHOM(manhom1);
        final AdaperForThanhVien _thanhvienAdapter = new AdaperForThanhVien(ThongTin.this,bussiness,dto_thanhViens);
        if(dto_thanhViens.size() > 0)
        {
            lwthanhvien.setAdapter(_thanhvienAdapter);
        }
        //..
        //Lam cho listview Duan
        final List<DTO_DuAn> dtoduan1 = bussiness.layDUANbyMANHOM(manhom1);
        final AdapterForDuAn _duanAdapter = new AdapterForDuAn(ThongTin.this,bussiness,dtoduan1);
        if(dtoduan1.size() > 0)
        {
            lwduan.setAdapter(_duanAdapter);
        }

        //Su kien click cua listview
        lwthanhvien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(ThongTin.this,HienThongTinThanhVien.class);
                _thanhvien = (DTO_ThanhVien) parent.getAdapter().getItem(position);
                it.putExtra("tenthanhvien",String.valueOf(_thanhvien.getTenthanhvien()));
                //Toast.makeText(ThongTin.this,""+_thanhvien.getDienthoai(),Toast.LENGTH_LONG).show();
                it.putExtra("id",Integer.valueOf(_thanhvien.getMathanhvien()));
                it.putExtra("lop",String.valueOf(_thanhvien.getLop()));
                it.putExtra("dienthoai",String.valueOf(_thanhvien.getDienthoai()));
                it.putExtra("sothich",String.valueOf(_thanhvien.getSothich()));
                it.putExtra("sotruong",String.valueOf(_thanhvien.getSotruong()));
                it.putExtra("sodoan",String.valueOf(_thanhvien.getSodoan()));
                it.putExtra("manhom",Integer.valueOf(_thanhvien.getManhom()));


               /* final Dialog _dialog = new Dialog(ThongTin.this);
                _dialog.setTitle("Thông tin");
                _dialog.setContentView(R.layout.activity_hien_thong_tin_thanh_vien);
                _dialog.show();*/
                startActivity(it);
            }
        });
        //listview du an

        lwduan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(ThongTin.this,HienThongTinDuAn.class);
                _duan = (DTO_DuAn) parent.getAdapter().getItem(position);
                it.putExtra("tenduan",String.valueOf(_duan.getTenda()));
                it.putExtra("maduan",Integer.valueOf(_duan.getMada()));
                it.putExtra("ngaybatdau",String.valueOf(_duan.getNgaybatdau()));
                it.putExtra("ngayketthuc",String.valueOf(_duan.getNgayketthuc()));
                it.putExtra("tiendo", String.valueOf(_duan.getTiendo()));
                it.putExtra("manhom",Integer.valueOf(_duan.getManhom()));

                startActivity(it);
            }
        });









//*******************************************************************************************************************

//_________________________________________________________________________________________________________________________
        //Cac su kien cac nut click
        btnThemThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ThongTin.this, ThemThanhVien.class);
                it.putExtra("manhom",manhom1);
                startActivity(it);
            }
        });
        btnThemDuAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ThongTin.this, ThemDuAn.class);
                it.putExtra("manhom",manhom1);
                startActivity(it);
            }
        });
        //
        btnXoaNhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = getIntent();
                String tennhom = it.getStringExtra("tennhom");
                int manhom1 = it.getIntExtra("manhom",0);
                String nhomtruong = it.getStringExtra("nhomtruong");
                int soluong = it.getIntExtra("soluong",0);
                if(bussiness._deleteLop(new DTO_Nhom(manhom1,tennhom,nhomtruong,soluong)))
                {
                    bussiness._deleteThanhVienTheoMaNhom(new DTO_ThanhVien(0,"","","","","","",manhom1));
                    bussiness._deleteDuAnTheoMaNhom(new DTO_DuAn(0,"","","","",manhom1));
                }
            }
        });
//*******************************************************************************************************************
//_________________________________________________________________________________________________________________________________
        //
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

    public void showLogoOnActionBar(Activity activity)
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.lachong);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }
    private  void lamchohinhnotron()
    {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.lachong);
        roundImage = new RoundImage(bitmap);
        imghinhanh.setImageDrawable(roundImage);
    }


    private  void anhxa()
    {

        //Ánh xạ
        btnThemThanhVien = (Button)findViewById(R.id.btnthemthanhvien);
        btnThemDuAn = (Button)findViewById(R.id.btnthemduan);
        lwthanhvien = (ListView)findViewById(R.id.listthanhvien);
        lwduan = (ListView)findViewById(R.id.listduan);
        imghinhanh= (ImageView)findViewById(R.id.hinhanh);
        btnXoaNhom = (Button)findViewById(R.id.btnxoanhom);
        //
    }

    @Override
    protected void onPostResume() {
        if(lwthanhvien!=null){//goi oncreate la !=null vi findviewbyid
//            load lai viet trong nay
            Intent it = getIntent();
            final int manhom1 = it.getIntExtra("manhom",0);
            final List<DTO_ThanhVien> dto_thanhViens = bussiness.layTHANHVIENALLbyMANHOM(manhom1);
            final AdaperForThanhVien _thanhvienAdapter = new AdaperForThanhVien(ThongTin.this,bussiness,dto_thanhViens);
            if(dto_thanhViens.size() > 0)
            {
                lwthanhvien.setAdapter(_thanhvienAdapter);
            }
            //..
        }
        if(lwduan != null)
        {
            Intent id = getIntent();
            final int manhom1 = id.getIntExtra("manhom",0);
            final List<DTO_DuAn> dtoduan1 = bussiness.layDUANbyMANHOM(manhom1);
            final AdapterForDuAn _duanAdapter = new AdapterForDuAn(ThongTin.this,bussiness,dtoduan1);
            if(dtoduan1.size() > 0)
            {
                lwduan.setAdapter(_duanAdapter);
            }
        }
        super.onPostResume();
    }
}
