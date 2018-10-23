package com.sda.SpringFacebook.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@EqualsAndHashCode(exclude = "friends")
@ToString(exclude = "friends")
@Builder
@NoArgsConstructor
@Document(collection = "user")
public class User {

    @Id
    private String id;
    private String login;
    private String firstName;
    private String lastName;
    private String password;

    private List<String> friends;
}
