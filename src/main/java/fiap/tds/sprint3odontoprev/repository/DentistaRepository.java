package fiap.tds.sprint3odontoprev.repository;

import fiap.tds.sprint3odontoprev.entity.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DentistaRepository extends JpaRepository<Dentista, String> {

}
