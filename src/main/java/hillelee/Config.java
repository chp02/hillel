package hillelee;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by dmitriy.chebotarev@hpe.com on 12/7/2017.
 */
@Configuration
@ConfigurationProperties("clinic-info")
@Data
public class Config {
  
  private List<String> specialties;
  
}
