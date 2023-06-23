package com.baitaplon.script.trangchu;

import com.baitaplon.script.dangnhap_dangky.DangNhap_DangKy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoan extends JDialog {
    private static final String sourceImage = "/com/baitaplon/image/";
    private JPanel panelTaiKhoan;
    private JLabel labelTenTaiKhoan;
    private JButton buttonDoiMatKhau;
    private JButton buttonDangXuat;
    private JLabel labelThongBaoDoiMatKhau;
    private JPasswordField passwordFieldMatKhauHienTai;
    private JPasswordField passwordFieldMatKhauMoi;
    private JPasswordField passwordFieldXacNhanMatKhauMoi;
    private Connection connection;
    private PreparedStatement statement;
    private String userName;
    private TrangChuAdmin trangChuAdmin;
    private TrangChuUser trangChuUser;

    public TaiKhoan() {
        setContentPane(panelTaiKhoan);
        setModal(true);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(DangNhap_DangKy.class.getResource(sourceImage + "Icon_100x100.png")).getImage());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public TaiKhoan(Connection connection, String userName, TrangChuAdmin trangChuAdmin) {
        this();
        setTitle("TÀI KHOẢN [" + userName + "]");
        this.connection = connection;
        this.userName = userName;
        this.trangChuAdmin = trangChuAdmin;
        init();
    }

    public TaiKhoan(Connection connection, String userName, TrangChuUser trangChuUser) {
        this();
        setTitle("TÀI KHOẢN [" + userName + "]");
        this.connection = connection;
        this.userName = userName;
        this.trangChuUser = trangChuUser;
        init();
    }

    public void init() {
        passwordFieldMatKhauHienTai.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                labelThongBaoDoiMatKhau.setText("");
            }
        });

        passwordFieldMatKhauMoi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                labelThongBaoDoiMatKhau.setText("");
            }
        });

        passwordFieldXacNhanMatKhauMoi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                labelThongBaoDoiMatKhau.setText("");
            }
        });

        buttonDangXuat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonDangXuat.setFocusPainted(false);
        buttonDangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (trangChuAdmin != null) {
                    trangChuAdmin.dispose();
                } else {
                    trangChuUser.dispose();
                }
                dispose();
                EventQueue.invokeLater(() -> {
                    new DangNhap_DangKy(userName).setVisible(true);
                });
            }
        });

        buttonDoiMatKhau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonDoiMatKhau.setFocusPainted(false);
        buttonDoiMatKhau.addActionListener(e -> {
            if (labelThongBaoDoiMatKhau.getText().equals("")
                    && kiemTraMatKhauTrungKhop_DoiMatKhau()
                    && kiemTraMatKhauMoi1_QuenMatKhau() == 1
                    && kiemTraMatKhauMoi2_QuenMatKhau() == 1) {
                doiMatKhau_DoiMatKhau();
            } else {
                if (!labelThongBaoDoiMatKhau.getText().equals("Đổi mật khẩu thành công!")) {
                    labelThongBaoDoiMatKhau.setText("Đổi mật khẩu không thành công!");
                }
            }
        });

        labelTenTaiKhoan.setText("Tên tài khoản: " + userName);
    }

    public boolean kiemTraMatKhauTrungKhop_DoiMatKhau() {
        try {
            String sql = "SELECT MatKhau FROM TaiKhoan_MatKhau WHERE TenTK = '" + userName + "'";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            if (resultSet.getString("MatKhau").equals(passwordFieldMatKhauHienTai.getText()))
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int kiemTraMatKhauMoi1_QuenMatKhau() {
        if (!passwordFieldMatKhauMoi.getText().matches("^(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])(?=.*[a-z]).{8,}$")) {
            return 0; // Không thỏa mãn A-Z, a-z, 0-9, !@#$&*, và ít nhất 8 ký tự
        }
        if (passwordFieldMatKhauMoi.getText().equals(passwordFieldMatKhauHienTai.getText())) {
            return -1; // Trùng mật khẩu hiện tại
        }
        return 1; // Mật khẩu 1 thỏa mãn
    }

    public int kiemTraMatKhauMoi2_QuenMatKhau() {
        if (passwordFieldXacNhanMatKhauMoi.getText().length() == 0) {
            return 0; // Bỏ trống mật khẩu 2
        }
        if (kiemTraMatKhauMoi1_QuenMatKhau() == 1) {
            if (passwordFieldXacNhanMatKhauMoi.getText().equals(passwordFieldMatKhauMoi.getText())) {
                return 1; // Mật khẩu 2 trùng khớp với mật khẩu 1
            }
        }
        return -1; // Không thỏa mãn A-Z, a-z, 0-9, !@#$&*, và ít nhất 8 ký tự hoặc không trùng khớp với mật khẩu 1
    }

    public void doiMatKhau_DoiMatKhau() {
        try {
            String sql = "UPDATE taikhoan_matkhau SET MatKhau = '" + passwordFieldMatKhauMoi.getText() + "' WHERE TenTK = '" + userName + "'";
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            labelThongBaoDoiMatKhau.setText("Đổi mật khẩu thành công!");
        } catch (SQLException e) {
            labelThongBaoDoiMatKhau.setText("Đổi mật khẩu không thành công!");
            e.printStackTrace();
        }
    }
}
