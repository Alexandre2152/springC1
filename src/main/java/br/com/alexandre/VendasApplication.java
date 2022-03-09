package br.com.alexandre;

import br.com.alexandre.annotation.Cachorro;
import br.com.alexandre.annotation.Gato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.WebEndpoint;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Value("${application.name}")
    private String aplicationName;

    @Autowired
    @Qualifier("beanTeste")
    public String TesteBean;


    @Cachorro
    public Animal animal;

    @Bean(name = "executarAnimal")
    public CommandLineRunner executar(){
        return args -> {
            this.animal.fazerBarulho();
        };
    }

    @GetMapping("hello")
    public String HelloWorld(){
        return aplicationName;
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
