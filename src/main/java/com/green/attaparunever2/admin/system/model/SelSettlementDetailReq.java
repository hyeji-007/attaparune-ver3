package com.green.attaparunever2.admin.system.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelSettlementDetailReq {
    @JsonIgnore
    private String startDate;
    @JsonIgnore
    private String endDate;
}
