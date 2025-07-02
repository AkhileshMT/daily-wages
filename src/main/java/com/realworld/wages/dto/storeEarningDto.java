package com.realworld.wages.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Data
public class storeEarningDto extends RepresentationModel<storeEarningDto>  implements Serializable {


    private long storeEarningId;

    private Long userId;

    @NotNull
    @Size(min = 2, max = 50)
    private String description;

    @NotNull
    private Long amountAdded;

    private Date createdDate;

    private Date modifiedDate;

    private Long currentAmount;

}
