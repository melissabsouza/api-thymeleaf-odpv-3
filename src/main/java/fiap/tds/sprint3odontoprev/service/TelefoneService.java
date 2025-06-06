package fiap.tds.sprint3odontoprev.service;

import fiap.tds.sprint3odontoprev.dto.TelefoneDTO;
import fiap.tds.sprint3odontoprev.entity.Telefone;
import fiap.tds.sprint3odontoprev.repository.TelefoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TelefoneService {
    @Autowired
    private final TelefoneRepository telefoneRepository;

    public TelefoneDTO saveTelefone(TelefoneDTO telefoneDTO){
        Telefone telefone = toEntity(telefoneDTO);

        if(telefoneDTO.getId()==null){
            System.out.println(telefone.getNumero());

            telefone = telefoneRepository.save(telefone);
        } else{
            TelefoneDTO byId = this.findById(telefoneDTO.getId());
            byId.setNumero(telefoneDTO.getNumero());
            byId.setTipo(telefoneDTO.getTipo());

            System.out.println(telefone.getNumero());

            telefone = telefoneRepository.save(toEntity(byId));
        }
        return toDto(telefone);
    }

    public List<TelefoneDTO> findAll(){
        List<Telefone> list = telefoneRepository.findAll();
        List<TelefoneDTO> dtos = list.stream().map(TelefoneService::toDto).toList();
        return dtos;
    }

    public void deleteById(Long id){
        System.out.println("telefone deletado");
        telefoneRepository.deleteById(id);
    }

    public TelefoneDTO findById(Long id){
        Optional<Telefone> byId = telefoneRepository.findById(id);
        if(byId.isPresent()){
            return toDto(byId.get());
        }
        throw new RuntimeException("id não encontrado");
    }

    private static Telefone toEntity(TelefoneDTO telefoneDTO){
        Telefone telefone = new Telefone();
        telefone.setId(telefoneDTO.getId());
        telefone.setNumero(telefoneDTO.getNumero());
        telefone.setTipo(telefoneDTO.getTipo());
        return telefone;
    }

    private static TelefoneDTO toDto(Telefone telefone){
        TelefoneDTO telefoneDTO = new TelefoneDTO();
        telefoneDTO.setId(telefone.getId());
        telefoneDTO.setNumero(telefone.getNumero());
        telefoneDTO.setTipo(telefone.getTipo());
        return telefoneDTO;

    }
}
