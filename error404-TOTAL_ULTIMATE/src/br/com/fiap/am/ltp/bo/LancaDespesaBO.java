package br.com.fiap.am.ltp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.fiap.am.ltp.beans.Despesa;
import br.com.fiap.am.ltp.beans.Honorario;
import br.com.fiap.am.ltp.beans.TipoDepesas;
import br.com.fiap.am.ltp.dao.LancaDespesaDAO;
import br.com.fiap.am.ltp.excecoes.Excecao;

/**
 * @see LancaDespesaBO
 * @author Gabriel Manzini
 * @category Java Class
 * @since 1.8.0
 * @version 27/10/2015
 */

public abstract class LancaDespesaBO {
	static LancaDespesaDAO dao = new LancaDespesaDAO();

	/**
	 * @author Gabriel Manzini
	 * @category Java Method
	 * @param c
	 * @return dao.listarTipoDespesa
	 * @throws Exception
	 */
	public static List<TipoDepesas> listarTipoDespesas(Connection c) throws Exception {
		return dao.listarTipoDespesa(c);

	}
	
	/**
	 * @author Victor Luiz
	 * @category Java Method
	 * @param c
	 * @param cdLancamento
	 * @throws Exception
	 */

	public static void deletarDespesa(Connection c,int cdLancamento)throws Exception{
		dao.deletarDespesa(c, cdLancamento);
	}
	/**
	 * @author Victor Luiz
	 * @category Java Method
	 * @param c
	 * @param d
	 * @param validaData
	 * @throws Exception
	 */
	public static void cadastrarDespesa(Connection c, Despesa d,
			String validaData, int validaDia ,int validaAno) throws Exception {
		if (validaData.equals(dao.getMes(c))) {
			if(validaDia <=dao.getDia(c) && validaAno <= dao.getAno(c) ){
				dao.cadastrarDespesa(d,c);
			}

			
		} else {
			throw new Excecao("Data invalida");

		}

		if (d.getValor() <= 0) {
			throw new Excecao("Valor da despesa deve ser maior que 0 !");
		}
	}
	/**
	 * @author Gabriel Manzini
	 * @category Java Method
	 * @param c
	 * @param numeroProcesso
	 * @return dao.listarDespesa
	 * @throws Exception
	 */
	public static List<Despesa> listarDespesas(Connection c,int numeroProcesso) throws Exception{
		return dao.listarDespesa(c, numeroProcesso);
	}
	/**
	 * @author Filipe Moraes
	 * @category Java Method
	 * @param c
	 * @param d
	 * @param validaData
	 * @throws Exception
	 */
	public static void atualiza(Connection c, Despesa d, String validaData,int validaAno,int validaDia)
			throws Exception {
		if (!validaData.equals(dao.getMes(c).trim())) {
			throw new Excecao("Data deve ser do mês atual");
		}
		

		if (d.getCodigoLancamento() == 0) {
			throw new Excecao("codigo inválido!");
		}
		
		if(validaDia <=dao.getDia(c) && validaAno <= dao.getAno(c) ){
			dao.atualizar(c, d);
			
		}else{
			throw new Excecao("Data invalida");
		}
		
	}

	/**
	 * @author Richard Duarte
	 * @category Java Method
	 * @param c
	 * @param cdLancamento
	 * @return
	 * @throws Exception
	 */
	public static List<Despesa> listarDespesaCodigo(Connection c, int cdLancamento) throws Exception{
		return dao.listarDespesasPorCodigo(c, cdLancamento);
	}

}
