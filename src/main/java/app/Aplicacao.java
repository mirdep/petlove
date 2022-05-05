package app;

import static spark.Spark.*;

import service.AnimalService;

public class Aplicacao {
	
	private static AnimalService animalService = new AnimalService();
	
    public static void main(String[] args) {
        port(6789);

        post("/animal", (request, response) -> animalService.add(request, response));

        get("/animal/:id", (request, response) -> animalService.get(request, response));

        get("/animal/update/:id", (request, response) -> animalService.update(request, response));

        get("/animal/delete/:id", (request, response) -> animalService.remove(request, response));

        get("/animal", (request, response) -> animalService.getAll(request, response));
    }
}