package prueba;

public class Producto {
	private String nombre;
	private double precioBase;
	private double descuento;
	private double impuestos;
	private String categoriaProducto;
	private String tipoPrducto;
	
	public Producto(String nombre, double precioBase, double descuento, double impuestos, String categoriaProducto, String tipoPrducto) {
		
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.descuento = descuento;
		this.impuestos = impuestos;
		this.tipoPrducto = tipoPrducto;
		this.categoriaProducto = categoriaProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(double impuestos) {
		this.impuestos = impuestos;
	}
	
	public String getTipoPrducto() {
		return tipoPrducto;
	}

	public void setTipoPrducto(String tipoPrducto) {
		this.tipoPrducto = tipoPrducto;
	}

	public String getCategoriaProducto() {
		return categoriaProducto;
	}

	public void setCategoriaProducto(String categoriaProducto) {
		this.categoriaProducto = categoriaProducto;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", precioBase=" + precioBase + ", descuento=" + descuento + ", impuestos="
				+ impuestos + ", tipoPrducto=" + tipoPrducto + ", categoriaProducto=" + categoriaProducto  + "]";
	}
	
	
	
}
