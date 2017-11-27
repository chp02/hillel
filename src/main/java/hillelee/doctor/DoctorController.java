package hillelee.doctor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dmitriy.chebotarev@hpe.com on 11/27/2017.
 */
@RestController
public class DoctorController
{
  @GetMapping("/doctor")
  public Doctor get() {
    return new Doctor(1, "Vasia", "zubnyuk");
  }
}
