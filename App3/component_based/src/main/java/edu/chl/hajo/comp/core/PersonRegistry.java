package edu.chl.hajo.comp.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * This is part of the server side model 
 *
 * This will later be handled by the database
 *
 * @author hajo
 */
@Named
@ApplicationScoped
public class PersonRegistry {

    private final List<Person> data = new ArrayList<>();

    @PostConstruct
    public void post() {
        generateData();
    }

    public void create(Person p) {
        if (data.contains(p)) {
            throw new IllegalArgumentException("Person already exists with id "
                    + p.getId());
        }
        data.add(p);
    }

    public void update(Person p) {
        Person old = find(p.getId());
        int i = data.indexOf(old);
        if (i > -1) {
            data.set(i, p);
        }
    }

    public void delete(Long id) {
        Person p = find(id);
        if (p != null) {
            data.remove(p);
        }
    }

    public Person find(Long id) {
        for (Person p : data) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public Collection<Person> findAll() {
        //return Collections.unmodifiableCollection(data); // Can't have this !?!
        return data;
    }

    public Collection<Person> findRange(int first, int n) {
        // Non structural changes on sublist will be reflected in original.
        // This is zerobased index
        if (first + n < data.size()) {
            return data.subList(first, first + n);
        } else {
            return data.subList(first, data.size());
        }
    }

    public int count() {
        return data.size();
    }

    // Emulating a database
    private void generateData() {
        String s = "Abbe Abel Abraham Acke Ada Adalbert Adam Adela Adele Adelia"
                + " Adelina Berit Berna Bernhard Bernhardina Bernt Berta"
                + " Bertel Berthold Bertil Bertina Bessie Beth Betsy Bettina"
                + " Cilla Cissi Claes Claire Clarence Clarie Daniel Daniela"
                + " Danny David Denise Elias Elin Elisa Erland Filip Filippa"
                + " Fingal Finn Gabriel Gabriella Gabrielle Gaby Hannes Hannu"
                + " Hanny Hans Harald Harold Ingegerd Ingela Ingeman Ingemar"
                + " Jan Jane Janet Janna Karin Karl Karla Karna Karolin Larry"
                + " Lars Lasse Laura Magnus Maisie Mait Maj Ninna Ninni Ninnie"
                + " Nisse Noomi Otto Ove Patrik Paul Paula Rakel Ralf Raoul Sabina"
                + " Sabine Salli Teddy Tekla Teodor Teodora Uno Urban Valfrida"
                + " Valle Yvette Yvonne";
        String[] names = s.split(" ");
        long id = 1L;
        Random r = new Random();
        for (String name : names) {
            data.add(new Person(id++, name, r.nextInt(60) + 15));
        }

    }

}
