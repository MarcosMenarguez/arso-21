package enlaces.repositorio;

import enlaces.modelo.Enlace;

public class Programa {

	public static void main(String[] args) throws Exception {
		
		
		RepositorioEnlaces repositorio = FactoriaRepositorioEnlaces.getRepositorio();

		// Crea un enlace y lo añade al repositorio
		
		Enlace enlace1 = new Enlace();
		enlace1.setUrl("https://um.es");
		enlace1.setDescripcion("Universidad");
		enlace1.setEmailUsuario("marcos@um.es");
		
		String id = repositorio.add(enlace1);
		
		System.out.println("Enlace insertado, id:" + id);
		
		// Actualiza el enlace
		
		enlace1.setDescripcion("Universidad de Murcia");
		
		repositorio.update(enlace1);

		// Métodos de consulta
		
		System.out.println("getById: " + repositorio.getById(id));

		// Muestra todos los identificadores del repositorio
		
		System.out.println("getAllIds: " + repositorio.getIds());
		
		System.out.println("getAll: ");
		repositorio.getAll().forEach(System.out::println);
		
		for (Enlace enlace : repositorio.getAll())
			// Borra todos los anteriores
			if (! enlace.getId().equals(id))
				repositorio.delete(enlace);
		
		
		System.out.println("fin.");
		
	}
}
