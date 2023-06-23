package com.baitaplon.script.trangchu;

import com.baitaplon.script.dangnhap_dangky.DangNhap_DangKy;
import com.baitaplon.script.dienthoai.DienThoai;
import com.baitaplon.script.trangchu.themanh.ImageFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

public class SuaSanPham extends JDialog {
    private static final String sourceImage = "/com/baitaplon/image/";
    private JButton buttonChonAnh;
    private JTextField textFieldMaThietBi;
    private JLabel labelAnh;
    private JLabel labelMaThietBi;
    private JTextField textFieldTenThietBi;
    private JLabel labelGiaThietBi;
    private JTextField textFieldGiaThietBi;
    private JTextField textFieldDungLuongBoNho;
    private JPanel panelSuaSanPham;
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
    private JButton buttonSuaSanPham;
    private JLabel labelTenThietBi;
    private JLabel labelLoaiThietBi;
    private JComboBox comboBoxLoaiThietBi;
    private JLabel labelThongBaoSuaSanPham;
    private JScrollPane scrollPane;
    private JFileChooser fileChooser;
    private File selectedFile;
    private Connection connection;
    private PreparedStatement statement;
    private DienThoai dienThoai;
    private String src;
    private String loaiThietBi;
    private boolean selectAnh = false;

    public SuaSanPham() {
        setTitle("SỬA SẢN PHẨM [ADMIN]");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setIconImage(new ImageIcon(DangNhap_DangKy.class.getResource(sourceImage + "Icon_100x100.png")).getImage());
        setModal(true);

        scrollPane = new JScrollPane(panelSuaSanPham);
        scrollPane.getVerticalScrollBar().setUnitIncrement(5);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        setContentPane(scrollPane);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        panelSuaSanPham.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public SuaSanPham(Connection connection, DienThoai dienThoai, String loaiThietBi) {
        this();
        this.connection = connection;
        this.dienThoai = dienThoai;
        this.loaiThietBi = loaiThietBi;

        init();
    }

    private void onOK() {
        saveDataToSQL();
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public JButton getButtonSuaSanPham() {
        return buttonSuaSanPham;
    }

    public void saveDataToSQL() {
        try {
            String sql = "UPDATE %s SET " +
                    "AnhThietBi = ?, " +
                    "TenThietBi = ?, " +
                    "GiaThietBi = ?, " +
                    "MauThietBi = ?, " +
                    "DungLuongBoNho = ?, " +
                    "LoaiManHinh = ?, " +
                    "DoPhanGiaiDai = ?, " +
                    "DoPhanGiaiRong = ?, " +
                    "ManHinh = ?, " +
                    "CameraTruoc = ?, " +
                    "CameraSau = ?, " +
                    "HeDieuHanh = ?, " +
                    "CPU = ?, " +
                    "RAM = ?, " +
                    "DungLuongPin = ?, " +
                    "ThoiDiemRaMat = ? " +
                    "WHERE MaThietBi = ?";
            if (!selectAnh) {
                sql = sql.replace("AnhThietBi = ?, ", "");
            }
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
            int i = 1;
            if (selectAnh) {
                statement.setBinaryStream(i++, new FileInputStream(src));
            } else {

            }
            statement.setString(i++, textFieldTenThietBi.getText());
            statement.setInt(i++, Integer.parseInt(textFieldGiaThietBi.getText()));
            statement.setString(i++, textFieldMauThietBi.getText());
            statement.setInt(i++, Integer.parseInt(textFieldDungLuongBoNho.getText()));
            statement.setString(i++, textFieldLoaiManHinh.getText());
            statement.setInt(i++, Integer.parseInt(textFieldDoPhanGiaiDai.getText()));
            statement.setInt(i++, Integer.parseInt(textFieldDoPhanGiaiRong.getText()));
            statement.setFloat(i++, Float.parseFloat(textFieldManHinh.getText()));
            statement.setInt(i++, Integer.parseInt(textFieldCameraTruoc.getText()));
            statement.setInt(i++, Integer.parseInt(textFieldCameraSau.getText()));
            statement.setString(i++, textFieldHeDieuHanh.getText());
            statement.setString(i++, textFieldCPU.getText());
            statement.setInt(i++, Integer.parseInt(textFieldRAM.getText()));
            statement.setInt(i++, Integer.parseInt(textFieldDungLuongPin.getText()));
            statement.setInt(i++, Integer.parseInt(textFieldThoiDiemRaMat.getText()));
            statement.setString(i++, textFieldMaThietBi.getText());
            statement.executeUpdate();
            labelThongBaoSuaSanPham.setText("Sửa sản phẩm thành công");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void initOther() {
        labelTenThietBi.setForeground(new Color(0, 0, 0));
        labelGiaThietBi.setForeground(new Color(0, 0, 0));
        labelMauThietBi.setForeground(new Color(0, 0, 0));
        labelDungLuongBoNho.setForeground(new Color(0, 0, 0));
        labelManHinh.setForeground(new Color(0, 0, 0));
        labelDoPhanGiai.setForeground(new Color(0, 0, 0));
        labelCameraTruoc.setForeground(new Color(0, 0, 0));
        labelCameraSau.setForeground(new Color(0, 0, 0));
        labelLoaiManHinh.setForeground(new Color(0, 0, 0));
        labelHeDieuHanh.setForeground(new Color(0, 0, 0));
        labelCPU.setForeground(new Color(0, 0, 0));
        labelRAM.setForeground(new Color(0, 0, 0));
        labelDungLuongPin.setForeground(new Color(0, 0, 0));
        labelThoiDiemRaMat.setForeground(new Color(0, 0, 0));

        labelAnh.setIcon(dienThoai.getAnhDienThoai().getIcon());
        labelAnh.setText(dienThoai.getAnhDienThoai().getIcon().toString().substring(dienThoai.getAnhDienThoai().getIcon().toString().lastIndexOf('.') + 1));
        textFieldMaThietBi.setText(dienThoai.getMaDienThoai());
        textFieldTenThietBi.setText(dienThoai.getTenDienThoai());
        textFieldGiaThietBi.setText(String.valueOf(dienThoai.getGiaDienThoai()));
        textFieldMauThietBi.setText(dienThoai.getMauDienThoai());
        textFieldDungLuongBoNho.setText(String.valueOf(dienThoai.getDungLuongBoNho()));
        textFieldLoaiManHinh.setText(dienThoai.getThongSoDienThoai().getLoaiManHinh());
        textFieldDoPhanGiaiDai.setText(String.valueOf(dienThoai.getThongSoDienThoai().getDoPhanGiaiDai()));
        textFieldDoPhanGiaiRong.setText(String.valueOf(dienThoai.getThongSoDienThoai().getDoPhanGiaiRong()));
        textFieldManHinh.setText(String.valueOf(dienThoai.getThongSoDienThoai().getManHinh()));
        textFieldCameraTruoc.setText(String.valueOf(dienThoai.getThongSoDienThoai().getCameraTruoc()));
        textFieldCameraSau.setText(String.valueOf(dienThoai.getThongSoDienThoai().getCameraSau()));
        textFieldHeDieuHanh.setText(dienThoai.getThongSoDienThoai().getHeDieuHanh());
        textFieldCPU.setText(dienThoai.getThongSoDienThoai().getCPU());
        textFieldRAM.setText(String.valueOf(dienThoai.getThongSoDienThoai().getRAM()));
        textFieldDungLuongPin.setText(String.valueOf(dienThoai.getThongSoDienThoai().getDungLuongPin()));
        textFieldThoiDiemRaMat.setText(String.valueOf(dienThoai.getThongSoDienThoai().getThoiDiemRaMat()));
        comboBoxLoaiThietBi.setSelectedItem(loaiThietBi);
        buttonSuaSanPham.setEnabled(false);
        labelLoaiThietBi.setForeground(new Color(0, 0, 0));
        labelThongBaoSuaSanPham.setText("");
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
        initOther();

        buttonChonAnh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonChonAnh.setFocusPainted(false);
        buttonChonAnh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(new ImageFilter());
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setCurrentDirectory(new File("D:\\BackUp 18-10-2022\\Code\\Project Java\\BaiTapLon\\src\\com\\baitaplon\\image"));
                int result = fileChooser.showOpenDialog(panelSuaSanPham);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    src = selectedFile.getAbsolutePath();
                    labelAnh.setIcon(new ImageIcon(new ImageIcon(src).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
                    labelAnh.setText(src.substring(src.lastIndexOf('\\') + 1));
                }
                if (enableButtonSuaSanPham()) {
                    selectAnh = true;
                    buttonSuaSanPham.setEnabled(true);
                } else {
                    buttonSuaSanPham.setEnabled(false);
                }
                labelThongBaoSuaSanPham.setText("");
            }
        });

        buttonSuaSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonSuaSanPham.setFocusPainted(false);
        buttonSuaSanPham.addActionListener(new ActionListener() {
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
                dispose();
            }
        });

        textFieldTenThietBi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!textFieldTenThietBi.getText().equals("")) {
                    labelTenThietBi.setForeground(new Color(0, 0, 0));
                    if (enableButtonSuaSanPham()) {
                        buttonSuaSanPham.setEnabled(true);
                    }
                } else {
                    labelTenThietBi.setForeground(new Color(255, 0, 0));
                    buttonSuaSanPham.setEnabled(false);
                }
                labelThongBaoSuaSanPham.setText("");
            }
        });

        textFieldGiaThietBi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldGiaThietBi.getText().matches("[0-9]+")) {
                    labelGiaThietBi.setForeground(new Color(0, 0, 0));
                    if (enableButtonSuaSanPham()) {
                        buttonSuaSanPham.setEnabled(true);
                    }
                } else {
                    labelGiaThietBi.setForeground(new Color(255, 0, 0));
                    buttonSuaSanPham.setEnabled(false);
                }
                labelThongBaoSuaSanPham.setText("");
            }
        });

        textFieldMauThietBi.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!textFieldMauThietBi.getText().equals("")) {
                    labelMauThietBi.setForeground(new Color(0, 0, 0));
                    if (enableButtonSuaSanPham()) {
                        buttonSuaSanPham.setEnabled(true);
                    }
                } else {
                    labelMauThietBi.setForeground(new Color(255, 0, 0));
                    buttonSuaSanPham.setEnabled(false);
                }
                labelThongBaoSuaSanPham.setText("");
            }
        });

        textFieldDungLuongBoNho.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldDungLuongBoNho.getText().matches("[0-9]+")) {
                    labelDungLuongBoNho.setForeground(new Color(0, 0, 0));
                    if (enableButtonSuaSanPham()) {
                        buttonSuaSanPham.setEnabled(true);
                    }
                } else {
                    labelDungLuongBoNho.setForeground(new Color(255, 0, 0));
                    buttonSuaSanPham.setEnabled(false);
                }
                labelThongBaoSuaSanPham.setText("");
            }
        });

        textFieldLoaiManHinh.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!textFieldLoaiManHinh.getText().equals("")) {
                    labelLoaiManHinh.setForeground(new Color(0, 0, 0));
                    if (enableButtonSuaSanPham()) {
                        buttonSuaSanPham.setEnabled(true);
                    }
                } else {
                    labelLoaiManHinh.setForeground(new Color(255, 0, 0));
                    buttonSuaSanPham.setEnabled(false);
                }
                labelThongBaoSuaSanPham.setText("");
            }
        });

        textFieldDoPhanGiaiRong.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldDoPhanGiaiRong.getText().matches("[0-9]+")) {
                    labelDoPhanGiai.setForeground(new Color(0, 0, 0));
                    if (enableButtonSuaSanPham()) {
                        buttonSuaSanPham.setEnabled(true);
                    }
                } else {
                    labelDoPhanGiai.setForeground(new Color(255, 0, 0));
                    buttonSuaSanPham.setEnabled(false);
                }
                labelThongBaoSuaSanPham.setText("");
            }
        });

        textFieldDoPhanGiaiDai.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldDoPhanGiaiDai.getText().matches("[0-9]+")) {
                    labelDoPhanGiai.setForeground(new Color(0, 0, 0));
                    if (enableButtonSuaSanPham()) {
                        buttonSuaSanPham.setEnabled(true);
                    }
                } else {
                    labelDoPhanGiai.setForeground(new Color(255, 0, 0));
                    buttonSuaSanPham.setEnabled(false);
                }
                labelThongBaoSuaSanPham.setText("");
            }
        });

        textFieldManHinh.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldManHinh.getText().matches("^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$")) {
                    labelManHinh.setForeground(new Color(0, 0, 0));
                    if (enableButtonSuaSanPham()) {
                        buttonSuaSanPham.setEnabled(true);
                    }
                } else {
                    labelManHinh.setForeground(new Color(255, 0, 0));
                    buttonSuaSanPham.setEnabled(false);
                }
                labelThongBaoSuaSanPham.setText("");
            }
        });

        textFieldCameraTruoc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldCameraTruoc.getText().matches("[0-9]+")) {
                    labelCameraTruoc.setForeground(new Color(0, 0, 0));
                    if (enableButtonSuaSanPham()) {
                        buttonSuaSanPham.setEnabled(true);
                    }
                } else {
                    labelCameraTruoc.setForeground(new Color(255, 0, 0));
                    buttonSuaSanPham.setEnabled(false);
                }
                labelThongBaoSuaSanPham.setText("");
            }
        });

        textFieldCameraSau.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldCameraSau.getText().matches("[0-9]+")) {
                    labelCameraSau.setForeground(new Color(0, 0, 0));
                    if (enableButtonSuaSanPham()) {
                        buttonSuaSanPham.setEnabled(true);
                    }
                } else {
                    labelCameraSau.setForeground(new Color(255, 0, 0));
                    buttonSuaSanPham.setEnabled(false);
                }
                labelThongBaoSuaSanPham.setText("");
            }
        });

        textFieldHeDieuHanh.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!textFieldHeDieuHanh.getText().equals("")) {
                    labelHeDieuHanh.setForeground(new Color(0, 0, 0));
                    if (enableButtonSuaSanPham()) {
                        buttonSuaSanPham.setEnabled(true);
                    }
                } else {
                    labelHeDieuHanh.setForeground(new Color(255, 0, 0));
                    buttonSuaSanPham.setEnabled(false);
                }
                labelThongBaoSuaSanPham.setText("");
            }
        });

        textFieldCPU.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!textFieldCPU.getText().equals("")) {
                    labelCPU.setForeground(new Color(0, 0, 0));
                    if (enableButtonSuaSanPham()) {
                        buttonSuaSanPham.setEnabled(true);
                    }
                } else {
                    labelCPU.setForeground(new Color(255, 0, 0));
                    buttonSuaSanPham.setEnabled(false);
                }
                labelThongBaoSuaSanPham.setText("");
            }
        });

        textFieldRAM.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldRAM.getText().matches("[0-9]+")) {
                    labelRAM.setForeground(new Color(0, 0, 0));
                    if (enableButtonSuaSanPham()) {
                        buttonSuaSanPham.setEnabled(true);
                    }
                } else {
                    labelRAM.setForeground(new Color(255, 0, 0));
                    buttonSuaSanPham.setEnabled(false);
                }
                labelThongBaoSuaSanPham.setText("");
            }
        });

        textFieldDungLuongPin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldDungLuongPin.getText().matches("[0-9]+")) {
                    labelDungLuongPin.setForeground(new Color(0, 0, 0));
                    if (enableButtonSuaSanPham()) {
                        buttonSuaSanPham.setEnabled(true);
                    }
                } else {
                    labelDungLuongPin.setForeground(new Color(255, 0, 0));
                    buttonSuaSanPham.setEnabled(false);
                }
                labelThongBaoSuaSanPham.setText("");
            }
        });

        textFieldThoiDiemRaMat.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textFieldThoiDiemRaMat.getText().matches("[0-9]+")) {
                    labelThoiDiemRaMat.setForeground(new Color(0, 0, 0));
                    if (enableButtonSuaSanPham()) {
                        buttonSuaSanPham.setEnabled(true);
                    }
                } else {
                    labelThoiDiemRaMat.setForeground(new Color(255, 0, 0));
                    buttonSuaSanPham.setEnabled(false);
                }
                labelThongBaoSuaSanPham.setText("");
            }
        });
    }

    public boolean enableButtonSuaSanPham() {
        if (!labelAnh.getText().equals("") && labelAnh.getIcon() != null
                && labelTenThietBi.getForeground().equals(new Color(0, 0, 0))
                && labelGiaThietBi.getForeground().equals(new Color(0, 0, 0))
                && labelMauThietBi.getForeground().equals(new Color(0, 0, 0))
                && labelDungLuongBoNho.getForeground().equals(new Color(0, 0, 0))
                && labelLoaiManHinh.getForeground().equals(new Color(0, 0, 0))
                && labelDoPhanGiai.getForeground().equals(new Color(0, 0, 0))
                && labelManHinh.getForeground().equals(new Color(0, 0, 0))
                && labelCameraTruoc.getForeground().equals(new Color(0, 0, 0))
                && labelCameraSau.getForeground().equals(new Color(0, 0, 0))
                && labelLoaiManHinh.getForeground().equals(new Color(0, 0, 0))
                && labelHeDieuHanh.getForeground().equals(new Color(0, 0, 0))
                && labelCPU.getForeground().equals(new Color(0, 0, 0))
                && labelRAM.getForeground().equals(new Color(0, 0, 0))
                && labelDungLuongPin.getForeground().equals(new Color(0, 0, 0))
                && labelThoiDiemRaMat.getForeground().equals(new Color(0, 0, 0))) {
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
}
