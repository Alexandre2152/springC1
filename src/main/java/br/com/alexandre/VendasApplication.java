package br.com.alexandre;

import br.com.alexandre.domain.entity.Cliente;
import br.com.alexandre.domain.entity.Produto;
import br.com.alexandre.domain.repository.Clientes;
import br.com.alexandre.domain.repository.Produtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {

            System.out.println("Salvando Cliente");
            clientes.salvar(new Cliente("Alexandre"));
            clientes.salvar(new Cliente("Santana"));
            clientes.salvar(new Cliente("Alexandre Santana"));
            clientes.salvar(new Cliente("Fontes Santana"));
            clientes.salvar(new Cliente("Fontes"));

            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("\nAtualizando Individualmente");

            clientes.atualizar(new Cliente(5, "Jaspion"));

            clientes.obterTodos().forEach(System.out::println);

            System.out.println("\nAtualizando");
            todosClientes.forEach(c->{
                c.setNome(c.getNome() + " atualizado.");
                clientes.atualizar(c);
            });

            clientes.obterTodos().forEach(System.out::println);

            System.out.println("\nBuscando Cliente");
            clientes.buscarPorNome("x").forEach(System.out::println);

            System.out.println("\nDeletando Cliente e exibindo a lista");
            clientes.delete(3);
            clientes.delete(2);

            clientes.obterTodos().forEach(System.out::println);

        };
    }

    @Bean
    public CommandLineRunner ini(@Autowired Produtos produtos){
        return args -> {

            System.out.println("\n Salvando Produto");
            produtos.salvar(new Produto("descricão um", 100.00));

            List<Produto> todosProdutos = produtos.obterTodos();
            todosProdutos.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
