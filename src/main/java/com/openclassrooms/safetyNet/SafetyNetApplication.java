package com.openclassrooms.safetyNet;

import com.google.gson.Gson;
import com.openclassrooms.safetyNet.entity.Person;
import com.openclassrooms.safetyNet.model.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@SpringBootApplication
public class SafetyNetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetApplication.class, args);
	}

	@Bean
	public Data getData() {
		Gson gson = new Gson();
		Data data = null;
		try (Reader reader = new FileReader("c:\\data.json")) {

			// Convert JSON File to Java Object
			data = gson.fromJson(reader, Data.class);
			System.out.println(data.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}
}
