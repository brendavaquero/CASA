package org.casa.backend.service.impl;

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
            throw new RuntimeException("Un archivo solo puede estar asociado a Actividad o Postulación, no a ambas");
        }

        // Asociar Actividad si existe
        if (archivoDto.getIdActividad() != null) {
            Actividad actividad = actividadRepository.findById(archivoDto.getIdActividad())
                    .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
            archivo.setActividad(actividad);
        }

        // Asociar Postulación si existe
        if (archivoDto.getIdPostulacion() != null) {
            Postulacion postulacion = postulacionRepository.findById(archivoDto.getIdPostulacion())
                    .orElseThrow(() -> new RuntimeException("Postulación no encontrada"));
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
    public ArchivoDto uploadArchivo(MultipartFile file, String idActividad, String idPostulacion) {
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
                case "mp3":
                    tipo = TipoArchivo.AUDIO_VIDEO;
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
                case AUDIO_VIDEO:
                    folder = "videos/";
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

            // Asociar actividad
            if (idActividad != null) {
                Actividad actividad = actividadRepository.findById(idActividad)
                        .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
                archivo.setActividad(actividad);
            }

            // Asociar postulación
            if (idPostulacion != null) {
                Postulacion postulacion = postulacionRepository.findById(idPostulacion)
                        .orElseThrow(() -> new RuntimeException("Postulación no encontrada"));
                archivo.setPostulacion(postulacion);
            }

            // 7. Guardar en BD
            Archivo saved = archivoRepository.save(archivo);

            // 8. Devolver DTO
            return ArchivoMapper.mapToArchivoDto(saved);

        } catch (IOException e) {
            throw new RuntimeException("Error al subir archivo: " + e.getMessage());
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

    /*@Override
    public byte[] getZipEvidenciasPrograma(String idPrograma) throws IOException {

        Programa programa = programaRepository.findById(idPrograma)
                .orElseThrow(() -> new ResourceNotFoundException("Programa no encontrado: " + idPrograma));

        // obtener actividades del programa
        List<TallerDiplomado> talleres = programa.getTalleres();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);

        for (TallerDiplomado taller : talleres) {

            // Archivos de tipo EVIDENCIA
            List<Archivo> evidencias = archivoRepository
                    .findByActividad_IdActividadAndCategoria(taller.getIdActividad(), CategoriaArchivo.EVIDENCIA);

            for (Archivo archivo : evidencias) {

                // ruta física del archivo
                //Path filePath = Paths.get(archivo.getRuta());
                String rutaPublica = archivo.getRuta();   // "/uploads/documents/archivo.pdf"

                // Convertir a ruta física real
                Path filePath = Paths.get("." + rutaPublica);  // "./uploads/documents/archivo.pdf"


                if (!Files.exists(filePath))
                    continue;

                // Nombre dentro del ZIP: Carpeta-por-actividad/archivo
                String zipEntryName = taller.getTitulo() + "/" + archivo.getNombre();
                zos.putNextEntry(new ZipEntry(zipEntryName));

                Files.copy(filePath, zos);
                zos.closeEntry();
            }
        }

        zos.close();
        return baos.toByteArray();
    }*/

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

                /*Path filePath = Paths.get(physicalPath);

                if (!Files.exists(filePath)) {
                    // Intento alternativo sin subcarpetas (uploadDir plano)
                    Path alt = Paths.get("uploads/" + archivo.getNombre());
                    System.out.println("Alternativo: " + alt.toAbsolutePath());
                    if (Files.exists(alt)) {
                        filePath = alt;
                    } else {
                        System.out.println("NO ENCONTRADO: " + filePath.toAbsolutePath());
                        continue;
                    }
                }*/


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



}
