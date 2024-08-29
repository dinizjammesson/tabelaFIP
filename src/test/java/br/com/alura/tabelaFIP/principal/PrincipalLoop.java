package br.com.alura.tabelaFIP.principal;

import br.com.alura.tabelaFIP.model.Dados;
import br.com.alura.tabelaFIP.model.Modelos;
import br.com.alura.tabelaFIP.model.Veiculo;
import br.com.alura.tabelaFIP.service.ConsumoApi;
import br.com.alura.tabelaFIP.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PrincipalLoop {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() {
        boolean continuar = true;

        while (continuar) {
            var menu = """
                *** OPÇÕES ***
                1. Carro
                2. Moto
                3. Caminhão
                4. Sair
                
                Digite uma das opções para consulta:            
                """;

            System.out.println(menu);
            var opcao = leitura.nextLine();
            String endereco;

            switch (opcao) {
                case "1":
                    endereco = URL_BASE + "carros/marcas";
                    break;
                case "2":
                    endereco = URL_BASE + "motos/marcas";
                    break;
                case "3":
                    endereco = URL_BASE + "caminhoes/marcas";
                    break;
                case "4":
                    continuar = false;
                    System.out.println("Programa encerrado.");
                    continue;
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    continue;
            }

            var json = consumo.obterDados(endereco);
            System.out.println(json);
            var marcas = conversor.obterLista(json, Dados.class);
            marcas.stream()
                    .sorted(Comparator.comparing(Dados::codigo))
                    .forEach(System.out::println);

            System.out.println("\nInforme o código da marca para consulta:");
            var codigoMarca = leitura.nextLine();

            endereco = endereco + "/" + codigoMarca + "/modelos";
            json = consumo.obterDados(endereco);
            var modeloLista = conversor.obterDados(json, Modelos.class);

            System.out.println("\nModelos dessa marca: ");
            modeloLista.modelos().stream()
                    .sorted(Comparator.comparing(Dados::codigo))
                    .forEach(System.out::println);

            System.out.println("\nDigite uma parte do nome do veiculo para ser buscado:");

            var nomeVeiculo = leitura.nextLine();

            List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                    .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                    .collect(Collectors.toList());

            System.out.println("\nModelos filtrados");
            modelosFiltrados.forEach(System.out::println);

            System.out.println("Digite por favor o código do modelo para buscar os valores de avaliação");
            var codigoModelo = leitura.nextLine();

            endereco = endereco + "/" + codigoModelo + "/anos";
            json = consumo.obterDados(endereco);
            List<Dados> anos = conversor.obterLista(json, Dados.class);

            List<Veiculo> veiculos = new ArrayList<>();

            for (int i = 0; i < anos.size(); i++) {
                var enderecoAnos = endereco + "/" + anos.get(i).codigo();
                json = consumo.obterDados(enderecoAnos);
                Veiculo veiculo = conversor.obterDados (json, Veiculo.class);
                veiculos.add(veiculo);
            }

            System.out.println("\nTodos os veiculos filtrados com avaliações por ano: ");
            veiculos.forEach(System.out::println);
        }
    }
}
