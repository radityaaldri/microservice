package com.raditya.notification.model;

public class Order {
    private long id;
    private long produkId;
    private int jumlah;
    private double total;
    private String tanggal;
    private String email;

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public long getProdukId(){
        return produkId;
    }
    public void setProdukId(long produkId){
        this.produkId = produkId;
    }
    public long getJumlah(){
        return jumlah;
    }
    public void setJumlah(int jumlah){
        this.jumlah = jumlah;
    }
    public double getTotal(){
        return total;
    }
    public void setTotal(double total){
        this.total = total;
    }
     public String getTanggal(){
        return tanggal;
    }
    public void setTanggal(String tanggal){
        this.tanggal = tanggal;
    }
     public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    

}
