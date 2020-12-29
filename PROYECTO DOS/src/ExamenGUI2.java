
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;

import Controlador.Exam;
import Controlador.ExamDemo;
import Controlador.Resp_Cortas_Pregunta;
import Controlador.Selec_Mul_Pregunta;
import Controlador.TFpreguntas;

public class ExamenGUI2 extends JFrame {

	JPanel panelMaster;
	JPanel panelEnunciado;
	JTextArea textEnunciado;
	JScrollPane scroll;
	JPanel panelRespuestaSelecMultiple;
	JPanel panelContenedorRespuestasAbsoluto;
	JPanel panelBotones;
	JButton btnSiguiente;
	JLabel labelTipoDePregunta;
	JLabel labelCantidadPreguntas;
	JLabel labelM;
	JLabel labelS;
	ButtonGroup grupoDeBotones;
	Controlador.Exam examen;
	private String[] respuestaSeleccionada;
	private ArrayList<JRadioButton> botonesSelecMult;
	private ArrayList<JRadioButton> botonesTF;
	private ArrayList<JTextPane> TextRespCortaPane;
	int iterador = 0;
	boolean flag = true;
	private JButton btnAnterior;
	private JPanel panelRespuestaTrueFalse;
	private JPanel panelRespuestaCorta;
	private Timer tiempo;
	private JRadioButton[] ArregloRbutton;
	
