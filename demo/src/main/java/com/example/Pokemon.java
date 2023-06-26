package com.example;

public class Pokemon {
    private int pokemonIndex;
    private String pokemonName;
    private String pokemonType;
    private String pokemonElementP;
    private String pokemonElementS;
    private String description;
    private String imagePath;

    public Pokemon(int pokemonIndex, String pokemonName, String pokemonType, String pokemonElementP, String pokemonElementS, String description, String imagePath) {
        this.pokemonIndex = pokemonIndex;
        this.pokemonName = pokemonName;
        this.pokemonType = pokemonType;
        this.pokemonElementP = pokemonElementP;
        this.pokemonElementS = pokemonElementS;
        this.description = description;
        this.imagePath = imagePath;
    }

    public int getPokemonIndex() {
        return pokemonIndex;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public String getPokemonType() {
        return pokemonType;
    }

    public String getPokemonElementP() {
        return pokemonElementP;
    }

    public String getPokemonElementS() {
        return pokemonElementS;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }
}