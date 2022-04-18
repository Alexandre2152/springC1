package br.com.alexandre.domain.repository;

import br.com.alexandre.domain.entity.Cliente;
import br.com.alexandre.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

    Set<Pedido> findByCliente(Cliente cliente);
}
