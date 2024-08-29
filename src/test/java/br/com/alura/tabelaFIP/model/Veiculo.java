package br.com.alura.tabelaFIP.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(
//        @JsonAlias("TipoVeiculo") Integer tipo,
        @JsonAlias("Valor") String valor,
        @JsonAlias("Marca") String marca,
        @JsonAlias("Modelo") String modelo,
        @JsonAlias("AnoModelo") Integer ano,
        @JsonAlias("Combustivel") String tipoCombustivel
//        @JsonAlias("CodigoFipe") String codFipe,
//        @JsonAlias("MesReferencia") String  ref,
//        @JsonAlias("SiglaCombustivel") String sigla


) {
}
