package com.loycifer.iqdb.service;

import com.loycifer.iqdb.db.seed.Seed;
import com.loycifer.iqdb.db.seed.Seeder;
import com.loycifer.iqdb.model.InspirationalQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InitService {
    Seeder seeder;

    @Autowired
    public InitService(Seeder seeder) {
        this.seeder = seeder;

        Seed quoteSeed = new Seed("quoteseed000");


        ArrayList<InspirationalQuote> builtinQuotes = new ArrayList<>();

        builtinQuotes.add(new InspirationalQuote("Drumsticks can also be chicken.", "Tardy the Turtle", false));
        builtinQuotes.add(new InspirationalQuote("Who can, like, never be sure?", "Liz", false));
        builtinQuotes.add(new InspirationalQuote("The higher, the fewer.", "Alexander Rozhenko", false));
        builtinQuotes.add(new InspirationalQuote("Is mayonnaise an instrument?", "Patrick Star", false));
        quoteSeed.setInspirationalQuotes(builtinQuotes);
        seeder.addSeed(quoteSeed);
        seeder.seedData();

    }
}