	private int m = 10, s = 0;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatCarbonIJTheme());
		} catch (Exception ex) {
			System.err.println("Tema no aplicado");
		}
		new ExamenGUI2();
	}


	public ExamenGUI2(){//Exam examen) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelMaster = new JPanel(new BorderLayout());

		panelMaster.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelMaster.setLayout(new BoxLayout(panelMaster, BoxLayout.Y_AXIS));
		setContentPane(panelMaster);
		// panelMaster.add(ComenzarExamen());
		panelMaster.add(BarraMenu(),BorderLayout.NORTH);
		//panelMaster.add(Box.createRigidArea(new Dimension(0, 20)));
		//panelMaster.add(iniciador(examen),BorderLayout.CENTER);
		panelMaster.add(iniciador(),BorderLayout.CENTER);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}
	
	public JPanel BarraMenu() {

		JPanel panelContenedorMenu = new JPanel();
		panelContenedorMenu.setLayout(new BoxLayout(panelContenedorMenu, BoxLayout.Y_AXIS));
		/************************* PANEL DE OPCIONES *******************************/
		JPanel panelMenu = new JPanel(new BorderLayout());
		JMenuBar BarraOpciones = new JMenuBar();
		JMenu Opciones = new JMenu("Menu");
		Opciones.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		JMenuItem OpcionCerrarSesion = new JMenuItem("CerrarSesion");
		OpcionCerrarSesion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		OpcionCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//new AdminGUI(); alumno
			}
		});
		Opciones.add(OpcionCerrarSesion);
		BarraOpciones.add(Opciones);
		panelMenu.add(BarraOpciones, BorderLayout.SOUTH);
		panelContenedorMenu.add(panelMenu);
		/***************************************************************************/
		return panelContenedorMenu;
	}
	
	public JPanel iniciador(){//Exam examen) {
		tiempo = new Timer(1000, acciones);
		tiempo.start();
		
		examen = ExamDemo.retornaexamen();
		respuestaSeleccionada = new String[examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size()];
		
		JPanel panelIniciador = new JPanel(new BorderLayout(0,20));
		panelIniciador.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		JPanel panelAgregados = new JPanel();
		panelAgregados.setLayout(new BorderLayout(20,0));
		panelIniciador.add(panelAgregados,BorderLayout.NORTH);
				
		JPanel panelContenedorPregunta = new JPanel(new BorderLayout());
		panelContenedorPregunta.setBorder(BorderFactory.createLineBorder(new Color(33, 48, 71), 2, true));
		panelIniciador.add(panelContenedorPregunta,BorderLayout.CENTER);

		JPanel panelContenedorBotones = new JPanel();
		panelContenedorBotones.setLayout(new BoxLayout(panelContenedorBotones, BoxLayout.Y_AXIS));
		panelIniciador.add(panelContenedorBotones,BorderLayout.SOUTH);
		
		/************************ PANEL DE AGREGADOS ****************************/
		JPanel panelInfoPreg = new JPanel();
		panelInfoPreg.setLayout(new BoxLayout(panelInfoPreg, BoxLayout.Y_AXIS));
		panelAgregados.add(panelInfoPreg, BorderLayout.WEST);
		
		JPanel panelTiempo = new JPanel(new GridLayout(0,2,0,0));
		panelAgregados.add(panelTiempo, BorderLayout.CENTER);
		

		JPanel panelBotonFinalizar = new JPanel();
		panelBotonFinalizar.setLayout(new BorderLayout(0, 0));
		panelAgregados.add(panelBotonFinalizar, BorderLayout.EAST);

		JButton botonFinalizar = new JButton("Finalizar Examen");
		botonFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Integer> respuestasVacias = new ArrayList<>();
				String mensaje = "";
				for (int i = 0; i < respuestaSeleccionada.length;i++) {
					if(respuestaSeleccionada[i]== null || respuestaSeleccionada[i] == "" ) {
						respuestasVacias.add(i+1);
					}
				}
				for (Integer integer : respuestasVacias) {
					if(mensaje.isBlank()) {
						mensaje += Integer.toString(integer);
					}else {
						mensaje = mensaje + "," +Integer.toString(integer);
					}
				}
				int respuesta = JOptionPane.showConfirmDialog(null, "<html>Seguro que quiere finalizar<br>le falta contestar las preguntas : "+mensaje+"</br> </html>", "Finalizar", JOptionPane.YES_NO_OPTION);
				if(respuesta == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Inicio sesion Alumno");
				}
			}
		});
		botonFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panelBotonFinalizar.add(botonFinalizar);

		labelM = new JLabel();
		labelM.setText("Tiempo "+m+":");
		labelM.setHorizontalAlignment(SwingConstants.RIGHT);
		labelM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTiempo.add(labelM);
		
		labelS = new JLabel();
		labelS.setText("0"+s+" minutos");
		labelS.setHorizontalAlignment(SwingConstants.LEFT);
		labelS.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTiempo.add(labelS);
		
		

		labelCantidadPreguntas = new JLabel();
		labelCantidadPreguntas.setFont(new Font("Tahoma", Font.PLAIN, 15));

		labelCantidadPreguntas.setText("Pregunta " + (iterador + 1) + " de "+ (examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size()));
		labelCantidadPreguntas.setHorizontalAlignment(SwingConstants.CENTER);
		panelInfoPreg.add(labelCantidadPreguntas);

		labelTipoDePregunta = new JLabel();
		labelTipoDePregunta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelTipoDePregunta.setText("SELECCIÓN MULTIPLE");
		labelTipoDePregunta.setHorizontalAlignment(SwingConstants.CENTER);
		panelInfoPreg.add(labelTipoDePregunta);
		/***************************************************************************/

		/************************* PANEL DEL ENUNCIADO ******************************/
		panelEnunciado = new JPanel(new BorderLayout());
		panelEnunciado.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelContenedorPregunta.add(panelEnunciado,BorderLayout.NORTH);

		textEnunciado = new JTextArea(8,12);
		textEnunciado.setLineWrap(true);
		textEnunciado.setWrapStyleWord(true);
		textEnunciado.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textEnunciado.setForeground(Color.WHITE);
		textEnunciado.setEditable(false);
		//textEnunciado.setPreferredSize(new Dimension(900, 220));
		textEnunciado.setText(examen.selecmulpreg.get(0).getEnunciado());
		
		scroll = new JScrollPane();	
		scroll.setViewportView(textEnunciado);
		
		panelEnunciado.add(scroll, BorderLayout.CENTER);
		
		/***************************************************************************/
		
		/************************* PANEL DE LA RESPUESTA ******************************/
		panelContenedorRespuestasAbsoluto = new JPanel(new BorderLayout());
		panelContenedorRespuestasAbsoluto.setBorder(new EmptyBorder(20, 70, 30, 70));
		panelContenedorRespuestasAbsoluto.setPreferredSize(new Dimension(900,250));

		panelRespuestaSelecMultiple = new JPanel(new GridLayout(5,4,20,20/*5, 4, 50, 30*/));
		agregaBotonesDinamicoSelecMult(examen.selecmulpreg.get(iterador));
		panelContenedorRespuestasAbsoluto.add(panelRespuestaSelecMultiple,BorderLayout.CENTER);

		panelRespuestaTrueFalse = new JPanel(new GridLayout(0, 2, 10, 0));
		panelRespuestaTrueFalse.setPreferredSize(panelRespuestaSelecMultiple.getPreferredSize());

		panelRespuestaCorta = new JPanel(new BorderLayout());
		panelRespuestaCorta.setBorder(new EmptyBorder(60,20,60,20));
		

		panelContenedorPregunta.add(panelContenedorRespuestasAbsoluto, BorderLayout.CENTER);
		/***************************************************************************/
		
		/************************* PANEL DE LOS BOTONES ****************************/
		panelBotones = new JPanel(new BorderLayout());
		panelContenedorBotones.add(panelBotones, BorderLayout.SOUTH);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (iterador < (examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size()) - 1) {
						iterador++;
						labelCantidadPreguntas.setText("Pregunta "+(iterador + 1) + " de "+(examen.selecmulpreg.size()+examen.tfpreg.size()+examen.rcpreg.size()));
						AvanzaRetrocedePregunta(examen);
					}
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		});

		btnAnterior = new JButton("Anterior");
		btnAnterior.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (iterador > 0) {
						iterador--;
						labelCantidadPreguntas.setText("Pregunta " + (iterador + 1) + " de "+ (examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size()));
						panelRespuestaSelecMultiple.removeAll();
						AvanzaRetrocedePregunta(examen);
					}
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		});
		panelBotones.add(btnAnterior, BorderLayout.WEST);
		panelBotones.add(btnSiguiente, BorderLayout.EAST);
		/***************************************************************************/
		
		return panelIniciador;
	}

	void AvanzaRetrocedePregunta(Exam examen) {
		if (iterador < examen.selecmulpreg.size()) {
			pack();		
			SelMulActualizaPanel(examen);
		} else if (iterador >= (examen.selecmulpreg.size()) && iterador < (examen.selecmulpreg.size() + examen.tfpreg.size())) {
			pack();
			System.out.println("TF "+ iterador);
			TrueFalseActualizaPanel(examen);
		} else if (iterador >= (examen.selecmulpreg.size() + examen.tfpreg.size())&& iterador < (examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size())) {
			pack();
			System.out.println("RC "+ iterador);
			RespCortActualizaPanel(examen);
		}
	}
	
	public void SelMulActualizaPanel(Exam examen) {
		panelContenedorRespuestasAbsoluto.removeAll();
		panelContenedorRespuestasAbsoluto.add(panelRespuestaSelecMultiple,BorderLayout.CENTER);
		panelContenedorRespuestasAbsoluto.updateUI();
		
		panelRespuestaSelecMultiple.removeAll();
		labelTipoDePregunta.setText("SELECCIÓN MULTIPLE");
		textEnunciado.setText(examen.selecmulpreg.get(iterador).getEnunciado());
		agregaBotonesDinamicoSelecMult(examen.selecmulpreg.get(iterador));
		RbutonSeleccionado(botonesSelecMult);
	}
	
	public void TrueFalseActualizaPanel(Exam examen) {
		panelContenedorRespuestasAbsoluto.removeAll();
		panelContenedorRespuestasAbsoluto.add(panelRespuestaTrueFalse,BorderLayout.CENTER);
		panelContenedorRespuestasAbsoluto.updateUI();
		panelRespuestaTrueFalse.removeAll();
		
		labelTipoDePregunta.setText("VERDADERO O FALSO");
		textEnunciado.setText(examen.tfpreg.get(iterador - examen.selecmulpreg.size()).getEnunciado());
		agregaBotonesDinamicoTrueFalse(examen.tfpreg.get(iterador - examen.selecmulpreg.size()));
		RbutonSeleccionado(botonesTF);
	}
	
	public void RespCortActualizaPanel(Exam examen) {
		panelContenedorRespuestasAbsoluto.removeAll();
		panelContenedorRespuestasAbsoluto.add(panelRespuestaCorta,BorderLayout.CENTER);
		panelContenedorRespuestasAbsoluto.updateUI();
		panelRespuestaCorta.removeAll();
		labelTipoDePregunta.setText("RESPUESTA CORTA");
		textEnunciado.setText(examen.rcpreg.get(iterador - examen.selecmulpreg.size() - examen.tfpreg.size()).getEnunciado());
		agregaTextPaneDinamico(examen.rcpreg.get(iterador - examen.selecmulpreg.size() - examen.tfpreg.size()));
	}
	
	public void RbutonSeleccionado(ArrayList<JRadioButton> botones) {
		for (int i = 0; i < botones.size(); i++) {
			if (botones.get(i).getText().equalsIgnoreCase(respuestaSeleccionada[iterador])) {
				botones.get(i).setSelected(true);
				break;
			}
		}
	}
	
	void agregaBotonesDinamicoSelecMult(Selec_Mul_Pregunta pregunta) {

		ArregloRbutton = new JRadioButton[pregunta.getopciones().length];
		botonesSelecMult = new ArrayList<JRadioButton>();
		grupoDeBotones = new ButtonGroup();

		for (int i = 0; i < ArregloRbutton.length; i++) {
			ArregloRbutton[i] = new JRadioButton(pregunta.getopciones()[i]);
			ArregloRbutton[i].setFont(new Font("Tahoma", Font.PLAIN, 20));
			botonesSelecMult.add(ArregloRbutton[i]);
			grupoDeBotones.add(ArregloRbutton[i]);
			panelRespuestaSelecMultiple.add(ArregloRbutton[i]);
			int aux = i;
			ArregloRbutton[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("presione el boton " + ArregloRbutton[aux].getText());
					respuestaSeleccionada[iterador] = ArregloRbutton[aux].getText();
					for (String algo : respuestaSeleccionada) {
						System.out.println(algo);
					}
				}
			});

		}
		panelRespuestaSelecMultiple.updateUI();
		pack();
		
	}
	
