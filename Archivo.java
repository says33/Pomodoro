/**
 * @author Sergio Rodriguez Duran	
 * @since 01/02/12
 * @version 1.0
 */

import java.io.*;
public class Archivo{
	
	public void insertarTexto(String texto)throws IOException{
		RandomAccessFile archivo;
		
		archivo = new RandomAccessFile("/Users/saysrodriguez/Documents/Java/pomodoro/log.txt", "rw");
		archivo.seek(archivo.length());
		try{
			archivo.writeBytes(texto);
		}finally {
			archivo.close();
		}
	}

}

