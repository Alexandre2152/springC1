package br.com.alexandre.service;

import br.com.alexandre.model.Cliente;
import br.com.alexandre.repository.ClientesRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    //Variável de instancia
    private ClientesRepository repository;

    //Classe Contrutora
    public ClienteService( ClientesRepository repository){
        this.repository = repository;
    }

    public void salvarCliente (Cliente cliente){
        validarCliente(cliente);
        this.repository.persistir(cliente);
    }

    public void validarCliente(Cliente cliente){
        //Metedos para validar a sinformações inseridas no banco
    }

}


/*
*Obs:
* Para o ClienteService funcionar, ele precisa do cliente repoitory.
* Pois o ClienteRepository que faz a gravação na base de dados.
*/