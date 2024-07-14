package com.loycifer.iqdb.db.seed;

import com.loycifer.iqdb.model.InspirationalQuote;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Entity
public class Seed {
    @Id
    private String name;

    @Transient
    @Setter
    private ArrayList<InspirationalQuote> inspirationalQuotes;

    public Seed() {

    }

    public Seed(String name) {
        this.name = name;
    }

}
