package com.example.producingwebservice.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
// Add these two imports for your SOAP classes:
import com.example.producingwebservice.CountryRepository;
import io.spring.guides.gs_producing_web_service.Country;

@Controller
public class CountryWebController {

    @Autowired
    private CountryRepository countryRepository; // Your existing repository

    @GetMapping("/")
    public String home(Model model) {
        return "search-country";
    }

    @PostMapping("/search")
    public String searchCountry(@RequestParam String countryName, Model model) {
        try {
            // Call your existing SOAP service logic here
            // This depends on how your CountryRepository works
            Country country = countryRepository.findCountry(countryName);
            
            if (country != null) {
                model.addAttribute("country", country);
            } else {
                model.addAttribute("error", "Country not found : " + countryName);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error searching for country : " + e.getMessage());
        }
        
        return "search-country";
    }
}