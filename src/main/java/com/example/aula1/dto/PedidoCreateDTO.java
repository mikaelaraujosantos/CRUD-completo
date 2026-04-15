package com.example.aula1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PedidoCreateDTO {
    



    @NotBlank(message = "O campo descricao precisa ser preenchido")
    @Schema(
        description = "Descricao do pedido",
        example = "Cerveja"
    )
    private String descricao;

    @NotNull(message = "O campo valor precisa ser preenchido")
    @Min(value = 1, message = "O campo valor precisa ser maior ou igual a 1")
    @Schema(
        description = "Valor do pedido",
        example = "10.00"
    )
    private Double valor;
    



    public PedidoCreateDTO() {}


    public PedidoCreateDTO(String descricao, Double valor) {
        this.descricao = descricao;
        this.valor = valor;
       
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }




}
