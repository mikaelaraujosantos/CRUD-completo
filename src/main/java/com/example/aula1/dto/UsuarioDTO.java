package com.example.aula1.dto;

public class UsuarioDTO {
    
    private Long id;
    private String nome;
    private int idade;

    public UsuarioDTO(){

    }


    public UsuarioDTO(Long id, String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
        this.id = id;
    }

    

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
