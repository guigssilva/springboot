package br.com.sistemas.odonto.service;

import java.util.List;

import br.com.sistemas.odonto.domain.Atividade;

public interface AtividadeService {

    Atividade buscar(Integer id) throws Exception;

    List<Atividade> buscarTodos(String nome) throws Exception;

    Atividade criar(Atividade atividade) throws Exception;

    Boolean deletar(Integer id) throws Exception;
}
