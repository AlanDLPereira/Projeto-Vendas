package Interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class JFrmAntes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
		//	public void run() {
				try {
					JFrmAntes frame = new JFrmAntes();
					frame.setVisible(true);
					frame.chama(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
		//	}
		//});
	}

	/**
	 * Create the frame.
	 */
	public JFrmAntes() {
		setBackground(Color.CYAN);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setLocationRelativeTo(null);		
	    setExtendedState(MAXIMIZED_BOTH);
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
   public void chama(JFrmAntes a) {
	   a.dispose();
	   JFrmVenda v = new JFrmVenda();
       v.setVisible(true);   
       v.setExtendedState(MAXIMIZED_BOTH);
       v.coordenadas(getHeight() , getWidth() - 18);
      // v.temporizador();
       v.relogio();
   }
}
