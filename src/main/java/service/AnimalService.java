package service;

import java.io.IOException;

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
		String nome = request.queryParams("nome");
		String idade = request.queryParams("idade");
		String porte = request.queryParams("porte");
		String especie = request.queryParams("especie");
		String local = request.queryParams("local");
		String contato = request.queryParams("contato");
		String foto = request.queryParams("foto");

		int id = animalDAO.getMaxId() + 1;

		Animal animal = new Animal(id, nome, idade, porte, especie, local, contato, foto);

		animalDAO.add(animal);

		response.status(201); // 201 Created
		return new String("Animal adicionado com sucesso - id = "+ id);
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Animal animal = (Animal) animalDAO.get(id);
		
		if (animal != null) {
    		response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<animal>\n" + 
            		"\t<id>" + animal.getId() + "</id>\n" +
            		"\t<nome>" + animal.getNome() + "</nome>\n" +
					"\t<idade>" + animal.getIdade() + "</idade>\n" +
					"\t<porte>" + animal.getPorte() + "</porte>\n" +
					"\t<especie>" + animal.getEspecie() + "</especie>\n" +
					"\t<local>" + animal.getLocal() + "</local>\n" +
					"\t<contato>" + animal.getContato() + "</contato>\n" +
					"\t<foto>" + animal.getFoto() + "</foto>\n" +
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
			animal.setNome(request.queryParams("nome"));
			animal.setIdade(request.queryParams("idade"));
			animal.setPorte(request.queryParams("porte"));
			animal.setEspecie(request.queryParams("especie"));
			animal.setLocal(request.queryParams("local"));
			animal.setContato(request.queryParams("contato"));
			animal.setFoto(request.queryParams("foto"));

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
					"\t<nome>" + animal.getNome() + "</nome>\n" +
					"\t<idade>" + animal.getIdade() + "</idade>\n" +
					"\t<porte>" + animal.getPorte() + "</porte>\n" +
					"\t<especie>" + animal.getEspecie() + "</especie>\n" +
					"\t<local>" + animal.getLocal() + "</local>\n" +
					"\t<contato>" + animal.getContato() + "</contato>"+
					"\t<foto>" + animal.getFoto() + "</foto>"+
            		"</animal>\n");
		}
		returnValue.append("</animals>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}