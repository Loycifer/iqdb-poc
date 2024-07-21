package com.loycifer.iqdb.api;

import com.loycifer.iqdb.inspirationalquote.InspirationalQuote;
import com.loycifer.iqdb.inspirationalquote.InspirationalQuoteService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping(path = "/inspirational-quote")
public class InspirationalQuoteController {
    private InspirationalQuoteService inspirationalQuoteService;

    @Autowired
    public InspirationalQuoteController(InspirationalQuoteService inspirationalQuoteService) {
        this.inspirationalQuoteService = inspirationalQuoteService;
    }

    @PostMapping(path = "/create")
    public @ResponseBody String create(@RequestBody InspirationalQuote inspirationalQuote) {
        inspirationalQuote.setCustom(true);
        inspirationalQuoteService.create(inspirationalQuote);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<InspirationalQuote> getAll() {
        return inspirationalQuoteService.getAll();
    }

    @PostMapping(path = "/random")
    public @ResponseBody InspirationalQuote getRandom(@RequestBody getRandomForm getRandomForm) {
        InspirationalQuote quote = inspirationalQuoteService.getRandom(getRandomForm.alreadySeen);
        if (quote == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return quote;
    }

    @Setter
    @Getter
    public static class getRandomForm {
        private int[] alreadySeen;
    }
}

