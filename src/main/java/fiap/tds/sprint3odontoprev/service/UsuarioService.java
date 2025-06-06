package fiap.tds.sprint3odontoprev.service;

import fiap.tds.sprint3odontoprev.dto.UsuarioDTO;
import fiap.tds.sprint3odontoprev.entity.Usuario;
import fiap.tds.sprint3odontoprev.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioDTO saveUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = toEntity(usuarioDTO);

        if(usuarioDTO.getId()==null){
            System.out.println("Senha recebida: " + usuario.getSenha());
            System.out.println("Status antes de salvar: " + usuario.getStatus());
            usuario = usuarioRepository.save(usuario);
        } else{
            UsuarioDTO byId = this.findById(usuarioDTO.getId());
            byId.setEmail(usuarioDTO.getEmail());
            byId.setSenha(usuarioDTO.getSenha());
            byId.setStatus(usuarioDTO.getStatus());

            System.out.println("Status antes de salvar: " + usuario.getStatus());

            System.out.println("Senha recebida: " + usuario.getSenha());

            usuario = usuarioRepository.save(toEntity(byId));
        }
        return toDto(usuario);
    }

    public List<UsuarioDTO> findAll() {
        List<Usuario> list = usuarioRepository.findAll();
        List<UsuarioDTO> dtos = list.stream().map(UsuarioService::toDto).toList();
        return dtos;
    }

    public void deleteById(Long id) {
        System.out.println("usuario deletado");
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO findById(Long id) {
        Optional<Usuario> byId = usuarioRepository.findById(id);
        if(byId.isPresent()) {
            return toDto(byId.get());
        }
        throw new RuntimeException("id nao encontrado");
    }


    private static Usuario toEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setStatus(usuarioDTO.getStatus());
        return usuario;
    }

    private static UsuarioDTO toDto(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setSenha(usuario.getSenha());
        usuarioDTO.setStatus(usuario.getStatus());
        return usuarioDTO;
    }
}
