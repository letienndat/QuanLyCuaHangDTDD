package com.baitaplon.script.trangchu.listpanelsanpham;

import com.baitaplon.script.danhsachdienthoai.DanhSachSamSung;
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

public class ListPanelSanPhamSamSung {
    private Connection connection;
    private PreparedStatement statement;
    private List<JPanel> listPanelSanPham;
    private DanhSachSamSung danhSachSamSung;

    public ListPanelSanPhamSamSung(Connection connection) {
        listPanelSanPham = new ArrayList<>();
        danhSachSamSung = new DanhSachSamSung();
        this.connection = connection;
        loadListDienThoai();
    }

    public ListPanelSanPhamSamSung(List<JPanel> listPanelSanPham) {
        this.listPanelSanPham = listPanelSanPham;
    }

    public List<JPanel> getListPanelSanPham() {
        return listPanelSanPham;
    }

    public void setListPanelSanPham(List<JPanel> listPanelSanPham) {
        this.listPanelSanPham = listPanelSanPham;
    }

    public DanhSachSamSung loadListDienThoai() {
        danhSachSamSung.getListSamSung().clear();
        listPanelSanPham.clear();
        try {
            String sql = "SELECT * FROM DienThoaiSamSung";
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
                DienThoai dienThoaiTemp = new DienThoai(maThietBiTemp, tenThietBiTemp, giaThietBiTemp, mauThietBiTemp, dungLuongBoNhoTemp, new ThongSoDienThoai(loaiManHinhTemp, doPhanGiaiDaiTemp, doPhanGiaiRongTemp, manHinhTemp, cameraTruocTemp, cameraSauTemp, heDieuHanhTemp, cpuTemp, ramTemp, dungLuongPinTemp, thoiDiemRaMatTemp, "SamSung"), label, luotXemTemp.get());
                danhSachSamSung.getListSamSung().add(dienThoaiTemp);
                PanelSanPham panelSanPham = new PanelSanPham(label, tenThietBiTemp, mauThietBiTemp, String.valueOf(luotXemTemp), String.valueOf(giaThietBiTemp));
                panelSanPham.getLabelXoa().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new XoaSanPham(connection, maThietBiTemp, "SamSung").setVisible(true);
                    }
                });
                panelSanPham.getLabelChinhSua().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new SuaSanPham(connection, dienThoaiTemp, "SamSung").setVisible(true);
                    }
                });
                panelSanPham.getLabelAnh().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            luotXemTemp.addAndGet(1);
                            String sql = "UPDATE DienThoaiSamSung SET LuotXem = " + luotXemTemp + " WHERE MaThietBi = " + "'" + maThietBiTemp + "'";
                            dienThoaiTemp.setLuotXem(luotXemTemp.get());
                            panelSanPham.getLabelLuotXem().setText("Lượt xem: " + luotXemTemp);                            statement = connection.prepareStatement(sql);
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
            return danhSachSamSung;
        }
    }

    public DanhSachSamSung getDanhSachSamSung() {
        return danhSachSamSung;
    }

    public void setDanhSachSamSung(DanhSachSamSung danhSachSamSung) {
        this.danhSachSamSung = danhSachSamSung;
    }
}
