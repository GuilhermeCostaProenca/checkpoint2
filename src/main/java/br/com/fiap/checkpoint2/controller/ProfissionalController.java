package br.com.fiap.checkpoint2.controller;

import br.com.fiap.checkpoint2.model.Profissional;
import br.com.fiap.checkpoint2.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalRepository repository;

    @PostMapping
    public ResponseEntity<Profissional> create(@RequestBody Profissional profissional) {
        return ResponseEntity.status(201).body(repository.save(profissional));
    }

    @GetMapping
    public List<Profissional> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> get(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profissional> update(@PathVariable Long id, @RequestBody Profissional data) {
        return repository.findById(id).map(p -> {
            p.setNome(data.getNome());
            p.setEspecialidade(data.getEspecialidade());
            p.setValorHora(data.getValorHora());
            return ResponseEntity.ok(repository.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
