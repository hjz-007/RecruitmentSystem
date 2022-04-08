package com.hjz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRegisterDTO {

    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{3,12}$")
    private String companyName;

    @Email
    private String companyEmail;

    @Pattern(regexp = "^[a-zA-Z0-9]{6,12}$")
    private String companyPassword;
}
