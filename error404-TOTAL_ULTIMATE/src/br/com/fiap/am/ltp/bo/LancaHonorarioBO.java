package br.com.fiap.am.ltp.bo;

import java.sql.Connection;
import java.util.List;

import br.com.fiap.am.ltp.beans.Despesa;
import br.com.fiap.am.ltp.beans.Honorario;
import br.com.fiap.am.ltp.dao.LancaHonorarioDAO;
import br.com.fiap.am.ltp.excecoes.Excecao;

/**
 * @see LancaHonorarioBO
 * @author Filipe Moraes
 * @category Java Class
 * @since 1.8.0
 * @version 27/10/2015
 */
public abstract class LancaHonorarioBO {
	static LancaHonorarioDAO dao = new LancaHonorarioDAO();
	
	/**
	 * @author Filipe Moraes
	 * @category Java Method
	 * @param c
	 * @param h
	 * @param validaData
	 * @throws Exception
	 */
	public static void cadastrarHonorario(Connection c, Honorario h,
			String validaData, int validaDia, int validaAno) throws Exception {
		if (validaData.equals(dao.getMes(c).trim())) {

			if (validaDia <= dao.getDia(c) && validaAno <= dao.getAno(c)) {
				dao.cadastrarHononario(c, h);
			}else{
				throw new Excecao("Data invalida!");
			}
		} else {
			throw new Excecao("Data invalida!");
		}
		if (h.getQtdeHoras() <= 0) {
			throw new Excecao("Quantidade de horas devem ser maior que 0!");
		}

	}

	/**
	 * @author Gabriel Manzini
	 * @category Java Method
	 * @param c
	 * @param numProceso
	 * @return
	 * @throws Exception
	 */
	public static List<Honorario> listarHonorario(Connection c, int numProceso) throws Exception{
		return dao.listarHonorario(c, numProceso);
	}
	/**
	 * @author Richard Duarte
	 * @category Java Method
	 * @param c
	 * @param cdLancamento
	 * @return dao.listarHonorarioPorCodigo
	 * @throws Exception
	 */
	public static List<Honorario> listarHonorarioCodigo(Connection c, int cdLancamento) throws Exception{
		return dao.listarHonorarioPorCodigo(c, cdLancamento);
	}
	
	/**
	 * @author Filipe Moraes
	 * @category Java Mehtod
	 * @param c
	 * @param h
	 * @param validaData
	 * @throws Exception
	 */
	public static void atualiza(Connection c, Honorario h, String validaData,
			int validaAno, int validaDia) throws Exception {
		if (!validaData.equals(dao.getMes(c).trim())) {
			throw new Excecao("Data deve ser do mês atual");
		}

		if (h.getCodLancamento() == 0) {
			throw new Excecao("codigo inválido!");
		}
		if (h.getQtdeHoras() <= 0) {
			throw new Excecao("Quantidade de horas devem ser maior que 0!");
		}

		if (validaDia <= dao.getDia(c) && validaAno <= dao.getAno(c)) {
			dao.atualizar(c, h);

		} else {
			throw new Excecao("Data invalida");
		}

	}
	/**
	 * @author Victor Luiz
	 * @category Java Method
	 * @param c
	 * @param cdLancamento
	 * @throws Exception
	 */
	public static void deletarHonorarios(Connection c, int cdLancamento)throws Exception{
		 dao.deletarHonorario(c, cdLancamento);
	}
	

}
