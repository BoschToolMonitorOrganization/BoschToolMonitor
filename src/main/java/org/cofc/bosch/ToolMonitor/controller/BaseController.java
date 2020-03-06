package org.cofc.bosch.ToolMonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BaseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @GetMapping("/")
    public String homePage() {
        return "welcome";
    }

    @GetMapping("/welcome")
    public String firstPage() {
        return "welcome";
    }

    @RequestMapping(value = "/productionLines")
    @ResponseBody
    public List<String> getProductionLines(@RequestParam String valueStream) {
        return jdbcTemplate.queryForList("Select Distinct productionLine From WPCCombos where valueStream=\"" + valueStream + "\";", String.class);
    }

    @RequestMapping(value = "/productTypes")
    @ResponseBody
    public List<String> getRegions(@RequestParam String productionLine, @RequestParam String valueStream) {
        return jdbcTemplate.queryForList("Select Distinct productType From WPCCombos where valueStream=\"" + valueStream + "\" and productionLine=\"" + productionLine + "\";", String.class);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
