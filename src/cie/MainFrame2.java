package cie;
import javax.swing.*;
import javax.swing.border.Border;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame2 {
	JFrame main;
	JLabel branchl,seml;
	String[] branches = {"ISE","CSE"};
	String[] sems = {"3","4","5","6","7","8"};
	final JComboBox<String> branch_choice,sem_choice;
	JButton next1;
	
	JFrame frame1;
	JLabel coursel, subject_label; 
	JComboBox<String> course_choice = null;
	String course;
	JButton update,del,prev,subdeets, close;
	
	JFrame frame2;
	JLabel coursenamel,semesterl,credsl, creditsl;
	JTextField credsf;
	JTextArea coursenamef, semesterf;
	JButton confirm, cancel, back;
	
	JFrame frame3;
	JScrollPane scrollPane = null;
	JTextArea subjdet;
	Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	Border loweredbevel = BorderFactory.createLoweredBevelBorder();
	
	static Connection con = null;
	static String chosen_b;
	static Integer chosen_sem;
	static int credit_show;
	
	MainFrame2()
	{
		
		main = new JFrame("Welcome");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setBounds(100, 100, 450, 252);
		main.getContentPane().setLayout(null);
		branchl = new JLabel("Branch");
		branchl.setBounds(6, 24, 75, 16);
		branch_choice = new JComboBox<String>(branches);
		branch_choice.setBounds(180, 21, 251, 27);	
		seml = new JLabel("Semester");
		seml.setBounds(6, 85, 85, 16);
		sem_choice = new JComboBox<String>(sems);
		sem_choice.setBounds(180, 82, 251, 27);
		next1 = new JButton("NEXT");
		next1.setBounds(314, 176, 117, 29);
		
		
		next1.setBorder(BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel));
		next1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chosen_b = (String) branch_choice.getSelectedItem();
				chosen_sem = Integer.parseInt((String) sem_choice.getSelectedItem());			
				main.dispose();
				ResultSet rs = null;
				try { 
					String sql = "Select name from course where branch = ? and semester = ?";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1,chosen_b);
					pstmt.setInt(2,chosen_sem);
				    rs = pstmt.executeQuery();
				    System.out.println("SQL query works");
				    course_choice = new JComboBox<String>();
					course_choice.setBounds(150, 17, 350, 27);

				    while(rs.next()) {
				    	 course_choice.addItem(rs.getString("name"));
					}   
				    
				}
				catch(Exception ex) {
					System.err.println(ex);
				}
				frame1 = new JFrame("Available Courses");
				frame1.setBounds(100, 100, 462, 225);
				frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame1.getContentPane().setLayout(null);
				frame1.getContentPane().add(coursel);
			    frame1.getContentPane().add(course_choice);
			    frame1.getContentPane().add(update);
				frame1.getContentPane().add(del);
				frame1.getContentPane().add(prev);
				frame1.getContentPane().add(subdeets);
				frame1.getContentPane().add(close);
				frame1.setVisible(true);
				frame1.setSize(515,225);
				frame1.setLayout(null);
			}
		});
		
		main.getContentPane().add(branchl);  
		main.getContentPane().add(branch_choice);
		main.getContentPane().add(seml);
		main.getContentPane().add(sem_choice);
		main.getContentPane().add(next1);
		coursel = new JLabel("All courses");
		coursel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		coursel.setBounds(25, 20, 117, 16);
		update = new JButton("Update");
		update.setBounds(50, 75, 175, 29);
		del = new JButton("Delete");
		del.setBounds(270, 75, 175, 29);
		prev = new JButton("Previous");
		prev.setBounds(50, 115, 175, 29);
		subdeets = new JButton("Subject Details");
		subdeets.setBounds(270, 115, 175, 29);
		close = new JButton("Close");
		close.setBounds(150, 155, 200, 30);
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				course = (String) course_choice.getSelectedItem();
				String sql_query = "select credits from course where name = ? and branch = ?";
				try {
					PreparedStatement p2 = con.prepareStatement(sql_query);
					p2.setString(1, course);
					p2.setString(2, chosen_b);
					ResultSet rs2 = p2.executeQuery();
					while(rs2.next()) {
						credit_show = rs2.getInt(1);
						semesterf.setText(String.valueOf(credit_show));
					}
				}
				catch(Exception ex) {
					System.err.println(ex);
				}
				coursenamef.setText(course);
				frame2.setVisible(true);
			}
			
		});
		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = JOptionPane.showConfirmDialog(frame1,"Are you sure you want to delete ?","Delete ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION)
				{
					try {
						PreparedStatement p4 = con.prepareStatement("delete from course "
								+ "where branch = ? and name = ? and semester = ?");
						p4.setString(1, chosen_b);
						p4.setString(2, course);
						p4.setInt(3, chosen_sem); 
						  
						int i=p4.executeUpdate();  
						System.out.println(i+" records deleted");  
					}
					catch(Exception ex) {
						System.err.println(ex); 
					}
					frame1.dispose();
					main.setVisible(true);
					JOptionPane.showMessageDialog(main,"Selected Record has been deleted.");  
				}				
			}
		});
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				main.setVisible(true);				
			}
		});

		subdeets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame3 = new JFrame("Subject Details");
				subjdet = new JTextArea();
				
				subject_label = new JLabel("Subject Details: ");
				String subject_details;
				ResultSet details = null;
				course = (String) course_choice.getSelectedItem();
				String details_string = "select code,credits from course where branch = ? and name = ? and semester = ?";
				try {
					PreparedStatement p3 = con.prepareStatement(details_string);
					p3.setString(1, chosen_b);
					p3.setString(2, course);
					p3.setInt(3, chosen_sem);
					details = p3.executeQuery();
					subjdet.setText(null);

					while(details.next()) {
						
						subject_details = "\nSubject Code: " + details.getString(1) + "\nSubject Name: " + 
								course + "\nSemester: " + chosen_sem + "\nCredits: " + details.getInt(2) 
								+ "\nBranch: " + chosen_b;
						subjdet.append(subject_details);
						JOptionPane.showMessageDialog(null, "Retrieved data succesfully.","Record Retrieved",
								JOptionPane.INFORMATION_MESSAGE);
						} 
					frame1.dispose();
					frame2.dispose();
					frame3.getContentPane().add(subject_label);
					frame3.getContentPane().add(subjdet);
					back = new JButton("Back");
					back.setBounds(300, 204, 117, 25);
					back.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame3.dispose();
							frame1.setVisible(true);
						}
						
					});
					frame3.getContentPane().add(back);
					frame3.setLayout(new FlowLayout());
					frame3.setVisible(true);
					frame3.setSize(300,300);
					
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
								JOptionPane.ERROR_MESSAGE);				
					}
				
			}
			
		});
		
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int final_credits = 0;
				try {
					PreparedStatement p5 = con.prepareStatement("select sum(credits) from course where "
							+ "semester = ? and branch = ? group by semester");
					p5.setInt(1, chosen_sem);
					p5.setString(2, chosen_b);
					ResultSet rs_credits = p5.executeQuery();
					while(rs_credits.next())
						final_credits = rs_credits.getInt(1);
					System.out.println("Final credits = " + final_credits);
				}
				catch(Exception ex) {
					System.err.println(ex);
				}
				if (final_credits != 25) {
					String wrong_credits = "The final credit count does not add up to 25. It is: " + final_credits + " Do you still want to close?";
					int credit_response = JOptionPane.showConfirmDialog(frame1,wrong_credits,"Credits may be wrong",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(credit_response == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
					
				}
				else {
					JOptionPane.showMessageDialog(frame1, "Thank you. Your changes have been saved.");
					System.exit(0);		
				}
				
			}
			
		});
		
		
		
		
		frame2 = new JFrame("Update Course Details");
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setBounds(100, 100, 450, 300);
		frame2.getContentPane().setLayout(null);
		
		coursenamel = new JLabel("Course");
		coursenamel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		coursenamel.setBounds(50, 28, 104, 16);
		creditsl = new JLabel("Credits");
		creditsl.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		creditsl.setBounds(50, 65, 104, 16);


		coursenamef = new JTextArea();
		coursenamef.setBounds(184, 18, 244, 26);
		semesterl = new JLabel("Credits");
		semesterf = new JTextArea();
		semesterf.setBounds(184, 57, 244, 27);
		credsl = new JLabel("Credits");
		credsl.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		credsl.setBounds(50, 169, 104, 16);
		credsf = new JTextField(2);
		credsf.setBounds(184, 165, 244, 26);
		confirm = new JButton("Confirm");
		confirm.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		confirm.setBounds(16, 223, 117, 29);
		cancel = new JButton("Cancel");
		cancel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cancel.setBounds(311, 223, 117, 29);
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int credits = Integer.parseInt(credsf.getText());
					if(credits <=0 || credits >6)
						throw new NumberFormatException();
					else
					{
						int response = JOptionPane.showConfirmDialog(frame2,"Are you sure you want to update and proceed ?","Save Changes ?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
						if(response == JOptionPane.YES_OPTION)
						{
							try {
								PreparedStatement p1 = con.prepareStatement("update course set credits = ? where name = ? and branch = ?");
								p1.setInt(1, credits);
								p1.setString(2, course);
								p1.setString(3, chosen_b);
								int i = p1.executeUpdate();
								String affected_rows = "No. of rows affected: " + i;
								System.out.println(affected_rows);
								frame2.dispose();
								frame1.setVisible(true);
								JOptionPane.showMessageDialog(frame1,affected_rows);
							}	
							catch(Exception ex) {
								System.err.println(ex); 
							}
						}
					} 
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(frame2,"Invalid no. of Credits","Error",JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2.dispose();
				frame1.setVisible(true);
				frame1.setVisible(true);				
			}
			
		});
		
		frame2.getContentPane().add(coursenamel);
		frame2.getContentPane().add(coursenamef);
		coursenamef.setEnabled(false);
		frame2.getContentPane().add(creditsl);
		frame2.getContentPane().add(semesterf);
		semesterf.setEnabled(false);
		frame2.getContentPane().add(credsl);
		frame2.getContentPane().add(credsf);
		frame2.getContentPane().add(confirm);
		frame2.getContentPane().add(cancel);
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/course_credit","root","abc123");
			System.out.println("connection established");
		}
		catch(Exception ex) {
			System.err.println(ex); 
		}
		MainFrame2 app = new MainFrame2();
		app.main.setVisible(true);
//		app.frame1.setVisible(false);
		app.frame2.setVisible(false);
//		app.frame3.setVisible(false);

	}
}
