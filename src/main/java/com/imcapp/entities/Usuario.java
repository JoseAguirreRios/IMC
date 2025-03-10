package com.imcapp.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre_completo") // Mapea a la columna "nombre_completo" en la base de datos
    private String nombreCompleto;

    @Column(name = "edad") // Mapea a la columna "edad" en la base de datos
    private int edad;

    @Column(name = "sexo") // Mapea a la columna "sexo" en la base de datos
    private String sexo;

    @Column(name = "estatura") // Mapea a la columna "estatura" en la base de datos
    private float estatura;

    @Column(name = "nombre_usuario") // Mapea a la columna "nombre_usuario" en la base de datos
    private String nombreUsuario;

    @Column(name = "contrasena") // Mapea a la columna "contrasena" en la base de datos
    private String contrasena;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public float getEstatura() {
        return estatura;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}




