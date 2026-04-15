package com.example.aula1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.aula1.model.UsuarioModel;

public interface UsuarioRepository 
        extends JpaRepository<UsuarioModel, Long> {
        // essa interface controla o banco

    //List <UsuarioModel> findByNome(String nome);
    // o spring cria automaticamente
    //SELECT * FROM USUARIO WHERE NOME = ?
    //busca todos o usuario que o parametro recebe
    // e retorna uma lista, pois pode ser mais de um usuario com o mesmo nome

    @Query("SELECT u FROM UsuarioModel u WHERE u.nome = :nome")
    List <UsuarioModel> buscarPorNome(@Param("nome") String nome);



   // List <UsuarioModel> findByIdadeLessThan(int idade);
    //busca idade menor que o parametro
    @Query("SELECT u FROM UsuarioModel u WHERE u.idade > :idade")
    List <UsuarioModel> buscarIdadeMaiorQue(@Param("idade") int idade);


    //List <UsuarioModel> findByIdadeGreaterThan(int idade);
    // buscar idade maior que o parametro
    @Query("SELECT u FROM UsuarioModel u WHERE u.idade < :idade")
    List <UsuarioModel> buscarIdadeMenorQue(@Param("idade") int idade);




    //List <UsuarioModel> findByNomeContainingIgnoreCase(String nome);
    //busca por parte do nome
    //ignore case ignora maiuscula e minuscula

    @Query("SELECT u FROM UsuarioModel u WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
     Page<UsuarioModel> buscarNomeContem(@Param("nome") String nome, Pageable pageable);
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





/*INSERT INTO USUARIOS (NOME_USUARIO, IDADE_USUARIO) VALUES ('Joao', 18);
INSERT INTO USUARIOS (NOME_USUARIO, IDADE_USUARIO) VALUES ('Maria', 22);
INSERT INTO USUARIOS (NOME_USUARIO, IDADE_USUARIO) VALUES ('Pedro', 30);
INSERT INTO USUARIOS (NOME_USUARIO, IDADE_USUARIO) VALUES ('Ana', 17);
INSERT INTO USUARIOS (NOME_USUARIO, IDADE_USUARIO) VALUES ('Carlos', 25);
INSERT INTO USUARIOS (NOME_USUARIO, IDADE_USUARIO) VALUES ('Marcos', 40);
INSERT INTO USUARIOS (NOME_USUARIO, IDADE_USUARIO) VALUES ('Julia', 19);
INSERT INTO USUARIOS (NOME_USUARIO, IDADE_USUARIO) VALUES ('Fernanda', 28);
INSERT INTO USUARIOS (NOME_USUARIO, IDADE_USUARIO) VALUES ('Lucas', 35);
INSERT INTO USUARIOS (NOME_USUARIO, IDADE_USUARIO) VALUES ('Rafael', 21);
INSERT INTO USUARIOS (NOME_USUARIO, IDADE_USUARIO) VALUES ('Roberto', 50);
INSERT INTO USUARIOS (NOME_USUARIO, IDADE_USUARIO) VALUES ('Mariana', 16);
INSERT INTO USUARIOS (NOME_USUARIO, IDADE_USUARIO) VALUES ('Patricia', 27);
INSERT INTO USUARIOS (NOME_USUARIO, IDADE_USUARIO) VALUES ('Gabriel', 23);
INSERT INTO USUARIOS (NOME_USUARIO, IDADE_USUARIO) VALUES ('Matheus', 31);
*/