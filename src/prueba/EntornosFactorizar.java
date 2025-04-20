package prueba;

public class EntornosFactorizar {
	
		public double calculaDato( int cantidad, Producto producto,
				 boolean esEnvioGratis, boolean esOfertaEspecial, boolean esNavidad, boolean aplicarCuotas,
				double precioEnvio, String codigoCupon, Usuario usuario, int cuota, MetodoPago metodoPago) {
		
			double total = producto.getPrecioBase() * cantidad;
			
			if (producto.getDescuento() > 0) {
				total -= total * (producto.getDescuento() / 100);
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
	        if (!validarProducto(producto.getTipoPrducto(), producto.getCategoriaProducto())) {
	            throw new IllegalArgumentException("El producto no es válido para esta compra.");
	        }

	        // Llamada a método para aplicar descuento por tipo de membresia, siempre que el usuario no sea null o normal.
	        if (usuario != null && !usuario.getTipoUsuario().equals(Membresia.NORMAL)) {
	            total = aplicarDescuentoPorUsuario(usuario, total);
	        }

	        // Metodo en el cual suma el precio del envio en caso de no ser envio gratis.
	     	if (!esEnvioGratis) {
	   			total += precioEnvio;
	 		}
	        
			total = aplicarCuotas(total, aplicarCuotas, cuota);
			
			//Se comprueba que el usuario tenga tarjeta de fidelidad y el saldo de la misma, se usará dicho saldo, y si sigue haciendo falta pago, 
			//se pasará el método de la forma de pago.
			if (usuario.isTieneTarjetaFidelidad() && usuario.getSaldoTarjeta() > 0) {
				if(usuario.getSaldoTarjeta() >= total) {
					usuario.setSaldoTarjeta(usuario.getSaldoTarjeta() - total);
					total = 0;
				}else {
					total -= usuario.getSaldoTarjeta();
					usuario.setSaldoTarjeta(0);
					total = aplicarCuoteMetodoPago(metodoPago, total);
				}	
			}else {
				total = aplicarCuoteMetodoPago(metodoPago, total);
			}
			
	     	if (total < 0) {
	     		total = 0;
	     	}
	        return total; 
	    }

		//Se separa este método del anterior y se pone como public, habra que acceder a el despues de acceder al metodo calcularDato.
		// Si no hay cuota devuelve el total sin tocarlo
		// si hay cuota decide entre 1,2,3
		// Devuelve el total actualizado
		
		private double aplicarCuotas(double total, boolean aplicarCuotas, int cuota) {
			// Cuotas
			if (!aplicarCuotas) return total;
			switch (cuota) {
				case 1 -> total *= 1.1;
				case 2 -> total *= 1.2;
				case 3 -> total *= 1.3;
			}
			return total;
		}
		
		//Se separa este método del método inicial para que quede más limpio, habra que acceder a el despues de los dos anteriores.
		//Aplicara un coste segun el método de pago.
		private double aplicarCuoteMetodoPago(MetodoPago metodo, double total) {
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
	    	switch (codigoCupon) { 
				case "CUPOFF" -> total *= 0.8; 
				case "NAVIDAD2025" -> total *= 0.75; 
				default -> {} //  No hay descuento si no hay caso cupon
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
					//El defaullt es necesario por sintaxis pero como nunca va a entrar no le ponemos nada
				}
				
	        }
	        return total;
	    }
}
