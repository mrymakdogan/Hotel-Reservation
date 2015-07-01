/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelpackage;

import java.util.Date;

/**
 *
 * @author ALTINDAL
 */
public class RezervasyonModel {
    private Integer misafirNo;
    private Integer tcno;
    private Date girisTarih;
    private Date cikisTarih;
    private Integer odaId;
    private Integer ucret;

    /**
     * @return the misafirNo
     */
    public Integer getMisafirNo() {
        return misafirNo;
    }


    public void setMisafirNo(Integer misafirNo) {
        this.misafirNo = misafirNo;
    }

    /**
     * @return the tcno
     */
    public Integer getTcno() {
        return tcno;
    }

    /**
     * @param tcno the tcno to set
     */
    public void setTcno(Integer tcno) {
        this.tcno = tcno;
    }

    /**
     * @return the girisTarih
     */
    public Date getGirisTarih() {
        return girisTarih;
    }

    /**
     * @param girisTarih the girisTarih to set
     */
    public void setGirisTarih(Date girisTarih) {
        this.girisTarih = girisTarih;
    }

    /**
     * @return the cikisTarih
     */
    public Date getCikisTarih() {
        return cikisTarih;
    }

    /**
     * @param cikisTarih the cikisTarih to set
     */
    public void setCikisTarih(Date cikisTarih) {
        this.cikisTarih = cikisTarih;
    }

    /**
     * @return the odaId
     */
    public Integer getOdaId() {
        return odaId;
    }

    /**
     * @param odaId the odaId to set
     */
    public void setOdaId(Integer odaId) {
        this.odaId = odaId;
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
    
}
