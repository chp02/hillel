package hillelee.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by dmitriy.chebotarev@hpe.com on 11/27/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Doctor
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private String specialty;
  
  public Doctor(String name, String specialty) {
    this.name = name;
    this.specialty = specialty;
  }
  
}
