package com.ats.ProjectAtS.models;

import java.io.Serializable;

import lombok.Data;

/**
 * Clase utilizada para crear una clave primaria compuesta para la tabla de Score.
 * Cada prueba tendrá una serie de puestos con valores diferentes.
 * @author Alexander
 *
 */

@Data
public class UniqueScore implements Serializable {

	private static final long serialVersionUID = -6783149490762593702L;
	
	private Trial trial;
	
	private Integer place;
}
