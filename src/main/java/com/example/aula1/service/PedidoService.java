package com.example.aula1.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public String criarPedido(Long usuarioId, PedidoModel pedido) {
        
         Optional<UsuarioModel> usuario = usuRepository.findById(usuarioId);

            if (usuario.isPresent()) {
                 pedido.setUsuario(usuario.get());
                 repository.save(pedido);
                 return "Pedido criado com sucesso";
            } else {
                    return "Usuario nao encontrado";
        }
    }

    public List<PedidoModel> listarPedidos() {
        return repository.findAll();
    }


    public List<PedidoModel> listarPedidosPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }



    public List<PedidoDTO> listarPedidosDTO(){
        return repository.findAll() // busca todos os pedidos no banco
        .stream() // transforma em um stream para percorrer
        .map(pedido -> new PedidoDTO(   // para cada pedido, cria um pedidoDTO
            pedido.getDescricao(),  
            pedido.getValor(),
            pedido.getUsuario().getNome()))
            .toList();  // retorna a lista
    }

}