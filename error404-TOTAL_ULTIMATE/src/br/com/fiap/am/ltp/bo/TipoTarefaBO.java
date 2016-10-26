package br.com.fiap.am.ltp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.fiap.am.ltp.beans.TipoTarefa;
import br.com.fiap.am.ltp.dao.TipoTarefaDAO;

/**
 * @see TipoTarefaBO
 * @author Richard Duarte
 * @category Java Class
 * @since 1.8.0
 * @version 27/10/2015
 */

public abstract class TipoTarefaBO {
	
	
	/**
	 * @author Richard Duarte
	 * @category Java Method
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public static List<TipoTarefa> listar(Connection con) throws Exception{		
		return new TipoTarefaDAO().listarTarefa(con);  
	}
}
