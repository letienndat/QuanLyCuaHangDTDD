package com.baitaplon.script.dienthoai;

import javax.swing.*;

public class DienThoai {
    private String maDienThoai;
    private String tenDienThoai;
    private Integer giaDienThoai;
    private String mauDienThoai;
    private Integer dungLuongBoNho;
    private ThongSoDienThoai thongSoDienThoai;
    private JLabel anhDienThoai;
    private Integer luotXem;

    public DienThoai() {

    }

    public DienThoai(String maDienThoai, String tenDienThoai, Integer giaDienThoai, String mauDienThoai, Integer dungLuongBoNho, ThongSoDienThoai thongSoDienThoai, JLabel anhDienThoai, Integer luotXem) {
        this.maDienThoai = maDienThoai;
        this.tenDienThoai = tenDienThoai;
        this.giaDienThoai = giaDienThoai;
        this.mauDienThoai = mauDienThoai;
        this.dungLuongBoNho = dungLuongBoNho;
        this.thongSoDienThoai = thongSoDienThoai;
        this.anhDienThoai = anhDienThoai;
        this.luotXem = luotXem;
    }

    public String getMaDienThoai() {
        return maDienThoai;
    }

    public void setMaDienThoai(String maDienThoai) {
        this.maDienThoai = maDienThoai;
    }

    public String getTenDienThoai() {
        return tenDienThoai;
    }

    public void setTenDienThoai(String tenDienThoai) {
        this.tenDienThoai = tenDienThoai;
    }

    public Integer getGiaDienThoai() {
        return giaDienThoai;
    }

    public void setGiaDienThoai(Integer giaDienThoai) {
        this.giaDienThoai = giaDienThoai;
    }

    public String getMauDienThoai() {
        return mauDienThoai;
    }

    public void setMauDienThoai(String mauDienThoai) {
        this.mauDienThoai = mauDienThoai;
    }

    public Integer getDungLuongBoNho() {
        return dungLuongBoNho;
    }

    public void setDungLuongBoNho(Integer dungLuongBoNho) {
        this.dungLuongBoNho = dungLuongBoNho;
    }

    public ThongSoDienThoai getThongSoDienThoai() {
        return thongSoDienThoai;
    }

    public void setThongSoDienThoai(ThongSoDienThoai thongSoDienThoai) {
        this.thongSoDienThoai = thongSoDienThoai;
    }

    public JLabel getAnhDienThoai() {
        return anhDienThoai;
    }

    public void setAnhDienThoai(JLabel anhDienThoai) {
        this.anhDienThoai = anhDienThoai;
    }

    public void setLuotXem(Integer luotXem) {
        this.luotXem = luotXem;
    }

    public Integer getLuotXem() {
        return luotXem;
    }
}
