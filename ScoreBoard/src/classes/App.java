package classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import interfaces.Team;

public class App extends JFrame{
	
	private javax.swing.JPanel jContentPane = null;
	private javax.swing.JMenuBar jJMenuBar = null;
	private javax.swing.JMenu fileMenu = null;
	private javax.swing.JMenu helpMenu = null;
	private javax.swing.JMenu windowMenu = null;
	private javax.swing.JMenuItem exitMenuItem = null;
	private javax.swing.JMenuItem aboutMenuItem = null;
	private javax.swing.JMenuItem preferenciasMenuItem = null;
	private JPanel jPanel = null;
	private JButton jbCerrar = null;
	private JScrollPane jspElementos		= null;
	private JPanel jpElementos 				= null;
	private JLabel jlCabeceraElementos		= null;
	private ArrayList<Team> elementos	= null;
	
	public App() {
		
		iniciar();
		//initialize();
	}
	
	public void initialize() {
		
		JFrame f=new JFrame();
		f.setBounds(100,500,300,200);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
    public App( String titulo ) 
    {
        setTitle(titulo);
        inicializarComponentes();
        mostrarVentana();
    }   

    public void inicializarComponentes() {
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        JButton btnUno = new JButton( "Uno" );
        JButton btnDos = new JButton( "Dos" );
        setLayout( new FlowLayout() );
        add( btnUno );
        add( btnDos );
    }   
    
    public void mostrarVentana() 
    {
        setSize( 300, 200 );
        setVisible( true );
    } 
    
    public void iniciar() {
    
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setSize(100, 400);
		
		this.setContentPane(getJContentPane());
		this.setJMenuBar(getJJMenuBar());
		this.setTitle("Inicio");
		
		elementos = new ArrayList<Team>();
		
		this.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent e) {
				jlCabeceraElementos.setSize(getWidth() - 25, 40);
				jlCabeceraElementos.setLocation(5, 0);
				
				jspElementos.setSize(getWidth() - 25, getHeight() - jspElementos.getY() - 70 - jbCerrar.getHeight());
				jspElementos.setLocation(5, jlCabeceraElementos.getY() + jlCabeceraElementos.getHeight());
				
				jbCerrar.setLocation(getWidth()- jbCerrar.getWidth() - 22, getHeight() - jbCerrar.getWidth() - 65);
				
				jContentPane.revalidate();
				jContentPane.repaint();
			}
		});
		
    }
	
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJPanel(), BorderLayout.CENTER);
		}

		return jContentPane;
	}
	
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			
			jPanel.setLayout(null);
			jPanel.add(getJspElementos(), null);
			jPanel.add(getJbCerrar(), null);
		}
		return jPanel;
	}
	
	private JScrollPane getJspElementos() {
		try {
			if(jspElementos == null) {
				jspElementos = new JScrollPane();
				
				jlCabeceraElementos = new JLabel();
				jlCabeceraElementos.setText("CAbecera");
								
				jPanel.add(jlCabeceraElementos, null);
				
				jpElementos = new javax.swing.JPanel();
				jpElementos.setLayout(new BoxLayout(jpElementos, BoxLayout.Y_AXIS));
				jpElementos.setBorder(
						BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2), jpElementos.getBorder()));
				
				jspElementos.setViewportView(jpElementos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jspElementos;
	}
	
	private javax.swing.JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new javax.swing.JMenuBar();
			jJMenuBar.add(getFileMenu());
			jJMenuBar.add(getWindowMenu());
		}
		return jJMenuBar;
	}
	
	private javax.swing.JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new javax.swing.JMenu();
			fileMenu.setText("Menu");
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}
	
	private javax.swing.JMenu getWindowMenu() {
		if (windowMenu == null) {
			windowMenu = new javax.swing.JMenu();
			windowMenu.setText("WinMenu");
		}
		return windowMenu;
	}
	
	private javax.swing.JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new javax.swing.JMenuItem();
			exitMenuItem.setText("exitMenu");
			exitMenuItem.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitMenuItem;
	}
	
	private JButton getJbCerrar() {
		if (jbCerrar == null) {
			jbCerrar = new JButton();
			jbCerrar.setSize(32, 32);		
			jbCerrar.setToolTipText("jbCerrar.toolTipTex");
			jbCerrar.setMargin(new Insets(0, 0, 0, 0));	
			jbCerrar.setMnemonic(java.awt.event.KeyEvent.VK_C);
			jbCerrar.setBorder(BorderFactory.createLineBorder(Color.black));
			
			jbCerrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					
					System.exit(0);
				}
			});
		}
		return jbCerrar;
	}

}
