import java.util.Scanner;
import java.sql.*;
import java.io.*;
public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws SQLException {
		
		String uid = "G13";

		// System.out.print("password: ");
		String pword = "Fall2022G13";

		String url = "jdbc:oracle:thin:@cisvm-oracle.unfcsd.unf.edu:1521:orcl";

		// Load the Oracle JDBC driver

		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

		// Connect to the database

		Connection conn = DriverManager.getConnection(url, uid, pword);
		
		// TODO Auto-generated method stub
		boolean finish=false;
		Scanner myObj = new Scanner(System.in);
		while (finish==false) {
			System.out.println("1. Add a student, department, course, instructor, or course section");
			System.out.println("2. Add students to a given course/section");
			System.out.println("3. Generate Grade Report");
			System.out.println("4. List all courses a Department teaches");
			System.out.println("5. List all courses an instructor has taught");
			System.out.println("6. Add a grade to a student's course section");
			System.out.println("7. Exit");
			System.out.print("Enter choice:");
			String choice=myObj.next();
			
		switch (choice) {
		case "1":
			insert.pick(conn);
			break;
		case "2":
			insert.InsertToCourse(conn);
			break;
		case "3":
			System.out.println("Give a student's Nnumber for their Report");
			String Nnumber=myObj.next();
			Report.StudentReport(conn,Nnumber);
			break;
		case "4":
			List.Offered(conn);
			break;
		case "5":
			List.Taught(conn);
			break;
		case "6":
			insert.InsertGrade(conn);
			break;
		case "7":
			finish=true;
			break;
		default:
			System.out.println("Enter a valid option");
			break;
			
			}
		System.out.println();
		}
		conn.close();
	}
	public static String getString() {
		try {
			StringBuffer buffer = new StringBuffer();
			int c = System.in.read();
			while (c != '\n' && c != -1) {
				buffer.append((char) c);
				c = System.in.read();
			}
			return buffer.toString().trim();
		} catch (IOException e) {
			return null;
		}
	}

	public static int getInt()

	{	try {
		String s = getString();
		return Integer.parseInt(s);
	}
		catch(Exception e) {
			return -1;
		}
	}

	public static char getChar()

	{
		String s = getString();
		try {
		return s.charAt(0);
		}
		catch (Exception e) {
			return 0;
		}
	}
}


