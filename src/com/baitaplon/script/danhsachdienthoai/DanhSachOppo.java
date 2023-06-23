package com.baitaplon.script.danhsachdienthoai;

import com.baitaplon.script.dienthoai.DienThoai;

import java.util.ArrayList;
import java.util.List;

public class DanhSachOppo {
    private List<DienThoai> listOppo;

    public DanhSachOppo() {
        listOppo = new ArrayList<>();
    }

    public DanhSachOppo(List<DienThoai> listOppo) {
        this.listOppo = listOppo;
    }

    public List<DienThoai> getListOppo() {
        return listOppo;
    }

    public void setListOppo(List<DienThoai> listOppo) {
        this.listOppo = listOppo;
    }
}
