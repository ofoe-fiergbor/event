package io.iamofoe.event.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name = "Events")
@NoArgsConstructor
@Accessors(chain = true)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String location;
    private String about;
    private String date;
    private Date timestamp;
    private String flyerUrl;
    @OneToMany(mappedBy = "event", cascade=CascadeType.ALL)
    private List<Ticket> tickets;
    @ManyToOne(cascade=CascadeType.ALL)
    private User organiser;
}
