package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Exceptions.*;
import Logica.Programa;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.InvalidKeyException;
import java.awt.Font;
import javax.swing.JScrollBar;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.SwingConstants;

/**
 * 
 * @author Juan_Santi
 * Aplicación con Interfaz Grafica de Usuario que brinda las funcionalidades planteadas en el paquete Lógica
 *
 */
public class App extends JFrame {

	private JPanel contentPane;
	private JTextField LU;
	private JTextField Nota;
	private Programa p;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setEnabled(true);
		setTitle("Registro de Alumnos ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 545);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		
		//Crea la primer pantalla emergente y solicita el nombre de la materia
		String materia = JOptionPane.showInputDialog("Ingrese el nombre de la Materia");

		
		while(materia.equals("")) {
			JOptionPane.showMessageDialog(null,"Ingrese el nombre de la materia");
			materia = JOptionPane.showInputDialog("Ingrese el nombre de la Materia");
		}
		
		
		//Inicializa el programa con la variable materia
		p = new Programa(materia);
		setTitle("Registro de  Alumnos - "+p.getMateria());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea Consola = new JTextArea();
		Consola.setFont(new Font("Consolas", Font.BOLD, 15));
		Consola.setEditable(false);
		JScrollPane scroll = new JScrollPane(Consola);
		scroll.setBounds(10, 300, 505, 150);
		contentPane.add(scroll);
		
		
		JLabel LabelLU = new JLabel("LU:");
		LabelLU.setBounds(10, 5, 50, 36);
		contentPane.add(LabelLU);
		
		JLabel LabelNota = new JLabel("NOTA :");
		LabelNota.setBounds(10, 50, 118, 36);
		contentPane.add(LabelNota);
		
		LU = new JTextField();
		LU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key>= 48 && key<=57;
				if (!numeros)
					e.consume();
			}
		});
		LU.setBounds(130, 11, 260, 25);
		contentPane.add(LU);
		LU.setColumns(10);
		
		Nota = new JTextField();
		Nota.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numeros = key>=48 && key<= 57;
				if(!numeros)
					e.consume();
			}
		});
		Nota.setBounds(132, 56, 258, 25);
		contentPane.add(Nota);
		Nota.setColumns(10);
		
		//BOTONES Y OYENTES
		
		JButton IngresarAlumno = new JButton("Ingresar Alumno Nuevo");
		IngresarAlumno.setFont(IngresarAlumno.getFont().deriveFont(IngresarAlumno.getFont().getStyle() | Font.BOLD));
		
		IngresarAlumno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
						
						p.agregarAlumno(Integer.parseInt(LU.getText()), Integer.parseInt(Nota.getText()));
						Consola.setText("El alumno con LU: "+LU.getText()+" fue ingresado");
				}catch(ParametrosInvalidosException a) {
					JOptionPane.showMessageDialog(null, a.getMessage());
				}catch (NotaInvalidaException a) {
					JOptionPane.showMessageDialog(null, a.getMessage());
				}catch (AlumnoIngresadoException a) {
					JOptionPane.showMessageDialog(null, a.getMessage());
				}catch (RangoDeLegajoInvalidoException a) {
					JOptionPane.showMessageDialog(null, a.getMessage());
				}catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(null, "Ingrese bien los datos");
				}
				
			}
		});
		IngresarAlumno.setBounds(10, 99, 245, 25);
		contentPane.add(IngresarAlumno);
		
		JButton ObtenerMateria = new JButton("Obtener Nombre de la Materia");
		ObtenerMateria.setFont(ObtenerMateria.getFont().deriveFont(ObtenerMateria.getFont().getStyle() | Font.BOLD));
		
		ActionListener e = new ActionListener(){
		
			@Override
			public void actionPerformed(ActionEvent e) {
				Consola.setText(p.getMateria());
				
			}
		};
		ObtenerMateria.addActionListener(e);
		
		
		
		ObtenerMateria.setBounds(269, 99, 245, 25);
		contentPane.add(ObtenerMateria);
		
		JButton EliminarAlumno = new JButton("Eliminar un Alumno");
		EliminarAlumno.setFont(EliminarAlumno.getFont().deriveFont(EliminarAlumno.getFont().getStyle() | Font.BOLD));
		EliminarAlumno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					try {
						
						if(p.estaAlumno(Integer.parseInt(LU.getText()))) {
							p.eliminarAlumno(Integer.parseInt(LU.getText()));
							Consola.setText(p.eliminarAlumno(Integer.parseInt(LU.getText())));
						}					
						
				}catch (NumberFormatException a) {
					JOptionPane.showMessageDialog(null, "Ingrese bien los datos");
				}catch(EmptyListException a) {
					JOptionPane.showMessageDialog(null, a.getMessage());
				}catch (InvalidPositionException a) {
					JOptionPane.showMessageDialog(null, a.getMessage());
				} catch (RangoDeLegajoInvalidoException a) {
					JOptionPane.showMessageDialog(null, a.getMessage());
				}catch (ParametrosInvalidosException a) {
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
			}
		});
		EliminarAlumno.setBounds(10, 135, 245, 25);
		contentPane.add(EliminarAlumno);
		
		JButton MostrarAlumnos = new JButton("Mostrar todos los Alumnos ");
		MostrarAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		MostrarAlumnos.setFont(MostrarAlumnos.getFont().deriveFont(MostrarAlumnos.getFont().getStyle() | Font.BOLD));
		MostrarAlumnos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Consola.setText(p.visualizarAlumnos());
				}catch(EmptyListException a) {
					JOptionPane.showMessageDialog(null, a.getMessage());
				}
					
			}
		});
		MostrarAlumnos.setBounds(10, 171, 245, 25);
		contentPane.add(MostrarAlumnos);
		
		JButton CalcularPromedio = new JButton("Calcular el Promedio");
		CalcularPromedio.setFont(new Font("Tahoma", Font.BOLD, 11));
		CalcularPromedio.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Consola.setText("El promedio total es: "+p.calcularPromedio());
				}catch(EmptyListException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		CalcularPromedio.setBounds(269, 135, 245, 25);
		contentPane.add(CalcularPromedio);
		
		JButton MinimaNota = new JButton("Obtener la Menor Nota");
		MinimaNota.setFont(new Font("Tahoma", Font.BOLD, 11));
		MinimaNota.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Consola.setText("La menor nota conseguida fue: "+p.minimaNota());
				}catch (EmptyListException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				} catch (Exceptions.InvalidKeyException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (EmptyPriorityQueueException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		MinimaNota.setBounds(269, 171, 245, 25);
		contentPane.add(MinimaNota);
		
		JButton MayorAMenor = new JButton("Alumnos de Mayor a Menor Nota");
		MayorAMenor.setFont(new Font("Tahoma", Font.BOLD, 11));
		MayorAMenor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Consola.setText(p.notasAlumnos());
				}catch(EmptyListException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (Exceptions.InvalidKeyException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (EmptyPriorityQueueException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (InvalidPositionException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		MayorAMenor.setBounds(10, 207, 245, 25);
		contentPane.add(MayorAMenor);
		
		JButton MismaNota = new JButton("Alumnos con misma Nota");
		MismaNota.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int notaBuscada = Integer.parseInt(Nota.getText());
					Consola.setText(p.alumnosConMismaNota(notaBuscada));
				}catch(ParametrosInvalidosException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch(NotaInvalidaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Ingrese bien los datos");
				}
					
				
			}
		});
		MismaNota.setFont(new Font("Tahoma", Font.BOLD, 11));
		MismaNota.setBounds(269, 207, 245, 25);
		contentPane.add(MismaNota);
		
		JButton AlumnosAprobados = new JButton("Alumnos Aprobados Y Desaprobados");
		AlumnosAprobados.setFont(new Font("Tahoma", Font.BOLD, 11));
		AlumnosAprobados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Consola.setText(p.alumnosAprobadosYDesaprobados());
			}
		});
		AlumnosAprobados.setBounds(10, 243, 505, 25);
		contentPane.add(AlumnosAprobados);

	}
}