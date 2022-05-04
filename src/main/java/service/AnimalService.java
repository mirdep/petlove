package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.AnimalDAO;
import model.Animal;
import spark.Request;
import spark.Response;


public class AnimalService {

	private AnimalDAO animalDAO;

	public AnimalService() {
		try {
			animalDAO = new AnimalDAO("animal.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Object add(Request request, Response response) {
		String descricao = request.queryParams("descricao");
		float preco = Float.parseFloat(request.queryParams("preco"));
		int quantidade = Integer.parseInt(request.queryParams("quantidade"));
		LocalDateTime dataFabricacao = LocalDateTime.parse(request.queryParams("dataFabricacao"));
		LocalDate dataValidade = LocalDate.parse(request.queryParams("dataValidade"));

		int id = animalDAO.getMaxId() + 1;

		Animal animal = new Animal(id, descricao, preco, quantidade, dataFabricacao, dataValidade);

		animalDAO.add(animal);

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Animal animal = (Animal) animalDAO.get(id);
		
		if (animal != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<animal>\n" + 
            		"\t<id>" + animal.getId() + "</id>\n" +
            		"\t<descricao>" + animal.getDescricao() + "</descricao>\n" +
            		"\t<preco>" + animal.getPreco() + "</preco>\n" +
            		"\t<quantidade>" + animal.getQuant() + "</quantidade>\n" +
            		"\t<fabricacao>" + animal.getDataFabricacao() + "</fabricacao>\n" +
            		"\t<validade>" + animal.getDataValidade() + "</validade>\n" +
            		"</animal>\n";
        } else {
            response.status(404); // 404 Not found
            return "Animal " + id + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Animal animal = (Animal) animalDAO.get(id);

        if (animal != null) {
        	animal.setDescricao(request.queryParams("descricao"));
        	animal.setPreco(Float.parseFloat(request.queryParams("preco")));
        	animal.setQuant(Integer.parseInt(request.queryParams("quantidade")));
        	animal.setDataFabricacao(LocalDateTime.parse(request.queryParams("dataFabricacao")));
        	animal.setDataValidade(LocalDate.parse(request.queryParams("dataValidade")));

        	animalDAO.update(animal);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Animal não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Animal animal = (Animal) animalDAO.get(id);

        if (animal != null) {

            animalDAO.remove(animal);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Animal não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<animals type=\"array\">");
		for (Animal animal : animalDAO.getAll()) {
			returnValue.append("\n<animal>\n" + 
            		"\t<id>" + animal.getId() + "</id>\n" +
            		"\t<descricao>" + animal.getDescricao() + "</descricao>\n" +
            		"\t<preco>" + animal.getPreco() + "</preco>\n" +
            		"\t<quantidade>" + animal.getQuant() + "</quantidade>\n" +
            		"\t<fabricacao>" + animal.getDataFabricacao() + "</fabricacao>\n" +
            		"\t<validade>" + animal.getDataValidade() + "</validade>\n" +
            		"</animal>\n");
		}
		returnValue.append("</animals>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}