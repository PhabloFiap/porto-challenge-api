package br.com.fiap.porto.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class Veiculo {

	private Long id;
	
	@NotBlank(message = "nome do veiculo nao pode estar em branco")
	private String modelo;
	
	@NotNull(message ="altura nao pode estar em branco")
	private float altura;
	
	@PastOrPresent(message ="O ano do veiculo nao deve ser maior que o ano atual")
	private LocalDate ano;
	
	@NotNull(message ="peso nao pode estar em branco")
	private float peso;
	
	@NotNull(message ="largura nao pode estar em branco")
	private float largura;
	
	@NotNull(message ="comprimento nao pode estar em branco")
	private float comprimento;
	
	@NotBlank(message = "categoria nao pode estar em branco")
	private String categoria;

	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public LocalDate getAno() {
		return ano;
	}

	public void setAno(LocalDate ano) {
		this.ano = ano;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getLargura() {
		return largura;
	}

	public void setLargura(float largura) {
		this.largura = largura;
	}

	public float getComprimento() {
		return comprimento;
	}

	public void setComprimento(float comprimento) {
		this.comprimento = comprimento;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Veiculo() {
		super();
	}

	public Veiculo(Long id, @NotBlank(message = "nome do veiculo nao pode estar em branco") String modelo,
			@NotNull(message = "altura nao pode estar em branco") float altura,
			@PastOrPresent(message = "O ano do veiculo nao deve ser maior que o ano atual") LocalDate ano,
			@NotNull(message = "peso nao pode estar em branco") float peso,
			@NotNull(message = "largura nao pode estar em branco") float largura,
			@NotNull(message = "comprimento nao pode estar em branco") float comprimento,
			@NotBlank(message = "categoria nao pode estar em branco") String categoria) {
		this.id = id;
		this.modelo = modelo;
		this.altura = altura;
		this.ano = ano;
		this.peso = peso;
		this.largura = largura;
		this.comprimento = comprimento;
		this.categoria = categoria;
	}

	

}
