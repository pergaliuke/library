package com.example.library.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TakeEvent {

    @Pattern(regexp = "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$")
    private String bookGuid;
    @Pattern(regexp = "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$")
    private String clientGuid;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

}
