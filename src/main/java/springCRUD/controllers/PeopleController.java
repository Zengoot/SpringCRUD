package springCRUD.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springCRUD.DAO.PersonDAO;
import springCRUD.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    //Внедрение зависимости через SPRING
    private final PersonDAO personDAO;
    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping() // не указав адресс  в скобках, мы предполагаем доступ к этому методу
    // по адресу из RequestMapping
    public String index(Model model) {
        // Получаем всех людей из DAO и передаем на отображение в представление
        model.addAttribute("people", personDAO.getPeople());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String Show(@PathVariable("id") int id, Model model) {
        //Получим одного человека по его ID из DAO и передадим на
        // отображение в представление
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person,
                         @PathVariable("id") int id) {
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return"redirect:/people";
    }






}
