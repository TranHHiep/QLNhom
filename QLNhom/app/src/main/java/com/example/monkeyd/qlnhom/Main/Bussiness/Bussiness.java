package com.example.monkeyd.qlnhom.Main.Bussiness;

import android.content.Context;
import android.widget.Toast;

import com.example.monkeyd.qlnhom.Main.Data_Object.DTO_DuAn;
import com.example.monkeyd.qlnhom.Main.Data_Object.DTO_Nhom;
import com.example.monkeyd.qlnhom.Main.Data_Object.DTO_ThanhVien;
import com.example.monkeyd.qlnhom.Main.MySQLHelper.MySqlHelper;

import java.util.List;

/**
 * Created by MonkeyD on 6/11/2016.
 */
public class Bussiness {
    //Lớp này là lớp trung gian lấy dữ liệu từ DTO... giao tiếp xuống Database... thực thi xong thì trả về giao diện..
    //Bài này chúng ta trả qua Adapter... Tại vì Listview nó lấy dữ liệu tu Adapter để hiển thị

    //Bước 1: Khai báo các biến cần thiết
    private MySqlHelper mySQLite;
    private Context mContext;

    //hàm tạo, Viết class thì nhớ phải viết mấy cái hàm tạo này, không là ngáo, nó không hiểu, vì khi gọi tới class thì nó sẽ gọi
    //tới Hàm tạo ( hướng đối tượng)
    //_____________________________________________________________________________________________________________
    public Bussiness(Context context)
    {
        mContext = context;
        mySQLite = new MySqlHelper(context);
        //trước tiên thì không cần quan tâm mấy cái thằng khỉ trong hàm tạo này... cứ viết theo là được
        //hiểu được nó thì cỡ lâp trình viên Android mới đủ trình
    }
    //_____________________________________________________________________________________________________________
    public DTO_Nhom layNHOMbyMANHOM(int manhom)
    {
        //chỗ này mới chua chát nè, lấy dữ liệu ở đâu lên ? . Lấy ở Database.
        // Thì trong class SQLhelper phải có hàm trả về giá trị cần lấy
        //Ví dụ ở đây mình lấy Sinh Viên dựa vào mã sv.... thì trong SQLHelper phải có hàm trả về SinhVien đó
        // Lúc này qua lớp MySQLhelper viết hàm lấy giá trị sinh viên cần trả về đó
        return mySQLite._laynhomTheoMANHOM(manhom);
    }

    public List<DTO_Nhom> layNHOMbyTENNHOM(String tennhom)
    {
        return mySQLite._laynhomTheoTENNHOM(tennhom);
    }
    //Hàm lấy tất cả sinh viên
    public List<DTO_Nhom> layNhomALL()
    {
        return mySQLite._layNhomALL();
    }
    //Toi day la xong 2 ham lay du lieu tu Database len
    // OK

    public boolean _insertLop(DTO_Nhom _nhom)
    {
        return mySQLite._themNhom(_nhom);
    }
    public boolean _deleteLop(DTO_Nhom _nhom)
    {
        boolean result = mySQLite._xoaNhom(_nhom.getId());
        if (result) {
            Toast.makeText(mContext, "Đã xóa item " + _nhom.getTennhom(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Xóa không thành công", Toast.LENGTH_SHORT).show();
        }
        return result;
    }


    //_______________________________________________________________________________________________________________
    public DTO_ThanhVien layTHANHVIENbyMATV(int mathanhvien)
    {
        return mySQLite._layThanhVienTheoMATHANHVIEN(mathanhvien);
    }
    public List<DTO_ThanhVien> layTHANHVIENALLbyMANHOM(int manhom)
    {
        return  mySQLite._layTHANHVIENALLBYMANHOM(manhom);
    }
    public List<DTO_ThanhVien> layTHANHVIENALL()
    {
        return mySQLite._layTHANHVIENALL();
    }
    public boolean _insertThanhVien(DTO_ThanhVien _thanhVien)
    {
        return mySQLite._themThanhVien(_thanhVien);
    }
    public boolean _deleteThanhVien(DTO_ThanhVien _thanhvien)
    {
        boolean result = mySQLite._xoaThanhVien(_thanhvien.getMathanhvien());
        if (result) {
            Toast.makeText(mContext, "Đã xóa item " + _thanhvien.getTenthanhvien(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Xóa không thành công", Toast.LENGTH_SHORT).show();
        }
        return result;
    }
    public boolean _deleteThanhVienTheoMaNhom(DTO_ThanhVien _thanhvien)
    {
        boolean result = mySQLite._xoaThanhVienTheoMaNhom(_thanhvien.getManhom());
        if (result) {
            Toast.makeText(mContext, "Đã xóa item " + _thanhvien.getTenthanhvien(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Xóa không thành công", Toast.LENGTH_SHORT).show();
        }
        return result;

    }
    //_______________________________________________________________________________________________________________

    //
    public DTO_DuAn layDUANbyMADA(int maduan)
    {
        return mySQLite._layDUANtheoMADA(maduan);
    }
    public List<DTO_DuAn> layDUANbyMANHOM(int manhom)
    {
        return  mySQLite._layDUANTHEOMANHOM(manhom);
    }
    public List<DTO_DuAn> layDUANALL()
    {
        return mySQLite._layDUANALL();
    }
    public boolean _insertDuAn(DTO_DuAn _duan)
    {
        return mySQLite._themDuAn(_duan);
    }

    public boolean _updateDuAn(DTO_DuAn _duan)
    {
        return  mySQLite._suaDuAn(_duan);
    }

    public boolean _deleteDuAn(DTO_DuAn _duan)
    {
        boolean result = mySQLite._xoaDuAn(_duan.getMada());
        if (result) {
            Toast.makeText(mContext, "Đã xóa dự án  " + _duan.getTenda()+ " thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Xóa không thành công", Toast.LENGTH_SHORT).show();
        }
        return result;
    }
    public boolean _deleteDuAnTheoMaNhom(DTO_DuAn _duan)
    {
        boolean result = mySQLite._xoaDuAnTheoMaNhom(_duan.getManhom());
        if (result) {
            Toast.makeText(mContext, "Đã xóa dự án  " + _duan.getTenda()+ " thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Xóa không thành công", Toast.LENGTH_SHORT).show();
        }
        return result;
    }
    //_______________________________________________________________________________________________________________

}

