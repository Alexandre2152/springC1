package br.com.alexandre.domain.repository;

import br.com.alexandre.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidos extends JpaRepository<ItemPedido, Integer> {
}
