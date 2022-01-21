package org.launchcode.VennTime.data;

import org.launchcode.VennTime.models.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {
}
