package prueba;

public class EntornosFactorizar {
	
		public double calculaDato(double precioBase, int cantidad, double descuento, double impuestos,
				boolean tieneTarjetaFidelidad, double saldoTarjeta, boolean esEnvioGratis, boolean esOfertaEspecial,
				boolean esNavidad,double precioEnvio, String tipoProducto,boolean aplicarCuotas,
				String categoriaProducto, String codigoCupon, Usuario usuario,int cuota, MetodoPago metodoPago) {
		
			double total = precioBase * cantidad;
			
			if (descuento > 0) {
				total -= total * (descuento / 100);
			}

			if (tieneTarjetaFidelidad && saldoTarjeta > 0) {
				total -= saldoTarjeta;
			}

			total += total * (impuestos / 100);
			// Metodo en el cual suma el precio del envio en caso de no ser envio gratis.
			if (!esEnvioGratis) {
				total += precioEnvio;
			}

			//Creo esta nueva llamada a método en caso de que haya presente una campaña de descuento.
			if(esOfertaEspecial || esNavidad) {
				total = aplicarDescuentoCampaña(total, esOfertaEspecial, esNavidad);
			}
			
			
			//llamada a método donde se aplicaria cupon descuento en caso de contener un código descuento.
			if (codigoCupon != null && !codigoCupon.isEmpty()) {
				total = aplicarCuponDescuento(total, codigoCupon);
			}

			
	        
	        // Valida el tipo de producto comprobando que exista y lanzando excpcion en caso contrario.
	        
	        if (!validarProducto(tipoProducto, categoriaProducto)) {
	            throw new IllegalArgumentException("El producto no es válido para esta compra.");
	        }

	        // Llamada a método para aplicar descuento por tipo de membresia, siempre que el usuario no sea null.
	        if (usuario != null && !usuario.getTipoUsuario().equals(Membresia.NORMAL)) {
	            total = aplicarDescuentoPorUsuario(usuario, total);
	        }
	        // ESTO SE TIENE QUE QUEDAR
	        
			total = aplicarCuotas(total, aplicarCuotas, cuota);
			total = aplicarCuoteMetodoPago(metodoPago, total);
	     	if (total < 0) {
	     		total = 0;
	     	}
	        return total; 
	    }

		//Se separa este método del anterior y se pone como public, habra que acceder a el despues de acceder al metodo calcularDato.
		public double aplicarCuotas(double total, boolean aplicarCuotas, int cuota) {
			// Cuotas
			if (aplicarCuotas) {
				if (cuota == 3) {
					total *= 1.1;
				} else if (cuota == 6) {
					total *= 1.2;
				} else if (cuota == 12) {
					total *= 1.3;
				}
			}
			return total;
		}
		
		//Se separa este método del método inicial para que quede más limpio, habra que acceder a el despues de los dos anteriores.
		//Aplicara un coste segun el método de pago.
		public double aplicarCuoteMetodoPago(MetodoPago metodo, double total) {
			// Metodo PAgos
			if (metodo.equals(MetodoPago.TARJETA_CREDITO)) {
				total *= 1.05;
			} else if (metodo.equals(MetodoPago.PAYPAL)) {
				total *= 1.02;
			}
			return total;
		}

		//Método para aplicar descuentos por campaña.
	    private double aplicarDescuentoCampaña(double total, boolean esOfertaEspecial, boolean esNavidad) {
	    	if (esOfertaEspecial) {
	            total *= 0.9;
	        }

	        if (esNavidad) {
	            total *= 0.85;
	        }
	        return total;
	    }

	  
	    private double aplicarCuponDescuento(double total, String codigoCupon) {
	        if (codigoCupon.equals("CUPOFF")) {
	            total *= 0.8;
	        } else if (codigoCupon.equals("NAVIDAD2025")) {
	            total *= 0.75;
	        }
	        return total;
	    }

		private boolean validarProducto(String tipoProducto, String categoriaProducto) {
			if (tipoProducto.equals("Electronico") && categoriaProducto.equals("Smartphones")) {
				return true;
			} else if (tipoProducto.equals("Ropa") && categoriaProducto.equals("Hombre")) {
				return true;
			} else if (tipoProducto.equals("Ropa") && categoriaProducto.equals("Mujer")) {
				return true;
			}
			return false;
		}

	   
		//Este método aplicara descuento en base a la membresia.
	    private double aplicarDescuentoPorUsuario(Usuario usuario, double total) {
	      
	        switch(usuario.getTipoUsuario()) {
				case EMPLEADO ->{
					total *= 0.7; 
				}
				case VIP ->{
					total *= 0.8;
				}
				case GOLD ->{
					total *= 0.85;
				}
				case SILVER ->{
					total *= 0.9; 
				}
				default ->{
					
				}
				
	        }
	        return total;
	    }
}
