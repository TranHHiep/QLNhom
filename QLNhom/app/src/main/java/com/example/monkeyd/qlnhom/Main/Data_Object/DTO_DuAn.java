package com.example.monkeyd.qlnhom.Main.Data_Object;

/**
 * Created by MonkeyD on 6/13/2016.
 */
public class DTO_DuAn {
    private  int mada;
    private  String tenda;
    private  String ngaybatdau;
    private  String ngayketthuc;
    private  String tiendo;
    private int manhom;
    public DTO_DuAn(int ma, String ten, String ngaybd, String ngaykt, String tiend, int manh)
    {
        this.setMada(ma);
        this.setTenda(ten);
        this.setNgaybatdau(ngaybd);
        this.setNgayketthuc(ngaykt);
        this.setTiendo(tiend);
        this.setManhom(manh);
    }

    public int getMada() {
        return mada;
    }

    public void setMada(int mada) {
        this.mada = mada;
    }

    public String getTenda() {
        return tenda;
    }

    public void setTenda(String tenda) {
        this.tenda = tenda;
    }

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public String getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(String ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    public String getTiendo() {
        return tiendo;
    }

    public void setTiendo(String tiendo) {
        this.tiendo = tiendo;
    }

    public int getManhom() {
        return manhom;
    }

    public void setManhom(int manhom) {
        this.manhom = manhom;
    }
}
