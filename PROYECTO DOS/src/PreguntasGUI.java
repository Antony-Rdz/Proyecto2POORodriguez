import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ExamenJava.Exam;
import ExamenJava.ExamDemo;
import ExamenJava.Selec_Mul_Pregunta;
import ExamenJava.TFpreguntas;

public class PreguntasGUI extends JFrame {
	JPanel panelMaster;
	JPanel panelEnunciado;

	JTextPane textEnunciado;
	JScrollPane scroll;
	JPanel panelRespuestaSelecMultiple;
	JPanel panelContenedorRespuestasAbsoluto;
	JPanel panelBotones;
	JButton btnSiguiente;
	ButtonGroup grupoDeBotones;
	GroupLayout gl_panelOpciones;
	Exam examen;
	private String[] respuestaSeleccionada;
	private ArrayList<JRadioButton> botonesSelecMult;
	private ArrayList<JRadioButton> botonesTF;

	int iterador = 0;
	private JButton btnAnterior;
	private JPanel panelRespuestaTrueFalse;
	private JPanel panelRespuestaCorta;
	private JRadioButton rdbVerdadero;
	private JRadioButton rdbFalso;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JTextArea textArea;
	private JTextArea txtRespuestaCortaPgr;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreguntasGUI frame = new PreguntasGUI();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JPanel iniciador() {
		examen = ExamDemo.retornaexamen();
		grupoDeBotones = new ButtonGroup();
		respuestaSeleccionada = new String[examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size()];

		JPanel panelIniciador = new JPanel();
		// panelIniciador.setPreferredSize(new Dimension(950, 700));
		panelIniciador.setLayout(new BoxLayout(panelIniciador, BoxLayout.Y_AXIS));

		JPanel panelOpciones = new JPanel();
		panelOpciones.setLayout(null);
		panelOpciones.setBackground(SystemColor.control);
		// panelOpciones.setPreferredSize(new Dimension(800, 40));
		gl_panelOpciones = new GroupLayout(panelOpciones);
		gl_panelOpciones.setHorizontalGroup(gl_panelOpciones.createParallelGroup(Alignment.LEADING).addGap(0, 950, Short.MAX_VALUE));
		gl_panelOpciones.setVerticalGroup(gl_panelOpciones.createParallelGroup(Alignment.TRAILING).addGap(0, 40, Short.MAX_VALUE));
		panelOpciones.setLayout(gl_panelOpciones);
		panelIniciador.add(panelOpciones);

		panelIniciador.add(Box.createRigidArea(new Dimension(0, 20)));

		JPanel panelContenedorPregunta = new JPanel();
		panelContenedorPregunta.setLayout(new BoxLayout(panelContenedorPregunta, BoxLayout.Y_AXIS));
		panelIniciador.add(panelContenedorPregunta);

		panelIniciador.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelContenedorBotones = new JPanel();
		panelContenedorBotones.setLayout(new BoxLayout(panelContenedorBotones, BoxLayout.Y_AXIS));
		panelIniciador.add(panelContenedorBotones);

		/************************* PANEL DEL ENUNCIADO ******************************/
		panelEnunciado = new JPanel();
		panelEnunciado.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelEnunciado.setBackground(SystemColor.control);
		// panelEnunciado.setPreferredSize(new Dimension(800, 350));
		panelContenedorPregunta.add(panelEnunciado, BorderLayout.NORTH);

		panelContenedorPregunta.add(Box.createRigidArea(new Dimension(0, 30)));

		textEnunciado = new JTextPane();
		textEnunciado.setFont(new Font("Arial", Font.PLAIN, 25));
		textEnunciado.setBackground(Color.WHITE);
		textEnunciado.setDisabledTextColor(Color.BLACK);
		textEnunciado.setEditable(false);
		// String enunciado = .replaceAll("(.{80})", "$1\n");
		scroll = new JScrollPane();
		textEnunciado.setText(examen.selecmulpreg.get(0).getEnunciado());
		scroll.setViewportView(textEnunciado);
		textEnunciado.setPreferredSize(new Dimension(900, 220));
		panelEnunciado.add(scroll, BorderLayout.CENTER);
		/***************************************************************************/

		/************************* PANEL DE LA RESPUESTA ******************************/
		panelContenedorRespuestasAbsoluto = new JPanel();
		panelContenedorRespuestasAbsoluto.setBackground(SystemColor.control);
		panelContenedorRespuestasAbsoluto.setBorder(new EmptyBorder(25, 70, 30, 70));
		panelContenedorRespuestasAbsoluto.setLayout(new CardLayout(0, 0));

		panelRespuestaSelecMultiple = new JPanel(new GridLayout(0, 2, 10, 30));
		panelRespuestaSelecMultiple.setBackground(SystemColor.control);
		// panelRespuestaSelecMultiple.setPreferredSize(new Dimension(200, 200));
		agregaBotonesDinamicoSelecMult(examen.selecmulpreg.get(iterador));
		panelContenedorRespuestasAbsoluto.add(panelRespuestaSelecMultiple, "seleccionMultiple");

		panelRespuestaTrueFalse = new JPanel(new GridLayout(0, 2, 10, 0));
		panelRespuestaTrueFalse.setBackground(SystemColor.control);
		// panelRespuestaTrueFalse.setPreferredSize(new Dimension(200, 200));
		panelContenedorRespuestasAbsoluto.add(panelRespuestaTrueFalse, "verdaderoFalso");

		panelRespuestaCorta = new JPanel();
		// panelRespuestaCorta.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelRespuestaCorta.setBackground(SystemColor.control);
		panelContenedorRespuestasAbsoluto.add(panelRespuestaCorta, "respuestaCorta");
		panelRespuestaCorta.setLayout(new BorderLayout(0, 0));

		txtRespuestaCortaPgr = new JTextArea();
		txtRespuestaCortaPgr.setFont(new Font("Calibri", Font.BOLD, 23));
		// txtRespuestaCortaPgr.setPreferredSize(new Dimension(700, 250));
		panelRespuestaCorta.add(txtRespuestaCortaPgr, BorderLayout.CENTER);

		panelContenedorPregunta.add(panelContenedorRespuestasAbsoluto, BorderLayout.CENTER);

		/***************************************************************************/

		/************************* PANEL DE LOS BOTONES ****************************/
		panelBotones = new JPanel(new GridLayout(0, 8, 0, 0));
		// panelBotones.setPreferredSize(new Dimension(22, 30));
		panelContenedorBotones.add(panelBotones, BorderLayout.SOUTH);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					iterador++;
					AvanzaRetrocedePregunta(iterador);
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		});

		btnAnterior = new JButton("Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (iterador > 0) {
						iterador--;
						panelRespuestaSelecMultiple.removeAll();
						AvanzaRetrocedePregunta(iterador);
					} else {
						JOptionPane.showMessageDialog(null, "No puedes retroceder mas");
					}
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		});
		panelBotones.add(btnAnterior, BorderLayout.WEST);
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.control);
		panelBotones.add(panel);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.control);
		panelBotones.add(panel_1);
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.control);
		panelBotones.add(panel_2);
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.control);
		panelBotones.add(panel_3);
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.control);
		panelBotones.add(panel_4);
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.control);
		panelBotones.add(panel_5);
		panelBotones.add(btnSiguiente, BorderLayout.EAST);
		/***************************************************************************/
		return panelIniciador;
	}

	public PreguntasGUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelMaster = new JPanel();

		panelMaster.setBackground(SystemColor.control);
		panelMaster.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelMaster.setLayout(new BoxLayout(panelMaster, BoxLayout.Y_AXIS));
		setContentPane(panelMaster);
		panelMaster.add(iniciador());
		setResizable(false);
		pack();
		setLocationRelativeTo(null);

	}

	void agregaBotonesDinamicoSelecMult(Selec_Mul_Pregunta pregunta) {
		botonesSelecMult = new ArrayList<JRadioButton>();
		for (int i = 0; i < pregunta.getTamanio_arreglo(); i++) {
			JRadioButton radioBotonesOpciones = new JRadioButton(pregunta.getopciones()[i]);
			radioBotonesOpciones.setBackground(SystemColor.control);
			radioBotonesOpciones.setFont(new Font("Tahoma", Font.PLAIN, 20));
			botonesSelecMult.add(radioBotonesOpciones);
			grupoDeBotones.add(radioBotonesOpciones);
			panelRespuestaSelecMultiple.add(radioBotonesOpciones);

			radioBotonesOpciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("presione el boton " + radioBotonesOpciones.getText());
					respuestaSeleccionada[iterador] = radioBotonesOpciones.getText();
					for (String algo : respuestaSeleccionada) {
						System.out.println(algo);
					}
				}
			});
		}
		panelRespuestaSelecMultiple.updateUI();
	}

	void agregaBotonesDinamicoTrueFalse(TFpreguntas pregunta) {
		botonesTF = new ArrayList<JRadioButton>();
		JRadioButton radioBotonT = new JRadioButton("Verdadero");
		radioBotonT.setBackground(SystemColor.control);
		radioBotonT.setHorizontalAlignment(SwingConstants.CENTER);
		radioBotonT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botonesTF.add(radioBotonT);
		grupoDeBotones.add(radioBotonT);
		panelRespuestaTrueFalse.add(radioBotonT);

		radioBotonT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("presione el boton " + radioBotonT.getText());
				respuestaSeleccionada[iterador] = radioBotonT.getText();
				for (String algo : respuestaSeleccionada) {
					System.out.println(algo);
				}
			}
		});

		JRadioButton radioBotonF = new JRadioButton("Falso");
		radioBotonF.setBackground(SystemColor.control);
		radioBotonF.setHorizontalAlignment(SwingConstants.CENTER);
		radioBotonF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botonesTF.add(radioBotonF);
		grupoDeBotones.add(radioBotonF);
		panelRespuestaTrueFalse.add(radioBotonF);

		radioBotonF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("presione el boton " + radioBotonF.getText());
				respuestaSeleccionada[iterador] = radioBotonF.getText();
				for (String algo : respuestaSeleccionada) {
					System.out.println(algo);
				}
			}
		});
		panelRespuestaTrueFalse.updateUI();
	}

	void AvanzaRetrocedePregunta(int i) {
		CardLayout c = (CardLayout) (panelContenedorRespuestasAbsoluto.getLayout());
		if (i < examen.selecmulpreg.size()) {
			c.show(panelContenedorRespuestasAbsoluto, "seleccionMultiple");
			panelRespuestaSelecMultiple.removeAll();
			textEnunciado.setText(examen.selecmulpreg.get(i).getEnunciado());
			agregaBotonesDinamicoSelecMult(examen.selecmulpreg.get(i));
			RbutonSeleccionado(botonesSelecMult);

		} else if (i >= (examen.selecmulpreg.size()) && i < (examen.selecmulpreg.size() + examen.tfpreg.size())) {
			c.show(panelContenedorRespuestasAbsoluto, "verdaderoFalso");
			panelRespuestaSelecMultiple.removeAll();
			panelRespuestaTrueFalse.removeAll();
			textEnunciado.setText(examen.tfpreg.get(i - examen.selecmulpreg.size()).getEnunciado());
			agregaBotonesDinamicoTrueFalse(examen.tfpreg.get(i - examen.selecmulpreg.size()));
			RbutonSeleccionado(botonesTF);
		} else if (i >= (examen.selecmulpreg.size() + examen.tfpreg.size())
				&& i < (examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size())) {
			String enunciadoRC = examen.rcpreg.get(i - examen.selecmulpreg.size() - examen.tfpreg.size())
					.getEnunciado();
			c.show(panelContenedorRespuestasAbsoluto, "respuestaCorta");
			panelRespuestaTrueFalse.removeAll();

			textEnunciado.setText(enunciadoRC);
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
}
