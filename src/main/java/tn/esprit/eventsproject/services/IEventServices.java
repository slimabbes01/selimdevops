package tn.esprit.eventsproject.services;

import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;

import java.time.LocalDate;
import java.util.List;

public interface IEventServices {
    public Participant addParticipant(Participant participant);
    public Event addAffectEvenParticipant(Event event, int idParticipant);
    public Event addAffectEvenParticipant(Event event);
    public Logistics addAffectLog(Logistics logistics, String descriptionEvent);
    public List<Logistics> getLogisticsDates(LocalDate date_debut, LocalDate date_fin);
    public void calculCout();
    public Event addEvent(Event event);
    public void deleteEvent(int idEvent);
    public Event updateEvent(int idEvent,Event event);
    public Event retrieveEvent(int idEvent);

}
