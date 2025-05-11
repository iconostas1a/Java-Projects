package lab2.com;

import lab2.com.controller.KittyController;
import lab2.com.controller.OwnerController;
import lab2.com.data.dao.KittyDaoImp;
import lab2.com.data.dao.OwnerDaoImp;
import lab2.com.data.entities.Kitty;
import lab2.com.data.dao.KittyDao;
import lab2.com.data.dao.OwnerDao;
import lab2.com.data.entities.Owner;
import lab2.com.data.models.Breed;
import lab2.com.data.models.Color;
import lab2.com.data.utils.HibernateSessionFactory;
import lab2.com.dto.OwnerDto;
import lab2.com.service.KittyService;
import lab2.com.service.OwnerService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Owner owner = new Owner();
            owner.setName("Паша");
            owner.setBirthDate(LocalDate.of(1990, 1, 15));

            Kitty kitty = new Kitty();
            kitty.setName("Kiti");
            kitty.setBirthDate(LocalDate.of(2022, 3, 5));
            kitty.setBreed(Breed.SIAMESE);
            kitty.setColor(Color.FAWN);
            kitty.setOwner(owner);

            owner.getKitties().add(kitty);

            session.persist(kitty);

            transaction.commit();

            System.out.println("Кот создан: " + kitty.getId());


        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

        } finally {
            if (session != null) session.close();
        }
    }

}

