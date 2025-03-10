package com.imcapp.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "historial_imc")
public class HistorialIMC implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "masa_corporal") // Mapea a la columna "masa_corporal" en la base de datos
    private float masaCorporal;

    @Column(name = "imc") // Mapea a la columna "imc" en la base de datos
    private float imc;

    @Column(name = "fecha_calculo") // Mapea a la columna "fecha_calculo" en la base de datos
    private Date fechaCalculo;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public float getMasaCorporal() {
        return masaCorporal;
    }

    public void setMasaCorporal(float masaCorporal) {
        this.masaCorporal = masaCorporal;
    }

    public float getImc() {
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public Date getFechaCalculo() {
        return fechaCalculo;
    }

    public void setFechaCalculo(Date fechaCalculo) {
        this.fechaCalculo = fechaCalculo;
    }
}



