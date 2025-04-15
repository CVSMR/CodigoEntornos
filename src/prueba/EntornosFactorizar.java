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

			if (!validarProducto(tipoProducto, categoriaProducto)) {
				throw new IllegalArgumentException("El producto no es válido para esta compra.");
			} // añadir excepciones

			// ESTO SE TIENE QUE QUEDAR
			if (usuario != null) {
				total = aplicarDescuentoPorUsuario(usuario, total);
			}

			
			if (total < 0) {
				total = 0;
			}

			return total;
		}

		private double aplicarOpcionesEspeciales(boolean esOfertaEspecial, boolean esNavidad, boolean esMiembroVip,
				double total) {// Metodo añadido, quiza hay que incializar double TOTAL;
			// Opciones especiales
			if (esOfertaEspecial) {
				total *= 0.9;
			}
			if (esNavidad) {
				total *= 0.85;
			}
			if (esMiembroVip) {
				total *= 0.8;
			}
			return total;
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

	 
	 	// Las ofertas conbinadas estan metidas en el mismo metodo
	    
	    private double aplicarDescuentosPromocionales(double total,boolean esOfertaEspecial,boolean esNavidad,boolean esMiembroVip){
	    	if (esOfertaEspecial) {
	            total *= 0.9;  
	        }
	        if (esNavidad) {
	            total *= 0.85; 
	        }
	        if (esMiembroVip) {
	            total *= 0.8;  
	        }
	        return total;
	    }
	    
	    //Metodos de pago refatcorizados en un metodo
	    
	    private double aplicarRecargoPorMetodoPago(double total,String metodoPago) {
	    	if (metodoPago.equals("TarjetaCredito")) {
	    		total *=1.05;
	    	} else if (metodoPago.equals("PayPal")) {
	    		total *=1.02;
	    	}
	    	return total;
	    }
	  	//Aplicar cupones descuento
	 
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
			if (usuario.esEmpleado()) {
				total *= 0.7;
			} else if (usuario.esMiembroGold()) {
				total *= 0.85;
			} else if (usuario.esMiembroSilver()) {
				total *= 0.9;
			}
			return total;
		}
}
