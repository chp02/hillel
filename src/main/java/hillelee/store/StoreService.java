package hillelee.store;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by JavaEE on 12/23/2017.
 */
@Service
@AllArgsConstructor
@Slf4j
public class StoreService {

    private final MedicineRepository medicineRepository;

    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    public void add(String name, Integer quantity) {
        Optional<Medicine> mayBeMedicine = medicineRepository.findByName(name);
        // orElse creates Medicine even when Optional is not empty, so we use orElseGet with lambda
        Medicine medicine = mayBeMedicine.orElseGet(() -> new Medicine(name, 0));
        medicine.setQuantity(medicine.getQuantity() + quantity);
        medicineRepository.save(medicine);
    }

    public void decrement(String medicineName, Integer quantity) {
        log.warn("in decrement" + Thread.currentThread().getName());

        Medicine medicine = medicineRepository
                .findByName(medicineName)
                .filter(m -> m.getQuantity() >= quantity)
                .orElseThrow(NoSuchMedicineException::new);

        log.warn("read version: " + medicine.getVersion());

        medicine.setQuantity(medicine.getQuantity() - quantity);
        medicineRepository.save(medicine);
    }

}
