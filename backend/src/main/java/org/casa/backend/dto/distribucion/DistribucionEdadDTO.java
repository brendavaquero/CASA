package org.casa.backend.dto.distribucion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistribucionEdadDTO {

    private long menoresDe18;
    private long entre18y25;
    private long entre26y40;
    private long mayoresDe40;
}
