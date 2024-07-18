package com.loycifer.iqdb.api;

import com.loycifer.iqdb.model.InspirationalQuote;
import com.loycifer.iqdb.service.InspirationalQuoteService;
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
    public @ResponseBody String create(@RequestParam String quote
            , @RequestParam String author) {

        InspirationalQuote inspirationalQuote = new InspirationalQuote(quote, author, true);
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

