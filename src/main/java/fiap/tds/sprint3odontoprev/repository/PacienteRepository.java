package fiap.tds.sprint3odontoprev.repository;

import fiap.tds.sprint3odontoprev.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, String> {
}
