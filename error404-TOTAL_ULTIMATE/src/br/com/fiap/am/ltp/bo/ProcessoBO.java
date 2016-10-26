package br.com.fiap.am.ltp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.fiap.am.ltp.beans.Processo;
import br.com.fiap.am.ltp.dao.ProcessoDAO;

/**
 * @see ProcessoBO
 * @author Gabriel Manzini
 * @category Java Class
 * @since 1.8.0
 * @version 27/10/2015
 */
public abstract class ProcessoBO {
	static ProcessoDAO dao = new ProcessoDAO();
	
	/**
	 * @author Victor Luiz
	 * @category Java Method
	 * @param c
	 * @param numOAB
	 * @return
	 * @throws Exception
	 */
	public static List<Processo> listarProcessoAberto(Connection c,int numOAB) throws Exception {
		return dao.listaProcessoAberto(c,numOAB);

	}
	
	/**
	 * @author Richard Duarte
	 * @category Java Method
	 * @param c
	 * @param numOAB
	 * @return
	 * @throws Exception
	 */
	public static List<Processo> listarProcessoFechado(Connection c,int numOAB) throws Exception {
		return dao.listaProcessoFechado(c,numOAB);

	}
}
