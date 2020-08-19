package br.com.rgrassi.events.service;

import br.com.rgrassi.events.error.IllegalArgumentException;
import br.com.rgrassi.events.modules.events.mongodb.entities.Event;
import br.com.rgrassi.events.modules.events.repositories.FakeRepository;
import br.com.rgrassi.events.modules.events.services.EventService;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
public class EventServiceTest {
  private FakeRepository fakeRepository;
  private EventService eventService;

  @BeforeEach
  public void setUp() {
    this.fakeRepository = new FakeRepository();
    this.eventService = new EventService(fakeRepository);
  }

  @Test
  @DisplayName("it should be able a create event with success")
  public void save_Event_WhenSuccessfull() {
    Event event = this.eventService.save(
      new Event().builder()
        .date(LocalDateTime.now().plusHours(1))
        .name("Festa de formatura")
        .userId("1").build()
    );
    Assertions.assertThat(event).isNotNull();
    Assertions.assertThat(event.getId()).isNotNull();
  }

  @Test
  @DisplayName("it should not be able create an appointment on a past date")
  public void save_Event_WhenError_OnPasteDate() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
      .isThrownBy(() -> this.eventService.save(
        new Event().builder()
          .date(LocalDateTime.now().minusHours(1))
          .name("Festa de formatura")
          .userId("1").build()
      )
    );
  }

  public void save_Event_WhenError_OnPastDate() {

  }
}
