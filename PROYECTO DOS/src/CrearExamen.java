import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import com.placeholder.PlaceHolder;

import ExamenJava.Exam;
import ExamenJava.Selec_Mul_Pregunta;

public class CrearExamen extends JFrame {

	JPanel panelEnunciado;
	JTextPane textEnunciado;
	JTextField textPeso;
	JScrollPane scroll;
	JPanel panelMayorCrearExamen;
	JPanel panelRespuestas;
	JPanel panelAlternativas;
	JPanel panelRdBotones;
	JPanel panelAlternativasInterno;
	ArrayList<JRadioButton> esCorrecta;
	ArrayList<JTextField> respuestasDeLaPregunta;
	ButtonGroup grupoCorrecto;
	boolean activador = false;
	int letra = 65;
	////////////////////////////////
	Exam examenCreado;
	/////////////////////////////////
	String enunciadoDeLaPregunta;
	int pesoDeLaPregunta;

	String respuestaCorrectaSelecMul;
	int indexCorrectoSelecMul;
	String[] alternativas;

	boolean respuestaVerdFalso;

	String respuestaRespCorta;
	/////////////////////////////////

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatCarbonIJTheme());
		} catch (Exception ex) {
			System.err.println("Tema no aplicado");
		}
		CrearExamen asd = new CrearExamen();
	}

	public CrearExamen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelMayorCrearExamen = new JPanel();
		panelMayorCrearExamen.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelMayorCrearExamen.setLayout(new BorderLayout(10, 10));
		setContentPane(panelMayorCrearExamen);
		panelMayorCrearExamen.add(panelIniciador());
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private JPanel panelIniciador() {

		examenCreado = new Exam();

		JPanel panelContenedorCreaExamen = new JPanel(new BorderLayout());
		panelContenedorCreaExamen.setBorder(new EmptyBorder(0, 20, 20, 20));

		JPanel panelTitulo = new JPanel(new BorderLayout(30, 30));
		panelTitulo.setBorder(new EmptyBorder(20, 20, 20, 20));
		JPanel panelCreador = new JPanel();

		JPanel panelTiempo = new JPanel(new BorderLayout());
		JPanel panelTimeInterno = new JPanel(new BorderLayout());
		panelTimeInterno.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelTiempo.add(panelTimeInterno, BorderLayout.WEST);
		panelTitulo.add(panelTiempo, BorderLayout.SOUTH);

		JPanel panelBotonesCrearPreguntas = new JPanel(new BorderLayout());// new GridLayout(0,3,12,12));
		panelBotonesCrearPreguntas.setBorder(new EmptyBorder(0, 50, 0, 50));

		JPanel panelLabelBotones = new JPanel(new BorderLayout());
		panelLabelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelBotonesCrearPreguntas.add(panelLabelBotones, BorderLayout.NORTH);

		JPanel panelBotonesCreador = new JPanel(new GridLayout(0, 3, 12, 12));
		panelBotonesCreador.setBorder(new EmptyBorder(5, 50, 5, 50));
		panelBotonesCrearPreguntas.add(panelBotonesCreador, BorderLayout.SOUTH);

		panelContenedorCreaExamen.add(panelTitulo, BorderLayout.NORTH);
		panelContenedorCreaExamen.add(panelCreador, BorderLayout.CENTER);
		panelContenedorCreaExamen.add(panelBotonesCrearPreguntas, BorderLayout.SOUTH);

		JLabel labelTiempo = new JLabel("Tiempo: ");
		labelTiempo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelTimeInterno.add(labelTiempo, BorderLayout.WEST);

		JTextField textTiempo = new JTextField();
		textTiempo.setBackground(Color.WHITE);
		textTiempo.setForeground(Color.BLACK);
		textTiempo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char letraIngresada = e.getKeyChar();
				if (!((letraIngresada >= '0') && (letraIngresada <= '9')) || (letraIngresada == KeyEvent.VK_BACK_SPACE)
						|| (letraIngresada == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		PlaceHolder holder = new PlaceHolder(textTiempo, Color.GRAY, Color.BLACK, "En minutos", false, "Thaoma", 15);
		panelTimeInterno.add(textTiempo, BorderLayout.CENTER);

		JLabel label = new JLabel("Creador de Examenes :");
		// label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelTitulo.add(label, BorderLayout.WEST);

		JButton botonFinalizar = new JButton("Finalizar");
		botonFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTitulo.add(botonFinalizar, BorderLayout.EAST);

		// panelCreador.add(panelCreadorSeleccionMultiple());
		// panelCreador.add(panelCreadorVerdaderoFalso());
		// panelCreador.add(panelCreadorRespuestaCorta());
		// panelCreador.removeAll();

		JLabel labelBotones = new JLabel("Agregar pregunta");
		labelBotones.setHorizontalAlignment(SwingConstants.CENTER);
		labelBotones.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelLabelBotones.add(labelBotones, BorderLayout.CENTER);

		JButton botonCrearSelecMul = new JButton("Seleccion Multiple");
		botonCrearSelecMul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (CamposVacios()) {
					letra = 65;
					panelCreador.removeAll();
					panelCreador.add(panelCreadorSeleccionMultiple());
					pack();
					setLocationRelativeTo(null);
					panelCreador.updateUI();
				} else {
					JOptionPane.showMessageDialog(null, "Tiene Cambios Sin Guardar");
				}

			}
		});
		botonCrearSelecMul.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelBotonesCreador.add(botonCrearSelecMul);

		JButton botonVerdaderoFalso = new JButton("Verdadero o Falso");
		botonVerdaderoFalso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (CamposVacios()) {
					panelCreador.removeAll();
					panelCreador.add(panelCreadorVerdaderoFalso());
					pack();
					setLocationRelativeTo(null);
					panelCreador.updateUI();
				} else {
					JOptionPane.showMessageDialog(null, "Tiene Cambios Sin Guardar");
				}
			}
		});
		botonVerdaderoFalso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelBotonesCreador.add(botonVerdaderoFalso);

		JButton botonRespuestCorta = new JButton("Respuesta Corta");
		botonRespuestCorta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (CamposVacios()) {
					panelCreador.removeAll();
					panelCreador.add(panelCreadorRespuestaCorta());
					pack();
					setLocationRelativeTo(null);
					panelCreador.updateUI();
				} else {
					JOptionPane.showMessageDialog(null, "Tiene Cambios Sin Guardar");
				}
			}
		});
		botonRespuestCorta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelBotonesCreador.add(botonRespuestCorta);

		return panelContenedorCreaExamen;
	}

	private JPanel panelCreadorSeleccionMultiple() {

		grupoCorrecto = new ButtonGroup();
		esCorrecta = new ArrayList<>();
		respuestasDeLaPregunta = new ArrayList<>();

		JPanel panelCreadorSelecMulti = new JPanel(new BorderLayout());
		panelCreadorSelecMulti.setBorder(BorderFactory.createLineBorder(new Color(33, 48, 71), 2, true));
		panelCreadorSelecMulti.setLayout(new BoxLayout(panelCreadorSelecMulti, BoxLayout.Y_AXIS));

		/************************* PANEL DEL ENUNCIADO ******************************/
		panelEnunciado = new JPanel(new BorderLayout(20, 10));
		panelEnunciado.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelCreadorSelecMulti.add(panelEnunciado, BorderLayout.NORTH);

		JPanel panelEnunciadoSuperior = new JPanel(new BorderLayout());
		panelEnunciado.add(panelEnunciadoSuperior, BorderLayout.NORTH);

		JLabel lblAyudaEnunciado = new JLabel("Enunciado de la pregunta:");
		lblAyudaEnunciado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelEnunciadoSuperior.add(lblAyudaEnunciado, BorderLayout.WEST);

		JLabel lblPeso = new JLabel("Puntaje : ");
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPeso.setHorizontalAlignment(SwingConstants.RIGHT);
		panelEnunciadoSuperior.add(lblPeso, BorderLayout.CENTER);
		textPeso = new JTextField();
		textPeso.setBackground(Color.WHITE);
		textPeso.setForeground(Color.BLACK);
		textPeso.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPeso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char letraIngresada = e.getKeyChar();
				if (!((letraIngresada >= '0') && (letraIngresada <= '9')) || (letraIngresada == KeyEvent.VK_BACK_SPACE)
						|| (letraIngresada == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		panelEnunciadoSuperior.add(textPeso, BorderLayout.EAST);

		textEnunciado = new JTextPane();
		textEnunciado.setBackground(Color.WHITE);
		textEnunciado.setForeground(Color.BLACK);
		textEnunciado.setFont(new Font("Tahoma", Font.PLAIN, 20));

		scroll = new JScrollPane();
		scroll.setViewportView(textEnunciado);
		textEnunciado.setPreferredSize(new Dimension(900, 100));
		panelEnunciado.add(scroll, BorderLayout.CENTER);
		/***************************************************************************/

		/************************* PANEL DE LA RESPUESTA ***************************/

		panelRespuestas = new JPanel(new BorderLayout(20, 10));
		panelRespuestas.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelCreadorSelecMulti.add(panelRespuestas, BorderLayout.SOUTH);

		JLabel lblAyudaRespuestas = new JLabel("Ingrese las respuestas: ");
		lblAyudaRespuestas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelRespuestas.add(lblAyudaRespuestas, BorderLayout.NORTH);

		panelAlternativas = new JPanel();
		panelAlternativas.setLayout(new BoxLayout(panelAlternativas, BoxLayout.Y_AXIS));
		panelAlternativas.setPreferredSize(new Dimension(10, 300));

		JPanel panelCentralAlternativas = new JPanel(new BorderLayout());
		panelRespuestas.add(panelCentralAlternativas, BorderLayout.CENTER);

		JScrollPane scrllBotones = new JScrollPane();
		scrllBotones.setPreferredSize(new Dimension(20, 200));

		panelCentralAlternativas.add(scrllBotones, BorderLayout.CENTER);

		panelRdBotones = new JPanel();
		panelRdBotones.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelRdBotones.setLayout(new BoxLayout(panelRdBotones, BoxLayout.Y_AXIS));
		scrllBotones.setViewportView(panelRdBotones);
		agregaAlternativa(grupoCorrecto);
		agregaAlternativa(grupoCorrecto);
		agregaAlternativa(grupoCorrecto);

		JButton botonAgregaMasAlternativas = new JButton("Agregar Alternativa");
		botonAgregaMasAlternativas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (letra <= 79) {
					agregaAlternativa(grupoCorrecto);

				} else {
					JOptionPane.showMessageDialog(null, "Alternativas maxima alcanzada");
				}

			}
		});

		JButton botonGuardar = new JButton("Guardar Pregunta");
		botonGuardar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				if (examenCreado != null) {

					if (textEnunciado == null || textEnunciado.getText().isBlank()) {
						JOptionPane.showMessageDialog(null, new JLabel("Enunciado Vacio"), "ERROR CRITICO",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (textPeso == null || textPeso.getText().isBlank()) {
						JOptionPane.showMessageDialog(null, new JLabel("Peso Vacio"), "ERROR CRITICO",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (respuestasDeLaPregunta.get(indexCorrectoSelecMul) == null
							|| respuestasDeLaPregunta.get(indexCorrectoSelecMul).getText().isBlank()) {
						JOptionPane.showMessageDialog(null, new JLabel("Casilla Correcta Vacia"), "ERROR CRITICO",
								JOptionPane.ERROR_MESSAGE);
						grupoCorrecto.clearSelection();
						return;
					}

					if (grupoCorrecto == null || grupoCorrecto.getSelection() == null) {
						JOptionPane.showMessageDialog(null, new JLabel("Seleccione Respuesta Correcta"),
								"ERROR CRITICO", JOptionPane.ERROR_MESSAGE);
						return;
					}

					List<String> temp = new ArrayList<String>();

					for (int i = 0; i < respuestasDeLaPregunta.size(); i++) {
						if (respuestasDeLaPregunta.get(i).getText() != null
								&& !respuestasDeLaPregunta.get(i).getText().isBlank()) {
							temp.add(respuestasDeLaPregunta.get(i).getText());
						}
					}

					for (int i = 0; i < temp.size(); i++) {
						if (temp.get(i).equals(respuestasDeLaPregunta.get(indexCorrectoSelecMul).getText())) {
							indexCorrectoSelecMul = i;
							break;
						}
					}
					alternativas = temp.toArray(new String[0]);

					if (alternativas == null || alternativas.length < 3) {
						JOptionPane.showMessageDialog(null, new JLabel("Agrege 3 Alternativas Válidas"),
								"ERROR CRITICO", JOptionPane.ERROR_MESSAGE);
						return;
					}

					enunciadoDeLaPregunta = textEnunciado.getText();
					pesoDeLaPregunta = Integer.parseInt(textPeso.getText());

					vaciaTextSelecMul();

					System.out.println("enunciado: " + enunciadoDeLaPregunta);
					for (String a : alternativas) {
						System.out.print(a + ",");
					}
					System.out.println("\nresp correcta: " + respuestaCorrectaSelecMul);
					System.out.println("index: " + indexCorrectoSelecMul);
					System.out.println("peso: " + pesoDeLaPregunta);

					examenCreado.agregaPregunta(new Selec_Mul_Pregunta(enunciadoDeLaPregunta, alternativas,indexCorrectoSelecMul, pesoDeLaPregunta));
					grupoCorrecto.clearSelection();
				} else {
					JOptionPane.showMessageDialog(null, new JLabel("Examen Vacio"), "ERROR CRITICO",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPanel panelBotonesSelecMul = new JPanel(new BorderLayout());
		panelRespuestas.add(panelBotonesSelecMul, BorderLayout.SOUTH);

		panelBotonesSelecMul.add(botonAgregaMasAlternativas, BorderLayout.WEST);
		panelBotonesSelecMul.add(botonGuardar, BorderLayout.EAST);

		/***************************************************************************/

		return panelCreadorSelecMulti;
	}

	private JPanel panelCreadorVerdaderoFalso() {
		grupoCorrecto = new ButtonGroup();

		JPanel panelCreadorVerdaderoFalso = new JPanel(new BorderLayout());
		panelCreadorVerdaderoFalso.setBorder(BorderFactory.createLineBorder(new Color(33, 48, 71), 2, true));
		panelCreadorVerdaderoFalso.setLayout(new BoxLayout(panelCreadorVerdaderoFalso, BoxLayout.Y_AXIS));

		/************************* PANEL DEL ENUNCIADO ******************************/
		panelEnunciado = new JPanel(new BorderLayout(20, 10));
		panelEnunciado.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelCreadorVerdaderoFalso.add(panelEnunciado, BorderLayout.NORTH);

		JPanel panelEnunciadoSuperior = new JPanel(new BorderLayout());
		panelEnunciado.add(panelEnunciadoSuperior, BorderLayout.NORTH);

		JLabel lblAyudaEnunciado = new JLabel("Enunciado de la pregunta:");
		lblAyudaEnunciado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelEnunciadoSuperior.add(lblAyudaEnunciado, BorderLayout.WEST);

		JLabel lblPeso = new JLabel("Puntaje : ");
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPeso.setHorizontalAlignment(SwingConstants.RIGHT);
		panelEnunciadoSuperior.add(lblPeso, BorderLayout.CENTER);
		textPeso = new JTextField();
		textPeso.setBackground(Color.WHITE);
		textPeso.setForeground(Color.BLACK);
		textPeso.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPeso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char letraIngresada = e.getKeyChar();
				if (!((letraIngresada >= '0') && (letraIngresada <= '9')) || (letraIngresada == KeyEvent.VK_BACK_SPACE)
						|| (letraIngresada == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		panelEnunciadoSuperior.add(textPeso, BorderLayout.EAST);

		textEnunciado = new JTextPane();
		textEnunciado.setBackground(Color.WHITE);
		textEnunciado.setForeground(Color.BLACK);
		textEnunciado.setFont(new Font("Tahoma", Font.PLAIN, 20));

		scroll = new JScrollPane();
		scroll.setViewportView(textEnunciado);
		textEnunciado.setPreferredSize(new Dimension(900, 100));
		panelEnunciado.add(scroll, BorderLayout.CENTER);
		/***************************************************************************/

		/************************* PANEL DE LA RESPUESTA ***************************/

		panelRespuestas = new JPanel(new BorderLayout(20, 10));
		panelRespuestas.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelCreadorVerdaderoFalso.add(panelRespuestas, BorderLayout.SOUTH);

		JLabel lblAyudaRespuestas = new JLabel("Respuesta: ");
		lblAyudaRespuestas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelRespuestas.add(lblAyudaRespuestas, BorderLayout.NORTH);

		JPanel panelCentralVerdaFalso = new JPanel(new GridLayout(0, 2, 10, 10));
		panelCentralVerdaFalso.setBorder(new EmptyBorder(30, 30, 30, 30));
		panelRespuestas.add(panelCentralVerdaFalso, BorderLayout.CENTER);

		JRadioButton rdbVerdadero = new JRadioButton("Verdadero");
		rdbVerdadero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbVerdadero.setHorizontalAlignment(SwingConstants.CENTER);
		rdbVerdadero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(getSelectedButtonText(grupoCorrecto));
			}
		});
		grupoCorrecto.add(rdbVerdadero);
		panelCentralVerdaFalso.add(rdbVerdadero);

		JRadioButton rdbFalso = new JRadioButton("Falso");
		rdbFalso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbFalso.setHorizontalAlignment(SwingConstants.CENTER);
		rdbFalso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(getSelectedButtonText(grupoCorrecto));
			}
		});
		grupoCorrecto.add(rdbFalso);
		panelCentralVerdaFalso.add(rdbFalso);

		JButton botonGuardar = new JButton("Guardar Pregunta");

		JPanel panelBotonGuardar = new JPanel(new BorderLayout());
		panelRespuestas.add(panelBotonGuardar, BorderLayout.SOUTH);
		panelBotonGuardar.add(botonGuardar, BorderLayout.EAST);

		/***************************************************************************/
		return panelCreadorVerdaderoFalso;
	}

	private JPanel panelCreadorRespuestaCorta() {
		JPanel panelCreadorRespCorta = new JPanel(new BorderLayout());
		panelCreadorRespCorta.setBorder(BorderFactory.createLineBorder(new Color(33, 48, 71), 2, true));
		panelCreadorRespCorta.setLayout(new BoxLayout(panelCreadorRespCorta, BoxLayout.Y_AXIS));

		/************************* PANEL DEL ENUNCIADO ******************************/
		panelEnunciado = new JPanel(new BorderLayout(20, 10));
		panelEnunciado.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelCreadorRespCorta.add(panelEnunciado, BorderLayout.NORTH);

		JPanel panelEnunciadoSuperior = new JPanel(new BorderLayout());
		panelEnunciado.add(panelEnunciadoSuperior, BorderLayout.NORTH);

		JLabel lblAyudaEnunciado = new JLabel("Enunciado de la pregunta:");
		lblAyudaEnunciado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelEnunciadoSuperior.add(lblAyudaEnunciado, BorderLayout.WEST);

		JLabel lblPeso = new JLabel("Valor: ");
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPeso.setHorizontalAlignment(SwingConstants.RIGHT);
		panelEnunciadoSuperior.add(lblPeso, BorderLayout.CENTER);
		JTextField textPeso = new JTextField();
		textPeso.setBackground(Color.WHITE);
		textPeso.setForeground(Color.BLACK);
		textPeso.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPeso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char letraIngresada = e.getKeyChar();
				if (!((letraIngresada >= '0') && (letraIngresada <= '9')) || (letraIngresada == KeyEvent.VK_BACK_SPACE)
						|| (letraIngresada == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
		panelEnunciadoSuperior.add(textPeso, BorderLayout.EAST);

		textEnunciado = new JTextPane();
		textEnunciado.setBackground(Color.WHITE);
		textEnunciado.setForeground(Color.BLACK);
		textEnunciado.setFont(new Font("Tahoma", Font.PLAIN, 20));

		scroll = new JScrollPane();
		scroll.setViewportView(textEnunciado);
		textEnunciado.setPreferredSize(new Dimension(900, 100));
		panelEnunciado.add(scroll, BorderLayout.CENTER);
		/***************************************************************************/

		/************************* PANEL DE LA RESPUESTA ***************************/

		panelRespuestas = new JPanel(new BorderLayout(20, 10));
		panelRespuestas.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelCreadorRespCorta.add(panelRespuestas, BorderLayout.SOUTH);

		JTextPane txtRespuestaCortaPgr = new JTextPane();
		txtRespuestaCortaPgr.setBackground(Color.WHITE);
		txtRespuestaCortaPgr.setForeground(Color.BLACK);
		txtRespuestaCortaPgr.setFont(new Font("Thaoma", Font.PLAIN, 20));

		JLabel lblAyudaRespuestas = new JLabel("Respuesta: ");
		lblAyudaRespuestas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelRespuestas.add(lblAyudaRespuestas, BorderLayout.NORTH);

		JScrollPane scrollRespCorta = new JScrollPane();
		scrollRespCorta.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollRespCorta.setViewportView(txtRespuestaCortaPgr);
		txtRespuestaCortaPgr.setPreferredSize(new Dimension(700, 100));
		panelRespuestas.add(scrollRespCorta, BorderLayout.CENTER);

		JButton botonGuardar = new JButton("Guardar Pregunta");
		botonGuardar.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JPanel panelBotonGuardar = new JPanel(new BorderLayout());
		panelRespuestas.add(panelBotonGuardar, BorderLayout.SOUTH);
		panelBotonGuardar.add(botonGuardar, BorderLayout.EAST);

		/***************************************************************************/

		return panelCreadorRespCorta;
	}

	private void agregaAlternativa(ButtonGroup grupoCorrecto) {
		String letraAux = Character.toString((char) letra);
		JPanel panel = new JPanel();
		panelRdBotones.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(20, 20, 20, 200));
		JTextField alternativa = new JTextField();
		alternativa.setBackground(Color.WHITE);
		alternativa.setForeground(Color.BLACK);
		alternativa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JRadioButton correcto = new JRadioButton(" " + letraAux + " ");

		correcto.addActionListener(new ActionListener() {
			int i = 0;

			public void actionPerformed(ActionEvent arg0) {
				try {
					for (JRadioButton alternativa : esCorrecta) {
						if (alternativa != null && alternativa.isSelected()) {
							if (respuestasDeLaPregunta.get(i) != null
									&& !respuestasDeLaPregunta.get(i).getText().isBlank()) {
								System.out.println(respuestasDeLaPregunta.get(i).getText());
								respuestaCorrectaSelecMul = respuestasDeLaPregunta.get(i).getText();
								indexCorrectoSelecMul = i;
							} else {
								grupoCorrecto.clearSelection();
								JOptionPane.showMessageDialog(null,
										new JLabel("<html>Alternativa Vacia<br>Agregue un Texto</html>"), "Error",
										JOptionPane.ERROR_MESSAGE);

								i = 0;
								return;
							}
							i = 0;
							break;
						}

						i++;
					}

				} catch (Exception e) {
					System.out.println(e.toString());
				}

			}
		});
		letra++;
		grupoCorrecto.add(correcto);
		panel.add(correcto, BorderLayout.WEST);
		panel.add(alternativa, BorderLayout.CENTER);
		esCorrecta.add(correcto);
		respuestasDeLaPregunta.add(alternativa);
		panelRdBotones.updateUI();
	}

	public String getSelectedButtonText(ButtonGroup buttonGroup) {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}
		return null;
	}

	public boolean CamposVacios() {
		if (textPeso == null || textPeso.getText().isBlank() && textEnunciado == null
				|| textEnunciado.getText().isBlank()) {
			return true;
		} else {
			return false;
		}
	}

	public void vaciaTextSelecMul() {
		for (JTextField resp : respuestasDeLaPregunta) {
			resp.setText(null);
		}
		for (JRadioButton rdb : esCorrecta) {
			rdb.setSelected(false);
		}
		textEnunciado.setText(null);
		textPeso.setText(null);
	}

}
