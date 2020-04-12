package cie;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Courses {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Courses window = new Courses();
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
	public Courses() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(55, 92, 70, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JTextArea txtrXhdjhvkjbkjbjhcjvvmj = new JTextArea();
		txtrXhdjhvkjbkjbjhcjvvmj.setText("xhdjhvkjbkjbjhcjvvmj");
		txtrXhdjhvkjbkjbjhcjvvmj.setBounds(12, 178, 220, 66);
		frame.getContentPane().add(txtrXhdjhvkjbkjbjhcjvvmj);
		
		JTextArea txtrHgcvjhkbjnjhvh = new JTextArea();
		txtrHgcvjhkbjnjhvh.setText("hgcvjhkbjnjhvh");
		txtrHgcvjhkbjnjhvh.setBounds(89, 178, 1, 15);
		frame.getContentPane().add(txtrHgcvjhkbjnjhvh);
	}
}
