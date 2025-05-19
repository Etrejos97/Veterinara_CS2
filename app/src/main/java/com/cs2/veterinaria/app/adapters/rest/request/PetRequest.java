package com.cs2.veterinaria.app.adapters.rest.request;

public class PetRequest {
    private String name;
    private Long idOwner;
    private int age;
    private String species;
    private String race;
    private String characteristics;
    private float weight;

    // Getters y setters
    public String getName() { 
        return name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    public Long getIdOwner() { 
        return idOwner; 
    }
    public void setIdOwner(Long idOwner) { 
        this.idOwner = idOwner; 
    }
    public int getAge() { 
        return age; 
    }
    public void setAge(int age) { 
        this.age = age; 
    }
    public String getSpecies() { 
        return species; 
    }
    public void setSpecies(String species) { 
        this.species = species; 
    }
    public String getRace() { 
        return race; 
    }
    public void setRace(String race) { 
        this.race = race; 
    }
    public String getCharacteristics() { 
        return characteristics; 
    }
    public void setCharacteristics(String characteristics) { 
        this.characteristics = characteristics; 
    }
    public float getWeight() { 
        return weight; 
    }
    public void setWeight(float weight) { 
        this.weight = weight; 
    }
}
