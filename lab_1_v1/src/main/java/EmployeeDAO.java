import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {

    /**
     * Zwraca pracownika o podanym id
     */

    Optional<Employee> findOne(Integer id);

    /**
     * Usuwa pracownika
     */
    void delete(Employee employee);

    /**
     * Zwraca wszystkich pracowników
     */

    List<Employee> findAll();

    /**
     * Zwraca pracownika o podanym nazwisku
     */

    Optional<Employee> findByName(String name);

    /**
     * Dodaje, jeśli brak, lub aktualizuje pracownika
     */

    void save(Employee employee);
}
