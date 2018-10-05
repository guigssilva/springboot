package br.com.sistemas.odonto.service;

import br.com.sistemas.odonto.domain.Atividade;
import br.com.sistemas.odonto.repository.AtividadeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtividadeServiceImpl implements AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Override
    public Atividade buscar(Integer id) throws Exception {
        return this.atividadeRepository.findById(id).orElseThrow(() -> new Exception("Não Encontrado"));
    }

    @Override
    public List<Atividade> buscarTodos(String nome) throws Exception {

        return this.atividadeRepository.findByNomeContaining(nome);
    }

}
