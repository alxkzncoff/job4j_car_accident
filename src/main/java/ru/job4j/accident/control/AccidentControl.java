package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AccidentControl {
    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("types", accidentService.findAllTypes());
        model.addAttribute("rules", accidentService.findAllRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        Accident rsl;
        rsl = addTypeAndRules(accident, req);
        accidentService.add(rsl);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String updateForm(@RequestParam("id") int id, Model model) {
        Accident accident = accidentService.findById(id);
        model.addAttribute("accident", accident);
        model.addAttribute("types", accidentService.findAllTypes());
        model.addAttribute("rules", accidentService.findAllRules());
        return "accident/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") int id, @ModelAttribute Accident accident,
                         HttpServletRequest req) {
        Accident rsl;
        rsl = addTypeAndRules(accident, req);
        accidentService.update(id, rsl);
        return "redirect:/";
    }

    /**
     * Метод заполняет поля type и rules в объект класс Accident.
     * @param accident Объект класса Accident.
     * @param req HttpServletRequest.
     * @return Обновленный объект класса Accident
     */
    private Accident addTypeAndRules(Accident accident, HttpServletRequest req) {
        List<AccidentType> types = accidentService.findAllTypes();
        Set<Rule> rules = new HashSet<>();
        String[] ids = req.getParameterValues("ruleIds");
        accident.setType(accidentService.findTypeById(accident.getType().getId()));
        if (ids != null) {
            Arrays.stream(ids).forEach(ruleId -> rules.add(accidentService.findRuleById(Integer.parseInt(ruleId))));
        }
        accident.setRules(rules);
        return accident;
    }
}
