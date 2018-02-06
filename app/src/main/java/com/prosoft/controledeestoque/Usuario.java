package com.prosoft.controledeestoque;



public class Usuario {

  private String id;
  private String nome;
  private String email;
  private String telefone;
  private Loja loja;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public Loja getLoja() {
    return loja;
  }

  public void setLoja(Loja loja) {
    this.loja = loja;
  }
}
