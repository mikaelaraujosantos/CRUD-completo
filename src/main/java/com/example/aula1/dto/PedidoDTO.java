package com.example.aula1.dto;

public class PedidoDTO {
    
    private String descricao;
    private double valor;
    private String usuarionome;



    public PedidoDTO(){
    }

    public PedidoDTO(String descricao, double valor, String usuarionome) {
        this.descricao = descricao;
        this.valor = valor;
        this.usuarionome = usuarionome;
    }


    

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public String getUsuarionome() {
        return usuarionome;
    }
    public void setUsuarionome(String usuarionome) {
        this.usuarionome = usuarionome;
    }
}
