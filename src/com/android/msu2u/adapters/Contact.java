package com.android.msu2u.adapters;

public class Contact implements Comparable<Contact> {

	// JSON Node names
	String Person_ID;
	String Position_1;
	String Position_2;
	String Name_Prefix;
	String FName;
	String Middle;
	String LName;
	String Email;
	String Suffix;
	String Dept_1;
	String Dept_2;
	String Office_Bldg_1;
	String Office_Bldg_2;
	String Office_Rm_Num_1;
	String Office_Rm_Num_2;
	String Phone1;
	String Fax1;
	String Phone2;
	String Fax2;
	String Link_To_More_Info;
	String Picture;
	String Website_Link;

	/** (non-Javadoc)
	 * Return a string for debugging purposes
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(FName);
		builder.append('\n');
		builder.append(LName);
		builder.append('\n');
		builder.append(Website_Link);
		builder.append('\n');
		builder.append(Picture);
		builder.append('\n');
		builder.append(Office_Bldg_1);
		
		return builder.toString();
	}
	/**
	 * @return the person_ID
	 */
	public String getPerson_ID() {
		return Person_ID;
	}

	/**
	 * @return the Position_1
	 */
	public String getPosition_1() {
		return Position_1;
	}

	/**
	 * @return the Position_2
	 */
	public String getPosition_2() {
		return Position_2;
	}

	/**
	 * @return the name_Prefix
	 */
	public String getName_Prefix() {
		return Name_Prefix + " ";
	}

	/**
	 * @return the fName
	 */
	public String getFName() {
		return FName;
	}

	/**
	 * @return the middle
	 */
	public String getMiddle() {
		return Middle;
	}

	/**
	 * @return the lName
	 */
	public String getLName() {
		return LName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return Suffix;
	}

	/**
	 * @return the Dept_1
	 */
	public String getDept_1() {
		return Dept_1;
	}

	/**
	 * @return the Dept_2
	 */
	public String getDept_2() {
		return Dept_2;
	}

	/**
	 * @return the Office_Bldg_1
	 */
	public String getOffice_Bldg_1() {
		return Office_Bldg_1;
	}

	/**
	 * @return the Office_Bldg_2
	 */
	public String getOffice_Bldg_2() {
		return Office_Bldg_2;
	}

	/**
	 * @return the office_Rm_Num_1
	 */
	public String getOffice_Rm_Num_1() {
		return Office_Rm_Num_1;
	}

	/**
	 * @return the office_Rm_Num_2
	 */
	public String getOffice_Rm_Num_2() {
		return Office_Rm_Num_2;
	}

	/**
	 * @return the phone1
	 */
	public String getPhone1() {
		return Phone1;
	}

	/**
	 * @return the fax1
	 */
	public String getFax1() {
		return Fax1;
	}

	/**
	 * @return the phone2
	 */
	public String getPhone2() {
		return Phone2;
	}

	/**
	 * @return the fax2
	 */
	public String getFax2() {
		return Fax2;
	}

	/**
	 * @return the link_To_More_Info
	 */
	public String getLink_To_More_Info() {
		return Link_To_More_Info;
	}

	/**
	 * @return the picture
	 */
	public String getPicture() {
		return Picture;
	}

	/**
	 * @return the website_Link
	 */
	public String getWebsite_Link() {
		return Website_Link;
	}

	/*
	 * Comparable allows us to sort our arrays of Contacts based on certain
	 * fields
	 */
	@Override
	public int compareTo(Contact p) {
		int last = this.LName.compareTo(p.LName);
		return last == 0 ? this.FName.compareTo(p.FName) : last;
	}

}
