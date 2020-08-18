package br.com.rgrassi.events.modules.events.mongodb.repositories;

import br.com.rgrassi.events.modules.events.mongodb.entities.Event;
import br.com.rgrassi.events.modules.events.repositories.IEventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EventsMongoRepositoryImpl implements IEventsRepository {
  private final MongoTemplate mongoTemplate;

  @Override
  public Optional<Event> save(Event event) {
    return Optional.of(mongoTemplate.insert(event));
  }

  @Override
  public Optional<Event> findById(String id) {
    return Optional.of(mongoTemplate.findById(id, Event.class));
  }

  @Override
  public Optional<List<Event>> findAll() {
    return Optional.of(mongoTemplate.findAll(Event.class));
  }

  @Override
  public void deleteById(String id) {
    mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), Event.class);
  }

  @Override
  public void update(Event event) {
    mongoTemplate.save(event);
  }
}
