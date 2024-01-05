package com.example.backend.controller;

import com.example.backend.service.DatabaseFiller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class MainController {
    @Autowired
    private DatabaseFiller dbFiller;

    // TODO Add some functionality to separate Java and HTML.
    @GetMapping("/")
    public String homepage() {
        return "<button style=\"background-color: green;\" onclick=\"fillDatabase()\">Fill in Database</button>\n" +
                "\n" +
                "<script>\n" +
                "    function fillDatabase() {\n" +
                "        fetch('/fillDatabase', { method: 'POST' })\n" +
                "            .then(response => {\n" +
                "                if (response.ok) {\n" +
                "                    console.log('Database filled successfully');\n" +
                "                    location.reload();\n" +
                "                } else {\n" +
                "                    console.error('Error filling database');\n" +
                "                }\n" +
                "            })\n" +
                "            .catch(error => console.error('Error:', error));\n" +
                "    }\n" +
                "</script>";
    }

    @PostMapping("/fillDatabase")
    public String fillDatabase() {
        try {
            this.dbFiller.insertDummyData();
        }
        catch(Exception e) {
            return e.getMessage();
        }

        return "redirect:/home";
    }

}