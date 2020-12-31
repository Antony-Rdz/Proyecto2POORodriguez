
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
	int formaExamen = 1;

	private int m = 10, s = 0;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatCarbonIJTheme());
		} catch (Exception ex) {
			System.err.println("Tema no aplicado");
		}
		new ExamenGUI2();
	}

	public ExamenGUI2() {// Exam examen) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelMaster = new JPanel(new BorderLayout());

		panelMaster.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelMaster.setLayout(new BoxLayout(panelMaster, BoxLayout.Y_AXIS));
		setContentPane(panelMaster);
		// panelMaster.add(ComenzarExamen());
		panelMaster.add(BarraMenu(), BorderLayout.NORTH);
		// panelMaster.add(Box.createRigidArea(new Dimension(0, 20)));
		// panelMaster.add(iniciador(examen),BorderLayout.CENTER);
		panelMaster.add(iniciador(), BorderLayout.CENTER);
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
				// new AdminGUI(); alumno
			}
		});
		Opciones.add(OpcionCerrarSesion);
		BarraOpciones.add(Opciones);
		panelMenu.add(BarraOpciones, BorderLayout.SOUTH);
		panelContenedorMenu.add(panelMenu);
		/***************************************************************************/
		return panelContenedorMenu;
	}

	public JPanel iniciador() {// Exam examen) {
		tiempo = new Timer(1000, acciones);
		tiempo.start();

		examen = ExamDemo.retornaexamen();

		JPanel panelIniciador = new JPanel(new BorderLayout(0, 20));
		panelIniciador.setBorder(new EmptyBorder(20, 20, 20, 20));

		JPanel panelAgregados = new JPanel();
		panelAgregados.setLayout(new BorderLayout(20, 0));
		panelIniciador.add(panelAgregados, BorderLayout.NORTH);

		JPanel panelContenedorPregunta = new JPanel(new BorderLayout());
		panelContenedorPregunta.setBorder(BorderFactory.createLineBorder(new Color(33, 48, 71), 2, true));
		panelIniciador.add(panelContenedorPregunta, BorderLayout.CENTER);

		JPanel panelContenedorBotones = new JPanel();
		panelContenedorBotones.setLayout(new BoxLayout(panelContenedorBotones, BoxLayout.Y_AXIS));
		panelIniciador.add(panelContenedorBotones, BorderLayout.SOUTH);

		/************************ PANEL DE AGREGADOS ****************************/
		JPanel panelInfoPreg = new JPanel();
		panelInfoPreg.setLayout(new BoxLayout(panelInfoPreg, BoxLayout.Y_AXIS));
		panelAgregados.add(panelInfoPreg, BorderLayout.WEST);

		JPanel panelTiempo = new JPanel(new GridLayout(0, 2, 0, 0));
		panelAgregados.add(panelTiempo, BorderLayout.CENTER);

		JPanel panelBotonFinalizar = new JPanel();
		panelBotonFinalizar.setLayout(new BorderLayout(0, 0));
		panelAgregados.add(panelBotonFinalizar, BorderLayout.EAST);

		JButton botonFinalizar = new JButton("Finalizar Examen");
		botonFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Integer> respuestasVacias = new ArrayList<>();
				String mensaje = "";
				for (int i = 0; i < respuestaSeleccionada.length; i++) {
					if (respuestaSeleccionada[i] == null || respuestaSeleccionada[i] == "") {
						respuestasVacias.add(i + 1);
					}
				}
				for (Integer integer : respuestasVacias) {
					if (mensaje.isBlank()) {
						mensaje += Integer.toString(integer);
					} else {
						mensaje = mensaje + "," + Integer.toString(integer);
					}
				}
				int respuesta = JOptionPane.showConfirmDialog(null,
						"<html>Seguro que quiere finalizar<br>le falta contestar las preguntas : " + mensaje
								+ "</br> </html>",
						"Finalizar", JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Inicio sesion Alumno");
				}
			}
		});
		botonFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panelBotonFinalizar.add(botonFinalizar);

		labelM = new JLabel();
		labelM.setText("Tiempo " + m + ":");
		labelM.setHorizontalAlignment(SwingConstants.RIGHT);
		labelM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTiempo.add(labelM);

		labelS = new JLabel();
		labelS.setText("0" + s + " minutos");
		labelS.setHorizontalAlignment(SwingConstants.LEFT);
		labelS.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTiempo.add(labelS);

		labelCantidadPreguntas = new JLabel();
		labelCantidadPreguntas.setFont(new Font("Tahoma", Font.PLAIN, 15));

		//labelCantidadPreguntas.setText("Pregunta " + (iterador + 1) + " de "+ (examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size()));
		labelCantidadPreguntas.setHorizontalAlignment(SwingConstants.CENTER);
		panelInfoPreg.add(labelCantidadPreguntas);

		labelTipoDePregunta = new JLabel();
		labelTipoDePregunta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		//labelTipoDePregunta.setText("SELECCIÓN MULTIPLE");
		labelTipoDePregunta.setHorizontalAlignment(SwingConstants.CENTER);
		panelInfoPreg.add(labelTipoDePregunta);
		
		respuestaSeleccionada = new String[examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size()];
		
		switch (formaExamen) {
		case 1: {
			respuestaSeleccionada = new String[examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size()];
			labelCantidadPreguntas.setText("Pregunta " + (iterador + 1) + " de "+ (examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size()));
			labelTipoDePregunta.setText("SELECCIÓN MULTIPLE");
			break;
		}
		case 2: {
			respuestaSeleccionada = new String[examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size()];
			labelCantidadPreguntas.setText("Pregunta " + (iterador + 1) + " de "+ (examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size()));
			labelTipoDePregunta.setText("VERDADERO O FALSO");
			break;
		}
		case 3: {
			respuestaSeleccionada = new String[examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size()];
			labelCantidadPreguntas.setText("Pregunta " + (iterador + 1) + " de "+ (examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size()));
			labelTipoDePregunta.setText("RESPUESTA CORTA");
			break;
		}
		case 4: {
			labelCantidadPreguntas.setText("Pregunta " + (iterador + 1) + " de "+ (examen.selecmulpreg.size()));
			respuestaSeleccionada = new String[examen.selecmulpreg.size()];
			labelTipoDePregunta.setText("VERDADERO O FALSO");
			break;
		}
		case 5: {
			labelCantidadPreguntas.setText("Pregunta " + (iterador + 1) + " de "+ (examen.tfpreg.size()));
			respuestaSeleccionada = new String[examen.tfpreg.size()];
			labelTipoDePregunta.setText("VERDADERO O FALSO");
			break;
		}
		case 6: {
			labelCantidadPreguntas.setText("Pregunta " + (iterador + 1) + " de "+ (examen.rcpreg.size()));
			respuestaSeleccionada = new String[examen.rcpreg.size()];
			labelTipoDePregunta.setText("RESPUESTA CORTA");
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + formaExamen);
		}
		/***************************************************************************/

		/************************* PANEL DEL ENUNCIADO ******************************/
		panelEnunciado = new JPanel(new BorderLayout());
		panelEnunciado.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelContenedorPregunta.add(panelEnunciado, BorderLayout.NORTH);

		textEnunciado = new JTextArea(8, 12);
		textEnunciado.setLineWrap(true);
		textEnunciado.setWrapStyleWord(true);
		textEnunciado.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textEnunciado.setForeground(Color.WHITE);
		textEnunciado.setEditable(false);
		// textEnunciado.setPreferredSize(new Dimension(900, 220));
		switch (formaExamen) {
		case 1:
		case 4: {
			textEnunciado.setText(examen.selecmulpreg.get(0).getEnunciado());
			break;
		}
		case 2:
		case 5: {
			textEnunciado.setText(examen.tfpreg.get(0).getEnunciado());
			break;
		}
		case 3:
		case 6: {
			textEnunciado.setText(examen.rcpreg.get(0).getEnunciado());
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + formaExamen);
		}

		scroll = new JScrollPane();
		scroll.setViewportView(textEnunciado);

		panelEnunciado.add(scroll, BorderLayout.CENTER);

		/***************************************************************************/

		/************************* PANEL DE LA RESPUESTA ******************************/
		panelContenedorRespuestasAbsoluto = new JPanel(new BorderLayout());
		panelContenedorRespuestasAbsoluto.setBorder(new EmptyBorder(20, 70, 30, 70));
		panelContenedorRespuestasAbsoluto.setPreferredSize(new Dimension(900, 250));

		panelRespuestaSelecMultiple = new JPanel(new GridLayout(5, 4, 20, 20/* 5, 4, 50, 30 */));
		

		panelRespuestaTrueFalse = new JPanel(new GridLayout(0, 2, 10, 0));
		panelRespuestaTrueFalse.setPreferredSize(panelRespuestaSelecMultiple.getPreferredSize());

		panelRespuestaCorta = new JPanel(new BorderLayout());
		panelRespuestaCorta.setBorder(new EmptyBorder(60, 20, 60, 20));
		
		switch (formaExamen) {
		case 1: 
		case 4: {
			panelContenedorRespuestasAbsoluto.add(panelRespuestaSelecMultiple, BorderLayout.CENTER);
			agregaBotonesDinamicoSelecMult(examen.selecmulpreg.get(iterador));
			break;
		}
		case 2:
		case 5: {
			panelContenedorRespuestasAbsoluto.add(panelRespuestaTrueFalse, BorderLayout.CENTER);
			agregaBotonesDinamicoTrueFalse(examen.tfpreg.get(iterador));
			break;
		}
		case 3:
		case 6: {
			panelContenedorRespuestasAbsoluto.add(panelRespuestaCorta, BorderLayout.CENTER);
			agregaTextPaneDinamico(examen.rcpreg.get(iterador));
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + formaExamen);
		}

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
					switch (formaExamen) {
					case 1:
					case 2:
					case 3: {
						if (iterador < (examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size()) - 1) {
							iterador++;
							labelCantidadPreguntas.setText("Pregunta " + (iterador + 1) + " de "
									+ (examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size()));
							AvanzaRetrocedePregunta(examen);
						}
						break;
					}
					case 4: {
						if (iterador < examen.selecmulpreg.size() - 1) {
							iterador++;
							labelCantidadPreguntas.setText("Pregunta " + (iterador + 1) + " de "+ examen.selecmulpreg.size());
							AvanzaRetrocedePregunta(examen);
						}
						break;
					}
					case 5: {
						if (iterador < examen.tfpreg.size() - 1) {
							iterador++;
							labelCantidadPreguntas.setText("Pregunta " + (iterador + 1) + " de "+ examen.tfpreg.size());
							AvanzaRetrocedePregunta(examen);
						}
						break;
					}
					case 6: {
						if (iterador < examen.rcpreg.size() - 1) {
							iterador++;
							labelCantidadPreguntas.setText("Pregunta " + (iterador + 1) + " de "+ examen.rcpreg.size());
							AvanzaRetrocedePregunta(examen);
						}
						break;
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + formaExamen);
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
					if (iterador > 0 && (formaExamen == 1 || formaExamen == 2 || formaExamen == 3)) {
						iterador--;
						labelCantidadPreguntas.setText("Pregunta " + (iterador + 1) + " de "+ (examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size()));
						panelRespuestaSelecMultiple.removeAll();
						AvanzaRetrocedePregunta(examen);
					}
					if(iterador > 0 && formaExamen == 4) {
						iterador--;
						labelCantidadPreguntas.setText("Pregunta " + (iterador + 1) + " de "+ examen.selecmulpreg.size());
						//panelRespuestaSelecMultiple.removeAll();
						AvanzaRetrocedePregunta(examen);
					}
					if(iterador > 0 && formaExamen == 5) {
						iterador--;
						labelCantidadPreguntas.setText("Pregunta " + (iterador + 1) + " de "+ examen.tfpreg.size());
						//panelRespuestaSelecMultiple.removeAll();
						AvanzaRetrocedePregunta(examen);
					}
					if(iterador > 0 && formaExamen == 6) {
						iterador--;
						labelCantidadPreguntas.setText("Pregunta " + (iterador + 1) + " de "+ examen.rcpreg.size());
						//panelRespuestaSelecMultiple.removeAll();
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

		switch (formaExamen) {
		case 1: {//selec mul - tf - rc
			if (iterador < examen.selecmulpreg.size()) {
				pack();
				SelMulActualizaPanel(examen);
			} else if (iterador >= (examen.selecmulpreg.size())
					&& iterador < (examen.selecmulpreg.size() + examen.tfpreg.size())) {
				pack();
				TrueFalseActualizaPanel(examen);
			} else if (iterador >= (examen.selecmulpreg.size() + examen.tfpreg.size())
					&& iterador < (examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size())) {
				pack();
				RespCortActualizaPanel(examen);
			}
			break;
		}
		case 2: {//tf - selec mul - rc
			if (iterador < examen.tfpreg.size()) {
				pack();
				TrueFalseActualizaPanel(examen);
			} else if (iterador >= (examen.tfpreg.size())
					&& iterador < (examen.selecmulpreg.size() + examen.tfpreg.size())) {
				pack();
				SelMulActualizaPanel(examen);
			} else if (iterador >= (examen.tfpreg.size() + examen.selecmulpreg.size())
					&& iterador < (examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size())) {
				pack();
				RespCortActualizaPanel(examen);
			}
			break;
		}
		case 3: {//rc - tf - selec mul
			if (iterador < examen.rcpreg.size()) {
				pack();
				RespCortActualizaPanel(examen);
				
			} else if (iterador >= (examen.rcpreg.size())
					&& iterador < (examen.rcpreg.size() + examen.tfpreg.size())) {
				pack();
				TrueFalseActualizaPanel(examen);
				
			} else if (iterador >= (examen.tfpreg.size() + examen.rcpreg.size())
					&& iterador < (examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size())) {
				pack();
				SelMulActualizaPanel(examen);
			}
			break;
		}
		case 4: {
			if (iterador < examen.selecmulpreg.size()) {
				pack();
				SelMulActualizaPanel(examen);
			}
			break;
		}
		case 5: {
			if (iterador < examen.tfpreg.size()) {
				pack();
				TrueFalseActualizaPanel(examen);
			}
			break;
		}
		case 6: {
			if (iterador < examen.rcpreg.size()) {
				pack();
				RespCortActualizaPanel(examen);
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + formaExamen);
		}

	}

	public void SelMulActualizaPanel(Exam examen) {
		panelContenedorRespuestasAbsoluto.removeAll();
		panelContenedorRespuestasAbsoluto.add(panelRespuestaSelecMultiple, BorderLayout.CENTER);
		panelContenedorRespuestasAbsoluto.updateUI();

		panelRespuestaSelecMultiple.removeAll();
		labelTipoDePregunta.setText("SELECCIÓN MULTIPLE");
		switch (formaExamen) {
		case 1:
		case 4: {
			textEnunciado.setText(examen.selecmulpreg.get(iterador).getEnunciado());
			agregaBotonesDinamicoSelecMult(examen.selecmulpreg.get(iterador));
			break;
		}
		case 2: {
			textEnunciado.setText(examen.selecmulpreg.get(iterador - examen.tfpreg.size()).getEnunciado());
			agregaBotonesDinamicoSelecMult(examen.selecmulpreg.get(iterador - examen.tfpreg.size()));
			break;
		}
		case 3: {
			textEnunciado.setText(examen.selecmulpreg.get(iterador - examen.tfpreg.size()- examen.rcpreg.size()).getEnunciado());
			agregaBotonesDinamicoSelecMult(examen.selecmulpreg.get(iterador - examen.tfpreg.size()- examen.rcpreg.size()));
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + formaExamen);
		}
		RbutonSeleccionado(botonesSelecMult);
	}

	public void TrueFalseActualizaPanel(Exam examen) {
		panelContenedorRespuestasAbsoluto.removeAll();
		panelContenedorRespuestasAbsoluto.add(panelRespuestaTrueFalse, BorderLayout.CENTER);
		panelContenedorRespuestasAbsoluto.updateUI();
		panelRespuestaTrueFalse.removeAll();

		labelTipoDePregunta.setText("VERDADERO O FALSO");

		switch (formaExamen) {
		case 1: {
			textEnunciado.setText(examen.tfpreg.get(iterador - examen.selecmulpreg.size()).getEnunciado());
			agregaBotonesDinamicoTrueFalse(examen.tfpreg.get(iterador - examen.selecmulpreg.size()));
			break;
		}
		case 2:
		case 5: {
			textEnunciado.setText(examen.tfpreg.get(iterador).getEnunciado());
			agregaBotonesDinamicoTrueFalse(examen.tfpreg.get(iterador));
			break;
		}
		case 3: {
			textEnunciado.setText(examen.tfpreg.get(iterador - examen.rcpreg.size()).getEnunciado());
			agregaBotonesDinamicoTrueFalse(examen.tfpreg.get(iterador - examen.rcpreg.size()));
			break;
		}
		case 4: {

			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + formaExamen);
		}
		RbutonSeleccionado(botonesTF);
	}

	public void RespCortActualizaPanel(Exam examen) {
		panelContenedorRespuestasAbsoluto.removeAll();
		panelContenedorRespuestasAbsoluto.add(panelRespuestaCorta, BorderLayout.CENTER);
		panelContenedorRespuestasAbsoluto.updateUI();
		panelRespuestaCorta.removeAll();
		labelTipoDePregunta.setText("RESPUESTA CORTA");
		switch (formaExamen) {
		case 1: {
			textEnunciado.setText(examen.rcpreg.get(iterador - examen.selecmulpreg.size() - examen.tfpreg.size()).getEnunciado());
			agregaTextPaneDinamico(examen.rcpreg.get(iterador - examen.selecmulpreg.size() - examen.tfpreg.size()));
			break;
		}
		case 2: {
			textEnunciado.setText(examen.rcpreg.get(iterador - examen.selecmulpreg.size() - examen.tfpreg.size()).getEnunciado());
			agregaTextPaneDinamico(examen.rcpreg.get(iterador - examen.selecmulpreg.size() - examen.tfpreg.size()));
			break;
		}
		case 3:
		case 6: {
			textEnunciado.setText(examen.rcpreg.get(iterador).getEnunciado());
			agregaTextPaneDinamico(examen.rcpreg.get(iterador));
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + formaExamen);
		}

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
		String[] opcion = { "Verdadero", "Falso" };

		for (int i = 0; i < ArregloRbutton.length; i++) {
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
		txtRespuestaCortaPgr
				.setText((respuestaSeleccionada[iterador] == null) ? "" : (respuestaSeleccionada[iterador]));
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
			if (s == 0) {
				s = 60;
				m--;
			}
			if (m < 0) {
				JOptionPane.showMessageDialog(rootPane, "<html>Tiempo Finalizado<br>Examen Terminado</html>");
				m = 0;
				s = 0;
				tiempo.stop();
			} else {

				s--;
				if (s < 10) {
					labelS.setText("0" + s + " minutos");
					flag = false;
				}
				if (m < 10) {
					labelM.setText("Tiempo 0" + m + ":");
					if (s < 10) {
						labelS.setText("0" + s + " minutos");
					} else {
						labelS.setText("" + s + " minutos");
					}
					flag = false;
				}
				if (flag) {
					labelM.setText("Tiempo " + m + ":");
					labelS.setText("" + s + " minutos");
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
