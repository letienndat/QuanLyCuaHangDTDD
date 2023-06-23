package com.baitaplon.script.trangchu;

import com.baitaplon.script.dangnhap_dangky.DangNhap_DangKy;
import com.baitaplon.script.danhsachdienthoai.*;
import com.baitaplon.script.trangchu.listpanelsanpham.*;
import com.baitaplon.script.trangchu.timkiem.TimKiem;
import com.baitaplon.script.wraplayout.WrapLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class TrangChuAdmin extends JFrame {
    private static final String sourceImage = "/com/baitaplon/image/";
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JPanel panelMain;
    private JLabel jLabelTieuDe_TrangChu;
    private JLabel jLabelTenNguoiDung_TrangChu;
    private JTabbedPane tabbedPane_TrangChu;
    private JPanel panelIPHONE_TrangChu;
    private JPanel panelSAMSUNG_TrangChu;
    private JPanel panelOPPO_TrangChu;
    private JPanel panelXIAOMI_TrangChu;
    private JPanel panelREALME_TrangChu;
    private JPanel panelVIVO_TrangChu;
    private JScrollPane scrollPaneIphone_TrangChu;
    private JScrollPane scrollPaneSamSung_TrangChu;
    private JScrollPane scrollPaneOppo_TrangChu;
    private JScrollPane scrollPaneXiaomi_TrangChu;
    private JScrollPane scrollPaneRealme_TrangChu;
    private JScrollPane scrollPaneVivo_TrangChu;
    private JLabel labelTieuDeTimKiem;
    private JTextField textFieldTimKiem;
    private JLabel labelThemSanPham_TrangChu;
    private JPanel panelTimKiem;
    private JLabel labelQuayTroVeTrangChu;
    private JLabel labelThongBaoTimKiem;
    private JPanel panelRessTimKiem;
    private PreparedStatement statement;
    private Connection connection;
    private DanhSachIphone danhSachIphone;
    private ListPanelSanPhamIphone listPanelSanPhamIphone;
    private DanhSachSamSung danhSachSamSung;
    private ListPanelSanPhamSamSung listPanelSanPhamSamSung;
    private DanhSachOppo danhSachOppo;
    private ListPanelSanPhamOppo listPanelSanPhamOppo;
    private DanhSachRealme danhSachRealme;
    private ListPanelSanPhamRealme listPanelSanPhamRealme;
    private DanhSachVivo danhSachVivo;
    private ListPanelSanPhamVivo listPanelSanPhamVivo;
    private DanhSachXiaomi danhSachXiaomi;
    private ListPanelSanPhamXiaomi listPanelSanPhamXiaomi;
    private TimKiem timKiem;
    private JPanel panelResTimKiem;
    private JScrollPane scrollPaneResTimKiem;

    public TrangChuAdmin() {
        super("HỆ THỐNG CỬA HÀNG ĐIỆN THOẠI DI ĐỘNG [ADMIN]");
        double width = screenSize.getWidth() - screenSize.getWidth() * 0.1;
        double height = screenSize.getHeight() - screenSize.getHeight() * 0.1;
        setIconImage(new ImageIcon(DangNhap_DangKy.class.getResource(sourceImage + "Icon_100x100.png")).getImage());
        setSize(new Dimension((int) width, (int) height));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setContentPane(panelMain);
    }

    public TrangChuAdmin(String s, Connection connection) {
        this();
        this.connection = connection;
        init();
        jLabelTenNguoiDung_TrangChu.setText(s);

        danhSachIphone = listPanelSanPhamIphone.loadListDienThoai();
        danhSachXiaomi = listPanelSanPhamXiaomi.loadListDienThoai();
        danhSachVivo = listPanelSanPhamVivo.loadListDienThoai();
        danhSachOppo = listPanelSanPhamOppo.loadListDienThoai();
        danhSachRealme = listPanelSanPhamRealme.loadListDienThoai();
        danhSachSamSung = listPanelSanPhamSamSung.loadListDienThoai();
    }

    public void init() {
        initOther();
        panelIphoneTrangChu();
        panelSamSungTrangChu();
        panelOppoTrangChu();
        panelXiaomiTrangChu();
        panelRealmeTrangChu();
        panelVivoTrangChu();
    }

    public void initOther() {
        panelTimKiem.setVisible(false);

        labelThemSanPhamTrangChu();
        jLabelTenNguoiDung_TrangChu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        TrangChuAdmin temp = this;
        jLabelTenNguoiDung_TrangChu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EventQueue.invokeLater(() -> {
                    new TaiKhoan(connection, jLabelTenNguoiDung_TrangChu.getText(), temp).setVisible(true);
                });
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabelTenNguoiDung_TrangChu.setForeground(new Color(242, 100, 0));
                jLabelTenNguoiDung_TrangChu.setIcon(new ImageIcon(TrangChuAdmin.class.getResource(sourceImage + "account_orange.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jLabelTenNguoiDung_TrangChu.setForeground(new Color(0, 0, 0));
                jLabelTenNguoiDung_TrangChu.setIcon(new ImageIcon(TrangChuAdmin.class.getResource(sourceImage + "account_default.png")));
            }
        });

        labelThemSanPham_TrangChu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        listPanelSanPhamIphone = new ListPanelSanPhamIphone(connection);
        listPanelSanPhamSamSung = new ListPanelSanPhamSamSung(connection);
        listPanelSanPhamRealme = new ListPanelSanPhamRealme(connection);
        listPanelSanPhamVivo = new ListPanelSanPhamVivo(connection);
        listPanelSanPhamOppo = new ListPanelSanPhamOppo(connection);
        listPanelSanPhamXiaomi = new ListPanelSanPhamXiaomi(connection);

        panelResTimKiem();

        textFieldTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!textFieldTimKiem.getText().equals("")) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                        tabbedPane_TrangChu.setVisible(false);
                        panelTimKiem.setBorder(BorderFactory.createTitledBorder(null, "Kết quả tìm kiếm liên quan tới: " + textFieldTimKiem.getText(), 0, 3, new Font("Leelawadee UI", Font.PLAIN, 13)));
                        panelTimKiem.setVisible(true);
                        timKiem = new TimKiem(textFieldTimKiem.getText(), connection);
                        if (timKiem.getDienThoaiListTimKiem() != null) {
                            loadListResTimKiem(timKiem.getDienThoaiListTimKiem());
                            labelThongBaoTimKiem.setText("");
                            scrollPaneResTimKiem.setVisible(true);
                        } else {
                            scrollPaneResTimKiem.setVisible(false);
                            labelThongBaoTimKiem.setText("Rất tiếc, không có sản phẩm nào phù hợp với yêu cầu tìm kiếm của bạn!");
                        }
                    }
                }
            }
        });

        labelQuayTroVeTrangChu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        labelQuayTroVeTrangChu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panelTimKiem.setVisible(false);
                loadListIphone(listPanelSanPhamIphone.getListPanelSanPham());
                loadListIphone(listPanelSanPhamIphone.getListPanelSanPham());

                loadListXiaomi(listPanelSanPhamXiaomi.getListPanelSanPham());
                loadListXiaomi(listPanelSanPhamXiaomi.getListPanelSanPham());

                loadListRealme(listPanelSanPhamRealme.getListPanelSanPham());
                loadListRealme(listPanelSanPhamRealme.getListPanelSanPham());

                loadListVivo(listPanelSanPhamVivo.getListPanelSanPham());
                loadListVivo(listPanelSanPhamVivo.getListPanelSanPham());

                loadListSamSung(listPanelSanPhamSamSung.getListPanelSanPham());
                loadListSamSung(listPanelSanPhamSamSung.getListPanelSanPham());

                loadListOppo(listPanelSanPhamOppo.getListPanelSanPham());
                loadListOppo(listPanelSanPhamOppo.getListPanelSanPham());

                tabbedPane_TrangChu.setSelectedIndex(0);
                tabbedPane_TrangChu.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                labelQuayTroVeTrangChu.setText("<HTML><U>Quay trở về trang chủ</U></HTML>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelQuayTroVeTrangChu.setText("<HTML>Quay trở về trang chủ</HTML>");
            }
        });
    }

    public void labelThemSanPhamTrangChu() {
        ThemSanPham themSanPham = new ThemSanPham(connection);
        labelThemSanPham_TrangChu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                labelThemSanPham_TrangChu.setText("<HTML><U>Thêm sản phẩm</U><HTML>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelThemSanPham_TrangChu.setText("<HTML>Thêm sản phẩm<HTML>");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                EventQueue.invokeLater(() -> {
                    themSanPham.setVisible(true);
                });
            }
        });
        themSanPham.getButtonThemSanPham().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (themSanPham.getComboBoxLoaiThietBi().getSelectedItem().equals("Iphone")) {
                    loadListIphone(listPanelSanPhamIphone.getListPanelSanPham());
                    loadListIphone(listPanelSanPhamIphone.getListPanelSanPham());
                } else if (themSanPham.getComboBoxLoaiThietBi().getSelectedItem().equals("SamSung")) {
                    loadListSamSung(listPanelSanPhamSamSung.getListPanelSanPham());
                    loadListSamSung(listPanelSanPhamSamSung.getListPanelSanPham());
                } else if (themSanPham.getComboBoxLoaiThietBi().getSelectedItem().equals("Xiaomi")) {
                    loadListXiaomi(listPanelSanPhamXiaomi.getListPanelSanPham());
                    loadListXiaomi(listPanelSanPhamXiaomi.getListPanelSanPham());
                } else if (themSanPham.getComboBoxLoaiThietBi().getSelectedItem().equals("Realme")) {
                    loadListRealme(listPanelSanPhamRealme.getListPanelSanPham());
                    loadListRealme(listPanelSanPhamRealme.getListPanelSanPham());
                } else if (themSanPham.getComboBoxLoaiThietBi().getSelectedItem().equals("Oppo")) {
                    loadListOppo(listPanelSanPhamOppo.getListPanelSanPham());
                    loadListOppo(listPanelSanPhamOppo.getListPanelSanPham());
                } else if (themSanPham.getComboBoxLoaiThietBi().getSelectedItem().equals("Vivo")) {
                    loadListVivo(listPanelSanPhamVivo.getListPanelSanPham());
                    loadListVivo(listPanelSanPhamVivo.getListPanelSanPham());
                }
            }
        });
    }

    public void panelResTimKiem() {
        panelResTimKiem = new JPanel();
        panelResTimKiem.setBackground(new Color(255, 255, 255, 255));
        panelResTimKiem.setLayout(new WrapLayout(WrapLayout.CENTER, 30, 30));

        scrollPaneResTimKiem = new JScrollPane(panelResTimKiem);

        scrollPaneResTimKiem.getVerticalScrollBar().setUnitIncrement(16);
        scrollPaneResTimKiem.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneResTimKiem.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panelRessTimKiem.add(scrollPaneResTimKiem);
    }

    public void loadListResTimKiem(List<JPanel> panelDienThoaiResTimKiem) {
        panelResTimKiem.removeAll();
        if (panelDienThoaiResTimKiem != null) {
            panelDienThoaiResTimKiem.forEach(i -> {
                i.setPreferredSize(new Dimension(204, 320));
                ((JPanel) i.getComponents()[1]).getComponents()[5].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        loadListResTimKiem(timKiem.getDienThoaiListTimKiem());
                        loadListResTimKiem(timKiem.getDienThoaiListTimKiem());
                    }
                });
                ((JPanel) i.getComponents()[1]).getComponents()[4].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        loadListResTimKiem(timKiem.getDienThoaiListTimKiem());
                        loadListResTimKiem(timKiem.getDienThoaiListTimKiem());
                    }
                });
                panelResTimKiem.add(i);
            });
        }
        scrollPaneResTimKiem.setViewportView(panelResTimKiem);
        scrollPaneResTimKiem.repaint();
    }

    public void panelIphoneTrangChu() {
        panelIPHONE_TrangChu = new JPanel();
        panelIPHONE_TrangChu.setBackground(new Color(255, 255, 255, 255));
        panelIPHONE_TrangChu.setLayout(new WrapLayout(WrapLayout.CENTER, 30, 30));

        scrollPaneIphone_TrangChu = new JScrollPane(panelIPHONE_TrangChu);

        loadListIphone(listPanelSanPhamIphone.getListPanelSanPham());

        scrollPaneIphone_TrangChu.getVerticalScrollBar().setUnitIncrement(16);
        scrollPaneIphone_TrangChu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneIphone_TrangChu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        tabbedPane_TrangChu.add("IPHONE", scrollPaneIphone_TrangChu);
    }

    public void loadListIphone(List<JPanel> listPanelSanPham) {
        panelIPHONE_TrangChu.removeAll();
        listPanelSanPham.forEach(i -> {
            i.setPreferredSize(new Dimension(204, 320));
            ((JPanel) i.getComponents()[1]).getComponents()[5].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    loadListIphone(listPanelSanPhamIphone.getListPanelSanPham());
                    loadListIphone(listPanelSanPhamIphone.getListPanelSanPham());
                }
            });
            ((JPanel) i.getComponents()[1]).getComponents()[4].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    loadListIphone(listPanelSanPhamIphone.getListPanelSanPham());
                    loadListIphone(listPanelSanPhamIphone.getListPanelSanPham());
                }
            });
            panelIPHONE_TrangChu.add(i);
        });
        scrollPaneIphone_TrangChu.setViewportView(panelIPHONE_TrangChu);
        scrollPaneIphone_TrangChu.repaint();
        danhSachIphone = listPanelSanPhamIphone.loadListDienThoai();
    }

    public void panelSamSungTrangChu() {
        panelSAMSUNG_TrangChu = new JPanel();
        panelSAMSUNG_TrangChu.setBackground(new Color(255, 255, 255));
        panelSAMSUNG_TrangChu.setLayout(new WrapLayout(WrapLayout.CENTER, 30, 30));

        scrollPaneSamSung_TrangChu = new JScrollPane(panelSAMSUNG_TrangChu);

        loadListSamSung(listPanelSanPhamSamSung.getListPanelSanPham());

        scrollPaneSamSung_TrangChu.getVerticalScrollBar().setUnitIncrement(16);
        scrollPaneSamSung_TrangChu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneSamSung_TrangChu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        tabbedPane_TrangChu.add("SAMSUNG", scrollPaneSamSung_TrangChu);
    }

    public void loadListSamSung(List<JPanel> listPanelSanPham) {
        panelSAMSUNG_TrangChu.removeAll();
        listPanelSanPham.forEach(i -> {
            i.setPreferredSize(new Dimension(204, 320));
            ((JPanel) i.getComponents()[1]).getComponents()[5].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    loadListSamSung(listPanelSanPhamSamSung.getListPanelSanPham());
                    loadListSamSung(listPanelSanPhamSamSung.getListPanelSanPham());
                }
            });
            ((JPanel) i.getComponents()[1]).getComponents()[4].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    loadListSamSung(listPanelSanPhamSamSung.getListPanelSanPham());
                    loadListSamSung(listPanelSanPhamSamSung.getListPanelSanPham());
                }
            });
            panelSAMSUNG_TrangChu.add(i);
        });
        scrollPaneSamSung_TrangChu.setViewportView(panelSAMSUNG_TrangChu);
        scrollPaneSamSung_TrangChu.repaint();
        danhSachSamSung = listPanelSanPhamSamSung.loadListDienThoai();
    }

    public void panelOppoTrangChu() {
        panelOPPO_TrangChu = new JPanel();
        panelOPPO_TrangChu.setBackground(new Color(255, 255, 255));
        panelOPPO_TrangChu.setLayout(new WrapLayout(WrapLayout.CENTER, 30, 30));

        scrollPaneOppo_TrangChu = new JScrollPane(panelOPPO_TrangChu);

        loadListOppo(listPanelSanPhamOppo.getListPanelSanPham());

        scrollPaneOppo_TrangChu.getVerticalScrollBar().setUnitIncrement(16);
        scrollPaneOppo_TrangChu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneOppo_TrangChu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        tabbedPane_TrangChu.add("OPPO", scrollPaneOppo_TrangChu);
    }

    public void loadListOppo(List<JPanel> listPanelSanPham) {
        panelOPPO_TrangChu.removeAll();
        listPanelSanPham.forEach(i -> {
            i.setPreferredSize(new Dimension(204, 320));
            ((JPanel) i.getComponents()[1]).getComponents()[5].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    loadListOppo(listPanelSanPhamOppo.getListPanelSanPham());
                    loadListOppo(listPanelSanPhamOppo.getListPanelSanPham());
                }
            });
            ((JPanel) i.getComponents()[1]).getComponents()[4].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    loadListOppo(listPanelSanPhamOppo.getListPanelSanPham());
                    loadListOppo(listPanelSanPhamOppo.getListPanelSanPham());
                }
            });
            panelOPPO_TrangChu.add(i);
        });
        scrollPaneOppo_TrangChu.setViewportView(panelOPPO_TrangChu);
        scrollPaneOppo_TrangChu.repaint();
        danhSachOppo = listPanelSanPhamOppo.loadListDienThoai();
    }

    public void panelXiaomiTrangChu() {
        panelXIAOMI_TrangChu = new JPanel();
        panelXIAOMI_TrangChu.setBackground(new Color(255, 255, 255));
        panelXIAOMI_TrangChu.setLayout(new WrapLayout(WrapLayout.CENTER, 30, 30));

        scrollPaneXiaomi_TrangChu = new JScrollPane(panelXIAOMI_TrangChu);

        loadListXiaomi(listPanelSanPhamXiaomi.getListPanelSanPham());

        scrollPaneXiaomi_TrangChu.getVerticalScrollBar().setUnitIncrement(16);
        scrollPaneXiaomi_TrangChu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneXiaomi_TrangChu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        tabbedPane_TrangChu.add("XIAOMI", scrollPaneXiaomi_TrangChu);
    }

    public void loadListXiaomi(List<JPanel> listPanelSanPham) {
        panelXIAOMI_TrangChu.removeAll();
        listPanelSanPham.forEach(i -> {
            i.setPreferredSize(new Dimension(204, 320));
            ((JPanel) i.getComponents()[1]).getComponents()[5].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    loadListXiaomi(listPanelSanPhamXiaomi.getListPanelSanPham());
                    loadListXiaomi(listPanelSanPhamXiaomi.getListPanelSanPham());
                }
            });
            ((JPanel) i.getComponents()[1]).getComponents()[4].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    loadListXiaomi(listPanelSanPhamXiaomi.getListPanelSanPham());
                    loadListXiaomi(listPanelSanPhamXiaomi.getListPanelSanPham());
                }
            });
            panelXIAOMI_TrangChu.add(i);
        });
        scrollPaneXiaomi_TrangChu.setViewportView(panelXIAOMI_TrangChu);
        scrollPaneXiaomi_TrangChu.repaint();
        danhSachXiaomi = listPanelSanPhamXiaomi.loadListDienThoai();
    }

    public void panelRealmeTrangChu() {
        panelREALME_TrangChu = new JPanel();
        panelREALME_TrangChu.setBackground(new Color(255, 255, 255));
        panelREALME_TrangChu.setLayout(new WrapLayout(WrapLayout.CENTER, 30, 30));

        scrollPaneRealme_TrangChu = new JScrollPane(panelREALME_TrangChu);

        loadListRealme(listPanelSanPhamRealme.getListPanelSanPham());

        scrollPaneRealme_TrangChu.getVerticalScrollBar().setUnitIncrement(16);
        scrollPaneRealme_TrangChu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneRealme_TrangChu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        tabbedPane_TrangChu.add("REALME", scrollPaneRealme_TrangChu);
    }

    public void loadListRealme(List<JPanel> listPanelSanPham) {
        panelREALME_TrangChu.removeAll();
        listPanelSanPham.forEach(i -> {
            i.setPreferredSize(new Dimension(204, 320));
            ((JPanel) i.getComponents()[1]).getComponents()[5].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    loadListRealme(listPanelSanPhamRealme.getListPanelSanPham());
                    loadListRealme(listPanelSanPhamRealme.getListPanelSanPham());
                }
            });
            ((JPanel) i.getComponents()[1]).getComponents()[4].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    loadListRealme(listPanelSanPhamRealme.getListPanelSanPham());
                    loadListRealme(listPanelSanPhamRealme.getListPanelSanPham());
                }
            });
            panelREALME_TrangChu.add(i);
        });
        scrollPaneRealme_TrangChu.setViewportView(panelREALME_TrangChu);
        scrollPaneRealme_TrangChu.repaint();
        danhSachRealme = listPanelSanPhamRealme.loadListDienThoai();
    }

    public void panelVivoTrangChu() {
        panelVIVO_TrangChu = new JPanel();
        panelVIVO_TrangChu.setBackground(new Color(255, 255, 255));
        panelVIVO_TrangChu.setLayout(new WrapLayout(WrapLayout.CENTER, 30, 30));

        scrollPaneVivo_TrangChu = new JScrollPane(panelVIVO_TrangChu);

        loadListVivo(listPanelSanPhamVivo.getListPanelSanPham());

        scrollPaneVivo_TrangChu.getVerticalScrollBar().setUnitIncrement(16);
        scrollPaneVivo_TrangChu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneVivo_TrangChu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        tabbedPane_TrangChu.add("VIVO", scrollPaneVivo_TrangChu);
    }

    public void loadListVivo(List<JPanel> listPanelSanPham) {
        panelVIVO_TrangChu.removeAll();
        listPanelSanPham.forEach(i -> {
            i.setPreferredSize(new Dimension(204, 320));
            ((JPanel) i.getComponents()[1]).getComponents()[5].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    loadListVivo(listPanelSanPhamVivo.getListPanelSanPham());
                    loadListVivo(listPanelSanPhamVivo.getListPanelSanPham());
                }
            });
            ((JPanel) i.getComponents()[1]).getComponents()[4].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    loadListVivo(listPanelSanPhamVivo.getListPanelSanPham());
                    loadListVivo(listPanelSanPhamVivo.getListPanelSanPham());
                }
            });
            panelVIVO_TrangChu.add(i);
        });
        scrollPaneVivo_TrangChu.setViewportView(panelVIVO_TrangChu);
        scrollPaneVivo_TrangChu.repaint();
        danhSachVivo = listPanelSanPhamVivo.loadListDienThoai();
    }
}
