package br.com.alexandre;

import br.com.alexandre.domain.entity.Cliente;
import br.com.alexandre.domain.entity.Pedido;
import br.com.alexandre.domain.entity.Produto;
import br.com.alexandre.domain.repository.Clientes;
import br.com.alexandre.domain.repository.Pedidos;
import br.com.alexandre.domain.repository.Produtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes,
                                  @Autowired Pedidos pedidos){
        return args -> {

            System.out.println("\nSalvando Cliente");
            Cliente fulano = new Cliente("Alexandre");
            clientes.save(fulano);


            List<Cliente> todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("\nSalvando Pedidos");
            Pedido p = new Pedido();
            p.setCliente(fulano);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(10.00));
            pedidos.save(p);

            p.setId(+1);
            p.setCliente(fulano);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(123.12));
            pedidos.save(p);

            p.setId(+1);
            p.setCliente(fulano);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(444.12));
            pedidos.save(p);

//            Cliente cliente = clientes.findClienteFetchPedidos(fulano.getId());
//            System.out.println(cliente);
//            System.out.println(cliente.getPedidos());

            System.out.println("\nPedidos para um Cliente");
            Set<Pedido> pedido = pedidos.findByCliente(fulano);
            System.out.println(fulano.getNome() + " tem esses pedidos: ");
            System.out.println(pedido);

//            System.out.println("\nAtualizando Individualmente");
//
//            clientes.save(new Cliente(5, "Jaspion"));
//
//            clientes.findAll().forEach(System.out::println);
//
//            System.out.println("\nAtualizando");
//            todosClientes.forEach(c->{
//                c.setNome(c.getNome() + " atualizado.");
//                clientes.save(c);
//            });
//
//            clientes.findAll().forEach(System.out::println);
//
//            System.out.println("\nBuscando Cliente por nome");
//            clientes.findByNomeLike("%Fontes%").forEach(System.out::println);
//
//            System.out.println("\nBuscando Cliente por nome / Outro metodo");
//            clientes.buscarNome("%Ale%").forEach(System.out::println);
//
//            System.out.println("\nBuscar nome por id");
//            clientes.buscarPorId(3).forEach(System.out::println);
//
//            System.out.println("\nVerificar se existe o nome");
//            boolean existe = clientes.existsByNome("Fontes");
//            if(existe = true){
//                System.out.println("Resposta: " + existe + " \nExiste no banco!" );
//            }else{
//                System.out.println("Resposta: " + existe + "\nNão existe no banco!");
//            }
//
//            System.out.println("\nDeletando Cliente e exibindo a lista");
//            clientes.deleteById(1);
//
//            clientes.findAll().forEach(System.out::println);
//
//            System.out.println("\nDeletando por nome/ Query Methods");
//            clientes.deleteByNomeLike("%Fonte%");
//
//            clientes.findAll().forEach(System.out::println);

        };

    }

//    @Bean
//    public CommandLineRunner init2(@Autowired Produtos produtos){
//        return args -> {
//
//            System.out.println("\n--------------------------");
//            System.out.println("\nSalvando Produto");
//
//            produtos.save(new Produto("teste", BigDecimal.valueOf(1123.10)));
//
//            produtos.findAll().forEach(System.out::println);
//
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
