package org.cofc.bosch.ToolMonitor.controller;

import org.cofc.bosch.ToolMonitor.components.WorkPieceCarrier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class BaseController {

    private static String upload_dir = "F:/springfileupload/";

    @RequestMapping("/welcome")
    public ModelAndView firstPage() {
        return new ModelAndView("welcome");
    }

    @GetMapping("/workpiece")
    public String workPieceCarrierForm(Model model) {
        model.addAttribute("WorkPieceCarrier", new WorkPieceCarrier());
        return "workPieceCarrierForm";
    }

    @PostMapping("/workpiece")
    public String workPieceCarrierSubmit(@ModelAttribute WorkPieceCarrier carrier) {


        return "workPieceCarrierSubmission";
    }

    @RequestMapping(value = "/file/{fileName}")
    @ResponseBody
    public byte[] getFile(@PathVariable("fileName") String fileName) throws IOException {
        File file = new File(upload_dir+fileName);
        return Files.readAllBytes(file.toPath());
    }

}