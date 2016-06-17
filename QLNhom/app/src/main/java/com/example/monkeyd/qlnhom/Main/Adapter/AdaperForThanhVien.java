package com.example.monkeyd.qlnhom.Main.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.monkeyd.qlnhom.Main.Bussiness.Bussiness;
import com.example.monkeyd.qlnhom.Main.Data_Object.DTO_ThanhVien;
import com.example.monkeyd.qlnhom.R;

import java.util.List;

/**
 * Created by MonkeyD on 6/12/2016.
 */
public class AdaperForThanhVien extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInFlat;
    private List<DTO_ThanhVien> _thanhvien;
    private Bussiness bussiness;
    private ViewHoler holer;
    //Đỏ lòm .... ViewHoler lúc này là 1 class do chúng ta tạo
    private class ViewHoler
    {
        //nhớ là private vì nó chỉ tồn tại nội bộ trong class này
        ImageView ivXoa;
        TextView  txttensv; // anh xa dau? findviewbyID
    }
    public AdaperForThanhVien(Context context, Bussiness bus, List<DTO_ThanhVien> dto_thanhViens)
    {
        mContext = context;
        bussiness = bus;
        _thanhvien = dto_thanhViens;
        mInFlat =(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Xong hàm tạo
    }

    public int getCount()
    {

        return _thanhvien.size();
    }

    public View getView(int position, View view, ViewGroup arg2)
    {
        if(view == null)
        {
            view = mInFlat.inflate(R.layout.row_thanhvien,null);
            holer = new ViewHoler();

            holer.txttensv = (TextView)view.findViewById(R.id.txttenthanhvien);
            view.setTag(holer);

        }
        else
        {
            holer = (ViewHoler)view.getTag();
        }
        final DTO_ThanhVien _thanhvien1 =  _thanhvien.get(position);

        holer.txttensv.setText(String.valueOf(_thanhvien1.getTenthanhvien()));
        /*holer.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        return view;

    }
    public void notifyDataSetChanged()
    {

        _thanhvien = bussiness.layTHANHVIENALL();
        super.notifyDataSetChanged();
    }

    public long getItemId(int position)
    {
        return 0;
    }

    public Object getItem(int position)
    {
        return _thanhvien.get(position);
    }

}
