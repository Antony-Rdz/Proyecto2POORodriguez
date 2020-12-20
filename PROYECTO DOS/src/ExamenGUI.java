import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.Box;
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
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;

import ExamenJava.Exam;
import ExamenJava.ExamDemo;
import ExamenJava.Resp_Cortas_Pregunta;
import ExamenJava.Selec_Mul_Pregunta;
import ExamenJava.TFpreguntas;

@SuppressWarnings("serial")
public class ExamenGUI extends JFrame {
	JPanel panelMaster;
	JPanel panelEnunciado;
	JTextPane textEnunciado;
	JScrollPane scroll;
	JPanel panelRespuestaSelecMultiple;
	JPanel panelContenedorRespuestasAbsoluto;
	JPanel panelBotones;
	JButton btnSiguiente;
	JLabel labelTipoDePregunta;
	JLabel labelCantidadPreguntas;
	JLabel labelTiempo;
	ButtonGroup grupoDeBotones;
	Exam examen;
	private String[] respuestaSeleccionada;
	private ArrayList<JRadioButton> botonesSelecMult;
	private ArrayList<JRadioButton> botonesTF;
	private ArrayList<JTextPane> TextRespCortaPane;
	int iterador = 0;
	private JButton btnAnterior;
	private JPanel panelRespuestaTrueFalse;
	private JPanel panelRespuestaCorta;
	private Timer tiempo;
	private JRadioButton[] ArregloRbutton;
	
