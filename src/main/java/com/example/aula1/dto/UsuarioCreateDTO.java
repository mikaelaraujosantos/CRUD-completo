package com.example.aula1.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class UsuarioCreateDTO {
    
    
    @NotBlank(message = "O campo nome precisa ser preenchido")
    private String nome;

    @Min(value = 1, message = "O campo idade precisa ser maior ou igual a 1")
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

   
    


}




