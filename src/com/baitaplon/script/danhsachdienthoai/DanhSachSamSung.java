package com.baitaplon.script.danhsachdienthoai;

import com.baitaplon.script.dienthoai.DienThoai;

import java.util.ArrayList;
import java.util.List;

public class DanhSachSamSung {
    private List<DienThoai> listSamSung;

    public DanhSachSamSung() {
        listSamSung = new ArrayList<>();
    }

    public DanhSachSamSung(List<DienThoai> listSamSung) {
        this.listSamSung = listSamSung;
    }

    public List<DienThoai> getListSamSung() {
        return listSamSung;
    }

    public void setListSamSung(List<DienThoai> listSamSung) {
        this.listSamSung = listSamSung;
    }
}
