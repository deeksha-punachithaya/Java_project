 
import java.util.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame implements ActionListener
{
	JFrame main;
	JLabel branchl,seml;
	String[] branches = {"Information Science and Engineering","Computer Science and Engineering"};
	String[] sems = {"Semester 1","Semester 2","Semester 3","Semester 4","Semester 5","Semester 6","Semester 7","Semester 8"};
	final JComboBox<String> branch_choice,sem_choice;
	JButton next1;
	
	JFrame frame1;
	JLabel coursel; 
	//return courses from database as array
	String[] courses = {"JAVA","OOADP","CG","ML"};
	final JComboBox<String> course_choice;
	String course;
	JButton update,del,prev,subdeets;
	
	JFrame frame2;
	JLabel coursenamel,semesterl,typel,credsl;
	JTextField credsf;
	JTextArea coursenamef, semesterf;
	String[] type = {"Mandatory","Elective"};
	final JComboBox<String> type_choice;
	JButton confirm, cancel;
	
	JFrame frame3;
	JTextArea subjdet;
	Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	Border loweredbevel = BorderFactory.createLoweredBevelBorder();
	
	MainFrame()
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
		next1.addActionListener(this);
		
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
		course_choice = new JComboBox<String>(courses);
		course_choice.setBounds(219, 17, 212, 27);
		update = new JButton("Update");
		update.setBounds(6, 89, 117, 29);
		del = new JButton("Delete");
		del.setBounds(301, 89, 130, 29);
		prev = new JButton("Previous");
		prev.setBounds(6, 130, 117, 29);
		subdeets = new JButton("Subject Details");
		subdeets.setBounds(301, 130, 130, 29);
		update.addActionListener(this);
		del.addActionListener(this);
		prev.addActionListener(this);
		
		frame1.getContentPane().add(coursel);
		frame1.getContentPane().add(course_choice);
		frame1.getContentPane().add(update);
		frame1.getContentPane().add(del);
		frame1.getContentPane().add(prev);
		frame1.getContentPane().add(subdeets);
		
		
		frame2 = new JFrame("Update Course Details");
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setBounds(100, 100, 450, 300);
		frame2.getContentPane().setLayout(null);
		
		coursenamel = new JLabel("Course name");
		coursenamel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		coursenamel.setBounds(16, 28, 104, 16);

		coursenamef = new JTextArea();
		coursenamef.setBounds(184, 18, 244, 26);
		semesterl = new JLabel("Semester");
		semesterf = new JTextArea();
		semesterf.setBounds(184, 57, 244, 27);
		typel = new JLabel("Course Type");
		typel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		typel.setBounds(16, 115, 104, 16);
		type_choice = new JComboBox<String>(type);
		type_choice.setBounds(184, 112, 244, 27);
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
		frame2.getContentPane().add(typel);
		frame2.getContentPane().add(type_choice);
		frame2.getContentPane().add(credsl);
		frame2.getContentPane().add(credsf);
		frame2.getContentPane().add(confirm);
		frame2.getContentPane().add(cancel);
		
		//add frame 3, text area with subject details
		//check total credits add upto 25
		
	}
	
	public static void main(String[] args)
	{
		MainFrame app = new MainFrame();
		app.main.setVisible(true);
		
		
		
		app.frame1.setVisible(false);
		app.frame2.setVisible(false);
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource() == next1)
		{
			main.dispose();
			courseFrame();
		}
		
		if(evt.getSource() == del)
		{
			int response = JOptionPane.showConfirmDialog(frame1,"Are you sure you want to delete ?","Delete ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(response == JOptionPane.YES_OPTION)
			{
				//add function
			}
		}
		
		if(evt.getSource() == update)
		{
			frame1.dispose();
			String course = (String) course_choice.getSelectedItem();
			String sem = (String) sem_choice.getSelectedItem();
			coursenamef.setText(course);
			semesterf.setText(sem);
			UpdateDeets();
		}
		
		if(evt.getSource() == prev)
		{
			frame1.dispose();
			main.setVisible(true);
		}
		
		if(evt.getSource() == subdeets)
		{
			frame1.dispose();
			frame3.setVisible(true);
			DisplaySubDeets();
		}
		
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
						//add function
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
			courseFrame();
		}
	}

	public void courseFrame()
	{
		frame1.setVisible(true);
		
	}
	
	public void UpdateDeets()
	{
		frame2.setVisible(true);
		
	}
	
	public void DisplaySubDeets()
	{
		frame3.setVisible(true);
		frame3.setSize(800,200);
		frame3.setLayout(new GridLayout(5,8));
	}

}
