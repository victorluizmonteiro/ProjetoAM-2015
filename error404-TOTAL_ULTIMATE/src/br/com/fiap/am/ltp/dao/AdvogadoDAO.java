package br.com.fiap.am.ltp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.am.ltp.beans.Advogado;
/**
 * @see AdvogadoDAO
 * @author Filipe Moraes
 * @category Java Class
 * @version 25/10/2015
 * @since 1.8.0
 */
public class AdvogadoDAO {
	private PreparedStatement estrutura = null;
	private ResultSet resultado = null;
	
	/**
	 * @category Java Util List
	 * @param c
	 * @param numOAB
	 * @return lstAdvogados
	 * @throws SQLException
	 */
	public List<Advogado> listarAdvogados(Connection c, int numOAB) throws SQLException{
		List<Advogado> lstAdvogados = new ArrayList<Advogado>();
		String sql = "select cd_advogado from T_AM_ERR_ADVOGADO where nr_oab = ?";
		estrutura = c.prepareStatement(sql);
		estrutura.setInt(1, numOAB);
		resultado = estrutura.executeQuery();
		while(resultado.next()){
			Advogado adv = new Advogado();
			adv.setCodigoPessoa(resultado.getInt("cd_advogado"));
			lstAdvogados.add(adv);
		}
		estrutura.close();
		resultado.close();
		return lstAdvogados;
	}
	
}

	
