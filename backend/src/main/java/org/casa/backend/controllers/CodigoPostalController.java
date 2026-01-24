package org.casa.backend.controllers;

import org.casa.backend.service.CopomexService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/codigo-postal")
@CrossOrigin(origins = "*")
public class CodigoPostalController {

    private final CopomexService copomexService;

    public CodigoPostalController(CopomexService copomexService) {
        this.copomexService = copomexService;
    }

    @GetMapping("/{cp}")
    public ResponseEntity<?> buscarCp(@PathVariable String cp) {

        if (cp.length() != 5) {
            return ResponseEntity.badRequest().body("Código postal inválido");
        }

        return ResponseEntity.ok(copomexService.buscarPorCp(cp));
    }
}
