/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import connectiondb.Baglan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelpackage.OdaModel;
import javafx.collections.ObservableList;
import modelpackage.MusteriModel;

/**
 *
 * @author ALTINDAL
 */
public class AdminOdaBean {

    /**
     * Creates a new instance of AdminOdaBean
     */
    public AdminOdaBean() {
    }
    
    private String durum;
    Connection c;
    Baglan b;
    
    public List<OdaModel> getOda(){
      
          List<OdaModel> liste=new ArrayList<OdaModel>();
        try {
         
            b=new Baglan();
            c=b.baglan();
            PreparedStatement ps;
            String sorgu="Select * from odatip";
            
        
            ps=c.prepareStatement(sorgu);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                OdaModel m=new OdaModel();
                if(rs.getInt("odadurum")==0){
                    setDurum("../images/gcircle.png");
                    m.setDurum(durum);
                    
                   
                }else{
                    setDurum("../images/rcircle.png"); 
                    m.setDurum(durum);
                }
                m.setOdaDurumu(rs.getInt("odadurum"));
                m.setOdaNo(rs.getInt("odano"));
                m.setOdaTip(rs.getInt("odatipno"));
                m.setYatakSayisi(rs.getInt("yataksayisi"));
                m.setUcret(rs.getInt("ucret"));
                
                liste.add(m);
                
                
            }
            
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        return liste;
    }
    
    
    
    
        

     
        
    public List<MusteriModel> musteriGetir(){
         List<MusteriModel> list=new ArrayList<MusteriModel>();
         
        try {
           b=new Baglan();
            c=b.baglan();
            PreparedStatement ps;
            String sorgu="Select * from odalar";        // "WHERE odadurum=0 \n"+ "ORDER BY odaNo asc";
            
            ps=c.prepareStatement(sorgu);
            ResultSet rs=ps.executeQuery();
            
            while (rs.next()) {
                MusteriModel m= new MusteriModel();
                m.setIsim(rs.getString("isim"));
                m.setSoyad(rs.getString("soyad"));
                m.setOdaNo(rs.getInt("odano"));
              
                
                list.add(m);
            }
            rs.close();
            ps.close();
 
        } catch (Exception e) {
            System.out.println(e.getMessage());
         
        }
         return list;
    }

    
    public String getDurum() {
        return durum;
    }

   
    public void setDurum(String durum) {
        this.durum = durum;
    }

  
    
}
