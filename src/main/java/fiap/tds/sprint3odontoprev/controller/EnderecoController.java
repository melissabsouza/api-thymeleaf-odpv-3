package fiap.tds.sprint3odontoprev.controller;

import fiap.tds.sprint3odontoprev.dto.EnderecoDTO;
import fiap.tds.sprint3odontoprev.service.EnderecoService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@Log
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private final EnderecoService enderecoService;

    @GetMapping
    public String listarEnderecos(Model model){
        List<EnderecoDTO> lista = enderecoService.findAll();
        System.out.println("Lista de enderecos: " + lista);
        model.addAttribute("enderecos", lista);
        return "enderecos/lista";
    }
}
