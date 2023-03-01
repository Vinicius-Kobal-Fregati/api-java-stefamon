package com.stefanini.resources;

import com.stefanini.dto.CompraStefamonDTO;
import com.stefanini.dto.JogadorCadastroDTO;
import com.stefanini.entities.Jogador;
import com.stefanini.services.JogadorService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/jogador")
public class JogadorResource {

    @Inject
    JogadorService jogadorService;

    @GET
    @Path("/{id}")
    public Response pegarPorId(@PathParam("id") Long id){
        return Response.status(Response.Status.OK).entity(jogadorService.pegarPorId(id)).build();
    }

    @GET
    @Path("/todos")
    public Response listarTodos(){
        return Response.status(Response.Status.OK).entity(jogadorService.listarTodos()).build();
    }

    @POST
    public Response salvar(@Valid JogadorCadastroDTO jogador) {
        jogadorService.salvar(jogador);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    public Response alterar(@Valid Jogador jogador) {
        jogadorService.alterar(jogador);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        jogadorService.deletar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Path("/login")
    public Response loginDoJogador(@Valid JogadorCadastroDTO jogador) {
        return Response.status(Response.Status.OK).entity(jogadorService.loginDoJogador(jogador)).build();
    }

    @PUT
    @Path("/compra")
    public Response loginDoJogador(CompraStefamonDTO compra) {
        jogadorService.compraStefamon(compra);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
