package cie;
import java.sql.*;  
class Demo{  
public static void main(String args[]){  
try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection(  
"jdbc:mysql://localhost:3306/course_credit","root","abc123");  
// Statement stmt=con.createStatement();  
PreparedStatement stmt=con.prepareStatement("select code,name from course where semester=? and branch=?");
stmt.setInt(1,4);
stmt.setString(2, "ISE");
ResultSet rs=stmt.executeQuery();  
while(rs.next()){  
System.out.println(rs.getString(1)+" "+rs.getString(2));  
}  
con.close();  
}catch(Exception e){ System.out.println(e);}  
}  
}  


