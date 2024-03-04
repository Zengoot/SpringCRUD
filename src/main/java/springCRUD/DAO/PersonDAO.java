package springCRUD.DAO;

import org.springframework.stereotype.Component;
import springCRUD.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int PEOPLE_COUNT;

    // В DAO обычно общаемся с базой данных
    private List<Person> people;


    //БЛОК инициализации
    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Kafka"));
        people.add(new Person(++PEOPLE_COUNT, "Black Swan"));
        people.add(new Person(++PEOPLE_COUNT, "Yae Miko"));
        people.add(new Person(++PEOPLE_COUNT, "Kequing"));
        people.add(new Person(++PEOPLE_COUNT, "Gan YU"));
        people.add(new Person(++PEOPLE_COUNT, "Shen He"));
        people.add(new Person(++PEOPLE_COUNT, "Xian Yun"));
    }

    public List<Person> getPeople() {
        return people;
    }

    //с помощью Лямбда-выражения
    public Person show(int id) {
        return people.stream().filter(person->person.getId() == id)
                .findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
    }

    public void delete (int id) {
        people.removeIf(p-> p.getId() == id); // удаление через лямбда-выражение
    }

}
