package br.com.alexandre.domain.repository;

import br.com.alexandre.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    private static String INSERT = "INSERT INTO CLIENTE (NOME) VALUES (?)";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE";
    private static String ATUALIZAR = "UPDATE CLIENTE SET NOME = ? WHERE ID = ?";
    private static String DELETAR = "DELETE FROM CLIENTE WHERE ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente){
        jdbcTemplate.update( INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public List<Cliente> obterTodos(){
      return jdbcTemplate.query(SELECT_ALL, new RowMapper<Cliente>() {
          @Override
          public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
              Integer id = resultSet.getInt("id");
              String nome = resultSet.getString("nome");
              return new Cliente(id, nome);
          }
      });
    };

    public Cliente atualizar(Cliente cliente){
        jdbcTemplate.update(ATUALIZAR, new Object[]{
                cliente.getNome(), cliente.getId()});
        return cliente;
    };

    public void delete(Integer id){
        jdbcTemplate.update(DELETAR, new Object[]{id});
    };

    public void delete(Cliente cliente){
        delete(cliente.getId());
    };

    public List<Cliente> buscarPorNome(String nome){
        return jdbcTemplate.query(
                SELECT_ALL.concat(" where nome like ? "),
                new Object[]{"%" + nome + "%"},
                new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        });
    }

}
