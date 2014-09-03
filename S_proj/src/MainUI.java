

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;



import detector.det;
import transation.Ttran;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.ScrollPaneConstants;

public class MainUI {

	
	private JFrame frmOn;
	private static JTextArea textArea;
	

	/**
	 * Launch the application.
	 */
	
	//public void transfer
	
	public static void main(String[] args) 
	{

		OutputStream textAreaStream = new OutputStream() {
			public void write(int b) throws IOException {
				
				textArea.append(String.valueOf((char) b));
				textArea.setCaretPosition(textArea.getText().length());
			}

			public void write(byte b[]) throws IOException {
				
				textArea.append(new String(b));
				textArea.setCaretPosition(textArea.getText().length());
			}

			public void write(byte b[], int off, int len) throws IOException {

				textArea.append(new String(b, off, len));
				textArea.setCaretPosition(textArea.getText().length());
			}
		};
		
		PrintStream ps = new PrintStream(textAreaStream);
	    System.setOut(ps);
		System.setErr(ps);
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frmOn.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOn = new JFrame();
		frmOn.setTitle("O2N");
		frmOn.setBounds(100, 100, 535, 462);
		frmOn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOn.getContentPane().setLayout(null);
		frmOn.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Select function here:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setBounds(51, 10, 150, 15);
		frmOn.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.setBounds(90, 39, 93, 23);
		frmOn.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Transfer");
		
		btnNewButton_1.setBounds(214, 39, 93, 23);
		frmOn.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Detect");
		
		btnNewButton_2.setBounds(340, 39, 93, 23);
		frmOn.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 72, 499, 342);
		frmOn.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		final LinkedBlockingQueue<Character> sb = new LinkedBlockingQueue<Character>();
		
		System.setIn(new BufferedInputStream(new InputStream() {
			
			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				int c = -1;
			    try {
			      c = sb.take();            
			    } catch(InterruptedException ie) {
			    } 
			    return c;
			}
		}));
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//textArea.setText("xxxxxxxxxxxxxxxxxx");
				dataCreator DC= new dataCreator();
				DC.creator();
				//textArea.setText(null);
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Ttran trans= new Ttran();
				trans.run(textArea);
				
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				det.main(null);
				
			}
		});
		
		
	}
}
