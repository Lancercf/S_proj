package detector;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class det {

	private JFrame frame;
	private static JTextArea textArea;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField txtPerson;
	private JTextField txtRoom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
					det window = new det();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public det() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 595, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Submit");
		
		btnNewButton.setBounds(474, 27, 81, 23);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField.setText("");
			}
		});
		textField.setBounds(278, 27, 175, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Search by person card number");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(45, 27, 175, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Search only by room reader id");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(45, 64, 175, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField_1.setText("");
			}
		});
		textField_1.setBounds(278, 60, 175, 23);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Submit");
		
		btnNewButton_1.setBounds(474, 60, 81, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Search by access event");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(45, 94, 175, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textField_2.setText("");
			}
		});
		textField_2.setBounds(278, 93, 175, 23);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Submit");
		
		btnNewButton_2.setBounds(474, 93, 81, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("search by person and room");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(45, 128, 175, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		txtPerson = new JTextField();
		txtPerson.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtPerson.setText("");
			}
		});
		txtPerson.setText("person");
		txtPerson.setBounds(278, 126, 175, 23);
		frame.getContentPane().add(txtPerson);
		txtPerson.setColumns(10);
		
		
		txtRoom = new JTextField();
		txtRoom.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtRoom.setText("");
			}
		});
		txtRoom.setText("room");
		txtRoom.setBounds(278, 159, 175, 23);
		frame.getContentPane().add(txtRoom);
		txtRoom.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Submit");
		
		btnNewButton_3.setBounds(474, 159, 81, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Frequency analysis");
		
		btnNewButton_4.setBounds(388, 206, 167, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 239, 559, 200);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			detec det =new detec();
			det.person(textArea,textField.getText());
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				detec det =new detec();
				det.room(textArea,textField_1.getText());
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				detec det =new detec();
				det.AcE(textArea,textField_2.getText());
			}
		});
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				detec det =new detec();
				det.P_R(textArea,txtPerson.getText(),txtRoom.getText());
			}
		});
		
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				detec det= new detec();
				try {
					det.Frq(textArea);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
