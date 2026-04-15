package com.example.aula1.dto;

import java.util.List;

public class UsuarioComPedidosDTO {

    private Long id;
    private String nome;
    private Integer idade;
    private List<PedidoDTO> pedidos;

    public UsuarioComPedidosDTO(
            Long id,
            String nome,
            Integer idade,
            List<PedidoDTO> pedidos) {

        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.pedidos = pedidos;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public List<PedidoDTO> getPedidos() {
        return pedidos;
    }

}