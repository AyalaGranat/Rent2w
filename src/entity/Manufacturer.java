package entity;

public class Manufacturer {
	 private String idManufacturer;
	 private String lNameManufactur;
	 private String fNameManufactur;
	 private String phoneManufactur; // do it int ? int renter the phone num is string 
	 
	 
	 
	 
	/**
	 * @param idManufacturer
	 * @param lNameManufactur
	 * @param fNameManufactur
	 * @param phoneManufactur
	 */
	public Manufacturer(String idManufacturer, String lNameManufactur, String fNameManufactur, String phoneManufactur) {
		this.idManufacturer = idManufacturer;
		this.lNameManufactur = lNameManufactur;
		this.fNameManufactur = fNameManufactur;
		this.phoneManufactur = phoneManufactur;
	}
	public String getIdManufacturer() {
		return idManufacturer;
	}
	public void setIdManufacturer(String idManufacturer) {
		this.idManufacturer = idManufacturer;
	}
	public String getlNameManufactur() {
		return lNameManufactur;
	}
	public void setlNameManufactur(String lNameManufactur) {
		this.lNameManufactur = lNameManufactur;
	}
	public String getfNameManufactur() {
		return fNameManufactur;
	}
	public void setfNameManufactur(String fNameManufactur) {
		this.fNameManufactur = fNameManufactur;
	}
	public String getPhoneManufactur() {
		return phoneManufactur;
	}
	public void setPhoneManufactur(String phoneManufactur) {
		this.phoneManufactur = phoneManufactur;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fNameManufactur == null) ? 0 : fNameManufactur.hashCode());
		result = prime * result + ((idManufacturer == null) ? 0 : idManufacturer.hashCode());
		result = prime * result + ((lNameManufactur == null) ? 0 : lNameManufactur.hashCode());
		result = prime * result + ((phoneManufactur == null) ? 0 : phoneManufactur.hashCode());
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
		Manufacturer other = (Manufacturer) obj;
		if (fNameManufactur == null) {
			if (other.fNameManufactur != null)
				return false;
		} else if (!fNameManufactur.equals(other.fNameManufactur))
			return false;
		if (idManufacturer == null) {
			if (other.idManufacturer != null)
				return false;
		} else if (!idManufacturer.equals(other.idManufacturer))
			return false;
		if (lNameManufactur == null) {
			if (other.lNameManufactur != null)
				return false;
		} else if (!lNameManufactur.equals(other.lNameManufactur))
			return false;
		if (phoneManufactur == null) {
			if (other.phoneManufactur != null)
				return false;
		} else if (!phoneManufactur.equals(other.phoneManufactur))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Manufacturer [idManufacturer=" + idManufacturer + ", lNameManufactur=" + lNameManufactur
				+ ", fNameManufactur=" + fNameManufactur + ", phoneManufactur=" + phoneManufactur + "]";
	}
	 
	 
	
	 
	 
	
	
}
