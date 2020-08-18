package br.com.rgrassi.events.modules.events.repositories;

import br.com.rgrassi.events.modules.events.mongodb.entities.Event;

import java.util.List;
import java.util.Optional;

public interface IEventsRepository {
  Optional<Event> save(Event event);
  Optional<Event> findById(String id);
  Optional<List<Event>> findAll();
  void deleteById(String id);
  void update(Event event);
}
