package com.realworld.wages.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class dailyExpensiveDto extends RepresentationModel<dailyExpensiveDto> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long expensesId;

    @Size( max = 100 )
    private String title;

    @Size( max = 100 )
    private String description;

    private Long amount;

    @Size( max = 100 )
    private String categoryName;

    private Date createdDate;

    private Date modifiedDate;

    @NotNull
    private Long categoryId;

}
