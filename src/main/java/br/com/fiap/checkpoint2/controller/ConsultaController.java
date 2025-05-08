package br.com.fiap.checkpoint2.controller;

import br.com.fiap.checkpoint2.dto.ConsultaDTO;
import br.com.fiap.checkpoint2.model.Consulta;
import br.com.fiap.checkpoint2.repository.ConsultaRepository;
import br.com.fiap.checkpoint2.repository.PacienteRepository;
import br.com.fiap.checkpoint2.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @PostMapping
    public ResponseEntity<Consulta> create(@RequestBody ConsultaDTO dto) {
        var consulta = new Consulta();
        consulta.setPaciente(pacienteRepository.findById(dto.pacienteId()).orElseThrow());
        consulta.setProfissional(profissionalRepository.findById(dto.profissionalId()).orElseThrow());
        consulta.setDataConsulta(dto.dataConsulta());
        consulta.setStatusConsulta(dto.statusConsulta());
        consulta.setQuantidadeHoras(dto.quantidadeHoras());
        consulta.setValorConsulta(dto.valorConsulta());

        return ResponseEntity.status(201).body(repository.save(consulta));
    }

    @GetMapping
    public List<Consulta> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> get(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> update(@PathVariable Long id, @RequestBody ConsultaDTO dto) {
        return repository.findById(id).map(c -> {
            c.setPaciente(pacienteRepository.findById(dto.pacienteId()).orElseThrow());
            c.setProfissional(profissionalRepository.findById(dto.profissionalId()).orElseThrow());
            c.setDataConsulta(dto.dataConsulta());
            c.setStatusConsulta(dto.statusConsulta());
            c.setQuantidadeHoras(dto.quantidadeHoras());
            c.setValorConsulta(dto.valorConsulta());
            return ResponseEntity.ok(repository.save(c));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
