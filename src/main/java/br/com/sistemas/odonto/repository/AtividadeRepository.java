/**
 * 
 */

package br.com.sistemas.odonto.repository;

import br.com.sistemas.odonto.domain.Atividade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Classe responsavel por expor metodos de conexão com o banco.
 * 
 * @author guilherme
 *
 */
@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Integer> {

    List<Atividade> findByNomeContaining(@Param("nome") String nome);

}
