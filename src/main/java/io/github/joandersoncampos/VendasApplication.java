package io.github.joandersoncampos;

import io.github.joandersoncampos.domain.entity.Cliente;
import io.github.joandersoncampos.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {

            clientes.salvar(new Cliente("Joanderson"));
            clientes.salvar(new Cliente("Jose"));

            List<Cliente> todosClientes = clientes.listarTodos();
            todosClientes.forEach(System.out::println);

            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado!");
                clientes.atualizar(c);
            });

            todosClientes = clientes.listarTodos();
            todosClientes.forEach(System.out::println);

            clientes.buscarCliente("Joa").forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
