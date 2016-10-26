package br.com.fiap.am.ltp.beans;

public class Advogado extends Pessoa{
	private Pessoa cdAdvogado;
	private int numOAB;
	private String cpf;
	private String rg;
	private String email;
	private String password;
	public Advogado() {
		super();
		
	}
	public Advogado(int codigoPessoa, String nome, Pessoa cdAdvogado,
			int numOAB, String cpf, String rg, String email, String password) {
		super(codigoPessoa, nome);
		this.cdAdvogado = cdAdvogado;
		this.numOAB = numOAB;
		this.cpf = cpf;
		this.rg = rg;
		this.email = email;
		this.password = password;
	}
	public Pessoa getCdAdvogado() {
		return cdAdvogado;
	}
	public void setCdAdvogado(Pessoa cdAdvogado) {
		this.cdAdvogado = cdAdvogado;
	}
	public int getNumOAB() {
		return numOAB;
	}
	public void setNumOAB(int numOAB) {
		this.numOAB = numOAB;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
