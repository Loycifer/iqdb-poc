package com.loycifer.iqdb.service;

import com.loycifer.iqdb.model.InspirationalQuote;
import com.loycifer.iqdb.repository.InspirationalQuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InspirationalQuoteService {
    private final InspirationalQuoteRepository inspirationalQuoteRepository;

    @Autowired
    public InspirationalQuoteService(InspirationalQuoteRepository inspirationalQuoteRepository) {
        this.inspirationalQuoteRepository = inspirationalQuoteRepository;
    }


    public String create(InspirationalQuote inspirationalQuote) {
        inspirationalQuoteRepository.save(inspirationalQuote);
        return "Saved";
    }

    public Iterable<InspirationalQuote> getAll() {
        return inspirationalQuoteRepository.findAll();
    }

    public InspirationalQuote getRandom(int[] alreadySeen) {
        if (alreadySeen.length == 0) {
            return inspirationalQuoteRepository.getRandom();
        }
        return inspirationalQuoteRepository.getRandom(alreadySeen);
    }
}
