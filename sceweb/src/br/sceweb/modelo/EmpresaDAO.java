package br.sceweb.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import br.sceweb.servico.ConnectionFactory;

public class EmpresaDAO {
	Logger logger = Logger.getLogger(EmpresaDAO.class);

	public int cadastrar(Empresa empresa){
		PreparedStatement ps; 
		int codRetorno = 0;
		try (Connection conn = new ConnectionFactory().getConnection()){
			ps = conn.prepareStatement(
					"insert into empresa (cnpj, nomeDaEmpresa, nomeFantasia, endereco, telefone) values (?,?,?,?,?)");
			ps.setString(1, empresa.getCnpj());
			ps.setString(2, empresa.getNomeDaEmpresa());
			ps.setString(3, empresa.getNomeFantasia());
			ps.setString(4, empresa.getEndereco());
			ps.setString(5, empresa.getTelefone());
			codRetorno = ps.executeUpdate();
			ps.close();
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		logger.info("Chamou o método Cadastrar!");
		return codRetorno; 
	}
	
	public int excluir(String cnpj){
		PreparedStatement ps; 
		int codRet = 0;
		
		try (Connection conn = new ConnectionFactory().getConnection()){
			ps = conn.prepareStatement("delete from empresa where cnpj = ?");
			ps.setString(1, cnpj);
			codRet = ps.executeUpdate();
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		return codRet;
	}
	
}
