package com.apk.reminder.Model;

public class jadwal {
   private String namaDosen;
   private String tglPertemuan;
   private String waktuPertemuan;
   private String status;
   private String tglPenginputan;

    public String getNamaDosen() {
        return namaDosen;
    }

    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }

    public String getTglPertemuan() {
        return tglPertemuan;
    }

    public void setTglPertemuan(String tglPertemuan) {
        this.tglPertemuan = tglPertemuan;
    }

    public String getWaktuPertemuan() {
        return waktuPertemuan;
    }

    public void setWaktuPertemuan(String waktuPertemuan) {
        this.waktuPertemuan = waktuPertemuan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTglPenginputan() {
        return tglPenginputan;
    }

    public void setTglPenginputan(String tglPenginputan) {
        this.tglPenginputan = tglPenginputan;
    }
}
