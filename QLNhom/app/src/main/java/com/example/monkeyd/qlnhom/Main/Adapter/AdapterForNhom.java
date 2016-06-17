package com.example.monkeyd.qlnhom.Main.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.monkeyd.qlnhom.Main.Bussiness.Bussiness;
import com.example.monkeyd.qlnhom.Main.Data_Object.DTO_Nhom;
import com.example.monkeyd.qlnhom.R;

import java.util.List;

/**
 * Created by MonkeyD on 6/11/2016.
 */
public class AdapterForNhom extends BaseAdapter{
    //nhớ la nó phải Extends
    //CHÚ Ý: Mỗi cái LISTVIEW CÓ adapter riêng nha.
    //Khai báo các biến cần thiết: Phải xem kĩ chỗ này không là NGÁO
    private Context mContext;
    private LayoutInflater mInFlat;
    private List<DTO_Nhom> _nhom;
    private Bussiness bussiness;
    private ViewHoler holer;
    //Đỏ lòm .... ViewHoler lúc này là 1 class do chúng ta tạo
    private class ViewHoler
    {
        //nhớ là private vì nó chỉ tồn tại nội bộ trong class này
        ImageView ivXoa;
        TextView txtmasv, txttensv, txtdiem,ivSTT; // anh xa dau? findviewbyID
    }
    //Xong .. hết đỏ
    //Giờ viết hàm tạo
    public AdapterForNhom(Context context, Bussiness bus, List<DTO_Nhom> _listnhom)
    {
        mContext = context;
        bussiness = bus;
        _nhom = _listnhom;
        mInFlat =(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Xong hàm tạo
    }
    //Tới lúc này tên class vẫn còn gạch đỏ lòm.... Chỉa vô thì nó kêu chưa tạo hàm GetCount
    //Vậy hàm GetCount để làm gì ? Tại sao phải tạo nó
    //

    //Tạo hàm getcount
    public int getCount()
    {
        //hàm getCount lúc này nó là một phương thức nạp đè nha.
        return _nhom.size();
    }

    //Vẫn còn đỏ lòm... chỉa vô nó kêu chưa tạo getView
    //GetView ?
    //

    //Tạo hàm GetView, hàm getview mình tạo ở đây vẫn là phương thức nạp đè nha ( nghĩa là trong hệ thống đã tồn tại hàm GetView rồi)
    public View getView(int position, View view, ViewGroup arg2)
    {
        if(view == null)
        {
            view = mInFlat.inflate(R.layout.row_nhom,null);
            holer = new ViewHoler();
            //tìm hiểu thử xem tại sao class ViewHoler ko có hàm tạo mà nó vẫn chạy được nhé
            holer.ivSTT = (TextView) view.findViewById(R.id.img);
            holer.txtmasv = (TextView)view.findViewById(R.id.txttennhom);
            holer.txttensv = (TextView)view.findViewById(R.id.txtnhomtruong);
            holer.txtdiem = (TextView)view.findViewById(R.id.txtsoluong); //khai ra toan ko anh xa -_-
            holer.ivXoa = (ImageView)view.findViewById(R.id.ivxoa);
            //ở trên đây là ánh xạ các control của rowSinhvien xuống ViewHoler
            view.setTag(holer);
            //cẩn thận thiếu dòng này
        }
        else
        {
            holer = (ViewHoler)view.getTag();
        }
        final DTO_Nhom _nhom1 =  _nhom.get(position);
        //nhớ là chỗ này 2 biến _sinhvien1 và sinhvien khác nhau nha

        //Chỗ này là lấy các giá trị truyền vào cho ViewHoler
        //chỗ này hơi nhức đầu.. cứ viết theo thôi
        if(_nhom1.getId() == 0 )
        {
            holer.ivSTT.setText("®");
            holer.txtmasv.setText("Lỗi !");
            holer.txttensv.setText("Không tìm thấy kết quả nào.");
            holer.txtdiem.setText("");
        }
        else
        {
            holer.ivSTT.setText(String.valueOf(_nhom1.getId()));
            holer.txtmasv.setText(String.valueOf(_nhom1.getTennhom()));
            holer.txttensv.setText(String.valueOf(_nhom1.getNhomtruong()));
            holer.txtdiem.setText(String.valueOf(_nhom1.getSoluong()));
        }


        holer.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bussiness._deleteLop(_nhom1))
                {

                    notifyDataSetChanged();
                }
            }
        });
        return view;
        //.. nhớ return
    }
    public void notifyDataSetChanged()
    {
        //nhớ viết cho đúng tên hàm này... nếu ko là lỗi
        _nhom = bussiness.layNhomALL();
        super.notifyDataSetChanged();
    }

    //Xong .. Vẫn còn đỏ
    //Tạo tiếp
    public long getItemId(int position)
    {
        return 0;
    }
    //Vẫn đỏ... Tạo tiếp
    public Object getItem(int position)
    {
        return _nhom.get(position);
    }
    //hết đỏ... Xong lớp Adapter

    //Rồi giờ qua Activy viết thôi.. Ở đây mình viết ở ActivitySinhVien nha....
}
