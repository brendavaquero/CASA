package org.casa.backend.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.DocenteDto;
import org.casa.backend.entity.Docente;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.DocenteMapper;
import org.casa.backend.repository.DocenteRepository;
import org.casa.backend.service.DocenteService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DocenteServiceImpl implements DocenteService{
    private final DocenteRepository docenteRepository;

    @Override
    public DocenteDto createDocente(DocenteDto docenteDto) {
        Docente docente = DocenteMapper.mapToDocente(docenteDto);
        Docente savedDocente = docenteRepository.save(docente);
        return DocenteMapper.mapToDocenteDto(savedDocente);
    }

    @Override
    public DocenteDto getDocenteById(String idDocente) {
        Docente docente = docenteRepository.findById(idDocente)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado con id: " + idDocente));
        return DocenteMapper.mapToDocenteDto(docente);
    }

    @Override
    public List<DocenteDto> getAllDocentes() {
        List<Docente> docentes = docenteRepository.findAll();
        return docentes.stream()
                .map(DocenteMapper::mapToDocenteDto)
                .collect(Collectors.toList());
    }

    @Override
    public DocenteDto updateDocente(String idUsuario, DocenteDto docenteDto, MultipartFile imagen) {
        Docente docente =  docenteRepository.findById(idUsuario).orElseThrow(
        () -> new ResourceNotFoundException("Docente no encontrado con ese id "+ idUsuario)
       );

       String urlfoto = null;
       try {
        
       
            if (imagen != null && !imagen.isEmpty()) {

                String originalName = imagen.getOriginalFilename();
                String extension = originalName.substring(originalName.lastIndexOf(".") + 1).toLowerCase();

                if (!(extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png"))) {
                    throw new RuntimeException("Solo se permiten imágenes JPG, JPEG o PNG");
                }

                String folder = "uploads/imagenes/docentes";
                File directory = new File(folder);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                String fileName = System.currentTimeMillis() + "_" + originalName;
                Path path = Paths.get(folder + fileName);
                Files.copy(imagen.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                urlfoto = "/uploads/imagenes/docentes" + fileName;
            }
            } catch (Exception e) {
                 throw new RuntimeException("Error al guardar la imagen del docente", e);
       }

       docente.setNombre(docenteDto.getNombre());
        docente.setApellidos(docenteDto.getApellidos());
        docente.setCorreo(docenteDto.getCorreo());
        docente.setFoto(urlfoto);
        docente.setSemblanza(docente.getSemblanza());

        Docente obj = docenteRepository.save(docente);
        return DocenteMapper.mapToDocenteDto(obj);
    }

    @Override
    public void deleteDocente(String idDocente) {
        Docente docente = docenteRepository.findById(idDocente)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado con id: " + idDocente));
        docenteRepository.delete(docente);
    }

}
