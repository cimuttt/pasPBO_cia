/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.controller.controller_siswa;
import com.view.form_siswa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import koneksi.koneksi;

public class model_siswa implements controller_siswa {
String jk;

    @Override
    public void Simpan(form_siswa siswa) throws SQLException {
        if (siswa.rbLaki.isSelected()) {
            jk = "Laki - Laki";
        }else {
           jk = "Perempuan";
        }
        try {
            Connection con = koneksi.getcon();
            String sql = "Insert Into siswa Values(?, ?, ?, ?)";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, siswa.txtNis.getText());
            prepare.setString(2, siswa.txtNama.getText());
            prepare.setString(3, jk);
            prepare.setString(4, siswa.txtAlamat.getText());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
            prepare.close();
        } catch (Exception e) {
            System.out.println();
        } finally {
            Tampil(siswa);
        }
    }


    @Override
    public void Hapus(form_siswa siswa) throws SQLException {
        try {
            Connection con = koneksi.getcon();
            String sql = "DELETE FROM siswa WHERE NIS = ?";
            PreparedStatement prepare = con.prepareStatement(sql);
            prepare.setString(1, siswa.txtNis.getText());
            prepare.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Tampil(siswa);
        }
    }

    @Override
    public void Edit(form_siswa siswa) throws SQLException {
        if (siswa.rbLaki.isSelected()) {
        jk = "Laki - Laki";
    } else {
    jk = "Perempuan";
}
    try {
    Connection con = koneksi.getcon();
    String sql = "UPDATE siswa SET nama=?, jenis_kelamin=?, + alamat=? WHERE NIS=?";
    PreparedStatement prepare = con.prepareStatement(sql);
    prepare.setString(4, siswa.txtNis.getText());
    prepare.setString(1, siswa.txtNama.getText());
    prepare.setString(2, jk);
    prepare.setString(3, siswa.txtAlamat.getText());
    prepare.executeUpdate();
    JOptionPane.showMessageDialog(null, "Data berhasil diedit");
    prepare.close();
    } catch (Exception e) {
        System.out.println(e);
    } finally {
        Tampil(siswa);
    }
}

    @Override
    public void Tampil(form_siswa siswa) throws SQLException {
        siswa.tblmodel.getDataVector().removeAllElements();
        siswa.tblmodel.fireTableDataChanged();
        try {
            Connection con = koneksi.getcon();
            Statement stt = con.createStatement();
            String sql = "SELECT * FROM siswa ORDER BY NIS ASC";
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {
                Object[] ob = new Object[4];
                ob[0] = res.getString(1);
                ob[1] = res.getString(2);
                ob[2] = res.getString(3);
                ob[3] = res.getString(4);
                siswa.tblmodel.addRow(ob);
            }    
        }catch (Exception e) {
               System.out.println(e);   

    }
}
}