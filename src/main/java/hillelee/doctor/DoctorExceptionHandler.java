package hillelee.doctor;

import hillelee.doctor.exceptions.DoctorAlreadyExistsException;
import hillelee.doctor.exceptions.DoctorNotFoundException;
import hillelee.doctor.exceptions.IdModificationIsNotAllowedException;
import hillelee.doctor.exceptions.InvalidDoctorSpecialtyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

/**
 * Created by dmitriy.chebotarev@hpe.com on 12/14/2017.
 */
@ControllerAdvice
public class DoctorExceptionHandler extends ResponseEntityExceptionHandler
{
  @ExceptionHandler(value = InvalidDoctorSpecialtyException.class)
  protected ResponseEntity<Object> HandleInvalidSpecialty(RuntimeException ex, WebRequest request) {
    String body = "Speciafied specialty is not allowed (use '/specialties' endpoint to get allowed list).";
    return handleExceptionInternal(ex, body, new HttpHeaders(), NOT_ACCEPTABLE, request);
  }
  
  @ExceptionHandler(value = IdModificationIsNotAllowedException.class)
  protected ResponseEntity<Object> HandleIdModifocation(RuntimeException ex, WebRequest request) {
    String body = "ID modification is not allowed.";
    return handleExceptionInternal(ex, body, new HttpHeaders(), BAD_REQUEST, request);
  }
  
  @ExceptionHandler(value = DoctorNotFoundException.class)
  protected ResponseEntity<Object> HandleDoctorNotFound(RuntimeException ex, WebRequest request) {
    String body = "Doctor with specified ID doesn't exist.";
    return handleExceptionInternal(ex, body, new HttpHeaders(), NOT_FOUND, request);
  }
  
  @ExceptionHandler(value = DoctorAlreadyExistsException.class)
  protected ResponseEntity<Object> HandleDoctorAlreadyExists(RuntimeException ex, WebRequest request) {
    String body = "Doctor with specified ID already exists.";
    return handleExceptionInternal(ex, body, new HttpHeaders(), CONFLICT, request);
  }
  
}
