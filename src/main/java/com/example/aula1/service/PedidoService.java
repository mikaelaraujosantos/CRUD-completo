package com.example.aula1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aula1.dto.PedidoCreateDTO;
import com.example.aula1.dto.PedidoDTO;
import com.example.aula1.model.PedidoModel;
import com.example.aula1.model.UsuarioModel;
import com.example.aula1.repository.PedidoRepository;
import com.example.aula1.repository.UsuarioRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private UsuarioRepository usuRepository;

    // 🔥 POST - Criar pedido
    public PedidoDTO criarPedidoDTO(
            Long usuarioId,
            PedidoCreateDTO dto){

        UsuarioModel usuario =
                usuRepository.findById(usuarioId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuario nao encontrado"
                        )
                );

        PedidoModel pedido =
                new PedidoModel();

        pedido.setDescricao(dto.getDescricao());
        pedido.setValor(dto.getValor());
        pedido.setUsuario(usuario);

        PedidoModel pedidoSalvo =
                repository.save(pedido);

        return new PedidoDTO(
                pedidoSalvo.getId(),
                pedidoSalvo.getDescricao(),
                pedidoSalvo.getValor(),
                pedidoSalvo
                        .getUsuario()
                        .getNome()
        );
    }

    // 🔥 GET - Listar pedidos
    public List<PedidoDTO> listarPedidosDTO(){

        return repository.findAll()
                .stream()
                .map(pedido -> new PedidoDTO(
                        pedido.getId(),
                        pedido.getDescricao(),
                        pedido.getValor(),
                        pedido.getUsuario().getNome()
                ))
                .toList();
    }

    // 🔥 GET - Buscar pedidos por usuário
    public List<PedidoDTO>
            listarPedidosPorUsuario(
            Long usuarioId){

        return repository
                .findByUsuarioId(usuarioId)
                .stream()
                .map(pedido -> new PedidoDTO(
                        pedido.getId(),
                        pedido.getDescricao(),
                        pedido.getValor(),
                        pedido.getUsuario().getNome()
                ))
                .toList();
    }

    // 🔥 PUT - Atualizar completo
    public PedidoDTO atualizarPedidoDTO(
            Long id,
            PedidoCreateDTO dto){

        PedidoModel pedido =
                repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Pedido nao encontrado"
                        )
                );

        pedido.setDescricao(dto.getDescricao());
        pedido.setValor(dto.getValor());

        PedidoModel pedidoAtualizado =
                repository.save(pedido);

        return new PedidoDTO(
                pedidoAtualizado.getId(),
                pedidoAtualizado.getDescricao(),
                pedidoAtualizado.getValor(),
                pedidoAtualizado
                        .getUsuario()
                        .getNome()
        );
    }

    // 🔥 PATCH - Atualização parcial
    public PedidoDTO atualizarParcialPedido(
            Long id,
            PedidoCreateDTO dto){

        PedidoModel pedido =
                repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Pedido nao encontrado"
                        )
                );

        if (dto.getDescricao() != null
                && !dto.getDescricao().isBlank()) {

            pedido.setDescricao(
                    dto.getDescricao()
            );
        }

        if (dto.getValor() != null
                && dto.getValor() > 0) {

            pedido.setValor(
                    dto.getValor()
            );
        }

        PedidoModel pedidoAtualizado =
                repository.save(pedido);

        return new PedidoDTO(
                pedidoAtualizado.getId(),
                pedidoAtualizado.getDescricao(),
                pedidoAtualizado.getValor(),
                pedidoAtualizado
                        .getUsuario()
                        .getNome()
        );
    }

    // 🔥 DELETE
    public void removerPedido(Long id){

        PedidoModel pedido =
                repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Pedido nao encontrado"
                        )
                );

        repository.delete(pedido);
    }

}