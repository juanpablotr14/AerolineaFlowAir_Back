package co.edu.usbcali.aerolinea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AerolineaApplication {
	public static void main(String[] args) {
		SpringApplication.run(AerolineaApplication.class, args);
		System.out.println("Server Activo :D");
	}
}
