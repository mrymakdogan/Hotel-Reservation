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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;
import modelpackage.MusteriModel;

/**
 *
 * @author ALTINDAL
 */
@ManagedBean
@SessionScoped
public class MusteriBean implements Serializable {

    public String email;
    public String sifre;
    public String tcno;
    public String isim;
    public String soyad;
    public int yetki;
    public static String stcno;

    public MusteriBean() {
    }

   
    Baglan b = null;
    MusteriModel k = null;

    public String giris() {
        b = new Baglan();

        Connection c = b.baglan();
        PreparedStatement ps;
        String sonuc = "";
        k = new MusteriModel();

        try {
            String sorgu = "select *from uyeler where sifre=" +"'"+ getSifre()+"'" + "and email="+"'" + getEmail() + "'"+"";
        
            ps = c.prepareStatement(sorgu);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                k.setIsim(rs.getString("isim"));
                k.setSoyad(rs.getString("soyad"));
                k.setSifre(rs.getString("sifre"));
                k.setEmail(rs.getString("email"));
                k.setYetki(rs.getInt("yetki"));
                k.setTcno(rs.getString("tcno"));
            }
            if (k.getEmail().equals(getEmail()) && k.getSifre().equals(getSifre()) && k.getYetki() == 1) {
                 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userAd",k.getIsim());
                 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userSoyad",k.getSoyad());
                 stcno=k.getTcno();
           
                             sonuc = "musteriRezervasyon";
               
                  JOptionPane.showMessageDialog(null,sonuc);
            } else if (k.getEmail().equals(getEmail()) && k.getSifre().equals(getSifre()) && k.getYetki() == 2) {
                   FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user",k.getIsim());
                sonuc = "adminAnasayfa";
                  JOptionPane.showMessageDialog(null,sonuc);
            }
           
            
            
        } catch (Exception ex) {
    
         sonuc="musteriAdminLogin";
           return sonuc;
        }
    
        return sonuc;
    }
    
   
    public void kaydet(){
        b=new Baglan();
        
       Connection c=b.baglan();
       PreparedStatement ps;
     
       k=new MusteriModel();
       
       String sorgu="Insert Into uyeler(tcno,email,isim,soyad,sifre,yetki) values (?,?,?,?,?)";
       
        try {
            ps=c.prepareStatement(sorgu);
            ps.setString(1,getTcno());
            JOptionPane.showMessageDialog(null,getTcno());
            ps.setString(2,getEmail());
            ps.setString(3,getIsim());
            ps.setString(4,getSoyad());
            ps.setString(5,getSifre());
            ps.setInt(6,1);
          
            
            ps.execute();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Hata var"+ex.getMessage());
        }
       
       
        
                
        
        
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the sifre
     */
    public String getSifre() {
        return sifre;
    }

    /**
     * @param sifre the sifre to set
     */
    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    /**
     * @return the tcno
     */
    public String getTcno() {
        return tcno;
    }

    /**
     * @param tcno the tcno to set
     */
    public void setTcno(String tcno) {
        this.tcno = tcno;
    }

    /**
     * @return the isim
     */
    public String getIsim() {
        return isim;
    }

    /**
     * @param isim the isim to set
     */
    public void setIsim(String isim) {
        this.isim = isim;
    }

    /**
     * @return the soyad
     */
    public String getSoyad() {
        return soyad;
    }

    /**
     * @param soyad the soyad to set
     */
    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    /**
     * @return the yetki
     */
    public int getYetki() {
        return yetki;
    }

    /**
     * @param yetki the yetki to set
     */
    public void setYetki(int yetki) {
        this.yetki = yetki;
    }

   
}
