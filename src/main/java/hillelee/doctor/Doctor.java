package hillelee.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
  @ElementCollection(fetch = FetchType.EAGER)
  private List<String> specialties;
  
  public Doctor(String name, List<String> specialties) {
    this.name = name;
    this.specialties = specialties;
  }
  
}
