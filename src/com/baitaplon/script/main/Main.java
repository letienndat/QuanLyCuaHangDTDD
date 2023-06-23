package com.baitaplon.script.main;

import com.baitaplon.script.dangnhap_dangky.DangNhap_DangKy;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new DangNhap_DangKy("").setVisible(true);
        });
    }
}
