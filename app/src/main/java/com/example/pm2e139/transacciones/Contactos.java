package com.example.pm2e139.transacciones;

import java.sql.Blob;

public class Contactos {
    private int codigo;
    private String codigoPais;
    private String nombreContacto;
    private int numeroContacto;
    private String notaContacto;
    private Blob image;

    public Blob getImage() {
        return image;
    }

    public Contactos() {
    }

    public Contactos(int codigo, String codigoPais, String nombreContacto, int numeroContacto, String notaContacto, Blob image) {
        this.codigo = codigo;
        this.codigoPais = codigoPais;
        this.nombreContacto = nombreContacto;
        this.numeroContacto = numeroContacto;
        this.notaContacto = notaContacto;
        this.image = image;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public int getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(int numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public String getNota() {
        return notaContacto;
    }

    public void setNota(String notaContacto) {
        this.notaContacto = notaContacto;
    }



    public void setImage(Blob image) {
        this.image = image;
    }


}

