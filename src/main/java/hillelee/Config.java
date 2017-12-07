package hillelee;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitriy.chebotarev@hpe.com on 12/7/2017.
 */
@Configuration
@ConfigurationProperties(prefix="clinic-info")
public class Config {
  
  private List<String> specialties = new ArrayList<>();
  
  public List<String> getSpecialties() {
    return specialties;
  }
  
}
