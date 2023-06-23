package com.baitaplon.script.trangchu.panelsanpham;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Locale;

public class PanelSanPham extends JPanel {
    private JPanel panelSanPham;
    private JLabel labelAnh;
    private JLabel labelTen;
    private JLabel labelMau;
    private JLabel labelLuotXem;
    private JLabel labelGia;
    private JLabel labelChinhSua;
    private JLabel labelXoa;
    private JPanel panelThongTin;

    public PanelSanPham() {
        panelSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        labelChinhSua.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelChinhSua.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                labelChinhSua.setText("<HTML><U>Chỉnh sửa</U></HTML>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelChinhSua.setText("<HTML>Chỉnh sửa</HTML>");
            }
        });

        labelXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelXoa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                labelXoa.setText("<HTML><U>Xóa</U></HTML>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelXoa.setText("<HTML>Xóa</HTML>");
            }
        });
    }

    public PanelSanPham(JLabel labelAnh, String labelTen, String labelMau, String labelLuotXem, String labelGia) {
        this();
        this.labelAnh.setText(labelAnh.getText());
        this.labelAnh.setIcon(labelAnh.getIcon());
        this.labelTen.setText(labelTen);
        this.labelMau.setText("Màu: " + labelMau);
        this.labelLuotXem.setText("Lượt xem: " + labelLuotXem);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat numberFormat = NumberFormat.getInstance(localeVN);
        String giaTemp = numberFormat.format(Integer.parseInt(labelGia));
        this.labelGia.setText(giaTemp + " VNĐ");
    }

    public JPanel getPanelSanPham() {
        return panelSanPham;
    }

    public void setPanelSanPham(JPanel panelSanPham) {
        this.panelSanPham = panelSanPham;
    }

    public JLabel getLabelAnh() {
        return labelAnh;
    }

    public void setLabelAnh(JLabel labelAnh) {
        this.labelAnh = labelAnh;
    }

    public JLabel getLabelTen() {
        return labelTen;
    }

    public void setLabelTen(JLabel labelTen) {
        this.labelTen = labelTen;
    }

    public JLabel getLabelMau() {
        return labelMau;
    }

    public void setLabelMau(JLabel labelMau) {
        this.labelMau = labelMau;
    }

    public JLabel getLabelLuotXem() {
        return labelLuotXem;
    }

    public void setLabelLuotXem(JLabel labelLuotXem) {
        this.labelLuotXem = labelLuotXem;
    }

    public JLabel getLabelGia() {
        return labelGia;
    }

    public void setLabelGia(JLabel labelGia) {
        this.labelGia = labelGia;
    }

    public JLabel getLabelChinhSua() {
        return labelChinhSua;
    }

    public void setLabelChinhSua(JLabel labelChinhSua) {
        this.labelChinhSua = labelChinhSua;
    }

    public JLabel getLabelXoa() {
        return labelXoa;
    }

    public void setLabelXoa(JLabel labelXoa) {
        this.labelXoa = labelXoa;
    }

    public JPanel getPanelThongTin() {
        return panelThongTin;
    }
}
