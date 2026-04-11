package com.mycompany.tennis.repository;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Event;
import org.hibernate.Session;

public class EventRepositoryImpl {

    public Event getById(Long id) {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Event event = session.get(Event.class, id);
            System.out.println("Epreuve lue.");
        return event;
    }

}