package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidad.Cliente;
import model.ClienteModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
import reporte.GeneradorReporte;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmReporteClienteConsulta extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDNI;
	private JButton btnFiltrar;
	private JPanel panelReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporteClienteConsulta frame = new FrmReporteClienteConsulta();
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
	public FrmReporteClienteConsulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 574);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("info"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsultaDeCliente = new JLabel("Consulta de Cliente por DNI");
		lblConsultaDeCliente.setForeground(Color.BLUE);
		lblConsultaDeCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConsultaDeCliente.setBounds(259, 11, 297, 42);
		contentPane.add(lblConsultaDeCliente);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDni.setForeground(Color.BLUE);
		lblDni.setBounds(46, 63, 46, 14);
		contentPane.add(lblDni);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBackground(new Color(0, 255, 255));
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(503, 59, 162, 23);
		contentPane.add(btnFiltrar);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(102, 60, 208, 20);
		contentPane.add(txtDNI);
		txtDNI.setColumns(10);
		
		panelReporte = new JPanel();
		panelReporte.setBounds(10, 92, 718, 432);
		contentPane.add(panelReporte);
		panelReporte.setLayout(new BorderLayout(0, 0));
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnFiltrar) {
			handle_btnFiltrar_actionPerformed(arg0);
		}
	}
	protected void handle_btnFiltrar_actionPerformed(ActionEvent arg0) {
		String dni = txtDNI.getText().trim();
		
		ClienteModel model = new ClienteModel();
		List<Cliente> lstData = null; 
		if(dni.equals("")) {
			lstData = model.listaCliente();
		} else {
			lstData = model.consultaCliente(dni);
		}
		
		// 1 La data
				JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstData);

		// 2 El diseño del reporte (Diseño)
		String file = "reporteCliente.jasper";
				
		// 3 Se genera el reporte
		JasperPrint jasperPrint = GeneradorReporte.genera(file, dataSource, null);

		// 4 Se muestra en el visor
		JRViewer jRViewer = new JRViewer(jasperPrint);
				
		// 5 Se añade el visor al panel
		panelReporte.removeAll();
		panelReporte.add(jRViewer);
		panelReporte.repaint();
		panelReporte.revalidate();
				
	}
}