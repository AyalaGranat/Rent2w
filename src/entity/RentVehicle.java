package entity;

import java.sql.Date;

import javax.management.loading.PrivateClassLoader;

public class RentVehicle {
	
	private String idRenter;
	private String idVehicle;
	private Date startDateRentvehicle;
	private Date endDateERentVehicle;
	private Double	cost;
	/**
	 * @param idRenter
	 * @param idVehicle
	 * @param startDateRentvehicle
	 * @param endDateERentVehicle
	 * @param cost
	 */
	public RentVehicle(String idRenter, String idVehicle, Date startDateRentvehicle, Date endDateERentVehicle,
			Double cost) {
		this.idRenter = idRenter;
		this.idVehicle = idVehicle;
		this.startDateRentvehicle = startDateRentvehicle;
		this.endDateERentVehicle = endDateERentVehicle;
		this.cost = cost;
	}
	public String getIdRenter() {
		return idRenter;
	}
	public void setIdRenter(String idRenter) {
		this.idRenter = idRenter;
	}
	public String getIdVehicle() {
		return idVehicle;
	}
	public void setIdVehicle(String idVehicle) {
		this.idVehicle = idVehicle;
	}
	public Date getStartDateRentvehicle() {
		return startDateRentvehicle;
	}
	public void setStartDateRentvehicle(Date startDateRentvehicle) {
		this.startDateRentvehicle = startDateRentvehicle;
	}
	public Date getEndDateERentVehicle() {
		return endDateERentVehicle;
	}
	public void setEndDateERentVehicle(Date endDateERentVehicle) {
		this.endDateERentVehicle = endDateERentVehicle;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((endDateERentVehicle == null) ? 0 : endDateERentVehicle.hashCode());
		result = prime * result + ((idRenter == null) ? 0 : idRenter.hashCode());
		result = prime * result + ((idVehicle == null) ? 0 : idVehicle.hashCode());
		result = prime * result + ((startDateRentvehicle == null) ? 0 : startDateRentvehicle.hashCode());
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
		RentVehicle other = (RentVehicle) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (endDateERentVehicle == null) {
			if (other.endDateERentVehicle != null)
				return false;
		} else if (!endDateERentVehicle.equals(other.endDateERentVehicle))
			return false;
		if (idRenter == null) {
			if (other.idRenter != null)
				return false;
		} else if (!idRenter.equals(other.idRenter))
			return false;
		if (idVehicle == null) {
			if (other.idVehicle != null)
				return false;
		} else if (!idVehicle.equals(other.idVehicle))
			return false;
		if (startDateRentvehicle == null) {
			if (other.startDateRentvehicle != null)
				return false;
		} else if (!startDateRentvehicle.equals(other.startDateRentvehicle))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RentVehicle [idRenter=" + idRenter + ", idVehicle=" + idVehicle + ", startDateRentvehicle="
				+ startDateRentvehicle + ", endDateERentVehicle=" + endDateERentVehicle + ", cost=" + cost + "]";
	}

	
	
	
}
