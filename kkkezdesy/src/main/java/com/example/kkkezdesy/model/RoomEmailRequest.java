package com.example.kkkezdesy.model;

import com.example.kkkezdesy.entities.Interest;
import lombok.Data;

import javax.persistence.Column;
import java.util.Set;

@Data
public class RoomEmailRequest {
    private String email;
    private String city;
    private String header;
    private String description;
    private int minAgeLimit;
    private int maxAgeLimit;
    private int maxMembers;
    private Set<Interest> interests;
}
