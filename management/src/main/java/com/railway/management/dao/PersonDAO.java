package com.railway.management.dao;

import com.railway.management.entity.Person;

import java.util.List;

public interface PersonDAO {

    void savePerson(Person person);

    Person getPersonById(int id);

    List<Person> getAllPersons();

    void updatePerson(Person person);

    void deletePerson(int id);
}
