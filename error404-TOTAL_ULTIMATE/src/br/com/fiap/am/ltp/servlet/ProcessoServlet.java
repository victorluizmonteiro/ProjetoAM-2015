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

import br.com.fiap.am.ltp.beans.Advogado;
import br.com.fiap.am.ltp.beans.Processo;
import br.com.fiap.am.ltp.bo.AdvogadoBO;
import br.com.fiap.am.ltp.bo.ProcessoBO;
import br.com.fiap.am.ltp.conexao.ConexaoFactory;
import br.com.fiap.am.ltp.excecoes.Excecao;
@WebServlet("/Index")
public class ProcessoServlet extends HttpServlet{
	
	Connection c= null;
	boolean acesso = false;
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		try{
			acessar(req);
			listarProcessos(req);
			if( acesso == true){
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			}else{
				req.getRequestDispatcher("acesso.jsp").forward(req, resp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
			
		
		
	


}
	public void acessar(HttpServletRequest req) throws Exception{
		try{
			c = ConexaoFactory.controlarInstancia().getConnection("OPS$RM74645", "130697");
			List<Advogado> lstAdvogado = new ArrayList<Advogado>();
		
			int numOAB = Integer.parseInt(req.getParameter("numOAB"));
			req.setAttribute("numOAB", numOAB);
			lstAdvogado = AdvogadoBO.listaAdvogado(c, numOAB);
			
			if (!lstAdvogado.isEmpty()){
				req.setAttribute("codigo", lstAdvogado.get(0).getCodigoPessoa());
				acesso = true;
				
			}else{
				req.setAttribute("error", "Número da OAB inexistente!");
				acesso = false;
			}
			
		}catch (Exception e){
			req.setAttribute("error", e.getMessage());
			
		}finally{
			try{
				c.close();
			}catch(Exception e){
				e.printStackTrace();
				throw new Excecao("Um erro foi encontrado,contate os Administradores");
			}
		}
		
	}
	public void listarProcessos(HttpServletRequest req) throws Exception{
		try{
			int numOAB = Integer.parseInt(req.getParameter("numOAB"));
			c = ConexaoFactory.controlarInstancia().getConnection("OPS$RM74645", "130697");
			
			List<Processo> lstProcessoFechado = new ArrayList<Processo>();
			List<Processo> lstProcessoAberto = new ArrayList<Processo>();
			lstProcessoFechado = ProcessoBO.listarProcessoFechado(c,numOAB);
			lstProcessoAberto = ProcessoBO.listarProcessoAberto(c,numOAB);
			
			req.setAttribute("ProcessosFechados", lstProcessoFechado);
			req.setAttribute("ProcessosAbertos", lstProcessoAberto);
			
		}catch (Exception e){
			req.setAttribute("error", e.getMessage());
		}finally{
			try{
				c.close();
			}catch(Exception e){
				e.printStackTrace();
				throw new Excecao("Um erro foi encontrado");
				
				
			}
		}
	}
}
