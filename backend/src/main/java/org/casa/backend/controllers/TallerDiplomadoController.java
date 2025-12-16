package org.casa.backend.controllers;

import lombok.AllArgsConstructor;

import org.casa.backend.dto.ActividadDto;
import org.casa.backend.dto.DocenteDto;
import org.casa.backend.dto.ProgramaDto;
import org.casa.backend.dto.TallerDiplomadoDto;
import org.casa.backend.enums.EstadoActividad;
import org.casa.backend.service.DocenteService;
import org.casa.backend.service.ProgramaService;
import org.casa.backend.service.TallerDiplomadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/talleresydiplomados")
@CrossOrigin(origins = "*")
public class TallerDiplomadoController {
    private TallerDiplomadoService tallerDiplomadoService;
    private DocenteService docenteService;
    private ProgramaService programaService;

    //Rest API ADD
    @PostMapping
    public ResponseEntity<TallerDiplomadoDto> createTallerDiplomado(@RequestBody TallerDiplomadoDto tallerDiplomadoDto) {
        TallerDiplomadoDto savedTallerDiplomado = tallerDiplomadoService.createTallerDiplomado(tallerDiplomadoDto);
        return new ResponseEntity<>(savedTallerDiplomado, HttpStatus.CREATED);
    }

    //Rest API GET
    @GetMapping("{id}")
    public ResponseEntity<TallerDiplomadoDto> getTallerDiplomadoById(@PathVariable("id") String idActividad) {
        TallerDiplomadoDto tallerDiplomadoDto = tallerDiplomadoService.getTallerDiplomadoById(idActividad);
        return ResponseEntity.ok(tallerDiplomadoDto);
    }

    @GetMapping
    public ResponseEntity<List<TallerDiplomadoDto>> getAllTalleresDiplomados() {
        List<TallerDiplomadoDto> talleresDiplomados = tallerDiplomadoService.getAllTalleresDiplomados();
        return ResponseEntity.ok(talleresDiplomados);
    }

    //Editar una actividad
    @PutMapping("/actividad/{id}")
    public ResponseEntity<TallerDiplomadoDto> updateActividad(@PathVariable("id") String actividadId, @RequestBody TallerDiplomadoDto updatedActividad){
        TallerDiplomadoDto actividad = tallerDiplomadoService.updateActividad(actividadId, updatedActividad);
        return ResponseEntity.ok(actividad);
    }
    //editar el taller o el diplomado
    @PutMapping("/{id}")
    public ResponseEntity<TallerDiplomadoDto> updateTallerDiplomado(@PathVariable("id") String tallerId, @RequestBody TallerDiplomadoDto updatedTallerDipl){
        TallerDiplomadoDto tallerDipDto = tallerDiplomadoService.updateTallerDiplo(tallerId, updatedTallerDipl);
        return ResponseEntity.ok(tallerDipDto);
    }

    //Editar el estado de la actividad
    @PutMapping("/estado/{id}")
    public ResponseEntity<ActividadDto> updateActividadEst(@PathVariable("id") String idActividad, @RequestBody  Map<String, String> body){
        String estadoString = body.get("estado");
        EstadoActividad estado = EstadoActividad.valueOf(estadoString.toUpperCase());

        ActividadDto actividadDto = tallerDiplomadoService.updateEstadoAct(idActividad, estado);
        return ResponseEntity.ok(actividadDto);
    }

    @GetMapping("/docente/{idUsuario}")
    public ResponseEntity<List<TallerDiplomadoDto>> getTalleresDocente(@PathVariable String idUsuario) {
        return ResponseEntity.ok(tallerDiplomadoService.getTalleresByDocente(idUsuario));
    }

    // por probar
    @GetMapping("/{id}/docente")
    public ResponseEntity<DocenteDto> getDocenteByTaller(@PathVariable String id) {

        TallerDiplomadoDto tallerDto = tallerDiplomadoService.getTallerDiplomadoById(id);

        if (tallerDto == null) {
            return ResponseEntity.notFound().build();
        }

        String idDocente = tallerDto.getIdDocente();

        if (idDocente == null) {
            return ResponseEntity.noContent().build();
        }

        DocenteDto docenteDto = docenteService.getDocenteById(idDocente);

        if (docenteDto == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(docenteDto);
    }

    // por probar
    @GetMapping("/{id}/programa")
    public ResponseEntity<ProgramaDto> getProgramaByTaller(@PathVariable String id) {
        TallerDiplomadoDto tallerDto = tallerDiplomadoService.getTallerDiplomadoById(id);

        if (tallerDto == null) {
            return ResponseEntity.notFound().build();
        }
        String idPrograma = tallerDto.getIdPrograma();

        if (idPrograma == null) {
            return ResponseEntity.noContent().build();
        }
        ProgramaDto programaDto = programaService.getById(idPrograma);

        if (programaDto == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(programaDto);
    }
    //Subir la imagen de la actividad
    @PostMapping("/uploadImagen")
    public ResponseEntity<String> uploadImagen(
            @RequestParam("file") MultipartFile file,
            @RequestParam("idActividad") String idActividad
    ) {
        String url = tallerDiplomadoService.uploadImagenActividad(file, idActividad);
        return ResponseEntity.ok(url);
    }

}