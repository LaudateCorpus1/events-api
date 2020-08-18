package br.com.rgrassi.events.service;

import br.com.rgrassi.events.modules.events.mongodb.entities.Event;
import br.com.rgrassi.events.modules.events.repositories.FakeRepository;
import br.com.rgrassi.events.modules.events.services.EventService;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceTest {
  private final FakeRepository fakeRepository;
  private final EventService eventService;

  public EventServiceTest() {
    this.fakeRepository = new FakeRepository();
    this.eventService = new EventService(fakeRepository);
  }

  @Test
  @DisplayName("it should be able a create event with success")
  public void save_Event_WhenSuccessfull() {
    Event event = fakeRepository.save(new Event().builder().date(LocalDateTime.now()).name("Festa de formatura").userId("1").build()).get();
    Assertions.assertThat(event).isNotNull();
    Assertions.assertThat(event.getId()).isNotNull();
  }
}
