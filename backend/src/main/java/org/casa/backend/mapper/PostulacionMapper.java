package org.casa.backend.mapper;

import org.casa.backend.dto.PostulacionDto;
import org.casa.backend.entity.Participante;
import org.casa.backend.entity.Postulacion;

public class PostulacionMapper {

    public static PostulacionDto mapToPostulacionDto(Postulacion postulacion) {
        if (postulacion == null) return null;

        return new PostulacionDto(
            postulacion.getIdPostulacion(),
            postulacion.getParticipante().getIdUsuario(),
            postulacion.getActividad().getIdActividad(),
            postulacion.getPostulante(),
            postulacion.getMotivo(),
            postulacion.getEstadoPos(),
            postulacion.getFechaPostulacion()
        );
    }

    public static Postulacion mapToPostulacion(PostulacionDto dto, Participante participante) {
        if (dto == null) return null;

        Postulacion postulacion = new Postulacion();
        postulacion.setIdPostulacion(dto.getIdPostulacion());
        postulacion.setPostulante(dto.getPostulante());
        postulacion.setMotivo(dto.getMotivo());
        postulacion.setEstadoPos(dto.getEstadoPos());
        postulacion.setFechaPostulacion(dto.getFechaPostulacion());
        postulacion.setParticipante(participante);

        return postulacion;
    }
}
