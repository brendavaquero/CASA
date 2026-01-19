package org.casa.backend.controllers;

import org.casa.backend.dto.record.CatalogoDto;
import org.casa.backend.enums.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/catalogos")
@CrossOrigin(origins = "*")
public class CatalogoController {

    @GetMapping("/paises")
    public List<CatalogoDto> paises() {
        return Arrays.stream(Pais.values())
                .map(g -> new CatalogoDto(g.name(), g.getNombre()))
                .toList();
    }

    @GetMapping("/grados-estudio")
    public List<CatalogoDto> gradosEstudio() {
        return Arrays.stream(GradoEstudio.values())
                .map(g -> new CatalogoDto(g.name(), g.getNombre()))
                .toList();
    }

    @GetMapping("/estados")
    public List<CatalogoDto> estados() {
        return Arrays.stream(Estado.values())
                .map(g -> new CatalogoDto(g.name(), g.getNombre()))
                .toList();
    }
    @GetMapping("/municipios/oaxaca")
    public List<CatalogoDto> municipios() {
        return Arrays.stream(Municipio.values())
                .map(g -> new CatalogoDto(g.name(), g.getNombre()))
                .toList();
    }

    @GetMapping("/lenguas")
    public List<CatalogoDto> lenguas() {
        return Arrays.stream(LenguaInd.values())
                .map(g -> new CatalogoDto(g.name(), g.getNombre()))
                .toList();
    }
}
