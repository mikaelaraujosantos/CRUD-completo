package com.example.aula1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aula1.model.UsuarioModel;
import com.example.aula1.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;


    public String criarUsuario(UsuarioModel usuario){
        repository.save(usuario);
        return "Usuario criado com sucesso";
    }


    public List<UsuarioModel> listarUsuarios(){

        return repository.findAll();
    }

    public UsuarioModel buscarPorid(long id){

        Optional<UsuarioModel> usuario = repository.findById(id);

        return usuario.orElse(null);
    }

    public String removerUsuario(long id){

        if(repository.existsById(id)){
            repository.deleteById(id);
            return "Usuario removido com sucesso";
        }

        return "Usuario nao encontrado";
    }

    public String atualizaUsuario(long id , UsuarioModel usuario_atualizado){

        Optional<UsuarioModel> usuario = repository.findById(id);

        if(usuario.isPresent()){

            UsuarioModel u = usuario.get();
            //pega o usuario que está dentro do optional e coloca na variavel u

            u.setNome(usuario_atualizado.getNome());
            u.setIdade(usuario_atualizado.getIdade());

            repository.save(u);

            return "Usuario atualizado com sucesso";
        }

        return "Usuario nao encontrado";
    }



    public List<UsuarioModel> buscarPorNome(String nome){
        return repository.findByNome(nome);
    }


    public List<UsuarioModel> buscarIdadeMenorQue(int idade){
        return repository.findByIdadeLessThan(idade);
    }


    public List<UsuarioModel> buscarIdadeMaiorQue(int idade){
        return repository.findByIdadeGreaterThan(idade);
    }


    public List<UsuarioModel> buscarNomeContem(String nome){
        return repository.findByNomeContainingIgnoreCase(nome);
    }

}