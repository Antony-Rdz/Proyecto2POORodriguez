import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.border.EmptyBorder;
import com.placeholder.PlaceHolder;
import java.util.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegistroProfesores extends JFrame {

	private JPanel panelRegistro;
	private JPanel btnPanel;
	private JPanel rutPanel;
	private JPanel clavePanel;
	private JPanel confirmaClavePanel;
	private JTextField textRut;
	private JButton btnModifica;
	private JButton btnBorrar;
	private JButton btnGuardar;
	JLabel lblConfirmaClave;
	JLabel lblContraseniaProfesor;
	JLabel lblRutProfesor;
	Connection conect = null;
	public static final String URL = "jdbc:mysql://localhost:3306/datosprograma?useSSL=false";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "Melopomelo13";
	PreparedStatement ps;
	ResultSet resl;
	PlaceHolder holder;
	private JPasswordField textClave;
	private JPasswordField textConfirmaClave;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroProfesores frame = new RegistroProfesores();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Connection getConnection() {
		Connection conect = null;
		try {
			conect = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			System.out.println(e);
		}

		return conect;
	}

	private void limpiarCajas() {
		textRut.setText(null);
		textClave.setText(null);
		textConfirmaClave.setText(null);
	}

	public static boolean validarut(String rut) {
		return rut.matches("^([0-9]{8,9}-[0-9|k|K])$");
	}

	public RegistroProfesores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		panelRegistro = new JPanel();
		panelRegistro.setBorder(new EmptyBorder(20, 20, 20, 20));
		panelRegistro.setLayout(new GridLayout(0,1,0,30));
		setContentPane(panelRegistro);

		/******************* PANELES *************************************/
		rutPanel = new JPanel(new GridLayout(0,2,30,0));
		panelRegistro.add(rutPanel);

		clavePanel = new JPanel(new GridLayout(0,2,30,0));
		panelRegistro.add(clavePanel);

		confirmaClavePanel = new JPanel(new GridLayout(0,2,30,0));
		panelRegistro.add(confirmaClavePanel);

		btnPanel = new JPanel(new GridLayout(0,3,10,0));
		panelRegistro.add(btnPanel);
		/******************************************************************/

		lblRutProfesor = new JLabel("Rut");
		lblRutProfesor.setForeground(Color.BLACK);
		lblRutProfesor.setFont(new Font("Calibri", Font.BOLD, 20));
		rutPanel.add(lblRutProfesor);

		textRut = new JTextField(15);
		holder = new PlaceHolder(textRut, "Sin punto y con guión");
		textRut.setForeground(Color.GRAY);
		rutPanel.add(textRut);

		lblContraseniaProfesor = new JLabel("Contrase\u00F1a");
		lblContraseniaProfesor.setForeground(Color.BLACK);
		lblContraseniaProfesor.setFont(new Font("Calibri", Font.BOLD, 20));
		clavePanel.add(lblContraseniaProfesor);

		textClave = new JPasswordField(15);
		holder = new PlaceHolder(textClave, "*********");
		clavePanel.add(textClave, BorderLayout.EAST);

		lblConfirmaClave = new JLabel("Confirmar contrase\u00F1a");
		lblConfirmaClave.setFont(new Font("Calibri", Font.BOLD, 20));
		lblConfirmaClave.setForeground(Color.BLACK);
		confirmaClavePanel.add(lblConfirmaClave, BorderLayout.WEST);

		textConfirmaClave = new JPasswordField(15);
		holder = new PlaceHolder(textConfirmaClave, "*********");
		confirmaClavePanel.add(textConfirmaClave, BorderLayout.EAST);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conect = null;
				String claveaux1 = "", claveaux2 = "";
				try {
					for (char c : textClave.getPassword()) {
						claveaux1 += c;
					}
					for (char c : textConfirmaClave.getPassword()) {
						claveaux2 += c;
					}
					conect = getConnection();
					if (claveaux1.equals(claveaux2)) {
						if (validarut(textRut.getText())) {
							ps = conect.prepareStatement(
									"INSERT INTO profesores (Rut,contraseña) VALUES(?,AES_ENCRYPT(?,'profesores'))");
							ps.setString(1, textRut.getText());
							ps.setString(2, claveaux2);
							int resl = ps.executeUpdate();

							if (resl > 0) {
								JOptionPane.showMessageDialog(null, "Profesor guardado");
								limpiarCajas();
							} else {
								JOptionPane.showMessageDialog(null,
										"Error al guardar profesor");
								limpiarCajas();
							}
							conect.close();
						} else {
							JOptionPane.showMessageDialog(null, "Formato de Rut invalido");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
					}
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		});
		btnPanel.add(btnGuardar);
		
		btnModifica = new JButton("Actualizar");
		btnPanel.add(btnModifica);
		
		btnBorrar = new JButton("Borrar");
		btnPanel.add(btnBorrar);

		pack();
		setLocationRelativeTo(null);
	}
}
