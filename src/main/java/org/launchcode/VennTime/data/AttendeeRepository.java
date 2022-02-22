package org.launchcode.VennTime.data;

import org.launchcode.VennTime.models.Attendee;
import org.springframework.data.repository.CrudRepository;

public interface AttendeeRepository extends CrudRepository<Attendee, Integer> {
}
