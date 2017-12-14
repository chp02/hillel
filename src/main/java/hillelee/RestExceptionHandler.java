package hillelee;

import hillelee.doctor.DoctorAlreadyExistsException;
import hillelee.doctor.DoctorNotFoundException;
import hillelee.doctor.IdModificationIsNotAllowedException;
import hillelee.doctor.InvalidDoctorSpecialtyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by dmitriy.chebotarev@hpe.com on 12/14/2017.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
  @ExceptionHandler(value = InvalidDoctorSpecialtyException.class)
  protected ResponseEntity<Object> HandleInvalidSpecialty(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "Speciafied specialty is not allowed (use '/specialties' endpoint to get allowed list).";
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
  }
  
  @ExceptionHandler(value = IdModificationIsNotAllowedException.class)
  protected ResponseEntity<Object> HandleIdModifocation(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "ID modification is not allowed.";
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }
  
  @ExceptionHandler(value = DoctorNotFoundException.class)
  protected ResponseEntity<Object> HandleDoctorNotFound(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "Doctor with specified ID doesn't exist.";
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }
  
  @ExceptionHandler(value = DoctorAlreadyExistsException.class)
  protected ResponseEntity<Object> HandleDoctorAlreadyExists(RuntimeException ex, WebRequest request) {
    String bodyOfResponse = "Doctor with specified ID already exists.";
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }
  
}
