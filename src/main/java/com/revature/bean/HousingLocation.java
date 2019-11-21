package com.revature.bean;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/*
 * @Authors: Roberto Rodriguez, Erik Haklar, Jane Shin. This object's purpose is to hold a housing
 * location, its fields consist of: locationID: a location id in which the object can be grabbed by
 * this id address1: an address pertaining to that location address2: a second address which is
 * optional (apt number, building number, etc) city: a city for narrowing down specific locations
 * state: a state for narrowing down specific locations zipCode: a zipcode for narrowing down
 * specific locations housingLocationName: housing location name to identify a location with
 * trainingLocation: a TrainingLocation object in which that consists of the training location name
 * and its respective training location id
 */
@Entity
@Table(name = "housing_location")
public class HousingLocation {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HLI_SEQ")
  @SequenceGenerator(name = "HLI_SEQ", sequenceName = "location_id_seq", allocationSize = 1)
  @Column(name = "location_id")
  private UUID locationID;

  @NotEmpty
  @Column(name = "address_1")
  private String address1;

  @Column(name = "address_2")
  private String address2;

  @NotEmpty
  @Size(max = 50)
  @Column(name = "city")
  private String city;

  @NotEmpty
  @Size(max = 50)
  @Column(name = "state")
  private String state;

  @NotEmpty
  @Size(max = 10)
  @Column(name = "zip_code")
  private String zipCode;

  @NotEmpty
  @Column(name = "housing_location_name")
  private String housingLocationName;

  @ManyToOne
  @JoinColumn(name = "training_location_id")
  private TrainingLocation trainingLocation;

  public HousingLocation() {
    super();

  }

  /**
   * The specific fields inside the housingLocation constructor.
   * 
   * @param locationID a location id in which the object can be grabbed by this id
   * @param address1 an address pertaining to a building
   * @param address2 optional field for apt number inside the building
   * @param city the city of the location object
   * @param state the state in the city
   * @param zipCode specific zipcode pertaining to the location
   * @param housingLocationName the actual name of the location
   * @param trainingLocation the trainingLocation object that this housing location is tied to
   */
  public HousingLocation(UUID locationID, @NotEmpty String address1, String address2,
      @NotEmpty @Size(max = 50) String city, @NotEmpty @Size(max = 50) String state,
      @NotEmpty @Size(max = 10) String zipCode, @NotEmpty String housingLocationName,
      TrainingLocation trainingLocation) {
    super();
    this.locationID = locationID;
    this.address1 = address1;
    this.address2 = address2;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.housingLocationName = housingLocationName;
    this.trainingLocation = trainingLocation;
  }

  public UUID getLocationID() {
    return locationID;
  }

  public void setLocationID(UUID locationID) {
    this.locationID = locationID;
  }

  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getHousingLocationName() {
    return housingLocationName;
  }

  public void setHousingLocationName(String housingLocationName) {
    this.housingLocationName = housingLocationName;
  }

  public TrainingLocation getTrainingLocation() {
    return trainingLocation;
  }

  public void setTrainingLocation(TrainingLocation trainingLocation) {
    this.trainingLocation = trainingLocation;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address1 == null) ? 0 : address1.hashCode());
    result = prime * result + ((address2 == null) ? 0 : address2.hashCode());
    result = prime * result + ((city == null) ? 0 : city.hashCode());
    result = prime * result + ((housingLocationName == null) ? 0 : housingLocationName.hashCode());
    result = prime * result + ((locationID == null) ? 0 : locationID.hashCode());
    result = prime * result + ((state == null) ? 0 : state.hashCode());
    result = prime * result + ((trainingLocation == null) ? 0 : trainingLocation.hashCode());
    result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
    HousingLocation other = (HousingLocation) obj;
    if (address1 == null) {
      if (other.address1 != null) {
        return false;
      }
    } else if (!address1.equals(other.address1)) {
      return false;
    }
    if (address2 == null) {
      if (other.address2 != null) {
        return false;
      }
    } else if (!address2.equals(other.address2)) {
      return false;
    }
    if (city == null) {
      if (other.city != null) {
        return false;
      }
    } else if (!city.equals(other.city)) {
      return false;
    }
    if (housingLocationName == null) {
      if (other.housingLocationName != null) {
        return false;
      }
    } else if (!housingLocationName.equals(other.housingLocationName)) {
      return false;
    }
    if (locationID == null) {
      if (other.locationID != null) {
        return false;
      }
    } else if (!locationID.equals(other.locationID)) {
      return false;
    }
    if (state == null) {
      if (other.state != null) {
        return false;
      }
    } else if (!state.equals(other.state)) {
      return false;
    }
    if (trainingLocation == null) {
      if (other.trainingLocation != null) {
        return false;
      }
    } else if (!trainingLocation.equals(other.trainingLocation)) {
      return false;
    }
    if (zipCode == null) {
      if (other.zipCode != null) {
        return false;
      }
    } else if (!zipCode.equals(other.zipCode)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "HousingLocation [locationID=" + locationID + ", address1=" + address1 + ", address2="
        + address2 + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode
        + ", housingLocationName=" + housingLocationName + ", trainingLocation=" + trainingLocation
        + "]";
  }

}
