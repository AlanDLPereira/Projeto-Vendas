package Interface;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import Controle.ControleBasico;
import Controle.ControleGeral;
import Negocio.Cliente;
import Negocio.Fornecedor;
import Negocio.Funcionario;
import Negocio.Pessoa;
import Util.Diversos;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;

import java.awt.ComponentOrientation;

import javax.swing.ButtonGroup;

public class JFrmPessoa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane;
	private JTextField jTxtIdentifica;
	private JTextField jTxtNome;
    private JTextArea jTxtARelatorio;
    private JButton jBtnIncluir;
    private JButton jBtnAlterar;
    private JButton jBtnExcluir;
    private final String titulo;
    private JButton jBtnRelatorio;
    private ControleBasico cP;
    private BasicComboBoxRenderer.UIResource uIResourceC;
    private JTextField jTxtEnde;
    private JLabel jLblCpf;
    private JLabel jLblCnpj;
    private JFormattedTextField jFtdTxtCpf;
    private JFormattedTextField jFtdTxtCnpj;
    private JLabel jLblSalario;
    private JLabel jLblCarteiraDeTrabalho;
    private JTextField jTxtCart;
    private JTextField jTxtSalario;
    private JFormattedTextField jFtdTxtTele1;
    private JFormattedTextField jFtdTxtTele2;
    private JTextField jTxtEmail;
    private JPanel panel;
    private JRadioButton jRdbCliente;
    private JRadioButton jRdbFuncionario;
    private JRadioButton jRdbFornecedor;
    private final ButtonGroup buttonGroup = new ButtonGroup();
  

    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	 	EventQueue.invokeLater(new Runnable() {
	  		public void run() {
				try {
					JFrmPessoa frame = new JFrmPessoa();
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
	public JFrmPessoa() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				limpar();
			}
			@Override
			public void windowActivated(WindowEvent arg0) {
				jTxtNome.requestFocusInWindow();
			}
		});
		titulo = "Cadastrar pessoas";
		this.cP = new ControleGeral(1);
		setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		//setTitle("                                                                           Cadastrar funcion\u00E1rios");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 709, 599);
		jContentPane = new JPanel();
		jContentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				centralizarTitulo();
			}
		});
		jContentPane.setBackground(Color.CYAN);
		jContentPane.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jContentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setContentPane(jContentPane);
		jContentPane.setLayout(null);
		
		JPanel jPnlBotoes = new JPanel();
		jPnlBotoes.setBackground(Color.CYAN);
		jPnlBotoes.setBounds(42, 365, 624, 57);
		jPnlBotoes.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jContentPane.add(jPnlBotoes);
		jPnlBotoes.setLayout(null);
		
		jBtnIncluir = new JButton("Incluir");
		jBtnIncluir.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jBtnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarDados('I');			
			}
		});
		jBtnIncluir.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnIncluir.setBounds(12, 17, 84, 25);
		jPnlBotoes.add(jBtnIncluir);
		
		JButton jBtnLimpar = new JButton("Limpar");
		jBtnLimpar.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jBtnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		jBtnLimpar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnLimpar.setBounds(415, 17, 84, 25);
		jPnlBotoes.add(jBtnLimpar);
		
		JButton jBtnRetornar = new JButton("Retornar");
		jBtnRetornar.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jBtnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Diversos.confirmar("Deseja retornar", titulo))
					dispose();
			}
		});
		jBtnRetornar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnRetornar.setBounds(509, 17, 103, 25);
		jPnlBotoes.add(jBtnRetornar);
		
		jBtnAlterar = new JButton("Alterar");
		jBtnAlterar.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jBtnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrarDados('A');
			}
		});
		jBtnAlterar.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnAlterar.setBounds(106, 17, 84, 25);
		jPnlBotoes.add(jBtnAlterar);
		
		jBtnExcluir = new JButton("Excluir");
		jBtnExcluir.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jBtnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Diversos.confirmar("Deseja excluir", titulo))
					if (!verificaChave())
						cadastrarDados('E');
				else
					Diversos.mostrarDados
					("N�o pode remover\n O(A) " + jTxtNome.getText() +
					" pois est� sendo manipulado pela venda" , titulo, false);
			}
		});
		jBtnExcluir.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnExcluir.setBounds(200, 17, 84, 25);
		jPnlBotoes.add(jBtnExcluir);
		
		jBtnRelatorio = new JButton("Relat\u00F3rio");
		jBtnRelatorio.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jBtnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			  relatorioGeral();	
			}
		});
		jBtnRelatorio.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jBtnRelatorio.setBounds(294, 17, 111, 25);
		jPnlBotoes.add(jBtnRelatorio);
		
		JLabel jLblIdentifica = new JLabel("Identifica\u00E7\u00E3o");
		jLblIdentifica.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblIdentifica.setHorizontalAlignment(SwingConstants.CENTER);
		jLblIdentifica.setBounds(10, 28, 107, 15);
		jLblIdentifica.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jContentPane.add(jLblIdentifica);
		
		jTxtIdentifica = new JTextField();
		jTxtIdentifica.setBackground(Color.CYAN);
		jTxtIdentifica.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				  pesquisa();
			}
			
		});
		jTxtIdentifica.setBounds(122, 23, 95, 25);
		jTxtIdentifica.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtIdentifica.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtIdentifica.setHorizontalAlignment(SwingConstants.CENTER);
		jContentPane.add(jTxtIdentifica);
		jTxtIdentifica.setColumns(10);
		
		JLabel jLblNome = new JLabel("Nome");
		jLblNome.setBounds(33, 75, 70, 15);
		jLblNome.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		jContentPane.add(jLblNome);
		
		jTxtNome = new JTextField();
		jTxtNome.setBackground(Color.CYAN);
		jTxtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jTxtNome.setText(jTxtNome.getText().toUpperCase());
			}
		});
		jTxtNome.setBounds(123, 70, 532, 25);
		jTxtNome.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtNome.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtNome.setColumns(10);
		jTxtNome.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jContentPane.add(jTxtNome);
		
		JLabel jLblEmail = new JLabel("Email");
		jLblEmail.setBounds(247, 24, 77, 25);
		jLblEmail.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		jContentPane.add(jLblEmail);
		
		JLabel jlblTele1 = new JLabel("1� Telefone ");
		jlblTele1.setHorizontalAlignment(SwingConstants.CENTER);
		jlblTele1.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jlblTele1.setBounds(20, 162, 95, 25);
		jContentPane.add(jlblTele1);
		
		JScrollPane jScrlPRelatorio = new JScrollPane();
		jScrlPRelatorio.setBackground(Color.CYAN);
		jScrlPRelatorio.setBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0), 
        		"Relat�rio", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13), null));
		jScrlPRelatorio.setBounds(40, 436, 626, 117);
		jContentPane.add(jScrlPRelatorio);
		
		jTxtARelatorio = new JTextArea();
		jTxtARelatorio.setBackground(Color.CYAN);
		jTxtARelatorio.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtARelatorio.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jScrlPRelatorio.setViewportView(jTxtARelatorio);
		uIResourceC = new BasicComboBoxRenderer.UIResource();
		uIResourceC.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel jLblEnde = new JLabel("Endere\u00E7o");
		jLblEnde.setHorizontalAlignment(SwingConstants.RIGHT);
		jLblEnde.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblEnde.setBounds(33, 121, 70, 15);
		jContentPane.add(jLblEnde);
		
		jTxtEnde = new JTextField();
		jTxtEnde.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jTxtEnde.setText(jTxtEnde.getText().toUpperCase());
			}
		});
		jTxtEnde.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtEnde.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtEnde.setColumns(10);
		jTxtEnde.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtEnde.setBackground(Color.CYAN);
		jTxtEnde.setBounds(124, 116, 532, 25);
		jContentPane.add(jTxtEnde);
		
		jFtdTxtTele1 = new JFormattedTextField();
		jFtdTxtTele1.setHorizontalAlignment(SwingConstants.CENTER);
		jFtdTxtTele1.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jFtdTxtTele1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jFtdTxtTele1.setBackground(Color.CYAN);
		jFtdTxtTele1.setBounds(122, 162, 139, 25);
		jContentPane.add(jFtdTxtTele1);
		jFtdTxtTele1.setFormatterFactory(Diversos.FormatoMascara(titulo, 2));
		
		JLabel jLblTele2 = new JLabel("2� Telefone ");
		jLblTele2.setHorizontalAlignment(SwingConstants.CENTER);
		jLblTele2.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblTele2.setBounds(408, 162, 95, 25);
		jContentPane.add(jLblTele2);
		
		jFtdTxtTele2 = new JFormattedTextField();
		jFtdTxtTele2.setHorizontalAlignment(SwingConstants.CENTER);
		jFtdTxtTele2.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jFtdTxtTele2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jFtdTxtTele2.setBackground(Color.CYAN);
		jFtdTxtTele2.setBounds(513, 162, 145, 25);
		jContentPane.add(jFtdTxtTele2);
		jFtdTxtTele2.setFormatterFactory(Diversos.FormatoMascara(titulo, 3));
		
		jTxtEmail = new JTextField();
		jTxtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtEmail.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtEmail.setColumns(10);
		jTxtEmail.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtEmail.setBackground(Color.CYAN);
		jTxtEmail.setBounds(336, 23, 319, 25);
		jContentPane.add(jTxtEmail);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 12));
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Defini��o", TitledBorder.CENTER, TitledBorder.TOP,
						new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13), null));
		panel.setBackground(Color.CYAN);
		panel.setBounds(42, 198, 624, 142);
		jContentPane.add(panel);
		
		jRdbCliente = new JRadioButton("Cliente");
		buttonGroup.add(jRdbCliente);
		jRdbCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleciona('C');
			}
		});
		jRdbCliente.setForeground(Color.BLACK);
		jRdbCliente.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jRdbCliente.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		jRdbCliente.setBackground(Color.CYAN);
		jRdbCliente.setBounds(16, 29, 112, 23);
		panel.add(jRdbCliente);
		
		jRdbFuncionario = new JRadioButton("Funcion\u00E1rio");
		jRdbFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleciona('F');
			}
		});
		buttonGroup.add(jRdbFuncionario);
		jRdbFuncionario.setForeground(Color.BLACK);
		jRdbFuncionario.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jRdbFuncionario.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		jRdbFuncionario.setBackground(Color.CYAN);
		jRdbFuncionario.setBounds(16, 65, 112, 23);
		panel.add(jRdbFuncionario);
		
		jRdbFornecedor = new JRadioButton("Fornecedor");
		buttonGroup.add(jRdbFornecedor);
		jRdbFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleciona('O');
			}
		});
		jRdbFornecedor.setForeground(Color.BLACK);
		jRdbFornecedor.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jRdbFornecedor.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		jRdbFornecedor.setBackground(Color.CYAN);
		jRdbFornecedor.setBounds(14, 100, 114, 23);
		panel.add(jRdbFornecedor);
		
		jLblCpf = new JLabel("Cpf");
		jLblCpf.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCpf.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblCpf.setBounds(175, 28, 63, 25);
		panel.add(jLblCpf);
		
		jLblCnpj = new JLabel("Cnpj");
		jLblCnpj.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCnpj.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblCnpj.setBounds(166, 100, 70, 25);
		panel.add(jLblCnpj);
		
		jFtdTxtCpf = new JFormattedTextField();
		jFtdTxtCpf.setHorizontalAlignment(SwingConstants.CENTER);
		jFtdTxtCpf.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jFtdTxtCpf.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jFtdTxtCpf.setBackground(Color.CYAN);
		jFtdTxtCpf.setBounds(243, 26, 161, 25);
		panel.add(jFtdTxtCpf);
		jFtdTxtCpf.setFormatterFactory(Diversos.FormatoMascara(titulo, 4));
		
		jFtdTxtCnpj = new JFormattedTextField();
		jFtdTxtCnpj.setHorizontalAlignment(SwingConstants.CENTER);
		jFtdTxtCnpj.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jFtdTxtCnpj.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jFtdTxtCnpj.setBackground(Color.CYAN);
		jFtdTxtCnpj.setBounds(244, 101, 161, 25);
		panel.add(jFtdTxtCnpj);
		jFtdTxtCnpj.setFormatterFactory(Diversos.FormatoMascara(titulo, 5));
		
		jLblSalario = new JLabel("Sal\u00E1rio R$");
		jLblSalario.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblSalario.setHorizontalAlignment(SwingConstants.CENTER);
		jLblSalario.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblSalario.setBounds(473, 26, 106, 21);
		panel.add(jLblSalario);
		
		jLblCarteiraDeTrabalho = new JLabel("Carteira de trabalho n\u00B0\r\n");
		jLblCarteiraDeTrabalho.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblCarteiraDeTrabalho.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCarteiraDeTrabalho.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jLblCarteiraDeTrabalho.setBounds(132, 65, 169, 21);
		panel.add(jLblCarteiraDeTrabalho);
		
		jTxtCart = new JTextField();
		jTxtCart.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtCart.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtCart.setColumns(10);
		jTxtCart.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtCart.setBackground(Color.CYAN);
		jTxtCart.setBounds(309, 63, 95, 25);
		panel.add(jTxtCart);
		
		jTxtSalario = new JTextField();
		jTxtSalario.setHorizontalAlignment(SwingConstants.CENTER);
		jTxtSalario.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jTxtSalario.setColumns(10);
		jTxtSalario.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtSalario.setBackground(Color.CYAN);
		jTxtSalario.setBounds(456, 63, 123, 25);
		panel.add(jTxtSalario);
		setResizable(false);
		//setSize(900, 500);
		setLocationRelativeTo(null); //centraliza o formul�rio
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("OptionPane.messageFont", 
                     new FontUIResource(new Font("ARIAL BLACK", Font.BOLD + Font.ITALIC, 13)));
            UIManager.put("OptionPane.messageForeground", Color.BLUE);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JFrmPessoa.class.getName()).log(Level.SEVERE, null, ex);
          } 

	}

	private void limpar() {
		 JTextField txt[] = {jTxtIdentifica, jTxtNome, jTxtEnde, jTxtCart, jTxtEmail, jTxtSalario};
	     for (JTextField t : txt)
	            t.setText("");
	     JFormattedTextField ftd[] = {jFtdTxtCpf, jFtdTxtCnpj, jFtdTxtTele1, jFtdTxtTele2};
	     for (JFormattedTextField f : ftd)
	            f.setText("");
	     jTxtARelatorio.setText("");
	     buttonGroup.clearSelection();
	     jTxtIdentifica.setEditable(true);
	     JButton btn[] = {jBtnAlterar, jBtnExcluir, jBtnIncluir};
	        for (JButton b : btn) 
	            b.setEnabled(false);    
	     jTxtNome.requestFocusInWindow();
	}
	
	private void carregaObjetos(Pessoa p) {
		 switch (p.getDefinicao()) {
                 case 'C' : jRdbCliente.setSelected(true);
                                   Cliente c = (Cliente) p;
                                   jFtdTxtCpf.setText(c.getCpf());
                                    break;
                 case 'F' : jRdbFuncionario.setSelected(true);
                                    Funcionario f = (Funcionario) p;
                                    jFtdTxtCpf.setText(f.getCpf());
                                    jTxtCart.setText(f.getCartTrab());
                                    jTxtSalario.setText(Diversos.doisDigitos(0).format(f.getSalario()));
                                   break; 
                  case 'O' : jRdbFornecedor.setSelected(true);
                                     Fornecedor fo = (Fornecedor) p;
                                     jFtdTxtCnpj.setText(fo.getCnpj());
         } 
		jTxtIdentifica.setText(String.valueOf(p.getIdentifica()));
        jTxtNome.setText(p.getNome());
       jTxtEmail.setText(p.getEmail());
        jTxtEnde.setText(p.getEndereco());
        jFtdTxtTele1.setText(p.getTelefone1());
        jFtdTxtTele2.setText(p.getTelefone2());   
   }
  
  private void seleciona(char opcao) {
	  switch (opcao) {
	      case 'C' : jFtdTxtCpf.setEditable(true);
	                        jTxtSalario.setEditable(false);
	                        jTxtCart.setEditable(false);
	                        jFtdTxtCnpj.setEditable(false);
	                        break;
	      case 'F' : jFtdTxtCpf.setEditable(true);
                            jTxtSalario.setEditable(true);
                           jTxtCart.setEditable(true);
                          jFtdTxtCnpj.setEditable(false);
                          break;
	      case 'O' : jFtdTxtCpf.setEditable(false);
                            jTxtSalario.setEditable(false);
                           jTxtCart.setEditable(false);
                          jFtdTxtCnpj.setEditable(true);                 
	  }
  }
	
  private void cadastrarDados(char opcao) {
         String resp = "";
         if(jTxtIdentifica.getText().isEmpty() || jTxtNome.getText().isEmpty() ||  
        		 jTxtEnde.getText().isEmpty() ||    jTxtEmail.getText().isEmpty())
            resp = "Favor digitar os dados";
         else {
        	      Pessoa p = null;
                  if(jRdbCliente.isSelected()) {
                     Cliente c = new Cliente();
                     c.setCpf(jFtdTxtCpf.getText());
                     p = c;
                 } 
                 else if (jRdbFuncionario.isSelected()) {
                              Funcionario f = new Funcionario();
                              f.setCpf(jFtdTxtCpf.getText());
                              f.setCartTrab(jTxtCart.getText());
                              f.setSalario(Float.parseFloat(Diversos.converterValor(jTxtSalario.getText())));
                             p = f;
                           } 
                          else if (jRdbFornecedor.isSelected()) {
                                       Fornecedor fo = new Fornecedor();
                                       fo.setCnpj(jFtdTxtCnpj.getText());
                                       p = fo;
                                    }
              p.setIdentifica(Integer.parseInt(jTxtIdentifica.getText()));
              p.setDefinicao(jRdbCliente.isSelected() ? 'C' :  jRdbFuncionario.isSelected() ? 'F' : 'O');
              p.setNome(jTxtNome.getText());
              p.setEndereco(jTxtEnde.getText());
              p.setTelefone1(jFtdTxtTele1.getText());
              p.setTelefone2(jFtdTxtTele2.getText());
              p.setEmail(jTxtEmail.getText());              
              if (!cP.setManipular(p, opcao)) { 
                  resp = "Problemas ao " + (opcao == 'A' ? "atualizar" : opcao == 'E' ?  
                   		 "remover" : " inserir") + " os dados do(a) " + p.getNome();
                  jBtnAlterar.setEnabled(false);
                  jBtnExcluir.setEnabled(false);
                  jBtnIncluir.setEnabled(false);
              }   
              else {
                   resp = "A pessoa " +  p.getNome();
                   switch(opcao) {
                          case 'A' :  resp += "\nFoi atualizado(a) ";
                                      break;
                          case 'E' :  resp  +=  "\nFoi removido(a) ";
                                      limpar();
                                      jBtnAlterar.setEnabled(false);
                                      jBtnExcluir.setEnabled(false);
                                      break;
                          case 'I' :  resp +=  "\nFoi inserido(a) ";
                                      jBtnAlterar.setEnabled(true);
                                      jBtnExcluir.setEnabled(true);
                                      jBtnIncluir.setEnabled(false);                  
                   }
                   resp += " com sucesso";
                 }                  
            }
            Diversos.mostrarDados(resp, titulo, (resp.charAt(0) != 'F' && resp.charAt(0) != 'P'));
    }
	 
	private void pesquisa() {
		int identifica;
        Pessoa p;
        if (!Diversos.testaNum(jTxtIdentifica.getText(), titulo))
            jTxtIdentifica.setText(""); // converter texto para numero
        else if (!Diversos.intervalo(Integer.parseInt(jTxtIdentifica.getText()), 0 ,0 , titulo))
                       jTxtIdentifica.setText(""); //testar se � maior que zero
                 else {
                          identifica = Integer.parseInt(jTxtIdentifica.getText());
                         Object o = cP.getBusca(identifica,0);
                         if (o == null) {
                            jBtnIncluir.setEnabled(true);
                          Diversos.mostrarDados("Pessoa " + identifica + " inexistente", titulo, true);
                        }    
                        else {
                                p = (Pessoa) o;
                                carregaObjetos(p); 
                                jBtnAlterar.setEnabled(true);
                               jBtnExcluir.setEnabled(true);
                      } 
                     jTxtIdentifica.setEditable(false);
               }
	}

	private void centralizarTitulo() {
   		 FontMetrics fM = getFontMetrics(getFont());
   		 int sC = (getWidth() / 2 - fM.stringWidth(titulo.trim()) / 2) / 
                    fM.stringWidth(" ");
         setTitle(String.format("%" + (sC + 30 ) + "s", "") + titulo.trim());  
    } 
	
	private void relatorioGeral() {
     //  String resp = cP.relatorio(2) ;
     //   jTxtARelatorio.setText(!resp.isEmpty() ? resp : "Inexist�ncia de dados");
    }
	
	private boolean verificaChave() {
		ControleGeral cG = new ControleGeral(0); 
	    String parametro[] = {jTxtIdentifica.getText(), "identifica", "Venda"};
		 return (cG.rotinas(1, parametro));
	  }
}
