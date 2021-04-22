package encuestas;


public class FactoriaRepositorioEncuestas {

	private static RepositorioEncuestas repository = null;
	
	public static RepositorioEncuestas getRepositorio() {
		if (repository == null) {
			repository = new RepositorioEncuestasMongoDB();
		}
		return repository;
	}
	
	// TODO: configuracion de la clase con propiedades
	
}
