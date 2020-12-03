import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import javax.swing.JEditorPane;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.awt.Font;
import ExamenJava.*;
import ExamenJava.Exam.almacen;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.CardLayout;
import javax.swing.JMenu;

public class PreguntasGUI extends JFrame {
	JPanel panelMaster;
	JPanel panelEnunciado;
	JPanel panelOpciones;
	JTextArea textEnunciado;
	JScrollPane scroll;
	JPanel panelRespuestaSelecMultiple;
	JPanel panelContenedorRespuestasAbsoluto;
	JPanel panelBotones;
	JButton btnSiguiente;
	ButtonGroup grupoDeBotones;
	JComboBox cbxMenuDesplegable;
	GroupLayout gl_panelOpciones;
	Exam examen;
	private String[] respuestaSeleccionada;
	private ArrayList<JRadioButton> botonesSelecMult;
	private ArrayList<JRadioButton> botonesTF;

	int iterador = 0;
	private JButton btnAnterior;
	private JLabel lblDatosTituloExamen;
	private JPanel panelRespuestaTrueFalse;
	private JPanel panelRespuestaCorta;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreguntasGUI frame = new PreguntasGUI();
					frame.setVisible(true);
					// frame.setResizable(false);
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
		panelIniciador.setLayout(new BoxLayout(panelIniciador, BoxLayout.Y_AXIS));

		JPanel panelContenedorPregunta = new JPanel();
		panelContenedorPregunta.setLayout(new BoxLayout(panelContenedorPregunta, BoxLayout.Y_AXIS));
		panelIniciador.add(panelContenedorPregunta);

		JPanel panelContenedorBotones = new JPanel();
		panelContenedorBotones.setLayout(new BoxLayout(panelContenedorBotones, BoxLayout.Y_AXIS));
		panelIniciador.add(panelContenedorBotones);

		panelOpciones = new JPanel();
		panelOpciones.setBackground(Color.WHITE);
		panelOpciones.setPreferredSize(new Dimension(800, 40));
		panelContenedorPregunta.add(panelOpciones);

		lblDatosTituloExamen = new JLabel("DATOS USUARIO");
		lblDatosTituloExamen.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosTituloExamen.setFont(new Font("Tahoma", Font.BOLD, 14));

