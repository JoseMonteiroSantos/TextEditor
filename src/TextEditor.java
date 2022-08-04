import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextEditor extends JFrame implements ActionListener {

	JTextArea textArea;
	JScrollPane scrollPane;
	JSpinner fontSizeSpinner;
	JLabel fontLabel;
	JButton fontColorButton;
	JComboBox fontBox;
	
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem openItem;
	JMenuItem saveItem;
	JMenuItem exitItem;
	JMenu helpMenu;
	JMenuItem docItem;
	JMenuItem aboutItem;
	
	TextEditor(){
		
		//-----Frame
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Editor de Texto 100% Atualizado E Funcional SEM VIRUS ATUALIZADO");
		this.setSize(700,700);
		this.setResizable(true);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		
		textArea = new JTextArea(); // Criando a área digitável
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Arial",Font.PLAIN,16));
		
		scrollPane = new JScrollPane(textArea); 
		scrollPane.setPreferredSize(new Dimension(550,550));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		fontLabel = new JLabel("Fonte: ");
		
		fontSizeSpinner = new JSpinner();
		fontSizeSpinner.setPreferredSize(new Dimension(50,25));
		fontSizeSpinner.setValue(16);
		fontSizeSpinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,(int) fontSizeSpinner.getValue()));
				
			}
		});
		
		fontColorButton = new JButton("Cor");
		fontColorButton.addActionListener(this);
		
		String[] fontes = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		fontBox = new JComboBox(fontes);
		fontBox.addActionListener(this);		
		fontBox.setSelectedItem("Arial");
		
		//-----Barra de Menu-----
		
		menuBar = new JMenuBar();
		fileMenu = new JMenu("Arquivo");
		openItem = new JMenuItem("Abrir Arquivo");
		saveItem = new JMenuItem("Salvar Arquivo");
		exitItem = new JMenuItem("Fechar Editor");
		
		helpMenu = new JMenu("Ajuda");
		docItem = new JMenuItem("Documentação");
		aboutItem = new JMenuItem("Sobre");
		
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		exitItem.addActionListener(this);
		docItem.addActionListener(this);
		aboutItem.addActionListener(this);
		
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
		helpMenu.add(docItem);
		helpMenu.add(aboutItem);
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		
		//-----/Barra de Menu-----
		
		this.setJMenuBar(menuBar);
		this.add(fontLabel);
		this.add(fontSizeSpinner);
		this.add(fontColorButton);
		this.add(fontBox);
		this.add(scrollPane);
		this.setVisible(true);
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fontColorButton) {
			JColorChooser colorChooser = new JColorChooser();
			
			Color color = colorChooser.showDialog(null, "Escolha uma cor", Color.black);
			
			textArea.setForeground(color);
			
		}
		
		if (e.getSource() == fontBox) {
			textArea.setFont(new Font((String)fontBox.getSelectedItem(),Font.PLAIN,textArea.getFont().getSize()));
		}
		
		if (e.getSource() == openItem) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			
			int response = fileChooser.showOpenDialog(null);
			
			if (response == fileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				Scanner fileIn = null;
				
				try {
					fileIn = new Scanner(file);
					if (file.isFile()) {
						
					while(fileIn.hasNextLine()) {
						String line = fileIn.nextLine()+"\n";
						textArea.append(line);
					}
				   }
				} catch (FileNotFoundException e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
				finally {
					fileIn.close();
				}
			}
		}
	
		if (e.getSource() == saveItem) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de texto", ".txt");
			fileChooser.setFileFilter(filter);
			
			
			int response = fileChooser.showSaveDialog(null);
			
			 if (response == JFileChooser.APPROVE_OPTION) {
				File file;
				PrintWriter fileOut = null;
				
				file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				try {
					fileOut = new PrintWriter(file);
					fileOut.println(textArea.getText());
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				finally {
					fileOut.close();
				}
			}
		}
		
		if (e.getSource() == exitItem) {
			System.exit(0);
		}
		
		if (e.getSource() == docItem) {
			JOptionPane.showMessageDialog(null, "Editor de Texto 100% Atualizado "
					+ "E Funcional SEM VIRUS ATUALIZADO possui DIVERSAS funcionalidades como abrir e salvar "
					+ "arquivos, mudar a fonte do texto e a cor da fonte, além de "
					+ "ser 100% atualizado e funcional e SEM VÍRUS", "SOBRE", JOptionPane.PLAIN_MESSAGE);
		}
		
		if (e.getSource() == aboutItem) {
			new About().setVisible(true);
		}
		
	}

}
