package br.com.alexandre.domain.repository;

import br.com.alexandre.domain.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Produtos {

    private static String INSERT = "INSERT INTO PRODUTO (DESCRICAO, PRECO_UNITARIO) VALUES (?,?)";
    private static String SELECT = "SELECT * FROM PRODUTO";

    @Autowired
    public JdbcTemplate jdbcTemplate;

    public Produto salvar(Produto produto){
        jdbcTemplate.update(INSERT, new Object[]{produto.getDescricao(), produto.getPreco_unitario()});
        return produto;
    };

    public List<Produto> obterTodos(){
        return jdbcTemplate.query(SELECT, new RowMapper<Produto>() {
            @Override
            public Produto mapRow(ResultSet resultSet, int i) throws SQLException{
                Integer id = resultSet.getInt("id");
                String descricao = resultSet.getString("descricao");
                Double preco_unitario = resultSet.getDouble("preco_unitario");
                return new Produto(id, descricao, preco_unitario);
            }
        });
    };



}
