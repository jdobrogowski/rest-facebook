package com.sda.SpringFacebook.request;

import com.sda.SpringFacebook.enums.RangeOfPost;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@NoArgsConstructor
@Getter
@Setter
@Validated
public class CreatePostRequest {

    @NotNull
    @Size(min = 5)
    private String postContent;

    @NotNull
    private RangeOfPost rangeOfPost;
}
