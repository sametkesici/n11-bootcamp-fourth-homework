package com.fourthhomework.n11bootcamp.debt.dto;

import lombok.Data;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class DebtDto {

    private Long id;

    private Double mainDebt;

    private Double remainingDebt;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    private Long userId;

    private Long debtId;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

}
