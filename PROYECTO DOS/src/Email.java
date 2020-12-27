import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;

public class Email extends JFrame {

	private JPanel PanelMayorAbs;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatCarbonIJTheme());
		} catch (Exception ex) {
			System.err.println("Tema no aplicado");
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Email frame = new Email("profesores");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Email(String tabla) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PanelMayorAbs = new JPanel();
		PanelMayorAbs.setLayout(new BorderLayout(0, 0));
		setContentPane(PanelMayorAbs);
		PanelMayorAbs.add(iniciador(tabla));
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public JPanel iniciador(String tabla) {
		
		/*if(tabla.equalsIgnoreCase("profesores")) {
			
			
		}*/

		JPanel panelMayorEmail = new JPanel(new BorderLayout(0, 35));

		JPanel panelHedderMayor = new JPanel(new BorderLayout());
		panelMayorEmail.add(panelHedderMayor, BorderLayout.NORTH);

		JMenuBar menuOpciones = new JMenuBar();
		JMenu opcion = new JMenu("Opciones");
		menuOpciones.add(opcion);
		JMenuItem regresar = new JMenuItem("Volver al inicio");
		regresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				if(tabla.equalsIgnoreCase("profesores")) {
					new ProfesorGUI();
				}else {
					new AlumnoGUI();
				}
				//ProfesorGUI profe = 
				//System.out.println("volver al inicio de sesion");
			}
		});
		opcion.add(regresar);
		panelHedderMayor.add(menuOpciones, BorderLayout.NORTH);

		JPanel panelIntroduceCorreo = new JPanel(new BorderLayout(0, 25));
		panelIntroduceCorreo.setBorder(new EmptyBorder(0, 35, 35, 35));
		panelMayorEmail.add(panelIntroduceCorreo, BorderLayout.CENTER);

		JPanel panelHedderIntroCorreo = new JPanel(new BorderLayout(0, 6));
		panelIntroduceCorreo.add(panelHedderIntroCorreo, BorderLayout.NORTH);
		JPanel panelBoddyIntroCorreo = new JPanel();
		panelBoddyIntroCorreo.setBorder(new EmptyBorder(0, 40, 0, 40));
		panelBoddyIntroCorreo.setLayout(new BoxLayout(panelBoddyIntroCorreo, BoxLayout.Y_AXIS));
		panelIntroduceCorreo.add(panelBoddyIntroCorreo, BorderLayout.CENTER);

		JLabel labelHeder = new JLabel("¿Has olvidado tu contraseña?");
		labelHeder.setHorizontalAlignment(SwingConstants.CENTER);
		labelHeder.setFont(new Font("Thaoma", Font.PLAIN, 30));
		panelHedderIntroCorreo.add(labelHeder, BorderLayout.NORTH);

		String text = "Por favor introduce la cuenta de email usada en el<br>registro";
		JLabel labelInstrucciones = new JLabel("<html><div style='text-align: center;'>" + text + "</div></html>");
		labelInstrucciones.setFont(new Font("Thaoma", Font.PLAIN, 16));
		labelInstrucciones.setHorizontalAlignment(SwingConstants.CENTER);
		panelHedderIntroCorreo.add(labelInstrucciones, BorderLayout.SOUTH);

		JPanel panelLabelBoddyInCorr = new JPanel(new BorderLayout());
		panelBoddyIntroCorreo.add(panelLabelBoddyInCorr);
		JPanel panelTextBoddyInCorr = new JPanel(new BorderLayout());
		panelBoddyIntroCorreo.add(panelTextBoddyInCorr);

		panelBoddyIntroCorreo.add(Box.createRigidArea(new Dimension(0, 25)));

		JPanel panelBtnBoddyInCorr = new JPanel(new BorderLayout());
		panelBoddyIntroCorreo.add(panelBtnBoddyInCorr);

		JLabel LabelBoddyInCorr = new JLabel("Tu Email");
		LabelBoddyInCorr.setFont(new Font("Thaoma", Font.PLAIN, 14));
		panelLabelBoddyInCorr.add(LabelBoddyInCorr, BorderLayout.WEST);

		JTextField TextBoddyInCorr = new JTextField();
		TextBoddyInCorr.setBackground(Color.WHITE);
		TextBoddyInCorr.setForeground(Color.BLACK);
		TextBoddyInCorr.setColumns(10);
		TextBoddyInCorr.setFont(new Font("Thaoma", Font.PLAIN, 20));
		panelTextBoddyInCorr.add(TextBoddyInCorr, BorderLayout.CENTER);

		JButton BtnBoddyInCorr = new JButton("Obtén una nueva contraseña");
		BtnBoddyInCorr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Properties propiedad = new Properties();
				String destinatario;
				String email;
				String nombre = "", apellido = "";
				PreparedStatement ps = null;
				ResultSet rs = null;
				MimeMessage mail;
				try {
					Conexion objCon = new Conexion();
					Connection conn = objCon.getConnection();
					email = TextBoddyInCorr.getText();
					ps = conn.prepareStatement("SELECT Nombre,Apellido FROM "+tabla+" WHERE Correo = ?");
					ps.setString(1, email);
					rs = ps.executeQuery();

					while (rs.next()) {
						nombre = rs.getString("Nombre");
						apellido = rs.getString("Apellido");
					}

				} catch (Exception e) {
					System.err.println(e.toString());
				}

				propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
				propiedad.setProperty("mail.smtp.starttls.enable", "true");
				propiedad.setProperty("mail.smtp.port", "587");
				propiedad.setProperty("mail.smtp.auth", "true");

				Session sesion = Session.getDefaultInstance(propiedad);

				String codigoDeVerificacion = codigoRandom();
				String correoEnvia = "exam.soft.edu@gmail.com";
				String contraseña = "melopomelo13";
				destinatario = TextBoddyInCorr.getText();
				String asunto = "Restauracion de contraseña ExamSoft";
				String mensaje = "Estimado/a " + nombre + " " + apellido + " "
						+ "Hemos recibido una solicitud para restablecer su contraseña."
						+ "Ingrese el siguiente codigo para restablecer su contraseña: " + codigoDeVerificacion
						+ " Si no desea cambiar su contraseña puede ignorar este correo." + "";

				if (isValidEmailAddress(destinatario)) {
					try {
						mail = new MimeMessage(sesion);
						mail.setFrom(new InternetAddress(correoEnvia));
						InternetAddress correo = new InternetAddress(destinatario);
						mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
						mail.setSubject(asunto);
						mail.setText(mensaje);
						Transport transporte = sesion.getTransport("smtp");
						transporte.connect(correoEnvia, contraseña);
						transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
						transporte.close();
					} catch (AddressException e) {
						e.printStackTrace();
					} catch (MessagingException e) {
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Codigo enviado");
				}else {
					JOptionPane.showMessageDialog(null, "Correo invalido");
				}
			}
		});
		BtnBoddyInCorr.setFont(new Font("Thaoma", Font.PLAIN, 20));
		panelBtnBoddyInCorr.add(BtnBoddyInCorr, BorderLayout.CENTER);

		return panelMayorEmail;
	}

	public String codigoRandom() {
		int leftLimit = 48; // numero '0'
		int rightLimit = 90; // letra 'z'
		int targetStringLength = 6;
		Random random = new Random();

		String codigoRndm = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return codigoRndm;
	}

	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

}
