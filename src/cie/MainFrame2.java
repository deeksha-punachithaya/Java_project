package cie;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.sql.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame2 implements ActionListener
{
	JFrame main;
	JLabel branchl,seml;
	String[] branches = {"ISE","CSE"};
	String[] sems = {"3","4","5","6","7","8"};
	final JComboBox<String> branch_choice,sem_choice;
	JButton next1;
	
	JFrame frame1;
	JLabel coursel; 
	//return courses from database as array
	String[] courses = null;
	JComboBox<String> course_choice = null;
	String course;
	JButton update,del,prev,subdeets;
	
	JFrame frame2;
	JLabel coursenamel,semesterl,credsl;
	JTextField credsf;
	JTextArea coursenamef, semesterf;
	// String[] type = {"Mandatory","Elective"};
	// final JComboBox<String> type_choice;
	JButton confirm, cancel;
	
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
				    while(rs.next()) {
				    	 course_choice.addItem(rs.getString("name"));
					}   
				    frame1.add(coursel);
				    frame1.add(course_choice);
				}
				catch(Exception ex) {
					System.err.println(ex);
				}
				frame1.setVisible(true);
				frame1.setSize(800,200);
				frame1.setLayout(new GridLayout(3,8));
			}
		});
		
		main.getContentPane().add(branchl); 
		main.getContentPane().add(branch_choice);
		main.getContentPane().add(seml);
		main.getContentPane().add(sem_choice);
		main.getContentPane().add(next1);
		
		
		frame1 = new JFrame("Available Courses");
		frame1.setBounds(100, 100, 462, 225);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		
		coursel = new JLabel("All courses");
		coursel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		coursel.setBounds(6, 20, 117, 16);
		course_choice = new JComboBox<String>();
		course_choice.setBounds(219, 17, 212, 27);
		update = new JButton("Update");
		update.setBounds(6, 89, 117, 29);
		del = new JButton("Delete");
		del.setBounds(301, 89, 130, 29);
		prev = new JButton("Previous");
		prev.setBounds(6, 130, 117, 29);
		subdeets = new JButton("Subject Details");
		subdeets.setBounds(301, 130, 130, 29);
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
		del.addActionListener(this);
		prev.addActionListener(this);
		
		frame1.getContentPane().add(coursel);
		frame1.getContentPane().add(course_choice);
		frame1.getContentPane().add(update);
		frame1.getContentPane().add(del);
		frame1.getContentPane().add(prev);
		frame1.getContentPane().add(subdeets);
		subdeets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				subjdet = new JTextArea();
				String subject_details = null;
				frame1.dispose();
				frame2.dispose();
				try {
					PreparedStatement p3 = con.prepareStatement("select * from course"
							+ " where branch = ? and name = ?");
					p3.setString(1, chosen_b);
					p3.setString(2, course);
					ResultSet details = p3.executeQuery();
					while(details.next()) {
						String code = (String) details.getString(1);
						int show_credits = details.getInt(3);
						subject_details = "Subject Code: " + code + "\nSubject Name: " + 
						course + "\nSemester: " + chosen_sem + "\nCredits: " + show_credits 
						+ "\nBranch: " + chosen_b;
						subjdet.setText(subject_details);
					}
					scrollPane = new JScrollPane(subjdet);
					frame3.add(scrollPane);
				}
					catch(Exception ex) {
						System.err.println(ex);				
					}
				frame3.setVisible(true);
				frame3.setSize(800,200);
				frame3.setLayout(new GridLayout(5,8));
			}
			
		});
		
		
		frame2 = new JFrame("Update Course Details");
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setBounds(100, 100, 450, 300);
		frame2.getContentPane().setLayout(null);
		
		coursenamel = new JLabel("Course name");
		coursenamel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		coursenamel.setBounds(16, 28, 104, 16);

		coursenamef = new JTextArea();
		coursenamef.setBounds(184, 18, 244, 26);
		semesterl = new JLabel("Credits");
		semesterf = new JTextArea();
		semesterf.setBounds(184, 57, 244, 27);
		credsl = new JLabel("Credits");
		credsl.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		credsl.setBounds(16, 169, 104, 16);
		credsf = new JTextField(2);
		credsf.setBounds(184, 165, 244, 26);
		confirm = new JButton("Confirm");
		confirm.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		confirm.setBounds(16, 223, 117, 29);
		cancel = new JButton("Cancel");
		cancel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cancel.setBounds(311, 223, 117, 29);
		confirm.addActionListener(this);
		cancel.addActionListener(this);
		
		frame2.getContentPane().add(coursenamel);
		frame2.getContentPane().add(coursenamef);
		coursenamef.setEnabled(false);
		frame2.getContentPane().add(semesterl);
		frame2.getContentPane().add(semesterf);
		semesterf.setEnabled(false);
		frame2.getContentPane().add(credsl);
		frame2.getContentPane().add(credsf);
		frame2.getContentPane().add(confirm);
		frame2.getContentPane().add(cancel);
		
		frame3 = new JFrame("Subject Details");
// 		subjdet = new JTextArea();
//		final JScrollPane scrollPane = null;
		// subjdet.setEditable(false);		
		//add frame 3, text area with subject details
		//check total credits add upto 25
		
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
		app.frame1.setVisible(false);
		app.frame2.setVisible(false);
		app.frame3.setVisible(false);
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource() == del)
		{
			int response = JOptionPane.showConfirmDialog(frame1,"Are you sure you want to delete ?","Delete ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(response == JOptionPane.YES_OPTION)
			{
				//add function
			}
		}
		
		if(evt.getSource() == prev)
		{
			frame1.dispose();
			main.setVisible(true);
		}
//		
//		if(evt.getSource() == subdeets)
//		{
//					
//	}
		
		if(evt.getSource() == confirm)
		{
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
							System.out.println("No. of rows affected: " + i);
							frame2.dispose();
							frame1.setVisible(true);
						}	
						catch(Exception ex) {
							System.err.println(ex); 
						}
					}
				}
			}
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(frame2,"Invalid no. of Credits","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(evt.getSource() == cancel)
		{
			frame2.dispose();
			frame1.setVisible(true);
			frame1.setVisible(true);
		}
	}
	
//	public void DisplaySubDeets()
//	{
//		
//		
//	}

}
