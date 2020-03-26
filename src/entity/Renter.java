package entity;

import java.sql.Date;

public class Renter {

	private String renterLName;
	private String renterFName;
	private String idRenter;
	private String email;
	private int phoneNum;
	private Date dateOfBirth; 
	private Boolean charger;//YES OR NO 
	
	
	
		
	/**
	 * @param renterLName
	 * @param renterFName
	 * @param idRenter
	 * @param email
	 * @param phoneNum
	 * @param dateOfBirth
	 * @param charger
	 */
	public Renter(String renterLName, String renterFName, String idRenter, String email, int phoneNum,
			Date dateOfBirth, Boolean charger) {
		this.renterLName = renterLName;
		this.renterFName = renterFName;
		this.idRenter = idRenter;
		this.email = email;
		this.phoneNum = phoneNum;
		this.dateOfBirth = dateOfBirth;
		this.charger = charger;
	}
	public String getRenterLName() {
		return renterLName;
	}
	public void setRenterLName(String renterLName) {
		this.renterLName = renterLName;
	}
	public String getRenterFName() {
		return renterFName;
	}
	public void setRenterFName(String renterFName) {
		this.renterFName = renterFName;
	}
	public String getIdRenter() {
		return idRenter;
	}
	public void setIdRenter(String idRenter) {
		this.idRenter = idRenter;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Boolean getCharger() {
		return charger;
	}
	public void setCharger(Boolean charger) {
		this.charger = charger;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((charger == null) ? 0 : charger.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idRenter == null) ? 0 : idRenter.hashCode());
		result = prime * result + phoneNum;
		result = prime * result + ((renterFName == null) ? 0 : renterFName.hashCode());
		result = prime * result + ((renterLName == null) ? 0 : renterLName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Renter other = (Renter) obj;
		if (charger == null) {
			if (other.charger != null)
				return false;
		} else if (!charger.equals(other.charger))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idRenter == null) {
			if (other.idRenter != null)
				return false;
		} else if (!idRenter.equals(other.idRenter))
			return false;
		if (phoneNum != other.phoneNum)
			return false;
		if (renterFName == null) {
			if (other.renterFName != null)
				return false;
		} else if (!renterFName.equals(other.renterFName))
			return false;
		if (renterLName == null) {
			if (other.renterLName != null)
				return false;
		} else if (!renterLName.equals(other.renterLName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Renter [renterLName=" + renterLName + ", renterFName=" + renterFName + ", idRenter=" + idRenter
				+ ", email=" + email + ", phoneNum=" + phoneNum + ", dateOfBirth=" + dateOfBirth + ", charger="
				+ charger + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
