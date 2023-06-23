package com.baitaplon.script.trangchu;

import com.baitaplon.script.dangnhap_dangky.DangNhap_DangKy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class XoaSanPham extends JDialog {
    private static final String sourceImage = "/com/baitaplon/image/";
    private JPanel panelXoaSanPham;
    private JButton buttonDongY;
    private JButton buttonHuy;
    private Connection connection;
    private String key;
    private PreparedStatement statement;
    private String loaiSanPham;

    public XoaSanPham() {
        setTitle("XÓA SẢN PHẨM [ADMIN]");
        setSize(350, 150);
        setContentPane(panelXoaSanPham);
        setModal(true);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(DangNhap_DangKy.class.getResource(sourceImage + "Icon_100x100.png")).getImage());
        getRootPane().setDefaultButton(buttonDongY);

        buttonDongY.setFocusPainted(false);
        buttonDongY.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonDongY.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonHuy.setFocusPainted(false);
        buttonHuy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonHuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        panelXoaSanPham.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public XoaSanPham(Connection connection, String key, String loaiSanPham) {
        this();
        this.connection = connection;
        this.key = key;
        this.loaiSanPham = loaiSanPham;
    }

    public void removeDataFromSQL() {
        try {
            String sql = "DELETE FROM DienThoai%s WHERE MaThietBi = '%s'";
            sql = String.format(sql, loaiSanPham, key);
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void onOK() {
        removeDataFromSQL();
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
