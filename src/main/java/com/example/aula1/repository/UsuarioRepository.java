package com.example.aula1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aula1.model.UsuarioModel;

public interface UsuarioRepository 
        extends JpaRepository<UsuarioModel, Long> {
        // essa interfaze controla o banco

    List <UsuarioModel> findByNome(String nome);
    // o spring cria automaticamente
    //SELECT * FROM USUARIO WHERE NOME = ?
    //busca todos o usuario que o parametro recebe
    // e retorna uma lista, pois pode ser mais de um usuario com o mesmo nome
}



// UsuarioModel -. qual tabela (entity)
// Long - qual o tipo de chave primaria
// automaticamente cia metodos como

// save() = salvar
// delete() = deletar
// findAll() = buscar todos
// findById() = buscar por id
// update() = atualizar
//deleteById() = deletar por id