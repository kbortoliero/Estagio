package br.sceweb.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		
		return codRetorno; 
	}
	
//	public Empresa listar() {
//		Empresa empresas = new Empresa();
//		PreparedStatement ps;
//		try (Connection conn = new ConnectionFactory().getConnection()) {
//			ps = conn.prepareStatement("SELECT * FROM empresa");
//			ResultSet resultSet = ps.executeQuery();
//			while (resultSet.next()) {
//				Empresa empresa = new Empresa();
//				empresa.setNomeDaEmpresa(resultSet.getString("nomeDaEmpresa"));
//			}
//			resultSet.close();
//			ps.close();
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//		return empresas;
//	}
	
	public Empresa consultar(String cnpj){
		String sql = "SELECT * FROM empresa WHERE cnpj = '" + cnpj + "'";
		PreparedStatement ps;
		Empresa empresa = new Empresa();
		
		try (Connection conn = new ConnectionFactory().getConnection()){
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				empresa.setCnpj(rs.getString("cnpj"));
				empresa.setNomeDaEmpresa(rs.getString("nomeDaEmpresa"));
				empresa.setNomeFantasia(rs.getString("nomeFantasia"));
				empresa.setEndereco(rs.getString("endereco"));
				empresa.setTelefone(rs.getString("telefone"));
			}
		rs.close();
		ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empresa;
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
