package dao;

import model.Animal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class AnimalDAO {
	private List<Animal> animals;
	private int maxId = 0;

	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;

	public int getMaxId() {
		return maxId;
	}

	public AnimalDAO(String filename) throws IOException {
		file = new File(filename);
		animals = new ArrayList<Animal>();
		if (file.exists()) {
			readFromFile();
		}

	}

	public void add(Animal animal) {
		try {
			animals.add(animal);
			this.maxId = (animal.getId() > this.maxId) ? animal.getId() : this.maxId;
			this.saveToFile();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o animal '" + animal.getNome() + "' no disco!");
		}
	}

	public Animal get(int id) {
		for (Animal animal : animals) {
			if (id == animal.getId()) {
				return animal;
			}
		}
		return null;
	}

	public void update(Animal p) {
		int index = animals.indexOf(p);
		if (index != -1) {
			animals.set(index, p);
			this.saveToFile();
		}
	}

	public void remove(Animal p) {
		int index = animals.indexOf(p);
		if (index != -1) {
			animals.remove(index);
			this.saveToFile();
		}
	}

	public List<Animal> getAll() {
		return animals;
	}

	private List<Animal> readFromFile() {
		animals.clear();
		Animal animal = null;
		try (FileInputStream fis = new FileInputStream(file);
				ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				animal = (Animal) inputFile.readObject();
				animals.add(animal);
				maxId = (animal.getId() > maxId) ? animal.getId() : maxId;
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar animal no disco!");
			e.printStackTrace();
		}
		return animals;
	}

	private void saveToFile() {
		try {
			fos = new FileOutputStream(file, false);
			outputFile = new ObjectOutputStream(fos);

			for (Animal animal : animals) {
				outputFile.writeObject(animal);
			}
			outputFile.flush();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar animal no disco!");
			e.printStackTrace();
		}
	}

	private void close() throws IOException {
		outputFile.close();
		fos.close();
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			this.saveToFile();
			this.close();
		} catch (Exception e) {
			System.out.println("ERRO ao salvar a base de dados no disco!");
			e.printStackTrace();
		}
	}
}