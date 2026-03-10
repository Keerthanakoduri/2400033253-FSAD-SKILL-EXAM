package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.Scanner;

public class ClientDemo
{
    public static void main(String[] args)
    {
        Configuration cfg = new Configuration();
        cfg.configure();

        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        Scanner sc = new Scanner(System.in);

        System.out.println("1.Insert");
        System.out.println("2.View By ID");

        int choice = sc.nextInt();

        if(choice == 1)
        {
            Project p = new Project();

            System.out.println("Enter Project Name:");
            p.setName(sc.next());

            System.out.println("Enter Description:");
            p.setDescription(sc.next());

            p.setDate(new Date());

            System.out.println("Enter Status:");
            p.setStatus(sc.next());

            Transaction tx = session.beginTransaction();

            session.save(p);

            tx.commit();

            System.out.println("Record Inserted Successfully");
        }

        else if(choice == 2)
        {
            System.out.println("Enter Project ID");

            int id = sc.nextInt();

            Project p = session.get(Project.class, id);

            if(p!=null)
            {
                System.out.println("ID : "+p.getId());
                System.out.println("Name : "+p.getName());
                System.out.println("Description : "+p.getDescription());
                System.out.println("Status : "+p.getStatus());
            }
            else
            {
                System.out.println("Record Not Found");
            }
        }

        session.close();
        sf.close();
    }
}