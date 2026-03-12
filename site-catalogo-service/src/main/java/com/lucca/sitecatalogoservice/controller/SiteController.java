package com.lucca.sitecatalogoservice.controller;

import com.lucca.sitecatalogoservice.dto.SiteRequestDTO;
import com.lucca.sitecatalogoservice.dto.SiteResponseDTO;
import com.lucca.sitecatalogoservice.model.Site;
import com.lucca.sitecatalogoservice.service.SiteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sites")
@RequiredArgsConstructor
public class SiteController {
    private final SiteService siteService;

    @PostMapping
    public ResponseEntity<SiteResponseDTO> inserir(@Valid @RequestBody SiteRequestDTO siteRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(siteService.inserir(siteRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SiteResponseDTO> alterar(@PathVariable String id, @Valid @RequestBody SiteRequestDTO siteRequestDTO){
        return ResponseEntity.ok(siteService.alterar(id, siteRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<SiteResponseDTO>> listarTodos(){
        return ResponseEntity.ok(siteService.listarTodos());
    }

    @GetMapping("/{busca}")
    public ResponseEntity<List<SiteResponseDTO>> buscarPorNome(@RequestParam String nome){
        return ResponseEntity.ok(siteService.localizarPorNome(nome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable String id){
        siteService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