		cbxMenuDesplegable = new JComboBox();
		cbxMenuDesplegable.setModel(new DefaultComboBoxModel(new String[] { "Opciones", "Salir" }));
		gl_panelOpciones = new GroupLayout(panelOpciones);
		gl_panelOpciones.setHorizontalGroup(gl_panelOpciones.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOpciones.createSequentialGroup().addGap(288)
						.addComponent(lblDatosTituloExamen, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE).addGap(170)
						.addComponent(cbxMenuDesplegable, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)));
		gl_panelOpciones.setVerticalGroup(gl_panelOpciones.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOpciones.createSequentialGroup().addGap(5)
						.addGroup(gl_panelOpciones.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDatosTituloExamen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(cbxMenuDesplegable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(0)));
		panelOpciones.setLayout(gl_panelOpciones);

		/************************* PANEL DEL ENUNCIADO ******************************/
		panelEnunciado = new JPanel();
		panelEnunciado.setBackground(Color.WHITE);
		panelEnunciado.setPreferredSize(new Dimension(800, 160));
		panelContenedorPregunta.add(panelEnunciado, BorderLayout.NORTH);

		textEnunciado = new JTextArea();
		textEnunciado.setFont(new Font("Arial", Font.PLAIN, 25));
		textEnunciado.setBackground(Color.WHITE);
		textEnunciado.setDisabledTextColor(Color.BLACK);
		
		textEnunciado.setLineWrap(true);
		textEnunciado.setWrapStyleWord(true);
		textEnunciado.setEditable(false);
		//String enunciado = ;// .replaceAll("(.{80})", "$1\n");
		scroll = new JScrollPane(textEnunciado,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textEnunciado.setText(examen.selecmulpreg.get(0).getEnunciado());
		scroll.setViewportView(textEnunciado);
		textEnunciado.setPreferredSize(new Dimension(750, 150));
		panelEnunciado.add(scroll,BorderLayout.CENTER);
		/***************************************************************************/

		/************************* PANEL DE LA RESPUESTA ******************************/
		panelContenedorRespuestasAbsoluto = new JPanel();
		panelContenedorRespuestasAbsoluto.setBackground(Color.WHITE);
		panelContenedorRespuestasAbsoluto.setBorder(new EmptyBorder(15, 30, 47, 30));
		panelContenedorRespuestasAbsoluto.setLayout(new CardLayout(0, 0));
		
		panelRespuestaSelecMultiple = new JPanel(new GridLayout(0, 3, 1, 30));
		panelRespuestaSelecMultiple.setBackground(Color.WHITE);
		panelRespuestaSelecMultiple.setPreferredSize(new Dimension(200, 200));
		agregaBotonesDinamicoSelecMult(examen.selecmulpreg.get(iterador));
		
		panelContenedorRespuestasAbsoluto.add(panelRespuestaSelecMultiple, "seleccionMultiple");

		panelRespuestaTrueFalse = new JPanel(new GridLayout(0, 2, 10, 0));
		panelRespuestaTrueFalse.setBorder(new EmptyBorder(80, 80, 80, 80));
		panelRespuestaTrueFalse.setBackground(Color.WHITE);
		panelRespuestaTrueFalse.setPreferredSize(new Dimension(200, 200));
		panelContenedorRespuestasAbsoluto.add(panelRespuestaTrueFalse, "verdaderoFalso");

		rdbtnNewRadioButton = new JRadioButton("New radio button");
		panelRespuestaTrueFalse.add(rdbtnNewRadioButton);

		rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		panelRespuestaTrueFalse.add(rdbtnNewRadioButton_1);

		panelRespuestaCorta = new JPanel();
		panelRespuestaCorta.setBackground(Color.WHITE);
		panelContenedorRespuestasAbsoluto.add(panelRespuestaCorta, "respuestaCorta");

		panelContenedorPregunta.add(panelContenedorRespuestasAbsoluto, BorderLayout.CENTER);

		/***************************************************************************/

		/************************* PANEL DE LOS BOTONES ****************************/
		panelBotones = new JPanel(new GridLayout(0, 8, 0, 0));
		panelBotones.setPreferredSize(new Dimension(22, 30));
		panelContenedorBotones.add(panelBotones, BorderLayout.SOUTH);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					iterador++;
					AvanzaRetrocedePregunta(iterador);
					/*
					 * if (iterador < examen.selecmulpreg.size() ) {
					 * panelRespuestaSelecMultiple.removeAll(); AvanzaRetrocedePregunta(iterador); }
					 * else if(iterador >= (examen.selecmulpreg.size() ) && iterador <
					 * (examen.selecmulpreg.size() + examen.tfpreg.size())){ CardLayout c =
					 * (CardLayout)(paneContenedorRespuestas.getLayout());
					 * c.show(paneContenedorRespuestas, "verdaderoFalso");
					 * 
					 * }else if(iterador >= (examen.selecmulpreg.size() + examen.tfpreg.size()) &&
					 * iterador < (examen.selecmulpreg.size() +
					 * examen.tfpreg.size()+examen.rcpreg.size())) {
					 * 
					 * }
					 */
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
		panel.setBackground(Color.WHITE);
		panelBotones.add(panel);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panelBotones.add(panel_1);
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panelBotones.add(panel_2);
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panelBotones.add(panel_3);
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panelBotones.add(panel_4);
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panelBotones.add(panel_5);
		panelBotones.add(btnSiguiente, BorderLayout.EAST);
		/***************************************************************************/
		return panelIniciador;
	}

	public PreguntasGUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelMaster = new JPanel();
		panelMaster.setBackground(Color.WHITE);
		panelMaster.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelMaster.setLayout(new BoxLayout(panelMaster, BoxLayout.Y_AXIS));
		panelMaster.add(iniciador());
		setContentPane(panelMaster);
		pack();
		setLocationRelativeTo(null);

	}

	void agregaBotonesDinamicoSelecMult(Selec_Mul_Pregunta pregunta) {
		botonesSelecMult = new ArrayList<JRadioButton>();
		for (int i = 0; i < pregunta.getTamanio_arreglo(); i++) {
			JRadioButton radioBotonesOpciones = new JRadioButton(pregunta.getopciones()[i]);
			radioBotonesOpciones.setBackground(Color.WHITE);
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

	/*
	 * void agregaBotonesDinamicoTrueFalse(TFpreguntas pregunta) { botonesTF = new
	 * ArrayList<JRadioButton>(); JRadioButton radioBotonT = new
	 * JRadioButton("Verdadero"); radioBotonT.setBackground(Color.WHITE);
	 * radioBotonT.setFont(new Font("Tahoma", Font.PLAIN, 20));
	 * botonesTF.add(radioBotonT); grupoDeBotones.add(radioBotonT);
	 * panelRespuestaTrueFalse.add(radioBotonT);
	 * 
	 * radioBotonT.addActionListener(new ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent arg0) {
	 * System.out.println("presione el boton " + radioBotonT.getText());
	 * respuestaSeleccionada[iterador] = radioBotonT.getText(); for (String algo :
	 * respuestaSeleccionada) { System.out.println(algo); } } });
	 * 
	 * JRadioButton radioBotonF = new JRadioButton("Falso"); radioBotonF.setFont(new
	 * Font("Tahoma", Font.PLAIN, 20)); botonesTF.add(radioBotonF);
	 * grupoDeBotones.add(radioBotonF); panelRespuestaTrueFalse.add(radioBotonF);
	 * 
	 * radioBotonF.addActionListener(new ActionListener() {
	 * 
	 * @Override public void actionPerformed(ActionEvent arg0) {
	 * System.out.println("presione el boton " + radioBotonF.getText());
	 * respuestaSeleccionada[iterador] = radioBotonF.getText(); for (String algo :
	 * respuestaSeleccionada) { System.out.println(algo); } } });
	 * panelRespuestaTrueFalse.updateUI(); }
	 */

	void AvanzaRetrocedePregunta(int i) {
		CardLayout c = (CardLayout) (panelContenedorRespuestasAbsoluto.getLayout());
		if (iterador < examen.selecmulpreg.size()) {
			String enunciadoSM = examen.selecmulpreg.get(i).getEnunciado();
			
			c.show(panelContenedorRespuestasAbsoluto, "seleccionMultiple");
			
			panelRespuestaSelecMultiple.removeAll();
			textEnunciado.setText(enunciadoSM);
			scroll.setViewportView(textEnunciado);
			agregaBotonesDinamicoSelecMult(examen.selecmulpreg.get(i));
			RbutonSeleccionado(botonesSelecMult);
		} else if (iterador >= (examen.selecmulpreg.size())&& iterador < (examen.selecmulpreg.size() + examen.tfpreg.size())) {
			
			String enunciadoTF = examen.tfpreg.get(i - examen.selecmulpreg.size()).getEnunciado();
			c.show(panelContenedorRespuestasAbsoluto, "verdaderoFalso");

			panelRespuestaSelecMultiple.removeAll();
			panelRespuestaCorta.removeAll();
			textEnunciado.setText(enunciadoTF);
			//scroll.setViewportView(textEnunciado);
		} else if (iterador >= (examen.selecmulpreg.size() + examen.tfpreg.size())&& iterador < (examen.selecmulpreg.size() + examen.tfpreg.size() + examen.rcpreg.size())) {
			
			String enunciadoRC = examen.rcpreg.get(i - examen.selecmulpreg.size() - examen.tfpreg.size()).getEnunciado();
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
