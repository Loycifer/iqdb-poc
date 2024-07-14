package com.loycifer.iqdb.service;

import com.loycifer.iqdb.model.InspirationalQuote;
import com.loycifer.iqdb.repository.InspirationalQuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class InspirationalQuoteService {
    private InspirationalQuoteRepository inspirationalQuoteRepository;

    @Autowired
    public InspirationalQuoteService(InspirationalQuoteRepository inspirationalQuoteRepository) {
        this.inspirationalQuoteRepository = inspirationalQuoteRepository;
    }


    public String create (InspirationalQuote inspirationalQuote) {
       inspirationalQuoteRepository.save(inspirationalQuote);
        return "Saved";
    }


    public Iterable<InspirationalQuote> getAll() {
        return inspirationalQuoteRepository.findAll();
    }
}
