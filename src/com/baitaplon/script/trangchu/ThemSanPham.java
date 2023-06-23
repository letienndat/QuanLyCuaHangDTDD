package com.baitaplon.script.trangchu;

import com.baitaplon.script.dangnhap_dangky.DangNhap_DangKy;
import com.baitaplon.script.trangchu.themanh.ImageFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

public class ThemSanPham extends JDialog {
    private static final String sourceImage = "/com/baitaplon/image/";
    private JButton buttonChonAnh;
    private JTextField textFieldMaThietBi;
    private JLabel labelAnh;
    private JLabel labelMaThietBi;
    private JTextField textFieldTenThietBi;
    private JLabel labelGiaThietBi;
    private JTextField textFieldGiaThietBi;
    private JTextField textFieldDungLuongBoNho;
    private JPanel panelThemSanPham;
    private JTextField textFieldMauThietBi;
    private JLabel labelMauThietBi;
    private JLabel labelLoaiManHinh;
    private JTextField textFieldLoaiManHinh;
    private JLabel labelDoPhanGiai;
    private JTextField textFieldDoPhanGiaiDai;
    private JTextField textFieldDoPhanGiaiRong;
    private JLabel labelManHinh;
    private JTextField textFieldManHinh;
    private JLabel labelCameraTruoc;
    private JTextField textFieldCameraTruoc;
    private JLabel labelCameraSau;
    private JTextField textFieldCameraSau;
    private JLabel labelHeDieuHanh;
    private JTextField textFieldHeDieuHanh;
    private JLabel labelCPU;
    private JTextField textFieldCPU;
    private JLabel labelRAM;
    private JTextField textFieldRAM;
    private JLabel labelDungLuongPin;
    private JTextField textFieldDungLuongPin;
    private JLabel labelThoiDiemRaMat;
    private JTextField textFieldThoiDiemRaMat;
    private JLabel labelDungLuongBoNho;
    private JButton buttonDong;
    private JButton buttonThemSanPham;
    private JLabel labelTenThietBi;
    private JLabel labelLoaiThietBi;
    private JComboBox comboBoxLoaiThietBi;
    private JLabel labelThongBaoThemSanPham;
    private JLabel labelLamMoi;
    private JScrollPane scrollPane;
    private JFileChooser fileChooser;
    private File selectedFile;
    private Connection connection;
    private PreparedStatement statement;
    private String src;

    public ThemSanPham() {
        setTitle("THÊM SẢN PHẨM [ADMIN]");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon(DangNhap_DangKy.class.getResource(sourceImage + "Icon_100x100.png")).getImage());
        setModal(true);

