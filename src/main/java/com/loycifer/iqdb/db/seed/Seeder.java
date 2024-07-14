package com.loycifer.iqdb.db.seed;

import com.loycifer.iqdb.model.InspirationalQuote;
import com.loycifer.iqdb.service.InspirationalQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class Seeder {
    private final InspirationalQuoteService inspirationalQuoteService;
    private final SeedRepository seedRepository;

    private final ArrayList<Seed> seeds = new ArrayList<>();

    @Autowired
    public Seeder(InspirationalQuoteService inspirationalQuoteService, SeedRepository seedRepository) {
        this.inspirationalQuoteService = inspirationalQuoteService;
        this.seedRepository = seedRepository;
    }

    public Seeder addSeed(Seed seed) {
        this.seeds.add(seed);
        return this;
    }

    public void seedData() {
        for (Seed seed : this.seeds) {
            Optional<Seed> seedOptional = seedRepository.findById(seed.getName());

            if (seedOptional.isEmpty()) {
                for (InspirationalQuote inspirationalQuote : seed.getInspirationalQuotes()) {
                    inspirationalQuoteService.create(inspirationalQuote);
                }
                seedRepository.save(seed);
            }
        }
    }
}
