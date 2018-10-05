package br.com.sistemas.odonto.service;

import br.com.sistemas.odonto.domain.Atividade;

import java.util.List;

public interface AtividadeService {

    Atividade buscar(Integer id) throws Exception;

    List<Atividade> buscarTodos(String nome) throws Exception;
}
