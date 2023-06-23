package com.baitaplon.script.danhsachdienthoai;

import com.baitaplon.script.dienthoai.DienThoai;

import java.util.ArrayList;
import java.util.List;

public class DanhSachXiaomi {
    private List<DienThoai> listXiaomi;

    public DanhSachXiaomi() {
        listXiaomi = new ArrayList<>();
    }

    public DanhSachXiaomi(List<DienThoai> listXiaomi) {
        this.listXiaomi = listXiaomi;
    }

    public List<DienThoai> getListXiaomi() {
        return listXiaomi;
    }

    public void setListXiaomi(List<DienThoai> listXiaomi) {
        this.listXiaomi = listXiaomi;
    }
}
