package model;

import java.io.Serializable;

public class Animal implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private int idade;
	private String porte;
	private String especie;
	private String local;



	
	public Animal() {
		id = -1;
		nome = "";
		idade = 0;
		porte = "";
		porte = "especie";

	}

	public Animal(int id, String nome, int idade, String porte, String especie) {
		setId(id);
		setNome(nome);
		setIdade(idade);
		setPorte(porte);
		setEspecie(especie);

	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String getPorte() {
		return porte;
	}
	
	public void setPorte(String porte) {
		this.porte = porte;
	}
	
	public String getEspecie() {
		return especie;
	}
	
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
	public String getLocal(){
		return local;
	}

	public void setLocal(String local){
		this.local = local;
	}


	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Animal "+id+": "+nome;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Animal) obj).getId());
	}	
}