package com.loycifer.iqdb.inspirationalquote;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class InspirationalQuote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String quote;

    private String author;

    private boolean isCustom;

    public InspirationalQuote() {

    }

    public InspirationalQuote(String quote, String author, boolean isCustom) {
        this.quote = quote;
        this.author = author;
        this.isCustom = isCustom;
    }


}