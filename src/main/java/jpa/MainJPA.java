package jpa;

import dao.AgendaDao;
import dao.IndividualDao;
import dao.MeetingDao;
import dao.ProfessionalDao;
import entities.Agenda;
import entities.Individual;
import entities.Meeting;
import entities.Professional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;


public class MainJPA {

    private EntityManager manager;

    public MainJPA(EntityManager manager) {
        this.manager = manager;
    }

    /**
     * Main class
     *
     * @param args Some args
     */
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();
        MainJPA jpa = new MainJPA(manager);

        // Dao initialisation
        AgendaDao agendaDao = new AgendaDao();
        MeetingDao meetingDao = new MeetingDao();
        IndividualDao individualDao = new IndividualDao();
        ProfessionalDao professionalDao = new ProfessionalDao();

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            jpa.createDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

        jpa.readDB(agendaDao, meetingDao, individualDao, professionalDao);

        manager.close();
        System.out.println("END");
    }

    /**
     * Create DB
     */
    private void createDB() {
        Agenda agenda = new Agenda("url", "login", "password");
        Individual individual1 = new Individual("IndividualFirstName1", "IndividualLastName1", "IndividualLogin1", "IndividualPassword1", "1");
        Individual individual2 = new Individual("IndividualFirstName2", "IndividualLastName2", "IndividualLogin2", "IndividualPassword2", "2");
        Meeting meeting1 = new Meeting(new Date(), "meeting1", individual1);
        Meeting meeting2 = new Meeting(new Date(), "meeting2", individual2);
        agenda.addMeeting(meeting1);
        agenda.addMeeting(meeting2);
        manager.persist(agenda);
        manager.persist(new Professional("ProfessionalFirstName", "ProfessionalLastName", "ProfessionalLLogin", "ProfessionalPassword", agenda));
        manager.persist(meeting1);
        manager.persist(meeting2);
        manager.persist(individual1);
        manager.persist(individual2);
    }

    /**
     * Read DB
     * @param agendaDao       Dao for Agenda object
     * @param meetingDao      Dao for Meeting object
     * @param individualDao   Dao for Meeting object
     * @param professionalDao Dao for Professional object
     */
    private void readDB(AgendaDao agendaDao, MeetingDao meetingDao, IndividualDao individualDao, ProfessionalDao professionalDao) {
        System.out.println("\n=======================\n");
        System.out.println("\nAgenda :");
        for (Agenda a : agendaDao.findAll()) {
            System.out.println(a.toString());
            //System.out.println("Agenda meeting is : ");
            //System.out.println(agendaDao.listMeeting(a.getId()));
        }


        System.out.println("\nMeeting :");
        for (Meeting m : meetingDao.findAll()) {
            System.out.println(m.toString());
        }

        System.out.println("\nIndividual :");
        for (Individual i : individualDao.findAll()) {
            System.out.println(i.toString());
        }

        System.out.println("\nProfessional :");
        for (Professional p : professionalDao.findAll()) {
            System.out.println(p.toString());
            System.out.println("Agenda of professional from DAO :");
            System.out.println(professionalDao.findProfessionalAgenda(p.getId()));
        }
    }


}
