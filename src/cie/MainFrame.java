package cie;
import java.util.*;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainFrame implements ActionListener
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
	JLabel coursenamel,semesterl,typel,credsl;
	JTextField credsf;
	JTextArea coursenamef, semesterf;
	String[] type = {"Mandatory","Elective"};
	final JComboBox<String> type_choice = null;
	JButton confirm, cancel;
	
	JFrame frame3;
	JTextArea subjdet;
	
	static Connection con = null;
	static Statement stmt = null;
	
	MainFrame()
	{
		
		main = new JFrame("Welcome");
		branchl = new JLabel("Branch");
		branch_choice = new JComboBox<String>(branches);
		seml = new JLabel("Semester");
		sem_choice = new JComboBox<String>(sems);
		next1 = new JButton("NEXT");
		next1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String chosen_b = (String) branch_choice.getSelectedItem();
				Integer chosen_sem = Integer.parseInt((String) sem_choice.getSelectedItem());			
				main.dispose();
				// courseFrame(chosen_b, chosen_sem);
				ResultSet rs = null;
				try { 
					
					//establishConnection();
					String sql = "Select name from course where branch = ?";
					PreparedStatement pstmt = con.prepareStatement(sql);
					pstmt.setString(1,chosen_b);
					// pstmt.setInt(2,chosen_sem);
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
				
			    // course_choice = new JComboBox<String>(courses);
			   
				// main.dispose();
				frame1.setVisible(true);
				frame1.setSize(800,200);
				frame1.setLayout(new GridLayout(3,8));
			}
		}); 
		
		main.add(branchl); 
		main.add(branch_choice);
		main.add(seml); 
		main.add(sem_choice);
		main.add(next1);
		
		
		frame1 = new JFrame("Available Courses");
		coursel = new JLabel("All courses");
		
//		update = new JButton("Update");
//		del = new JButton("Delete");
//		prev = new JButton("Previous");
//		subdeets = new JButton("Subject Details");
//		update.addActionListener(this);
//		del.addActionListener(this);
//		prev.addActionListener(this);
//		
//		frame1.add(coursel);
		
//		frame1.add(update);
//		frame1.add(del);
//		frame1.add(prev);
//		frame1.add(subdeets);
//		
		
//		frame2 = new JFrame("Update course details");
//		coursenamel = new JLabel("Course name");
//		coursenamef = new JTextArea();
//		semesterl = new JLabel("Semester");
//		semesterf = new JTextArea();
//		typel = new JLabel("Course Type");
//		type_choice = new JComboBox<String>(type);
//		credsl = new JLabel("Credits");
//		credsf = new JTextField(2);
//		confirm = new JButton("Confirm");
//		cancel = new JButton("Cancel");
//		confirm.addActionListener(this);
//		cancel.addActionListener(this);
//		
//		frame2.add(coursenamel);
//		frame2.add(coursenamef);
//		coursenamef.setEnabled(false);
//		frame2.add(semesterl);
//		frame2.add(semesterf);
//		semesterf.setEnabled(false);
//		frame2.add(typel);
//		frame2.add(type_choice);
//		frame2.add(credsl);
//		frame2.add(credsf);
//		frame2.add(confirm);
//		frame2.add(cancel);
		
		//add frame 3, text area with subject details
		//check total credits add upto 25
		
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		// Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/course_credit","root","abc123");
			// stmt = con.createStatement();
			System.out.println("connection established");
		}
		catch(Exception ex) {
			System.err.println(ex); 
		}
		MainFrame app = new MainFrame();
		app.main.setVisible(true);
		app.main.setSize(600,200);
		app.main.setLayout(new GridLayout(4,5));
		
		app.frame1.setVisible(false);
//		app.frame2.setVisible(false);
	}
	
