package br.com.livrarioapi;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LivrarioApiApplication {

	private static final Dotenv DOTENV = Dotenv.load();
	private static final String DB_HOST = DOTENV.get("DB_HOST");
	private static final String DB_PORT = DOTENV.get("DB_PORT");
	private static final String DB_USERNAME = DOTENV.get("DB_USERNAME");
	private static final String DB_NAME = DOTENV.get("DB_NAME");
	private static final String DB_PASSWORD = DOTENV.get("DB_PASSWORD");

	public static void main(String[] args) {

		System.setProperty("DB_HOST", DB_HOST);
		System.setProperty("DB_PORT", DB_PORT);
		System.setProperty("DB_USERNAME", DB_USERNAME);
		System.setProperty("DB_NAME", DB_NAME);
		System.setProperty("DB_PASSWORD", DB_PASSWORD);

		SpringApplication.run(LivrarioApiApplication.class, args);
	}

}
