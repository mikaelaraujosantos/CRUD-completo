package com.example.aula1.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.aula1.model.UsuarioModel;
import com.example.aula1.service.UsuarioService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;






@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;


    @PostMapping("/usuario")
    public ResponseEntity<String> saudacao(@RequestBody UsuarioModel usuario) {
        return ResponseEntity.status(201).body(service.criarUsuario(usuario));

        //Requestbody transforma json em objeto(UsuarioModel), esse objeto é guardado na variavel usuario
        //depois chama a funcao criarUsuario do service passando o objeto da variavel como parametro

    }


    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        return ResponseEntity.ok().body(service.listarUsuarios());
    }


    @GetMapping("/usuario/{id}")
    public ResponseEntity<UsuarioModel> id(@PathVariable long id ){
        UsuarioModel usuario = service.buscarPorid(id);

        if (usuario != null){
            return ResponseEntity.ok().body(usuario);
        }else{
            return ResponseEntity.notFound().build();
        }

    }


    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<String> removerUsuariuID(@PathVariable long id){

        String resposta = service.removerUsuario(id);
        if (resposta.contains("sucesso")){
            return ResponseEntity.ok().body(resposta);
        }else{
            return ResponseEntity.status(404).body(resposta);
        }

       
    }


@PutMapping("/usuario/{id}")
public ResponseEntity<String> atualizarUsuarioId(@PathVariable long id, @RequestBody UsuarioModel usuario_atualizado) {
    String resposta = service.atualizaUsuario(id, usuario_atualizado); 

    if (resposta.contains("sucesso")) {
        return ResponseEntity.ok().body(resposta);
    } else {
        return ResponseEntity.status(404).body(resposta);
    }
}


 @GetMapping("/usuario/nome/{nome}")
 public ResponseEntity<List<UsuarioModel>> buscarPorNome(@PathVariable String nome) {
    List<UsuarioModel> usuarios = service.buscarPorNome(nome);

    if (!usuarios.isEmpty()) {
        //verifica se a lista não está vazia
        return ResponseEntity.ok().body(usuarios);
    } else {
        return ResponseEntity.notFound().build();
    }
 }
 
 

/* 
RestController é uma anotação do Spring que indica que a classe é um controlador Rest.
GetMapping é um mapeador de requisições HTTP GET para o método hello.
PostMapping é um mapeador de requisições HTTP POST para o método saudacao.
RequestBody é uma anotação que indica que o parâmetro do método deve ser preenchido com o corpo da requisição HTTP, que é convertido para um objeto Usuario.

 */
}
