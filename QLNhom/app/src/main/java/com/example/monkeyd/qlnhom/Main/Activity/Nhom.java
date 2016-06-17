package com.example.monkeyd.qlnhom.Main.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.monkeyd.qlnhom.Main.Adapter.AdapterForNhom;
import com.example.monkeyd.qlnhom.Main.Bussiness.Bussiness;
import com.example.monkeyd.qlnhom.Main.Data_Object.DTO_Nhom;
import com.example.monkeyd.qlnhom.R;

import java.util.List;

public class Nhom extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ListView lw;
    Button add;
    TextView txttennhom;
    int id = 0;
    private Bussiness bussiness;
    private DTO_Nhom _nhom;


    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bussiness = new Bussiness(Nhom.this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        setContentView(R.layout.activity_nhom);
        setTitle("Danh Sách Nhóm.");
        anhxa();
        //

        //
        final List<DTO_Nhom> _listnhom = bussiness.layNhomALL();
        final AdapterForNhom _nhomAdapter = new AdapterForNhom(Nhom.this,bussiness,_listnhom);
        if(_listnhom.size() > 0)

            lw.setAdapter(_nhomAdapter);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  /*  String tennhom= "Lucifer ";
                    String nhomtruong = "Nguyễn Ngọc Trâm ";
                    int soluong = 5;
                    if(bussiness._insertLop(new DTO_Nhom(id,tennhom,nhomtruong,soluong)));
                    _nhomAdapter.notifyDataSetChanged();*/
                Intent it = new Intent(Nhom.this,ThemNhom.class);
                startActivity(it);

            }
        });
        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(Nhom.this,ThongTin.class);
                _nhom = (DTO_Nhom)parent.getAdapter().getItem(position);
                it.putExtra("tennhom",_nhom.getTennhom());
                it.putExtra("manhom",Integer.valueOf(_nhom.getId()));
                it.putExtra("nhomtruong",_nhom.getNhomtruong());
                it.putExtra("soluong",_nhom.getSoluong());
                startActivity(it);


            }
        });

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.search_view, menu);
        MenuItem itemSearch = menu.findItem(R.id.action_seach);
        searchView = (SearchView) itemSearch.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
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
    //
    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("ketqua",newText);
        do{
            if(!newText.isEmpty()) {
                do {
                    final List<DTO_Nhom> _listnhommoi = bussiness.layNHOMbyTENNHOM(String.valueOf(newText));
                    final AdapterForNhom _nhomAdapterMoi = new AdapterForNhom(Nhom.this, bussiness, _listnhommoi);
                    if (_listnhommoi.size() > 0) {
                        lw.setAdapter(_nhomAdapterMoi);
                        //_nhomAdapterMoi.notifyDataSetChanged();
                    }
                    return true;
                }while (true);


            }
            else
            {
                do {
                    final List<DTO_Nhom> _listnhom = bussiness.layNhomALL();
                    final AdapterForNhom _nhomAdapter = new AdapterForNhom(Nhom.this,bussiness,_listnhom);
                    if(_listnhom.size() > 0)
                        lw.setAdapter(_nhomAdapter);
                    return true;
                }while(true);
            }

        } while (true);

    }
    //
    @Override
    public boolean onQueryTextSubmit(String arg0) {
        // TODO Auto-generated method stub
        return true;
    }


    @Override
    protected void onPostResume() {
        if(lw != null)
        {
            final List<DTO_Nhom> _listnhom = bussiness.layNhomALL();
            final AdapterForNhom _nhomAdapter = new AdapterForNhom(Nhom.this,bussiness,_listnhom);
            if(_listnhom.size() > 0)

                lw.setAdapter(_nhomAdapter);
        }

        super.onPostResume();
    }

    private void  anhxa()
    {
        lw =  (ListView)findViewById(R.id.lwnhom);
        add = (Button) findViewById(R.id.btnadd);
        txttennhom = (TextView)findViewById(R.id.txttennhomcanlay);
    }

}
