package hillelee.clinic;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JavaEE on 12/2/2017.
 */
@RestController
@AllArgsConstructor
public class ClinicController {

    private final ClinicInfo clinicInfo;

    @GetMapping("/clinic/info")
    public ClinicInfo getClinicInfo() {
        return clinicInfo;
    }

}

@Data
@Configuration
@ConfigurationProperties("clinic-info")
class ClinicInfo {
    private String name;
    private String workingHours;
}
