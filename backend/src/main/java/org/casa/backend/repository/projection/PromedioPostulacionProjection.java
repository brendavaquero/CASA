package org.casa.backend.repository.projection;

public interface PromedioPostulacionProjection {
    String getIdPostulacion();
    Double getPromedio();
    Long getTotalEvaluaciones();
}
