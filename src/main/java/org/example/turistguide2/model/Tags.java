package org.example.turistguide2.model;

public enum Tags {
    CHILD_FRIENDLY,
    GRATIS,
    ART,
    MUSEUM,
    NATURE,
    HISTORY,
    CULTURAL_INHERITANCE,
    ROYAL_FAMILY;

    public String toLowerCaseWithSpace(){
        return this.toString().toLowerCase().replace("_", " ");
    }
}
