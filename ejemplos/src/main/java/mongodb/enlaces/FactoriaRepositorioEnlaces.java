package mongodb.enlaces;


public class FactoriaRepositorioEnlaces {

	private static RepositorioEnlaces repository = null;
	
	public static RepositorioEnlaces getRepositorio() {
		if (repository == null) {
			repository = new RepositorioLinksMongoDB();
		}
		return repository;
	}
	
	// TODO: configuracion de la clase con propiedades
	
}
