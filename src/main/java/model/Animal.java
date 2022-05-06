package model;

import java.io.Serializable;

public class Animal implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String idade;
	private String porte;
	private String especie;
	private String local;
	private String contato;
	private String foto;

	public Animal() {
		id = -1;
		nome = "";
		idade = "0";
		porte = "";
		porte = "especie";
		local = "";
		contato = "";
		foto = "";

	}

	public Animal(int id, String nome, String idade, String porte, String especie, String local, String contato,
			String foto) {
		setId(id);
		setNome(nome);
		setIdade(idade);
		setPorte(porte);
		setEspecie(especie);
		setLocal(local);
		setContato(contato);
		setFoto(foto);

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

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
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

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Animal " + id + ": " + nome;
	}

	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Animal) obj).getId());
	}
}