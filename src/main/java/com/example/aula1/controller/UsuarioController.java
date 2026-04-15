package com.example.aula1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.aula1.dto.ApiResponse;
import com.example.aula1.dto.UsuarioComPedidosDTO;
import com.example.aula1.dto.UsuarioCreateDTO;
import com.example.aula1.dto.UsuarioDTO;
import com.example.aula1.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name = "Usuarios",
    description = "Operações relacionadas aos usuarios"
)


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;


    @Operation(
        summary = "Cria um novo usuario",
        description = "Cria um novo usuario no sistema"
    )


    // POST - Criar usuário
    @PostMapping("/dto")
    public ResponseEntity<ApiResponse<UsuarioDTO>>
            criarUsuarioDTO(
            @Valid @RequestBody UsuarioCreateDTO dto){

        UsuarioDTO usuario =
                service.criarUsuarioDTO(dto);

        ApiResponse<UsuarioDTO> response =
                new ApiResponse<>(
                        "success",
                        "Usuario criado com sucesso",
                        usuario
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }




    @Operation(
        summary = "Listar usuarios",
        description = "Listar usuarios no sistema com paginação"
    )



    // GET - Listar com paginação
    @GetMapping("/dto")
    public ResponseEntity<ApiResponse<Page<UsuarioDTO>>>
            listarUsuariosDTO(
            @PageableDefault(size = 5, sort = "nome")
            org.springframework.data.domain.Pageable pageable){

        Page<UsuarioDTO> usuarios =
                service.listarUsuariosDTO(pageable);

        ApiResponse<Page<UsuarioDTO>> response =
                new ApiResponse<>(
                        "success",
                        "Usuarios listados com sucesso",
                        usuarios
                );

        return ResponseEntity.ok(response);
    }

    // GET - Buscar por nome com paginação
    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<Page<UsuarioDTO>>>
            buscarNomeContemDTO(

            @RequestParam String nome,
            @PageableDefault(size = 5)
            org.springframework.data.domain.Pageable pageable){

        Page<UsuarioDTO> usuarios =
                service.buscarNomeContemDTO(
                        nome,
                        pageable
                );

        ApiResponse<Page<UsuarioDTO>> response =
                new ApiResponse<>(
                        "success",
                        "Usuarios encontrados",
                        usuarios
                );

        return ResponseEntity.ok(response);
    }

    // PUT - Atualizar completo
    @PutMapping("/dto/{id}")
    public ResponseEntity<ApiResponse<UsuarioDTO>>
            atualizarUsuarioDTO(

            @PathVariable Long id,
            @Valid @RequestBody UsuarioCreateDTO dto){

        UsuarioDTO usuario =
                service.atualizarPorIdDTO(id, dto);

        ApiResponse<UsuarioDTO> response =
                new ApiResponse<>(
                        "success",
                        "Usuario atualizado com sucesso",
                        usuario
                );

        return ResponseEntity.ok(response);
    }



    @Operation(
        summary = "Atualizar parcialmente um usuario",
        description = "Atualizar parcialmente um usuario no sistema, somente os campos preenchidos serao atualizados"
    )


    // PATCH - Atualização parcial
    @PatchMapping("/dto/{id}")
    public ResponseEntity<ApiResponse<UsuarioDTO>>
            atualizarParcialUsuario(

            @PathVariable Long id,
            @RequestBody UsuarioCreateDTO dto){

        UsuarioDTO usuario =
                service.atualizarParcialUsuario(
                        id,
                        dto
                );

        ApiResponse<UsuarioDTO> response =
                new ApiResponse<>(
                        "success",
                        "Usuario atualizado parcialmente",
                        usuario
                );

        return ResponseEntity.ok(response);
    }



    @Operation(
        summary = "Remover um usuario",
        description = "Remover um usuario no sistema, utilizando o id do usuario"
    )


    // DELETE - Remover usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
            deletarUsuario(
            @PathVariable Long id){

        service.removerUsuario(id);

        return ResponseEntity
                .noContent()
                .build();
    }


    @Operation(
    summary = "Buscar usuario com pedidos",
    description = "Retorna usuario junto com seus pedidos"
)
@GetMapping("/{id}/pedidos")
public ResponseEntity<
        ApiResponse<UsuarioComPedidosDTO>>
        buscarUsuarioComPedidos(
        @PathVariable Long id){

    UsuarioComPedidosDTO usuario =
            service.buscarUsuarioComPedidos(id);

    ApiResponse<UsuarioComPedidosDTO> response =
            new ApiResponse<>(
                    "success",
                    "Usuario encontrado",
                    usuario
            );

    return ResponseEntity.ok(response);
}
}