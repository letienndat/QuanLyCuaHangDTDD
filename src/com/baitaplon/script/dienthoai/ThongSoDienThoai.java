package com.baitaplon.script.dienthoai;

public class ThongSoDienThoai {
    private String LoaiManHinh;
    private Integer DoPhanGiaiRong;
    private Integer DoPhanGiaiDai;
    private Double ManHinh;
    private Integer CameraTruoc;
    private Integer CameraSau;
    private String HeDieuHanh;
    private String CPU;
    private Integer RAM;
    private Integer DungLuongPin;
    private Integer ThoiDiemRaMat;
    private String LoaiDienThoai;

    public ThongSoDienThoai() {

    }

    public ThongSoDienThoai(String loaiManHinh, Integer doPhanGiaiDai, Integer doPhanGiaiRong, Double manHinh, Integer cameraTruoc, Integer cameraSau, String heDieuHanh, String CPU, Integer RAM, Integer dungLuongPin, Integer thoiDiemRaMat, String loaiDienThoai) {
        LoaiManHinh = loaiManHinh;
        DoPhanGiaiRong = doPhanGiaiRong;
        DoPhanGiaiDai = doPhanGiaiDai;
        ManHinh = manHinh;
        CameraTruoc = cameraTruoc;
        CameraSau = cameraSau;
        HeDieuHanh = heDieuHanh;
        this.CPU = CPU;
        this.RAM = RAM;
        DungLuongPin = dungLuongPin;
        ThoiDiemRaMat = thoiDiemRaMat;
        LoaiDienThoai = loaiDienThoai;
    }

    public String getLoaiManHinh() {
        return LoaiManHinh;
    }

    public void setLoaiManHinh(String loaiManHinh) {
        LoaiManHinh = loaiManHinh;
    }

    public Integer getDoPhanGiaiRong() {
        return DoPhanGiaiRong;
    }

    public void setDoPhanGiaiRong(Integer doPhanGiaiRong) {
        DoPhanGiaiRong = doPhanGiaiRong;
    }

    public Integer getDoPhanGiaiDai() {
        return DoPhanGiaiDai;
    }

    public void setDoPhanGiaiDai(Integer doPhanGiaiDai) {
        DoPhanGiaiDai = doPhanGiaiDai;
    }

    public Double getManHinh() {
        return ManHinh;
    }

    public void setManHinh(Double manHinh) {
        ManHinh = manHinh;
    }

    public Integer getCameraTruoc() {
        return CameraTruoc;
    }

    public void setCameraTruoc(Integer cameraTruoc) {
        CameraTruoc = cameraTruoc;
    }

    public Integer getCameraSau() {
        return CameraSau;
    }

    public void setCameraSau(Integer cameraSau) {
        CameraSau = cameraSau;
    }

    public String getHeDieuHanh() {
        return HeDieuHanh;
    }

    public void setHeDieuHanh(String heDieuHanh) {
        HeDieuHanh = heDieuHanh;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public Integer getRAM() {
        return RAM;
    }

    public void setRAM(Integer RAM) {
        this.RAM = RAM;
    }

    public Integer getDungLuongPin() {
        return DungLuongPin;
    }

    public void setDungLuongPin(Integer dungLuongPin) {
        DungLuongPin = dungLuongPin;
    }

    public Integer getThoiDiemRaMat() {
        return ThoiDiemRaMat;
    }

    public void setThoiDiemRaMat(Integer thoiDiemRaMat) {
        ThoiDiemRaMat = thoiDiemRaMat;
    }

    public void setLoaiDienThoai(String loaiDienThoai) {
        LoaiDienThoai = loaiDienThoai;
    }

    public String getLoaiDienThoai() {
        return LoaiDienThoai;
    }
}
