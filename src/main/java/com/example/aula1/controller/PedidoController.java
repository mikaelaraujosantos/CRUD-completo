package com.example.aula1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.aula1.dto.PedidoDTO;
import com.example.aula1.model.PedidoModel;
import com.example.aula1.service.PedidoService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
public class PedidoController {
    
    @Autowired
    private PedidoService service;


    @PostMapping("/pedido/{usuarioid}")
    public ResponseEntity<String> criarPedido(@PathVariable Long usuarioid,@RequestBody PedidoModel pedido) {

        String resposta = service.criarPedido(usuarioid, pedido);
        if (resposta.contains("sucesso")){
            return ResponseEntity.status(201).body(resposta);
        }
        return ResponseEntity.status(404).body(resposta);
    }
    
    @GetMapping("/pedidos")
    public ResponseEntity<List<PedidoModel>> listarPedidos() {
        return ResponseEntity.ok().body(service.listarPedidos());
    }
    
    @GetMapping("/pedido/usuario/{id}")
    public ResponseEntity<List<PedidoModel>> buscarPedidosPorUsuario(@PathVariable Long id) {
        List<PedidoModel> pedidos = service.listarPedidosPorUsuario(id);

        if (pedidos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(pedidos);
        }
    }

    @GetMapping("/pedidos/dto")
    public ResponseEntity<List<PedidoDTO>> listarPedidosDTO() {
        List<PedidoDTO> pedidos = service.listarPedidosDTO();
        return ResponseEntity.ok().body(pedidos);
    }
    
    


}
