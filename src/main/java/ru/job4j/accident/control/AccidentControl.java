package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.service.AccidentService;

import java.util.List;

@Controller
public class AccidentControl {
    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("types", accidentService.findAllTypes());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        List<AccidentType> types = accidentService.findAllTypes();
        accident.setType(accidentService.findTypeById(accident.getType().getId()));
        accidentService.add(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String updateForm(@RequestParam("id") int id, Model model) {
        Accident accident = accidentService.findById(id);
        model.addAttribute("accident", accident);
        model.addAttribute("types", accidentService.findAllTypes());
        return "accident/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") int id, @ModelAttribute Accident accident) {
        List<AccidentType> types = accidentService.findAllTypes();
        accident.setType(accidentService.findTypeById(accident.getType().getId()));
        accidentService.update(id, accident);
        return "redirect:/";
    }
}
