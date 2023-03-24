package com.qaq.base.model.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Token {
    private String fullname;
    private String email;
    private String userid;
    private String openid;
    private String avatar;
    private String salt;
    private long exp;
}