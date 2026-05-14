package com.mycompany.tennis.repository;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Event;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EventRepositoryImpl {

    public Event getById(Long id) {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Event event = session.get(Event.class, id);
            System.out.println("Epreuve lue.");
        return event;
    }

    public List<Event> getEventsList(String eventCode) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Event> query = session.createQuery("select e from Event e join fetch e.tournament where e.tournament.code=?0", Event.class);
        query.setParameter(0, eventCode);
        List<Event> events = query.getResultList();
        System.out.println("Epreuves lues");

        return events;
    }

}