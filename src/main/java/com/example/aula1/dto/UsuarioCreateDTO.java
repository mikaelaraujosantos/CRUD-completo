package com.example.aula1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class UsuarioCreateDTO {
    
    
    @NotBlank(message = "O campo nome precisa ser preenchido")
    @Schema(
        description = "Nome do usuario",
        example = "Joaquim"
    )
    private String nome;

    @Min(value = 1, message = "O campo idade precisa ser maior ou igual a 1")
    @Schema(
        description = "Idade do usuario",
        example = "20"
    )
    private Integer idade;

    public UsuarioCreateDTO() {
    }

    


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

   
    


}




