package com.sapient.sms.entity;

import com.sapient.sms.ExcludeFromJacocoGeneratedReport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Float turnover;
    private String ceo;
    private Boolean listed;
    private String sector;
    private String brief;
    private String stockCode;

    @OneToMany(targetEntity = Director.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId", referencedColumnName = "id")
    private Set<Director> directors;

    @OneToMany(targetEntity = StockExchange.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId", referencedColumnName = "id")
    private Set<StockExchange> stockExchanges;

    private boolean deleted;

    public boolean getFlag() {
        return deleted;
    }

    public void setFlag(boolean flag) {
        this.deleted = flag;
    }

}
