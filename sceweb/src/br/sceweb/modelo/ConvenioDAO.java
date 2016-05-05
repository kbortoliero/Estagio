package br.sceweb.modelo;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mysql.jdbc.PreparedStatement;

import br.sceweb.servico.ConnectionFactory;

public class ConvenioDAO {

	Logger logger = Logger.getLogger(ConvenioDAO.class);

	public int adiciona(Convenio convenio) {
		PreparedStatement ps;
		int codigoRetorno = 0;
		try (Connection conn = new ConnectionFactory().getConnection()) {
			ps = (PreparedStatement) conn
					.prepareStatement("insert into convenio (empresa_cnpj, dataInicio, dataFim) values(?,?,?)");
			ps.setString(1, convenio.getCnpj());
			ps.setString(2, convenio.getDataInicio().toString("YYYY-MM-dd"));
			ps.setString(3, convenio.getDataTermino().toString("YYYY-MM-dd"));
			codigoRetorno = ps.executeUpdate();
			logger.info("codigo de retorno do metodo adiciona convenio = " + codigoRetorno);
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return codigoRetorno;
	}
	
	
	public int excluir(String cnpj){
		PreparedStatement ps; 
		int codRet = 0;
		
		try (Connection conn = new ConnectionFactory().getConnection()){
			ps = (PreparedStatement) conn.prepareStatement("delete from convenio where empresa_cnpj = ?");
			ps.setString(1, cnpj);
			codRet = ps.executeUpdate();
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		return codRet;
	}

}
