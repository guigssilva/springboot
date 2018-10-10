/**
 * 
 */
package br.com.sistemas.odonto.resource;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sistemas.odonto.domain.Atividade;

/**
 * @author Guilherme
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AtividadeResourceTest {

    @Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;
    private final static String URL = "/atividades";
    private final static String URL_RECURSO = "/atividades/{id}";
    private ObjectMapper mapper;
    private Atividade atividade;

    @Before
    public void setUp() {
	this.mapper = new ObjectMapper();
	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.applicationContext).build();
	this.atividade = new Atividade();
	this.atividade.setNome("TESTE");
	this.atividade.setId(2);
    }

    @Test
    public void teste_01_consultar_todos_sucesso_com_nome() throws Exception {
	this.mockMvc.perform(get(URL).accept(MediaType.APPLICATION_JSON).param("nome", "GDG"))
		.andExpect(status().isOk());
    }

    @Test
    public void teste_02_consultar_todos_sucesso() throws Exception {
	this.mockMvc.perform(get(URL).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void teste_03_consultar_unico_sucesso() throws Exception {
	this.mockMvc.perform(get(URL_RECURSO, 1).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void teste_04_consultar_unico_nao_encontrado() {
	try {
	    this.mockMvc.perform(get(URL_RECURSO, 99)).andExpect(status().isInternalServerError());
	}
	catch (Exception e) {
	    assertEquals("Não Encontrado", e.getCause().getMessage());
	}
    }

    @Test
    public void teste_05_criar_sucesso() throws Exception {
	this.mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
		.content(this.mapper.writeValueAsString(this.atividade))).andExpect(status().isCreated());
    }

    @Test
    public void teste_06_criar_ja_existente() throws Exception {
	try {
	    this.mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
		    .content(this.mapper.writeValueAsString(this.atividade))).andExpect(status().isCreated());
	}
	catch (Exception e) {
	    assertEquals("Atividade Encontrado 2", e.getCause().getMessage());
	}
    }

    @Test
    public void teste_07_deletar_sucesso() throws Exception {
	this.mockMvc.perform(delete(URL_RECURSO, 2).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void teste_08_deletar_nao_existente() throws Exception {
	try {
	    this.mockMvc.perform(delete(URL_RECURSO, 2).contentType(MediaType.APPLICATION_JSON)
		    .content(this.mapper.writeValueAsString(this.atividade))).andExpect(status().isOk());
	}
	catch (Exception e) {
	    assertEquals("Não Encontrado", e.getCause().getMessage());
	}
    }
}
