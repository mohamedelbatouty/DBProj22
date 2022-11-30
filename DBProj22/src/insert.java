import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;
public class insert {

	public static String GetNnumber() {
		try {
		String Nnumber = Main.getString();
		while(Nnumber.compareTo("")==0||(Nnumber.charAt(0)!='N')||Nnumber.length()!=9||Nnumber.substring(1,8).matches("[0-9]+")==false) {
			System.out.println("Enter a valid Nnumber");
			Nnumber=Main.getString();
		}
		return Nnumber;
		}
		catch (Exception e) {
			System.out.println("Enter a valid SSN (No Dashes)");
			return GetNnumber();
		}
	}
	
	public static String DepCode() {
		String Dep_Code= Main.getString();
		while(Dep_Code.compareTo("")==0||Dep_Code.length()>4) {
			System.out.println("Enter a valid Department Code");
			Dep_Code=Main.getString();
		}
		return Dep_Code;
	}
	
	public static String CourseNumber() {
		try{
		String CNumber= Main.getString();
		while(CNumber.compareTo("")==0||(CNumber.substring(3,7).matches("[0-9]+")==false||CNumber.substring(0,2).matches("[a-zA-Z]+")==false)) {
			System.out.println("Enter a valid Course Number");
			CNumber=Main.getString();
		}
		return CNumber;
		}
		catch (Exception e) {
			System.out.println("Enter a valid Course Number");
				return CourseNumber();
		}
	}
	
