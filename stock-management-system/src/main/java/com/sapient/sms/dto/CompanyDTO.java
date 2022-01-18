package com.sapient.sms.dto;

import com.sapient.sms.ExcludeFromJacocoGeneratedReport;
import com.sapient.sms.entity.Director;
import com.sapient.sms.entity.StockExchange;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class CompanyDTO {
    @NotBlank(message = "Company name is required")
    private String name;
    private Float turnover;
    @NotBlank(message = "CEO name is required")
    private String ceo;
    private Boolean listed;
    private String sector;
    @Size(min = 10)
    private String brief;
    private String stockCode;
    @Size(max = 15)
    private Set<Director> directors;
    @Size(min = 1)
    private Set<StockExchange> stockExchanges;
    private boolean deleted;
}
