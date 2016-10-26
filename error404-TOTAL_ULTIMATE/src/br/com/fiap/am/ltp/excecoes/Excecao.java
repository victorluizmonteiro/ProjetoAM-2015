package br.com.fiap.am.ltp.excecoes;
// Classe que direciona excecoes para um classe filha da clase Pai Exception
//Podem criar suas exce�es
public class Excecao extends Exception {
	public Excecao(String mensagem, Exception e){
		if(e.getClass().toString().equalsIgnoreCase("class java.lang.NullPointerException")){
			System.out.println("Obejeto n�o encontrado!!");
		}
		
		
		if(e.getClass().toString().equals("class java.sql.SQLException")){
			System.out.println("Problmas com banco de dados");
			
		}
		if(e.getClass().toString().equalsIgnoreCase("class java.awt.FontFormatException")){
			System.out.println("Fonte n�o compativel");
		}else{
			System.out.println("Erro desconhecido");
			
		}
	}
	
	public Excecao(String m){
		System.out.println(m);
	}

}
