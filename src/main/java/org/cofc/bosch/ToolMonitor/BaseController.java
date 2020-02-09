package org.cofc.bosch.ToolMonitor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {

    @RequestMapping("/welcome.html")
    public ModelAndView firstPage() {
        return new ModelAndView("welcome");
    }

    @GetMapping
    public String workPieceCarrierForm(Model model) {
        model.addAttribute("work piece carrier", new WorkPieceCarrier());
        return "workPieceCarrierForm";
    }

    @PostMapping
    public String workPieceCarrierSubmit(@ModelAttribute WorkPieceCarrier carrier) {


        return "workPieceCarrierSubmission";
    }

}