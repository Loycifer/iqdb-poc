package com.loycifer.iqdb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
public class InspirationalQuote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String quote;

    @Getter
    @Setter
    private String author;

    @Getter
    @Setter
    private boolean isCustom;

    public InspirationalQuote() {

    }

    public InspirationalQuote(String quote, String author, boolean isCustom) {
        this.quote = quote;
        this.author = author;
        this.isCustom = isCustom;
    }


}