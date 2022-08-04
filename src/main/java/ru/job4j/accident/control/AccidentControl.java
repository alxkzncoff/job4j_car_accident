package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.RuleService;
import ru.job4j.accident.service.TypeService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AccidentControl {
    private final AccidentService accidentService;
    private final TypeService typeService;
    private final RuleService ruleService;

    public AccidentControl(AccidentService accidentService, TypeService typeService, RuleService ruleService) {
        this.accidentService = accidentService;
        this.typeService = typeService;
        this.ruleService = ruleService;
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        accidentService.add(addTypeAndRules(accident, req));
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String updateForm(@RequestParam("id") int id, Model model) {
        Accident accident = accidentService.findById(id).get();
        model.addAttribute("accident", accident);
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "accident/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") int id, @ModelAttribute Accident accident,
                         HttpServletRequest req) {
        accidentService.add(addTypeAndRules(accident, req));
        return "redirect:/";
    }

    /**
     * Метод заполняет поля type и rules в объект класс Accident.
     * @param accident Объект класса Accident.
     * @param req HttpServletRequest.
     * @return Обновленный объект класса Accident
     */
    private Accident addTypeAndRules(Accident accident, HttpServletRequest req) {
        Set<Rule> rules = new HashSet<>();
        String[] ids = req.getParameterValues("ruleIds");
        accident.setType(typeService.findById(accident.getType().getId()).get());
        if (ids != null) {
            Arrays.stream(ids).forEach(ruleId -> rules.add(ruleService.findById(Integer.parseInt(ruleId)).get()));
            rules.forEach(accident::addRule);
        }
        return accident;
    }
}
