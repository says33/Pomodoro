/** Clase Cronometro, clase donde se implementan Thread para la corrida del proceso de contador, Mediante la implementacion de la 
 * clase Runnable, y la utilizacion de la libreria "JavaZoom", para lanzar un sonido al finalizar el proceso del metodo Run
 * @author Sergio Rodriguez Duran	
 * @since 01/02/12
 * @version 1.0
 */


import javax.swing.*;
import javax.swing.SwingConstants;
import java.awt.Font; 
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;	
import java.awt.event.ActionListener;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.*;

public class Cronometro implements Runnable {

	Thread crono;
	public int minutos = 24;
	public int segundos = 59; 
	Ventana wind = new Ventana();
	public JFrame ventana;
	private JLabel tiempo = new JLabel("Tiempo", SwingConstants.CENTER);
	private JLabel texto;
	private JButton pausa;
	private JPanel contenedor;
	private JPanel mensaje;
	public int bandera = 0;
	public String nombreBoton;
	
/**Metodo constructor Cronometro, donde se implememnta el Thread para iniciar el proceso del metodo Run, se muestra el texto que se introdujo en 
 * el metodo contructor de la clase Ventana
 */

	public Cronometro() {
	
		wind.ventana.setVisible(false);
		nombreBoton = "Pausa";
		ventana = new JFrame("Pomodoro");
		pausa = new JButton(nombreBoton);
		contenedor = new JPanel();
		mensaje = new JPanel();
		texto = new JLabel(wind.getTexto());
			tiempo.setFont(new Font("TimesRoman",Font.BOLD, 60));	
			ventana.setLayout(new BorderLayout());
			ventana.add(tiempo, BorderLayout.CENTER);
			ventana.add(contenedor, BorderLayout.SOUTH);
			ventana.add(mensaje, BorderLayout.NORTH);
			mensaje.add(texto);
			contenedor.setLayout(new FlowLayout());
			contenedor.add(pausa);
			ventana.setSize(300,400);
			ventana.setVisible(true);
			ventana.setResizable(false);
			ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sirve para cuando cierre la ventana termine el proceso.
				crono = new Thread(this);
					crono.start();
			pausa.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(e.getSource() == pausa){
							if(bandera == 0){
								crono.suspend();
								bandera=1;
								nombreBoton ="Reanudar";
							//	System.out.println(wind.concepto);
							}else if(bandera == 1){
								crono.resume();
								bandera=0;
								nombreBoton ="Pausa";
							}
						}
										
					}
				});			

	}
	
	public void run(){ 
		Font font = new Font("TimesRoman",Font.PLAIN,60);
	 	try {
			for(;;) {
				if(segundos==0) { 
					segundos=59; minutos--; 
				}//fin del if de 
			segundos--;
			tiempo.setText(minutos+":"+segundos);
				if(minutos == 0 && segundos == 0){
					try{
						FileInputStream sonido = new FileInputStream("//Users//saysrodriguez//Documents//Java//pomodoro//alarma.mp3");
						BufferedInputStream bis = new BufferedInputStream(sonido);
						Player player = new Player(bis);
						player.play();
						wind.ventanaConfirmacion();
						ventana.setVisible(false);
					}catch (JavaLayerException e){
						e.printStackTrace();				
					}catch (FileNotFoundException e){
						e.printStackTrace();
					}//fin del try del sonido
					crono.stop();
				}//fin del if del sonido
				crono.sleep(1000);
			}
	 	}catch (InterruptedException e) { 
			System.out.println(e.getMessage());
		}//fin del try 1

	}//fin del metodo rub



}//fin de la clase