package hillelee.doctor;

/**
 * Created by dmitriy.chebotarev@hpe.com on 12/7/2017.
 */
class InvalidDoctorSpecialty extends RuntimeException {
  
  InvalidDoctorSpecialty(String message) {
    super(message);
  }
  
}
