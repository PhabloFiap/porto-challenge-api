package br.com.fiap.porto.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public class Cliente {

	private Long id;

	@NotBlank(message = "nome do cliente nao pode estar em branco")
	private String nomeCliente;
	@NotBlank(message = "cpf do cliente nao pode ser vazio")
	private String cpf;
	@NotBlank(message = "cpf do cliente nao pode ser vazio")
	private String rg;
	@PastOrPresent(message = "data de cadastro deve ser menor ou igual a hoje")
	private LocalDate dtCadastro;

	private Veiculo veiculo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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

	public LocalDate getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(LocalDate dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Cliente() {
		super();
	}

	public Cliente(Long id, @NotBlank(message = "nome do cliente nao pode estar em branco") String nomeCliente,
			@NotBlank(message = "cpf do cliente nao pode ser vazio") String cpf,
			@NotBlank(message = "cpf do cliente nao pode ser vazio") String rg,
			@PastOrPresent(message = "data de cadastro deve ser menor ou igual a hoje") LocalDate dtCadastro,
			Veiculo veiculo) {
		super();
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.cpf = cpf;
		this.rg = rg;
		this.dtCadastro = dtCadastro;
		this.veiculo = veiculo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, dtCadastro, id, nomeCliente, rg, veiculo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(dtCadastro, other.dtCadastro)
				&& Objects.equals(id, other.id) && Objects.equals(nomeCliente, other.nomeCliente)
				&& Objects.equals(rg, other.rg) && Objects.equals(veiculo, other.veiculo);
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nomeCliente=" + nomeCliente + ", cpf=" + cpf + ", rg=" + rg + ", dtCadastro="
				+ dtCadastro + ", veiculo=" + veiculo + "]";
	}

}