	private int m = 15, s = 0;

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(new FlatCarbonIJTheme());
		} catch (Exception ex) {
			System.err.println("Tema no aplicado");
		}
		new ExamenGUI();

	}

	public JPanel ComenzarExamen() {
		JPanel panelContenedorPrevio = new JPanel();

		return panelContenedorPrevio;
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
				new AdminGUI();
			}
		});
		Opciones.add(OpcionCerrarSesion);
		BarraOpciones.add(Opciones);
		panelMenu.add(BarraOpciones, BorderLayout.SOUTH);
		panelContenedorMenu.add(panelMenu);
		/***************************************************************************/
		return panelContenedorMenu;
	}

	public JPanel iniciador() {
		tiempo = new Timer(1000, acciones);
		tiempo.start();
		examen = ExamDemo.retornaexamen();
		
		respuestaSeleccionada = new String[examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size()];
		TextRespCortaPane = new ArrayList<JTextPane>();

		JPanel panelIniciador = new JPanel();
		panelIniciador.setBorder(new EmptyBorder(0, 20, 20, 20));
		panelIniciador.setLayout(new BoxLayout(panelIniciador, BoxLayout.Y_AXIS));

		JPanel panelAgregados = new JPanel();
		panelAgregados.setLayout(new BorderLayout());
		panelIniciador.add(panelAgregados);

		panelIniciador.add(Box.createRigidArea(new Dimension(0, 30)));

		JPanel panelContenedorPregunta = new JPanel();
		panelContenedorPregunta.setLayout(new BoxLayout(panelContenedorPregunta, BoxLayout.Y_AXIS));
		panelIniciador.add(panelContenedorPregunta);

		panelIniciador.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelContenedorBotones = new JPanel();
		panelContenedorBotones.setLayout(new BoxLayout(panelContenedorBotones, BoxLayout.Y_AXIS));
		panelIniciador.add(panelContenedorBotones);

		/************************ PANEL DE AGREGADOS ****************************/
		JPanel panelInfoPreg = new JPanel();
		panelInfoPreg.setLayout(new BoxLayout(panelInfoPreg, BoxLayout.Y_AXIS));

		JPanel panelTiempo = new JPanel();
		panelTiempo.setLayout(new BoxLayout(panelTiempo, BoxLayout.Y_AXIS));

		panelTiempo.add(Box.createRigidArea(new Dimension(160, 0)));

		JPanel panelBotonFinalizar = new JPanel();

		panelAgregados.add(panelInfoPreg, BorderLayout.WEST);
		panelAgregados.add(panelTiempo, BorderLayout.CENTER);
		panelAgregados.add(panelBotonFinalizar, BorderLayout.EAST);
		panelBotonFinalizar.setLayout(new BorderLayout(0, 0));

		JButton botonFinalizar = new JButton("Finalizar");
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
		botonFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelBotonFinalizar.add(botonFinalizar);

		labelTiempo = new JLabel();
		labelTiempo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTiempo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		actualizaLabel();
		botonFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelTiempo.add(labelTiempo);

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
		panelEnunciado = new JPanel();
		panelEnunciado.setBackground(Color.WHITE);
		panelEnunciado.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelContenedorPregunta.add(panelEnunciado, BorderLayout.NORTH);

		panelContenedorPregunta.add(Box.createRigidArea(new Dimension(0, 30)));

		textEnunciado = new JTextPane();
		textEnunciado.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textEnunciado.setEditable(false);

		scroll = new JScrollPane();
		textEnunciado.setText(examen.selecmulpreg.get(0).getEnunciado());
		scroll.setViewportView(textEnunciado);
		textEnunciado.setPreferredSize(new Dimension(900, 220));
		panelEnunciado.add(scroll, BorderLayout.CENTER);
		/***************************************************************************/

		/************************* PANEL DE LA RESPUESTA ******************************/
		panelContenedorRespuestasAbsoluto = new JPanel();
		panelContenedorRespuestasAbsoluto.setBorder(new EmptyBorder(25, 70, 30, 70));
		panelContenedorRespuestasAbsoluto.setLayout(new CardLayout(0, 0));

		panelRespuestaSelecMultiple = new JPanel(new GridLayout(5, 4, 10, 30));
		agregaBotonesDinamicoSelecMult(examen.selecmulpreg.get(iterador));
		panelContenedorRespuestasAbsoluto.add(panelRespuestaSelecMultiple, "seleccionMultiple");

		panelRespuestaTrueFalse = new JPanel(new GridLayout(0, 2, 10, 0));
		panelRespuestaTrueFalse.setPreferredSize(panelRespuestaSelecMultiple.getPreferredSize());
		panelContenedorRespuestasAbsoluto.add(panelRespuestaTrueFalse, "verdaderoFalso");

		panelRespuestaCorta = new JPanel();
		panelContenedorRespuestasAbsoluto.add(panelRespuestaCorta, "respuestaCorta");
		panelRespuestaCorta.setLayout(new BorderLayout(0, 0));

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
						AvanzaRetrocedePregunta();
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
						AvanzaRetrocedePregunta();
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

	public ExamenGUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelMaster = new JPanel();

		panelMaster.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelMaster.setLayout(new BoxLayout(panelMaster, BoxLayout.Y_AXIS));
		setContentPane(panelMaster);
		// panelMaster.add(ComenzarExamen());
		panelMaster.add(BarraMenu());
		panelMaster.add(Box.createRigidArea(new Dimension(0, 20)));
		panelMaster.add(iniciador());
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

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
	}


	public void agregaTextPaneDinamico(Resp_Cortas_Pregunta pregunta) {
		JTextPane txtRespuestaCortaPgr = new JTextPane();
		txtRespuestaCortaPgr.setText((respuestaSeleccionada[iterador] == null) ? "" : (respuestaSeleccionada[iterador]));
		txtRespuestaCortaPgr.setBackground(Color.WHITE);
		txtRespuestaCortaPgr.setForeground(Color.BLACK);
		txtRespuestaCortaPgr.setFont(new Font("Thaoma", Font.BOLD, 23));
		JScrollPane scrollRespCorta = new JScrollPane();
		scrollRespCorta.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollRespCorta.setViewportView(txtRespuestaCortaPgr);
		txtRespuestaCortaPgr.setPreferredSize(new Dimension(700, 100));
		TextRespCortaPane.add(txtRespuestaCortaPgr);
		panelRespuestaCorta.add(scrollRespCorta, BorderLayout.CENTER);

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
	}

	void AvanzaRetrocedePregunta() {
		CardLayout c = (CardLayout) (panelContenedorRespuestasAbsoluto.getLayout());
		if (iterador < examen.selecmulpreg.size()) {
			c.show(panelContenedorRespuestasAbsoluto, "seleccionMultiple");
			SelMulActualizaPanel();
			

		} else if (iterador >= (examen.selecmulpreg.size()) && iterador < (examen.selecmulpreg.size() + examen.tfpreg.size())) {
			c.show(panelContenedorRespuestasAbsoluto, "verdaderoFalso");
			TrueFalseActualizaPanel();

		} else if (iterador >= (examen.selecmulpreg.size() + examen.tfpreg.size())&& iterador < (examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size())) {
			c.show(panelContenedorRespuestasAbsoluto, "respuestaCorta");
			RespCortActualizaPanel();
		}
	}
	
	
	public void SelMulActualizaPanel() {
		panelRespuestaSelecMultiple.removeAll();
		labelTipoDePregunta.setText("SELECCIÓN MULTIPLE");
		textEnunciado.setText(examen.selecmulpreg.get(iterador).getEnunciado());
		agregaBotonesDinamicoSelecMult(examen.selecmulpreg.get(iterador));
		RbutonSeleccionado(botonesSelecMult);
	}
	
	public void TrueFalseActualizaPanel() {
		panelRespuestaTrueFalse.removeAll();
		labelTipoDePregunta.setText("VERDADERO O FALSO");
		textEnunciado.setText(examen.tfpreg.get(iterador - examen.selecmulpreg.size()).getEnunciado());
		agregaBotonesDinamicoTrueFalse(examen.tfpreg.get(iterador - examen.selecmulpreg.size()));
		RbutonSeleccionado(botonesTF);
	}
	
	public void RespCortActualizaPanel() {
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

	private ActionListener acciones = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			/*
			 * if(m == 0) { if (h >= 0 && m != 0) { m = 60; } --h; if(h<=0) { h= 0; } }para
			 * despues
			 */
			if (s == 0) {
				s = 60;
				--m;
				if (m <= 0) {
					m = 0;

				}
			}
			s--;
			actualizaLabel();
			if (m == 0 && s == 0) {
				JOptionPane.showMessageDialog(null, "<html>Tiempo Finalizado<br>Examen Terminado</html>");
				System.exit(0);
			}
		}
	};

	private void actualizaLabel() {
		String MinToSeg = " minutos";
		String time = (m <= 9 ? "0" : "") + m + ":" + (s <= 9 ? "0" : "") + s;
		if (m == 0) {
			MinToSeg = " segundos";
		}
		labelTiempo.setText("Tiempo restante " + time + MinToSeg);
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

}
