package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

@Controller
public class AccidentControl {
    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String createForm() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        accidentService.add(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String updateForm(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id));
        return "accident/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") int id, @ModelAttribute Accident accident) {
        accidentService.update(id, accident);
        return "redirect:/";
    }
}
