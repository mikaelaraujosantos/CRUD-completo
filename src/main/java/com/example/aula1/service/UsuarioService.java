package com.example.aula1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.aula1.dto.PedidoDTO;
import com.example.aula1.dto.UsuarioComPedidosDTO;
import com.example.aula1.dto.UsuarioCreateDTO;
import com.example.aula1.dto.UsuarioDTO;
import com.example.aula1.model.UsuarioModel;
import com.example.aula1.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // POST - Criar usuário
    public UsuarioDTO criarUsuarioDTO(
            UsuarioCreateDTO dto){

        UsuarioModel usuario =
                new UsuarioModel();

        usuario.setNome(dto.getNome());
        usuario.setIdade(dto.getIdade());

        UsuarioModel usuarioSalvo =
                repository.save(usuario);

        return new UsuarioDTO(
                usuarioSalvo.getId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getIdade()
        );
    }

    // GET - Listar com paginação
    public Page<UsuarioDTO> listarUsuariosDTO(
            Pageable pageable){

        Page<UsuarioModel> pagina =
                repository.findAll(pageable);

        return pagina.map(usuario ->
                new UsuarioDTO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getIdade()
                )
        );
    }

    // GET - Buscar por nome com paginação
    public Page<UsuarioDTO> buscarNomeContemDTO(
            String nome,
            Pageable pageable){

        Page<UsuarioModel> pagina =
                repository.buscarNomeContem(
                        nome,
                        pageable
                );

        return pagina.map(usuario ->
                new UsuarioDTO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getIdade()
                )
        );
    }

    // 🔥 PUT - Atualização completa
    public UsuarioDTO atualizarPorIdDTO(
            Long id,
            UsuarioCreateDTO dto){

        UsuarioModel usuario =
                repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuario nao encontrado"
                        )
                );

        usuario.setNome(dto.getNome());
        usuario.setIdade(dto.getIdade());

        UsuarioModel usuarioAtualizado =
                repository.save(usuario);

        return new UsuarioDTO(
                usuarioAtualizado.getId(),
                usuarioAtualizado.getNome(),
                usuarioAtualizado.getIdade()
        );
    }

    // 🔥 PATCH - Atualização parcial
    public UsuarioDTO atualizarParcialUsuario(
            Long id,
            UsuarioCreateDTO dto){

        UsuarioModel usuario =
                repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuario nao encontrado"
                        )
                );

        // Atualiza apenas se vier valor válido

        if (dto.getNome() != null
                && !dto.getNome().isBlank()) {

            usuario.setNome(dto.getNome());
        }

        if (dto.getIdade() != null
                && dto.getIdade() > 0) {

            usuario.setIdade(dto.getIdade());
        }

        UsuarioModel usuarioAtualizado =
                repository.save(usuario);

        return new UsuarioDTO(
                usuarioAtualizado.getId(),
                usuarioAtualizado.getNome(),
                usuarioAtualizado.getIdade()
        );
    }

    // 🔥 DELETE - Remover usuário
    public void removerUsuario(
            Long id){

        UsuarioModel usuario =
                repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuario nao encontrado"
                        )
                );

        repository.delete(usuario);
    }

    public UsuarioComPedidosDTO
        buscarUsuarioComPedidos(Long id){

    UsuarioModel usuario =
            repository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException(
                            "Usuario nao encontrado"
                    )
            );

    List<PedidoDTO> pedidosDTO =
            usuario.getPedidos()
            .stream()
            .map(pedido -> new PedidoDTO(
                    pedido.getId(),
                    pedido.getDescricao(),
                    pedido.getValor(),
                    usuario.getNome()
            ))
            .toList();

    return new UsuarioComPedidosDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getIdade(),
            pedidosDTO
    );
}

}