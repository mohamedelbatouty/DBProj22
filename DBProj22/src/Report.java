import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


// finish report and make it look nice
public class Report {
	public static void StudentReport(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
Statement stmt = conn.createStatement();
		
		System.out.print("Student's Nnumber: ");
		String v = Main.getString();
		// System.out.println(v);
		// Select the P_CODE, P_DESCRIPT, P_PRICE, and P_ONHAND columns from the
		// PRODUCT table
		
		String w = "select * " + "from STUDENT " + "where Nnumber= '" + v +"'";
		ResultSet Info = stmt.executeQuery(w);
		Info.next();
		System.out.println("Name: "+Info.getString("Fname")+" "+ Info.getString("Minit")+" "+ Info.getString("LName"));
		System.out.println("Birth Date: "+Info.getDate("Birth_Date"));
		System.out.println("Sex: "+Info.getString("Sex"));
		System.out.println("Current Address: "+Info.getString("Street_Curr")+" "+Info.getString("City_Curr")+" "+ Info.getString("State_Curr")+" "+ Info.getString("Zip_Curr"));
		System.out.println("Permanent Address: "+Info.getString("Street_Perm")+" "+Info.getString("City_Perm")+" "+ Info.getString("State_Perm")+" "+ Info.getString("Zip_Perm"));
		System.out.println("Current Phone #:"+Info.getString("Phone_Curr"));
		System.out.println("Current Phone #:"+Info.getString("Phone_Perm"));

		String z = "select * " + "from Major_Dep " + "where Nnumber= '" + v +"'";
		ResultSet Major = stmt.executeQuery(z);
		System.out.print("Major(s) Department: ");
		while (Major.next()) {
			System.out.println(Major.getString("Major_code"));
		}
		
		z = "select * " + "from Minor_Dep " + "where Nnumber= '" + v +"'";
		ResultSet Minor = stmt.executeQuery(z);
		System.out.print("Minor(s) Department: ");
		while (Minor.next()) {
			System.out.println(Minor.getString("Minor_code"));
		}
		
		z = "select * " + "from DEGREE_PROGRAM " + "where Nnumber= '" + v +"'";
		ResultSet Degree = stmt.executeQuery(z);
		System.out.print("Degree Program(s): ");
		while (Degree.next()) {
			System.out.println(Degree.getString("Degree_Program"));
		}
		
		
		String q = "select Dep_Code, CNumber, Section_Number, CS_Year, Semester, Grade " + "from ENROLLEDIN " + "where Nnumber= '" + v +"'";
		
		ResultSet rset = stmt.executeQuery(q);

		System.out.println("\n");
		int Cred_Hours;
		// Iterate through the result
		int credits=0;
		double qualityPoints = 0;
		String Letter_Grade="";
		System.out.println("Department Code| Course Number | Semester | Section Year | Section Number | Credit Hours | Letter Grade | Quality Points");
		while (rset.next()) {
			String Dep_Code = rset.getString("Dep_Code");
			String CNumber = rset.getString("CNumber");
			int Section_Number = rset.getInt("Section_Number");
			int CS_Year = rset.getInt("CS_Year");
			String Semester = rset.getString("Semester");
			double Grade = rset.getDouble("Grade");
			
			Statement search = conn.createStatement();
			String f = "select Num_Hours " + "from COURSE " + "where CNumber= '" + CNumber +"' and Dep_Code= '"+Dep_Code+"'";
			ResultSet hours = search.executeQuery(f);
			hours.next();
			Cred_Hours = hours.getInt("Num_Hours");
			double qual=Grade*Cred_Hours;
			//System.out.println("Grade: "+ Grade);
			if (Grade==4.0)
				Letter_Grade="A";
			else if (Grade==3.7)
				Letter_Grade="A-";
			else if (Grade==3.3)
				Letter_Grade="B+";
			else if (Grade==3.0)
				Letter_Grade="B";
			else if (Grade==2.7)
				Letter_Grade="B-";
			else if (Grade==2.3)
				Letter_Grade="C+";
			else if (Grade==2.0)
				Letter_Grade="C";
			else if (Grade==1.0)
				Letter_Grade="D";
			else if (Grade==0) 
				Letter_Grade="F";
			else if (Grade==-1) {
				Letter_Grade="N/A";
				qual=0;
			}
					
			
			
			String form = String.format("%-16s %-10s      %-10s %-10d     %-16d %-15d%-15s %2.2f", Dep_Code,CNumber,Semester,CS_Year,Section_Number, Cred_Hours, Letter_Grade, qual);
	        System.out.println(form);
	        
	        if (Grade==-1)
	        	Cred_Hours=0;
	        
	        qualityPoints+=Grade*Cred_Hours;
			credits+=Cred_Hours;
	        
		} // while rset
		System.out.printf("GPA: %,.1f",qualityPoints/credits);

		System.out.println("\n");
	}
}