	public static String Semester() {
		String Semester= Main.getString();
		while(Semester.compareTo("")==0||(Semester.compareToIgnoreCase("Summer")!=0 && Semester.compareToIgnoreCase("fall")!=0 && Semester.compareToIgnoreCase("Spring")!=0)) {
			System.out.println("Enter a valid Semester");
			Semester=Main.getString();
		}
		return Semester;
	}
	
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

private static void InsertCS(Connection conn) throws SQLException {
	PreparedStatement pstmt = conn.prepareStatement(
			"INSERT INTO COURSE_SECTION(Dep_Code, CNumber, Section_Number, Instructor , CS_Year, Semester) "
					+ "VALUES (?, ?, ?, ?, ?, ?)");
		
	System.out.print("\nEnter Department Code: ");
	String Dep_Code = DepCode();
	
	
	System.out.print("\nEnter Course Number: ");
	String CNumber = CourseNumber();
	
	
	System.out.print("\nEnter Section Number: ");
	int Section_Number = Main.getInt();
	while (Section_Number==-1) {
		System.out.println("Enter a valid Section Number");
		Section_Number=Main.getInt();
	}
	
	
	System.out.print("\nEnter Instructor Nnumber: ");
	String Nnumber = GetNnumber();
	
	System.out.print("\nEnter Year: ");
	int year = Main.getInt();
	while (year==-1) {
		System.out.println("Enter a valid year");
		year=Main.getInt();
	}
	
	System.out.print("\nEnter Semester: ");
	String Semester = Semester();
	
		
	pstmt.setString(1, Dep_Code);
	pstmt.setString(2, CNumber);
	pstmt.setInt(3, Section_Number);
	pstmt.setString(4, Nnumber);
	pstmt.setInt(5,year);
	pstmt.setString(6, Semester);
	try {
	int NumRows = pstmt.executeUpdate();
	System.out.println("\n" + NumRows + " Course Section inserted");
	}
	catch(SQLIntegrityConstraintViolationException e){
		System.out.println("That Course section already exists, or that course/Instructor doesn't exist");
	}
	// TODO Auto-generated method stub
	
}


private static void InsertInstructor(Connection conn) throws SQLException {
	PreparedStatement pstmt = conn.prepareStatement(
			"INSERT INTO INSTRUCTOR(Nnumber, Ssn, Fname, Minit, LName, City, State, Zip, Phone, Office_Number, Department_Code, Street, Age)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	PreparedStatement pstmtt = conn.prepareStatement(
			"INSERT INTO INFORMATION(Nnumber, Ssn)"
					+ "VALUES (?, ?)");
	
	System.out.print("\nEnter Instructor Nnumber: ");
	String Nnumber = GetNnumber();
	
	System.out.print("\nEnter SSN (No Dashes): ");
	String Ssn = Main.getString();
	while(Ssn.compareTo("")==0||Ssn.length()!=9||Ssn.matches("[0-9]+")==false) {
		System.out.println("Enter a valid Ssn");
		Ssn=Main.getString();
	}
	
	System.out.print("\nEnter First Name: ");
	String Fname = Main.getString();
	while(Fname.compareTo("")==0) {
		System.out.print("Enter a first name:");
		Fname=Main.getString();
	}
	
	System.out.print("\nEnter Middle Initial: ");
	String Minit = String.valueOf(Main.getChar());
	
	System.out.print("\nEnter Last Name: ");
	String LName = Main.getString();
	while(LName.compareTo("")==0) {
		System.out.println("Enter a Last name:");
		LName=Main.getString();
	}
	
	System.out.print("\nEnter Age: ");
	int Age = Main.getInt(); // the date is read as a String object in the specified format
	
	
	System.out.print("\nEnter Street : ");
	String Street_Curr = Main.getString();
	
	System.out.print("\nEnter City of Residence : ");
	String City_Curr = Main.getString();
	
	System.out.print("\nEnter Zip of Residence: ");
	String Zip_Curr = Main.getString();
	
	System.out.print("\nEnter State of Residence: ");
	String State_Curr = Main.getString();
	
	System.out.print("\nEnter Phone Number: ");
	String Phone_Curr = Main.getString();
	
	
	System.out.print("\nEnter Office Phone Number : ");
	String Office_Number = Main.getString();
	
	System.out.print("\nEnter Department Code : ");
	String Dep_Code = DepCode();
	
	
	pstmt.setString(1, Nnumber);
	pstmt.setString(2, Ssn);
	pstmtt.setString(1, Nnumber);
	pstmtt.setString(2, Ssn);
	pstmt.setString(3, Fname);
	pstmt.setString(4, Minit);
	pstmt.setString(5, LName);
	pstmt.setInt(13,Age);
	pstmt.setString(6, City_Curr);
	pstmt.setString(7, State_Curr);
	pstmt.setString(8, Zip_Curr);
	pstmt.setString(9, Phone_Curr);
	pstmt.setString(10, Office_Number);
	pstmt.setString(11, Dep_Code);
	pstmt.setString(12, Street_Curr);
	
	
	try {
	int NumRows = pstmtt.executeUpdate();	
	NumRows = pstmt.executeUpdate();
	System.out.println("\n" + NumRows + " Instructor inserted");
	}
	catch(SQLIntegrityConstraintViolationException e){
		System.out.println("An Instructor/Student with that SSN or Nnumber already exists");
	}
	// TODO Auto-generated method stub
	
}
private static void InsertCourse(Connection conn) throws SQLException {
	PreparedStatement pstmt = conn.prepareStatement(
			"INSERT INTO COURSE(Dep_Code, CNumber, CName, CLevel, Description, Num_Hours) "
					+ "VALUES (?, ?, ?, ?, ?, ?)");
		
	System.out.print("\nEnter Department Code: ");
	String Dep_Code = DepCode();
	
	System.out.print("\nEnter Course Number: ");
	String CNumber = CourseNumber();
	
	System.out.print("\nEnter Course Name: ");
	String CName = Main.getString();
	while(CName.compareTo("")==0) {
		System.out.println("You must enter a Course Name");
		CName=Main.getString();
	}
	
	System.out.print("\nEnter Course Level: ");
	String CLevel = Main.getString();
	
	System.out.print("\nEnter Course Description: ");
	String Description = Main.getString();
	
	System.out.print("\nEnter Course Credit Hours: ");
	int Num_Hours = Main.getInt();
	while (Num_Hours==-1) {
		System.out.println("You must enter an hour amount");
		Num_Hours=Main.getInt();
	}
		
	pstmt.setString(1, Dep_Code);
	pstmt.setString(2, CNumber);
	pstmt.setString(3, CName);
	pstmt.setString(4, CLevel);
	pstmt.setString(5, Description);
	pstmt.setInt(6, Num_Hours);
	try {
	int NumRows = pstmt.executeUpdate();
	System.out.println("\n" + NumRows + " Course inserted");
	}
	catch(SQLIntegrityConstraintViolationException e){
		System.out.println("A Course with that Name or Code already exists, or that department doesn't exist");
	}
	// TODO Auto-generated method stub
	
}

private static void InsertDepartment(Connection conn) throws SQLException {
	PreparedStatement pstmt = conn.prepareStatement(
			"INSERT INTO DEPARTMENT(Office_num, Dep_Code, Dep_Name, Office_Phone, College) "
					+ "VALUES (?, ?, ?, ?, ?)");
	System.out.print("\nEnter Office Number: ");
	int Office_num = Main.getInt();
	
	
	System.out.print("\nEnter Department Code: ");
	String Dep_Code = DepCode();
	
	System.out.print("\nEnter Department Name: ");
	String Dep_Name = Main.getString();
	while(Dep_Name.compareTo("")==0||Dep_Name.length()>15) {
		System.out.println("Enter a valid Department Name");
		Dep_Name=Main.getString();
	}
	
	System.out.print("\nEnter Office Phone Number: ");
	String Office_Phone = Main.getString();
	
	System.out.print("\nEnter College: ");
	String College = Main.getString();
	
		
	pstmt.setInt(1, Office_num);
	pstmt.setString(2, Dep_Code);
	pstmt.setString(3, Dep_Name);
	pstmt.setString(4, Office_Phone);
	pstmt.setString(5, College);
	try {
	int NumRows = pstmt.executeUpdate();
	System.out.println("\n" + NumRows + " Department inserted");
	}
	catch(SQLIntegrityConstraintViolationException e){
		System.out.println("A Department with that Name or Code already exists");
	}
	// TODO Auto-generated method stub
	
}

private static void InsertStudent(Connection conn) throws SQLException {
	PreparedStatement pstmt = conn.prepareStatement(
			"INSERT INTO STUDENT(Nnumber, Ssn, Fname, Minit, LName, Birth_Date, Sex, Class, City_Curr, State_Curr, Zip_Curr, Phone_Curr, City_Perm, State_Perm, Zip_Perm, Phone_Perm, Street_Curr, Street_Perm) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	PreparedStatement pstmtt = conn.prepareStatement(
			"INSERT INTO INFORMATION(Nnumber, Ssn)"
					+ "VALUES (?, ?)");
	
	
	System.out.print("\nEnter Student Nnumber: ");
	String Nnumber = GetNnumber();
	
	System.out.print("\nEnter SSN (No Dashes): ");
	String Ssn = Main.getString();
	while(Ssn.compareTo("")==0||Ssn.length()!=9||Ssn.matches("[0-9]+")==false) {
		System.out.println("Enter a valid Ssn");
		Ssn=Main.getString();
	}
	
	System.out.print("\nEnter First Name: ");
	String Fname = Main.getString();
	while(Fname.compareTo("")==0) {
		System.out.print("Enter a first name:");
		Fname=Main.getString();
	}
	
	System.out.print("\nEnter Middle Initial: ");
	String Minit = String.valueOf(Main.getChar());
	
	System.out.print("\nEnter Last Name: ");
	String LName = Main.getString();
	while(LName.compareTo("")==0) {
		System.out.println("Enter a Last name:");
		LName=Main.getString();
	}
	
	System.out.print("\nEnter Birth-date (yyyy-mm-dd): ");
	String tmppindate = Main.getString(); // the date is read as a String object in the specified format
	Date Birth_Date=null;
	if (tmppindate.compareTo("")!=0)
		 Birth_Date = Date.valueOf(tmppindate); // this converts the String object into a Date object
	
	System.out.print("\nEnter M or F: ");
	String Sex = String.valueOf(Main.getChar());
	
	System.out.print("\nEnter Class: ");
	String Class = Main.getString();
	
	System.out.print("\nEnter Current Street: ");
	String Street_Curr = Main.getString();
	
	System.out.print("\nEnter Current City: ");
	String City_Curr = Main.getString();
	
	System.out.print("\nEnter Current Zip: ");
	String Zip_Curr = Main.getString();
	
	System.out.print("\nEnter Current State: ");
	String State_Curr = Main.getString();
	
	System.out.print("\nEnter Current Phone Number: ");
	String Phone_Curr = Main.getString();
	
	System.out.print("\nEnter Current Street: ");
	String Street_Perm = Main.getString();
	
	System.out.print("\nEnter Permanent City: ");
	String City_Perm = Main.getString();
	
	System.out.print("\nEnter Permanent Zip: ");
	String Zip_Perm = Main.getString();
	
	System.out.print("\nEnter Permanent State: ");
	String State_Perm = Main.getString();
	
	System.out.print("\nEnter Permanent Phone Number: ");
	String Phone_Perm = Main.getString();
	
	
	pstmt.setString(1, Nnumber);
	pstmtt.setString(1, Nnumber);
	pstmt.setString(2, Ssn);
	pstmtt.setString(2, Ssn);
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
	pstmt.setString(17,Street_Curr);
	pstmt.setString(18,Street_Perm);
	try {
	int NumRows = pstmtt.executeUpdate();
	NumRows = pstmt.executeUpdate();
	System.out.println("\n" + NumRows + " Student inserted");
	PreparedStatement Major = conn.prepareStatement(
			"INSERT INTO MAJOR_DEP(Nnumber, Major_code)"
					+ "VALUES (?, ?)");
	PreparedStatement Minor = conn.prepareStatement(
			"INSERT INTO MINOR_DEP(Nnumber, Minor_code)"
					+ "VALUES (?, ?)");
	PreparedStatement Degree = conn.prepareStatement(
			"INSERT INTO DEGREE_PROGRAM(Nnumber, Degree_Program)"
					+ "VALUES (?, ?)");
	
	System.out.print("\nEnter Major Department Code: ");
	String MajorDep = Main.getString();
	
	Major.setString(1, Nnumber);
	Major.setString(2, MajorDep);

	System.out.print("\nEnter Minor Department Code: ");
	String MinorDep = Main.getString();
	
	Minor.setString(1, Nnumber);
	Minor.setString(2, MinorDep);

	
	System.out.print("\nEnter Degree Program: ");
	String DegreePro = Main.getString();
	
	Degree.setString(1, Nnumber);
	Degree.setString(2, DegreePro);
	
	
	NumRows = Major.executeUpdate();
	System.out.println("\n" + NumRows + " Major inserted");
	NumRows = Minor.executeUpdate();
	System.out.println("\n" + NumRows + " Minor inserted");
	NumRows = Degree.executeUpdate();
	System.out.println("\n" + NumRows + " Degree Program inserted");
	
	}
	catch(SQLIntegrityConstraintViolationException e){
		System.out.println("An Instructor/Student with that SSN or Nnumber already exists");
	}
}

public static void InsertToCourse(Connection conn) throws SQLException{
	PreparedStatement pstmt = conn.prepareStatement(
			"INSERT INTO ENROLLEDIN(Nnumber, Dep_Code, CNumber, Section_Number , CS_Year, Semester, Grade) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
	
	System.out.print("\nEnter Student Nnumber: ");
	String Nnumber = GetNnumber();
	
	System.out.print("\nEnter Department Code: ");
	String Dep_Code = DepCode();
	
	System.out.print("\nEnter Course Number: ");
	String CNumber = CourseNumber();
	
	System.out.print("\nEnter Section Number: ");
	int Section_Number = Main.getInt();
	while (Section_Number==-1) {
		System.out.println("Enter a valid Section Number");
		Section_Number=Main.getInt();
	}
		
	System.out.print("\nEnter Year: ");
	int year = Main.getInt();
	while (year==-1) {
		System.out.println("Enter a valid year");
		year=Main.getInt();
	}
	
	System.out.print("\nEnter Semester: ");
	String Semester = Semester();

	
		
	pstmt.setString(1, Nnumber);
	pstmt.setString(2, Dep_Code);
	pstmt.setString(3, CNumber);
	pstmt.setInt(4, Section_Number);
	pstmt.setInt(5,year);
	pstmt.setString(6, Semester);
    pstmt.setInt(7, -1);
	
	try {
	int NumRows = pstmt.executeUpdate();
	System.out.println("\n" + NumRows + " Course inserted");
	}
	catch(SQLIntegrityConstraintViolationException e){
		System.out.println("That Course section doesn't exist or the Student is already signed up for another Section this semester.");
	}
}

public static void InsertGrade(Connection conn) throws SQLException{
	
	System.out.print("\nEnter Student Nnumber: ");
	String Nnumber = GetNnumber();
	
	System.out.print("\nEnter Department Code: ");
	String Dep_Code = DepCode();
	
	System.out.print("\nEnter Course Number: ");
	String CNumber = CourseNumber();
	
	System.out.print("\nEnter Section Number: ");
	int Section_Number = Main.getInt();
	while (Section_Number==-1) {
		System.out.println("Enter a valid Section Number");
		Section_Number=Main.getInt();
	}
		
	System.out.print("\nEnter Year: ");
	int year = Main.getInt();
	while (year==-1) {
		System.out.println("Enter a valid year");
		year=Main.getInt();
	}
	
	System.out.print("\nEnter Semester: ");
	String Semester = Semester();

	System.out.print("\nEnter Letter Grade (Not Required): ");
	String Grade = Main.getString();
	while(Grade.compareTo("")==0||!(Grade.compareToIgnoreCase("A")==0||Grade.compareToIgnoreCase("A-")==0||Grade.compareToIgnoreCase("B+")==0||Grade.compareToIgnoreCase("B")==0||Grade.compareToIgnoreCase("B-")==0
			||Grade.compareToIgnoreCase("C+")==0||Grade.compareToIgnoreCase("C")==0||Grade.compareToIgnoreCase("C")==0||Grade.compareToIgnoreCase("D")==0||Grade.compareToIgnoreCase("F")==0||Grade.compareToIgnoreCase("FA")==0)) {
		System.out.println("Enter a valid Letter Grade");
		Grade=Main.getString();
	}
	double Points=0;
	if (Grade.compareToIgnoreCase("A")==0)
		Points=4.0;
	if (Grade.compareToIgnoreCase("A-")==0)
		Points=3.7;
	if (Grade.compareToIgnoreCase("B+")==0)
		Points=3.3;
	if (Grade.compareToIgnoreCase("B")==0)
		Points=3.0;
	if (Grade.compareToIgnoreCase("B-")==0)
		Points=2.7;
	if (Grade.compareToIgnoreCase("C+")==0)
		Points=2.3;
	if (Grade.compareToIgnoreCase("C")==0)
		Points=2.0;
	if (Grade.compareToIgnoreCase("D")==0)
		Points=1.0;
	
	Statement stmt = conn.createStatement();
	String q = "UPDATE ENROLLEDIN SET Grade= "+Points+" WHERE Nnumber= '" + Nnumber +"' and Dep_Code='"+Dep_Code+"' and CNumber='"+CNumber+"' and"
			+ " Section_Number= "+ Section_Number+" and CS_Year= "+year+" and Semester= '"+Semester+"'";

	
	try {
	ResultSet NumRows = stmt.executeQuery(q);
	System.out.println("\nGrade inserted");
	}
	catch (Exception e) {
		System.out.println("That Enrolled Record Doesn't Exist");
	}
}


}
