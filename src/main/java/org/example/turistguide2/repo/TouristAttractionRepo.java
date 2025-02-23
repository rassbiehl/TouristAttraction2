package org.example.turistguide2.repo;

import org.example.turistguide2.model.Tags;
import org.example.turistguide2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristAttractionRepo {
    private final List<TouristAttraction> touristAttractionList;

    public TouristAttractionRepo() {
        // Bruger ArrayList i stedet for List.of(), da ArrayList er ændringsbar
        this.touristAttractionList = new ArrayList<>();
        touristAttractionList.add(new TouristAttraction("Tivoli", "Amusement park in Copenhagen", "Copenhagen", List.of(Tags.CHILD_FRIENDLY)));
        touristAttractionList.add(new TouristAttraction("Amalienborg", "Where the king and queen live", "Copenhagen", List.of(Tags.ROYAL_FAMILY, Tags.CULTURAL_INHERITANCE, Tags.HISTORY)));
        touristAttractionList.add(new TouristAttraction("Viking Ship Museum", "A museum about the Vikings and their ships", "Roskilde", List.of(Tags.HISTORY, Tags.MUSEUM, Tags.CHILD_FRIENDLY, Tags.CULTURAL_INHERITANCE)));
        touristAttractionList.add(new TouristAttraction("Legoland Billund", "A theme park based on Lego bricks", "Billund", List.of(Tags.CHILD_FRIENDLY, Tags.CULTURAL_INHERITANCE)));
        touristAttractionList.add(new TouristAttraction("Aarhus Art Museum (ARoS)", "A modern art museum with the famous rainbow panorama", "Aarhus", List.of(Tags.ART, Tags.MUSEUM)));
        touristAttractionList.add(new TouristAttraction("Kronborg Castle", "The castle that inspired Shakespeare's Hamlet", "Helsingør", List.of(Tags.HISTORY, Tags.CULTURAL_INHERITANCE, Tags.ROYAL_FAMILY)));
        touristAttractionList.add(new TouristAttraction("Ribe Viking Center", "An open-air museum showcasing Viking life", "Ribe", List.of(Tags.HISTORY, Tags.CHILD_FRIENDLY, Tags.CULTURAL_INHERITANCE)));
        touristAttractionList.add(new TouristAttraction("Skagen Grenen", "The northernmost point of Denmark where two seas meet", "Skagen", List.of(Tags.NATURE, Tags.GRATIS)));
        touristAttractionList.add(new TouristAttraction("Den Gamle By", "An open-air museum depicting Danish urban history", "Aarhus", List.of(Tags.HISTORY, Tags.MUSEUM, Tags.CULTURAL_INHERITANCE)));
        touristAttractionList.add(new TouristAttraction("Møns Klint", "Beautiful white chalk cliffs with stunning nature views", "Møn", List.of(Tags.NATURE, Tags.GRATIS)));
    }

    public synchronized List<TouristAttraction> getTouristAttractionList() {
        return new ArrayList<>(touristAttractionList); // Returnér en kopi for at undgå utilsigtet ændring
    }

    public synchronized void addTouristAttractionToList(TouristAttraction touristAttraction) {
        touristAttractionList.add(touristAttraction);  // Tilføjer korrekt til en ArrayList
    }

    public synchronized boolean deleteTouristAttractionFromList(String name) {
        return touristAttractionList.removeIf(touristAttraction -> touristAttraction.getName().equalsIgnoreCase(name));
    }

    public synchronized TouristAttraction findAttractionByName(String name) {
        return touristAttractionList.stream()
                .filter(touristAttraction -> touristAttraction.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public synchronized void updateTouristAttraction(TouristAttraction updatedAttraction) {
        for (int i = 0; i < touristAttractionList.size(); i++) {
            if (touristAttractionList.get(i).getName().equalsIgnoreCase(updatedAttraction.getName())) {
                // Erstat den gamle attraktion med den nye version
                touristAttractionList.set(i, updatedAttraction);
                return;
            }
        }
    }
}
