import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;

import Controlador.ConvierteExamenJson;
import Controlador.Exam;
import Controlador.FechaDeCreacion;
import Controlador.FileSystemModel;
import Controlador.JSONaExamen;
import Controlador.ModeloNotasAlumno;
import Controlador.Resp_Cortas_Pregunta;
import Controlador.Selec_Mul_Pregunta;
import Controlador.TFpreguntas;

@SuppressWarnings("serial")
public class ProfesorGUI extends JFrame {

	private JPanel PanelMayorProfesor;
	static String nombreDelArchivo;
	String rut;
	JTextField textRut;
	int fila;
	String ubicacionArchivos = "";
	String nombreExamen = "";
	JTable tablaDeExamenesNotas;
	
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(new FlatCarbonIJTheme());
		} catch (Exception ex) {
			System.err.println("Tema no aplicado");
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					ProfesorGUI frame = new ProfesorGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ProfesorGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PanelMayorProfesor = new JPanel();
		PanelMayorProfesor.setLayout(new BorderLayout(10, 10));
		setContentPane(PanelMayorProfesor);
		PanelMayorProfesor.add(inicioSesionProfesor());
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public JPanel inicioSesionProfesor() {
		JPanel panelContenedorInicioSesion = new JPanel();
		panelContenedorInicioSesion.setBorder(new EmptyBorder(0, 0, 20, 0));
		panelContenedorInicioSesion.setLayout(new BoxLayout(panelContenedorInicioSesion, BoxLayout.Y_AXIS));

		JPanel panelMenuBar = new JPanel(new BorderLayout(0,10));
		panelContenedorInicioSesion.add(panelMenuBar);
		
		panelContenedorInicioSesion.add(Box.createRigidArea(new Dimension(0, 20)));
	
		JPanel panelImagen = new JPanel(new BorderLayout());
		panelImagen.setBorder(new EmptyBorder(0, 70, 0, 70));
		panelContenedorInicioSesion.add(panelImagen);

		panelContenedorInicioSesion.add(Box.createRigidArea(new Dimension(0, 30)));

		JPanel panelLblRut = new JPanel(new BorderLayout());
		panelLblRut.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelContenedorInicioSesion.add(panelLblRut);

		JPanel panelTextRut = new JPanel();
		panelTextRut.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelTextRut.setLayout(new BoxLayout(panelTextRut, BoxLayout.X_AXIS));
		panelContenedorInicioSesion.add(panelTextRut);

		panelContenedorInicioSesion.add(Box.createRigidArea(new Dimension(0, 30)));

		JPanel panelLblContra = new JPanel(new BorderLayout());
		panelLblContra.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelContenedorInicioSesion.add(panelLblContra);

		JPanel panelTextContra = new JPanel();
		panelTextContra.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelTextContra.setLayout(new BoxLayout(panelTextContra, BoxLayout.X_AXIS));
		panelContenedorInicioSesion.add(panelTextContra);

		panelContenedorInicioSesion.add(Box.createRigidArea(new Dimension(0, 20)));

		JPanel panelBotonLogin = new JPanel(new GridLayout(0, 1, 0, 0));
		panelBotonLogin.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelContenedorInicioSesion.add(panelBotonLogin);

		panelContenedorInicioSesion.add(Box.createRigidArea(new Dimension(0, 0)));

		JPanel panelLblReset = new JPanel();
		panelLblReset.setBorder(new EmptyBorder(0, 65, 0, 65));
		panelLblReset.setLayout(new BorderLayout());
		panelContenedorInicioSesion.add(panelLblReset);

		
		JMenuBar barraOpciones = new JMenuBar();
		panelMenuBar.add(barraOpciones, BorderLayout.NORTH);
		JMenu menuOpciones = new JMenu("Opciones");
		barraOpciones.add(menuOpciones);
		JMenuItem VolverOpcion = new JMenuItem("Volver");
		VolverOpcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new InicioSesion();
			}
		});
		JMenuItem SalirOpcion = new JMenuItem("Salir");
		SalirOpcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menuOpciones.add(VolverOpcion);
		menuOpciones.add(SalirOpcion);
	
		String titulo = "Inicio de Sesión<br>Profesor";
		JLabel lblEncabezado = new JLabel("<html><div style='text-align: center;'>" + titulo + "</div></html>");
		lblEncabezado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEncabezado.setFont(new Font("Thaoma", Font.PLAIN, 30));
		panelMenuBar.add(lblEncabezado,BorderLayout.SOUTH);

		JLabel lblImagenProfeInicSes = new JLabel();
		lblImagenProfeInicSes.setHorizontalAlignment(SwingConstants.CENTER);
		String ubicacionImagen = "";
		try {

			ubicacionImagen = new File(".").getCanonicalPath() + "\\res\\profesor.png";
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		ImageIcon imagenAlumno = new ImageIcon(ubicacionImagen);
		lblImagenProfeInicSes.setIcon(new ImageIcon(imagenAlumno.getImage().getScaledInstance(240, 220, Image.SCALE_DEFAULT)));
		panelImagen.add(lblImagenProfeInicSes, BorderLayout.CENTER);
		
		
		JLabel lblRut = new JLabel("Rut");
		lblRut.setFont(new Font("Thaoma", Font.PLAIN, 18));
		panelLblRut.add(lblRut, BorderLayout.WEST);

		textRut = new JTextField();
		textRut.setBackground(Color.WHITE);
		textRut.setForeground(Color.BLACK);
		textRut.addKeyListener(new noAgregaLetrasRUTField());
		textRut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelTextRut.add(textRut);
		

		JLabel lblContra = new JLabel("Contraseña");
		lblContra.setHorizontalAlignment(SwingConstants.LEFT);
		lblContra.setFont(new Font("Thaoma", Font.PLAIN, 18));
		panelLblContra.add(lblContra, BorderLayout.WEST);

		JPasswordField textContrasenia = new JPasswordField();
		textContrasenia.setBackground(Color.WHITE);
		textContrasenia.setForeground(Color.BLACK);
		textContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelTextContra.add(textContrasenia);

		JButton botonLogin = new JButton("Ingresar");
		botonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String claveAux = "";
				for (char c : textContrasenia.getPassword()) {
					claveAux += c;
				}
				if (claveAux.equalsIgnoreCase(retornaClaveProfesor())) {
					PanelMayorProfesor.removeAll();
					PanelMayorProfesor.add(MenuProfesor());
					PanelMayorProfesor.updateUI();
					pack();
					setLocationRelativeTo(null);
				} else {
					JOptionPane.showMessageDialog(null, "Rut o Contraseña Incorrecto");
				}
			}
		});
		botonLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelBotonLogin.add(botonLogin);
		

		JButton btnResetContra = new JButton("Recuperar Contraseña");
		btnResetContra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new Email("profesores");
			}
		});
		
		btnResetContra.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnResetContra.setBackground(new Color(23,32,48));
		btnResetContra.setBorderPainted(false);
		panelLblReset.add(btnResetContra,BorderLayout.CENTER);
		
		return panelContenedorInicioSesion;
	}

	public JPanel MenuProfesor() {

		JPanel panelContenedorMenu = new JPanel(new BorderLayout());
		JPanel panelContenedorIzquierdo = new JPanel(new BorderLayout());
		panelContenedorIzquierdo.setBorder(new EmptyBorder(0, 0, 0, 0));
		JPanel panelContenedorDerecho = new JPanel(new BorderLayout());

		panelContenedorMenu.add(panelContenedorIzquierdo, BorderLayout.WEST);
		panelContenedorMenu.add(panelContenedorDerecho, BorderLayout.CENTER);

		JMenuBar barraOpciones = new JMenuBar();
		panelContenedorMenu.add(barraOpciones, BorderLayout.NORTH);
		JMenu menuOpciones = new JMenu("Opciones");
		menuOpciones.setFont(new Font("Tahoma", Font.PLAIN, 15));
		barraOpciones.add(menuOpciones);
		JMenu menuCreacion = new JMenu("Creación");
		menuCreacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		barraOpciones.add(menuCreacion);		
		JMenuItem VolverOpcion = new JMenuItem("Volver");
		VolverOpcion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ProfesorGUI();
			}
		});
		JMenuItem SalirOpcion = new JMenuItem("Salir");
		SalirOpcion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		VolverOpcion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SalirOpcion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		menuOpciones.add(VolverOpcion);
		menuOpciones.add(SalirOpcion);
		JMenuItem CrearExamOpcion = new JMenuItem("Crear Exámen");
		CrearExamOpcion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CrearExamOpcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (ventanaColocaNombreArchivo()) {
					PanelMayorProfesor.removeAll();
					PanelMayorProfesor.add(new CrearExamen());
					PanelMayorProfesor.updateUI();
					pack();
					setLocationRelativeTo(null);
				}
			}
		});
		menuCreacion.add(CrearExamOpcion);
		
		JLabel lblImagenProfesor = new JLabel();
		String ubicacionImagen = "";
		try {
			
			ubicacionImagen = new File(".").getCanonicalPath() + "\\res\\profesor.png";
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		ImageIcon imagenAdmin = new ImageIcon(ubicacionImagen);
		lblImagenProfesor.setIcon(new ImageIcon(imagenAdmin.getImage().getScaledInstance(210, 200, Image.SCALE_DEFAULT)));
		panelContenedorIzquierdo.add(lblImagenProfesor, BorderLayout.CENTER);

		JTabbedPane panelControladorProfesor = new JTabbedPane();
		panelContenedorDerecho.add(panelControladorProfesor, BorderLayout.CENTER);

		JPanel panelCreacionExamen = new JPanel(new BorderLayout());
		panelCreacionExamen.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelControladorProfesor.addTab("Examenes", panelCreacionExamen);

		JPanel panelNotasExamen = new JPanel(new BorderLayout());
		panelNotasExamen.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelControladorProfesor.addTab("Notas", panelNotasExamen);

		JPanel panelArbolCarpetasCreaExamen = new JPanel(new BorderLayout(0, 10));
		panelArbolCarpetasCreaExamen.setBorder(new EmptyBorder(10, 10, 10, 0));

		
		
		try {
			ubicacionArchivos = new File(".").getCanonicalPath() + "\\Profesor\\Examenes\\Examenes " + getProfesorName();
			if (!new File(ubicacionArchivos).exists()) {
				new File(ubicacionArchivos).mkdirs();
			}
			
			if(!new File(new File(".").getCanonicalPath() + "\\Profesor\\Notas\\Notas " + getProfesorName()).exists()) {
				new File(new File(".").getCanonicalPath() + "\\Profesor\\Notas\\Notas " + getProfesorName()).mkdirs();
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		JTree arbolCarpetas = new JTree();
		Controlador.FileSystemModel modelo = new FileSystemModel(new File(ubicacionArchivos));
		arbolCarpetas.setModel(modelo);
		
		JScrollPane scrollArbol = new JScrollPane(arbolCarpetas);
		panelCreacionExamen.add(scrollArbol, BorderLayout.CENTER);

		tablaDeExamenesNotas = new JTable();
		tablaDeExamenesNotas.setModel(encabezado());
		llenaTablaDeExamenes(tablaDeExamenesNotas, ubicacionArchivos);
		tablaDeExamenesNotas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					System.out.println("entre");
					fila = tablaDeExamenesNotas.getSelectedRow();
					PanelMayorProfesor.removeAll();
					PanelMayorProfesor.add(tablaResultadosDeExamen());
					PanelMayorProfesor.repaint();
					pack();
					setLocationRelativeTo(null);

				}
			}
		});
		tablaDeExamenesNotas.getColumnModel().getColumn(0).setPreferredWidth(300);
		tablaDeExamenesNotas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaDeExamenesNotas.getTableHeader().setReorderingAllowed(false);
		tablaDeExamenesNotas.getTableHeader().setResizingAllowed(false);
		JScrollPane scrollTablaNota = new JScrollPane(tablaDeExamenesNotas);
		panelNotasExamen.add(scrollTablaNota, BorderLayout.CENTER);

		return panelContenedorMenu;
	}
	
	
	DefaultTableModel encabezado() {
		String[] rubrosTablaProf = { "Nombre", "P1","P2","P3","P4","P5","Nota" };
		DefaultTableModel tablaPorDefecto = new DefaultTableModel(rubrosTablaProf, 0);
		return tablaPorDefecto;
	}
	
	public JPanel tablaResultadosDeExamen(){
		JTable tablaNotas = new JTable();
		JPanel panelMaestroTabla = new JPanel(new BorderLayout(0,10));
		JPanel panelMenu = new JPanel(new BorderLayout());
		panelMaestroTabla.add(panelMenu,BorderLayout.NORTH);
		JPanel panelTabla = new JPanel(new BorderLayout(0,10));
		panelTabla.setBorder(new EmptyBorder(0, 20, 20, 20));
		panelMaestroTabla.add(panelTabla,BorderLayout.CENTER);
		
		JMenuBar menuOpciones = new JMenuBar();
		panelMenu.add(menuOpciones,BorderLayout.CENTER);
		JMenu opciones = new JMenu("Opciones");
		opciones.setFont(new Font("Tahoma", Font.PLAIN, 15));
		menuOpciones.add(opciones);
		JMenu graficar = new JMenu("Graficar");
		graficar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		menuOpciones.add(graficar);
		JMenuItem opcionVolver = new JMenuItem("Volver");
		opcionVolver.setFont(new Font("Tahoma", Font.PLAIN, 15));
		opcionVolver.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("entre acaa");
				PanelMayorProfesor.removeAll();
				PanelMayorProfesor.add(MenuProfesor());
				PanelMayorProfesor.updateUI();
				pack();
				setLocationRelativeTo(null);
			}
		});
		opciones.add(opcionVolver);
		JMenuItem opcionGraficar = new JMenuItem("<html>Graficar resultados<br>del examen</html>");
		opcionGraficar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		opcionGraficar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tablaNotas!= null) {
					System.out.println("tabla col: "+ tablaNotas.getColumnCount()+" filas: "+tablaNotas.getRowCount());
					
					panelMaestroTabla.add(graficarResultados(tablaNotas),BorderLayout.EAST);
					panelMaestroTabla.updateUI();
					pack();
					setLocationRelativeTo(null);
				}
			}
		});
		graficar.add(opcionGraficar);
		
		
		
		tablaNotas.setPreferredSize(new Dimension(20,100));
		tablaNotas.setModel(encabezado());
		tablaNotas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					System.out.println("Datos ");
					muestraDatosAlumno();
				}
			}
		});
		
		JSONaExamen conversor = new JSONaExamen();
		File archivos = new File(ubicacionArchivos);
		String[] archivoElegido = archivos.list();
		
		Exam examen = new Exam();
		try {
			examen = conversor.generaExamen(new File(ubicacionArchivos+"\\"+archivoElegido[fila]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		for(int i = 0; i< archivoElegido[fila].length();i++ ) {
			if(archivoElegido[fila].charAt(i) != '.') {
				nombreExamen += archivoElegido[fila].charAt(i);
			}else {
				break;
			}
		}
		String ubicacion = "";
		
		try {
			ubicacion = new File(".").getCanonicalPath() + "\\Profesor\\Notas\\Notas " + getProfesorName() +"\\Notas Examen "+ nombreExamen;
		} catch (IOException e) {
			e.printStackTrace();
		}
		llenaTablaDeAlumnos(tablaNotas,ubicacion,examen);
		tablaNotas.getTableHeader().setReorderingAllowed(false);
		tablaNotas.getTableHeader().setResizingAllowed(false);
		JScrollPane scrolltabla = new JScrollPane(tablaNotas);
		panelTabla.add(scrolltabla, BorderLayout.CENTER);
		
		JLabel labelInformacion = new JLabel("Notas del Examen: "+nombreExamen);
		System.out.println("Name exam: "+nombreExamen);
		nombreExamen = "";
		
		labelInformacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelInformacion.setHorizontalAlignment(SwingConstants.LEFT);
		panelTabla.add(labelInformacion,BorderLayout.NORTH);
	
		return panelMaestroTabla;
	}
	
	public JPanel graficarResultados(JTable tabla){
		JPanel panelGrafica = new JPanel(new BorderLayout());
		int filas = tabla.getRowCount();
		int columnaDeLaNota = tabla.getColumnCount();
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		
		System.out.println("es: "+tabla.getValueAt(0, columnaDeLaNota-1));
		for(int i = 0; i < filas; i++) {
			String[] arr = String.valueOf(tabla.getValueAt(i, columnaDeLaNota-1)).split("/");
			
			int porcentaje = (int) ((double) Integer.valueOf(arr[0]) / (Integer.valueOf(arr[1])) * 100);
			System.out.println("porcentaje "+ Integer.valueOf(arr[0])+" otra: "+Integer.valueOf(arr[1])+" porc: "+porcentaje);
			data.addValue(porcentaje, "Estudiante", String.valueOf(tabla.getValueAt(i, 0)));
		}
		
		JFreeChart grafica = ChartFactory.createBarChart3D("Grafico de notas", "Alumnos", "% de nota", data, PlotOrientation.VERTICAL, false, true , false);
		ChartPanel contenedor = new ChartPanel(grafica);
		
		panelGrafica.add(contenedor,BorderLayout.CENTER);
		
		
		return panelGrafica;
	}
	
	private void llenaTablaDeAlumnos(JTable tabla, String ubicacion,Exam examen) {
		JSONaExamen revision = new JSONaExamen();
		ModeloNotasAlumno alumno = new ModeloNotasAlumno();
		
		DefaultTableModel modelo = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelo.addColumn("Nombre");
		for (int i = 0; i < examen.getTotalPreguntas(); i++) {
			modelo.addColumn("P"+(i+1));
			
		}
		modelo.addColumn("Puntaje");

		Controlador.FileSystemModel ubicacionArchivos = new FileSystemModel(new File(ubicacion));
		System.out.println(ubicacion);
		File archivos = new File(ubicacion);
		String[] totalArchivos = archivos.list();
		if(totalArchivos != null) {
			System.out.println(totalArchivos.length);
			Object[] fila = new Object[examen.getTotalPreguntas()+2];
			for (int i = 0; i < totalArchivos.length; i++) {
				fila[0] = ubicacionArchivos.getNombreArchivos(archivos, i).substring(0,ubicacionArchivos.getNombreArchivos(archivos, i).lastIndexOf("."));
					try {
						alumno = revision.generaResultadoExam(new File(ubicacion+"\\"+ubicacionArchivos.getNombreArchivos(archivos, i)));
						int[] arr = alumno.getPuntajeAlumno();
						for (int j = 1; j <= examen.getTotalPreguntas()+1; j++) {
							if(j == examen.getTotalPreguntas()+1) {
								fila[j] = Integer.toString(alumno.getPuntajeTotalAlumno())+"/"+Integer.toString(alumno.getPuntajeTotalExam());
								break;
							}
							fila[j] = Integer.toString(arr[j-1]);
							
						}						

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				modelo.addRow(fila);
			}
			tabla.setModel(modelo);
			
			tabla.getColumnModel().getColumn(0).setPreferredWidth(300);
			for (int i = 1; i <= examen.getTotalPreguntas(); i++) {
				tabla.getColumnModel().getColumn(i).setPreferredWidth(100);
			}
			tabla.getColumnModel().getColumn(examen.getTotalPreguntas()+1).setPreferredWidth(100);
			
		}
	}
	
	public void muestraDatosAlumno() {
		
	}
	
	private void llenaTablaDeExamenes(JTable tabla, String ubicacion) {
		DefaultTableModel modelo = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		FechaDeCreacion fecha = new FechaDeCreacion();
		modelo.addColumn("Nombre");
		modelo.addColumn("Fecha");
		Controlador.FileSystemModel ubicacionArchivos = new FileSystemModel(new File(ubicacion));
		File archivos = new File(ubicacion);
		String[] totalArchivos = archivos.list();
		Object[] fila = new Object[2];
		for (int i = 0; i < totalArchivos.length; i++) {
			fila[0] = ubicacionArchivos.getNombreArchivos(archivos, i).substring(0,ubicacionArchivos.getNombreArchivos(archivos, i).lastIndexOf("."));
			fila[1] = fecha.retornaFecha(ubicacion+"\\"+ubicacionArchivos.getNombreArchivos(archivos, i));
			modelo.addRow(fila);
		}
		tabla.setModel(modelo);

	}
	
	
	JPanel AgregaExamen() {

		JPanel panelContenedorAgregaExamen = new JPanel();

		return panelContenedorAgregaExamen;
	}

	Boolean ventanaColocaNombreArchivo() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(10, 10, 0, 10));

		JPanel panelLabel = new JPanel(new BorderLayout());
		panel.add(panelLabel);
		panel.add(Box.createRigidArea(new Dimension(0, 15)));
		JPanel paneltexto = new JPanel(new BorderLayout());
		panel.add(paneltexto);

		JLabel label = new JLabel("Cree Un Nombre Para Su Examen");
		label.setHorizontalAlignment(JLabel.LEFT);
		label.setFont(new Font("Thaoma", Font.PLAIN, 16));

		JTextField ingresaNombreArchivo = new JTextField();
		ingresaNombreArchivo.setFont(new Font("Thaoma", Font.PLAIN, 18));
		ingresaNombreArchivo.setForeground(Color.BLACK);
		ingresaNombreArchivo.setBackground(Color.WHITE);

		panelLabel.add(label, BorderLayout.WEST);
		paneltexto.add(ingresaNombreArchivo, BorderLayout.CENTER);

		String[] options = new String[] { "Cancelar", "Aceptar" };

		int option = JOptionPane.showOptionDialog(null, panel, "Nombre del Exámen", JOptionPane.NO_OPTION,JOptionPane.PLAIN_MESSAGE, null, options, options[1]);

		if (ingresaNombreArchivo.getText().isBlank() && option == 1) {
			JOptionPane.showMessageDialog(null, "Nombre no puede estar vacio");

		} else {
			if (option == 1) {
				nombreDelArchivo = ingresaNombreArchivo.getText();
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	public String retornaClaveProfesor() {
		String clave = "";//new String();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			Conexion objCon = new Conexion();
			Connection conn = objCon.getConnection();
			rut = textRut.getText();
			ps = conn.prepareStatement("SELECT Contraseña, CAST(AES_DECRYPT(Contraseña,'profesores')AS CHAR(50)) FROM profesores WHERE Rut = ?");
			ps.setString(1, rut);
			rs = ps.executeQuery();

			while (rs.next()) {
				clave = rs.getString("CAST(AES_DECRYPT(Contraseña,'profesores')AS CHAR(50))");
			}
		} catch (Exception e2) {
			System.err.println(e2.toString());
		}
		return clave;
	}

	public String getProfesorName() {
		String name = new String();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Conexion objCon = new Conexion();
			Connection conn = objCon.getConnection();
			if(textRut != null) {
				rut = textRut.getText();
			}			
			ps = conn.prepareStatement("SELECT Nombre,Apellido FROM profesores WHERE Rut = ?");
			ps.setString(1, rut);
			rs = ps.executeQuery();

			while (rs.next()) {
				name = rs.getString("Nombre") + " " + rs.getString("Apellido");
			}

		} catch (Exception e2) {
			System.err.println(e2.toString());
		}
		return name;
	}

	public class noAgregaLetrasRUTField implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			char letraIngresada = arg0.getKeyChar();
			if (!((letraIngresada >= '0') && (letraIngresada <= '9')
					|| ((letraIngresada == '-') || (letraIngresada == 'k') || (letraIngresada == 'K')))
					|| (letraIngresada == KeyEvent.VK_BACK_SPACE) || (letraIngresada == KeyEvent.VK_DELETE)) {
				arg0.consume();
			}
		}

	}
	
	class CrearExamen extends JPanel {

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
		JButton botonFinalizar;
		JLabel labelContadorTotalPreguntas;
		JTextField textTiempo;
		JRadioButton activarTiempo;
		boolean activador = false;
		int letra = 65;
		int totalPreguntas = 0;
		JTextPane txtRespuestaCortaPgr;
		////////////////////////////////
		Controlador.Exam examenCreado;
		/////////////////////////////////
		String enunciadoDeLaPregunta;
		int pesoDeLaPregunta;
		Controlador.ConvierteExamenJson jsonArchivo;
		String respuestaCorrectaSelecMul;
		int indexCorrectoSelecMul;
		String[] alternativas;
		boolean respuestaVerdFalso;
		JPanel panelCreador;
		String respuestaRespCorta;
		/////////////////////////////////

		public CrearExamen() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			panelMayorCrearExamen = new JPanel();
			//panelMayorCrearExamen.setBorder(new EmptyBorder(5, 5, 5, 5));
			panelMayorCrearExamen.setLayout(new BorderLayout(10, 10));
			setContentPane(panelMayorCrearExamen);
			panelMayorCrearExamen.add(panelIniciador());
			setResizable(false);
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		}

		private JPanel panelIniciador() {
			jsonArchivo = new ConvierteExamenJson();
			examenCreado = new Exam();

			JPanel panelContenedorCreaExamen = new JPanel(new BorderLayout());
			panelContenedorCreaExamen.setBorder(new EmptyBorder(0, 20, 20, 20));

			JPanel panelTitulo = new JPanel(new BorderLayout(30, 30));
			panelTitulo.setBorder(new EmptyBorder(20, 5, 20, 5));
			panelCreador = new JPanel();

			JPanel panelSuperiorCreador = new JPanel(new BorderLayout());
			JPanel panelTiempo = new JPanel(new BorderLayout(5, 0));
			panelTiempo.setBorder(new EmptyBorder(0, 0, 0, 0));
			panelSuperiorCreador.add(panelTiempo, BorderLayout.WEST);
			panelTitulo.add(panelSuperiorCreador, BorderLayout.SOUTH);

			JPanel panelContadorTotalPreguntas = new JPanel(new BorderLayout());
			panelSuperiorCreador.add(panelContadorTotalPreguntas, BorderLayout.EAST);
			labelContadorTotalPreguntas = new JLabel("Total preguntas: " + totalPreguntas);
			labelContadorTotalPreguntas.setFont(new Font("Tahoma", Font.PLAIN, 18));
			panelContadorTotalPreguntas.add(labelContadorTotalPreguntas, BorderLayout.CENTER);

			JPanel panelBotonesCrearPreguntas = new JPanel(new BorderLayout());// new GridLayout(0,3,12,12));
			panelBotonesCrearPreguntas.setBorder(new EmptyBorder(0, 50, 0, 50));
			panelBotonesCrearPreguntas.setBorder(BorderFactory.createLineBorder(new Color(33, 48, 71), 2, true));

			JPanel panelLabelBotones = new JPanel(new BorderLayout());
			panelLabelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));
			panelBotonesCrearPreguntas.add(panelLabelBotones, BorderLayout.NORTH);

			JPanel panelBotonesCreador = new JPanel(new GridLayout(0, 3, 12, 12));
			panelBotonesCreador.setBorder(new EmptyBorder(5, 50, 5, 50));
			panelBotonesCrearPreguntas.add(panelBotonesCreador, BorderLayout.SOUTH);

			panelContenedorCreaExamen.add(panelTitulo, BorderLayout.NORTH);
			panelContenedorCreaExamen.add(panelCreador, BorderLayout.CENTER);
			panelContenedorCreaExamen.add(panelBotonesCrearPreguntas, BorderLayout.SOUTH);

			activarTiempo = new JRadioButton();
			activarTiempo.addActionListener(new botonActivarTiempo());
			panelTiempo.add(activarTiempo, BorderLayout.WEST);

			JLabel labelTiempo = new JLabel("Tiempo:");
			labelTiempo.setFont(new Font("Tahoma", Font.PLAIN, 18));
			panelTiempo.add(labelTiempo, BorderLayout.CENTER);

			textTiempo = new JTextField();
			textTiempo.setEnabled(false);
			textTiempo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textTiempo.setBackground(new Color(23, 32, 48));
			textTiempo.addKeyListener(new noAgregaLetrasField());
			panelTiempo.add(textTiempo, BorderLayout.EAST);

			JLabel label = new JLabel("Creador de Examenes :");
			label.setFont(new Font("Tahoma", Font.PLAIN, 20));
			panelTitulo.add(label, BorderLayout.WEST);

			botonFinalizar = new JButton("Finalizar");
			botonFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			botonFinalizar.setEnabled(false);
			botonFinalizar.addActionListener(new botonFinalizarCreacionExamen());
			panelTitulo.add(botonFinalizar, BorderLayout.EAST);

			JLabel labelBotones = new JLabel("Agregar pregunta");
			labelBotones.setHorizontalAlignment(SwingConstants.CENTER);
			labelBotones.setFont(new Font("Tahoma", Font.PLAIN, 20));
			panelLabelBotones.add(labelBotones, BorderLayout.CENTER);

			JButton botonCrearSelecMul = new JButton("Seleccion Multiple");
			botonCrearSelecMul.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					panelBotonesCrearPreguntas.setBorder(null);
					if (CamposVacios()) {
						letra = 65;
						agregaOtraPregunta(panelCreador, panelCreadorSeleccionMultiple());
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
					panelBotonesCrearPreguntas.setBorder(null);
					if (CamposVacios()) {
						agregaOtraPregunta(panelCreador, panelCreadorVerdaderoFalso());

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
					panelBotonesCrearPreguntas.setBorder(null);
					if (CamposVacios()) {
						agregaOtraPregunta(panelCreador, panelCreadorRespuestaCorta());
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
			textPeso.addKeyListener(new noAgregaLetrasField());
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
			botonAgregaMasAlternativas.addActionListener(new btnAgregaMasAlternativas());

			JButton botonGuardar = new JButton("Guardar Pregunta");
			botonGuardar.addActionListener(new botonGuardarSelMulListener());

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
					if (!((letraIngresada >= '0') && (letraIngresada <= '9'))
							|| (letraIngresada == KeyEvent.VK_BACK_SPACE) || (letraIngresada == KeyEvent.VK_DELETE)) {
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
			rdbVerdadero.setActionCommand("Verdadero");
			rdbVerdadero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println(rdbVerdadero.getActionCommand());
				}
			});
			grupoCorrecto.add(rdbVerdadero);
			panelCentralVerdaFalso.add(rdbVerdadero);

			JRadioButton rdbFalso = new JRadioButton("Falso");
			rdbFalso.setFont(new Font("Tahoma", Font.PLAIN, 20));
			rdbFalso.setHorizontalAlignment(SwingConstants.CENTER);
			rdbFalso.setActionCommand("Falso");
			rdbFalso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println(rdbFalso.getActionCommand());
				}
			});
			grupoCorrecto.add(rdbFalso);
			panelCentralVerdaFalso.add(rdbFalso);

			JButton botonGuardar = new JButton("Guardar Pregunta");
			botonGuardar.addActionListener(new botonGuardarVerdFalsoListener());

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
			textPeso = new JTextField();
			textPeso.setBackground(Color.WHITE);
			textPeso.setForeground(Color.BLACK);
			textPeso.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textPeso.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char letraIngresada = e.getKeyChar();
					if (!((letraIngresada >= '0') && (letraIngresada <= '9'))
							|| (letraIngresada == KeyEvent.VK_BACK_SPACE) || (letraIngresada == KeyEvent.VK_DELETE)) {
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

			txtRespuestaCortaPgr = new JTextPane();
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
			botonGuardar.addActionListener(new botonGuardarRespCorta());

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

		void agregaOtraPregunta(JPanel padre, JPanel panelNuevo) {
			padre.removeAll();
			padre.add(panelNuevo);
			pack();
			setLocationRelativeTo(null);
			padre.updateUI();
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

		public void vaciaTextVerFalso() {
			textEnunciado.setText(null);
			textPeso.setText(null);
			grupoCorrecto.clearSelection();
		}

		public void vaciaRespuestaCorta() {
			textEnunciado.setText(null);
			txtRespuestaCortaPgr.setText(null);
			textPeso.setText(null);
		}

		private class noAgregaLetrasField implements KeyListener {

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				char letraIngresada = arg0.getKeyChar();
				if (!((letraIngresada >= '0') && (letraIngresada <= '9')) || (letraIngresada == KeyEvent.VK_BACK_SPACE)
						|| (letraIngresada == KeyEvent.VK_DELETE)) {
					arg0.consume();
				}
			}

		}

		private class btnAgregaMasAlternativas implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (letra <= 79) {
					agregaAlternativa(grupoCorrecto);

				} else {
					JOptionPane.showMessageDialog(null, "Alternativas maxima alcanzada");
				}

			}
		}

		private class botonGuardarSelMulListener implements ActionListener {
			@Override
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

					examenCreado.agregaPregunta(new Selec_Mul_Pregunta(enunciadoDeLaPregunta, alternativas,
							indexCorrectoSelecMul, pesoDeLaPregunta));
					totalPreguntas++;
					labelContadorTotalPreguntas.setText("Total preguntas: " + (totalPreguntas));
					botonFinalizar.setEnabled(true);
					grupoCorrecto.clearSelection();
					letra = 65;
					agregaOtraPregunta(panelCreador, panelCreadorSeleccionMultiple());
				} else {
					JOptionPane.showMessageDialog(null, new JLabel("Examen Vacio"), "ERROR CRITICO",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		}

		private class botonGuardarVerdFalsoListener implements ActionListener {
			@Override
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

					if (grupoCorrecto != null && grupoCorrecto.getSelection() != null) {
						if (grupoCorrecto.getSelection().getActionCommand().equals("Verdadero")) {
							respuestaVerdFalso = true;
						} else {
							respuestaVerdFalso = false;
						}
					} else {
						JOptionPane.showMessageDialog(null, new JLabel("Seleccione estado de la respuesta"),
								"ERROR CRITICO", JOptionPane.ERROR_MESSAGE);
						return;
					}

					enunciadoDeLaPregunta = textEnunciado.getText();
					pesoDeLaPregunta = Integer.parseInt(textPeso.getText());

					System.out.println("enunciado: " + enunciadoDeLaPregunta);
					System.out.println("resp correcta: " + respuestaVerdFalso);
					System.out.println("peso: " + pesoDeLaPregunta);

					examenCreado.agregaPregunta(
							new TFpreguntas(enunciadoDeLaPregunta, respuestaVerdFalso, pesoDeLaPregunta));
					totalPreguntas++;
					labelContadorTotalPreguntas.setText("Total preguntas: " + (totalPreguntas));
					botonFinalizar.setEnabled(true);
					vaciaTextVerFalso();
				} else {
					JOptionPane.showMessageDialog(null, new JLabel("Examen Vacio"), "ERROR CRITICO",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		}

		private class botonGuardarRespCorta implements ActionListener {

			@Override
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

					if (txtRespuestaCortaPgr == null || txtRespuestaCortaPgr.getText().isBlank()) {
						JOptionPane.showMessageDialog(null, new JLabel("Respuesta Vacia"), "ERROR CRITICO",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					pesoDeLaPregunta = Integer.parseInt(textPeso.getText());
					enunciadoDeLaPregunta = textEnunciado.getText();
					respuestaRespCorta = txtRespuestaCortaPgr.getText();

					System.out.println("enunciado: " + enunciadoDeLaPregunta);
					System.out.println("resp correcta: " + respuestaRespCorta);
					System.out.println("peso: " + pesoDeLaPregunta);

					examenCreado.agregaPregunta(new Resp_Cortas_Pregunta(enunciadoDeLaPregunta, respuestaRespCorta, pesoDeLaPregunta));
					totalPreguntas++;
					labelContadorTotalPreguntas.setText("Total preguntas: " + (totalPreguntas));
					botonFinalizar.setEnabled(true);
					vaciaRespuestaCorta();
				} else {
					JOptionPane.showMessageDialog(null, new JLabel("Examen Vacio"), "ERROR CRITICO",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		private class botonActivarTiempo implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (activarTiempo.isSelected()) {
					textTiempo.setEnabled(true);
					textTiempo.setEditable(true);
					textTiempo.setBackground(Color.WHITE);
					textTiempo.setForeground(Color.BLACK);
					textTiempo.setFont(new Font("Tahoma", Font.PLAIN, 15));
				} else {
					textTiempo.setEnabled(false);
					textTiempo.setBackground(new Color(23, 32, 48));
					textTiempo.setEditable(false);
					textTiempo.setText(null);
				}
			}
		}

		private class botonFinalizarCreacionExamen implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Controlador.ConvierteExamenJson json = new ConvierteExamenJson();
				
				String direccion = "";
				
				try {
					direccion = new File(".").getCanonicalPath() + "\\Profesor\\Examenes\\Examenes " + getProfesorName();
					
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (examenCreado != null) {
					if (CamposVacios()) {
						if (activarTiempo.isSelected()) {
							if (textTiempo == null || textTiempo.getText().isBlank()) {
								JOptionPane.showMessageDialog(null, new JLabel("Asigne Tiempo Al Examen"),"ERROR CRITICO", JOptionPane.ERROR_MESSAGE);
								return;
							} 
						}
						
						json.guardarenArchivo(direccion+"\\"+ProfesorGUI.nombreDelArchivo, textTiempo.getText(),totalPreguntas);
						
						if (examenCreado.selecmulpreg != null && examenCreado.selecmulpreg.size() >= 1) {
							for (int i = 0; i < examenCreado.selecmulpreg.size(); i++) {
								json.guardarenArreglo(direccion, examenCreado.selecmulpreg.get(i),ProfesorGUI.nombreDelArchivo);
							}
							
						}
						if (examenCreado.tfpreg != null && examenCreado.tfpreg.size() >= 1) {
							for (int i = 0; i < examenCreado.tfpreg.size(); i++) {
								json.guardarenArreglo(direccion, examenCreado.tfpreg.get(i),ProfesorGUI.nombreDelArchivo);
							}
							
						}
						if (examenCreado.rcpreg != null && examenCreado.rcpreg.size() >= 1) {
							for (int i = 0; i < examenCreado.rcpreg.size(); i++) {
								json.guardarenArreglo(direccion, examenCreado.rcpreg.get(i), ProfesorGUI.nombreDelArchivo);
							}
							
						}
						json.guardaArregloEnArchivo(direccion+"\\"+ProfesorGUI.nombreDelArchivo);
						try {
							if(!new File(new File(".").getCanonicalPath() + "\\Profesor\\Notas\\Notas " + getProfesorName()+"\\Examen "+ProfesorGUI.nombreDelArchivo).exists()) {
								new File(new File(".").getCanonicalPath() + "\\Profesor\\Notas\\Notas " + getProfesorName()+"\\Examen "+ProfesorGUI.nombreDelArchivo).mkdirs();
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						JOptionPane.showInternalMessageDialog(null, "Examen Creado");
						panelMayorCrearExamen.removeAll();
						panelMayorCrearExamen.add(MenuProfesor());
						panelMayorCrearExamen.repaint();
						pack();
						setLocationRelativeTo(null);
					} else {
						JOptionPane.showMessageDialog(null, new JLabel("Guarde La Pregunta Antes De Finalizar"),
								"ERROR CRITICO", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, new JLabel("Examen Vacio"), "ERROR CRITICO",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

}
