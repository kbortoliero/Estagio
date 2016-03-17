package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC01CadastrarEmpresa {
	static EmpresaDAO empresaDAO;
	static Empresa empresa;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 empresaDAO = new EmpresaDAO();
		 empresa = new Empresa();
		 
		 empresa.setNomeDaEmpresa("Empresa X");
		 empresa.setCnpj("89424232000180");
		 empresa.setNomeFantasia("Empresa X");
		 empresa.setEndereco("Rua Taquari");
		 empresa.setTelefone("12121212");
	}
	
	@Test
	public void CT01UC01FBCadastraEmpresa_Com_Sucesso() {
		assertEquals(1, empresaDAO.cadastrar(empresa));
	}
	
	@Test
	public void CT02UC01A2CadastraEmpresa_Com_Cnpj_Ja_Cadastrado() {
		assertEquals(0, empresaDAO.cadastrar(empresa));
	}
	
	@Test
	public void CT03UC01A3CadastraEmpresa_Com_Cnpj_Invalido() {
		Empresa empresa2 = new Empresa();
		try{
			empresa2.setCnpj("894243200018");
			fail("Deveria disparar uma Exception!");
		}catch(Exception e){
			assertEquals("CNPJ Inválido!", e.getMessage());
		}
	}
	
	@Test
	public void CT04UC01A4CadastraEmpresa_Com_Dados_Invalidos() {
		Empresa empresa2 = new Empresa();
		try{
			empresa2.setNomeDaEmpresa("");
			fail("Deveria disparar uma Exception!");
		}catch(Exception e){
			assertEquals("Nome da Empresa Inválido!",  e.getMessage());
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		empresaDAO.excluir("89424232000180");
	}

}
