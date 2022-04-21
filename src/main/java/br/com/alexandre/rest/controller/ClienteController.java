package br.com.alexandre.rest.controller;

import br.com.alexandre.domain.entity.Cliente;
import br.com.alexandre.domain.repository.Clientes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    //Metodo para listar cliente por ID
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById ( @PathVariable Integer id){
        Optional<Cliente> cliente = clientes.findById(id);

        if (cliente.isPresent()){
            return ResponseEntity.ok( cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    //Método recuperar cliente por nome
    @PostMapping("/nome")
    @ResponseBody
    public ResponseEntity getClienteByNome (@RequestBody Cliente cliente){
        List<Cliente> clienteNome = clientes.findByNomeLike(cliente.getNome());

        if (clienteNome.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok( clienteNome );
    }

    //Método para recuperar todos os clientes
    @GetMapping("/todos")
    @ResponseBody
    public ResponseEntity<List<Cliente>> getClienteFindAll (){
        List<Cliente> todos = clientes.findAll();
        return ResponseEntity.ok(todos);
    }

    //Método para salvar clientes
    @PostMapping("/Salvar")
    @ResponseBody
    public ResponseEntity save( @RequestBody Cliente cliente){
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    //Metodo Deletar por id
    @DeleteMapping("/deletar/{id}")
    @ResponseBody
    public ResponseEntity deletar (@PathVariable Integer id){
        Optional<Cliente> clienteDeletar = clientes.findById(id);

        if (clienteDeletar.isPresent()){
            clientes.delete(clienteDeletar.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //Deletar por nome
    @DeleteMapping("/deletar")
    @ResponseBody
    public ResponseEntity deletarNome (@RequestBody Cliente cliente){
        List<Cliente> clienteNome = clientes.findByNomeLike(cliente.getNome());

        if (clienteNome.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        clientes.deleteByNomeLike(cliente.getNome());
        return ResponseEntity.noContent().build();
    }

    //Atulizar
    @PutMapping("/atualizar/{id}")
    public ResponseEntity update( @PathVariable Integer id,
                                  @RequestBody Cliente cliente){
        return clientes
                .findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return ResponseEntity.noContent().build();
                } ).orElseGet( () -> ResponseEntity.notFound().build() );
    }

    //Método para pesquisar
    @GetMapping("/filtro")
    @ResponseBody
    public ResponseEntity find( Cliente filtro ){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        List<Cliente> lista = clientes.findAll(example);
        return ResponseEntity.ok(lista);
    }
}
