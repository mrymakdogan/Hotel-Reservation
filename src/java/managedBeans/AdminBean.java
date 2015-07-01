/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import connectiondb.Baglan;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelpackage.AdminAciklamaModel;

/**
 *
 * @author ALTINDAL
 */
public class AdminBean implements Serializable {

    public static String aciklama;
    public Integer id;
    public List<AdminAciklamaModel> list;

    /**
     * @return the aciklama
     */
    public String getAciklama() {
        return aciklama;
    }

    /**
     * @param aciklama the aciklama to set
     */
    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public AdminBean() {
    }

    Connection con;
    Baglan b = null;

    public void kaydet() {
        b = new Baglan();
        con = b.baglan();
        PreparedStatement ps;
        String sorgu = "Insert Into admin(aciklama) values (?)";

        try {
            ps = con.prepareStatement(sorgu);

            ps.setString(1, getAciklama());

            ps.execute();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "HATA VAR" + ex.getMessage());
        }
    }

    public void listele() {
        b = new Baglan();
        con = b.baglan();
        PreparedStatement ps;
        String sorgu = "Select * from admin ";
        List<AdminAciklamaModel> liste = new ArrayList<AdminAciklamaModel>();

        try {
            ps = con.prepareStatement(sorgu);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AdminAciklamaModel m = new AdminAciklamaModel();
                m.setId(rs.getInt("id"));
                m.setAciklama(rs.getString("aciklama"));
                liste.add(m);

            }
            setList(liste);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }
    public void kaldir(){
        try {
            String sorgu;
            b=new Baglan();
            con=b.baglan();
            PreparedStatement ps;
            
            sorgu="Delete from admin where id="+"'"+this.id+"'";
            
            ps=con.prepareStatement(sorgu);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    public String yayinla() {
        String sonuc = "";

        b = new Baglan();
        con = b.baglan();
        PreparedStatement ps;

        String sorgu = "Select * from admin where id=" + "'" + this.id + "'";
        try {
            ps = con.prepareStatement(sorgu);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AdminAciklamaModel m = new AdminAciklamaModel();
                m.setId(rs.getInt("id"));

                if (m.getId() == this.id) {
                     aciklama= rs.getString("aciklama");
                     sonuc=this.aciklama;
                     break;
                }

            }

           
            JOptionPane.showMessageDialog(null, "sonuc" + sonuc);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Hata Var" + ex.getMessage());
        }
        return sonuc;
    }

    
    
    

    public List<AdminAciklamaModel> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<AdminAciklamaModel> list) {
        this.list = list;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

}
