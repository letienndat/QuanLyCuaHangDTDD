package com.baitaplon.script.danhsachdienthoai;

import com.baitaplon.script.dienthoai.DienThoai;

import java.util.ArrayList;
import java.util.List;

public class DanhSachVivo {
    private List<DienThoai> listVivo;

    public DanhSachVivo() {
        listVivo = new ArrayList<>();
    }

    public DanhSachVivo(List<DienThoai> listVivo) {
        this.listVivo = listVivo;
    }

    public List<DienThoai> getListVivo() {
        return listVivo;
    }

    public void setListVivo(List<DienThoai> listVivo) {
        this.listVivo = listVivo;
    }
}
