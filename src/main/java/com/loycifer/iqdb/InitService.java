package com.loycifer.iqdb;

import com.loycifer.iqdb.db.seed.Seed;
import com.loycifer.iqdb.db.seed.Seeder;
import com.loycifer.iqdb.inspirationalquote.InspirationalQuote;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class InitService {
    Seeder seeder;

    @Autowired
    public InitService(Seeder seeder) {
        this.seeder = seeder;

        Seed quoteSeed = new Seed("quoteseed000");

        ArrayList<InspirationalQuote> builtinQuotes = new ArrayList<>();

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader("seed.csv"))
                .withCSVParser(new CSVParserBuilder()
                        .withSeparator(';')
                        .build()
                ).build()) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                if (!Objects.equals(values[0], "")) {
                    builtinQuotes.add(new InspirationalQuote(values[0], values[1], false));
                }
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }

        quoteSeed.setInspirationalQuotes(builtinQuotes);
        seeder.addSeed(quoteSeed);
        seeder.seedData();

    }
}
