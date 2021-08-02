package com.viettel.vds.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class PostUserRequestDto {

    @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
    @NotNull(message = "id là bắt buộc")
    private long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull(message = "name là bắt buộc")
    @NotEmpty(message = "name là bắt buộc")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull(message = "createDate là bắt buộc")
    private Date createDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull(message = "address là bắt buộc")
    @NotEmpty(message = "address là bắt buộc")
    private String address;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull(message = "phone là bắt buộc")
    @NotEmpty(message = "phone là bắt buộc")
    @Size(min=10,max = 10,message = "phone bat buoc gom 10 chu so")
    private String phone;
}
