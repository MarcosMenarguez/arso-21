package rabbitmq;

import java.io.IOException;
import java.util.Map;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Productor {

	public static void main(String[] args) throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setUri("amqps://cjwcgmld:PSivlu_1XxFdvs6FclraBxll-yrT1vvl@bonobo.rmq.cloudamqp.com/cjwcgmld");

	    Connection connection = factory.newConnection();

	    Channel channel = connection.createChannel();

	    /** Declaración del Exchange y cola, opcional **/
	    
	    
	    final String exchangeName = "arso-exchange";
	    final String queueName = "arso-queue";
	    final String bindingKey = "arso-queue";
	   
	    try {
	        boolean durable = true;
	        channel.exchangeDeclare(exchangeName, "direct", durable);

	        boolean exclusive = false;
	        boolean autodelete = false;
	        Map<String, Object> properties = null; // sin propiedades
	        channel.queueDeclare(queueName, durable, exclusive, autodelete, properties);    
	        
	        channel.queueBind(queueName, exchangeName, bindingKey);
	    } catch (IOException e) {

	        String mensaje = e.getMessage() == null ? e.getCause().getMessage() : e.getMessage();

	        System.out.println("No se ha podido establecer la conexion con el exchange o la cola: \n\t->" + mensaje);
	        return;
	    }
	    
	    
	    
	    String mensaje = "hola2"; 
        
	    String routingKey = "arso-test";
	    String exchange = "amq.direct";
	    
        channel.basicPublish(exchange, routingKey, 
                new AMQP.BasicProperties.Builder()
                    // .contentType("application/json")
                    .build()                
                , mensaje.getBytes());
        
        channel.close();
        connection.close();
        
        System.out.println("fin.");
	    
	}
}
