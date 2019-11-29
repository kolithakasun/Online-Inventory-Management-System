package com.oop.model;


public class Employee {

	private String EmployeeID;
	
	private String Name;

	private String Designation;

	private String FacultyName;

	private String Department;

	private String Address;
	
	private String Qualifications;
	
	private String Gender;

	
	public String getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}

	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}

	
	public String getDesignation() {
		return Designation;
	}


	public void setDesignation(String designation) {
		Designation = designation;
	}


	public String getFacultyName() {
		return FacultyName;
	}


	public void setFacultyName(String facultyName) {
		FacultyName = facultyName;
	}


	public String getDepartment() {
		return Department;
	}


	public void setDepartment(String department) {
		Department = department;
	}


	public String getAddress() {
		return Address;
	}


	public void setAddress(String address) {
		Address = address;
	}


	public String getQualifications() {
		return Qualifications;
	}

	public void setQualifications(String qualifications) {
		Qualifications = qualifications;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	@Override
	public String toString() {
		
		return "Employee ID = " + EmployeeID + "\n" + "Emplyee Name = " + Name + "\n" + "Address = " + Address + "\n"
				+ "Faculty Name = " + FacultyName + "\n" + "Department = " + Department + "\n" + "Designation = "
				+ Designation + "\n" + "Qualifications = " + Qualifications + "\n" + "Gender = " + Gender;
	}
}
