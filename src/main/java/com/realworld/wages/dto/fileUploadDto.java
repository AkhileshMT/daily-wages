package com.realworld.wages.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class fileUploadDto extends RepresentationModel<fileUploadDto> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size( max = 100 )
    private String name;

    @Size( max = 100 )
    private String type;

    @Size( max = 100 )
    private String filePath;

    private byte[] imageData;

    private Long expenseId;
}
