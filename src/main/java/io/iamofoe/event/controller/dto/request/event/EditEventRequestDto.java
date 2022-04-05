package io.iamofoe.event.controller.dto.request.event;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Value
@Builder
public class EditEventRequestDto {
    @NotBlank(message = "Id is required")
    long id;
    @NotBlank(message = "Title is required")
    String title;
    @NotBlank(message = "Location is required")
    String location;
    @NotBlank(message = "About is required")
    String about;
    @NotBlank(message = "Event date is required")
    String date;
    @NotBlank(message = "Flyer is required")
    String flyerUrl;
}
