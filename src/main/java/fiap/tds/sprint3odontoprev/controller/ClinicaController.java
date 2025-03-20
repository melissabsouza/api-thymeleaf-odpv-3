package fiap.tds.sprint3odontoprev.controller;

import fiap.tds.sprint3odontoprev.dto.ClinicaDTO;
import fiap.tds.sprint3odontoprev.service.ClinicaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@Log
@RequestMapping("/clinicas")
public class ClinicaController {
    @Autowired
    private final ClinicaService clinicaService;

    @GetMapping
    public String listarClinicas(Model model){
        List<ClinicaDTO> lista = clinicaService.findAll();
        System.out.println("Lista de Clinicas: " + lista);
        model.addAttribute("clinicas", lista);
        return "clinicas/lista";
    }

    @GetMapping("/novo")
    public String novaClinica(Model model){
        model.addAttribute("clinica", new ClinicaDTO());
        return "clinicas/formulario";
    }

    @PostMapping
    public String salvarClinica(@Valid @ModelAttribute("clinica") ClinicaDTO clinica, BindingResult bindingResults, Model model){
        if(bindingResults.hasErrors()){
            bindingResults.getAllErrors().forEach(e-> log.info(e.toString()));
            model.addAttribute("clinica", clinica);
            return "clinicas/formulario";
        }
        clinicaService.saveClinica(clinica);
        return "redirect:/clinica";
    }

    @GetMapping("/editar/{cnpj}")
    public String editarClinica(@PathVariable String cnpj, Model model){
        model.addAttribute("clinica", clinicaService.findById(cnpj));
        return "clinicas/formulario";
    }

    @GetMapping("/deletar/{cnpj}")
    public String deletarEndereco(@PathVariable String cnpj, Model model){
        clinicaService.deleteById(cnpj);
        return "redirect:/clinicas";
    }
}
