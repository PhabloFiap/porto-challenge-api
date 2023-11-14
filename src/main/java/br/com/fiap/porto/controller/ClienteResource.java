package br.com.fiap.porto.controller;

import java.net.URI;
import java.util.List;


import br.com.fiap.porto.controller.ClienteResource;

import br.com.fiap.porto.model.Cliente;
import br.com.fiap.porto.model.repository.ClienteRepository;
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

@Path("/cliente")
public class ClienteResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		List<Cliente> retorno = ClienteRepository.findAll();
		ResponseBuilder response = Response.ok();
		response.entity(retorno);

		return response.build();

	}
//	@Path("/clientes")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response findAllCliente() {
//		List<Cliente> retorno = ClienteRepository.findAllCliente();
//		ResponseBuilder response = Response.ok();
//		response.entity(retorno);
//
//		return response.build();
//
//	}

	@POST
	//@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response salva( Cliente cliente) {
		Cliente resp = ClienteRepository.salva(cliente);
		final URI clienteURI = UriBuilder.fromResource(ClienteResource.class).path("/cliente/(id)").build(resp.getId());
		ResponseBuilder response = Response.created(clienteURI);
		
		response.entity(resp);

		return response.build();
		
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long clienteId) {

		if (ClienteRepository.delete(clienteId)) {
			ResponseBuilder response = Response.noContent();
			return response.build();

		} else {
			System.out.println("nao foi possivel remover" + clienteId);
			ResponseBuilder response = Response.status(404);
			return response.build();
		}

	}
	
	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") Long clienteId) {
		Cliente cliente = ClienteRepository.findById(clienteId);
		if (cliente != null) {
			ResponseBuilder response = Response.ok();
			response.entity(cliente);
			return response.build();

		} else {
			ResponseBuilder response = Response.noContent();
			return response.build();
		}
	}
	
	@PUT
	@Path("/{id}")
	public Response atualiza(@PathParam("id") Long clienteID,  @Valid Cliente cliente) {

		Cliente velho = ClienteRepository.findById(clienteID);
		Cliente novo = null;
		if (velho == null || velho.getId() != cliente.getId()) {
			novo = ClienteRepository.salva(cliente);

			final URI clienteUri = UriBuilder.fromResource(ClienteResource.class).path("/cliente/{id}").build(novo.getId());

			ResponseBuilder response = Response.created(clienteUri);
			response.entity(novo);
			return response.build();

		}

		novo = ClienteRepository.atualiza(cliente);
		return Response.ok(novo).build();

	}
	
	
	
}
