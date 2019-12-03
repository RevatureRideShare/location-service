package com.revature.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * This object's purpose is to hold a training location. Its fields consist of: trainingLocationID:
 * an id used to identify each training location; trainingLocationName: a string used to hold the
 * name of a training location.
 * 
 * @author Jane Shin
 * @author Roberto Rodriguez
 */


@Entity
@Table(name = "training_location")
public class TrainingLocation {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TLI_SEQ")
  @SequenceGenerator(name = "TLI_SEQ", sequenceName = "training_location_id_seq",
      allocationSize = 1)
  @Column(name = "training_location_id")
  private int trainingLocationID;

  @NotEmpty(message = "Training Location Name cannot be empty!")
  @Column(name = "training_location_name")
  private String trainingLocationName;

  public TrainingLocation() {
    super();
  }

  /**
   * The specific fields inside of the trainingLocation object.
   * 
   * @param trainingLocationID an id pertaining to the object
   * @param trainingLocationName the literal name to the training location
   */
  public TrainingLocation(int trainingLocationID, @NotEmpty String trainingLocationName) {
    super();
    this.trainingLocationID = trainingLocationID;
    this.trainingLocationName = trainingLocationName;
  }

  public int getTrainingLocationID() {
    return trainingLocationID;
  }

  public void setTrainingLocationID(int trainingLocationID) {
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
    result = prime * result + trainingLocationID;
    result =
        prime * result + ((trainingLocationName == null) ? 0 : trainingLocationName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    TrainingLocation other = (TrainingLocation) obj;
    if (trainingLocationID != other.trainingLocationID) {
      return false;
    }
    if (trainingLocationName == null) {
      if (other.trainingLocationName != null) {
        return false;
      }
    } else if (!trainingLocationName.equals(other.trainingLocationName)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "TrainingLocation [trainingLocationID=" + trainingLocationID + ", trainingLocationName="
        + trainingLocationName + "]";
  }

}
