package br.com.fiap.am.ltp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.fiap.am.ltp.beans.Despesa;
import br.com.fiap.am.ltp.beans.Processo;
import br.com.fiap.am.ltp.beans.TipoDepesas;

/**
 * @see LancaDespesaDAO
 * @author Victor Luiz
 * @category Java Class
 * @version 20/10/2015
 * @since 1.8.0
 */
public class LancaDespesaDAO {
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// Select responsavel por alimentar o campo select no formulário lancar
	// despesa
	/**
	 * @category Java Util List
	 * @author Manzini
	 * @param c
	 * @param usuario
	 * @param senha
	 * @return lista
	 * @throws Exception
	 */
	public List<TipoDepesas> listarTipoDespesa(Connection c) throws Exception {
		List<TipoDepesas> lista = new ArrayList<TipoDepesas>();
		stmt = c.prepareStatement("select * from T_AM_ERR_TIPO_DESPESA");
		rs = stmt.executeQuery();

		while (rs.next()) {
			TipoDepesas td = new TipoDepesas();
			td.setCodigoDespesa(rs.getInt("cd_tipo_despesa"));
			td.setDespesa(rs.getString("ds_tipo_despesa"));

			lista.add(td);

		}
		rs.close();
		stmt.close();
		return lista;

	}

	// Método responsavel por alimentar o camo selec no formulario lancar
	// despesas
	/**
	 * @category Java Util List
	 * @author Filipe
	 * @param c
	 * @param usuario
	 * @param senha
	 * @return lista
	 * @throws Exception
	 */
	public List<Processo> listaProcesso(Connection c) throws Exception {
		List<Processo> lista = new ArrayList<Processo>();
		stmt = c.prepareStatement("select nr_processo from T_AM_ERR_PROCESSO");
		rs = stmt.executeQuery();
		while (rs.next()) {
			Processo p = new Processo();
			p.setNumProcesso(rs.getInt("nr_processo"));

			lista.add(p);
		}
		rs.close();
		stmt.close();

		return lista;

	}

	/**
	 * @author Gabriel Manzini
	 * @category Java Method
	 * @param d
	 * @param c
	 * @throws Exception
	 */
	public void cadastrarDespesa(Despesa d, Connection c) throws Exception {
		stmt = c.prepareStatement("insert into T_AM_ERR_LANCA_DESPESA (cd_lancamento,cd_tipo_despesa,nr_processo,dt_despesa,vl_despesa,ds_observacao)"
				+ " values(SQ_AM_LANCA_DESPESA.nextval,?,?,TO_DATE(?, 'YYYY-MM-DD'),?,?)");

		stmt.setInt(1, d.getTipoDespesa().getCodigoDespesa());
		stmt.setInt(2, d.getNrProcesso().getNumProcesso());
		stmt.setString(3, d.getDtDespesa());
		stmt.setFloat(4, d.getValor());
		stmt.setString(5, d.getObservacao());

		stmt.execute();

	}

	/**
	 * @author Gabriel Manzini
	 * @category Java Util List
	 * @param c
	 * @param numeroProcesso
	 * @return
	 * @throws Exception
	 */
	public List<Despesa> listarDespesa(Connection c, int numeroProcesso)
			throws Exception {
		List<Despesa> lstDespesa = new ArrayList<>();
		String sql = "select T_AM_ERR_LANCA_DESPESA.cd_lancamento, TO_CHAR(T_AM_ERR_LANCA_DESPESA.dt_despesa, 'DD/MM/YYYY') \"DT_DESPESA\" ,T_AM_ERR_TIPO_DESPESA.ds_tipo_despesa,T_AM_ERR_LANCA_DESPESA.vl_despesa,"
				+ "T_AM_ERR_LANCA_DESPESA.ds_observacao "
				+ "from T_AM_ERR_TIPO_DESPESA INNER JOIN T_AM_ERR_LANCA_DESPESA "
				+ "ON (T_AM_ERR_TIPO_DESPESA.CD_TIPO_DESPESA = T_AM_ERR_LANCA_DESPESA.CD_TIPO_DESPESA )"
				+ "where nr_processo = " + numeroProcesso;
		stmt = c.prepareStatement(sql);
		rs = stmt.executeQuery();
		while (rs.next()) {
			TipoDepesas td = new TipoDepesas();
			Despesa d = new Despesa();
			td.setDespesa(rs.getString("ds_tipo_despesa"));

			d.setCodigoLancamento(rs.getInt("cd_lancamento"));
			d.setDtDespesa(rs.getString("dt_despesa"));
			d.setTipoDespesa(td);
			d.setValor(rs.getFloat("vl_despesa"));
			d.setObservacao(rs.getString("ds_observacao"));

			lstDespesa.add(d);

		}
		rs.close();
		stmt.close();
		return lstDespesa;
	}

