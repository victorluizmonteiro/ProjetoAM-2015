package br.com.fiap.am.ltp.testes;

import java.sql.Connection;

import br.com.fiap.am.ltp.conexao.ConexaoFactory;
import br.com.fiap.am.ltp.excecoes.Excecao;

public class TestaConexao {

	public static void main(String[] args) throws Excecao {

		Connection c = null;

		try {

			String usuario = "OPS$RM74645";
			String senha = "130697";

			c = ConexaoFactory.controlarInstancia().getConnection(usuario, senha);
			System.out.println("Conexao aberta!");

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				c.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

}
