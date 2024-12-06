package tn.esprit.eventsproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServicesImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServicesImpl eventServices;

    private Event sampleEvent;

    @BeforeEach
    void setUp() {
        sampleEvent = new Event();
        sampleEvent.setIdEvent(1);
        sampleEvent.setDescription("Sample Event");
        sampleEvent.setDateDebut(LocalDate.of(2024, 1, 1));
        sampleEvent.setDateFin(LocalDate.of(2024, 1, 2));
        sampleEvent.setCout(1000.0f);
    }

    @Test
    void addEventTest() {
        when(eventRepository.save(Mockito.any(Event.class))).thenReturn(sampleEvent);

        Event addedEvent = eventServices.addEvent(sampleEvent);

        assertThat(addedEvent).isNotNull();
        assertThat(addedEvent.getDescription()).isEqualTo("Sample Event");
        verify(eventRepository, times(1)).save(sampleEvent);
    }

    @Test
    void deleteEventTest() {
        int idEvent = 1;

        when(eventRepository.existsById(idEvent)).thenReturn(true);
        doNothing().when(eventRepository).deleteById(idEvent);

        eventServices.deleteEvent(idEvent);

        verify(eventRepository, times(1)).deleteById(idEvent);
    }

    @Test
    void deleteEventNonExistentTest() {
        int idEvent = 1;

        when(eventRepository.existsById(idEvent)).thenReturn(false);

        try {
            eventServices.deleteEvent(idEvent);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Event with ID 1 does not exist.");
        }

        verify(eventRepository, never()).deleteById(idEvent);
    }

    @Test
    void updateEventTest() {
        int idEvent = 1;

        Event updatedEvent = new Event();
        updatedEvent.setDescription("Updated Event");
        updatedEvent.setDateDebut(LocalDate.of(2024, 1, 5));
        updatedEvent.setDateFin(LocalDate.of(2024, 1, 6));
        updatedEvent.setCout(2000.0f);

        when(eventRepository.findById(idEvent)).thenReturn(Optional.of(sampleEvent));
        when(eventRepository.save(Mockito.any(Event.class))).thenReturn(updatedEvent);

        Event result = eventServices.updateEvent(idEvent, updatedEvent);

        assertThat(result).isNotNull();
        assertThat(result.getDescription()).isEqualTo("Updated Event");
        assertThat(result.getCout()).isEqualTo(2000.0f);
        verify(eventRepository, times(1)).findById(idEvent);
        verify(eventRepository, times(1)).save(sampleEvent);
    }

    @Test
    void retrieveEventTest() {
        int idEvent = 1;

        when(eventRepository.findById(idEvent)).thenReturn(Optional.of(sampleEvent));

        Event retrievedEvent = eventServices.retrieveEvent(idEvent);

        assertThat(retrievedEvent).isNotNull();
        assertThat(retrievedEvent.getDescription()).isEqualTo("Sample Event");
        verify(eventRepository, times(1)).findById(idEvent);
    }

    @Test
    void retrieveNonExistentEventTest() {
        int idEvent = 1;

        when(eventRepository.findById(idEvent)).thenReturn(Optional.empty());

        try {
            eventServices.retrieveEvent(idEvent);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Event with ID 1 does not exist.");
        }

        verify(eventRepository, times(1)).findById(idEvent);
    }
}
