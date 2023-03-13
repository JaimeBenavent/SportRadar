package classes;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class App extends JFrame{
	
	private static final long serialVersionUID = 1L;
	    
	private JLabel lblImage = new JLabel();
	 
	public App() {	          
	     
	    // Agregamos un tículo
		this.setTitle("Football World Cup");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		// Agregamos una imagen de fondo
		ImageIcon image = new ImageIcon(getClass().getResource("Sportradar.png"));
		lblImage.setIcon(image);
		lblImage.setSize(image.getIconWidth(), image.getIconHeight());
		lblImage.setOpaque(true);
		JLayeredPane layered = new JLayeredPane();
		layered.add(lblImage);	 
		
		// Le damos un tamaño a la ventana
		this.setSize(image.getIconWidth(), image.getIconHeight());	   
		
		  
		// Lo centramos en pantalla
		this.setLocationRelativeTo(null);
		  
		// Le decimos que no se puede redimensionar
		this.setResizable(false);
		  
		// Agregamos una imagen de fondo a la ventana
		contentPane.add(layered);
		
		setContentPane(contentPane);
	    	  
	}	     
}
