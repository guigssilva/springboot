package br.com.sistemas.odonto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistemas.odonto.domain.Atividade;
import br.com.sistemas.odonto.repository.AtividadeRepository;

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

	final List<Atividade> retorno = new ArrayList<>();
	final Optional<String> optionalNome = Optional.ofNullable(nome);
	if (optionalNome.isPresent()) {
	    retorno.addAll(this.atividadeRepository.findByNomeContaining(nome));
	}
	else {
	    retorno.addAll(this.atividadeRepository.findAll());
	}
	return retorno;
    }

    @Override
    public Atividade criar(Atividade atividade) throws Exception {
	this.atividadeRepository.findById(atividade.getId())
		.ifPresent(ati -> new Exception("Atividade Encontrado ".concat(ati.toString())));
	return this.atividadeRepository.save(atividade);
    }

    @Override
    public Boolean deletar(Integer id) throws Exception {
	final Atividade atividade = this.atividadeRepository.findById(id)
		.orElseThrow(() -> new Exception("Não Encontrado"));
	this.atividadeRepository.delete(atividade);
	return Boolean.TRUE;
    }

}
