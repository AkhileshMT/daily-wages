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
public class categoryDto extends RepresentationModel<categoryDto> implements Serializable {

    private Long categoryId;

    private Long userId;

    @NotNull
    @Size(min = 1, max = 100)
    private String categoryName;

    private boolean active=true;

    private Date createdDate;

    private Date modifiedDate;

}
