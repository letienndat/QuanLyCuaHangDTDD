package com.baitaplon.script.dangnhap_dangky;

import com.baitaplon.script.InfoConnectDatabase;
import com.baitaplon.script.trangchu.TrangChuAdmin;
import com.baitaplon.script.trangchu.TrangChuUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DangNhap_DangKy extends JFrame {
    private static final String DATABASE_NAME = "BaiTapLonJava";
    private static final String URL = "jdbc:mysql://localhost:3306/" + InfoConnectDatabase.DATABASE_NAME + "?characterEncoding=utf8";
    private static final String USER = InfoConnectDatabase.USERNAME;
    private static final String PASSWORD = InfoConnectDatabase.PASSWORD;
    private static final String sourceImage = "/com/baitaplon/image/";
    private JPanel panelMain;
    private JTabbedPane tabbedPaneDangNhapOrDangKy;
    private JLabel jLabelTieuDe2_DangNhap;
    private JTextField textFieldTenNguoiDung_DangNhap;
    private JCheckBox jCheckBoxHienMatKhau_DangNhap;
    private JButton jButtonDangNhap_DangNhap;
    private JLabel jLabelTenNguoiDung_DangNhap;
    private JLabel jLabelMatKhau_DangNhap;
    private JPanel jPanelDangNhap_TabbedPane;
    private JPasswordField passwordFieldMatKhau_DangNhap;
    private JComboBox jComboBoxAdminOrUser_DangNhap;
    private JLabel jLabelTieuDe2_DangKy;
    private JLabel jLabelTieuDe1_DangNhap;
    private JTextField textFieldTenNguoiDung_DangKy;
    private JPasswordField passwordFieldMatKhau1_DangKy;
    private JPasswordField passwordFieldMatKhau2_DangKy;
    private JButton jButtonDangKy_DangKy;
    private JLabel jLabelTieuDe1_DangKy;
    private JLabel jLabelTenNguoiDung_DangKy;
    private JLabel jLabelMatKhau1_DangKy;
    private JLabel jLabelMatKhau2_DangKy;
    private JPanel jPanelDangKy_TabbedPane;
    private JLabel jLabelThongBaoTenNguoiDung_DangNhap;
    private JLabel jLabelThongBaoMatKhau_DangNhap;
    private JLabel jLabelThongaoTenNguoDung_DangKy;
    private JLabel jLabelThongBaoMatKhau1_DangKy;
    private JLabel jLabelThongBaoMatKhau2_DangKy;
    private JLabel jLabelKiemTraDangKy;
    private JLabel labelThongBaoDangNhap;
    private Connection connection;
    private PreparedStatement statement;

    public DangNhap_DangKy(String s) {
        super("HỆ THỐNG QUẢN LÝ CỬA HÀNG ĐIỆN THOẠI DI ĐỘNG [ĐĂNG NHẬP | ĐĂNG KÝ]");
        setIconImage(new ImageIcon(DangNhap_DangKy.class.getResource(sourceImage + "Icon_100x100.png")).getImage());
        setSize(new Dimension(700, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setContentPane(panelMain);
        init();

        try {
            connectSQL();
        } catch (SQLException e) {
            labelThongBaoDangNhap.setText("Không thể kết nối tới máy chủ!");
        }

        jCheckBoxHienMatKhau_DangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jCheckBoxHienMatKhau_DangNhap.isSelected()) {
                    passwordFieldMatKhau_DangNhap.setEchoChar((char) 0);
                } else {
                    passwordFieldMatKhau_DangNhap.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
                }
            }
        });
        textFieldTenNguoiDung_DangNhap.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() != KeyEvent.VK_ENTER) {
                    jLabelThongBaoTenNguoiDung_DangNhap.setText("");
                    if (!textFieldTenNguoiDung_DangNhap.getText().equals("")) {
                        jLabelThongBaoMatKhau_DangNhap.setText("");
                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (jComboBoxAdminOrUser_DangNhap.getSelectedItem().equals("NGƯỜI DÙNG [USER]")) {
                        if (kiemTraTenTK_DangNhap() == 0) {
                            jLabelThongBaoTenNguoiDung_DangNhap.setText("Yêu cầu a-z, 0-9, -, _, từ 3 tới 16 ký tự!");
                            if (kiemTraMatKhau_DangNhap() == 0) {
                                jLabelThongBaoMatKhau_DangNhap.setText("Không được bỏ trống!");
                            }
                        } else if (kiemTraTenTK_DangNhap() == -1) {
                            if (kiemTraMatKhau_DangNhap() == 0) {
                                jLabelThongBaoMatKhau_DangNhap.setText("Không được bỏ trống!");
                            } else if (kiemTraMatKhau_DangNhap() != 0) {
                                jLabelThongBaoTenNguoiDung_DangNhap.setText("Tên đăng nhập không tồn tại!");
                            }
                        } else if (kiemTraTenTK_DangNhap() == 1) {
                            if (kiemTraMatKhau_DangNhap() == 0) {
                                jLabelThongBaoMatKhau_DangNhap.setText("Không được bỏ trống!");
                            } else if (kiemTraMatKhau_DangNhap() == -1) {
                                jLabelThongBaoMatKhau_DangNhap.setText("Sai mật khẩu!");
                            } else if (kiemTraMatKhau_DangNhap() == 1) {
                                dispose();
                                EventQueue.invokeLater(() -> {
                                    new TrangChuUser(textFieldTenNguoiDung_DangNhap.getText(), connection).setVisible(true);
                                });
                            }
                        }
                    } else {
                        if (textFieldTenNguoiDung_DangNhap.getText().length() == 0) {
                            jLabelThongBaoTenNguoiDung_DangNhap.setText("Không được bỏ trống!");
                            if (passwordFieldMatKhau_DangNhap.getText().length() == 0) {
                                jLabelThongBaoMatKhau_DangNhap.setText("Không được bỏ trống!");
                            }
                        } else if (!textFieldTenNguoiDung_DangNhap.getText().equals("admin")) {
                            if (passwordFieldMatKhau_DangNhap.getText().length() == 0) {
                                jLabelThongBaoMatKhau_DangNhap.setText("Không được bỏ trống!");
                            } else if (passwordFieldMatKhau_DangNhap.getText().length() > 0) {
                                jLabelThongBaoTenNguoiDung_DangNhap.setText("Tên đăng nhập không tồn tại!");
                            }
                        } else if (textFieldTenNguoiDung_DangNhap.getText().equals("admin")) {
                            if (passwordFieldMatKhau_DangNhap.getText().length() == 0) {
                                jLabelThongBaoMatKhau_DangNhap.setText("Không được bỏ trống!");
                            } else if (!passwordFieldMatKhau_DangNhap.getText().equals("admin")) {
                                jLabelThongBaoMatKhau_DangNhap.setText("Sai mật khẩu!");
                            } else if (passwordFieldMatKhau_DangNhap.getText().equals("admin")) {
                                dispose();
                                EventQueue.invokeLater(() -> {
                                    new TrangChuAdmin(textFieldTenNguoiDung_DangNhap.getText(), connection).setVisible(true);
                                });
                            }
                        }
                    }
                }
            }
        });
        passwordFieldMatKhau_DangNhap.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() != KeyEvent.VK_ENTER && e.getKeyChar() != KeyEvent.VK_SPACE) {
                    jLabelThongBaoMatKhau_DangNhap.setText("");
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (jComboBoxAdminOrUser_DangNhap.getSelectedItem().equals("NGƯỜI DÙNG [USER]")) {
                        if (kiemTraTenTK_DangNhap() == 0) {
                            jLabelThongBaoTenNguoiDung_DangNhap.setText("Yêu cầu a-z, 0-9, -, _, từ 3 tới 16 ký tự!");
                            if (kiemTraMatKhau_DangNhap() == 0) {
                                jLabelThongBaoMatKhau_DangNhap.setText("Không được bỏ trống!");
                            }
                        } else if (kiemTraTenTK_DangNhap() == -1) {
                            if (kiemTraMatKhau_DangNhap() == 0) {
                                jLabelThongBaoMatKhau_DangNhap.setText("Không được bỏ trống!");
                            } else if (kiemTraMatKhau_DangNhap() != 0) {
                                jLabelThongBaoTenNguoiDung_DangNhap.setText("Tên đăng nhập không tồn tại!");
                            }
                        } else if (kiemTraTenTK_DangNhap() == 1) {
                            if (kiemTraMatKhau_DangNhap() == 0) {
                                jLabelThongBaoMatKhau_DangNhap.setText("Không được bỏ trống!");
                            } else if (kiemTraMatKhau_DangNhap() == -1) {
                                jLabelThongBaoMatKhau_DangNhap.setText("Sai mật khẩu!");
                            } else if (kiemTraMatKhau_DangNhap() == 1) {
                                dispose();
                                EventQueue.invokeLater(() -> {
                                    new TrangChuUser(textFieldTenNguoiDung_DangNhap.getText(), connection).setVisible(true);
                                });
                            }
                        }
                    } else {
                        if (textFieldTenNguoiDung_DangNhap.getText().length() == 0) {
                            jLabelThongBaoTenNguoiDung_DangNhap.setText("Không được bỏ trống!");
                            if (passwordFieldMatKhau_DangNhap.getText().length() == 0) {
                                jLabelThongBaoMatKhau_DangNhap.setText("Không được bỏ trống!");
                            }
                        } else if (!textFieldTenNguoiDung_DangNhap.getText().equals("admin")) {
                            if (passwordFieldMatKhau_DangNhap.getText().length() == 0) {
                                jLabelThongBaoMatKhau_DangNhap.setText("Không được bỏ trống!");
                            } else if (passwordFieldMatKhau_DangNhap.getText().length() > 0) {
                                jLabelThongBaoTenNguoiDung_DangNhap.setText("Tên đăng nhập không tồn tại!");
                            }
                        } else if (textFieldTenNguoiDung_DangNhap.getText().equals("admin")) {
                            if (passwordFieldMatKhau_DangNhap.getText().length() == 0) {
                                jLabelThongBaoMatKhau_DangNhap.setText("Không được bỏ trống!");
                            } else if (!passwordFieldMatKhau_DangNhap.getText().equals("admin")) {
                                jLabelThongBaoMatKhau_DangNhap.setText("Sai mật khẩu!");
                            } else if (passwordFieldMatKhau_DangNhap.getText().equals("admin")) {
                                dispose();
                                EventQueue.invokeLater(() -> {
                                    new TrangChuAdmin(textFieldTenNguoiDung_DangNhap.getText(), connection).setVisible(true);
                                });
                            }
                        }
                    }
                }
            }
        });
        jButtonDangNhap_DangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jComboBoxAdminOrUser_DangNhap.getSelectedItem().equals("NGƯỜI DÙNG [USER]")) {
                    if (kiemTraTenTK_DangNhap() == 0) {
                        jLabelThongBaoTenNguoiDung_DangNhap.setText("Yêu cầu a-z, 0-9, -, _, từ 3 tới 16 ký tự!");
                        if (kiemTraMatKhau_DangNhap() == 0) {
                            jLabelThongBaoMatKhau_DangNhap.setText("Không được bỏ trống!");
                        }
                    } else if (kiemTraTenTK_DangNhap() == -1) {
                        if (kiemTraMatKhau_DangNhap() == 0) {
                            jLabelThongBaoMatKhau_DangNhap.setText("Không được bỏ trống!");
                        } else if (kiemTraMatKhau_DangNhap() != 0) {
                            jLabelThongBaoTenNguoiDung_DangNhap.setText("Tên đăng nhập không tồn tại!");
                        }
                    } else if (kiemTraTenTK_DangNhap() == 1) {
                        if (kiemTraMatKhau_DangNhap() == 0) {
                            jLabelThongBaoMatKhau_DangNhap.setText("Không được bỏ trống!");
                        } else if (kiemTraMatKhau_DangNhap() == -1) {
                            jLabelThongBaoMatKhau_DangNhap.setText("Sai mật khẩu!");
                        } else if (kiemTraMatKhau_DangNhap() == 1) {
                            dispose();
                            EventQueue.invokeLater(() -> {
                                new TrangChuUser(textFieldTenNguoiDung_DangNhap.getText(), connection).setVisible(true);
                            });
                        }
                    }
                } else {
                    if (textFieldTenNguoiDung_DangNhap.getText().length() == 0) {
                        jLabelThongBaoTenNguoiDung_DangNhap.setText("Không được bỏ trống!");
                        if (passwordFieldMatKhau_DangNhap.getText().length() == 0) {
                            jLabelThongBaoMatKhau_DangNhap.setText("Không được bỏ trống!");
                        }
                    } else if (!textFieldTenNguoiDung_DangNhap.getText().equals("admin")) {
                        if (passwordFieldMatKhau_DangNhap.getText().length() == 0) {
                            jLabelThongBaoMatKhau_DangNhap.setText("Không được bỏ trống!");
                        } else if (passwordFieldMatKhau_DangNhap.getText().length() > 0) {
                            jLabelThongBaoTenNguoiDung_DangNhap.setText("Tên đăng nhập không tồn tại!");
                        }
                    } else if (textFieldTenNguoiDung_DangNhap.getText().equals("admin")) {
                        if (passwordFieldMatKhau_DangNhap.getText().length() == 0) {
                            jLabelThongBaoMatKhau_DangNhap.setText("Không được bỏ trống!");
                        } else if (!passwordFieldMatKhau_DangNhap.getText().equals("admin")) {
                            jLabelThongBaoMatKhau_DangNhap.setText("Sai mật khẩu!");
                        } else if (passwordFieldMatKhau_DangNhap.getText().equals("admin")) {
                            dispose();
                            EventQueue.invokeLater(() -> {
                                new TrangChuAdmin(textFieldTenNguoiDung_DangNhap.getText(), connection).setVisible(true);
                            });
                        }
                    }
                }
            }
        });
        textFieldTenNguoiDung_DangKy.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (kiemTraTaiKhoan_DangKy() == 0) {
                    jLabelThongaoTenNguoDung_DangKy.setText("Yêu cầu a-z, 0-9, -, _, từ 3 tới 16 ký tự!");
                } else if (kiemTraTaiKhoan_DangKy() == -1) {
                    jLabelThongaoTenNguoDung_DangKy.setText("Tên đăng nhập đã được sử dụng!");
                } else {
                    jLabelThongaoTenNguoDung_DangKy.setText("Thỏa mãn!");
                }
                if (textFieldTenNguoiDung_DangKy.getText().equals("Thỏa mãn!") &&
                        jLabelThongBaoMatKhau1_DangKy.getText().equals("Thỏa mãn!") &&
                        jLabelThongBaoMatKhau2_DangKy.getText().equals("Thỏa mãn!")) {
                    jButtonDangKy_DangKy.setEnabled(true);
                } else {
                    jButtonDangKy_DangKy.setEnabled(false);
                }
                if (e.getKeyChar() != KeyEvent.VK_ENTER) {
                    jLabelKiemTraDangKy.setText("");
                }
            }
        });
        passwordFieldMatKhau1_DangKy.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (kiemTraMatKhau1_DangKy() == 0) {
                    jLabelThongBaoMatKhau1_DangKy.setText("Yêu cầu A-Z, a-z, 0-9, !@#$&*, và ít nhất 8 ký tự!");
                    if (kiemTraMatKhau2_DangKy() != 0) {
                        jLabelThongBaoMatKhau2_DangKy.setText("Mật khẩu không trùng khớp!");
                    }
                } else if (kiemTraMatKhau1_DangKy() == 1) {
                    jLabelThongBaoMatKhau1_DangKy.setText("Thỏa mãn!");
                    if (kiemTraMatKhau2_DangKy() == 1) {
                        jLabelThongBaoMatKhau2_DangKy.setText("Thỏa mãn!");
                    }
                    if (kiemTraMatKhau2_DangKy() == -1) {
                        jLabelThongBaoMatKhau2_DangKy.setText("Mật khẩu không trùng khớp!");
                    }
                }
                if (kiemTraTaiKhoan_DangKy() == 0) {
                    jLabelThongaoTenNguoDung_DangKy.setText("Yêu cầu a-z, 0-9, -, _, từ 3 tới 16 ký tự!");
                } else if (kiemTraTaiKhoan_DangKy() == -1) {
                    jLabelThongaoTenNguoDung_DangKy.setText("Tên đăng nhập đã được sử dụng!");
                } else {
                    jLabelThongaoTenNguoDung_DangKy.setText("Thỏa mãn!");
                }
                if (jLabelThongaoTenNguoDung_DangKy.getText().equals("Thỏa mãn!") &&
                        jLabelThongBaoMatKhau1_DangKy.getText().equals("Thỏa mãn!") &&
                        jLabelThongBaoMatKhau2_DangKy.getText().equals("Thỏa mãn!")) {
                    jButtonDangKy_DangKy.setEnabled(true);
                } else {
                    jButtonDangKy_DangKy.setEnabled(false);
                }
                if (e.getKeyChar() != KeyEvent.VK_ENTER) {
                    jLabelKiemTraDangKy.setText("");
                }
            }
        });
        passwordFieldMatKhau2_DangKy.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (kiemTraMatKhau2_DangKy() == 0) {
                    jLabelThongBaoMatKhau2_DangKy.setText("Không được bỏ trống!");
                } else if (kiemTraMatKhau2_DangKy() == -1) {
                    jLabelThongBaoMatKhau2_DangKy.setText("Mật khẩu không trùng khớp!");
                } else if (kiemTraMatKhau2_DangKy() == 1) {
                    jLabelThongBaoMatKhau2_DangKy.setText("Thỏa mãn!");
                }
                if (kiemTraTaiKhoan_DangKy() == 0) {
                    jLabelThongaoTenNguoDung_DangKy.setText("Yêu cầu a-z, 0-9, -, _, từ 3 tới 16 ký tự!");
                } else if (kiemTraTaiKhoan_DangKy() == -1) {
                    jLabelThongaoTenNguoDung_DangKy.setText("Tên đăng nhập đã được sử dụng!");
                } else {
                    jLabelThongaoTenNguoDung_DangKy.setText("Thỏa mãn!");
                }
                if (jLabelThongaoTenNguoDung_DangKy.getText().equals("Thỏa mãn!") &&
                        jLabelThongBaoMatKhau1_DangKy.getText().equals("Thỏa mãn!") &&
                        jLabelThongBaoMatKhau2_DangKy.getText().equals("Thỏa mãn!")) {
                    jButtonDangKy_DangKy.setEnabled(true);
                } else {
                    jButtonDangKy_DangKy.setEnabled(false);
                }
                if (e.getKeyChar() != KeyEvent.VK_ENTER) {
                    jLabelKiemTraDangKy.setText("");
                }
            }
        });
        jButtonDangKy_DangKy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTaiKhoanToSQL();
                jLabelKiemTraDangKy.setText("Tạo tài khoản thành công!");
            }
        });
        textFieldTenNguoiDung_DangNhap.setText(s);
    }

    public void init() {
        jButtonDangNhap_DangNhap.setFocusPainted(false);
        jButtonDangNhap_DangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        jCheckBoxHienMatKhau_DangNhap.setFocusPainted(false);
        jCheckBoxHienMatKhau_DangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        jButtonDangKy_DangKy.setFocusPainted(false);
        jButtonDangKy_DangKy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void connectSQL() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new SQLException();
        }
    }

    public boolean kiemTraTaiKhoanTonTai(String s) {
        try {
            String sql = "SELECT TenTK FROM taikhoan_matkhau WHERE TenTK = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, s);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) return true; // Tài khoản có tồn tại trong hệ thống
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Tài khoản không tồn tại trong hệ thống
    }

    public int kiemTraTaiKhoan_DangKy() {
        if (!textFieldTenNguoiDung_DangKy.getText().matches("^[a-z0-9_-]{3,16}$")) {
            return 0; // Không thỏa mãn a-z, 0-9, -, _, từ 3 đến 16 ký tự
        } else if (kiemTraTaiKhoanTonTai(textFieldTenNguoiDung_DangKy.getText())) {
            return -1; // Tên đăng nhập đã được sử dụng
        }
        return 1; // Tên đăng nhập thỏa mãn
    }

    public int kiemTraMatKhau1_DangKy() {
        if (!passwordFieldMatKhau1_DangKy.getText().matches("^(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])(?=.*[a-z]).{8,}$")) {
            return 0; // Không thỏa mãn A-Z, a-z, 0-9, !@#$&*, và ít nhất 8 ký tự
        }
        return 1; // Mật khẩu 1 thỏa mãn
    }

    public int kiemTraMatKhau2_DangKy() {
        if (passwordFieldMatKhau2_DangKy.getText().length() == 0) {
            return 0; // Bỏ trống mật khẩu 2
        }
        if (kiemTraMatKhau1_DangKy() == 1) {
            if (passwordFieldMatKhau2_DangKy.getText().equals(passwordFieldMatKhau1_DangKy.getText())) {
                return 1; // Mật khẩu 2 trùng khớp với mật khẩu 1
            }
        }
        return -1; // Không thỏa mãn A-Z, a-z, 0-9, !@#$&*, và ít nhất 8 ký tự hoặc không trùng khớp với mật khẩu 1
    }

    public void addTaiKhoanToSQL() {
        try {
            String sql = "INSERT INTO taikhoan_matkhau VALUES ('" + textFieldTenNguoiDung_DangKy.getText() + "', '" + passwordFieldMatKhau1_DangKy.getText() + "')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO ThongTinCaNhan VALUES ('" + textFieldTenNguoiDung_DangKy.getText() + "', '', '', '')";
            statement.executeUpdate(sql);
        } catch (SQLException e) {

        }
    }

    public int kiemTraTenTK_DangNhap() {
        if (!textFieldTenNguoiDung_DangNhap.getText().matches("^[a-z0-9_-]{3,16}$")) {
            return 0; // Không thỏa mãn a-z, 0-9, -, _, từ 3 đến 16 ký tự
        }
        if (kiemTraTaiKhoanTonTai(textFieldTenNguoiDung_DangNhap.getText())) {
            return 1; // Tên đăng nhập tồn tại
        }
        return -1; // Tên đăng nhập không tồn tại
    }

    public int kiemTraMatKhau_DangNhap() {
        if (passwordFieldMatKhau_DangNhap.getText().length() == 0) {
            return 0; // Bỏ trống mật khẩu
        }
        if (kiemTraTenTK_DangNhap() == 1) {
            try {
                String sql = "SELECT MatKhau FROM taikhoan_matkhau WHERE TenTK = '" + textFieldTenNguoiDung_DangNhap.getText() + "'";
                ResultSet resultSet = statement.executeQuery(sql);
                resultSet.next();
                if (resultSet.getString("MatKhau").equals(passwordFieldMatKhau_DangNhap.getText())) {
                    return 1; // Mật khẩu trùng khớp
                }
                return -1; // Sai mật khẩu
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -2; // Sai tên đăng nhập
    }
}
