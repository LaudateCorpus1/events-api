package br.com.rgrassi.events.repository;

import br.com.rgrassi.events.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {

}
