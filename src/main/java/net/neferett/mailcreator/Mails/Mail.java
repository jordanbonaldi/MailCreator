package net.neferett.mailcreator.Mails;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mail {

    private final int id;

    private final int domain;

    private final String email;

    private final String user;

}
