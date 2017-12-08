package hillelee.doctor;

/**
 * Created by dmitriy.chebotarev@hpe.com on 12/7/2017.
 */
class IdModificationIsNotAllowed extends RuntimeException {
  
  IdModificationIsNotAllowed(String message) {
    super(message);
  }
  
}
