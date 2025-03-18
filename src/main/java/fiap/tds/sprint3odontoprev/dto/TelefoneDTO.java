package fiap.tds.sprint3odontoprev.dto;

import fiap.tds.sprint3odontoprev.enums.TipoTelefone;
import lombok.Data;

@Data
public class TelefoneDTO {

    private Long id;
    private String numero;
    private TipoTelefone tipo;
}
