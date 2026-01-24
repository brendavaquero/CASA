package org.casa.backend.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.casa.backend.dto.ArchivoDto;
import org.casa.backend.entity.*;
import org.casa.backend.enums.CategoriaArchivo;
import org.casa.backend.enums.TipoArchivo;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.ArchivoMapper;
import org.casa.backend.repository.ActividadRepository;
import org.casa.backend.repository.ArchivoRepository;
import org.casa.backend.repository.PostulacionRepository;
import org.casa.backend.repository.ProgramaRepository;
import org.casa.backend.service.ArchivoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipOutputStream;


@Service
@AllArgsConstructor
public class ArchivoServiceImpl implements ArchivoService {
    private ArchivoRepository archivoRepository;
    private ActividadRepository actividadRepository;
    private PostulacionRepository postulacionRepository;
    private ProgramaRepository programaRepository;

    /*@Override
    public ArchivoDto createArchivo(ArchivoDto archivoDto) {
        Archivo archivo = ArchivoMapper.mapToArchivo(archivoDto);
        Archivo savedArchivo = archivoRepository.save(archivo);

        return null;
    }*/

    @Override
    public ArchivoDto createArchivo(ArchivoDto archivoDto) {
        Archivo archivo = ArchivoMapper.mapToArchivo(archivoDto);

        // Validar exclusividad
        if (archivoDto.getIdActividad() != null && archivoDto.getIdPostulacion() != null) {
            throw new ResourceNotFoundException("Un archivo solo puede estar asociado a Actividad o Postulación, no a ambas");
        }

        // Asociar Actividad si existe
        if (archivoDto.getIdActividad() != null) {
            Actividad actividad = actividadRepository.findById(archivoDto.getIdActividad())
                    .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada"));
            archivo.setActividad(actividad);
        }

        // Asociar Postulación si existe
        if (archivoDto.getIdPostulacion() != null) {
            Postulacion postulacion = postulacionRepository.findById(archivoDto.getIdPostulacion())
                    .orElseThrow(() -> new ResourceNotFoundException("Postulación no encontrada"));
            archivo.setPostulacion(postulacion);
        }

        Archivo savedArchivo = archivoRepository.save(archivo);

        return ArchivoMapper.mapToArchivoDto(savedArchivo);
    }

    @Override
    public ArchivoDto getArchivoById(String idArchivo) {
        Archivo archivo = archivoRepository.findById(idArchivo)
                .orElseThrow(() -> new ResourceNotFoundException("Archivo no encontrado. ID: " + idArchivo));
        return ArchivoMapper.mapToArchivoDto(archivo);
    }

    @Override
    public void deleteArchivo(String idArchivo) {
        Archivo archivo = archivoRepository.findById(idArchivo)
                .orElseThrow(() -> new ResourceNotFoundException("Archivo no encontrado. ID: " + idArchivo));
        archivoRepository.delete(archivo);
    }

    @Override
    public List<ArchivoDto> getAllArchivos() {
        List<Archivo> archivos =  archivoRepository.findAll();
        return archivos.stream().map((archivo) -> ArchivoMapper.mapToArchivoDto(archivo))
                .collect(Collectors.toList());
    }

    @Override
    public ArchivoDto uploadArchivo(MultipartFile file, String idActividad, String idPostulacion,CategoriaArchivo categoria) {
        try {
            // 1. Validar archivo
            if (file.isEmpty()) {
                throw new RuntimeException("El archivo está vacío");
            }

            // Detectar extensión
            String originalName = file.getOriginalFilename();
            String extension = originalName.substring(originalName.lastIndexOf(".") + 1).toLowerCase();

            TipoArchivo tipo;
            switch (extension) {
                case "jpg":
                case "jpeg":
                case "png":
                case "gif":
                    tipo = TipoArchivo.IMAGEN;
                    break;

                case "pdf":
                case "doc":
                case "docx":
                case "xls":
                case "xlsx":
                case "ppt":
                case "pptx":
                    tipo = TipoArchivo.DOCUMENTO;
                    break;

                case "mp4":
                    tipo = TipoArchivo.VIDEO;
                    break;

                case "mp3":
                case "wav":
                    tipo = TipoArchivo.AUDIO;
                    break;

                default:
                    tipo = TipoArchivo.OTRO;
            }

            String folder;

            switch (tipo) {
                case IMAGEN:
                    folder = "images/";
                    break;
                case DOCUMENTO:
                    folder = "documents/";
                    break;
                case VIDEO:
                    folder = "videos/";
                    break;
                case AUDIO:
                    folder = "audios/";
                    break;
                default:
                    folder = "others/";
            }

            String uploadDir = "uploads/" + folder;

            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 3. Crear nombre único
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = uploadDir + fileName;

            // 4. Guardar archivo en el servidor
            Path path = Paths.get(filePath);

            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // 5. Convertir ruta en URL accesible
            String url = "/uploads/" + folder + fileName;

            // 6. Crear entidad Archivo
            Archivo archivo = new Archivo();
            archivo.setNombre(originalName);
            archivo.setRuta(url);
            archivo.setTipo(tipo);
            archivo.setCategoria(categoria);

            // Asociar actividad
            if (idActividad != null) {
                Actividad actividad = actividadRepository.findById(idActividad)
                        .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada"));
                archivo.setActividad(actividad);
            }

            // Asociar postulación
            if (idPostulacion != null) {
                Postulacion postulacion = postulacionRepository.findById(idPostulacion)
                        .orElseThrow(() -> new ResourceNotFoundException("Postulación no encontrada"));
                archivo.setPostulacion(postulacion);
            }

            // 7. Guardar en BD
            Archivo saved = archivoRepository.save(archivo);

            // 8. Devolver DTO
            return ArchivoMapper.mapToArchivoDto(saved);

        } catch (IOException e) {
            throw new ResourceNotFoundException("Error al subir archivo: " + e.getMessage());
        }
    }

