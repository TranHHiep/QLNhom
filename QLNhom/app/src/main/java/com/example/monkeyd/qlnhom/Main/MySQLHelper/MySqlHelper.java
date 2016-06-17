package com.example.monkeyd.qlnhom.Main.MySQLHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.monkeyd.qlnhom.Main.Data_Object.DTO_DuAn;
import com.example.monkeyd.qlnhom.Main.Data_Object.DTO_Nhom;
import com.example.monkeyd.qlnhom.Main.Data_Object.DTO_ThanhVien;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MonkeyD on 6/11/2016.
 */
public class MySqlHelper extends SQLiteOpenHelper {
    private  static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "QLSV";
    private static final String TABLE_THANHVIEN = "tblThanhVien";
    private static final String TABLE_NHOM = "tblNhom";
    private static final String TABLE_DUAN = "tblDuAn";
    private Context mContext;
    //hàm tạo
    public MySqlHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        mContext = context;
    }
    //viết tới đây thì cái tên hàm vẫn còn đỏ lòm
    //Phai viet 2 phuong thuc OnCreate và onUpgrade cho nó..

    public void onCreate(SQLiteDatabase db)
    {
        //CHÚ Ý: Hàm này chỉ chạy một lần DUY NHẤT khi lần đầu tiên chạy app


        //Lúc này chúng ta sẽ gọi các phương thức tạo bảng
        // Khi tao bang thi chu y viet cach cach ra.. neu khong se bi loi
        String bangNhom = "CREATE TABLE " + TABLE_NHOM + "( manhom INTEGER PRIMARY KEY AUTOINCREMENT, tennhom TEXT, nhomtruong TEXT, soluong INTEGER )";
        String bangThanhVien = "CREATE TABLE " + TABLE_THANHVIEN + "( maso INTEGER PRIMARY KEY AUTOINCREMENT, hoten TEXT, lop TEXT, dienthoai TEXT, sothich TEXT, sotruong TEXT, sodoan TEXT, manhom INTEGER  )";
        String bangDuAn = "CREATE TABLE " + TABLE_DUAN + "( id INTEGER PRIMARY KEY AUTOINCREMENT, tenduan TEXT, ngaybatdau TEXT, ngayketthuc TEXT, tiendo TEXT, manhom INTEGER )";
        //tao xong thi Excute no de no thuc thi giong SQLSEver
        db.execSQL(bangNhom);
        db.execSQL(bangThanhVien);//cu an kieu nhu dong duoi luc sau chay lam sao no tao dc
        db.execSQL(bangDuAn);
        //1 lan duy nhat khi app cai cho den luc xoa
        //db.execSQL(bangDuAn);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        //CHÚ Ý : Hàm này chạy sau khi chúng ta Update Version của Chương trình....ví dụ như chúng ta thêm cột, hoặc xóa bảng....

        //viết tới đây thì tên class hết đỏ rồi kìa
        //bắt đầu xét... chỗ này tự hiểu

      //  db.execSQL("DROP TABLE IF EXISTS" + TABLE_DUAN);
        //chỗ này đe update version

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THANHVIEN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DUAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NHOM);
        onCreate(db);

    }
