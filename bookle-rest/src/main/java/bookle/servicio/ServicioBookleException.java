package bookle.servicio;

@SuppressWarnings("serial")
public class ServicioBookleException extends Exception {

	public ServicioBookleException(String msg, Throwable causa) {		
		super(msg, causa);
	}
	
	public ServicioBookleException(String msg) {
		super(msg);		
	}
	
		
}
