package fiap.tds.sprint3odontoprev.repository;

import fiap.tds.sprint3odontoprev.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
