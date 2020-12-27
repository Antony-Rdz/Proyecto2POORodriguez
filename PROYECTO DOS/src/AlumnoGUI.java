import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;

import Controlador.FileSystemModel;

public class AlumnoGUI extends JFrame {

	private JPanel panelMayorAlumno;
	JTextField textRutInicSes;
	JPasswordField textContraInicSes;
	JTextField textNombre;
	JTextField textApellido;
	JTextField textRut;
	JComboBox<String> profesores;
	JTextField textCorreo;
	JPasswordField textContra;
	JPasswordField textConfirma;
	String rut;
	String ubicacionArchivos = "";	

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatCarbonIJTheme());
		} catch (Exception ex) {
			System.err.println("Tema no aplicado");
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlumnoGUI frame = new AlumnoGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AlumnoGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelMayorAlumno = new JPanel();
		// panelMayorAlumno.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelMayorAlumno.setLayout(new BorderLayout(0, 0));
		setContentPane(panelMayorAlumno);
		// panelMayorAlumno.add(panelRegistro());
		 //panelMayorAlumno.add(InicioSesionAlumno());
		panelMayorAlumno.add(MenuAlumno());
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public JPanel InicioSesionAlumno() {

		JPanel panelContenedorInicioSesion = new JPanel();
		panelContenedorInicioSesion.setBorder(new EmptyBorder(0, 0, 30, 0));
		panelContenedorInicioSesion.setLayout(new BoxLayout(panelContenedorInicioSesion, BoxLayout.Y_AXIS));

		JPanel panelMenuBar = new JPanel(new BorderLayout(0, 10));
		panelContenedorInicioSesion.add(panelMenuBar);

		JPanel panelImagenInicSes = new JPanel(new BorderLayout());
		panelImagenInicSes.setBorder(new EmptyBorder(40, 70, 0, 70));
		panelContenedorInicioSesion.add(panelImagenInicSes);

		panelContenedorInicioSesion.add(Box.createRigidArea(new Dimension(0, 30)));

		JPanel panelLabelRut = new JPanel(new BorderLayout());
		panelLabelRut.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelContenedorInicioSesion.add(panelLabelRut);

		JPanel panelTextRut = new JPanel(new BorderLayout());
		panelTextRut.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelContenedorInicioSesion.add(panelTextRut);

		panelContenedorInicioSesion.add(Box.createRigidArea(new Dimension(0, 30)));

		JPanel panelLabelContra = new JPanel(new BorderLayout());
		panelLabelContra.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelContenedorInicioSesion.add(panelLabelContra);

		JPanel panelTextContra = new JPanel(new BorderLayout());
		panelTextContra.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelContenedorInicioSesion.add(panelTextContra);

		panelContenedorInicioSesion.add(Box.createRigidArea(new Dimension(0, 20)));

		JPanel panelBotonIngresar = new JPanel(new BorderLayout());
		panelBotonIngresar.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelContenedorInicioSesion.add(panelBotonIngresar);

		JPanel panelRecuperaContra = new JPanel(new BorderLayout());
		panelRecuperaContra.setBorder(new EmptyBorder(0, 25, 0, 25));
		panelContenedorInicioSesion.add(panelRecuperaContra);

		JMenuBar barraOpciones = new JMenuBar();
		panelMenuBar.add(barraOpciones, BorderLayout.NORTH);
		JMenu menuOpciones = new JMenu("Opciones");
		barraOpciones.add(menuOpciones);
		JMenuItem VolverOpcion = new JMenuItem("Volver");
		JMenuItem SalirOpcion = new JMenuItem("Salir");
		menuOpciones.add(VolverOpcion);
		menuOpciones.add(SalirOpcion);

		String titulo = "Inicio de Sesión<br>Alumno";
		JLabel lblTitulo = new JLabel("<html><div style='text-align: center;'>" + titulo + "</div></html>");
		lblTitulo.setFont(new Font("Thaoma", Font.PLAIN, 30));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		panelMenuBar.add(lblTitulo, BorderLayout.SOUTH);

		JLabel lblImagenAlumnoInicSes = new JLabel();
		String ubicacionImagen = "";
		try {

			ubicacionImagen = new File(".").getCanonicalPath() + "\\res\\alumno.png";
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		ImageIcon imagenAlumno = new ImageIcon(ubicacionImagen);
		lblImagenAlumnoInicSes
				.setIcon(new ImageIcon(imagenAlumno.getImage().getScaledInstance(240, 200, Image.SCALE_DEFAULT)));
		panelImagenInicSes.add(lblImagenAlumnoInicSes, BorderLayout.CENTER);

		JLabel lblRut = new JLabel("Rut");
		lblRut.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRut.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelRut.add(lblRut, BorderLayout.CENTER);

		textRutInicSes = new JTextField();
		textRutInicSes.setBackground(Color.WHITE);
		textRutInicSes.setForeground(Color.BLACK);
		textRutInicSes.addKeyListener(new noAgregaLetrasRUTField());
		textRutInicSes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelTextRut.add(textRutInicSes, BorderLayout.CENTER);

		JLabel lblContraseña = new JLabel("Contraseña");
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblContraseña.setHorizontalAlignment(SwingConstants.LEFT);
		panelLabelContra.add(lblContraseña, BorderLayout.CENTER);

		textContraInicSes = new JPasswordField();
		textContraInicSes.setBackground(Color.WHITE);
		textContraInicSes.setForeground(Color.BLACK);
		textContraInicSes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelTextContra.add(textContraInicSes, BorderLayout.CENTER);

		JButton botonIngresar = new JButton("Ingresar");
		botonIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (verificaCasillasVaciasInicSes()) {
					if (validarut(textRutInicSes.getText())) {
						panelMayorAlumno.removeAll();
						panelMayorAlumno.add(MenuAlumno());
						panelMayorAlumno.updateUI();
						pack();
						setLocationRelativeTo(null);
					} else {
						JOptionPane.showMessageDialog(null, "Formato de rut invalido");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Llene todos los campos");
				}
			}
		});
		botonIngresar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelBotonIngresar.add(botonIngresar, BorderLayout.CENTER);

		JButton botonRecuperaContra = new JButton("Recuperar Contraseña");
		botonRecuperaContra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new Email("alumnos");
			}
		});
		botonRecuperaContra.setFont(new Font("Tahoma", Font.PLAIN, 13));
		botonRecuperaContra.setBackground(new Color(23, 32, 48));
		botonRecuperaContra.setBorderPainted(false);
		panelRecuperaContra.add(botonRecuperaContra, BorderLayout.WEST);

		JButton botonCrearCuenta = new JButton("No tengo cuenta");
		botonCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelMayorAlumno.removeAll();
				panelMayorAlumno.add(panelRegistro());
				pack();
				setLocationRelativeTo(null);
				panelMayorAlumno.updateUI();
			}
		});
		botonCrearCuenta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		botonCrearCuenta.setBackground(new Color(23, 32, 48));
		botonCrearCuenta.setBorderPainted(false);
		panelRecuperaContra.add(botonCrearCuenta, BorderLayout.EAST);

		return panelContenedorInicioSesion;
	}

	public JPanel panelRegistro() {

		JPanel panelMayorRegistro = new JPanel();
		panelMayorRegistro.setLayout(new BoxLayout(panelMayorRegistro, BoxLayout.Y_AXIS));
		panelMayorRegistro.setBorder(new EmptyBorder(30, 15, 30, 15));

		JPanel panelTitulo = new JPanel(new BorderLayout(0, 20));
		panelMayorRegistro.add(panelTitulo);

		panelMayorRegistro.add(Box.createRigidArea(new Dimension(0, 30)));

		JPanel panelNombreApellido = new JPanel();
		panelNombreApellido.setBorder(new EmptyBorder(0, 15, 0, 15));
		panelNombreApellido.setLayout(new BoxLayout(panelNombreApellido, BoxLayout.X_AXIS));
		panelMayorRegistro.add(panelNombreApellido);

		JPanel panelName = new JPanel(new BorderLayout());
		JPanel panelApellido = new JPanel(new BorderLayout());
		panelNombreApellido.add(panelName);
		panelNombreApellido.add(Box.createRigidArea(new Dimension(30, 0)));
		panelNombreApellido.add(panelApellido);

		panelMayorRegistro.add(Box.createRigidArea(new Dimension(0, 15)));

		JPanel panelRutyProfesor = new JPanel(new BorderLayout());
		panelRutyProfesor.setBorder(new EmptyBorder(0, 15, 0, 15));
		panelRutyProfesor.setLayout(new BoxLayout(panelRutyProfesor, BoxLayout.X_AXIS));
		panelMayorRegistro.add(panelRutyProfesor);

		JPanel panelRut = new JPanel(new BorderLayout());
		JPanel panelProfesor = new JPanel(new BorderLayout());
		panelRutyProfesor.add(panelRut);
		panelRutyProfesor.add(Box.createRigidArea(new Dimension(30, 0)));
		panelRutyProfesor.add(panelProfesor);

		panelMayorRegistro.add(Box.createRigidArea(new Dimension(0, 15)));

		JPanel panelCorreo = new JPanel(new BorderLayout());
		panelCorreo.setBorder(new EmptyBorder(0, 15, 0, 15));
		panelMayorRegistro.add(panelCorreo);

		panelMayorRegistro.add(Box.createRigidArea(new Dimension(0, 15)));

		JPanel panelContrayConfirma = new JPanel();
		panelContrayConfirma.setBorder(new EmptyBorder(0, 15, 0, 15));
		panelContrayConfirma.setLayout(new BoxLayout(panelContrayConfirma, BoxLayout.X_AXIS));
		panelMayorRegistro.add(panelContrayConfirma);

		JPanel panelContra = new JPanel(new BorderLayout());
		JPanel panelConfirma = new JPanel(new BorderLayout());
		panelContrayConfirma.add(panelContra);
		panelContrayConfirma.add(Box.createRigidArea(new Dimension(30, 0)));
		panelContrayConfirma.add(panelConfirma);

		panelMayorRegistro.add(Box.createRigidArea(new Dimension(0, 15)));

		JPanel panelBotones = new JPanel(new BorderLayout());
		panelBotones.setBorder(new EmptyBorder(0, 0, 0, 15));
		panelMayorRegistro.add(panelBotones);

		String titulo = "Crea una cuenta de<br>Alumno";
		JLabel lblTitulo = new JLabel("<html><div style='text-align: center;'>" + titulo + "</div></html>");
		lblTitulo.setFont(new Font("Thaoma", Font.PLAIN, 30));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitulo.add(lblTitulo, BorderLayout.NORTH);

		JLabel lblImagenAlumnoInicSes = new JLabel();
		lblImagenAlumnoInicSes.setHorizontalAlignment(SwingConstants.CENTER);
		String ubicacionImagen = "";
		try {

			ubicacionImagen = new File(".").getCanonicalPath() + "\\res\\alumno.png";
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		ImageIcon imagenAlumno = new ImageIcon(ubicacionImagen);
		lblImagenAlumnoInicSes
				.setIcon(new ImageIcon(imagenAlumno.getImage().getScaledInstance(190, 160, Image.SCALE_DEFAULT)));
		panelTitulo.add(lblImagenAlumnoInicSes, BorderLayout.CENTER);

		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelNombre.setHorizontalAlignment(SwingConstants.LEFT);
		panelName.add(labelNombre, BorderLayout.NORTH);

		JLabel labelApellido = new JLabel("Apellido");
		labelApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelApellido.setHorizontalAlignment(SwingConstants.LEFT);
		panelApellido.add(labelApellido, BorderLayout.NORTH);

		textNombre = new JTextField();
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textNombre.setBackground(Color.WHITE);
		textNombre.setForeground(Color.BLACK);
		panelName.add(textNombre, BorderLayout.CENTER);

		textApellido = new JTextField();
		textApellido.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textApellido.setBackground(Color.WHITE);
		textApellido.setForeground(Color.BLACK);
		panelApellido.add(textApellido, BorderLayout.CENTER);

		JLabel labelRut = new JLabel("Rut");
		labelRut.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelRut.setHorizontalAlignment(SwingConstants.LEFT);
		panelRut.add(labelRut, BorderLayout.NORTH);

		textRut = new JTextField(8);
		textRut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textRut.addKeyListener(new noAgregaLetrasRUTField());
		textRut.setBackground(Color.WHITE);
		textRut.setForeground(Color.BLACK);
		panelRut.add(textRut, BorderLayout.CENTER);

		JLabel labelProfesor = new JLabel("Profesor");
		labelProfesor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelProfesor.setHorizontalAlignment(SwingConstants.LEFT);
		panelProfesor.add(labelProfesor, BorderLayout.NORTH);

		profesores = new JComboBox<>();
		profesores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		agregaProfesoresCombo(profesores);
		panelProfesor.add(profesores, BorderLayout.CENTER);

		JLabel labelCorreo = new JLabel("Correo");
		labelCorreo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		panelCorreo.add(labelCorreo, BorderLayout.NORTH);

		textCorreo = new JTextField();
		textCorreo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textCorreo.setBackground(Color.WHITE);
		textCorreo.setForeground(Color.BLACK);
		panelCorreo.add(textCorreo, BorderLayout.CENTER);

		JLabel labelContra = new JLabel("Contraseña");
		labelContra.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelContra.setHorizontalAlignment(SwingConstants.LEFT);
		panelContra.add(labelContra, BorderLayout.NORTH);

		JLabel labelConfirma = new JLabel("Confirma");
		labelConfirma.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelConfirma.setHorizontalAlignment(SwingConstants.LEFT);
		panelConfirma.add(labelConfirma, BorderLayout.NORTH);

		textContra = new JPasswordField();
		textContra.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textContra.setBackground(Color.WHITE);
		textContra.setForeground(Color.BLACK);
		panelContra.add(textContra, BorderLayout.CENTER);

		textConfirma = new JPasswordField();
		textConfirma.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textConfirma.setBackground(Color.WHITE);
		textConfirma.setForeground(Color.BLACK);
		panelConfirma.add(textConfirma, BorderLayout.CENTER);

		JButton botonCrearCuenta = new JButton("Crear Cuenta");
		botonCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (verificaCasillasVaciasRegistro()) {
					llenaDatosABase();
				} else {
					JOptionPane.showMessageDialog(null, "Llene todos los campos");
				}

			}
		});
		botonCrearCuenta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelBotones.add(botonCrearCuenta, BorderLayout.EAST);

		JButton botonIniciarSesion = new JButton("Iniciar Sesión");
		botonIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelMayorAlumno.removeAll();
				panelMayorAlumno.add(InicioSesionAlumno());
				pack();
				setLocationRelativeTo(null);
				panelMayorAlumno.updateUI();
			}
		});
		botonIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		botonIniciarSesion.setBackground(new Color(23, 32, 48));
		botonIniciarSesion.setBorderPainted(false);
		panelBotones.add(botonIniciarSesion, BorderLayout.WEST);

		return panelMayorRegistro;
	}

	private void agregaProfesoresCombo(JComboBox<String> profesores) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Conexion objCon = new Conexion();
			Connection conn = objCon.getConnection();
			ps = conn.prepareStatement("SELECT Nombre,Apellido FROM profesores");
			rs = ps.executeQuery();

			profesores.addItem("Seleccione");
			while (rs.next()) {
				profesores.addItem(rs.getString("Nombre") + " " + rs.getString("Apellido"));
			}

		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	private JPanel MenuAlumno() {

		JPanel panelContenedorMenu = new JPanel(new BorderLayout());
		JPanel panelContenedorIzquierdo = new JPanel(new BorderLayout());
		panelContenedorIzquierdo.setBorder(new EmptyBorder(0, 0, 0, 0));
		JPanel panelContenedorDerecho = new JPanel(new BorderLayout());

		panelContenedorMenu.add(panelContenedorIzquierdo, BorderLayout.WEST);
		panelContenedorMenu.add(panelContenedorDerecho, BorderLayout.CENTER);

		JMenuBar barraOpciones = new JMenuBar();
		panelContenedorMenu.add(barraOpciones, BorderLayout.NORTH);
		JMenu menuOpciones = new JMenu("Opciones");
		JMenu menuCreacion = new JMenu("Creación");
		menuCreacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		menuOpciones.setFont(new Font("Tahoma", Font.PLAIN, 15));
		barraOpciones.add(menuOpciones);
		barraOpciones.add(menuCreacion);
		JMenuItem VolverOpcion = new JMenuItem("Volver");
		JMenuItem SalirOpcion = new JMenuItem("Salir");
		VolverOpcion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		SalirOpcion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		menuOpciones.add(VolverOpcion);
		menuOpciones.add(SalirOpcion);

		JLabel lblImagenProfesor = new JLabel();
		String ubicacionImagen = "";
	
		try {
			ubicacionImagen = new File(".").getCanonicalPath() + "\\res\\alumno.png";
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		ImageIcon imagenAdmin = new ImageIcon(ubicacionImagen);
		lblImagenProfesor
				.setIcon(new ImageIcon(imagenAdmin.getImage().getScaledInstance(240, 200, Image.SCALE_DEFAULT)));
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
			ubicacionArchivos = new File(".").getCanonicalPath() + "\\Profesor\\Examenes " + getProfesorAlumno();
			if (!new File(ubicacionArchivos).exists()) {
				new File(ubicacionArchivos).mkdirs();
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		//-String ubicacionExamenes = ubicacionArchivos.substring(ubicacionArchivos.lastIndexOf('\\')+1);


		JTable tablaDeExamamenesParaRealizar = new JTable();
		tablaDeExamamenesParaRealizar.setModel(encabezadoExamen());
		llenaTablaDeExamenes(tablaDeExamamenesParaRealizar,ubicacionArchivos);
		tablaDeExamamenesParaRealizar.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
				    if (e.getClickCount() == 2) {
				      int fila = tablaDeExamamenesParaRealizar.getSelectedRow();
				      realizarExamen(fila, ubicacionArchivos);
				    }
				  }
				});
		tablaDeExamamenesParaRealizar.getColumnModel().getColumn(0).setPreferredWidth(300);
		tablaDeExamamenesParaRealizar.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaDeExamamenesParaRealizar.getTableHeader().setReorderingAllowed(false);
		tablaDeExamamenesParaRealizar.getTableHeader().setResizingAllowed(false);
		
		JScrollPane scrollArbol = new JScrollPane(tablaDeExamamenesParaRealizar);
		panelCreacionExamen.add(scrollArbol, BorderLayout.CENTER);

		JTable tablaDeExamenesNotas = new JTable();
		tablaDeExamenesNotas.setModel(encabezadoNota());
		llenaTablaDeNotas(tablaDeExamenesNotas,ubicacionArchivos);
		tablaDeExamenesNotas.getColumnModel().getColumn(0).setPreferredWidth(300);
		tablaDeExamenesNotas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaDeExamenesNotas.getTableHeader().setReorderingAllowed(false);
		tablaDeExamenesNotas.getTableHeader().setResizingAllowed(false);
		JScrollPane scrollTablaNota = new JScrollPane(tablaDeExamenesNotas);
		panelNotasExamen.add(scrollTablaNota, BorderLayout.CENTER);

		return panelContenedorMenu;

	}

	private String getProfesorAlumno() {
		String profe = "";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Conexion objCon = new Conexion();
			Connection conn = objCon.getConnection();
			//if (textRutInicSes != null) {
				rut = "20779149-0";//textRutInicSes.getText();
				ps = conn.prepareStatement("SELECT Profesor FROM alumnos WHERE Rut = ?");
				ps.setString(1, rut);
				rs = ps.executeQuery();
				
				while (rs.next()) {
					profe = rs.getString("Profesor");
				}
			//}
		} catch (Exception e2) {
			System.err.println(e2.toString());
		}
		return profe;
	}

	DefaultTableModel encabezadoNota() {
		String[] rubrosTablaProf = { "Examenes", "Nota" };
		DefaultTableModel tablaPorDefecto = new DefaultTableModel(rubrosTablaProf, 0);
		return tablaPorDefecto;
	}

	DefaultTableModel encabezadoExamen() {
		String[] rubrosTablaProf = { "Examenes", "Estado" };
		DefaultTableModel tablaPorDefecto = new DefaultTableModel(rubrosTablaProf, 0);
		return tablaPorDefecto;
	}
	
	private void llenaTablaDeExamenes(JTable tabla, String ubicacion) {
		DefaultTableModel modelo = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelo.addColumn("Examenes");
		modelo.addColumn("Estado");
		Controlador.FileSystemModel ubicacionArchivos = new FileSystemModel(new File(ubicacion));
		File archivos = new File(ubicacion);
		String[] totalArchivos = archivos.list();
		Object[] fila = new Object[2];
		for (int i = 0; i < totalArchivos.length; i++) {
			fila[0] = ubicacionArchivos.getNombreArchivos(archivos,i);
			if(finalzoElExamen()) {
				fila[1] = "Resuelto";
			}else {
				fila[1] = "Sin resolver";
			}
			modelo.addRow(fila);
		}
		tabla.setModel(modelo);
		
	}
	
	private void llenaTablaDeNotas(JTable tabla, String ubicacion) {
		DefaultTableModel modelo = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelo.addColumn("Examenes");
		modelo.addColumn("Nota");
		Controlador.FileSystemModel ubicacionArchivos = new FileSystemModel(new File(ubicacion));
		File archivos = new File(ubicacion);
		String[] totalArchivos = archivos.list();
		Object[] fila = new Object[2];
		for (int i = 0; i < totalArchivos.length; i++) {
			fila[0] = ubicacionArchivos.getNombreArchivos(archivos,i);
			if(finalzoElExamen()) {
				fila[1] = "getNota()";
			}else {
				fila[1] = " ";
			}
			modelo.addRow(fila);
		}
		tabla.setModel(modelo);
		
	}
	
	private void realizarExamen(int fila, String ubicacion) {
		String examen;
		File archivos = new File(ubicacion);
		String[] archivoElegido = archivos.list();
		Controlador.archivos lector = new Controlador.archivos();
		examen = lector.leerArchivo(new File(ubicacion+"\\"+archivoElegido[fila]));
		System.out.println(examen);
		
		
	}
	
	
	private boolean finalzoElExamen() {
		return false;
	}

	private boolean verificaCasillasVaciasRegistro() {
		String clave = "", conficlave = "";
		for (char contra : textContra.getPassword()) {
			clave += contra;
		}
		for (char confi : textConfirma.getPassword()) {
			conficlave += confi;
		}
		if (textApellido.getText().isBlank() || textNombre.getText().isBlank() || textRut.getText().isBlank()
				|| textCorreo.getText().isBlank() || clave.isBlank() || conficlave.isBlank()
				|| profesores.getSelectedIndex() == 0) {
			return false;
		}
		return true;
	}

	private boolean verificaCasillasVaciasInicSes() {
		String clave = "";
		for (char c : textContraInicSes.getPassword()) {
			clave += c;
		}
		if (textRutInicSes.getText().isBlank() || clave.isBlank()) {
			return false;
		}
		return true;
	}

	public static boolean validarut(String rut) {
		return rut.matches("^([0-9]{8,9}-[0-9|k|K])$");
	}

	private void llenaDatosABase() {
		PreparedStatement ps = null;
		String claveaux1 = "", claveaux2 = "";
		if (!cuentaExiste()) {
			try {

				Conexion objCon = new Conexion();
				Connection conn = objCon.getConnection();
				ps = conn.prepareStatement(
						"INSERT INTO alumnos (Nombre,Apellido,Rut,Correo,Profesor,Contraseña) VALUES(?,?,?,?,?,AES_ENCRYPT(?,'alumno'))");

				for (char c : textContra.getPassword()) {
					claveaux1 += c;
				}
				for (char c : textConfirma.getPassword()) {
					claveaux2 += c;
				}

				if (claveaux1.equals(claveaux2)) {
					if (validarut(textRut.getText())) {

						ps.setString(1, textNombre.getText());
						ps.setString(2, textApellido.getText());
						ps.setString(3, textRut.getText());
						ps.setString(4, textCorreo.getText());
						ps.setString(5, (String) profesores.getSelectedItem());
						ps.setString(6, claveaux2);

						int resl = ps.executeUpdate();

						if (resl > 0) {
							JOptionPane.showMessageDialog(null, "Cuenta Creada");
							limpiaCajas();
						} else {
							JOptionPane.showMessageDialog(null, "No se pudo crear la cuenta");
						}
						conn.close();

					} else {
						JOptionPane.showMessageDialog(null, "Formato de Rut invalido");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
				}
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "<html>La cuenta ya existe<br>Por favor inicie sesión</html>");
		}

	}

	public void limpiaCajas() {
		textApellido.setText(null);
		textNombre.setText(null);
		textRut.setText(null);
		textCorreo.setText(null);
		textContra.setText(null);
		textConfirma.setText(null);
	}

	private boolean cuentaExiste() {

		String rut = "";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			Conexion objCon = new Conexion();
			Connection conn = objCon.getConnection();
			ps = conn.prepareStatement("SELECT Rut FROM alumnos WHERE Rut = ?");
			ps.setString(1, textRut.getText());
			rs = ps.executeQuery();

			while (rs.next()) {
				rut = rs.getString("Rut");
			}
			if (rut.equalsIgnoreCase(textRut.getText())) {
				return true;
			}
		} catch (Exception e2) {
			System.err.println(e2.toString());
		}
		return false;
	}

	private class noAgregaLetrasRUTField implements KeyListener {
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

}
