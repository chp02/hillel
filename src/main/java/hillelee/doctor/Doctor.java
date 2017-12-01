package hillelee.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by dmitriy.chebotarev@hpe.com on 11/27/2017.
 */
@Data
@AllArgsConstructor
public class Doctor
{
  private Integer id;
  private String name;
  private String specialization;
  
}
