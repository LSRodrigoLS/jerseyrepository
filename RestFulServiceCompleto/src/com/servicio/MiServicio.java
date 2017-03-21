package com.servicio;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dao.EmpleadoDAO;
import model.Empleado;

@Path("/empleado")
public class MiServicio {
	
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public String HolaMundo(){
		EmpleadoDAO dao = new EmpleadoDAO();
		Gson json = new Gson();
		String resultado = json.toJson(dao.listarEmpleados(),ArrayList.class);
		return resultado;
	}
	
	@POST
	@Path("/registrar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String registrar(String empleado){
		String respuesta = "";
		try {		
			ObjectMapper mapper = new ObjectMapper();
			Empleado emple = mapper.readValue(empleado, Empleado.class);
			int resultado = new EmpleadoDAO().registrar(emple);
			if (resultado == 1) {
				respuesta =  "{\"id\":46,\"resultado\":\"Bien\"}";
			} else {
				respuesta =  "{\"id\":47,\"resultado\":\"Mal\"}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			respuesta = e.getMessage();
		}
		return respuesta;
	}
	
	@POST
	@Path("/actualizar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String actualizar(String empleado){
		String respuesta = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
			Empleado emple = mapper.readValue(empleado, Empleado.class);
			int resultado = new EmpleadoDAO().actualizar(emple);
			if (resultado == 1) {
				respuesta =  "{\"id\":48,\"resultado\":\"Bien\"}";
			} else {
				respuesta =  "{\"id\":49,\"resultado\":\"Mal\"}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			respuesta = e.getMessage();
		}
		return respuesta;
	}
	
	@POST
	@Path("/eliminar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String eliminar(String empleado){
		String respuesta = "";
		try {			
			
			JsonParser parser = new JsonParser();
			JsonObject obj = (JsonObject) parser.parse(empleado);			
			int codigo = Integer.parseInt(obj.get("codigo").toString());
			int resultado = new EmpleadoDAO().eliminar(codigo);
			if (resultado == 1) {
				respuesta =  "{\"id\":50,\"resultado\":\"Bien\"}";
			} else {
				respuesta =  "{\"id\":51,\"resultado\":\"Mal\"}";
			}
		} catch (Exception e) {
			e.printStackTrace();
			respuesta = e.getMessage();
		}
		return respuesta;
	}
}
