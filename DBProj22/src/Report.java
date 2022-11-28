import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Report {
	public static void StudentReport(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
Statement stmt = conn.createStatement();
		
		System.out.print("Student's Nnumber: ");
		String v = Main.getString();
		// System.out.println(v);
		// Select the P_CODE, P_DESCRIPT, P_PRICE, and P_ONHAND columns from the
		// PRODUCT table

		String q = "select Dep_Code, CNumber, Section_Number, CS_Year, Semester, Grade " + "from ENROLLEDIN " + "where Nnumber= '" + v +"'";
		
		ResultSet rset = stmt.executeQuery(q);

		System.out.println("\n");
		int Cred_Hours;
		// Iterate through the result
		int credits=0;
		double qualityPoints = 0;
		while (rset.next()) {
			String Dep_Code = rset.getString("Dep_Code");
			String CNumber = rset.getString("CNumber");
			int Section_Number = rset.getInt("Section_Number");
			int CS_Year = rset.getInt("CS_Year");
			String Semester = rset.getString("Semester");
			double grade = rset.getDouble("Grade");
			
			Statement search = conn.createStatement();
			String f = "select Num_Hours " + "from COURSE " + "where CNumber= '" + CNumber +"' and Dep_Code= '"+Dep_Code+"'";
			ResultSet hours = search.executeQuery(f);
			hours.next();
			Cred_Hours = hours.getInt("Num_Hours");
			qualityPoints+=grade*Cred_Hours;
			credits+=Cred_Hours;
			
			
			System.out.println(Dep_Code + ":" + CNumber + ":" + Section_Number + ":" + CS_Year+ ":" + Semester+":"+grade+":"+Cred_Hours);
		} // while rset
		System.out.printf("GPA: %,.1f",qualityPoints/credits);

		System.out.println("\n");
	}
}
