package br.com.fiap.am.ltp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.fiap.am.ltp.beans.Advogado;
import br.com.fiap.am.ltp.dao.AdvogadoDAO;
/**
 * @see AdvogadoBO
 * @author Richard Duarte
 * @category Java Class
 * @since 1.8.0
 * @version 27/10/2015
 */
public abstract class AdvogadoBO {
	static AdvogadoDAO dao = new AdvogadoDAO();
	
	/**
	 * @author Victor Luiz
	 * @param c
	 * @param numOAB
	 * @return
	 * @throws Exception
	 */
	public static List<Advogado> listaAdvogado(Connection c, int numOAB) throws Exception{
		return dao.listarAdvogados(c, numOAB);
	}
}
