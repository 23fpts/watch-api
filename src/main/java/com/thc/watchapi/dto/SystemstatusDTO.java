package com.thc.watchapi.dto;


import com.thc.watchapi.model.Systemstatus;
import com.thc.watchapi.model.Weiyivalue;
import lombok.Data;

import java.util.List;

@Data
public class SystemstatusDTO {
    private List<Systemstatus> list;

    private Double XoffsetAverage;
    private Double YoffsetAverage;
    private Double HorAngleAverage;
    private Double VerAngelAverage;



    private Double maxofXoffset;
    private Double maxofYoffset;
    private Double maxofHorAngle;
    private Double maxofVerAngle;

}
