package entidad;

import java.sql.Date;

public class Cliente {
	private int idCliente;
	private String nombres;
	private String apellidos;
	private String dni;
	private Date fechaNacimiento;
	private String tipclnombre;
	private Tipo_cliente tipo_cliente;
	
	
	
	public String getTipclnombre() {
		tipclnombre = tipo_cliente.getNombre();
		return tipclnombre;
	}
	public void setTipclnombre(String tipclnombre) {
		this.tipclnombre = tipclnombre;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Tipo_cliente getTipo_cliente() {
		return tipo_cliente;
	}
	public void setTipo_cliente(Tipo_cliente tipo_cliente) {
		this.tipo_cliente = tipo_cliente;
	} 
	
	
	
	



}