    @Override
    public List<ArchivoDto> getArchivosByActividad(String idActividad) {
        return archivoRepository.findByActividad_IdActividad(idActividad)
                .stream()
                .map(ArchivoMapper::mapToArchivoDto)
                .toList();
    }

    @Override
    public List<ArchivoDto> getEvidenciasByActividad(String idActividad) {
        return archivoRepository
                .findByActividad_IdActividadAndCategoria(idActividad, CategoriaArchivo.EVIDENCIA)
                .stream()
                .map(ArchivoMapper::mapToArchivoDto)
                .toList();
    }

    @Override
    public byte[] getZipEvidenciasPrograma(String idPrograma) throws IOException {

        Programa programa = programaRepository.findById(idPrograma)
                .orElseThrow(() -> new ResourceNotFoundException("Programa no encontrado: " + idPrograma));

        List<TallerDiplomado> talleres = programa.getTalleres();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        for (TallerDiplomado taller : talleres) {

            List<Archivo> evidencias = archivoRepository
                    .findByActividad_IdActividadAndCategoria(taller.getIdActividad(), CategoriaArchivo.EVIDENCIA);

            for (Archivo archivo : evidencias) {

                String rutaPublica = archivo.getRuta();   // "/uploads/documents/file.pdf"

                // Convertir URL pública → ruta física real
                String physicalPath = rutaPublica.replaceFirst("^/uploads/", "uploads/");

                Path filePath = Paths.get(physicalPath);

                if (!Files.exists(filePath)) {
                    System.out.println("NO ENCONTRADO: " + filePath.toAbsolutePath());
                    continue;
                }

                String zipEntryName = taller.getTitulo() + "/" + archivo.getNombre();
                zos.putNextEntry(new ZipEntry(zipEntryName));

                Files.copy(filePath, zos);
                zos.closeEntry();
            }
        }

        zos.close();
        return baos.toByteArray();
    }

    @Override
    public void deleteArchivoFisicoYRegistro(String idArchivo) {
        //Buscar en la bd
        Archivo archivo = archivoRepository.findById(idArchivo)
                .orElseThrow(() -> new ResourceNotFoundException("Archivo no encontrado. ID: " + idArchivo));

        //borrar fisicamente
        if (archivo.getRuta() != null && !archivo.getRuta().isEmpty()) {

            //Convertir ruta pública → ruta física
            String physicalPath = archivo.getRuta().replaceFirst("^/uploads/", "uploads/");
            Path path = Paths.get(physicalPath);

            try {
                if (Files.exists(path)) {
                    Files.delete(path);
                    System.out.println("Archivo físico eliminado: " + path.toAbsolutePath());
                } else {
                    System.out.println("Archivo físico NO encontrado: " + path.toAbsolutePath());
                }
            } catch (IOException e) {
                throw new RuntimeException("Error al eliminar archivo físico: " + e.getMessage());
            }
        }

        //registro en la base de datos
        archivoRepository.delete(archivo);
    }

    @Override
    public Archivo obtenerPorPostulacion(String idPostulacion) {
        return archivoRepository.findByPostulacion_IdPostulacion(idPostulacion)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No se encontró archivo para la postulación " + idPostulacion
                ));
    }
}