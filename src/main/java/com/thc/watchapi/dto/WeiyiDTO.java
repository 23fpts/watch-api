package com.thc.watchapi.dto;



import com.thc.watchapi.model.Weiyivalue;
import lombok.Data;

import java.util.List;

@Data
public class WeiyiDTO {

    private List<Weiyivalue> list;

    private Double average1;
    private Double average2;
    private Double average3;
    private Double average4;
    private Double average5;
    private Double average6;
    private Double average7;


    private Double max1;
    private Double max2;
    private Double max3;
    private Double max4;
    private Double max5;
    private Double max6;
    private Double max7;


}
