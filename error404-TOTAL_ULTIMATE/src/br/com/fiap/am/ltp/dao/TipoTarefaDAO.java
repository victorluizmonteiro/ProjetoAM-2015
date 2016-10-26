package br.com.fiap.am.ltp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.am.ltp.beans.TipoTarefa;
/**
 * 
 * @author Filipe Moraes
 * @see TipoTarefaDAO
 * @category Java Class
 * @version 20/10/2015
 * @since 1.8.0
 */

public class TipoTarefaDAO {
	
	
	List<TipoTarefa> lstTarefa = new ArrayList<TipoTarefa>();
	
	/**
	 * @author Victor Luiz
	 * @category Java.Util.List
	 * @since 21/10/2015
	 * @param con
	 * @return lstTarefa
	 * @throws SQLException
	 */
	
	public List<TipoTarefa> listarTarefa(Connection con) throws SQLException{
		
		String sql = "select * from T_AM_ERR_TIPO_TAREFA";
		PreparedStatement estrutura = con.prepareStatement(sql);
		ResultSet resultaDados = estrutura.executeQuery();
		while (resultaDados.next()){
			TipoTarefa ta = new TipoTarefa();
			ta.setTarefa(resultaDados.getString("DS_TIPO_TAREFA"));
			ta.setCodigoTipoTarefa(resultaDados.getInt("CD_TIPO_TAREFA"));
			lstTarefa.add(ta);
		}
		
		resultaDados.close();
		estrutura.close();
		return  lstTarefa;
	}
}
