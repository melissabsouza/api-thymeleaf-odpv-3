package fiap.tds.sprint3odontoprev.repository;

import fiap.tds.sprint3odontoprev.entity.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
}
