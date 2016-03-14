package ChattingApp;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientGUI extends JFrame {

	private JPanel contentPane;
	private static JTextField message_area;
	private JTextField message_text;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI frame = new ClientGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
			s = new Socket("localhost", 1234);
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			String message_input= "";
			while(!message_input.equalsIgnoreCase("bye") || !message_input.equalsIgnoreCase("quit")) {
				message_input = din.readUTF();
				message_area.setText(message_area.getText().trim() + "\n Server: \t" + message_input);
			}
		} catch (Exception e) {
			
		}
	}

	/**
	 * Create the frame.
	 */
	public ClientGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		message_area = new JTextField();
		message_area.setBounds(10, 11, 348, 184);
		contentPane.add(message_area);
		message_area.setColumns(10);
		
		message_text = new JTextField();
		message_text.setBounds(20, 206, 232, 44);
		contentPane.add(message_text);
		message_text.setColumns(10);
		
		JButton message_send = new JButton("Submit !");
		message_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String message_out = "";
					message_out = message_text.getText().trim();
					dout.writeUTF(message_out);
					
				} 
				catch (Exception e1) {
					
				}
			}
		});
		message_send.setBounds(285, 206, 89, 39);
		contentPane.add(message_send);
	}
}
