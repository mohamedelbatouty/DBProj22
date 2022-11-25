import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
public class insert {

@SuppressWarnings("resource")
public static void pick(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		Scanner myObj = new Scanner(System.in);
		System.out.println("Would you like to insert a student, department, course, instructor, or course section");
		String choice=myObj.nextLine();
		if (choice.compareToIgnoreCase("Student")==0) {
			InsertStudent(conn);
		}
		else if (choice.compareToIgnoreCase("department")==0) {
			InsertDepartment(conn);
		}
		else if (choice.compareToIgnoreCase("course")==0) {
			InsertCourse(conn);
		}
		else if (choice.compareToIgnoreCase("Instructor")==0) {
			InsertInstructor(conn);
		}
		else if (choice.compareToIgnoreCase("course section")==0) {
			InsertCS(conn);
		}
		else {
			System.out.println("Type an option present");
			pick(conn);
		}
			
}	

private static void InsertCS(Connection conn) {
	// TODO Auto-generated method stub
	
}

private static void InsertInstructor(Connection conn) {
	// TODO Auto-generated method stub
	
}

private static void InsertCourse(Connection conn) {
	// TODO Auto-generated method stub
	
}

private static void InsertDepartment(Connection conn) {
	// TODO Auto-generated method stub
	
}

private static void InsertStudent(Connection conn) throws SQLException {
	PreparedStatement pstmt = conn.prepareStatement(
			"INSERT INTO STUDENT(Nnumber, Ssn, Fname, Minit, LName, Birth_Date, Sex, Class, City_Curr, State_Curr, Zip_Curr, Phone_Curr, City_Perm, State_Perm, Zip_Perm, Phone_Perm) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	System.out.print("\nEnter Student Nnumber: ");
	String Nnumber = Main.getString();
	System.out.println(Nnumber.compareTo(""));
	while(!(Nnumber!= null)) {
		System.out.println("null");
		break;
	}
	
	System.out.print("\nEnter SSN: ");
	String Ssn = Main.getString();
	
	System.out.print("\nEnter First Name: ");
	String Fname = Main.getString();
	
	System.out.print("\nEnter Middle Initial: ");
	String Minit = String.valueOf(Main.getChar());
	
	System.out.print("\nEnter Last Name: ");
	String LName = Main.getString();
	
	System.out.print("\nEnter Birth-date (yyyy-mm-dd): ");
	String tmppindate = Main.getString(); // the date is read as a String object in the specified format
	Date Birth_Date = Date.valueOf(tmppindate); // this converts the String object into a Date object
	
	System.out.print("\nEnter M or F: ");
	String Sex = String.valueOf(Main.getChar());
	
	System.out.print("\nEnter Class: ");
	String Class = Main.getString();
	
	System.out.print("\nEnter Current City: ");
	String City_Curr = Main.getString();
	
	System.out.print("\nEnter Current Zip: ");
	String Zip_Curr = Main.getString();
	
	System.out.print("\nEnter Current State: ");
	String State_Curr = Main.getString();
	
	System.out.print("\nEnter Current Phone Number: ");
	String Phone_Curr = Main.getString();
	
	System.out.print("\nEnter Permanent City: ");
	String City_Perm = Main.getString();
	
	System.out.print("\nEnter Permanent Zip: ");
	String Zip_Perm = Main.getString();
	
	System.out.print("\nEnter Permanent State: ");
	String State_Perm = Main.getString();
	
	System.out.print("\nEnter Permanent Phone Number: ");
	String Phone_Perm = Main.getString();
	
	
	pstmt.setString(1, Nnumber);
	pstmt.setString(2, Ssn);
	pstmt.setString(3, Fname);
	pstmt.setString(4, Minit);
	pstmt.setString(5, LName);
	pstmt.setDate(6,Birth_Date);
	pstmt.setString(7,Sex);
	pstmt.setString(8, Class);
	pstmt.setString(9, City_Curr);
	pstmt.setString(10, State_Curr);
	pstmt.setString(11, Zip_Curr);
	pstmt.setString(12, Phone_Curr);
	pstmt.setString(13, City_Perm);
	pstmt.setString(14, State_Perm);
	pstmt.setString(15, Zip_Perm);
	pstmt.setString(16,Phone_Perm);
	try {
	int NumRows = pstmt.executeUpdate();
	System.out.println("\n" + NumRows + " Student inserted");
	}
	catch(Exception e){
		System.out.println("A student with that SSN or Nnumber already exists");
	}
	
	// TODO Auto-generated method stub
	
}

public static void InsertToCourse(Connection conn){
	System.out.println("Inserting to course");
}

public static void InsertGrade(Connection conn){
	System.out.println("Inserting Grade");
}

}
