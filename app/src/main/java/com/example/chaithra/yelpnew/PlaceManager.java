package com.example.chaithra.yelpnew;

import java.util.ArrayList;

public class PlaceManager {
    static final PlaceManager Instance = new PlaceManager();
    private ArrayList<Place> listOfPlaces = new ArrayList<>();

    public Place getBook(int position) {
        return listOfPlaces.get(position);
    }

    public int totalBooks() {
        return listOfPlaces.size();
    }


    public void addPlaces(ArrayList<Place> books) {
        if (books != null) {
            listOfPlaces.addAll(books);
        }


    }

    public void removePlaces() {
        listOfPlaces.removeAll(listOfPlaces);
    }
}
