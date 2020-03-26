package entity;

import java.sql.Date;



public class ElectricVehicle extends Vehicle {
	Date dateOfPro;
	String idManufacturer;
	Double drivingDistanceForVehicle;
	String idBattery;
	
	public ElectricVehicle(String idVehicle, TypeVehicle type, entity.Status status, String lastParkingStop,
			java.util.Date dateOfProdata, String idManufacturer, Double drivingDistanceForVehicle, String idBattery) {
		super(idVehicle, type, status, lastParkingStop);
		this.dateOfPro = (Date) dateOfProdata;
		this.idManufacturer = idManufacturer;
		this.drivingDistanceForVehicle = drivingDistanceForVehicle;
		this.idBattery = idBattery;
	}

	public ElectricVehicle(String idVehicle, java.util.Date dateOfPro1, String codeBattery, String codeManufacturer1) {
		super(idVehicle);
		
		this.dateOfPro = (Date) dateOfPro1;
		this.idBattery = codeBattery;
		this.idManufacturer = codeManufacturer1;
	}

	public Date getDateOfPro() {
		return dateOfPro;
	}

	public void setDateOfPro(Date dateOfPro) {
		this.dateOfPro = dateOfPro;
	}

	public String getIdManufacturer() {
		return idManufacturer;
	}

	public void setIdManufacturer(String idManufacturer) {
		this.idManufacturer = idManufacturer;
	}

	public Double getDrivingDistanceForVehicle() {
		return drivingDistanceForVehicle;
	}

	public void setDrivingDistanceForVehicle(Double drivingDistanceForVehicle) {
		this.drivingDistanceForVehicle = drivingDistanceForVehicle;
	}

	public String getIdBattery() {
		return idBattery;
	}

	public void setIdBattery(String idBattery) {
		this.idBattery = idBattery;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dateOfPro == null) ? 0 : dateOfPro.hashCode());
		result = prime * result + ((drivingDistanceForVehicle == null) ? 0 : drivingDistanceForVehicle.hashCode());
		result = prime * result + ((idBattery == null) ? 0 : idBattery.hashCode());
		result = prime * result + ((idManufacturer == null) ? 0 : idManufacturer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElectricVehicle other = (ElectricVehicle) obj;
		if (dateOfPro == null) {
			if (other.dateOfPro != null)
				return false;
		} else if (!dateOfPro.equals(other.dateOfPro))
			return false;
		if (drivingDistanceForVehicle == null) {
			if (other.drivingDistanceForVehicle != null)
				return false;
		} else if (!drivingDistanceForVehicle.equals(other.drivingDistanceForVehicle))
			return false;
		if (idBattery == null) {
			if (other.idBattery != null)
				return false;
		} else if (!idBattery.equals(other.idBattery))
			return false;
		if (idManufacturer == null) {
			if (other.idManufacturer != null)
				return false;
		} else if (!idManufacturer.equals(other.idManufacturer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return " Vehicle : number Vehicle: " + idVehicle + " type: " + type + " Last Parking Stop: "
				+ LastParkingStop +" date of Production: " + dateOfPro + " id Manufacturer: " + idManufacturer
						+ " driving Distance For Vehicle: " + drivingDistanceForVehicle + " idBattery: " + idBattery;
	}




	
}
