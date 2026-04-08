package com.example.aula1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
// tranforma o objeto em uma tabela
@Table(name = "usuarios")
//nomeia a tabela, caso não seja nomeado o padrao será o nome da classe
public class UsuarioModel {
    //id
    @Id 
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    //id

    //nome
    @NotBlank(message = "O campo nome precisa ser preenchido")
    @Size(min = 3 , max = 100, message = "O campo nome precisa ter entre 3 e 100 caracteres") 
    @Column(name = "nome_usuario")
    private String nome;
    //nome

    //idade
    @Min(value = 1, message = "O campo idade precisa ser maior ou igual a 1")
    @Column(name = "idade_usuario")
    private int idade;
    //idade


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

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

}
