package com.base.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemoModel {

    private Long id;

    @NotBlank(message = "name 不能为空")
    private String name;

    private String age;

    @Max(value = 11, message = "size最大为11")
    private String size;
}