//_______________________________________________________________________________________________________________________
    public boolean _themNhom(DTO_Nhom _nhom)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            ContentValues itemsv = new ContentValues();
            //khoong phai lay id vi no tu tang, nhung qua ben kia phai khai bao cho no = 0 de tu tang
            //  itemsv.put("masv",sv.getMasv());
            itemsv.put("tennhom",_nhom.getTennhom());
            itemsv.put("nhomtruong",_nhom.getNhomtruong());
            itemsv.put("soluong",_nhom.getSoluong());
            try
            {
                db.insert(TABLE_NHOM,null,itemsv);
            }
            catch (Exception ex)
            {
                return false;
            }
            return true;
        }
        finally {
            db.close();
        }
    }
    public boolean _suaNhom(DTO_Nhom _nhom)
    {
        // sửa trên cái Database nào
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues itemContentValues = new ContentValues();
            //cái dòng màu xanh lá cây phải trùng với tên cột trong bảng
            itemContentValues.put("tensv", _nhom.getTennhom());
            itemContentValues.put("nhomtruong",_nhom.getNhomtruong());
            try {
                //update phải có điều kiện where nhé
                db.update(TABLE_NHOM, itemContentValues, "manhom =" + _nhom.getId(), null);
            } catch (Exception ex) {

                return false;
            }

            return true;
        } finally {
            db.close();
        }
    }
    //xóa dựa vào mã sinh viên
    public boolean _xoaNhom(int manhom)
    {
        boolean result = true;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_NHOM, "manhom = " + manhom, null);
        } catch (Exception ex) {
            result = false;
        } finally {
            db.close();
            return result;
        }
    }



    //Xong hàm thêm xóa Sửa... H thì thiết kế giao diện các kiểu đi
    //Nếu giao diện được thiết kế trước rồi thì giờ viết tiếp lớp Business để giao tiếp với Database này
    //Tạo Packer Busines -> Class Business


    public DTO_Nhom _laynhomTheoMANHOM(int manhom)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        //Transaction các kiểu cho nó đỡ lỗi. Không cần hiểu chỗ này.
        db.beginTransaction();
        Cursor cursor = null;
        DTO_Nhom _nhom = null;
        try
        {
            cursor = db.rawQuery("select * from "+TABLE_NHOM + "where manhom = "+manhom,null);
            if(cursor.moveToFirst())
            {
                int ma = cursor.getInt(cursor.getColumnIndex("manhom"));
                String tennhom = cursor.getString(cursor.getColumnIndex("tennhom"));
                String nhomtruong = cursor.getString(cursor.getColumnIndex("nhomtruong"));
                int soluong = cursor.getInt(cursor.getColumnIndex("soluong"));
                _nhom = new DTO_Nhom(ma,tennhom,nhomtruong,soluong);
                //chỗ này mà thấy đỏ lòm là chưa có Constructor bên DTO_SinhVien
            }
            db.setTransactionSuccessful();

        }
        catch (Exception ex)
        {

        }

        finally {

            db.endTransaction();
            cursor.close();
            return _nhom;
        }
    }

    public List<DTO_Nhom> _laynhomTheoTENNHOM(String search)
    {
        List<DTO_Nhom> _nhom = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        //Transaction các kiểu cho nó đỡ lỗi. Không cần hiểu chỗ này.
        db.beginTransaction();
        Cursor cursor = null;

        try
        {
            cursor = db.rawQuery("select * from "+TABLE_NHOM +  " WHERE tennhom  LIKE  '%" +search + "%' " ,null);
            if(cursor.moveToFirst()) {
                while (cursor.isAfterLast() == false)

                {
                    int ma = cursor.getInt(cursor.getColumnIndex("manhom"));
                    String tennhom = cursor.getString(cursor.getColumnIndex("tennhom"));
                    String nhomtruong = cursor.getString(cursor.getColumnIndex("nhomtruong"));
                    int soluong = cursor.getInt(cursor.getColumnIndex("soluong"));
                    _nhom.add(new DTO_Nhom(ma, tennhom, nhomtruong, soluong));
                    cursor.moveToNext();
                    //chỗ này mà thấy đỏ lòm là chưa có Constructor bên DTO_SinhVien
                }
            }
            else
            {
                _nhom =new ArrayList<>();
                _nhom.add(new DTO_Nhom(0  ,"","",0));


            }
            db.setTransactionSuccessful();

        }
        catch (Exception ex)
        {

        }

        finally {

            db.endTransaction();
            cursor.close();
            return _nhom;
        }
    }
    //ham tra ve tat ca sv
    public List<DTO_Nhom> _layNhomALL()
    {
        List<DTO_Nhom> _nhom = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        Cursor cursor = null;
        try
        {
            cursor = db.rawQuery("select * from " + TABLE_NHOM +" order by manhom ASC", null);
            if(cursor.moveToFirst())
            {
                while (cursor.isAfterLast() == false)
                {
                    int ma = cursor.getInt(cursor.getColumnIndex("manhom"));
                    String tennhom = cursor.getString(cursor.getColumnIndex("tennhom"));
                    String nhomtruong = cursor.getString(cursor.getColumnIndex("nhomtruong"));
                    int soluong = cursor.getInt(cursor.getColumnIndex("soluong"));
                    _nhom.add(new DTO_Nhom(ma,tennhom,nhomtruong,soluong));
                    cursor.moveToNext();
                }
            }
            db.setTransactionSuccessful();
        }
        catch (Exception ex)
        {

        }
        finally {
            db.endTransaction();
            cursor.close();
            return _nhom;
        }
    }




    //_______________________________________________________________________________________________________________________
    //Lam cho phan nhanvien
    public boolean _themThanhVien(DTO_ThanhVien _thanhvien)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            ContentValues itemsv = new ContentValues();
            //khoong phai lay id vi no tu tang, nhung qua ben kia phai khai bao cho no = 0 de tu tang
            //  itemsv.put("masv",sv.getMasv());
            //( maso INTEGER PRIMARY KEY AUTOINCREMENT, hoten TEXT, lop TEXT, dienthoai TEXT, sothich TEXT, sotruong TEXT, sodoan TEXT, manhom INTEGER  )";
            itemsv.put("hoten",_thanhvien.getTenthanhvien());
            itemsv.put("lop",_thanhvien.getLop());
            itemsv.put("dienthoai",_thanhvien.getDienthoai());
            itemsv.put("sothich",_thanhvien.getSothich());
            itemsv.put("sotruong",_thanhvien.getSotruong());
            itemsv.put("sodoan",_thanhvien.getSodoan());
            itemsv.put("manhom",_thanhvien.getManhom());
            try
            {
                db.insert(TABLE_THANHVIEN ,null,itemsv);
            }
            catch (Exception ex)
            {
                return false;
            }
            return true;
        }
        finally {
            db.close();
        }
    }
    public boolean _suaThanhVien(DTO_ThanhVien _thanhvien)
    {
        // sửa trên cái Database nào
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues itemContentValues = new ContentValues();
            //cái dòng màu xanh lá cây phải trùng với tên cột trong bảng
            itemContentValues.put("hoten",_thanhvien.getTenthanhvien());
            itemContentValues.put("dienthoai",_thanhvien.getDienthoai());
            try {
                //update phải có điều kiện where nhé
                db.update(TABLE_THANHVIEN, itemContentValues, "maso =" + _thanhvien.getMathanhvien(), null);
            } catch (Exception ex) {

                return false;
            }

            return true;
        } finally {
            db.close();
        }
    }
    //xóa dựa vào mã sinh viên
    public boolean _xoaThanhVien(int mathanhvien)
    {
        boolean result = true;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_THANHVIEN, "maso = " + mathanhvien, null);
        } catch (Exception ex) {
            result = false;
        } finally {
            db.close();
            return result;
        }
    }

    public boolean _xoaThanhVienTheoMaNhom(int manhom)
    {
        boolean result = true;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_THANHVIEN, "manhom = " + manhom, null);
        } catch (Exception ex) {
            result = false;
        } finally {
            db.close();
            return result;
        }
    }


    public DTO_ThanhVien _layThanhVienTheoMATHANHVIEN(int mathanhvien)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        //Transaction các kiểu cho nó đỡ lỗi. Không cần hiểu chỗ này.
        db.beginTransaction();
        Cursor cursor = null;
        DTO_ThanhVien _thanhvien = null;
        try
        {
            cursor = db.rawQuery("select * from "+TABLE_THANHVIEN + "where maso = "+mathanhvien,null);
            if(cursor.moveToFirst())
            {

                int id = cursor.getInt(cursor.getColumnIndex("maso"));
                String hoten = cursor.getString(cursor.getColumnIndex("hoten"));
                String lop = cursor.getString(cursor.getColumnIndex("lop"));
                String sotruong = cursor.getString(cursor.getColumnIndex("sotruong"));
                String dienthoai = cursor.getString(cursor.getColumnIndex("dienthoai"));
                String sothich = cursor.getString(cursor.getColumnIndex("sothich"));
                String sodoan = cursor.getString(cursor.getColumnIndex("sodoan"));
                int manhom = cursor.getInt(cursor.getColumnIndex("manhom"));
                _thanhvien = new DTO_ThanhVien(id,hoten,lop,dienthoai,sothich,sotruong,sodoan,manhom);
                //chỗ này mà thấy đỏ lòm là chưa có Constructor bên DTO_SinhVien
                cursor.moveToNext();
            }
            db.setTransactionSuccessful();

        }
        catch (Exception ex)
        {

        }

        finally {

            db.endTransaction();
            cursor.close();
            return _thanhvien;
        }
    }


    //ham tra ve tat ca sv
    public List<DTO_ThanhVien> _layTHANHVIENALL()
    {
        List<DTO_ThanhVien> _thanhvien = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        Cursor cursor = null;
        try
        {
            cursor = db.rawQuery("select * from "+TABLE_THANHVIEN ,null);
            if(cursor.moveToFirst())
            {
                while (cursor.isAfterLast() == false)
                {
                    int maso = cursor.getInt(cursor.getColumnIndex("maso"));
                    String hoten = cursor.getString(cursor.getColumnIndex("hoten"));
                    String lop = cursor.getString(cursor.getColumnIndex("lop"));
                    String dienthoai = cursor.getString(cursor.getColumnIndex("dienthoai"));
                    String sothich = cursor.getString(cursor.getColumnIndex("sothich"));
                    String sotruong = cursor.getString(cursor.getColumnIndex("sotruong"));
                    String sodoan = cursor.getString(cursor.getColumnIndex("sodoan"));
                    int manhom = cursor.getInt(cursor.getColumnIndex("manhom"));
                    _thanhvien.add(new DTO_ThanhVien(maso,hoten,lop,dienthoai,sothich,sotruong,sodoan,manhom));
                    cursor.moveToNext();
                }
            }
            db.setTransactionSuccessful();
        }
        catch (Exception ex)
        {

        }
        finally {
            db.endTransaction();
            cursor.close();
            return _thanhvien;
        }
    }

    public List<DTO_ThanhVien> _layTHANHVIENALLBYMANHOM(int manhom)
    {
        List<DTO_ThanhVien> _thanhvien = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        Cursor cursor = null;
        try
        {
            cursor = db.rawQuery("select * from " + TABLE_THANHVIEN +" where manhom = "+manhom +" order by maso ASC", null);
            if(cursor.moveToFirst())
            {
                while (cursor.isAfterLast() == false)
                {
                    int id = cursor.getInt(cursor.getColumnIndex("maso"));
                    String hoten = cursor.getString(cursor.getColumnIndex("hoten"));
                    String lop = cursor.getString(cursor.getColumnIndex("lop"));
                    String sotruong = cursor.getString(cursor.getColumnIndex("sotruong"));
                    String dienthoai = cursor.getString(cursor.getColumnIndex("dienthoai"));
                    String sothich = cursor.getString(cursor.getColumnIndex("sothich"));
                    String sodoan = cursor.getString(cursor.getColumnIndex("sodoan"));
                    int manho = cursor.getInt(cursor.getColumnIndex("manhom"));
                    _thanhvien.add(new DTO_ThanhVien(id,hoten,lop,dienthoai,sothich,sotruong,sodoan,manho));
                    cursor.moveToNext();
                }
            }
            db.setTransactionSuccessful();
        }
        catch (Exception ex)
        {

        }
        finally {
            db.endTransaction();
            cursor.close();
            return _thanhvien;
        }
    }



    //_______________________________________________________________________________________________________________________

    //_______________________________________________________________________________________________________________________
    //Làm cho phan Duan

    public boolean _themDuAn(DTO_DuAn _duan)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            ContentValues itemsv = new ContentValues();
            //khoong phai lay id vi no tu tang, nhung qua ben kia phai khai bao cho no = 0 de tu tang
            //  itemsv.put("masv",sv.getMasv());
            //( id INTEGER PRIMARY KEY AUTOINCREMENT, tenduan TEXT, ngaybatdau TEXT, ngaybatdau TEXT, tiendo TEXT, manhom INTEGER )";

            itemsv.put("tenduan",_duan.getTenda());
            itemsv.put("ngaybatdau",_duan.getNgaybatdau());
            itemsv.put("ngayketthuc",_duan.getNgayketthuc());
            itemsv.put("tiendo",_duan.getTiendo());
            itemsv.put("manhom",_duan.getManhom());
            try
            {
                db.insert(TABLE_DUAN ,null,itemsv);
            }
            catch (Exception ex)
            {
                return false;
            }
            return true;
        }
        finally {
            db.close();
        }
    }
    public boolean _suaDuAn(DTO_DuAn _duan)
    {
        // sửa trên cái Database nào
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues itemContentValues = new ContentValues();
            itemContentValues.put("tiendo",_duan.getTiendo());
            try {
                db.update(TABLE_DUAN, itemContentValues, " id = " +_duan.getMada(), null);
            } catch (Exception ex) {

                return false;
            }

            return true;
        } finally {
            db.close();
        }
    }
    //xóa dựa vào mã sinh viên
    public boolean _xoaDuAn(int maduan)
    {
        boolean result = true;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_DUAN, "id = " + maduan, null);
        } catch (Exception ex) {
            result = false;
        } finally {
            db.close();
            return result;
        }
    }
    public boolean _xoaDuAnTheoMaNhom(int manhom)
    {
        boolean result = true;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(TABLE_DUAN, "manhom = " + manhom, null);
        } catch (Exception ex) {
            result = false;
        } finally {
            db.close();
            return result;
        }
    }


    public DTO_DuAn _layDUANtheoMADA(int maduan)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        //Transaction các kiểu cho nó đỡ lỗi. Không cần hiểu chỗ này.
        db.beginTransaction();
        Cursor cursor = null;
        DTO_DuAn _duan = null;
        try
        {
            cursor = db.rawQuery("select * from "+TABLE_DUAN + "where id = "+maduan,null);
            if(cursor.moveToFirst())
            {

                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String tenduan = cursor.getString(cursor.getColumnIndex("tenduan"));
                String ngaybatdau = cursor.getString(cursor.getColumnIndex("ngaybatdau"));
                String ngayketthuc = cursor.getString(cursor.getColumnIndex("ngayketthuc"));
                String tiendo = cursor.getString(cursor.getColumnIndex("tiendo"));
                int manhom = cursor.getInt(cursor.getColumnIndex("manhom"));
                _duan = new DTO_DuAn(id,tenduan,ngaybatdau,ngayketthuc,tiendo,manhom);
                cursor.moveToNext();
            }
            db.setTransactionSuccessful();

        }
        catch (Exception ex)
        {

        }

        finally {

            db.endTransaction();
            cursor.close();
            return _duan;
        }
    }


    //ham tra ve tat ca sv
    public List<DTO_DuAn> _layDUANALL()
    {
        List<DTO_DuAn> _duan = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        Cursor cursor = null;
        try
        {
            cursor = db.rawQuery("select * from "+TABLE_DUAN ,null);
            if(cursor.moveToFirst())
            {
                while (cursor.isAfterLast() == false)
                {
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String tenduan = cursor.getString(cursor.getColumnIndex("tenduan"));
                    String ngaybatdau = cursor.getString(cursor.getColumnIndex("ngaybatdau"));
                    String ngayketthuc = cursor.getString(cursor.getColumnIndex("ngayketthuc"));
                    String tiendo = cursor.getString(cursor.getColumnIndex("tiendo"));
                    int manhom = cursor.getInt(cursor.getColumnIndex("manhom"));
                    _duan.add(new DTO_DuAn(id,tenduan,ngaybatdau,ngayketthuc,tiendo,manhom));
                    cursor.moveToNext();
                }
            }
            db.setTransactionSuccessful();
        }
        catch (Exception ex)
        {

        }
        finally {
            db.endTransaction();
            cursor.close();
            return _duan;
        }
    }

    public List<DTO_DuAn> _layDUANTHEOMANHOM(int manhom)
    {
        List<DTO_DuAn> _duan = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        Cursor cursor = null;
        try
        {
            cursor = db.rawQuery("select * from " + TABLE_DUAN +" where manhom = "+manhom +" order by id ASC", null);
            if(cursor.moveToFirst())
            {
                while (cursor.isAfterLast() == false)
                {
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String tenduan = cursor.getString(cursor.getColumnIndex("tenduan"));
                    String ngaybatdau = cursor.getString(cursor.getColumnIndex("ngaybatdau"));
                    String ngayketthuc = cursor.getString(cursor.getColumnIndex("ngayketthuc"));
                    String tiendo = cursor.getString(cursor.getColumnIndex("tiendo"));
                    int manhom1 = cursor.getInt(cursor.getColumnIndex("manhom"));
                    _duan.add(new DTO_DuAn(id,tenduan,ngaybatdau,ngayketthuc,tiendo,manhom1));
                    cursor.moveToNext();
                }
            }
            db.setTransactionSuccessful();
        }
        catch (Exception ex)
        {

        }
        finally {
            db.endTransaction();
            cursor.close();
            return _duan;
        }
    }
    //_______________________________________________________________________________________________________________________
}
