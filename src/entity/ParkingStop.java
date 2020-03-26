package entity;

public class ParkingStop {
	private String idParkingStop;
	private String city;
	private String street;
	private Double coorX;
	private Double coorY;
	private int capacity;
	private int correntCap;
	private int savedSpot;
	private String nameParkingStop;
	
	
	public ParkingStop(String idParkingStop, String city, String street, Double coorX, Double coorY, int capacity,
			int correntCap, int savedSpot, String nameParkingStop) {
		super();
		this.idParkingStop = idParkingStop;
		this.city = city;
		this.street = street;
		this.coorX = coorX;
		this.coorY = coorY;
		this.capacity = capacity;
		this.correntCap = correntCap;
		this.savedSpot = savedSpot;
		this.nameParkingStop = nameParkingStop;
	}
	
	public ParkingStop(String idParkingStop2, String city2, String street2, Double coorX1, Double coorY1,
			int capacity1) {
		this.idParkingStop = idParkingStop2;
		this.city = city2;
		this.street = street2;
		this.coorX = coorX1;
		this.coorY = coorY1;
		this.capacity = capacity1;
	}

	public String getIdParkingStop() {
		return idParkingStop;
	}
	public void setIdParkingStop(String idParkingStop) {
		this.idParkingStop = idParkingStop;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Double getCoorX() {
		return coorX;
	}
	public void setCoorX(Double coorX) {
		this.coorX = coorX;
	}
	public Double getCoorY() {
		return coorY;
	}
	public void setCoorY(Double coorY) {
		this.coorY = coorY;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getCorrentCap() {
		return correntCap;
	}
	public void setCorrentCap(int correntCap) {
		this.correntCap = correntCap;
	}
	public int getSavedSpot() {
		return savedSpot;
	}
	public void setSavedSpot(int savedSpot) {
		this.savedSpot = savedSpot;
	}
	public String getNameParkingStop() {
		return nameParkingStop;
	}
	public void setNameParkingStop(String nameParkingStop) {
		this.nameParkingStop = nameParkingStop;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((coorX == null) ? 0 : coorX.hashCode());
		result = prime * result + ((coorY == null) ? 0 : coorY.hashCode());
		result = prime * result + correntCap;
		result = prime * result + ((idParkingStop == null) ? 0 : idParkingStop.hashCode());
		result = prime * result + ((nameParkingStop == null) ? 0 : nameParkingStop.hashCode());
		result = prime * result + savedSpot;
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		ParkingStop other = (ParkingStop) obj;
		if (capacity != other.capacity)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (coorX == null) {
			if (other.coorX != null)
				return false;
		} else if (!coorX.equals(other.coorX))
			return false;
		if (coorY == null) {
			if (other.coorY != null)
				return false;
		} else if (!coorY.equals(other.coorY))
			return false;
		if (correntCap != other.correntCap)
			return false;
		if (idParkingStop == null) {
			if (other.idParkingStop != null)
				return false;
		} else if (!idParkingStop.equals(other.idParkingStop))
			return false;
		if (nameParkingStop == null) {
			if (other.nameParkingStop != null)
				return false;
		} else if (!nameParkingStop.equals(other.nameParkingStop))
			return false;
		if (savedSpot != other.savedSpot)
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ParkingStop [idParkingStop=" + idParkingStop + ", city=" + city + ", street=" + street + ", coorX="
				+ coorX + ", coorY=" + coorY + ", capacity=" + capacity + ", correntCap=" + correntCap + ", savedSpot="
				+ savedSpot + ", nameParkingStop=" + nameParkingStop + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
}
