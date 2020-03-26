package entity;

import com.healthmarketscience.jackcess.util.ErrorHandler.Location;

public class Battery {
	
	private String idBattery;
	private Double rateBattery;
	private entity.Location location;
	private Double drivingDistancePossi;//check it again 

	public Battery(String idBattery, Double rateBattery, entity.Location location, Double drivingDistancePossi) {
		super();
		this.idBattery = idBattery;
		this.rateBattery = rateBattery;
		this.location = location;
		this.drivingDistancePossi = drivingDistancePossi;
	}
	public String getIdBattery() {
		return idBattery;
	}
	public void setIdBattery(String idBattery) {
		this.idBattery = idBattery;
	}
	public Double getRateBattery() {
		return rateBattery;
	}
	public void setRateButtery(Double rateBattery) {
		this.rateBattery = rateBattery;
	}
	public entity.Location getLocation() {
		return location;
	}
	public void setLocation(entity.Location location) {
		this.location = location;
	}
	public Double getDrivingDistancePossi() {
		return drivingDistancePossi;
	}
	public void setDrivingDistancePossi(Double drivingDistancePossi) {
		this.drivingDistancePossi = drivingDistancePossi;
	}
	@Override
	public String toString() {
		return "Buttery [idBattery=" + idBattery + ", rateBattery=" + rateBattery + ", location=" + location
				+ ", drivingDistancePossi=" + drivingDistancePossi + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drivingDistancePossi == null) ? 0 : drivingDistancePossi.hashCode());
		result = prime * result + ((idBattery == null) ? 0 : idBattery.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((rateBattery == null) ? 0 : rateBattery.hashCode());
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
		Battery other = (Battery) obj;
		if (drivingDistancePossi == null) {
			if (other.drivingDistancePossi != null)
				return false;
		} else if (!drivingDistancePossi.equals(other.drivingDistancePossi))
			return false;
		if (idBattery == null) {
			if (other.idBattery != null)
				return false;
		} else if (!idBattery.equals(other.idBattery))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (rateBattery == null) {
			if (other.rateBattery != null)
				return false;
		} else if (!rateBattery.equals(other.rateBattery))
			return false;
		return true;
	}
	
	
	/**
	 * @param idBattery
	 * @param rateButtery
	 * @param location2
	 * @param drivingDistancePossi
	 */

}
