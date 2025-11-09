package com.WebTechProjekt.Expense_Tracker.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    //@Id
    private Long id;
    private String name;
    private String description;
    private String color;      // z. B. für visuelle Darstellung im Frontend
    private String owner;
    //private User user;         // falls jede*r Nutzer*in eigene Kategorien hat
}
