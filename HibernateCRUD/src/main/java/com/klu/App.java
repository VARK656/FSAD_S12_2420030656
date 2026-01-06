package com.klu;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {
    public static void main(String[] args) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // 1️⃣ INSERT Student (manual ID as per your requirement)
            Student s1 = new Student();
            s1.setId(101);                    // Manually set ID to 101
            s1.setName("Ram");
            s1.setDept("CSE");

            session.save(s1);
            tx.commit();                      // Commit to make insert permanent
            System.out.println("✅ Student Inserted Successfully (ID: 101)");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return;  // Stop if insert fails
        } finally {
            if (session != null) session.close();
        }

        // 2️⃣ READ Student
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Student s = session.get(Student.class, 101);

            if (s != null) {
                System.out.println("✅ Retrieved: " + s.getName() + " from department " + s.getDept());
            } else {
                System.out.println("❌ No student found with ID 101");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        // 3️⃣ UPDATE Department to "AI & DS"
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Student s = session.get(Student.class, 101);
            if (s != null) {
                s.setDept("AI & DS");
                session.update(s);
                tx.commit();
                System.out.println("✅ Student Updated Successfully: " + s);
            } else {
                System.out.println("❌ Student not found for update");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        // Optional: Comment out DELETE if you want data to remain
        // 4️⃣ DELETE Student (COMMENT THIS BLOCK IF YOU WANT DATA TO STAY)
        /*
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Student s = session.get(Student.class, 101);
            if (s != null) {
                session.delete(s);
                tx.commit();
                System.out.println("✅ Student Deleted Successfully");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        */

        System.out.println("🎉 CRUD Operations Completed! Check MySQL: SELECT * FROM student;");
        System.out.println("   Expected: id=101, name=Ram, dept=AI & DS");
    }
}