package br.com.rgrassi.events.modules.events.repositories;

import br.com.rgrassi.events.modules.events.mongodb.entities.Event;

import java.util.List;
import java.util.Optional;

public class FakeRepository implements IEventsRepository {
  @Override
  public Optional<Event> save(Event event) {
    return Optional.empty();
  }

  @Override
  public Optional<Event> findById(String id) {
    return Optional.empty();
  }

  @Override
  public Optional<List<Event>> findAll() {
    return Optional.empty();
  }

  @Override
  public void deleteById(String id) {

  }

  @Override
  public void update(Event event) {

  }
}
