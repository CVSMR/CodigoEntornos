package prueba;

public class Usuario {

	private String nombre;
	private String email;
	private Membresia tipoUsuario;

	public Usuario(String nombre, String email, Membresia tipo) {
		this.nombre = nombre;
		this.email = email;
		tipoUsuario = tipo;
		
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
