package com.morganoliveira.agrohelp.Model;

/**
 * Created by Morgan Oliveira on 22/12/2017.
 */

public class TermoTecnico {

    private int ID;
    private String Nome;
    private String Descricao;
    private String Fonte;
    private byte [] image;
    private String decricaoRDZ;

    public TermoTecnico() {

    }

    public int getID() {
        return ID;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getFonte() {
        return Fonte;
    }

    public void setFonte(String fonte) {
        Fonte = fonte;
    }

    public String getDecricaoRDZ() {
        return decricaoRDZ;
    }

    public void setDecricaoRDZ(String decricaoRDZ) {
        this.decricaoRDZ = decricaoRDZ;
    }

    @Override
    public String toString() {
        return Nome;

    }
}