        scrollPane = new JScrollPane(panelThemSanPham);
        scrollPane.getVerticalScrollBar().setUnitIncrement(5);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        setContentPane(scrollPane);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        panelThemSanPham.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        init();
    }

    public ThemSanPham(Connection connection) {
        this();
        this.connection = connection;
    }

    private void onOK() {
        saveDataToSQL();
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public JButton getButtonThemSanPham() {
        return buttonThemSanPham;
    }

    public void saveDataToSQL() {
        try {
            String sql = "INSERT INTO %s VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            if (comboBoxLoaiThietBi.getSelectedItem().equals("Iphone")) {
                sql = String.format(sql, "DienThoaiIphone");
            } else if (comboBoxLoaiThietBi.getSelectedItem().equals("SamSung")) {
                sql = String.format(sql, "DienThoaiSamSung");
            } else if (comboBoxLoaiThietBi.getSelectedItem().equals("Oppo")) {
                sql = String.format(sql, "DienThoaiOppo");
            } else if (comboBoxLoaiThietBi.getSelectedItem().equals("Xiaomi")) {
                sql = String.format(sql, "DienThoaiXiaomi");
            } else if (comboBoxLoaiThietBi.getSelectedItem().equals("Realme")) {
                sql = String.format(sql, "DienThoaiRealme");
            } else if (comboBoxLoaiThietBi.getSelectedItem().equals("Vivo")) {
                sql = String.format(sql, "DienThoaiVivo");
            }
            statement = connection.prepareStatement(sql);
            statement.setBinaryStream(1, new FileInputStream(src));
            statement.setString(2, textFieldMaThietBi.getText());
            statement.setString(3, textFieldTenThietBi.getText());
            statement.setInt(4, Integer.parseInt(textFieldGiaThietBi.getText()));
            statement.setString(5, textFieldMauThietBi.getText());
            statement.setInt(6, Integer.parseInt(textFieldDungLuongBoNho.getText()));
            statement.setString(7, textFieldLoaiManHinh.getText());
            statement.setInt(8, Integer.parseInt(textFieldDoPhanGiaiDai.getText()));
            statement.setInt(9, Integer.parseInt(textFieldDoPhanGiaiRong.getText()));
            statement.setFloat(10, Float.parseFloat(textFieldManHinh.getText()));
            statement.setInt(11, Integer.parseInt(textFieldCameraTruoc.getText()));
            statement.setInt(12, Integer.parseInt(textFieldCameraSau.getText()));
            statement.setString(13, textFieldHeDieuHanh.getText());
            statement.setString(14, textFieldCPU.getText());
            statement.setInt(15, Integer.parseInt(textFieldRAM.getText()));
            statement.setInt(16, Integer.parseInt(textFieldDungLuongPin.getText()));
            statement.setInt(17, Integer.parseInt(textFieldThoiDiemRaMat.getText()));
            statement.setInt(18, 0);
            statement.executeUpdate();
            labelThongBaoThemSanPham.setText("Thêm sản phẩm thành công");
        } catch (SQLException e) {
            labelThongBaoThemSanPham.setText("Sản phẩm chứa mã này đã tồn tại");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void restartPanelThemSanPham() {
        labelAnh.setIcon(null);
        labelAnh.setText("");
        textFieldMaThietBi.setText("");
        textFieldTenThietBi.setText("");
        textFieldGiaThietBi.setText("");
        textFieldMauThietBi.setText("");
        textFieldDungLuongBoNho.setText("");
        textFieldLoaiManHinh.setText("");
        textFieldDoPhanGiaiDai.setText("");
        textFieldDoPhanGiaiRong.setText("");
        textFieldManHinh.setText("");
        textFieldCameraTruoc.setText("");
        textFieldCameraSau.setText("");
        textFieldHeDieuHanh.setText("");
        textFieldCPU.setText("");
        textFieldRAM.setText("");
        textFieldDungLuongPin.setText("");
        textFieldThoiDiemRaMat.setText("");
        comboBoxLoaiThietBi.setSelectedIndex(0);
        buttonThemSanPham.setEnabled(false);
        labelLoaiThietBi.setForeground(new Color(0, 0, 0));
        labelThongBaoThemSanPham.setText("");
    }

//    public void getImageSql() {
//        try {
//            String sql = "SELECT AnhThietBi FROM DienThoai WHERE MaThietBi = 'a'";
//            statement = connection.prepareStatement(sql);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                labelCPU.setIcon(new ImageIcon(new ImageIcon(ImageIO.read(resultSet.getBlob("AnhThietBi").getBinaryStream())).getImage().getScaledInstance(180, 200, Image.SCALE_DEFAULT)));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void init() {
        buttonChonAnh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonChonAnh.setFocusPainted(false);
        buttonChonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(new ImageFilter());
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setCurrentDirectory(new File("D:\\BackUp 18-10-2022\\Code\\Project Java\\BaiTapLon\\src\\com\\baitaplon\\image"));
                int result = fileChooser.showOpenDialog(panelThemSanPham);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    src = selectedFile.getAbsolutePath();
                    labelAnh.setIcon(new ImageIcon(new ImageIcon(src).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
                    labelAnh.setText(src.substring(src.lastIndexOf('\\') + 1));
                }
                if (enableButtonThemSanPham()) {
                    buttonThemSanPham.setEnabled(true);
                } else {
                    buttonThemSanPham.setEnabled(false);
                }
                labelThongBaoThemSanPham.setText("");
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                restartPanelThemSanPham();
            }
        });

        labelLamMoi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelLamMoi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                restartPanelThemSanPham();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                labelLamMoi.setText("<HTML><U>Làm mới</U></HTML>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelLamMoi.setText("<HTML>Làm mới</HTML>");
            }
        });

        buttonThemSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonThemSanPham.setFocusPainted(false);
        buttonThemSanPham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDataToSQL();
            }
        });


        buttonDong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonDong.setFocusPainted(false);
        buttonDong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartPanelThemSanPham();
                dispose();
            }
        });

        textFieldMaThietBi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!textFieldMaThietBi.getText().equals("")) {
                    labelMaThietBi.setForeground(new Color(0, 0, 0));
                    if (enableButtonThemSanPham()) {
                        buttonThemSanPham.setEnabled(true);
                    }
                } else {
                    labelMaThietBi.setForeground(new Color(255, 0, 0));
                    buttonThemSanPham.setEnabled(false);
                }
                labelThongBaoThemSanPham.setText("");
            }
        });

        textFieldTenThietBi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!textFieldTenThietBi.getText().equals("")) {
                    labelTenThietBi.setForeground(new Color(0, 0, 0));
                    if (enableButtonThemSanPham()) {
                        buttonThemSanPham.setEnabled(true);
                    }
                } else {
                    labelTenThietBi.setForeground(new Color(255, 0, 0));
                    buttonThemSanPham.setEnabled(false);
                }
                labelThongBaoThemSanPham.setText("");
            }
        });

        textFieldGiaThietBi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldGiaThietBi.getText().matches("[0-9]+")) {
                    labelGiaThietBi.setForeground(new Color(0, 0, 0));
                    if (enableButtonThemSanPham()) {
                        buttonThemSanPham.setEnabled(true);
                    }
                } else {
                    labelGiaThietBi.setForeground(new Color(255, 0, 0));
                    buttonThemSanPham.setEnabled(false);
                }
                labelThongBaoThemSanPham.setText("");
            }
        });

        textFieldMauThietBi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!textFieldMauThietBi.getText().equals("")) {
                    labelMauThietBi.setForeground(new Color(0, 0, 0));
                    if (enableButtonThemSanPham()) {
                        buttonThemSanPham.setEnabled(true);
                    }
                } else {
                    labelMauThietBi.setForeground(new Color(255, 0, 0));
                    buttonThemSanPham.setEnabled(false);
                }
                labelThongBaoThemSanPham.setText("");
            }
        });

        textFieldDungLuongBoNho.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldDungLuongBoNho.getText().matches("[0-9]+")) {
                    labelDungLuongBoNho.setForeground(new Color(0, 0, 0));
                    if (enableButtonThemSanPham()) {
                        buttonThemSanPham.setEnabled(true);
                    }
                } else {
                    labelDungLuongBoNho.setForeground(new Color(255, 0, 0));
                    buttonThemSanPham.setEnabled(false);
                }
                labelThongBaoThemSanPham.setText("");
            }
        });

        textFieldLoaiManHinh.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!textFieldLoaiManHinh.getText().equals("")) {
                    labelLoaiManHinh.setForeground(new Color(0, 0, 0));
                    if (enableButtonThemSanPham()) {
                        buttonThemSanPham.setEnabled(true);
                    }
                } else {
                    labelLoaiManHinh.setForeground(new Color(255, 0, 0));
                    buttonThemSanPham.setEnabled(false);
                }
                labelThongBaoThemSanPham.setText("");
            }
        });

        textFieldDoPhanGiaiRong.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldDoPhanGiaiRong.getText().matches("[0-9]+")) {
                    labelDoPhanGiai.setForeground(new Color(0, 0, 0));
                    if (enableButtonThemSanPham()) {
                        buttonThemSanPham.setEnabled(true);
                    }
                } else {
                    labelDoPhanGiai.setForeground(new Color(255, 0, 0));
                    buttonThemSanPham.setEnabled(false);
                }
                labelThongBaoThemSanPham.setText("");
            }
        });

        textFieldDoPhanGiaiDai.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldDoPhanGiaiDai.getText().matches("[0-9]+")) {
                    labelDoPhanGiai.setForeground(new Color(0, 0, 0));
                    if (enableButtonThemSanPham()) {
                        buttonThemSanPham.setEnabled(true);
                    }
                } else {
                    labelDoPhanGiai.setForeground(new Color(255, 0, 0));
                    buttonThemSanPham.setEnabled(false);
                }
                labelThongBaoThemSanPham.setText("");
            }
        });

        textFieldManHinh.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldManHinh.getText().matches("^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$")) {
                    labelManHinh.setForeground(new Color(0, 0, 0));
                    if (enableButtonThemSanPham()) {
                        buttonThemSanPham.setEnabled(true);
                    }
                } else {
                    labelManHinh.setForeground(new Color(255, 0, 0));
                    buttonThemSanPham.setEnabled(false);
                }
                labelThongBaoThemSanPham.setText("");
            }
        });

        textFieldCameraTruoc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldCameraTruoc.getText().matches("[0-9]+")) {
                    labelCameraTruoc.setForeground(new Color(0, 0, 0));
                    if (enableButtonThemSanPham()) {
                        buttonThemSanPham.setEnabled(true);
                    }
                } else {
                    labelCameraTruoc.setForeground(new Color(255, 0, 0));
                    buttonThemSanPham.setEnabled(false);
                }
                labelThongBaoThemSanPham.setText("");
            }
        });

        textFieldCameraSau.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldCameraSau.getText().matches("[0-9]+")) {
                    labelCameraSau.setForeground(new Color(0, 0, 0));
                    if (enableButtonThemSanPham()) {
                        buttonThemSanPham.setEnabled(true);
                    }
                } else {
                    labelCameraSau.setForeground(new Color(255, 0, 0));
                    buttonThemSanPham.setEnabled(false);
                }
                labelThongBaoThemSanPham.setText("");
            }
        });

        textFieldHeDieuHanh.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!textFieldHeDieuHanh.getText().equals("")) {
                    labelHeDieuHanh.setForeground(new Color(0, 0, 0));
                    if (enableButtonThemSanPham()) {
                        buttonThemSanPham.setEnabled(true);
                    }
                } else {
                    labelHeDieuHanh.setForeground(new Color(255, 0, 0));
                    buttonThemSanPham.setEnabled(false);
                }
                labelThongBaoThemSanPham.setText("");
            }
        });

        textFieldCPU.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!textFieldCPU.getText().equals("")) {
                    labelCPU.setForeground(new Color(0, 0, 0));
                    if (enableButtonThemSanPham()) {
                        buttonThemSanPham.setEnabled(true);
                    }
                } else {
                    labelCPU.setForeground(new Color(255, 0, 0));
                    buttonThemSanPham.setEnabled(false);
                }
                labelThongBaoThemSanPham.setText("");
            }
        });

        textFieldRAM.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldRAM.getText().matches("[0-9]+")) {
                    labelRAM.setForeground(new Color(0, 0, 0));
                    if (enableButtonThemSanPham()) {
                        buttonThemSanPham.setEnabled(true);
                    }
                } else {
                    labelRAM.setForeground(new Color(255, 0, 0));
                    buttonThemSanPham.setEnabled(false);
                }
                labelThongBaoThemSanPham.setText("");
            }
        });

        textFieldDungLuongPin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldDungLuongPin.getText().matches("[0-9]+")) {
                    labelDungLuongPin.setForeground(new Color(0, 0, 0));
                    if (enableButtonThemSanPham()) {
                        buttonThemSanPham.setEnabled(true);
                    }
                } else {
                    labelDungLuongPin.setForeground(new Color(255, 0, 0));
                    buttonThemSanPham.setEnabled(false);
                }
                labelThongBaoThemSanPham.setText("");
            }
        });

        textFieldThoiDiemRaMat.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldThoiDiemRaMat.getText().matches("[0-9]+")) {
                    labelThoiDiemRaMat.setForeground(new Color(0, 0, 0));
                    if (enableButtonThemSanPham()) {
                        buttonThemSanPham.setEnabled(true);
                    }
                } else {
                    labelThoiDiemRaMat.setForeground(new Color(255, 0, 0));
                    buttonThemSanPham.setEnabled(false);
                }
                labelThongBaoThemSanPham.setText("");
            }
        });

        comboBoxLoaiThietBi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!comboBoxLoaiThietBi.getSelectedItem().equals("...")) {
                    labelLoaiThietBi.setForeground(new Color(0, 0, 0));
                    if (enableButtonThemSanPham()) {
                        buttonThemSanPham.setEnabled(true);
                    }
                } else {
                    labelLoaiThietBi.setForeground(new Color(255, 0, 0));
                    buttonThemSanPham.setEnabled(false);
                }
                labelThongBaoThemSanPham.setText("");
            }
        });
    }

    public boolean enableButtonThemSanPham() {
        if (!labelAnh.getText().equals("") && labelAnh.getIcon() != null
                && labelMaThietBi.getForeground().equals(new Color(0, 0, 0)) && !textFieldMaThietBi.getText().equals("")
                && labelTenThietBi.getForeground().equals(new Color(0, 0, 0)) && !textFieldTenThietBi.getText().equals("")
                && labelGiaThietBi.getForeground().equals(new Color(0, 0, 0)) && !textFieldGiaThietBi.getText().equals("")
                && labelMauThietBi.getForeground().equals(new Color(0, 0, 0)) && !textFieldMauThietBi.getText().equals("")
                && labelDungLuongBoNho.getForeground().equals(new Color(0, 0, 0)) && !textFieldDungLuongBoNho.getText().equals("")
                && labelLoaiManHinh.getForeground().equals(new Color(0, 0, 0)) && !textFieldLoaiManHinh.getText().equals("")
                && labelDoPhanGiai.getForeground().equals(new Color(0, 0, 0)) && !textFieldDoPhanGiaiDai.getText().equals("") && !textFieldDoPhanGiaiRong.getText().equals("")
                && labelManHinh.getForeground().equals(new Color(0, 0, 0)) && !textFieldManHinh.getText().equals("")
                && labelCameraTruoc.getForeground().equals(new Color(0, 0, 0)) && !textFieldCameraTruoc.getText().equals("")
                && labelCameraSau.getForeground().equals(new Color(0, 0, 0)) && !textFieldCameraSau.getText().equals("")
                && labelLoaiManHinh.getForeground().equals(new Color(0, 0, 0)) && !textFieldLoaiManHinh.getText().equals("")
                && labelHeDieuHanh.getForeground().equals(new Color(0, 0, 0)) && !textFieldHeDieuHanh.getText().equals("")
                && labelCPU.getForeground().equals(new Color(0, 0, 0)) && !textFieldCPU.getText().equals("")
                && labelRAM.getForeground().equals(new Color(0, 0, 0)) && !textFieldRAM.getText().equals("")
                && labelDungLuongPin.getForeground().equals(new Color(0, 0, 0)) && !textFieldDungLuongPin.getText().equals("")
                && labelThoiDiemRaMat.getForeground().equals(new Color(0, 0, 0)) && !textFieldThoiDiemRaMat.getText().equals("")
                && labelLoaiThietBi.getForeground().equals(new Color(0, 0, 0)) && !comboBoxLoaiThietBi.getSelectedItem().equals("...")) {
            return true;
        }
        return false;
    }

    public JComboBox getComboBoxLoaiThietBi() {
        return comboBoxLoaiThietBi;
    }

    public JButton getButtonDong() {
        return buttonDong;
    }

    public JLabel getLabelLamMoi() {
        return labelLamMoi;
    }
}