	/**
	 * @author Gabriel Manzini
	 * @category Java Method
	 * @param c
	 * @param cdLancamento
	 * @throws Exception
	 */

	public List<Despesa> listarDespesasPorCodigo(Connection c, int codLancamento)
			throws Exception {
		List<Despesa> lstDespesaCodigo = new ArrayList<Despesa>();
		String sql = "select T_AM_ERR_LANCA_DESPESA.cd_lancamento, TO_CHAR(T_AM_ERR_LANCA_DESPESA.dt_despesa, 'DD/MM/YYYY') \"DT_DESPESA\" ,T_AM_ERR_TIPO_DESPESA.ds_tipo_despesa,T_AM_ERR_LANCA_DESPESA.vl_despesa,"
				+ "T_AM_ERR_LANCA_DESPESA.ds_observacao "
				+ "from T_AM_ERR_TIPO_DESPESA INNER JOIN T_AM_ERR_LANCA_DESPESA "
				+ "ON (T_AM_ERR_TIPO_DESPESA.CD_TIPO_DESPESA = T_AM_ERR_LANCA_DESPESA.CD_TIPO_DESPESA )"
				+ "where cd_lancamento = ?";
		stmt = c.prepareStatement(sql);
		stmt.setInt(1, codLancamento);
		rs = stmt.executeQuery();
		while (rs.next()) {
			TipoDepesas td = new TipoDepesas();
			Despesa d = new Despesa();
			td.setDespesa(rs.getString("ds_tipo_despesa"));

			d.setCodigoLancamento(rs.getInt("cd_lancamento"));
			d.setDtDespesa(rs.getString("dt_despesa"));
			d.setTipoDespesa(td);
			d.setValor(rs.getFloat("vl_despesa"));
			d.setObservacao(rs.getString("ds_observacao"));

			lstDespesaCodigo.add(d);

		}
		rs.close();
		stmt.close();
		return lstDespesaCodigo;

	}

	/**
	 * @author Gabriel Manzini
	 * @category Java Method
	 * @param c
	 * @return mes
	 * @throws Exception
	 */
	public String getMes(Connection c) throws Exception {

		Date data = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		String mes = String
				.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);

		return mes;

	}

	/**
	 * @author Victor Luiz
	 * @category Java Method
	 * @param c
	 * @param cdLancamento
	 * @throws Exception
	 */
	public void deletarDespesa(Connection c, int cdLancamento) throws Exception {
		String sql = "delete from T_AM_ERR_LANCA_DESPESA where cd_lancamento = "
				+ cdLancamento;
		stmt = c.prepareStatement(sql);
		stmt.execute();
		stmt.close();
	}

	/**
	 * @author Victor Luiz
	 * @category Java Method
	 * @param c
	 * @param d
	 * @return saida
	 * @throws Exception
	 */

	public int atualizar(Connection c, Despesa d) throws Exception {
		String sql = "update T_AM_ERR_LANCA_DESPESA SET DT_DESPESA = TO_DATE(? , 'YYYY-MM-DD'),VL_DESPESA = ?,DS_OBSERVACAO = ? "
				+ " where cd_lancamento = ?";
		stmt = c.prepareStatement(sql);
		stmt.setString(1, d.getDtDespesa());
		stmt.setFloat(2, d.getValor());
		stmt.setString(3, d.getObservacao());
		stmt.setInt(4, d.getCodigoLancamento());

		int saida = stmt.executeUpdate();
		stmt.close();

		return saida;

	}

	/**
	 * @author Gabiel Manzini
	 * @category Java Method
	 * @param c
	 * @return diaNovo
	 * @throws Exception
	 */
	public int getDia(Connection c) throws Exception {
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String dia = String.valueOf(Calendar.getInstance().get(
				Calendar.DAY_OF_MONTH));
		int diaNovo = Integer.parseInt(dia);

		return diaNovo;

	}

	/**
	 * @author Victor Luiz
	 * @category Java Method
	 * @param c
	 * @return mes
	 * @throws Exception
	 */
	public int getMesInt(Connection c) throws Exception {
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String mesint = String.valueOf(Calendar.getInstance().get(
				Calendar.MONTH) + 1);
		int mes = Integer.parseInt(mesint);

		return mes;

	}
	/**
	 * @author Victor Luiz
	 * @category Java Mehtod
	 * @param c
	 * @return anoInt
	 * @throws Exception
	 */

	public int getAno(Connection c) throws Exception {
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String ano = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		int anoInt = Integer.parseInt(ano);

		return anoInt;

	}

}
