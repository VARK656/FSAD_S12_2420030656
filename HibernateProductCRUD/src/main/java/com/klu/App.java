package com.klu;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {
    public static void main(String[] args) {
        Session session = null;
        Transaction tx = null;
        Integer laptopId = null;  // ← Use Integer

        // 1️⃣ INSERT Products
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Product p1 = new Product("Laptop", "Gaming Laptop", 75000, 10);
            Product p2 = new Product("Mouse", "Wireless Mouse", 800, 50);
            Product p3 = new Product("Keyboard", "Mechanical Keyboard", 2500, 30);

            session.save(p1);
            session.save(p2);
            session.save(p3);

            tx.commit();
            System.out.println("✅ Products Inserted Successfully!");

            laptopId = p1.getId();
            System.out.println("Inserted Laptop ID: " + laptopId);

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        // 2️⃣ RETRIEVE Product
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Product retrieved = session.get(Product.class, laptopId);
            if (retrieved != null) {
                System.out.println("✅ Retrieved Product: " + retrieved);
            } else {
                System.out.println("❌ No product found with ID: " + laptopId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        // 3️⃣ UPDATE Product
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Product toUpdate = session.get(Product.class, laptopId);
            if (toUpdate != null) {
                toUpdate.setPrice(72000);
                toUpdate.setQuantity(8);
                session.update(toUpdate);
                tx.commit();
                System.out.println("✅ Product Updated Successfully: " + toUpdate);
            } else {
                System.out.println("❌ Product not found for update");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        // 4️⃣ DELETE Product
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Product toDelete = session.get(Product.class, laptopId);
            if (toDelete != null) {
                session.delete(toDelete);
                tx.commit();
                System.out.println("✅ Product Deleted Successfully (ID: " + laptopId + ")");
            } else {
                System.out.println("❌ Product not found for deletion");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        System.out.println("🎉 All CRUD Operations Completed Successfully!");
    }
}