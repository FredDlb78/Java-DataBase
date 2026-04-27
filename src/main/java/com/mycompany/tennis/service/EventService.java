package com.mycompany.tennis.service;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.dto.EventFullDTO;
import com.mycompany.tennis.dto.EventLightDTO;
import com.mycompany.tennis.dto.TournamentDTO;
import com.mycompany.tennis.entity.Event;
import com.mycompany.tennis.repository.EventRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EventService {

    private EventRepositoryImpl eventRepository;

    public EventService() {
        this.eventRepository = new EventRepositoryImpl();
    }

    public EventFullDTO getEventWithTournament(Long id) {
        Session session = null;
        Transaction tx = null;
        Event event = null;
        EventFullDTO dto = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            event = eventRepository.getById(id);
            Hibernate.initialize(event.getTournament());
            dto = new EventFullDTO();
            dto.setId(event.getId());
            dto.setYear(event.getYear());
            dto.setEventType(event.getEventType());
            TournamentDTO tournamentDTO = new TournamentDTO();
            tournamentDTO.setId(event.getTournament().getId());
            tournamentDTO.setCode(event.getTournament().getCode());
            tournamentDTO.setName(event.getTournament().getName());
            dto.setTournamentDTO(tournamentDTO);

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
        return dto;
    }

    public EventLightDTO getEventWithoutTournament(Long id) {
        Session session = null;
        Transaction tx = null;
        Event event = null;
        EventLightDTO dto = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            event = eventRepository.getById(id);
            dto = new EventLightDTO();
            dto.setId(event.getId());
            dto.setYear(event.getYear());
            dto.setEventType(event.getEventType());
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
        return dto;
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
