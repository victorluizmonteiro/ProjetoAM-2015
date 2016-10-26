package br.com.fiap.am.ltp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.crypto.provider.DESedeParameters;

import br.com.fiap.am.ltp.beans.Despesa;
import br.com.fiap.am.ltp.beans.Processo;
import br.com.fiap.am.ltp.beans.TipoDepesas;
import br.com.fiap.am.ltp.bo.LancaDespesaBO;
import br.com.fiap.am.ltp.conexao.ConexaoFactory;
import br.com.fiap.am.ltp.excecoes.Excecao;

@WebServlet("/Controller")
public class ControllerServlet extends HttpServlet {
	// String endereco =
	// getServletContext().getContext("/error404").getRealPath("/");

	static Connection c = null;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			c = ConexaoFactory.controlarInstancia().getConnection("OPS$RM74645", "130697");
			int numProcesso = Integer.parseInt(req.getParameter("num"));
			req.setAttribute("numProcesso", numProcesso);
			
			int numOAB = Integer.parseInt(req.getParameter("numOAB"));
			req.setAttribute("numOAB", numOAB);
			String acao = req.getParameter("acao");
			
			
			
			switch (acao) {
			case "deletar":
				deletar(req);
				listarDespesas(req);
				listarTipoDespesa(req);
				break;
			case "Cadastrar" :
				cadastrarDespesa(req);
				listarDespesas(req);
				listarTipoDespesa(req);
			
			break;
			case "alterar":
				listarDespesas(req);
				listarTipoDespesa(req);
				listarDespesaCodigo(req);
				break;
			case "Atualizar":
				atualizarDespesa(req);
				listarDespesas(req);
				listarTipoDespesa(req);
			}
			

		} catch (Exception e) {
			req.setAttribute("error", e.getMessage());
			
		} finally {
			
			req.getRequestDispatcher("Despesa.jsp").forward(req, resp);
			try {
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			c = ConexaoFactory.controlarInstancia().getConnection("OPS$RM74645", "130697");

			int numOAB = Integer.parseInt(req.getParameter("numOAB"));
			req.setAttribute("numOAB", numOAB);
			String acao = req.getParameter("acao");
			String retorno = "";
			switch (acao) {
			case "listar":
				listarTipoDespesa(req);
				listarDespesas(req);
				retorno = "Despesa.jsp";
				break;
				
			case "alterar":
				listarDespesas(req);
				listarTipoDespesa(req);
				listarDespesaCodigo(req);
				retorno = "Despesa.jsp";

			}

			req.getRequestDispatcher(retorno).forward(req, resp);

			/*
			 * int numProcesso = Integer.parseInt(req.getParameter("num"));
			 * 
			 * // Listar tipo despesa List<TipoDepesas> lstTipoDespesas = new
			 * ArrayList<TipoDepesas>(); lstTipoDespesas =
			 * LancaDespesaBO.listarTipoDespesas(c);
			 * 
			 * req.setAttribute("TipoDespesas", lstTipoDespesas);
			 * 
			 * // Listar Despesas List<Despesa> lstDespesa = new
			 * ArrayList<Despesa>(); lstDespesa =
			 * LancaDespesaBO.listarDespesas(c, numProcesso);
			 * 
			 * req.setAttribute("despesas", lstDespesa);
			 * 
			 * 
			 * // Passar o numero do processo para o jsp
			 * req.setAttribute("numProcesso", numProcesso);
			 * 
			 * listarDespesas(req);
			 * 
			 * 
			 * 
			 * } catch (Exception e) { e.printStackTrace();
			 * req.setAttribute("error", e.getMessage()); }
			 * 
			 * req.getRequestDispatcher(	"Despesa.jsp").forward(req, resp);
			 */
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		} finally {
			try {
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void listarTipoDespesa(HttpServletRequest req) {
		try {

			List<TipoDepesas> lstTipoDespesas = new ArrayList<TipoDepesas>();
			lstTipoDespesas = LancaDespesaBO.listarTipoDespesas(c);

			req.setAttribute("TipoDespesas", lstTipoDespesas);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		}

	}

	public void listarDespesas(HttpServletRequest req) {
		try {
			int numProcesso = Integer.parseInt(req.getParameter("num"));
			List<Despesa> lstDespesa = new ArrayList<Despesa>();
			lstDespesa = LancaDespesaBO.listarDespesas(c, numProcesso);

			req.setAttribute("despesas", lstDespesa);
			// Passar o numero do processo para o jsp
			req.setAttribute("numProcesso", numProcesso);

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		}
	}

	public void deletar(HttpServletRequest req) {
		try {
			int codLancamento = Integer.parseInt(req
					.getParameter("codLancamento"));
			LancaDespesaBO.deletarDespesa(c, codLancamento);

			req.setAttribute("msg", "Despesa deletada com sucesso !");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		}

	}
	
	public void cadastrarDespesa(HttpServletRequest request) throws Exception{
		try{
			
			int numProcesso = Integer.parseInt(request.getParameter("num"));
			request.setAttribute("numProcesso", numProcesso);
			
			
			int numOAB = Integer.parseInt(request.getParameter("numOAB"));
			request.setAttribute("numOAB", numOAB);
			
			
			String dataOcorrencia = request.getParameter("dtOcorreDespesa");
			
			String dia ="";
			String ano="";
			if (dataOcorrencia.indexOf('/') >= 0){
				dia = dataOcorrencia.substring(0,2);
				String mes = dataOcorrencia.substring(3,5);
				ano	= dataOcorrencia.substring(6,10);
				dataOcorrencia = ano + "-" + mes + "-" + dia;
			}
			dia = dataOcorrencia.substring(8,10);
			ano= dataOcorrencia.substring(0,4);
			
			
			String dataValida = dataOcorrencia.trim().substring(5, 7);
			
			int ano1= Integer.parseInt(ano);
			
			int dia1= Integer.parseInt(dia);
		
		
		
		TipoDepesas tp = new TipoDepesas();
		tp.setCodigoDespesa(Integer.parseInt(request.getParameter("slcDespesa")));
		
		Processo p = new Processo();
		p.setNumProcesso(Integer.parseInt(request.getParameter("num")));
		
		
		Despesa d = new Despesa();
		d.setTipoDespesa(tp);
		d.setNrProcesso(p);
		d.setDtDespesa(dataOcorrencia);
		
		
		String valor = request.getParameter("txtValor");
		while(valor.indexOf(',')>=0){
			valor = valor.replace(",", "");
		}
		d.setValor(Float.parseFloat(valor));
		d.setObservacao(request.getParameter("txtHObs"));
		
		
		
		LancaDespesaBO.cadastrarDespesa(c, d, dataValida,dia1,ano1);
		
		request.setAttribute("msg", "Cadastrado com sucesso!");
		
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		
	}
	public void listarDespesaCodigo(HttpServletRequest req)throws Exception{
		int cdLancamento =Integer.parseInt(req.getParameter("codLancamento"));
		int numProcesso = Integer.parseInt(req.getParameter("num"));
		int numOAB = Integer.parseInt(req.getParameter("numOAB"));
		req.setAttribute("numOAB", numOAB);
		List<Despesa>lstDespesas = new ArrayList<Despesa>();
		lstDespesas = LancaDespesaBO.listarDespesaCodigo(c, cdLancamento);
		//Recuperando parametros
		
		int codigo = lstDespesas.get(0).getCodigoLancamento();
		int codDespesa = lstDespesas.get(0).getTipoDespesa().getCodigoDespesa();
		String dataOcorrencia = lstDespesas.get(0).getDtDespesa();
		float valorDespesa = lstDespesas.get(0).getValor();
		String obs = lstDespesas.get(0).getObservacao();
		
		//Passando valores para o jsp
		req.setAttribute("numProcesso", numProcesso);
		req.setAttribute("codigoDespesa", codDespesa);
		req.setAttribute("data", dataOcorrencia);
		req.setAttribute("valorDespesa", valorDespesa);
		req.setAttribute("obs", obs);
		req.setAttribute("codLancamento",codigo);
		req.setAttribute("regra", "disabled");
		req.setAttribute("tipo", "Atualizar");
		
		
		
	}
	
	public void atualizarDespesa(HttpServletRequest req)throws Exception{
		
		try{
			int numOAB = Integer.parseInt(req.getParameter("numOAB"));
			int numProcesso = Integer.parseInt(req.getParameter("num"));
			
			int cdLancamento =Integer.parseInt(req.getParameter("cdLancamento"));
			
			String dataOcorrencia = req.getParameter("dtOcorreDespesa");
			
			String dia ="";
			String ano = "";
			if (dataOcorrencia.indexOf('/') >= 0){
				 dia = dataOcorrencia.substring(0,2);
				String mes = dataOcorrencia.substring(3,5);
				 ano = dataOcorrencia.substring(6,10);
				dataOcorrencia = ano + "-" + mes + "-" + dia;
			}
			dia = dataOcorrencia.substring(8,10);
			ano= dataOcorrencia.substring(0,4);
			
			String dataValida = dataOcorrencia.trim().substring(5, 7);
			
					
			String valor = req.getParameter("txtValor");
			while(valor.indexOf(',')>=0){
				valor = valor.replace(",", "");
			}
			
			float valorDespesa = Float.parseFloat(valor);
			
			String obs = req.getParameter("txtHObs");
			TipoDepesas td = new TipoDepesas();
			
			
			Processo  p = new Processo();
			p.setNumProcesso(numProcesso);
			
			Despesa d = new Despesa();
			d.setCodigoLancamento(cdLancamento);
			d.setDtDespesa(dataOcorrencia);
			d.setNrProcesso(p);
			d.setObservacao(obs);
			d.setTipoDespesa(td);
			d.setValor(valorDespesa);
			
			
			
			int ano1= Integer.parseInt(ano);
			
			int dia1= Integer.parseInt(dia);
			
			LancaDespesaBO.atualiza(c, d,dataValida,ano1,dia1);
			req.setAttribute("numOAB", numOAB);
			req.setAttribute("numProcesso", numProcesso);
			req.setAttribute("msg", "Despesa do processo  " + numProcesso + "  alterada com sucesso!");
			
			
		}catch(Exception e){
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		}

	}
}
