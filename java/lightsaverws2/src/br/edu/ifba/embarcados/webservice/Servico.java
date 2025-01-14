package br.edu.ifba.embarcados.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.ifba.embarcados.webservice.sensoriamento.LeitorSensoriamento;

@Path("sw")
public class Servico {

	@GET
	@Path("/id/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getId() {
		return LeitorSensoriamento.getRFID() + "";
	}

	@GET
	@Path("/sensores")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSensores() {
		return LeitorSensoriamento.getSensores() + "";
	}

	@GET
	@Path("/sensores/luminosidade")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMovimento() {
		return LeitorSensoriamento.getLuminosidade() + "";
	}

	@GET
	@Path("/sensores/distancia")
	@Produces(MediaType.TEXT_PLAIN)
	public String getBatimentos() {
		return LeitorSensoriamento.getDistancia() + "";
	}

	@GET
	@Path("/sensores/temperatura")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTemperatura() {
		return LeitorSensoriamento.getTemperatura() + "";
	}
	
}
