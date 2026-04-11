package com.mycompany.tennis.service;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Event;
import com.mycompany.tennis.entity.Tournament;
import com.mycompany.tennis.repository.EventRepositoryImpl;
import com.mycompany.tennis.repository.TournamentRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EventService {

    private EventRepositoryImpl eventRepository;

    public EventService() {
        this.eventRepository = new EventRepositoryImpl();
    }

    public Event getEvent(Long id) {
        Session session = null;
        Transaction tx = null;
        Event event = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            event = eventRepository.getById(id);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return event;
    }
}
