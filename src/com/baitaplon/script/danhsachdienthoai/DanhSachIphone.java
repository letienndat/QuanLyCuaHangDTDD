package com.baitaplon.script.danhsachdienthoai;

import com.baitaplon.script.dienthoai.DienThoai;

import java.util.ArrayList;
import java.util.List;

public class DanhSachIphone {
    private List<DienThoai> listIphone;

    public DanhSachIphone() {
        listIphone = new ArrayList<>();
    }

    public DanhSachIphone(List<DienThoai> listIphone) {
        this.listIphone = listIphone;
    }

    public List<DienThoai> getListIphone() {
        return listIphone;
    }

    public void setListIphone(List<DienThoai> listIphone) {
        this.listIphone = listIphone;
    }
}
