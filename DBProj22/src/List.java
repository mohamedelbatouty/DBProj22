import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class List {

	public static void Taught(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		
		System.out.print("Instructor Nnumber: ");
		String v=insert.GetNnumber();

		String q = "select Dep_Code, CNumber, Section_Number, CS_Year, Semester " + "from COURSE_SECTION " + "where Instructor= '" + v +"'";
		
		ResultSet rset = stmt.executeQuery(q);

		System.out.println("\n");

		// Iterate through the result

		while (rset.next()) {
			String Dep_Code = rset.getString("Dep_Code");
			String CNumber = rset.getString("CNumber");
			int Section_Number = rset.getInt("Section_Number");
			int CS_Year = rset.getInt("CS_Year");
			String Semester = rset.getString("Semester");
			System.out.println(Dep_Code + ":" + CNumber + ":" + Section_Number + ":" + CS_Year+ ":" + Semester);
		} // while rset

		System.out.println("\n");
	}
	
	public static void Offered(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		Statement stmt = conn.createStatement();
		System.out.print("Would you like to insert department name or code?");
		String choice=Main.getString();
		String q = null;
		if (choice.compareToIgnoreCase("name")==0) {
			System.out.print("\nEnter department name:");
			String name= Main.getString();
			q = "select CNumber, CName, CLevel, Num_hours, Description " + "from COURSE Co, DEPARTMENT De " + "where De.Dep_Name='" + name+"' and Co.Dep_code=De.Dep_Code";
		}
		else if (choice.compareToIgnoreCase("code")==0) {
			System.out.print("\nEnter department code:");
			String code=Main.getString();
			q = "select CNumber, CName, CLevel, Num_hours, Description " + "from COURSE " + "where Dep_Code='" + code+"'";
			
		}
		
		ResultSet rset = stmt.executeQuery(q);
		System.out.println("\n");
		
		while (rset.next()) {
			String CNumber = rset.getString("CNumber");
			String CName = rset.getString("CName");
			String CLevel = rset.getString("CLevel");
			int Num_hours = rset.getInt("Num_hours");
			String Description = rset.getString("Description");
			System.out.println(CNumber + ":" + CName + ":" + CLevel + ":" + Num_hours+ ":" + Description);
		}
	}
}
