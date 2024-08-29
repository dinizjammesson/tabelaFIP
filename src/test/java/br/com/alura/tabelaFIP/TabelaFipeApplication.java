//package br.com.alura.tabelaFIP;
//
//import br.com.alura.tabelaFIP.principal.Principal;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//
//public class TabelaFipeApplication implements CommandLineRunner {
//
//    public static void main(String[] args) {
//
//        SpringApplication.run(TabelaFipeApplication.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Principal principal = new Principal();
//        principal.exibeMenu();
//    }
//}

//segue script para escolher se quer rodar em loop a aplicacao tabela fipe comente trecho acima
package br.com.alura.tabelaFIP;

import br.com.alura.tabelaFIP.principal.Principal;
import br.com.alura.tabelaFIP.principal.PrincipalLoop;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TabelaFipeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TabelaFipeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);



            System.out.println("Escolha uma opção:");
            System.out.println("1. Fazer uma consulta");
            System.out.println("2. Fazer múltiplas consultas");

            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    Principal principal = new Principal();
                    principal.exibeMenu(); // Executa a consulta única
                    break;

                case "2":
                    PrincipalLoop principalLoop = new PrincipalLoop();
                    principalLoop.exibeMenu(); // Executa as múltiplas consultas
                    break;

                default:
                    System.out.println("Opção inválida! Escolha 1 ou 2.");
                    break;
            }
        }
    }

///////////////pelo amor desdfgsdfgsdfgsdgASDFASDFASFASFDafdasfdasdffasdfasfd

