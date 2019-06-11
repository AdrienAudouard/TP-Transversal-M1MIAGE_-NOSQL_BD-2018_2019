package com.miage.bigdata.controllers.item;

import com.miage.bigdata.daos.itemDao.graph.GraphObjectDao;
import com.miage.bigdata.models.Item;
import com.miage.bigdata.models.graph.GraphItem;
import com.miage.bigdata.models.graph.Person;
import com.miage.bigdata.models.graph.Post;
import com.miage.bigdata.models.graph.Tag;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GraphItemController<T extends GraphItem> extends ItemController<T> {

    public GraphItemController(GraphObjectDao objectDao) {
        super(objectDao);
    }

    public void addKnow(Person p1, Person p2) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        graphObjectDao.addKnow(p1, p2);
    }

    public List<Person> knows(Person p1) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        return graphObjectDao.knows(p1);
    }

    public void addHas(Person p, Tag t) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        graphObjectDao.addHas(p, t);
    }

    public void addHas(Post p, Tag t) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        graphObjectDao.addHas(p, t);
    }

    public List<Person> peoplesHas(Tag tag) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        return graphObjectDao.peoplesHas(tag);
    }

    public List<Tag> tagsOn(Person p) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        return graphObjectDao.tagsOn(p);
    }

    public List<Tag> tagsOn(Post p) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        return graphObjectDao.tagsOn(p);
    }

    public List<Tag> postHas(Tag t) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        return graphObjectDao.postHas(t);
    }

    public void addcreated(Post post, Person person) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        graphObjectDao.addCreated(post, person);
    }

    public List<Person> createdBy(Post post) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        return graphObjectDao.createdBy(post);
    }

    public List<Post> hasCreated(Person person) {
        GraphObjectDao graphObjectDao = (GraphObjectDao) objectDao;
        return graphObjectDao.hasCreated(person);
    }

    public void createpostHasCreator(List<Person> persons, List<Post> posts) {
        String filePath = Item.getDataPath() + "socialNetwork/post_hasCreator_person_0_0.csv";
        double succes = 0;
        double fail = 0;
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(filePath));
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .withCSVParser(
                            new CSVParserBuilder()
                                    .withSeparator('|')
                                    .build()
                    ).build();

            String[] nextRecord;

            csvReader.readNext();

            while ((nextRecord = csvReader.readNext()) != null) {
                String postID = nextRecord[0];
                String personID = nextRecord[1];

                Person p1 = persons.stream().filter(person -> person.getItemId().equals(personID)).findAny().orElse(null);
                Post p2 = posts.stream().filter(person -> person.getItemId().equals(postID)).findAny().orElse(null);

                if (p1 != null && p2 != null) {
                    addcreated(p2, p1);
                    succes ++;
                } else {
                    fail++;
                }
            }

            System.out.println("Add created relations success rate: " + (succes / (succes + fail) * 100.0) + "%");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createPersonKnowsPerson(List<Person> persons) {
        String filePath = Item.getDataPath() + "socialNetwork/person_knows_person_0_0.csv";
        double succes = 0;
        double fail = 0;
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(filePath));
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .withCSVParser(
                            new CSVParserBuilder()
                                    .withSeparator('|')
                                    .build()
                    ).build();

            String[] nextRecord;

            csvReader.readNext();

            while ((nextRecord = csvReader.readNext()) != null) {
                String person1ID = nextRecord[0];
                String person2ID = nextRecord[1];

                Person p1 = persons.stream().filter(person -> person.getItemId().equals(person1ID)).findAny().orElse(null);
                Person p2 = persons.stream().filter(person -> person.getItemId().equals(person2ID)).findAny().orElse(null);

                if (p1 != null && p2 != null) {
                    addKnow(p1, p2);
                    succes ++;
                } else {
                    fail++;
                }
            }

            System.out.println("Add knows relations success rate: " + (succes / (succes + fail) * 100.0) + "%");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
