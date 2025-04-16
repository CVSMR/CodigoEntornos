package prueba;

public class Usuario {

	private String nombre;
	private String email;
	private Membresia tipoUsuario;
	private boolean tieneTarjetaFidelidad;
	private double saldoTarjeta;

	public Usuario(String nombre, String email, Membresia tipo, boolean tieneTarjetaFidelidad, double saldoTarjeta) {
		this.nombre = nombre;
		this.email = email;
		tipoUsuario = tipo; 
		this.tieneTarjetaFidelidad = tieneTarjetaFidelidad;
		this.saldoTarjeta = saldoTarjeta;
		
	}

	public boolean isTieneTarjetaFidelidad() {
		return tieneTarjetaFidelidad;
	}

	public void setTieneTarjetaFidelidad(boolean tieneTarjetaFidelidad) {
		this.tieneTarjetaFidelidad = tieneTarjetaFidelidad;
	}

	public double getSaldoTarjeta() {
		return saldoTarjeta;
	}

	public void setSaldoTarjeta(double saldoTarjeta) {
		this.saldoTarjeta = saldoTarjeta;
	}



	public Membresia getTipoUsuario() {
		return tipoUsuario;
	}



	public void setTipoUsuario(Membresia tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}



	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "Usuario{" + "nombre='" + nombre + '\'' + ", email='" + email + '\'' + ", tipo de usuario =" + tipoUsuario + '}';
	}
}
