package com.example.monkeyd.qlnhom.Main.Data_Object;

/**
 * Created by MonkeyD on 6/12/2016.
 */
public class DTO_ThanhVien {
    private int mathanhvien;
    private String tenthanhvien;
    private String lop;
    private String dienthoai;
    private String sothich;
    private String sotruong;
    private String sodoan;
    private int manhom;
    public DTO_ThanhVien(int id, String ten, String flop, String dt, String thich, String truong, String doan, int nhom)
    {
        this.setMathanhvien(id);
        this.setTenthanhvien(ten);
        this.setLop(flop);
        this.setDienthoai(dt);
        this.setSothich(thich);
        this.setSotruong(truong);
        this.setSodoan(doan);
        this.setManhom(nhom);
    }

    public int getMathanhvien() {
        return mathanhvien;
    }

    public void setMathanhvien(int mathanhvien) {
        this.mathanhvien = mathanhvien;
    }

    public String getTenthanhvien() {
        return tenthanhvien;
    }

    public void setTenthanhvien(String tenthanhvien) {
        this.tenthanhvien = tenthanhvien;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public String getSothich() {
        return sothich;
    }

    public void setSothich(String sothich) {
        this.sothich = sothich;
    }

    public String getSotruong() {
        return sotruong;
    }

    public void setSotruong(String sotruong) {
        this.sotruong = sotruong;
    }

    public String getSodoan() {
        return sodoan;
    }

    public void setSodoan(String sodoan) {
        this.sodoan = sodoan;
    }

    public int getManhom() {
        return manhom;
    }

    public void setManhom(int manhom) {
        this.manhom = manhom;
    }
}
