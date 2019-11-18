package com.revature.bean;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


 //! Authors: Erik Haklar, Jane Shin, Roberto Rodriguez
 //! This objects purpose is to hold a training location. Its fields consist of:
 //! trainingLocationID: an id used to identify each training location
 //! trainingLocationName: a string used to hold the name of a training location


@Entity
@Table(name="training_location")
public class TrainingLocation {
	
	@Id
	@SequenceGenerator(name="TLI_SEQ", sequenceName="training_location_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "TLI_SEQ")
	@Column(name="training_location_id")
	private UUID trainingLocationID;
	
	@Column(name="training_location_name")
	@NotEmpty
	private String trainingLocationName;

	public TrainingLocation() {
		super();
		
	}

	

	public TrainingLocation(UUID trainingLocationID, @NotEmpty String trainingLocationName) {
		super();
		this.trainingLocationID = trainingLocationID;
		this.trainingLocationName = trainingLocationName;
	}



	public UUID getTrainingLocationID() {
		return trainingLocationID;
	}

	public void setTrainingLocationID(UUID trainingLocationID) {
		this.trainingLocationID = trainingLocationID;
	}

	public String getTrainingLocationName() {
		return trainingLocationName;
	}

	public void setTrainingLocationName(String trainingLocationName) {
		this.trainingLocationName = trainingLocationName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((trainingLocationID == null) ? 0 : trainingLocationID.hashCode());
		result = prime * result + ((trainingLocationName == null) ? 0 : trainingLocationName.hashCode());
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
		TrainingLocation other = (TrainingLocation) obj;
		if (trainingLocationID == null) {
			if (other.trainingLocationID != null)
				return false;
		} else if (!trainingLocationID.equals(other.trainingLocationID))
			return false;
		if (trainingLocationName == null) {
			if (other.trainingLocationName != null)
				return false;
		} else if (!trainingLocationName.equals(other.trainingLocationName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrainingLocation [trainingLocationID=" + trainingLocationID + ", trainingLocationName="
				+ trainingLocationName + "]";
	}
	
	

}
