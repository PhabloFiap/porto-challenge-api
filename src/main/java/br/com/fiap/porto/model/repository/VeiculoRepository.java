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

public class VeiculoRepository extends Repository {

	private static List<Veiculo> veiculo = null;

	public static List<Veiculo> findAll() {

		String sql = "SELECT * FROM Veiculo";

		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Veiculo> veiculos = new ArrayList<>();

		try {
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					Veiculo veiculo = new Veiculo();
					veiculo.setId(rs.getLong("id"));
					veiculo.setModelo(rs.getString("modelo"));
					veiculo.setAltura(rs.getFloat("altura"));
					veiculo.setAno(rs.getDate("ANO").toLocalDate());
					veiculo.setPeso(rs.getFloat("peso"));
					veiculo.setLargura(rs.getFloat("largura"));
					veiculo.setComprimento(rs.getFloat("Comprimento"));
					veiculo.setCategoria(rs.getString("categoria"));
					
					

					veiculos.add(veiculo);
				}

			} else {
				System.out.println("nao foram encontrados registro no banco de dados");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return veiculos;
	}

	public static Veiculo salva(Veiculo veiculo) {

		String sql = "begin insert into veiculo (id, modelo, altura, ANO, peso, largura, Comprimento, categoria) values (SEQ_veiculo_PORTO.nextval, ?, ?, ?, ?, ?, ?, ?)"
				+ "return id into ?; end;";

		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);

			cs.setString(1, veiculo.getModelo());
			cs.setFloat(2, veiculo.getAltura());
			cs.setDate(3, Date.valueOf(veiculo.getAno()));
			cs.setFloat(4, veiculo.getPeso());
			cs.setFloat(5, veiculo.getLargura());
			cs.setFloat(6, veiculo.getComprimento());
			cs.setString(7, veiculo.getCategoria());
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.executeUpdate();
			veiculo.setId(Long.valueOf(cs.getInt(8)));

			return veiculo;

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

	public static boolean delete(Long veiculoId) {
		Veiculo veiculo = null;
		String sql = "delete from veiculo where id = ?";
		PreparedStatement ps = null;
		veiculo = findById(veiculoId);

		if (veiculo == null) {
			return false;

		}

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setLong(1, veiculoId);
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

	public static Veiculo findById(Long id) {
		String sql = "select * from veiculo where id = ?";

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			ps = getConnection().prepareStatement(sql);
			ps.setLong(1, id);
			
			rs = ps.executeQuery();
			
			if (rs.isBeforeFirst()) {
				
				Veiculo veiculo= new Veiculo();
				
				while(rs.next()) {
					
					veiculo.setId(rs.getLong("ID"));
					veiculo.setModelo(rs.getString("modelo"));
					veiculo.setAltura(rs.getFloat("altura"));
					veiculo.setAno(rs.getDate("ano").toLocalDate());
					veiculo.setPeso(rs.getFloat("peso"));
					veiculo.setLargura(rs.getFloat("largura"));
					veiculo.setComprimento(rs.getFloat("comprimento"));
					veiculo.setCategoria(rs.getString("categoria"));
					

				}
				return veiculo;
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

	public static Veiculo atualiza(@Valid Veiculo veiculo) {
		String sql = "update veiculo set modelo=?, altura=?, ano=?, peso=?, largura=?,comprimento=?, categoria=? where id =?";
		CallableStatement cs = null;
		try {
			cs = getConnection().prepareCall(sql);
			cs.setString(1, veiculo.getModelo());
			cs.setFloat(2, veiculo.getAltura());
			cs.setDate(3, Date.valueOf(veiculo.getAno()));
			cs.setFloat(4,veiculo.getPeso());
			cs.setFloat(5,veiculo.getLargura());
			cs.setFloat(6,veiculo.getComprimento());
			cs.setString(7,veiculo.getCategoria());
			
			cs.executeUpdate();
			
			return veiculo;
			

			
			
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
