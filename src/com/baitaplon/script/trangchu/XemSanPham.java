package com.baitaplon.script.trangchu;

import com.baitaplon.script.dangnhap_dangky.DangNhap_DangKy;
import com.baitaplon.script.dienthoai.DienThoai;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Locale;

public class XemSanPham extends JDialog {
    private static final String sourceImage = "/com/baitaplon/image/";
    private JPanel panelXemSanPham;
    private JLabel labelTenSanPham;
    private JLabel labelMaSanPham;
    private JLabel labelAnh;
    private JLabel labelTen;
    private JLabel labelMau;
    private JLabel labelDungLuong;
    private JLabel labelGia;
    private JLabel labelMauSanPham;
    private JLabel labelDungLuongBoNho;
    private JLabel labelLoaiManHinh;
    private JLabel labelDoPhanGiai;
    private JLabel labelManHinh;
    private JLabel labelCameraTruoc;
    private JLabel labelCameraSau;
    private JLabel labelHeDieuHanh;
    private JLabel labelChip;
    private JLabel labelRam;
    private JLabel labelDungLuongPin;
    private JLabel labelThoiDiemRaMat;
    private JLabel labelLuotXem;
    private DienThoai dienThoai;

    public XemSanPham() {
        setContentPane(panelXemSanPham);
        setModal(true);
        setSize(new Dimension(600, 700));
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(DangNhap_DangKy.class.getResource(sourceImage + "Icon_100x100.png")).getImage());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        panelXemSanPham.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public XemSanPham(DienThoai dienThoai) {
        this();
        this.dienThoai = dienThoai;
        setTitle(dienThoai.getTenDienThoai());
        init();
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void init() {
        labelAnh.setIcon(dienThoai.getAnhDienThoai().getIcon());
        labelTen.setText(dienThoai.getTenDienThoai());
        labelMau.setText("Màu: " + dienThoai.getMauDienThoai());
        labelDungLuong.setText("Dung lượng: " + dienThoai.getDungLuongBoNho() + "GB");
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat numberFormat = NumberFormat.getInstance(localeVN);
        String giaTemp = numberFormat.format(dienThoai.getGiaDienThoai());
        labelGia.setText(giaTemp + " VNĐ");
        labelLuotXem.setText("Lượt xem: " + dienThoai.getLuotXem());
        labelTenSanPham.setText("Tên thiết bị: " + dienThoai.getTenDienThoai());
        labelMaSanPham.setText("Mã thiết bị: " + dienThoai.getMaDienThoai());
        labelMauSanPham.setText("Màu thiết bị: " + dienThoai.getMauDienThoai());
        labelDungLuongBoNho.setText("Dung lượng bộ nhớ: " + dienThoai.getDungLuongBoNho() + "GB");
        labelLoaiManHinh.setText("Loại màn hình: " + dienThoai.getThongSoDienThoai().getLoaiManHinh());
        labelDoPhanGiai.setText("Độ phân giải: " + dienThoai.getThongSoDienThoai().getDoPhanGiaiDai() + "x" + dienThoai.getThongSoDienThoai().getDoPhanGiaiRong() + " pixel");
        labelManHinh.setText("Màn hình: " + dienThoai.getThongSoDienThoai().getManHinh() + " inches");
        labelCameraTruoc.setText("Camera trước: " + dienThoai.getThongSoDienThoai().getCameraTruoc() + "MP");
        labelCameraSau.setText("Camera sau: " + dienThoai.getThongSoDienThoai().getCameraSau() + "MP");
        labelHeDieuHanh.setText("Hệ điều hành: " + dienThoai.getThongSoDienThoai().getHeDieuHanh());
        labelChip.setText("Chip: " + dienThoai.getThongSoDienThoai().getCPU());
        labelRam.setText("Ram: " + dienThoai.getThongSoDienThoai().getRAM() + "GB");
        labelDungLuongPin.setText("Dung lượng pin: " + dienThoai.getThongSoDienThoai().getDungLuongPin() + "mAh");
        labelThoiDiemRaMat.setText("Thời điểm ra mắt: " + dienThoai.getThongSoDienThoai().getThoiDiemRaMat());
    }
}
