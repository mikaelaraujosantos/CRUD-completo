package com.example.aula1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.aula1.dto.ApiResponse;
import com.example.aula1.dto.PedidoCreateDTO;
import com.example.aula1.dto.PedidoDTO;
import com.example.aula1.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@Tag(
    name = "Pedidos",
    description = "Gerenciamento de pedidos"
)

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    // 🔥 POST - Criar pedido
    @Operation(
        summary = "Criar pedido",
        description = "Cria um novo pedido para um usuario"
    )
    @PostMapping("/dto/{usuarioId}")
    public ResponseEntity<ApiResponse<PedidoDTO>>
            criarPedidoDTO(

            @PathVariable Long usuarioId,
            @Valid @RequestBody PedidoCreateDTO dto){

        PedidoDTO pedido =
                service.criarPedidoDTO(
                        usuarioId,
                        dto
                );

        ApiResponse<PedidoDTO> response =
                new ApiResponse<>(
                        "success",
                        "Pedido criado com sucesso",
                        pedido
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // 🔥 GET - Listar todos
    @Operation(
        summary = "Listar pedidos",
        description = "Lista todos os pedidos"
    )
    @GetMapping("/dto")
    public ResponseEntity<ApiResponse<List<PedidoDTO>>>
            listarPedidosDTO(){

        List<PedidoDTO> pedidos =
                service.listarPedidosDTO();

        ApiResponse<List<PedidoDTO>> response =
                new ApiResponse<>(
                        "success",
                        "Pedidos listados com sucesso",
                        pedidos
                );

        return ResponseEntity.ok(response);
    }

    // 🔥 GET - Por usuário
    @Operation(
        summary = "Listar pedidos por usuario",
        description = "Lista pedidos de um usuario especifico"
    )
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<ApiResponse<List<PedidoDTO>>>
            listarPedidosPorUsuario(
            @PathVariable Long usuarioId){

        List<PedidoDTO> pedidos =
                service.listarPedidosPorUsuario(
                        usuarioId
                );

        ApiResponse<List<PedidoDTO>> response =
                new ApiResponse<>(
                        "success",
                        "Pedidos do usuario listados",
                        pedidos
                );

        return ResponseEntity.ok(response);
    }

    // 🔥 PUT
    @Operation(
        summary = "Atualizar pedido",
        description = "Atualiza completamente um pedido"
    )
    @PutMapping("/dto/{id}")
    public ResponseEntity<ApiResponse<PedidoDTO>>
            atualizarPedidoDTO(

            @PathVariable Long id,
            @Valid @RequestBody PedidoCreateDTO dto){

        PedidoDTO pedido =
                service.atualizarPedidoDTO(
                        id,
                        dto
                );

        ApiResponse<PedidoDTO> response =
                new ApiResponse<>(
                        "success",
                        "Pedido atualizado com sucesso",
                        pedido
                );

        return ResponseEntity.ok(response);
    }

    // 🔥 PATCH
    @Operation(
        summary = "Atualizar pedido parcialmente",
        description = "Atualiza apenas campos informados"
    )
    @PatchMapping("/dto/{id}")
    public ResponseEntity<ApiResponse<PedidoDTO>>
            atualizarParcialPedido(

            @PathVariable Long id,
            @RequestBody PedidoCreateDTO dto){

        PedidoDTO pedido =
                service.atualizarParcialPedido(
                        id,
                        dto
                );

        ApiResponse<PedidoDTO> response =
                new ApiResponse<>(
                        "success",
                        "Pedido atualizado parcialmente",
                        pedido
                );

        return ResponseEntity.ok(response);
    }

    // 🔥 DELETE
    @Operation(
        summary = "Remover pedido",
        description = "Remove um pedido pelo ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
            removerPedido(
            @PathVariable Long id){

        service.removerPedido(id);

        return ResponseEntity
                .noContent()
                .build();
    }

}