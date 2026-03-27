package com.example.aula1;

public class UsuarioModel {
    
    private long id;
    private String nome;
    private int idade;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setId(int id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

}
