package prueba;

public class EntornosFactorizar {
	
		public double calculaDato(double precioBase, int cantidad, double descuento, double impuestos,
				boolean tieneTarjetaFidelidad, double saldoTarjeta, boolean esEnvioGratis,
				double precioEnvio, String tipoProducto, String categoriaProducto, String codigoCupon, Usuario usuario) {
		
			double total = precioBase * cantidad;
			
			// ESto tinee que ir junto?
			if (descuento > 0) {
				total -= total * (descuento / 100);
			}

			if (tieneTarjetaFidelidad && saldoTarjeta > 0) {
				total -= saldoTarjeta;
			}

			total += total * (impuestos / 100);
			// Tiene que ir junto

		

			// Envio gratis
			if (!esEnvioGratis) {
				total += precioEnvio;
			}

			if (codigoCupon != null && !codigoCupon.isEmpty()) {
				total = aplicarCuponDescuento(total, codigoCupon);
			}

	     
	        if (esMiembroVip) {
	            total *= 0.8;
	        }

	        

			// ESTO SE TIENE QUE QUEDAR
			if (usuario != null) {
				total = aplicarDescuentoPorUsuario(usuario, total);
			}

			
			if (total < 0) {
				total = 0;
			}

	        // Envio gratis
	        
	        if (!esEnvioGratis) {
	            total += precioEnvio;
	        }

		private double aplicarCuotas(double total, boolean aplicarCuotas, int cuota) {
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
		
		private double aplicarCuoteMetodoPago(MetodoPago metodo, double total) {
			// Metodo PAgos
			if (metodo.equals(MetodoPago.TARJETA_CREDITO)) {
				total *= 1.05;
			} else if (metodo.equals(MetodoPago.PAYPAL)) {
				total *= 1.02;
			}
			return total;
		}

	        // Invalidacion
	        
	        if (!validarProducto(tipoProducto, categoriaProducto)) {
	            throw new IllegalArgumentException("El producto no es válido para esta compra.");
	        }

	        // usuario nulo
	        
	        if (usuario != null) {
	            total = aplicarDescuentoPorUsuario(usuario, total);
	        }
	        if (esMiembroVip) {
	            total *= 0.8;  
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
	        }
	        return total;
	    }
}
