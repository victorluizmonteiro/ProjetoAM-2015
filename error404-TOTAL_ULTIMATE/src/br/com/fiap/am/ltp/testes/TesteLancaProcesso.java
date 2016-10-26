package br.com.fiap.am.ltp.testes;

import java.sql.Connection;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.fiap.am.ltp.beans.Processo;
import br.com.fiap.am.ltp.bo.ProcessoBO;
import br.com.fiap.am.ltp.conexao.ConexaoFactory;

public class TesteLancaProcesso {

	public static void main(String[] args) throws Exception{
		Connection c = null;
		
		try{
			c= ConexaoFactory.controlarInstancia().getConnection("OPS$RM74645", "130697");
			int numOAB = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da OAB"));
			List<Processo>lstProcessoAberto = ProcessoBO.listarProcessoAberto(c,numOAB);
			List<Processo>lstProcessoFechado = ProcessoBO.listarProcessoFechado(c,numOAB);
			
			System.out.println("========== Processos em aberto ==========");
			System.out.println("");
			for(Processo p : lstProcessoAberto){
				System.out.println("Numero do processo :" + p.getNumProcesso()+ "\nDescri��o :" + p.getDescricao()
						+ "\nRaz�o Social : " + p.getCliente().getRazaoSocial() + "\nDescri��o da causa :" + p.getCausa().getCausa()
						+ "\nObserva��o : " + p.getObservacao());
				
			}
			
			System.out.println("========== Processos fechados ==========");
			System.out.println("");
			
			for(Processo p : lstProcessoFechado){
				System.out.println("Numero do processo :" + p.getNumProcesso() + "\nC�digo do advogado : "+ p.getDescricao()
						+"\nRaz�o Social :" + p.getCliente().getRazaoSocial() + "\nDescri��o da causa :" + p.getCausa().getCausa()
						+"\nObserva��o : " + p.getObservacao());
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
		}
		

	}

}
