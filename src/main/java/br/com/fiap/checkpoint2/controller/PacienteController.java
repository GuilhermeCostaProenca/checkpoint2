package br.com.fiap.checkpoint2.controller;

import br.com.fiap.checkpoint2.model.Paciente;
import br.com.fiap.checkpoint2.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public ResponseEntity<Paciente> create(@RequestBody Paciente paciente) {
        return ResponseEntity.status(201).body(repository.save(paciente));
    }

    @GetMapping
    public List<Paciente> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> get(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> update(@PathVariable Long id, @RequestBody Paciente data) {
        return repository.findById(id).map(p -> {
            p.setNome(data.getNome());
            p.setEndereco(data.getEndereco());
            p.setBairro(data.getBairro());
            p.setEmail(data.getEmail());
            p.setTelefoneCompleto(data.getTelefoneCompleto());
            p.setDataNascimento(data.getDataNascimento());
            return ResponseEntity.ok(repository.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
