package com.mycompany.tennis.repository;

import com.mycompany.tennis.EntityManagerHolder;
import com.mycompany.tennis.entity.Event;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EventRepositoryImpl {

    public Event getById(Long id) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        Event event = em.find(Event.class, id);
        System.out.println("Epreuve lue.");
        return event;
    }

    public List<Event> getEventsList(String eventCode) {

        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        TypedQuery<Event> query = em.createQuery("select e from Event e join fetch e.tournament where e.tournament.code=?0", Event.class);
        query.setParameter(0, eventCode);
        List<Event> events = query.getResultList();
        System.out.println("Epreuves lues");

        return events;
    }

}