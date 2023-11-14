package br.com.fiap.porto.model.repository;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.com.fiap.porto.model.Cliente;
import br.com.fiap.porto.model.Veiculo;
import jakarta.validation.Valid;

public class ClienteRepository extends Repository {

	private static List<Cliente> clientes = null;

	public static List<Cliente> findAll() {

		String sql = "SELECT * FROM cliente";

		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Cliente> clientes = new ArrayList<>();

		try {
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Cliente cliente= new Cliente();
					cliente.setId(rs.getLong("id"));
					cliente.setNomeCliente("nomecliente");
					cliente.setCpf("cpf");
					cliente.setRg("rg");
					cliente.setDtCadastro(rs.getDate("dtcadastro").toLocalDate());
					
					
					

					clientes.add(cliente);
				}

			} 

			 else {
				System.out.println("nao foram encontrados registro no banco de dados");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return clientes;
	}

	public static Cliente salva(Cliente cliente) {

		String sql = "begin insert into cliente (id, nomecliente, cpf,rg, dtcadastro) values (SEQ_CLIENTE_PORTO.nextval, ?, ?, ?, ?)"
				+ "return id into ?; end;";

		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);
			cs.setString(1,cliente.getNomeCliente());
			cs.setString(2,cliente.getCpf());
			cs.setString(3,cliente.getRg());
			cs.setDate(4, Date.valueOf(cliente.getDtCadastro()));
			cs.registerOutParameter(5, java.sql.Types.INTEGER);
			cs.executeUpdate();
			cliente.setId(Long.valueOf(cs.getInt(5)));
			
			
//			cs.setString(6, cliente.getVeiculo().getCategoria());

			return cliente;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (cs != null)
				try {
					cs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;

	}

	public static boolean delete(Long clienteID) {

		Cliente cliente = null;
		String sql = "delete from cliente where id = ?";
		PreparedStatement ps = null;
		cliente = findById(clienteID);

		if (cliente == null) {
			return false;

		}

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setLong(1, clienteID);
			ps.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println("erro pra deletar no banco" + e.getMessage());

		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return false;
	}
	
	public static Cliente findById(Long id) {
		String sql = "select * from cliente where id = ?";

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setLong(1, id);
			
			rs = ps.executeQuery();
			
			if (rs.isBeforeFirst()) {
				
				Cliente cliente = new Cliente();
				
				while(rs.next()) {
					
					cliente.setId(rs.getLong("ID"));
					cliente.setNomeCliente(rs.getString("nomeCliente"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setRg(rs.getString("rg"));
					cliente.setDtCadastro(rs.getDate("dtCadastro").toLocalDate());
					

				}
				return cliente;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		finally 
				{
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		return null;

	}

		public static Cliente atualiza(@Valid Cliente cliente) {
			String sql = "update cliente set nomecliente=?, cpf=?, rg=?, dtcadastro=? where id =?";
			CallableStatement cs = null;
			try {
				cs = getConnection().prepareCall(sql);
				cs.setString(1, cliente.getNomeCliente());
				cs.setString(2, cliente.getCpf());
				cs.setString(2, cliente.getRg());
				cs.setDate(4, Date.valueOf(cliente.getDtCadastro()));
	
				
				cs.executeUpdate();
				
				return cliente;
				
				
				
				
			} catch (SQLException e) {

				System.out.println("Erro ao atualizar o bd" +e.getMessage());
			}
			
			finally {
				if (cs != null)
					try {
						cs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
		
		return null;
	}
	

}
