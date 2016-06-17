package com.example.monkeyd.qlnhom.Main.Data_Object;

/**
 * Created by MonkeyD on 6/11/2016.
 */
public class DTO_Nhom {
    private int id;
    private String tennhom;
    private String nhomtruong;
    private int soluong;
    public DTO_Nhom(int id, String ten, String nhomtruong, int soluong)
    {
        this.setId(id);
        this.setTennhom(ten);
        this.setNhomtruong(nhomtruong);
        this.setSoluong(soluong);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTennhom() {
        return tennhom;
    }

    public void setTennhom(String tennhom) {
        this.tennhom = tennhom;
    }

    public String getNhomtruong() {
        return nhomtruong;
    }

    public void setNhomtruong(String nhomtruong) {
        this.nhomtruong = nhomtruong;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
