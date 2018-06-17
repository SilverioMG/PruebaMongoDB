package net.atopecode.pruebamongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Test")
public class Persona {

    @Id
    private String id;

    private String nombre;

    public Persona(){

    }

    public Persona(String nombre){
        this.nombre = nombre;
    }

    public Persona(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        return "id: " + id + " nombre: " + nombre;
    }
}

