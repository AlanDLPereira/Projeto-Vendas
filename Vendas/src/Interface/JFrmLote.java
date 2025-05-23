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

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import Controle.ControleBasico;
import Controle.ControleGeral;
import Negocio.Lote;
import Negocio.Produto;
import Util.Diversos;

import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class JFrmLote extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane;
	private JTextField jTxtNumero;
	private JLabel jLblCod;
	private JLabel jLblCodigo;
    private JButton jBtnIncluir;
    private JButton jBtnAlterar;
    private JButton jBtnExcluir;
    private final String titulo;
    private JTextArea jTxtARelatorio;
    private JButton jBtnRelatorio;
    private JScrollPane jScrlPRelatorio;
    private ControleBasico cL, cP;
    private List<Object> listaP;
    private JComboBox<String> jCmbProduto;
	private BasicComboBoxRenderer.UIResource uIResource;
	private JLabel lblDataDeValidade;
	private JLabel jDateCalendario;
	
    
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	 	EventQueue.invokeLater(new Runnable() {
	  		public void run() {
				try {
					JFrmLote frame = new JFrmLote();
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
	@SuppressWarnings("unchecked")
	public JFrmLote() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				carregaLista();
		        limpar();
			}
			@Override
			public void windowActivated(WindowEvent arg0) {
				jCmbProduto.requestFocusInWindow();
			}
		});
		titulo = "Cadastrar lotes";
		
		this.cP =  new ControleGeral(2);
		this.cL = new ControleGeral(3);
		setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		centralizarTitulo();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 757, 367);
		jContentPane = new JPanel();
		jContentPane.setForeground(new Color(30, 144, 255));
		jContentPane.setBackground(Color.CYAN);
		jContentPane.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 13));
		jContentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setContentPane(jContentPane);
		jContentPane.setLayout(null);
		
		JPanel jPnlBotoes = new JPanel();
		jPnlBotoes.setBackground(Color.CYAN);
		jPnlBotoes.setBounds(35, 130, 694, 59);
		jPnlBotoes.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jContentPane.add(jPnlBotoes);
		jPnlBotoes.setLayout(null);
		
		jBtnIncluir = new JButton("Incluir");
		jBtnIncluir.setBackground(Color.CYAN);
		jBtnIncluir.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jBtnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarDados('I');			
			}
		});
		jBtnIncluir.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		jBtnIncluir.setBounds(12, 17, 84, 25);
		jPnlBotoes.add(jBtnIncluir);
		
		JButton jBtnLimpar = new JButton("Limpar");
		jBtnLimpar.setBackground(Color.CYAN);
		jBtnLimpar.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jBtnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		jBtnLimpar.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		jBtnLimpar.setBounds(455, 17, 84, 25);
		jPnlBotoes.add(jBtnLimpar);
		
		JButton jBtnRetornar = new JButton("Retornar");
		jBtnRetornar.setBackground(Color.CYAN);
		jBtnRetornar.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jBtnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Diversos.confirmar("Deseja retornar", titulo))
					dispose();
			}
		});
		jBtnRetornar.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		jBtnRetornar.setBounds(557, 17, 119, 25);
		jPnlBotoes.add(jBtnRetornar);
		
		jBtnAlterar = new JButton("Alterar");
		jBtnAlterar.setBackground(Color.CYAN);
		jBtnAlterar.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jBtnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrarDados('A');
			}
		});
		jBtnAlterar.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		jBtnAlterar.setBounds(116, 17, 84, 25);
		jPnlBotoes.add(jBtnAlterar);
		
		jBtnExcluir = new JButton("Excluir");
		jBtnExcluir.setBackground(Color.CYAN);
		jBtnExcluir.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jBtnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Diversos.confirmar("Deseja excluir", titulo))
					if (!verificaChave())
						 cadastrarDados('E');
				else
					Diversos.mostrarDados
					("N�o pode remover o animal da esp�cie  " + jCmbProduto.getSelectedItem() +
							"\nPois est� sendo atendido por um funcion�rio" , titulo, false);
			}
		});
		jBtnExcluir.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		jBtnExcluir.setBounds(215, 17, 84, 25);
		jPnlBotoes.add(jBtnExcluir);
		
		jBtnRelatorio = new JButton("Relat�rio");
		jBtnRelatorio.setBackground(Color.CYAN);
		jBtnRelatorio.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jBtnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				relatorioGeral();
			}
		});
		jBtnRelatorio.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		jBtnRelatorio.setBounds(322, 17, 111, 25);
		jPnlBotoes.add(jBtnRelatorio);
		
		JLabel jLblNumero = new JLabel("N\u00FAmero");
		jLblNumero.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		jLblNumero.setBounds(51, 28, 90, 15);
		jLblNumero.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		jContentPane.add(jLblNumero);
		
		jTxtNumero = new JTextField();
		jTxtNumero.setBackground(Color.CYAN);
		jTxtNumero.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				  pesquisa();
			}
			
		});
		jTxtNumero.setBounds(154, 23, 81, 25);
		jTxtNumero.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		jTxtNumero.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		jTxtNumero.setHorizontalAlignment(SwingConstants.CENTER);
		jContentPane.add(jTxtNumero);
		jTxtNumero.setColumns(10);
		
		JLabel jLblProduto = new JLabel("Produto");
		jLblProduto.setBounds(53, 76, 75, 15);
		jLblProduto.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		jLblProduto.setHorizontalAlignment(SwingConstants.RIGHT);
		jContentPane.add(jLblProduto);
		
		jScrlPRelatorio = new JScrollPane();
		jScrlPRelatorio.setBackground(Color.CYAN);
		jScrlPRelatorio.setBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0), 
        		"Relat�rio", TitledBorder.CENTER, TitledBorder.TOP,
				new Font("Arial Black", Font.BOLD | Font.ITALIC, 13), null));
		jScrlPRelatorio.setBounds(42, 210, 687, 97);
		jContentPane.add(jScrlPRelatorio);
		
		jTxtARelatorio = new JTextArea();
		jTxtARelatorio.setBackground(Color.CYAN);
		jScrlPRelatorio.setViewportView(jTxtARelatorio);
		jTxtARelatorio.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		jTxtARelatorio.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		
		jCmbProduto = new JComboBox<String>();
		jCmbProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionaChave();
			}			 
		});
		jCmbProduto.setBackground(Color.CYAN);
		jCmbProduto.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		jCmbProduto.setBorder(new LineBorder(Color.BLACK, 2));
		jCmbProduto.setBounds(154, 68, 240, 28);
		uIResource = new BasicComboBoxRenderer.UIResource();
	    uIResource.setHorizontalAlignment(SwingConstants.CENTER);
	    jCmbProduto.setRenderer(uIResource);
		jContentPane.add(jCmbProduto);
		
		lblDataDeValidade = new JLabel("Data de validade");
		lblDataDeValidade.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDataDeValidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataDeValidade.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		lblDataDeValidade.setBounds(341, 28, 146, 17);
		jContentPane.add(lblDataDeValidade);
		
		jLblCod = new JLabel("C\u00F3digo");
		jLblCod.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblCod.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCod.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		jLblCod.setBounds(407, 76, 90, 15);
		jContentPane.add(jLblCod);
		
		jLblCodigo = new JLabel("");
		jLblCodigo.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jLblCodigo.setHorizontalTextPosition(SwingConstants.CENTER);
		jLblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		jLblCodigo.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		jLblCodigo.setBounds(507, 68, 90, 28);
		jContentPane.add(jLblCodigo);
		
		jDateCalendario = new JLabel("");
		jDateCalendario.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		jDateCalendario.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		jDateCalendario.setBounds(497, 23, 107, 25);
		jContentPane.add(jDateCalendario);
		
		 setResizable(false);
		 setLocationRelativeTo(null); //centraliza o formul�rio
		 try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
             UIManager.put("OptionPane.messageFont", 
                     new FontUIResource(new Font("ARIAL BLACK", Font.BOLD + Font.ITALIC, 13)));
             UIManager.put("OptionPane.messageForeground", Color.BLUE);
         } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JFrmLote.class.getName()).log(Level.SEVERE, null, ex);
          } 
	}
	
	private void limpar() {
		  jTxtNumero.setText("");
	     jCmbProduto.setSelectedIndex(-1);
	     jTxtARelatorio.setText("");
	     jTxtNumero.setEditable(true);
	     JButton jBtn[] = {jBtnAlterar, jBtnExcluir, jBtnIncluir};
	        for (JButton btn : jBtn) 
	            btn.setEnabled(false);  
	      //jDateCalendario.setDate(null);
	     jCmbProduto.requestFocusInWindow();
	}
	
	private void carregaLista() {
		 int posi = jCmbProduto.getSelectedIndex();
         
         listaP= cP.lista();  
         jCmbProduto.removeAllItems();
         for (Object o : listaP) {
              Produto p = (Produto) o;
              jCmbProduto.addItem(p.getDescricao());
         } 
         if (posi > -1) 
            jCmbProduto.setSelectedIndex(posi);
         else {
            jLblCodigo.setText("");
            jCmbProduto.setSelectedIndex(-1);
         }	           
     }
       
	private void selecionaChave() {
		 int ind = jCmbProduto.getSelectedIndex();
         if (ind > -1) {
            Object o = listaP.get(ind);
            Produto p = (Produto) o;
            jLblCodigo.setText(String.valueOf(p.getCodigo()));
         }                      
	}     
	
	private void carregaObjetos(Lote l) {
		jTxtNumero.setText(String.valueOf(l.getNumLote()));
        jCmbProduto.setSelectedItem(l.getProduto().getDescricao());
        jLblCodigo.setText(String.valueOf(l.getProduto().getCodigo()));
       // SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
       /* try {
        	jDateCalendario.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(l.getDataVal()));
			jDateCalendario.setDate(formato.parse(l.getDataVal()));
			jLblData.setText(l.getDataVal());
		} catch (ParseException e) {*/
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
      
	}
	
	private void centralizarTitulo() {
		    FontMetrics fM = getFontMetrics(getFont());
	        int sC = (getWidth() / 2 - fM.stringWidth(titulo.trim()) / 2) / fM.stringWidth(" ");
	        setTitle(String.format("%" + (sC + 90 ) + "s", "") + titulo.trim());
	}

	private void cadastrarDados(char opcao) {
         String resp = "";
         if(jTxtNumero.getText().isEmpty() || jCmbProduto.getSelectedIndex() == -1)
        	 resp = "Favor digitar os dados";
         else {
                 Lote l = new Lote();
                 l.setNumLote(Integer.parseInt(jTxtNumero.getText()));
                 Produto p = (Produto) cP.getBusca(Integer.parseInt(jLblCodigo.getText()), 0); 
                 l.setProduto(p);
              //S   SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
               //  String data = formato.format(jDateCalendario.getDate());
                 
               //  l.setDataVal(data);
                 jDateCalendario.setText(l.getDataVal());
                 if (!cL.setManipular(l, opcao)) {
                    resp = "Problemas ao " + 
                    (opcao == 'A' ? "atualizar" : opcao == 'E' ?  "remover" : " inserir") +
                    " os dados do lote " + l.getNumLote();
                     jBtnAlterar.setEnabled(false);
                     jBtnExcluir.setEnabled(false);
                    jBtnIncluir.setEnabled(false);
                 }   
                 else {
            	      resp = "O lote n� " +  l.getNumLote() + "\nDo produto " + 
                       (String)jCmbProduto.getSelectedItem();
                       switch(opcao) {
                            case 'A' :  resp += "\nFoi atualizado ";
                                             break;
                            case 'E' :  resp  +=  "\nFoi removido ";
                                             limpar();
                                             jBtnAlterar.setEnabled(false);
                                             jBtnExcluir.setEnabled(false);
                                             break;
                            case 'I' :  resp +=  "\nFoi inserido ";
                                            jBtnAlterar.setEnabled(true);
                                            jBtnExcluir.setEnabled(true);
                                           jBtnIncluir.setEnabled(false);                  
                        }
                      resp += " com sucesso";              
                 }                  
            }
            Diversos.mostrarDados(resp, titulo, (resp.charAt(0) != 'F' && 
            		resp.charAt(0) != 'P'));
    }
	 
	private void pesquisa() {
		int numero;
        Lote l;
        if (!Diversos.testaNum(jTxtNumero.getText(), titulo))
            jTxtNumero.setText(""); // converter texto para numero
        else if (!Diversos.intervalo(Integer.parseInt(jTxtNumero.getText()), 0 ,0 , titulo))
                 jTxtNumero.setText(""); //testar se � maior que zero
             else {
                     numero = Integer.parseInt(jTxtNumero.getText());
                     Object o = cL.getBusca(numero, 0);
                   if (o == null) {
                      jBtnIncluir.setEnabled(true);
                      Diversos.mostrarDados("Lote de n� " + numero+ " inexistente", titulo, true);
                   }    
                   else {
                           l = (Lote) o;
                           carregaObjetos(l); 
                           jBtnAlterar.setEnabled(true);
                           jBtnExcluir.setEnabled(true);
                  } 
                  jTxtNumero.setEditable(false);
            }
	}
	
	private void relatorioGeral() {
		String resp = ""; // cL.relatorio(3);
	    jTxtARelatorio.setText(!resp.isEmpty() ? resp : " Inexist�ncia de dados");
    }
	
	private boolean verificaChave() {
		ControleGeral cG = new ControleGeral(0); 
	  	String parametro[] = {jTxtNumero.getText(), "numero", "Lote"};
		return (cG.rotinas(1, parametro));		 
	}
}
