package org.byron4j.tacocloud.domain;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class Order {
    @NotBlank(message = "name 必须填写")
    private String name;
    @NotBlank(message = "street 必须填写")
    private String street;
    @NotBlank(message = "city 必须填写")
    private String city;
    @NotBlank(message = "state 必须填写")
    private String state;
    @NotBlank(message = "zip 必须填写")
    private String zip;
    @CreditCardNumber(message = "不是合法的信用卡号")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "格式必须是 MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "无效的CVV")
    private String ccCVV;
}
