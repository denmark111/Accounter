package com.accounter.restapi.model.params;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class GoFileParam {

    @NotNull(
            message = "계정정보는 필수값 입니다."
    )
    private MultipartFile account;

    @NotNull(
            message = "회사정보는 필수값 입니다."
    )
    private MultipartFile company;

}
