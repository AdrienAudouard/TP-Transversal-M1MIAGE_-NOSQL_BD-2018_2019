package com.miage.bigdata.mains;

import com.miage.bigdata.controllers.GraphController;
import com.miage.bigdata.controllers.GraphItemController;
import com.miage.bigdata.models.graph.Person;
import com.miage.bigdata.models.graph.Post;
import com.miage.bigdata.models.graph.Tag;

import java.util.Date;

public class GraphMain {
    public static void main(String[] args) throws InterruptedException {
        GraphController graphController = new GraphController();
        GraphItemController<Person> pController = (GraphItemController<Person>) graphController.getItemController(Person.class);
        GraphItemController<Tag> tController = (GraphItemController<Tag>) graphController.getItemController(Tag.class);
        GraphItemController<Post> poController = (GraphItemController<Post>) graphController.getItemController(Post.class);

        Person p1 = new Person("1", "adrien", "audouard", "male"
                , new Date(), "Nice", "chrome", 1);
        Person p2 = new Person("2", "victor", "monsch", "male"
                , new Date(), "Nice", "chrome", 1);
        Person p3 = new Person("3", "jeremy", "primat", "male"
                , new Date(), "St lo", "chrome", 1);
        Person p4 = new Person("4", "benji", "dk", "male"
                , new Date(), "St lo", "chrome", 1);
        Person p5 = new Person("5", "romain", "lambo", "male"
                , new Date(), "St lo", "chrome", 1);

        Tag t1 = new Tag("6", "Tag 1");
        Tag t2 = new Tag("7", "Tag 2");
        Tag t3 = new Tag("8", "Tag 3");
        Tag t4 = new Tag("9", "Tag 4");
        Tag t5 = new Tag("10", "Tag 5");
        Tag t6 = new Tag("11", "Tag 6");

        Post po1 = new Post("12", "Nice", "Chrome", "kjbhjn", 10);
        Post po2 = new Post("13", "St lo", "Firefix", "kjbhjn", 10);
        Post po3 = new Post("14", "La Colle", "IE", "kjbhjn", 10);
        Post po4 = new Post("15", "Marseille", "Vivaldi", "kjbhjn", 10);


        System.out.println("------------ Delete table ------------");

        System.out.println("Success: " + pController.deleteTable());
        System.out.println("Success: " + tController.deleteTable());
        System.out.println("Success: " + poController.deleteTable());

        Thread.sleep(100);

        System.out.println("------------ Create table ------------");

        System.out.println("Success: " + pController.createTable());
        System.out.println("Success: " + tController.createTable());
        System.out.println("Success: " + poController.createTable());

        Thread.sleep(100);

        System.out.println("------------ Add person ------------");

        pController.create(p1, p2, p3, p4, p5);

        Thread.sleep(100);

        System.out.println(pController.readAll());

        Thread.sleep(100);

        System.out.println("------------ Get perso with id 3 ------------");

        System.out.println(pController.getByID("3"));

        Thread.sleep(100);

        System.out.println("------------ Update perso with id 3 ------------");

        p3.setFirstName("Zouz");
        pController.update(p3);

        System.out.println(pController.getByID("3"));

        Thread.sleep(100);

        System.out.println("------------ Set perso 1 and 2 to know tag 3 ------------");

        pController.addKnow(p3, p1);
        Thread.sleep(100);
        pController.addKnow(p3, p2);
        Thread.sleep(100);

        System.out.println("------------ Person that knows 3 ------------");

        System.out.println(pController.knows(p3));

        Thread.sleep(100);

        System.out.println("------------ Drop item with id 3 ------------");

        pController.delete("3");

        System.out.println(pController.readAll());

        Thread.sleep(100);

        System.out.println("------------ Create Tag ------------");

        tController.create(t1, t2, t3, t4, t5, t6);

        Thread.sleep(100);

        System.out.println(tController.readAll());

        System.out.println("------------ 1, 2 and 4 has Tag 8 ------------");

        tController.addHas(p1, t3);
        Thread.sleep(100);
        tController.addHas(p2, t3);
        Thread.sleep(100);
        tController.addHas(p4, t3);
        Thread.sleep(100);

        System.out.println("------------ Person that has Tag 8 ------------");

        System.out.println(tController.peoplesHas(t3));
        Thread.sleep(100);

        System.out.println("------------ perso 1 has Tag 8, 9, 10 ------------");

        tController.addHas(p1, t4);
        Thread.sleep(100);
        tController.addHas(p1, t5);
        Thread.sleep(100);

        System.out.println("------------ Tag that has perso 1 ------------");

        System.out.println(tController.tagsOn(p1));
        Thread.sleep(100);

        System.out.println("------------ Create Post ------------");
        poController.create(po1, po2, po3, po4);
        System.out.println(poController.readAll());
        Thread.sleep(100);

        System.out.println("------------ post 12 has Tag 8, 9 ------------");

        poController.addHas(po1, t3);
        Thread.sleep(100);
        poController.addHas(po1, t4);
        Thread.sleep(100);

        System.out.println("------------ Post that has Tag 8 ------------");

        System.out.println(poController.postHas(t3));
        Thread.sleep(100);

        System.out.println("------------ Tags on  post 12------------");

        System.out.println(poController.tagsOn(po1));
        Thread.sleep(100);

        System.out.println("------------ Person 1 has created post 12------------");

        poController.addcreated(po1, p1);
        Thread.sleep(100);

        System.out.println("------------ Who create post 12------------");
        System.out.println(poController.createdBy(po1));
        Thread.sleep(100);

        System.out.println("------------ Created by perso 1 ------------");

        System.out.println(poController.hasCreated(p1));
        Thread.sleep(100);
    }
}
