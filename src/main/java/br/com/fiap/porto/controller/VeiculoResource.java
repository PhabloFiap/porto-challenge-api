package br.com.fiap.porto.controller;

import java.net.URI;
import java.util.List;

import br.com.fiap.porto.model.Cliente;
import br.com.fiap.porto.model.Veiculo;
import br.com.fiap.porto.model.repository.ClienteRepository;
import br.com.fiap.porto.model.repository.VeiculoRepository;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.UriBuilder;

@Path("/veiculo")
public class VeiculoResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		List<Veiculo> retorno = VeiculoRepository.findAll();
		ResponseBuilder response = Response.ok();
		response.entity(retorno);

		return response.build();

	}

	@POST
	//@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response salva(@Valid Veiculo veiculo) {
		Veiculo resp = VeiculoRepository.salva(veiculo);
		final URI veiculoURI = UriBuilder.fromResource(VeiculoResource.class).path("/veiculo/(id)").build(resp.getId());
		ResponseBuilder response = Response.created(veiculoURI);
		
		response.entity(resp);

		return response.build();
		
	}
	
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long veiculoId) {

		if (VeiculoRepository.delete(veiculoId)) {
			ResponseBuilder response = Response.noContent();
			return response.build();

		} else {
			System.out.println("nao foi possivel remover" + veiculoId);
			ResponseBuilder response = Response.status(404);
			return response.build();
		}

	}
	

	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") Long veiculoId) {
		Veiculo veiculo= VeiculoRepository.findById(veiculoId);
		if (veiculo != null) {
			ResponseBuilder response = Response.ok();
			response.entity(veiculo);
			return response.build();

		} else {
			ResponseBuilder response = Response.noContent();
			return response.build();
		}
	}
	
	@PUT
	@Path("/{id}")
	public Response atualiza(@PathParam("id") Long veiculoId,  @Valid Veiculo veiculo) {

		Veiculo velho = VeiculoRepository.findById(veiculoId);
		Veiculo novo = null;
		if (velho == null || velho.getId() != veiculo.getId()) {
			novo = VeiculoRepository.salva(veiculo);

			final URI veiculoUri = UriBuilder.fromResource(VeiculoResource.class).path("/veiculo/{id}").build(novo.getId());

			ResponseBuilder response = Response.created(veiculoUri);
			response.entity(novo);
			return response.build();

		}

		novo = VeiculoRepository.atualiza(veiculo);
		return Response.ok(novo).build();

	}
	
	
}
