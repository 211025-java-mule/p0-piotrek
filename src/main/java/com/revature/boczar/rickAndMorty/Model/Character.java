package com.revature.boczar.rickAndMorty.Model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.net.URL;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode

public class Character {
    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private RmObject origin;
    private RmObject location;
    private String image;
    private List<URL> episode;
    private String url;
    private String created;
}