//	public static void establishConnection() throws SQLException, ClassNotFoundException {
//		
//	}
	
	public void actionPerformed(ActionEvent evt)
	{
//		if(evt.getSource() == next1)
//		{
////			String chosen_b = (String) branch_choice.getSelectedItem();
////			Integer chosen_sem = Integer.parseInt((String) sem_choice.getSelectedItem());			
////			main.dispose();
////			// courseFrame(chosen_b, chosen_sem);
////			List<String> courses_list = null;
////			ResultSet rs = null;
////			try {
////				establishConnection();
////				String sql = "SELECT code FROM course where branch "
////						+ "= '"+chosen_b+"' and semester = "+chosen_sem+";";
////			    rs = stmt.executeQuery(sql);
////			    System.out.println("SQL query works");
////			    while(rs.next()) {
////			    	course_choice.addItem(rs.getString("name"))
//////			         String code  = rs.getString("code");
//////			         String name = rs.getString("name");
//////			         courses_list = new ArrayList<String>();
//////			         courses_list.add(name);
////				}   
////			}
////			catch(Exception ex) {
////				System.err.println(ex);
////			}
////			String[] courses = new String[ courses_list.size() ];
////		    courses_list.toArray( courses );
////		    System.out.println(courses);
////		    course_choice = new JComboBox<String>(courses);
////		    frame1.add(coursel);
////		    frame1.add(course_choice);
////			// main.dispose();
////			frame1.setVisible(true);
////			frame1.setSize(800,200);
////			frame1.setLayout(new GridLayout(3,8));
//		}
//		
////		public void courseFrame(String b, int s)
////		{
////			List<String> courses_list = null;
////			ResultSet rs = null;
////			try {
////				String sql = "SELECT code,name FROM course where branch = "+b+" and semester = "+s;
////			    rs = stmt.executeQuery(sql);
////			    System.out.println("SQL query works");
////			    while(rs.next()){
////			         String code  = rs.getString("code");
////			         String name = rs.getString("name");
////			         courses_list = new ArrayList<String>();
////			         courses_list.add(name);
////				}
////			    String[] courses = new String[ courses_list.size() ];
////			    courses_list.toArray( courses );
////			    course_choice = new JComboBox<String>(courses);
////			    frame1.add(course_choice);
////			}
////			catch(Exception ex) {
////				System.err.println(ex);
////			}
////			// main.dispose();
////			frame1.setVisible(true);
////			frame1.setSize(800,200);
////			frame1.setLayout(new GridLayout(3,8));
////		}
////		
//		if(evt.getSource() == del)
//		{
//			int response = JOptionPane.showConfirmDialog(frame1,"Are you sure you want to delete ?","Delete ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//			if(response == JOptionPane.YES_OPTION)
//			{
//				//add function
//			}
//		}
//		
//		if(evt.getSource() == update)
//		{
//			frame1.dispose();
//			String course = (String) course_choice.getSelectedItem();
//			String sem = (String) sem_choice.getSelectedItem();
//			coursenamef.setText(course);
//			semesterf.setText(sem);
//			UpdateDeets();
//		}
//		
//		if(evt.getSource() == prev)
//		{
//			frame1.dispose();
//			main.setVisible(true);
//		}
//		
//		if(evt.getSource() == subdeets)
//		{
//			frame1.dispose();
//			frame3.setVisible(true);
//			DisplaySubDeets();
//		}
//		
//		if(evt.getSource() == confirm)
//		{
//			try
//			{
//				int credits = Integer.parseInt(credsf.getText());
//				if(credits <=0 || credits >6)
//					throw new NumberFormatException();
//				else
//				{
//					int response = JOptionPane.showConfirmDialog(frame2,"Are you sure you want to update and proceed ?","Save Changes ?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
//					if(response == JOptionPane.YES_OPTION)
//					{
//						//add function
//					}
//				}
//			}
//			catch(NumberFormatException e)
//			{
//				JOptionPane.showMessageDialog(frame2,"Invalid no. of Credits","Error",JOptionPane.ERROR_MESSAGE);
//			}
//		}
//		
//		if(evt.getSource() == cancel)
//		{
//			frame2.dispose();
//			frame1.setVisible(true);
//			// courseFrame();
//		}
	}
//
//
//	
//	public void UpdateDeets()
//	{
//		frame2.setVisible(true);
//		frame2.setSize(800,300);
//		frame2.setLayout(new GridLayout(5,8));
//	}
//	
//	public void DisplaySubDeets()
//	{
//		frame3.setVisible(true);
//		frame3.setSize(800,200);
//		frame3.setLayout(new GridLayout(5,8));
//	}
//
}