package com.baitaplon.script.trangchu.timkiem;

import com.baitaplon.script.danhsachdienthoai.*;
import com.baitaplon.script.dienthoai.DienThoai;
import com.baitaplon.script.trangchu.SuaSanPham;
import com.baitaplon.script.trangchu.XemSanPham;
import com.baitaplon.script.trangchu.XoaSanPham;
import com.baitaplon.script.trangchu.listpanelsanpham.*;
import com.baitaplon.script.trangchu.panelsanpham.PanelSanPham;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimKiem {
    private DanhSachIphone danhSachIphone;
    private DanhSachSamSung danhSachSamSung;
    private DanhSachXiaomi danhSachXiaomi;
    private DanhSachRealme danhSachRealme;
    private DanhSachOppo danhSachOppo;
    private DanhSachVivo danhSachVivo;
    private String key;
    private List<DienThoai> dienThoaiList;
    private Connection connection;
    private PreparedStatement statement;
    private List<JPanel> panelResTimKiem;

    public TimKiem() {
        panelResTimKiem = new ArrayList<>();
    }

    public TimKiem(String key, Connection connection) {
        this();
        this.key = formKey(key);
        this.connection = connection;
    }

    public String formKey(String key) {
        String res = key.toLowerCase().trim();
        String ress = "";
        for (String s : res.split("\\s+")) {
            ress += (s + " ");
        }
        return ress.substring(0, ress.length() - 1);
    }

    public List<JPanel> getDienThoaiListTimKiem() {
        panelResTimKiem.clear();
        if (getDienThoaiListIphoneTimKiem() != null) {
            getDienThoaiListIphoneTimKiem().getListIphone().forEach(i -> {
                PanelSanPham panelSanPham = new PanelSanPham(i.getAnhDienThoai(), i.getTenDienThoai(), i.getMauDienThoai(), String.valueOf(i.getLuotXem()), String.valueOf(i.getGiaDienThoai()));
                panelSanPham.getLabelXoa().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new XoaSanPham(connection, i.getMaDienThoai(), "Iphone").setVisible(true);
                    }
                });
                panelSanPham.getLabelChinhSua().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new SuaSanPham(connection, i, "Iphone").setVisible(true);
                    }
                });
                panelSanPham.getLabelAnh().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            String sql = "UPDATE DienThoaiIphone SET LuotXem = " + (i.getLuotXem() + 1) + " WHERE MaThietBi = " + "'" + i.getMaDienThoai() + "'";
                            i.setLuotXem(i.getLuotXem() + 1);
                            panelSanPham.getLabelLuotXem().setText("Lượt xem: " + i.getLuotXem());
                            statement = connection.prepareStatement(sql);
                            statement.executeUpdate();
                        } catch (SQLException exception) {
                            exception.printStackTrace();
                        }
                        new XemSanPham(i).setVisible(true);
                    }
                });
                panelResTimKiem.add(panelSanPham.getPanelSanPham());
            });
        }
        if (getDienThoaiListXiaomiTimKiem() != null) {
            getDienThoaiListXiaomiTimKiem().getListXiaomi().forEach(i -> {
                PanelSanPham panelSanPham = new PanelSanPham(i.getAnhDienThoai(), i.getTenDienThoai(), i.getMauDienThoai(), String.valueOf(i.getLuotXem()), String.valueOf(i.getGiaDienThoai()));
                panelSanPham.getLabelXoa().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new XoaSanPham(connection, i.getMaDienThoai(), "Xiaomi").setVisible(true);
                    }
                });
                panelSanPham.getLabelChinhSua().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new SuaSanPham(connection, i, "Xiaomi").setVisible(true);
                    }
                });
                panelSanPham.getLabelAnh().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            String sql = "UPDATE DienThoaiXiaomi SET LuotXem = " + (i.getLuotXem() + 1) + " WHERE MaThietBi = " + "'" + i.getMaDienThoai() + "'";
                            i.setLuotXem(i.getLuotXem() + 1);
                            panelSanPham.getLabelLuotXem().setText("Lượt xem: " + i.getLuotXem());
                            statement = connection.prepareStatement(sql);
                            statement.executeUpdate();
                        } catch (SQLException exception) {
                            exception.printStackTrace();
                        }
                        new XemSanPham(i).setVisible(true);
                    }
                });
                panelResTimKiem.add(panelSanPham.getPanelSanPham());
            });
        }
        if (getDienThoaiListSamSungTimKiem() != null) {
            getDienThoaiListSamSungTimKiem().getListSamSung().forEach(i -> {
                PanelSanPham panelSanPham = new PanelSanPham(i.getAnhDienThoai(), i.getTenDienThoai(), i.getMauDienThoai(), String.valueOf(i.getLuotXem()), String.valueOf(i.getGiaDienThoai()));
                panelSanPham.getLabelXoa().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new XoaSanPham(connection, i.getMaDienThoai(), "SamSung").setVisible(true);
                    }
                });
                panelSanPham.getLabelChinhSua().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new SuaSanPham(connection, i, "SamSung").setVisible(true);
                    }
                });
                panelSanPham.getLabelAnh().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            String sql = "UPDATE DienThoaiSamSung SET LuotXem = " + (i.getLuotXem() + 1) + " WHERE MaThietBi = " + "'" + i.getMaDienThoai() + "'";
                            i.setLuotXem(i.getLuotXem() + 1);
                            panelSanPham.getLabelLuotXem().setText("Lượt xem: " + i.getLuotXem());
                            statement = connection.prepareStatement(sql);
                            statement.executeUpdate();
                        } catch (SQLException exception) {
                            exception.printStackTrace();
                        }
                        new XemSanPham(i).setVisible(true);
                    }
                });
                panelResTimKiem.add(panelSanPham.getPanelSanPham());
            });
        }
        if (getDienThoaiListRealmeTimKiem() != null) {
            getDienThoaiListRealmeTimKiem().getListRealme().forEach(i -> {
                PanelSanPham panelSanPham = new PanelSanPham(i.getAnhDienThoai(), i.getTenDienThoai(), i.getMauDienThoai(), String.valueOf(i.getLuotXem()), String.valueOf(i.getGiaDienThoai()));
                panelSanPham.getLabelXoa().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new XoaSanPham(connection, i.getMaDienThoai(), "Realme").setVisible(true);
                    }
                });
                panelSanPham.getLabelChinhSua().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new SuaSanPham(connection, i, "Realme").setVisible(true);
                    }
                });
                panelSanPham.getLabelAnh().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            String sql = "UPDATE DienThoaiRealme SET LuotXem = " + (i.getLuotXem() + 1) + " WHERE MaThietBi = " + "'" + i.getMaDienThoai() + "'";
                            i.setLuotXem(i.getLuotXem() + 1);
                            panelSanPham.getLabelLuotXem().setText("Lượt xem: " + i.getLuotXem());
                            statement = connection.prepareStatement(sql);
                            statement.executeUpdate();
                        } catch (SQLException exception) {
                            exception.printStackTrace();
                        }
                        new XemSanPham(i).setVisible(true);
                    }
                });
                panelResTimKiem.add(panelSanPham.getPanelSanPham());
            });
        }
        if (getDienThoaiListOppoTimKiem() != null) {
            getDienThoaiListOppoTimKiem().getListOppo().forEach(i -> {
                PanelSanPham panelSanPham = new PanelSanPham(i.getAnhDienThoai(), i.getTenDienThoai(), i.getMauDienThoai(), String.valueOf(i.getLuotXem()), String.valueOf(i.getGiaDienThoai()));
                panelSanPham.getLabelXoa().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new XoaSanPham(connection, i.getMaDienThoai(), "Oppo").setVisible(true);
                    }
                });
                panelSanPham.getLabelChinhSua().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new SuaSanPham(connection, i, "Oppo").setVisible(true);
                    }
                });
                panelSanPham.getLabelAnh().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            String sql = "UPDATE DienThoaiOppo SET LuotXem = " + (i.getLuotXem() + 1) + " WHERE MaThietBi = " + "'" + i.getMaDienThoai() + "'";
                            i.setLuotXem(i.getLuotXem() + 1);
                            panelSanPham.getLabelLuotXem().setText("Lượt xem: " + i.getLuotXem());
                            statement = connection.prepareStatement(sql);
                            statement.executeUpdate();
                        } catch (SQLException exception) {
                            exception.printStackTrace();
                        }
                        new XemSanPham(i).setVisible(true);
                    }
                });
                panelResTimKiem.add(panelSanPham.getPanelSanPham());
            });
        }
        if (getDienThoaiListVivoTimKiem() != null) {
            getDienThoaiListVivoTimKiem().getListVivo().forEach(i -> {
                PanelSanPham panelSanPham = new PanelSanPham(i.getAnhDienThoai(), i.getTenDienThoai(), i.getMauDienThoai(), String.valueOf(i.getLuotXem()), String.valueOf(i.getGiaDienThoai()));
                panelSanPham.getLabelXoa().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new XoaSanPham(connection, i.getMaDienThoai(), "Vivo").setVisible(true);
                    }
                });
                panelSanPham.getLabelChinhSua().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new SuaSanPham(connection, i, "Vivo").setVisible(true);
                    }
                });
                panelSanPham.getLabelAnh().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            String sql = "UPDATE DienThoaiVivo SET LuotXem = " + (i.getLuotXem() + 1) + " WHERE MaThietBi = " + "'" + i.getMaDienThoai() + "'";
                            i.setLuotXem(i.getLuotXem() + 1);
                            panelSanPham.getLabelLuotXem().setText("Lượt xem: " + i.getLuotXem());
                            statement = connection.prepareStatement(sql);
                            statement.executeUpdate();
                        } catch (SQLException exception) {
                            exception.printStackTrace();
                        }
                        new XemSanPham(i).setVisible(true);
                    }
                });
                panelResTimKiem.add(panelSanPham.getPanelSanPham());
            });
        }
        return panelResTimKiem.size() == 0 ? null : panelResTimKiem;
    }

    public DanhSachIphone getDienThoaiListIphoneTimKiem() {
        ListPanelSanPhamIphone listPanelSanPhamIphone = new ListPanelSanPhamIphone(connection);
        DanhSachIphone danhSachIphoneTemp = new DanhSachIphone();
        danhSachIphone = listPanelSanPhamIphone.getDanhSachIphone();
        danhSachIphone.getListIphone().forEach(i -> {
            if (formKey(i.getMaDienThoai()).toLowerCase().contains(key) || i.getTenDienThoai().toLowerCase().contains(key)) {
                danhSachIphoneTemp.getListIphone().add(i);
            }
        });
        return danhSachIphoneTemp.getListIphone().size() == 0 ? null : danhSachIphoneTemp;
    }

    public DanhSachXiaomi getDienThoaiListXiaomiTimKiem() {
        ListPanelSanPhamXiaomi listPanelSanPhamXiaomi = new ListPanelSanPhamXiaomi(connection);
        DanhSachXiaomi danhSachXiaomiTemp = new DanhSachXiaomi();
        danhSachXiaomi = listPanelSanPhamXiaomi.getDanhSachXiaomi();
        danhSachXiaomi.getListXiaomi().forEach(i -> {
            if (formKey(i.getMaDienThoai()).toLowerCase().contains(key) || i.getTenDienThoai().toLowerCase().contains(key)) {
                danhSachXiaomiTemp.getListXiaomi().add(i);
            }
        });
        return danhSachXiaomiTemp.getListXiaomi().size() == 0 ? null : danhSachXiaomiTemp;
    }

    public DanhSachOppo getDienThoaiListOppoTimKiem() {
        ListPanelSanPhamOppo listPanelSanPhamOppo = new ListPanelSanPhamOppo(connection);
        DanhSachOppo danhSachOppoTemp = new DanhSachOppo();
        danhSachOppo = listPanelSanPhamOppo.getDanhSachOppo();
        danhSachOppo.getListOppo().forEach(i -> {
            if (formKey(i.getMaDienThoai()).toLowerCase().contains(key) || i.getTenDienThoai().toLowerCase().contains(key)) {
                danhSachOppoTemp.getListOppo().add(i);
            }
        });
        return danhSachOppoTemp.getListOppo().size() == 0 ? null : danhSachOppoTemp;
    }

    public DanhSachRealme getDienThoaiListRealmeTimKiem() {
        ListPanelSanPhamRealme listPanelSanPhamRealme = new ListPanelSanPhamRealme(connection);
        DanhSachRealme danhSachRealmeTemp = new DanhSachRealme();
        danhSachRealme = listPanelSanPhamRealme.getDanhSachRealme();
        danhSachRealme.getListRealme().forEach(i -> {
            if (formKey(i.getMaDienThoai()).toLowerCase().contains(key) || i.getTenDienThoai().toLowerCase().contains(key)) {
                danhSachRealmeTemp.getListRealme().add(i);
            }
        });
        return danhSachRealmeTemp.getListRealme().size() == 0 ? null : danhSachRealmeTemp;
    }

    public DanhSachSamSung getDienThoaiListSamSungTimKiem() {
        ListPanelSanPhamSamSung listPanelSanPhamSamSung = new ListPanelSanPhamSamSung(connection);
        DanhSachSamSung danhSachSamSungTemp = new DanhSachSamSung();
        danhSachSamSung = listPanelSanPhamSamSung.getDanhSachSamSung();
        danhSachSamSung.getListSamSung().forEach(i -> {
            if (formKey(i.getMaDienThoai()).toLowerCase().contains(key) || i.getTenDienThoai().toLowerCase().contains(key)) {
                danhSachSamSungTemp.getListSamSung().add(i);
            }
        });
        return danhSachSamSungTemp.getListSamSung().size() == 0 ? null : danhSachSamSungTemp;
    }

    public DanhSachVivo getDienThoaiListVivoTimKiem() {
        ListPanelSanPhamVivo listPanelSanPhamVivo = new ListPanelSanPhamVivo(connection);
        DanhSachVivo danhSachVivoTemp = new DanhSachVivo();
        danhSachVivo = listPanelSanPhamVivo.getDanhSachVivo();
        danhSachVivo.getListVivo().forEach(i -> {
            if (formKey(i.getMaDienThoai()).toLowerCase().contains(key) || i.getTenDienThoai().toLowerCase().contains(key)) {
                danhSachVivoTemp.getListVivo().add(i);
            }
        });
        return danhSachVivoTemp.getListVivo().size() == 0 ? null : danhSachVivoTemp;
    }
}
