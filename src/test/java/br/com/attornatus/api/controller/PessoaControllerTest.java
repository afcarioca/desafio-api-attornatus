package br.com.attornatus.api.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {

	@Autowired
	private MockMvc mock;

	@Test
	public void deveriaDevolver201AoCadastrarUmaPessoaQueAindaNaoEstaNaBaseDeDados() throws Exception {
		URI uri = new URI("/api/pessoas");
		String json = "{\r\n" + "		\"nome\":\"ANDRE GAMA\",\r\n"
				+ "		\"dataDeNascimento\": \"20-10-1992\",\r\n" + "	  \"endereco\" :\r\n" + "	\r\n"
				+ "		\r\n" + "		   {\r\n" + "					\"logradouro\":\"A\",\r\n"
				+ "					\"cep\":\"66666-333\",\r\n" + "					\"numero\":\"12\",\r\n"
				+ "					\"cidade\":\"A\",\r\n" + "					\"principal\": true\r\n"
				+ "			 }\r\n" + "			\r\n" + "}";

		mock.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}

	@Test
	public void deveriaDevolver201AoCadastrarUmaPessoaQueAindaNaoEstaNaBaseDeDadosMasComUmEnderecoExistente()
			throws Exception {
		URI uri = new URI("/api/pessoas");
		String json = "{\r\n" + "		\"nome\":\"ANDRE GAMA\",\r\n"
				+ "		\"dataDeNascimento\": \"20-10-1992\",\r\n" + "	  \"endereco\" :\r\n" + "	\r\n"
				+ "		\r\n" + "		   {\r\n" + "					\"logradouro\":\"A\",\r\n"
				+ "					\"cep\":\"55555-333\",\r\n" + "					\"numero\":\"12\",\r\n"
				+ "					\"cidade\":\"A\",\r\n" + "					\"principal\": true\r\n"
				+ "			 }\r\n" + "			\r\n" + "}";

		mock.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}

	@Test
	public void deveriaDevolver400AoCadastrarUmaPessoaQueEstaNaBaseDeDadosESeuEnderecoTambem() throws Exception {
		URI uri = new URI("/api/pessoas");
		String json = "{\r\n" + "		\"nome\":\"ANDRE FELIPE\",\r\n"
				+ "		\"dataDeNascimento\": \"20-10-1992\",\r\n" + "	  \"endereco\" :\r\n" + "	\r\n"
				+ "		\r\n" + "		   {\r\n" + "					\"logradouro\":\"A4564\",\r\n"
				+ "					\"cep\":\"88888-333\",\r\n" + "					\"numero\":\"12\",\r\n"
				+ "					\"cidade\":\"A\",\r\n" + "					\"principal\": true\r\n"
				+ "			 }\r\n" + "			\r\n" + "}";

		mock.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void deveriaDevolver400AoCadastrarUmaPessoaQueEstaNaBaseDeDadosESeuEnderecoNaoEsta() throws Exception {
		URI uri = new URI("/api/pessoas");
		String json = "{\r\n" + "		\"nome\":\"ANDRE FELIPE\",\r\n"
				+ "		\"dataDeNascimento\": \"20-10-1992\",\r\n" + "	  \"endereco\" :\r\n" + "	\r\n"
				+ "		\r\n" + "		   {\r\n" + "					\"logradouro\":\"A\",\r\n"
				+ "					\"cep\":\"66666-333\",\r\n" + "					\"numero\":\"12\",\r\n"
				+ "					\"cidade\":\"A\",\r\n" + "					\"principal\": true\r\n"
				+ "			 }\r\n" + "			\r\n" + "}";

		mock.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

	@Test
	public void deveriaDevolver200AoTentarEditarONomeDaPessoaEOEndereco() throws Exception {
		URI uri = new URI("/api/pessoas/6b09bc77-548f-4649-83d8-81743a355e4e");

		String json = "{\r\n" + "		\"nome\":\"ANDRE FREITAS\",\r\n"
				+ "		\"dataDeNascimento\": \"20-10-1993\",\r\n" + "	  \"endereco\" :\r\n" + "	\r\n"
				+ "		 [\r\n" + "				{\r\n" + "				\"logradouro\": \"A\",\r\n"
				+ "				\"cep\": \"55555-333\",\r\n" + "				\"numero\": \"12\",\r\n"
				+ "				\"cidade\": \"A\",\r\n" + "				\"principal\":true\r\n" + "				}\r\n"
				+ "			 \r\n" + "			]\r\n" + "			\r\n" + "}";

		mock.perform(MockMvcRequestBuilders.put(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));

	}

	@Test
	public void deveriaDevolver200AoTentarEditarUmaPessoaComUmListaDeEnderecos() throws Exception {
		URI uri = new URI("/api/pessoas/6b09bc77-548f-4649-83d8-81743a355e4e");

		String json = "{\r\n" + "		\"nome\":\"ANDRE FREITAS\",\r\n"
				+ "		\"dataDeNascimento\": \"20-10-1993\",\r\n" + "	  \"endereco\" :\r\n" + "	\r\n"
				+ "		 [\r\n" + "				{\r\n" + "				\"logradouro\": \"A\",\r\n"
				+ "				\"cep\": \"55555-333\",\r\n" + "				\"numero\": \"12\",\r\n"
				+ "				\"cidade\": \"A\",\r\n" + "				\"principal\":true\r\n" + "				},\r\n"
				+ "			 {\r\n" + "			\r\n" + "			\"logradouro\": \"A\",\r\n"
				+ "			\"cep\": \"11111-333\",\r\n" + "			\"numero\": \"12\",\r\n"
				+ "			\"cidade\": \"A\",\r\n" + "			 \"principal\":false\r\n" + "		   }\r\n"
				+ "			 \r\n" + "			]\r\n" + "			\r\n" + "}";

		mock.perform(MockMvcRequestBuilders.put(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));

	}

	@Test
	public void deveriaDevolver400AoTentarEditarUmaPessoaComUmaListaDeEnderecosVazia() throws Exception {
		URI uri = new URI("/api/pessoas/6b09bc77-548f-4649-83d8-81743a355e4e");

		String json = "{\r\n" + "		\"nome\":\"ANDRE FREITAS\",\r\n"
				+ "		\"dataDeNascimento\": \"20-10-1993\",\r\n" + "	  \"endereco\" :\r\n" + "	\r\n"
				+ "		 [\r\n" + "			\r\n" + "			 \r\n" + "			]\r\n" + "			\r\n" + "}";

		mock.perform(MockMvcRequestBuilders.put(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));

	}

	@Test
	public void deveriaDevolver400AoTentarEditarUmaPessoaQueNaoEstejaNaBaseDeDados() throws Exception {
		URI uri = new URI("/api/pessoas/6b09bc77-548f-4649-83d8-81743a355e4");

		String json = "{\r\n" + "		\"nome\":\"ANDRE FREITAS\",\r\n"
				+ "		\"dataDeNascimento\": \"20-10-1993\",\r\n" + "	  \"endereco\" :\r\n" + "	\r\n"
				+ "		 [\r\n" + "			\r\n" + "			 \r\n" + "			]\r\n" + "			\r\n" + "}";

		mock.perform(MockMvcRequestBuilders.put(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));

	}

	@Test
	public void deveriaDevolver200AoConsultarUmaPessoaQueEstejaNaBaseDeDados() throws Exception {
		URI uri = new URI("/api/pessoas/6b09bc77-548f-4649-83d8-81743a355e4e");

		mock.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));

	}

	@Test
	public void deveriaDevolver400AoConsultarUmaPessoaQueNaoEstejaNaBaseDeDados() throws Exception {
		URI uri = new URI("/api/pessoas/6b09bc77-548f-4649-83d8-81743a355e4");

		mock.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));

	}

	@Test
	public void deveriaDevolver200AoTentarListarAsPessoas() throws Exception {
		URI uri = new URI("/api/pessoas");

		mock.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));

	}

	@Test
	public void deveriaDevolver201AoTentarCriarUmEnderecoQueNaoEstejaListaDaPessoa() throws Exception {
		URI uri = new URI("/api/pessoa/6b09bc77-548f-4649-83d8-81743a355e4e/endereco");
		String json = "{\r\n" + "			\"logradouro\": \"A\",\r\n" + "			\"cep\": \"11111-333\",\r\n"
				+ "			\"numero\": \"12\",\r\n" + "			\"cidade\": \"A\",\r\n"
				+ "	    \"principal\":true\r\n" + "}";

		mock.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));

	}

	@Test
	public void deveriaDevolver400AoTentarCriarUmEnderecoQueEstejaListaDaPessoa() throws Exception {
		URI uri = new URI("/api/pessoa/6b09bc77-548f-4649-83d8-81743a355e4e/endereco");
		String json = "{\r\n" + "			\"logradouro\": \"A\",\r\n" + "			\"cep\": \"55555-333\",\r\n"
				+ "			\"numero\": \"12\",\r\n" + "			\"cidade\": \"A\",\r\n"
				+ "	    \"principal\":true\r\n" + "}";

		mock.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));

	}

	@Test
	public void deveriaDevolver400AoTentarCriarUmEnderecoDeUmaPessoaQueNaoEstejaNaBaseDeDados() throws Exception {
		URI uri = new URI("/api/pessoa/6b09bc77-548f-4649-83d8-81743a355e4/endereco");
		String json = "{\r\n" + "			\"logradouro\": \"A\",\r\n" + "			\"cep\": \"55555-333\",\r\n"
				+ "			\"numero\": \"12\",\r\n" + "			\"cidade\": \"A\",\r\n"
				+ "	    \"principal\":true\r\n" + "}";

		mock.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));

	}

	@Test
	public void deveriaDevolver200AoTentarListarOsEnderecosDeUmaPessoaQueEstejaNaBaseDeDados() throws Exception {
		URI uri = new URI("/api/pessoa/6b09bc77-548f-4649-83d8-81743a355e4e/endereco");

		mock.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));

	}

	@Test
	public void deveriaDevolver400AoTentarListarOsEnderecosDeUmaPessoaQueNaoEstejaNaBaseDeDados() throws Exception {
		URI uri = new URI("/api/pessoa/6b09bc77-548f-4649-83d8-81743a355e4/endereco");

		mock.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));

	}

	@Test
	public void deveriaDevolver200AoTentarListarOEnderecoPrincipalDeUmaPessoaQueEstejaNaBaseDeDados() throws Exception {
		URI uri = new URI("/api/pessoa/6b09bc77-548f-4649-83d8-81743a355e4e/endereco/principal");

		mock.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));

	}

	@Test
	public void deveriaDevolver400AoTentarListarOEnderecoPrincipalDeUmaPessoaQueNaoEstejaNaBaseDeDados()
			throws Exception {
		URI uri = new URI("/api/pessoa/6b09bc77-548f-4649-83d8-81743a355e4/endereco/principal");

		mock.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));

	}

}
