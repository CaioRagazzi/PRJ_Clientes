import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.LineNumberInputStream;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Color;

public class frmClientes extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmClientes frame = new frmClientes();
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

	private ConexaoBDA conn;
	DefaultListModel<String> dlist = new DefaultListModel<>();
	private JTextField txtData;
	private JTextField txtSexo;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtbusca;

	public frmClientes() {

		initComponents();
		carregarDados();

	}

	private void carregarDados() {

		dlist.clear();

		conn = new ConexaoBDA();

		List<String> aux = conn.getLista();

		for (int i = 0; i < aux.size(); i++) {
			dlist.add(i, aux.get(i));

		}

	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 967, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(24, 29, 70, 15);
		contentPane.add(lblCodigo);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(24, 77, 70, 15);
		contentPane.add(lblNome);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(105, 27, 114, 19);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBounds(105, 75, 114, 19);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JButton btnPrimeiro = new JButton("<<");
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux[] = conn.getPrimeiro();
				setDados(aux[0], aux[1], aux[2], aux[3], aux[4], aux[5]);
			}
		});
		btnPrimeiro.setBounds(24, 311, 58, 25);
		contentPane.add(btnPrimeiro);

		JButton btnAnterior = new JButton("<");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux[] = conn.getAnterior();
				setDados(aux[0], aux[1], aux[2], aux[3], aux[4], aux[5]);
			}
		});
		btnAnterior.setBounds(83, 311, 58, 25);
		contentPane.add(btnAnterior);

		JButton btnUltimo = new JButton(">>");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String aux[] = conn.getUltimo();
				setDados(aux[0], aux[1], aux[2], aux[3], aux[4], aux[5]);
			}
		});
		btnUltimo.setBounds(219, 311, 58, 25);
		contentPane.add(btnUltimo);

		JButton btnProximo = new JButton(">");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aux[] = conn.getProximo();
				System.out.println(aux[0] + aux[1] + aux[2] + aux[3] + aux[4] + aux[5]);
				setDados(aux[0], aux[1], aux[2], aux[3], aux[4], aux[5]);
			}
		});
		btnProximo.setBounds(153, 311, 58, 25);
		contentPane.add(btnProximo);

		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				insere();
				carregarDados();
			}
		});
		btnInserir.setBounds(54, 361, 87, 25);
		contentPane.add(btnInserir);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				editar();
				carregarDados();
			}

		});
		btnEditar.setBounds(153, 361, 87, 25);
		contentPane.add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				excluir();
				carregarDados();
			}
		});
		btnExcluir.setBounds(54, 396, 87, 25);
		contentPane.add(btnExcluir);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setDados("", "", "", "", "", "");
			}

		});

		btnLimpar.setBounds(153, 398, 87, 25);
		contentPane.add(btnLimpar);

		JList list = new JList();
		list.setValueIsAdjusting(true);
		list.setBounds(242, 28, 698, 271);
		contentPane.add(list);
		list.setModel(dlist);

		JLabel lblData = new JLabel("Data Nasc");
		lblData.setBounds(12, 120, 82, 15);
		contentPane.add(lblData);

		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(24, 164, 70, 15);
		contentPane.add(lblSexo);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(24, 206, 70, 15);
		contentPane.add(lblEmail);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(24, 239, 70, 15);
		contentPane.add(lblTelefone);

		txtData = new JTextField();
		txtData.setColumns(10);
		txtData.setBounds(105, 118, 114, 19);
		contentPane.add(txtData);

		txtSexo = new JTextField();
		txtSexo.setColumns(10);
		txtSexo.setBounds(105, 162, 114, 19);
		contentPane.add(txtSexo);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(105, 202, 114, 19);
		contentPane.add(txtEmail);

		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(105, 237, 114, 19);
		contentPane.add(txtTelefone);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				buscaEspecifico();

			}
		});
		btnBuscar.setBounds(257, 377, 117, 25);
		contentPane.add(btnBuscar);
		
		txtbusca = new JTextField();
		txtbusca.setBounds(386, 377, 87, 25);
		contentPane.add(txtbusca);
		txtbusca.setColumns(10);

	}

	private void setDados(String str1, String str2, String str3, String str4, String str5, String str6) {
		this.txtCodigo.setText(str1);
		this.txtNome.setText(str2);
		this.txtData.setText(str3);
		this.txtSexo.setText(str4);
		this.txtEmail.setText(str5);
		this.txtTelefone.setText(str6);

	}

	private void insere() {
		String aux[] = new String[6];
		aux[1] = this.txtNome.getText();
		aux[2] = this.txtData.getText();
		aux[3] = this.txtSexo.getText();
		aux[4] = this.txtEmail.getText();
		aux[5] = this.txtTelefone.getText();
		System.out.println(aux);
		conn.add(aux);
	}

	private void editar() {
		String aux[] = new String[6];
		aux[0] = this.txtCodigo.getText();
		aux[1] = this.txtNome.getText();
		aux[2] = this.txtData.getText();
		aux[3] = this.txtSexo.getText();
		aux[4] = this.txtEmail.getText();
		aux[5] = this.txtTelefone.getText();
		conn.update(aux);
	}

	private void excluir() {
		String aux[] = new String[2];
		aux[0] = this.txtCodigo.getText();
		conn.remove(aux);
	}

	private void buscaEspecifico() {
		String filtro = this.txtbusca.getText();

		List<String> lista = conn.getEspecifico(filtro);
		String aux[] = new String[6];

		for (int i = 0; i < lista.size(); i++) {
			
			aux[i] = lista.get(i);
			System.out.println(aux[i] + i);

		}
		
		this.txtCodigo.setText(aux[0]);
		this.txtNome.setText(aux[1]);
		this.txtData.setText(aux[2]);
		this.txtSexo.setText(aux[3]);
		this.txtEmail.setText(aux[4]);
		this.txtTelefone.setText(aux[5]);		

	}
}
