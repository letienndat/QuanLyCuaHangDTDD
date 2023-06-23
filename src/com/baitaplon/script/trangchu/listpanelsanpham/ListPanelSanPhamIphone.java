package com.baitaplon.script.trangchu.listpanelsanpham;

import com.baitaplon.script.danhsachdienthoai.DanhSachIphone;
import com.baitaplon.script.dienthoai.DienThoai;
import com.baitaplon.script.dienthoai.ThongSoDienThoai;
import com.baitaplon.script.trangchu.SuaSanPham;
import com.baitaplon.script.trangchu.XemSanPham;
import com.baitaplon.script.trangchu.XoaSanPham;
import com.baitaplon.script.trangchu.panelsanpham.PanelSanPham;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ListPanelSanPhamIphone {
    private Connection connection;
    private PreparedStatement statement;
    private List<JPanel> listPanelSanPham;
    private DanhSachIphone danhSachIphone;

    public ListPanelSanPhamIphone(Connection connection) {
        listPanelSanPham = new ArrayList<>();
        danhSachIphone = new DanhSachIphone();
        this.connection = connection;
        loadListDienThoai();
    }

    public ListPanelSanPhamIphone(List<JPanel> listPanelSanPham) {
        this.listPanelSanPham = listPanelSanPham;
    }

    public List<JPanel> getListPanelSanPham() {
        return listPanelSanPham;
    }

    public void setListPanelSanPham(List<JPanel> listPanelSanPham) {
        this.listPanelSanPham = listPanelSanPham;
    }

    public DanhSachIphone loadListDienThoai() {
        danhSachIphone.getListIphone().clear();
        listPanelSanPham.clear();
        try {
            String sql = "SELECT * FROM DienThoaiIphone";
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JLabel label = new JLabel("");
                label.setIcon(new ImageIcon(new ImageIcon(ImageIO.read(resultSet.getBlob("AnhThietBi").getBinaryStream())).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
                String maThietBiTemp = resultSet.getString("MaThietBi");
                String tenThietBiTemp = resultSet.getString("TenThietBi");
                Integer giaThietBiTemp = resultSet.getInt("GiaThietBi");
                String mauThietBiTemp = resultSet.getString("MauThietBi");
                Integer dungLuongBoNhoTemp = resultSet.getInt("DungLuongBoNho");
                String loaiManHinhTemp = resultSet.getString("LoaiManHinh");
                Integer doPhanGiaiDaiTemp = resultSet.getInt("DoPhanGiaiDai");
                Integer doPhanGiaiRongTemp = resultSet.getInt("DoPhanGiaiRong");
                Double manHinhTemp = resultSet.getDouble("ManHinh");
                Integer cameraTruocTemp = resultSet.getInt("CameraTruoc");
                Integer cameraSauTemp = resultSet.getInt("CameraSau");
                String heDieuHanhTemp = resultSet.getString("HeDieuHanh");
                String cpuTemp = resultSet.getString("CPU");
                Integer ramTemp = resultSet.getInt("RAM");
                Integer dungLuongPinTemp = resultSet.getInt("DungLuongPin");
                Integer thoiDiemRaMatTemp = resultSet.getInt("ThoiDiemRaMat");
                AtomicInteger luotXemTemp = new AtomicInteger(resultSet.getInt("LuotXem"));
                DienThoai dienThoaiTemp = new DienThoai(maThietBiTemp, tenThietBiTemp, giaThietBiTemp, mauThietBiTemp, dungLuongBoNhoTemp, new ThongSoDienThoai(loaiManHinhTemp, doPhanGiaiDaiTemp, doPhanGiaiRongTemp, manHinhTemp, cameraTruocTemp, cameraSauTemp, heDieuHanhTemp, cpuTemp, ramTemp, dungLuongPinTemp, thoiDiemRaMatTemp, "Iphone"), label, luotXemTemp.get());
                danhSachIphone.getListIphone().add(dienThoaiTemp);
                PanelSanPham panelSanPham = new PanelSanPham(label, tenThietBiTemp, mauThietBiTemp, String.valueOf(luotXemTemp), String.valueOf(giaThietBiTemp));
                panelSanPham.getLabelXoa().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new XoaSanPham(connection, maThietBiTemp, "Iphone").setVisible(true);
                    }
                });
                panelSanPham.getLabelChinhSua().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new SuaSanPham(connection, dienThoaiTemp, "Iphone").setVisible(true);
                    }
                });
                panelSanPham.getLabelAnh().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            luotXemTemp.addAndGet(1);
                            String sql = "UPDATE DienThoaiIphone SET LuotXem = " + luotXemTemp + " WHERE MaThietBi = " + "'" + maThietBiTemp + "'";
                            dienThoaiTemp.setLuotXem(luotXemTemp.get());
                            panelSanPham.getLabelLuotXem().setText("Lượt xem: " + luotXemTemp);
                            statement = connection.prepareStatement(sql);
                            statement.executeUpdate();
                        } catch (SQLException exception) {
                            exception.printStackTrace();
                        }
                        new XemSanPham(dienThoaiTemp).setVisible(true);
                    }
                });
                listPanelSanPham.add(panelSanPham.getPanelSanPham());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            return danhSachIphone;
        }
    }

    public DanhSachIphone getDanhSachIphone() {
        return danhSachIphone;
    }

    public void setDanhSachIphone(DanhSachIphone danhSachIphone) {
        this.danhSachIphone = danhSachIphone;
    }
}
