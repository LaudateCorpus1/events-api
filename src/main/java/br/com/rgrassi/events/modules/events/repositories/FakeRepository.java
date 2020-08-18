package br.com.rgrassi.events.modules.events.repositories;

import br.com.rgrassi.events.modules.events.mongodb.entities.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public class FakeRepository implements IEventsRepository {
  private List<Event> events;

  public FakeRepository() {
    this.events = new ArrayList<>();
  }

  @Override
  public Optional<Event> save(Event event) {
    UUID uuid = UUID.randomUUID();
    event.setId(uuid.toString());
    events.add(event);

    return Optional.of(event);
  }

  @Override
  public Optional<Event> findById(String id) {
    Event event = events.stream()
      .filter(e -> id.equals(e.getId()))
      .findAny()
      .orElse(null);

    return Optional.of(event);
  }

  @Override
  public Optional<List<Event>> findAll() {
    return Optional.of(events);
  }

  @Override
  public void deleteById(String id) {
    Predicate<Event> forDelete = event -> event.getId().equals(id);
    events.removeIf(forDelete);
  }

  @Override
  public void update(Event event) {
    int index = events.indexOf(event);
    events.set(index, event);
  }
}
