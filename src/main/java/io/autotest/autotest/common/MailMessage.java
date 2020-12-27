package io.autotest.autotest.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class MailMessage implements Serializable {

    @JsonIgnore
    private String sender;
    private String subject;
    private String content;

}
