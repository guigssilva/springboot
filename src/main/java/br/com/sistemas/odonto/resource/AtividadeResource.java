/**
 * 
 */

package br.com.sistemas.odonto.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemas.odonto.domain.Atividade;
import br.com.sistemas.odonto.service.AtividadeService;

/**
 * Classe que expoe servicos rest.
 * 
 * @author guilherme
 *
 */
@RestController
@RequestMapping(value = "/atividades")
public class AtividadeResource {

    @Autowired
    private AtividadeService service;

    /**
     * 
     * @param nome
     * @return
     * @throws Exception
     */
    @GetMapping
    public ResponseEntity<List<Atividade>> listar(@RequestParam(required = false, name = "nome") String nome)
	    throws Exception {
	return ResponseEntity.ok(this.service.buscarTodos(nome));
    }

    /**
     * 
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) throws Exception {

	final Atividade atividade = this.service.buscar(id);
	return ResponseEntity.ok(atividade);
    }

    @PostMapping
    public ResponseEntity<Atividade> criar(@Valid @RequestBody Atividade atividade) throws Exception {

	final Atividade atividadeSalva = this.service.criar(atividade);
	return ResponseEntity.status(HttpStatus.CREATED).body(atividadeSalva);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) throws Exception {

	final Boolean deletado = this.service.deletar(id);
	return ResponseEntity.ok(deletado);
    }
}
