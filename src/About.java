import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JFrame;

public class About extends JFrame implements ActionListener{
	
		JButton button1;
		JButton button2;
	
	 	About() {
	 		
	 		button1 = new JButton("Licença"); 
	 		button1.setBounds(50,150,100,60);
	 		button1.setFocusable(false);
	 		button1.addActionListener(this);
	 		button2 = new JButton("Autor"); 
	 		button2.setBounds(150,150,100,60);
	 		button2.setFocusable(false);
	 		button2.addActionListener(this);
	 		
	 		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 		this.setTitle("Sobre");
	 		this.setSize(300,300);
	 		this.setResizable(false);
	 		this.setLayout(null);
	 		this.setLocationRelativeTo(null);
	 		this.add(button1);
	 		this.add(button2);
	}
	 	
	 	@Override
	 	public void actionPerformed(ActionEvent e) {
	 		
	 		if(e.getSource() == button1) {
	 			Desktop browser = Desktop.getDesktop();
	 			
	 			try {
					browser.browse(new URI("https://www.gnu.org/licenses/gpl-3.0.pt-br.html"));
				} catch (Exception e2) {
					
				}
	 		}
	 	
	 		if(e.getSource() == button2) {
	 			Desktop browser = Desktop.getDesktop();
	 			
	 			try {
					browser.browse(new URI("https://www.instagram.com/josefmonteiro/"));
				} catch (Exception e2) {
				
				}
	 		}
	 		
	 	}

}
