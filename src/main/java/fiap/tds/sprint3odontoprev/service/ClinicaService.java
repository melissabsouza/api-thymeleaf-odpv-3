package fiap.tds.sprint3odontoprev.service;

import fiap.tds.sprint3odontoprev.dto.ClinicaDTO;
import fiap.tds.sprint3odontoprev.dto.EnderecoDTO;
import fiap.tds.sprint3odontoprev.dto.TelefoneDTO;
import fiap.tds.sprint3odontoprev.dto.UsuarioDTO;
import fiap.tds.sprint3odontoprev.entity.Clinica;
import fiap.tds.sprint3odontoprev.entity.Endereco;
import fiap.tds.sprint3odontoprev.entity.Telefone;
import fiap.tds.sprint3odontoprev.entity.Usuario;
import fiap.tds.sprint3odontoprev.repository.ClinicaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClinicaService {
    @Autowired
    private final ClinicaRepository clinicaRepository;
    private final EnderecoService enderecoService;
    private final TelefoneService telefoneService;
    private final UsuarioService usuarioService;

    public ClinicaDTO saveClinica(ClinicaDTO clinicaDTO) {
        Clinica clinica = toEntity(clinicaDTO);

        if(clinicaDTO.getCnpj()==null){
            clinica = clinicaRepository.save(clinica);
        } else{
            ClinicaDTO byId = this.findById(clinicaDTO.getCnpj());
            byId.setNome(clinicaDTO.getNome());
            byId.setUsuario(clinicaDTO.getUsuario());
            byId.setEndereco(clinicaDTO.getEndereco());
            byId.setTelefone(clinicaDTO.getTelefone());


            clinica = clinicaRepository.save(toEntity(byId));
        }
        return toDto(clinica);
    }

    public List<ClinicaDTO> findAll(){
        List<Clinica> list = clinicaRepository.findAll();
        List<ClinicaDTO> dtos = list.stream().map(ClinicaService::toDto).toList();
        return dtos;
    }

    public void deleteById(String cnpj){
        System.out.println("clinica deletada");
        clinicaRepository.deleteById(cnpj);
    }

    public ClinicaDTO findById(String cnpj){
        Optional<Clinica> byId = clinicaRepository.findById(cnpj);
        if(byId.isPresent()){
            return toDto(byId.get());
        }
        throw new RuntimeException("cnpj não encontrado");
    }

    public Clinica toEntity(ClinicaDTO clinicaDTO) {
        Clinica clinica = new Clinica();
        clinica.setCnpj(clinicaDTO.getCnpj());
        clinica.setNome(clinicaDTO.getNome());

        if (clinicaDTO.getUsuario() != null) {
            // Converter UsuarioDTO para Usuario (entidade)
            Usuario usuario = toEntity(clinicaDTO.getUsuario()); // Método de conversão do DTO para entidade
            clinica.setUsuario(usuario);
        }

        if (clinicaDTO.getTelefone() != null) {
            // Converter UsuarioDTO para Usuario (entidade)
            Telefone telefone = toEntity(clinicaDTO.getTelefone()); // Método de conversão do DTO para entidade
            clinica.setTelefone(telefone);
        }

        if (clinicaDTO.getEndereco() != null) {
            // Converter UsuarioDTO para Usuario (entidade)
            Endereco endereco = toEntity(clinicaDTO.getEndereco()); // Método de conversão do DTO para entidade
            clinica.setEndereco(endereco);
        }

        return clinica;
    }

    private Usuario toEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setStatus(usuarioDTO.getStatus());
        return usuario;
    }

    private Endereco toEntity(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setId(enderecoDTO.getId());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setRua(enderecoDTO.getRua());
        return endereco;
    }

    private Telefone toEntity(TelefoneDTO telefoneDTO) {
        Telefone telefone = new Telefone();
        telefone.setId(telefoneDTO.getId());
        telefone.setNumero(telefoneDTO.getNumero());
        telefone.setTipo(telefoneDTO.getTipo());
        return telefone;
    }




    public static ClinicaDTO toDto(Clinica clinica) {
        ClinicaDTO clinicaDTO = new ClinicaDTO();
        clinicaDTO.setCnpj(clinica.getCnpj());
        clinicaDTO.setNome(clinica.getNome());

        if (clinica.getUsuario() != null) {
            clinicaDTO.setUsuario(new UsuarioDTO());
            clinicaDTO.getUsuario().setId(clinica.getUsuario().getId());
            clinicaDTO.getUsuario().setEmail(clinica.getUsuario().getEmail());
            clinicaDTO.getUsuario().setStatus(clinica.getUsuario().getStatus());
        }

        if (clinica.getTelefone() != null) {
            clinicaDTO.setTelefone(new TelefoneDTO());
            clinicaDTO.getTelefone().setId(clinica.getTelefone().getId());
            clinicaDTO.getTelefone().setNumero(clinica.getTelefone().getNumero());
            clinicaDTO.getTelefone().setTipo(clinica.getTelefone().getTipo());
            // Preencha outros campos se necessário
        }

        if (clinica.getEndereco() != null) {
            clinicaDTO.setEndereco(new EnderecoDTO());
            clinicaDTO.getEndereco().setId(clinica.getEndereco().getId());
            clinicaDTO.getEndereco().setNumero(clinica.getEndereco().getNumero());
            clinicaDTO.getEndereco().setBairro(clinica.getEndereco().getBairro());
            clinicaDTO.getEndereco().setCep(clinica.getEndereco().getCep());
            clinicaDTO.getEndereco().setCidade(clinica.getEndereco().getCidade());
            clinicaDTO.getEndereco().setEstado(clinica.getEndereco().getEstado());
            clinicaDTO.getEndereco().setComplemento(clinica.getEndereco().getComplemento());
            clinicaDTO.getEndereco().setRua(clinica.getEndereco().getRua());
            // Adicione outros campos do EnderecoDTO, se necessário
        }

        return clinicaDTO;
    }
}
