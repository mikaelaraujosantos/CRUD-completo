package com.example.aula1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.aula1.UsuarioModel;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
public class UsuarioController {

    private List<UsuarioModel> usuarios = new ArrayList<>();

    int contador = 1;
    @PostMapping("/usuario")
    public String saudacao(@RequestBody UsuarioModel usuario) {
        usuario.setId(contador);
        contador ++;
        usuarios.add(usuario);
       
        return "Usuário " + usuario.getNome() + " criado com sucesso!";
    }



    @GetMapping("/usuarios")
    public List<UsuarioModel> listarUsuarios() {
        return usuarios;
    }
   


    @GetMapping("/usuario/{id}")
    public UsuarioModel id(@PathVariable long id ){

        for(UsuarioModel u : usuarios){
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }


    @DeleteMapping("/usuario/{id}")
    public String removerUsuariuID(@PathVariable long id){

        boolean resultado = usuarios.removeIf(u -> u.getId() == id);

        if (resultado){
            return "Usuario removido com sucesso";
        }else{
            return "Usuario não encontrado";
        }
    }

    @PutMapping("/usuario/{id}")
    public String atualizarUsuarioId(@PathVariable long id, @RequestBody UsuarioModel usuario_atualizado) {
        for (UsuarioModel u : usuarios){
            if (u.getId() == id){
                u.setNome(usuario_atualizado.getNome());
                u.setIdade(usuario_atualizado.getIdade());
                return "Usuario atualizado com sucesso!!";            
        }
            
        }
        return"Usuario não encontrado";
     }
    

}
 

/* 
RestController é uma anotação do Spring que indica que a classe é um controlador Rest.
GetMapping é um mapeador de requisições HTTP GET para o método hello.
PostMapping é um mapeador de requisições HTTP POST para o método saudacao.
RequestBody é uma anotação que indica que o parâmetro do método deve ser preenchido com o corpo da requisição HTTP, que é convertido para um objeto Usuario.

 */
