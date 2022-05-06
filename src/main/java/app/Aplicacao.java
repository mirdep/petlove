package app;

import static spark.Spark.*;

import service.AnimalService;
import spark.Filter;


public class Aplicacao {
	
	private static AnimalService animalService = new AnimalService();
	
    public static void main(String[] args) {
        port(6789);
        
        options("/*",
	        (request, response) -> {
	
	            String accessControlRequestHeaders = request
	                    .headers("Access-Control-Request-Headers");
	            if (accessControlRequestHeaders != null) {
	                response.header("Access-Control-Allow-Headers",
	                        accessControlRequestHeaders);
	            }
	
	            String accessControlRequestMethod = request
	                    .headers("Access-Control-Request-Method");
	            if (accessControlRequestMethod != null) {
	                response.header("Access-Control-Allow-Methods",
	                        accessControlRequestMethod);
	            }
	
	            return "OK";
	        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
               
        post("/animal", (request, response) -> animalService.add(request, response));
       
        get("/animal", (request, response) -> animalService.getAll(request, response));
         
        get("/animal/:id", (request, response) -> animalService.get(request, response));

        get("/animal/update/:id", (request, response) -> animalService.update(request, response));

        get("/animal/delete/:id", (request, response) -> animalService.remove(request, response));
        
    }
}