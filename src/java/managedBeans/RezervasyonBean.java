package managedBeans;

import connectiondb.Baglan;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;
import modelpackage.OdaModel;
import modelpackage.RezervasyonModel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

public class RezervasyonBean implements Serializable {

    private Date date1;
    private Date date2;
    private String durum;
    private Integer ucret;
    private List<Integer> bosoda;
    private RezervasyonModel rezerv;

    Connection c;
    Baglan b;

    public RezervasyonBean() {
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }

    public List<OdaModel> getBosOda() {

        b = new Baglan();
        c = b.baglan();
        PreparedStatement ps;
        String sorgu = "SELECT *\n"
                + "FROM odatip  \n"
                + "WHERE odadurum=0 \n"
                + "ORDER BY odaNo asc";

        List<OdaModel> list = new ArrayList<OdaModel>();

        try {
            ps = c.prepareStatement(sorgu);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OdaModel m = new OdaModel();
                m.setOdaNo(rs.getInt("odaNo"));
                list.add(m);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

        return list;
    }

    public void rezervasyonYap() {
        RezervasyonModel rezerv = new RezervasyonModel();
        OdaModel oda = new OdaModel();
        MusteriBean m = new MusteriBean();

        try {
            b = new Baglan();
            c = b.baglan();

            String listSorgu = "Select * from odatip WHERE odano=" + bosoda.get(0);
            PreparedStatement ps1;
            ps1 = c.prepareStatement(listSorgu);
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                oda.setOdaTipId(rs.getInt("odatipid"));
                oda.setYatakSayisi(rs.getInt("yataksayisi"));
                oda.setOdaNo(rs.getInt("odano"));
                oda.setUcret(rs.getInt("ucret"));
                oda.setOdaTip(rs.getInt("odatipno"));
                oda.setOdaDurumu(rs.getInt("odadurum"));
            }

            String ekleSorgu = "Insert into musteri(tcno,giristarih,cikistarih,odano,ucret) values (?,?,?,?,?)";
            PreparedStatement ps;
            ps = c.prepareStatement(ekleSorgu);
            
            
                       
            
            String duyuruSorgu="Select * from odatip WHERE odano=" + bosoda.get(0);
            PreparedStatement ps2;
            ps2 = c.prepareStatement(duyuruSorgu);
            ResultSet rs1 = ps2.executeQuery();
            while (rs.next()) {
                oda.setOdaTipId(rs.getInt("odatipid"));
                oda.setYatakSayisi(rs.getInt("yataksayisi"));
                oda.setOdaNo(rs.getInt("odano"));
                oda.setUcret(rs.getInt("ucret"));
                oda.setOdaTip(rs.getInt("odatipno"));
                oda.setOdaDurumu(rs.getInt("odadurum"));
            }
            
            String dSorgu = "UPDATE odatip SET odadurum = 1 ";
            PreparedStatement ps3;
            ps3 = c.prepareStatement(dSorgu);
            
            /*String durumSorgu="UPDATE odatip SET odadurum = 1, odadurum=0 WHERE odano = "+oda.getOdaNo();
            PreparedStatement ps2;
            ps2 = c.prepareStatement(durumSorgu);*/
            
            
            /*oda.setOdaDurumu(Integer.parseInt(0+""));*/
            
                     
            java.sql.Date sqlDate1 = new java.sql.Date(getDate1().getTime());
            java.sql.Date sqlDate2 = new java.sql.Date(getDate2().getTime());

            JOptionPane.showMessageDialog(null, sqlDate1);
            JOptionPane.showMessageDialog(null, getDate2());
            ps.setString(1, m.stcno);
            ps.setDate(2, sqlDate1);
            ps.setDate(3, sqlDate2);
            ps.setInt(4, oda.getOdaNo());
            ps.setInt(5, oda.getUcret());

            ps.execute();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }
 
    
    
    /**
     * @return the rezerv
     */
    public RezervasyonModel getRezerv() {
        return rezerv;
    }

    /**
     * @param rezerv the rezerv to set
     */
    public void setRezerv(RezervasyonModel rezerv) {
        this.rezerv = rezerv;
    }

    /**
     * @return the durum
     */
    public String getDurum() {
        return durum;
    }

    /**
     * @param durum the durum to set
     */
    public void setDurum(String durum) {
        this.durum = durum;
    }

    /**
     * @return the ucret
     */
    public Integer getUcret() {
        return ucret;
    }

    /**
     * @param ucret the ucret to set
     */
    public void setUcret(Integer ucret) {
        this.ucret = ucret;
    }

    /**
     * @return the
     */
    public List<Integer> getBosoda() {
        return bosoda;
    }

    /**
     * @param bosoda the bosoda to set
     */
    public void setBosoda(List<Integer> bosoda) {
        this.bosoda = bosoda;
    }

    /**
     * @return the date1
     */
    public Date getDate1() {
        return date1;
    }

    /**
     * @param date1 the date1 to set
     */
    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    /**
     * @return the date2
     */
    public Date getDate2() {
        return date2;
    }

    /**
     * @param date2 the date2 to set
     */
    public void setDate2(Date date2) {
        this.date2 = date2;
    }

}
