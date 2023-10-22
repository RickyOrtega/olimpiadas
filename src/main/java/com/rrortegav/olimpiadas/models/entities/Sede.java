package com.rrortegav.olimpiadas.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "sedes")
public class Sede implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "nombre", nullable = false, length = 50)
	@NotNull
	private String nombre;

	@Column(name = "numero_complejos", nullable = false)
	@NotNull
	private int numero_complejos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumero_complejos() {
		return numero_complejos;
	}

	public void setNumero_complejos(int numero_complejos) {
		this.numero_complejos = numero_complejos;
	}
}