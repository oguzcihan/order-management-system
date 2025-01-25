package org.cihan.ordermanagementsystem.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private Date timestamp;
    private String message;
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String field;
}
