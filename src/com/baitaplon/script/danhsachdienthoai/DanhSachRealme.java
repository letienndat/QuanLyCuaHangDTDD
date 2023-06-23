package com.baitaplon.script.danhsachdienthoai;

import com.baitaplon.script.dienthoai.DienThoai;

import java.util.ArrayList;
import java.util.List;

public class DanhSachRealme {
    private List<DienThoai> listRealme;

    public DanhSachRealme() {
        listRealme = new ArrayList<>();
    }

    public DanhSachRealme(List<DienThoai> listRealme) {
        this.listRealme = listRealme;
    }

    public List<DienThoai> getListRealme() {
        return listRealme;
    }

    public void setListRealme(List<DienThoai> listRealme) {
        this.listRealme = listRealme;
    }
}
