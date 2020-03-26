package entity;

public class Vehicle {
	protected String idVehicle;
	protected TypeVehicle type;
	protected Status status;
	protected String LastParkingStop;
	
	
	
	public Vehicle(String idVehicle, TypeVehicle type, entity.Status status, String lastParkingStop) {
		super();
		this.idVehicle = idVehicle;
		this.type = type;
		this.status = status;
		LastParkingStop = lastParkingStop;
	}
	public Vehicle(String idVehicle2, TypeVehicle type2, String lastParkingStop2) {
		this.idVehicle = idVehicle2;
		this.type = type2;
		LastParkingStop = lastParkingStop2;
	}
	public Vehicle(String idVehicle2) {
		this.idVehicle = idVehicle2;
	}
	public String getIdVehicle() {
		return idVehicle;
	}
	public void setIdVehicle(String idVehicle) {
		this.idVehicle = idVehicle;
	}
	public TypeVehicle getType() {
		return type;
	}
	public void setType(TypeVehicle type) {
		this.type = type;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getLastParkingStop() {
		return LastParkingStop;
	}
	public void setLastParkingStop(String lastParkingStop) {
		LastParkingStop = lastParkingStop;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((LastParkingStop == null) ? 0 : LastParkingStop.hashCode());
		result = prime * result + ((idVehicle == null) ? 0 : idVehicle.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Vehicle other = (Vehicle) obj;
		if (LastParkingStop == null) {
			if (other.LastParkingStop != null)
				return false;
		} else if (!LastParkingStop.equals(other.LastParkingStop))
			return false;
		if (idVehicle == null) {
			if (other.idVehicle != null)
				return false;
		} else if (!idVehicle.equals(other.idVehicle))
			return false;
		if (status != other.status)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Vehicle: number Vehicle: " + idVehicle + ", type:" + type + ", Parking Stop: "
				+ LastParkingStop +"";
	}
	
	
	
	
	

}