void agregaBotonesDinamicoTrueFalse(TFpreguntas pregunta) {
		
		ArregloRbutton = new JRadioButton[2];
		botonesTF = new ArrayList<JRadioButton>();
		grupoDeBotones = new ButtonGroup();
		String[] opcion = {"Verdadero","Falso"};
		
		for(int i = 0; i < ArregloRbutton.length; i++) {
			ArregloRbutton[i] = new JRadioButton(opcion[i]);
			ArregloRbutton[i].setHorizontalAlignment(SwingConstants.CENTER);
			ArregloRbutton[i].setFont(new Font("Tahoma", Font.PLAIN, 20));
			botonesTF.add(ArregloRbutton[i]);
			grupoDeBotones.add(ArregloRbutton[i]);
			panelRespuestaTrueFalse.add(ArregloRbutton[i]);
			int aux = i;
			ArregloRbutton[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("presione el boton " + ArregloRbutton[aux].getText());
					respuestaSeleccionada[iterador] = ArregloRbutton[aux].getText();
					for (String algo : respuestaSeleccionada) {
						System.out.println(algo);
					}
				}
			});
		}
		panelRespuestaTrueFalse.updateUI();
		pack();
	}


	public void agregaTextPaneDinamico(Resp_Cortas_Pregunta pregunta) {
		JTextField txtRespuestaCortaPgr = new JTextField();
		txtRespuestaCortaPgr.setText((respuestaSeleccionada[iterador] == null) ? "" : (respuestaSeleccionada[iterador]));
		txtRespuestaCortaPgr.setBackground(Color.WHITE);
		txtRespuestaCortaPgr.setForeground(Color.BLACK);
		txtRespuestaCortaPgr.setFont(new Font("Thaoma", Font.PLAIN, 23));
		panelRespuestaCorta.add(txtRespuestaCortaPgr, BorderLayout.CENTER);
		txtRespuestaCortaPgr.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				respuestaSeleccionada[iterador] = txtRespuestaCortaPgr.getText();
				for (String algo : respuestaSeleccionada) {
					System.out.println(algo);
				}
			}
			public void focusGained(FocusEvent arg0) {
			}
		});
		
		panelRespuestaCorta.updateUI();
		pack();
	}
	
	private ActionListener acciones = new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) {
			if(s==0) {
				s = 60;
				m--;
			}
			if(m < 0) {
				JOptionPane.showMessageDialog(rootPane, "<html>Tiempo Finalizado<br>Examen Terminado</html>");
				m = 0; s = 0;
				tiempo.stop();
			}else {
				
				s--;
				if(s < 10 ) {
					labelS.setText("0"+s+" minutos");
					flag = false;
				}
				if(m < 10) {
					labelM.setText("Tiempo 0"+m+":");
					if(s < 10){	
						labelS.setText("0"+s+" minutos");
					}else {
						labelS.setText(""+s+" minutos");
					}
					flag = false; 
				}
				if(flag) {
					labelM.setText("Tiempo "+m+":");
					labelS.setText(""+s+" minutos");
				}
				
				
			}
		}
	};
	
	private void actualizaLabel() {
		String MinToSeg = " minutos";
		String time = (m <= 9 ? "0" : "") + m + ":" + (s <= 9 ? "0" : "") + s;
		if (m == 0) {
			MinToSeg = " segundos";
		}
		labelM.setText("Tiempo restante " + time + MinToSeg);
	}	
}
