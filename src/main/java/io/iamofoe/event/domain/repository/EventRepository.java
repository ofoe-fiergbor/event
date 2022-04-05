package io.iamofoe.event.domain.repository;

import io.iamofoe.event.domain.model.Event;
import io.iamofoe.event.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findEventsByOrganiser(User organiser);
}
