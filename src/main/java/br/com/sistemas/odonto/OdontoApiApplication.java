package br.com.sistemas.odonto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe utilizada para inicializar a api.
 * 
 * @author guilherme
 *
 */
@SpringBootApplication
public class OdontoApiApplication {

    /**
     * Método de inicialização.
     * 
     * @param args
     *            String[] - Parâmetros que são passados na linha de comando ao
     *            inicializar a aplicação.
     */
    public static void main(String[] args) {
        SpringApplication.run(OdontoApiApplication.class, args);
    }
}
