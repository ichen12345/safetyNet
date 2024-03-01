package com.openclassrooms.safetyNet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.openclassrooms.safetyNet.Model.Person;

import jakarta.persistence.criteria.Path;

public class GsonToReadJson {

    Gson gson = new Gson();

        try (Reader reader = new FileReader("c:\\data.json")) {

            // Convert JSON File to Java Object
            Person person = gson.fromJson(reader, Person.class);
			
			// print person 
            System.out.println(person);

        } catch (IOException e) {
            e.printStackTrace();
        }
}

