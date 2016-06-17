package com.example.monkeyd.qlnhom.Main.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.monkeyd.qlnhom.Main.Bussiness.Bussiness;
import com.example.monkeyd.qlnhom.Main.Data_Object.DTO_DuAn;
import com.example.monkeyd.qlnhom.R;

import java.util.List;

/**
 * Created by MonkeyD on 6/13/2016.
 */
public class AdapterForDuAn extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInFlat;
    private List<DTO_DuAn> _duan;
    private Bussiness bussiness;
    private ViewHoler holer;
    //Đỏ lòm .... ViewHoler lúc này là 1 class do chúng ta tạo
    private class ViewHoler
    {
        //nhớ là private vì nó chỉ tồn tại nội bộ trong class này
        ImageView ivXoa;
        TextView txttenduan, txtngaybatdau,txtngayketthuc; // anh xa dau? findviewbyID
    }
    public AdapterForDuAn(Context context, Bussiness bus, List<DTO_DuAn> dto_duAnList)
    {
        mContext = context;
        bussiness = bus;
        _duan = dto_duAnList;
        mInFlat =(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Xong hàm tạo
    }

    public int getCount()
    {

        return _duan.size();
    }

    public View getView(int position, View view, ViewGroup arg2)
    {
        if(view == null)
        {
            view = mInFlat.inflate(R.layout.row_duan,null);
            holer = new ViewHoler();

            holer.txttenduan = (TextView)view.findViewById(R.id.txttenduan);
            holer.txtngaybatdau = (TextView)view.findViewById(R.id.txtngaybatdau);
            holer.txtngayketthuc = (TextView)view.findViewById(R.id.txtngayketthuc);
            view.setTag(holer);

        }
        else
        {
            holer = (ViewHoler)view.getTag();
        }
        final DTO_DuAn _duan1 =  _duan.get(position);

        holer.txttenduan.setText(String.valueOf(_duan1.getTenda()));
        holer.txtngaybatdau.setText(String.valueOf(_duan1.getNgaybatdau()));
        holer.txtngayketthuc.setText(String.valueOf(_duan1.getNgayketthuc()));
        /*holer.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        return view;

    }
    public void notifyDataSetChanged()
    {

        _duan = bussiness.layDUANALL();
        super.notifyDataSetChanged();
    }

    public long getItemId(int position)
    {
        return 0;
    }

    public Object getItem(int position)
    {
        return _duan.get(position);
    }

}
