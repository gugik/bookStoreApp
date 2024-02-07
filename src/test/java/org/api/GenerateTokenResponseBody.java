package org.api;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GenerateTokenResponseBody {
    String token;
    Date expires;
    String status;
    String result;
}
