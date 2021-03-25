package rabbitmq;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Consumidor {

	public static void main(String[] args) throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setUri("url");

	    Connection connection = factory.newConnection();

	    Channel channel = connection.createChannel();
	    
		boolean autoAck = false;
		String cola = "arso-test";
		String etiquetaConsumidor = "arso-consumidor";
		
		// Consumidor push
		
		channel.basicConsume(cola, autoAck, etiquetaConsumidor, 
		  
		  new DefaultConsumer(channel) {
		    @Override
		    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
		            byte[] body) throws IOException {
		        
		        String routingKey = envelope.getRoutingKey();
		        String contentType = properties.getContentType();
		        long deliveryTag = envelope.getDeliveryTag();

		        String contenido = new String(body, "UTF-8");

		        System.out.println(contenido);
		        
		        // Confirma el procesamiento
		        channel.basicAck(deliveryTag, false);
		    }
		});
		
		 
		System.out.println("consumidor esperando ...");
		
	}
}
