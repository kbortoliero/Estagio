package br.sceweb.teste;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC02ConsultarEmpresa {

	static EmpresaDAO empresaDAO;
	static Empresa empresa; 
	
	@BeforeClass
	public static void setUpBeforeClass(){
		empresaDAO = new EmpresaDAO();
		empresa = new Empresa();
		empresa.setNomeDaEmpresa("Empresa X");
		empresa.setCnpj("89424232000180");
		empresa.setNomeFantasia("Empresa X");
		empresa.setEndereco("Rua Taquari");
		empresa.setTelefone("12121212");
		empresa.setResponsavel("José");
		empresa.setTelefoneResponsavel("111111111");
		empresa.setSetor("Informática");
		empresa.setEmail("info@teste.com.br");
		
	}
	
	@Test
	public void CT01UC02FBConsultarEmpresa_Com_Sucesso() throws SQLException {
		empresaDAO.cadastrar(empresa);
		assertTrue(empresa.equals(empresaDAO.consultar("89424232000180")));
		empresaDAO.excluir("89424232000180");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		empresaDAO.excluir("89424232000180");
	}

}
