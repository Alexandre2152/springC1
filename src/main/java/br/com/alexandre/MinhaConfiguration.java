package br.com.alexandre;

import br.com.alexandre.annotation.Development;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class MinhaConfiguration {

    @Bean(name = "aplicationName")
    public String applicationName(){
        return "Teste de configurações e Beans";
    }

    @Bean(name = "beanTeste")
    public String aplicationsTeste(){
        return "Teste para outro bean!";
    }

    @Bean(name = "executar")
    public CommandLineRunner executar(){
        return args -> {
          System.out.println("Rodando a configuração de desenvolvimento!");
        };
    }


//CONFIGURAÇÃO PARA A ANOTAÇÃO DE ANIMAL
//Estou utliziando aqui esse espaço para não ficar muitas classes.

    @Bean(name = "cachorro")
    public Animal cachorro(){
      return new Animal() {
          @Override
          public void fazerBarulho() {
              System.out.println("au au");
          }
      };
    }

    @Bean(name = "gato")
    public Animal gato(){
        return new Animal() {
            @Override
            public void fazerBarulho() {
                System.out.println("miau");
            }
        };
    }


}
