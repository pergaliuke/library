package com.example.library.models;

import java.time.LocalDate;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TakeEvent {

    @Pattern(regexp = "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$")
    private String bookGuid;
    @Pattern(regexp = "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$")
    private String clientGuid;
    private LocalDate returnDate;

}
