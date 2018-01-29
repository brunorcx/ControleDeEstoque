package com.prosoft.controledeestoque;

/**
 * Created by gabri on 12/01/2018.
 */

public class Produto {
    private String nome;
    private String descricao;
    private String quantidade;
    private String valor;
    private String codigo;
    private String usuario;

    public void Produto () {} // TODO Fazer construtor

    //Geters
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public String getValor() {
        return valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    //Seters
    public void setNome(String pNome) {
        nome = pNome;
    }

    public void setDescricao(String pDescricao) {
        descricao = pDescricao;
    }

    public void setQuantidade(String pQuantidade) {
        quantidade = pQuantidade;
    }

    public void setValor(String pValor) {
        valor = pValor;
    }

    public void setCodigo(String pCodigo) {
        codigo = pCodigo;
    }

    public void setUsuario(String pUsuario) {
        usuario = pUsuario;
    }

}
