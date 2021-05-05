package enlaces.repositorio;


public class FactoriaRepositorioEnlaces {

	private static RepositorioEnlaces repository = null;
	
	public static RepositorioEnlaces getRepositorio() {
		if (repository == null) {
			repository = new RepositorioEnlacesMongoDB();
		}
		return repository;
	}
	
	// TODO: configuracion de la clase con propiedades
	
}
