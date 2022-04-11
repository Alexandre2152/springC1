package br.com.alexandre;

import br.com.alexandre.domain.entity.Cliente;
import br.com.alexandre.domain.repository.Clientes;
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
            clientes.save(new Cliente("Alexandre"));
            clientes.save(new Cliente("Santana"));
            clientes.save(new Cliente("Alexandre Santana"));
            clientes.save(new Cliente("Fontes Santana"));
            clientes.save(new Cliente("Fontes"));

            List<Cliente> todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("\nAtualizando Individualmente");

            clientes.save(new Cliente(5, "Jaspion"));

            clientes.findAll().forEach(System.out::println);

            System.out.println("\nAtualizando");
            todosClientes.forEach(c->{
                c.setNome(c.getNome() + " atualizado.");
                clientes.save(c);
            });

            clientes.findAll().forEach(System.out::println);

            System.out.println("\nBuscando Cliente por nome");
            clientes.findByNomeLike("%Fontes%").forEach(System.out::println);

            System.out.println("\nBuscando Cliente por nome / Outro metodo");
            clientes.buscarNome("%Ale%").forEach(System.out::println);

            System.out.println("\nBuscar nome por id");
            clientes.buscarPorId(3).forEach(System.out::println);

            System.out.println("\nVerificar se existe o nome");
            boolean existe = clientes.existsByNome("Fontes");
            if(existe = true){
                System.out.println("Resposta: " + existe + " \nExiste no banco!" );
            }else{
                System.out.println("Resposta: " + existe + "\nNão existe no banco!");
            }

            System.out.println("\nDeletando Cliente e exibindo a lista");
            clientes.deleteById(1);

            clientes.findAll().forEach(System.out::println);

            System.out.println("\nDeletando por nome/ Query Methods");
            clientes.deleteByNomeLike("%Fonte%");

            clientes.findAll().forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
