package br.com.alexandre.domain.repository;

import br.com.alexandre.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface Clientes extends JpaRepository<Cliente, Integer> {

    //Estrutura feita por Query Methods
    List<Cliente> findByNomeLike (String nome);

    //Estrutura feita por hql
    @Query(value = "Select c from Cliente c where c.id = :id ")
    List<Cliente> buscarPorId (@Param( "id" ) Integer id);

    //Estrutura feita por sql nativo
    @Query(value = "select * from Cliente where nome like ':nome' ", nativeQuery = true)
    List<Cliente> buscarNome (@Param("nome") String nome);

    //Estrutura feita por hql
    @Query("delete from Cliente c where c.nome like :nome")
    @Modifying
    @Transactional
    void deleteByNomeLike (String nome);


    boolean existsByNome (String nome);
}
