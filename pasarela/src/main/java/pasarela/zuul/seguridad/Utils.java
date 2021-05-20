package pasarela.zuul.seguridad;

import java.io.IOException;

import javax.json.Json;
import javax.servlet.http.HttpServletResponse;

public class Utils {

	public static void writeResponseJSON(HttpServletResponse response, int codigoHttp, String mensaje) throws IOException {

		response.resetBuffer();
		response.setStatus(codigoHttp);
		response.setHeader("Content-Type", "application/json");
		response.getOutputStream().write(mensaje.getBytes());
		response.flushBuffer();
	}

	public static String buildAuthorizationErrorJSON(String cause, String msg) {

		return Json.createObjectBuilder().add("Error", "Unathorized")
				.add("Description", "El token de autenticación no es valido").add("Cause", cause).add("Message", msg)
				.build().toString();

	}

	public static String buildLoginErrorJSON(String userName) {

		return Json.createObjectBuilder().add("Error", "Unauthorized")
				.add("Description", "El usuario no existe en el sistema: " + userName).build().toString();

	}
	
	public static String jwtResponse(String jwt) {
		
		return Json.createObjectBuilder()
				.add("status", "Authorized")
				.add("token_type", "Bearer")
				.add("token", jwt)
				.add("expires_in", "3600")
				.build().toString();
	}
}
