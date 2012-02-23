/**Programa Pomodoro: Clase Ventana, generada para la creacion de 2 ventanas y 4 clases anonimas ActionListener.
 * se implementa metodos de la clase Archivo.java para guardar en un archivo de texto el proceso que se desea realizar
 * en el tiempo de ejecucion.
 *
 * @author Sergio Rodriguez Duran	
 * @since 01/02/12
 * @version 1.0
 */

import javax.swing.*;
import javax.swing.AbstractButton;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ventana {
	Archivo archivo = new Archivo();	
	private JButton jBoton;
	private JButton confYes;
	private JButton confNo;
	public JFrame confirmacion;
	private JTextField jTexto;
	private JLabel jEtiqueta;
	private JPanel botones;
	public JFrame ventana = new JFrame("REDMINE");
	public static String mensaje;
	public static String concepto; 
	
/**Metodo constructor Ventana, se implementa este metodo cada vez que se iniciara la ejecucion de la clase Main.java
 * como ventana incial, la cual contiene 2 clases anonimas para la accion de continuar con el proceso del Pomodoro.
 * Cada clase anonima dentro de este metodo utiliza la el objeto archivo de la clase Archivo.java para guardar el texto
 * y asu vez los metodos getNombreUsuario y getFecha para efectos de vitacora.
 */
	
	public Ventana(){
		jBoton = new JButton("Iniciar");
		jTexto = new JTextField(50);
		ventana.setLayout(new BorderLayout());
		ventana.add(jTexto, BorderLayout.CENTER);
		ventana.add(jBoton, BorderLayout.SOUTH);
		ventana.setSize(700,100);
		ventana.setVisible(true);
		jTexto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				mensaje = jTexto.getText();
				setTexto(mensaje);
				String texto = "Usuario: "+getNombreUsuario()+"\t Mensaje: "+getTexto()+ "\t Fecha: "+getFecha()+"\n";
					ventana.setVisible(false);
					try{
						new Cronometro();
						archivo.insertarTexto(texto);
						}catch(IOException fnfe){
							fnfe.printStackTrace();	
						}
				}
		});
		jBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mensaje = jTexto.getText();
				setTexto(mensaje);
				String texto = "Usuario: "+getNombreUsuario()+"\t Mensaje: "+getTexto()+ "\t Fecha: "+getFecha()+"\n";
				if(e.getSource() == jBoton){
					concepto = jTexto.getText();
					ventana.setVisible(false);	
					try{
						new Cronometro();	
						archivo.insertarTexto(texto);
						}catch(IOException fnfe){
							fnfe.printStackTrace();	
						}
				}
			}
		});

	}

/**Metodo VentanaConfirmacion, implementado para iniciar o finalizar el proceso del Pomodoro, contiene la implementacion de 2 clases anonimas
 * para reanudar la ejecucion del Pomodoro o el cierre. 
 *
 */	
	
	
	public void ventanaConfirmacion(){
		confirmacion = new JFrame("Confirmacion");
		confYes = new JButton("Si");
		confNo = new JButton("No");
		jEtiqueta = new JLabel(" El Tiempo del Pomodoro a Terminada. Desea Iniciar un Nuevo Pomodoro?");
		botones = new JPanel();
		confirmacion.setLayout(new BorderLayout());
		confirmacion.add(jEtiqueta, BorderLayout.CENTER);
		confirmacion.add(botones, BorderLayout.SOUTH);
		botones.setLayout(new FlowLayout());
		botones.add(confYes);
		botones.add(confNo);
		confirmacion.setSize(500, 150);
		confirmacion.setVisible(true);
		confYes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == confYes){
					confirmacion.setVisible(false);
					new Ventana();
				}
			}
		});
		confNo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == confNo){
					System.exit(0);
				}
			}
		});
	}

	public static String getNombreUsuario(){
		return System.getProperty("user.name");
	}
	public static String getFecha(){
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss");
		return formato.format(fecha);
	}
	public void setTexto(String concepto){
		this.concepto = concepto;
	}
	public static String getTexto(){
		return concepto;
	}
	
}