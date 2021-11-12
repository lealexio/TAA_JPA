package dao;

import entities.Agenda;
import entities.Meeting;
import java.util.List;

/**
 * Dao for Agenda
 */
public class AgendaDao extends AbstractJpaDao<Long, Agenda> {

    public AgendaDao() {
        super(Agenda.class);
    }

    /**
     * List Meetings of en Agenda from DB
     * @param id of Agenda
     * @return Meeting list
     */
    public List<Meeting> listMeeting(long id) {
        return this.entityManager.createNamedQuery("listMeetingOfAgenda", Meeting.class).setParameter("id", id).getResultList();
    }
}
