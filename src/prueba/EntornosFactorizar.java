package prueba;

public class EntornosFactorizar {
	 public double calculaDato(double precioBase, int cantidad, double descuento, double impuestos, boolean tieneTarjetaFidelidad, double saldoTarjeta, boolean esOfertaEspecial, boolean esNavidad, boolean esMiembroVip, String metodoPago, boolean aplicarCuotas, int cuota, boolean esEnvioGratis, double precioEnvio, String tipoProducto, String categoriaProducto, String codigoCupon, Usuario usuario) {
	        double total = precioBase * cantidad;

	       // descuentos
	        if (descuento > 0) {
	            total -= total * (descuento / 100);
	        }

	   
	        if (tieneTarjetaFidelidad && saldoTarjeta > 0) {
	            total -= saldoTarjeta;
	        }

	        // impuestos
	        total += total * (impuestos / 100);

	        // Ofertas
	        
	        if (esOfertaEspecial) {
	            total *= 0.9;
	        }

	     
	        if (esNavidad) {
	            total *= 0.85;
	        }

	     
	        if (esMiembroVip) {
	            total *= 0.8;
	        }

	        // Metodos de pago
	        
	        if (metodoPago.equals("TarjetaCredito")) {
	            total *= 1.05;
	        } else if (metodoPago.equals("PayPal")) {
	            total *= 1.02;
	        }

	        // cuotas
	        
	        if (aplicarCuotas) {
	            if (cuota == 3) {
	                total *= 1.1;
	            } else if (cuota == 6) {
	                total *= 1.2;
	            } else if (cuota == 12) {
	                total *= 1.3;
	            }
	        }

	        // Envio gratis
	        
	        if (!esEnvioGratis) {
	            total += precioEnvio;
	        }

	        
	        if (codigoCupon != null && !codigoCupon.isEmpty()) {
	            total = aplicarCuponDescuento(total, codigoCupon);
	        }

	        // Invalidacion
	        
	        if (!validarProducto(tipoProducto, categoriaProducto)) {
	            throw new IllegalArgumentException("El producto no es válido para esta compra.");
	        }

	        // usuario nulo
	        
	        if (usuario != null) {
	            total = aplicarDescuentoPorUsuario(usuario, total);
	        }

	     
	        if (total < 0) {
	            total = 0;
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

	    // validar producto
	    
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

	   // Aplicar descuentos por usuario
	    
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
