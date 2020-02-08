package com.example.demo;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="book")
public class Book {
    @Id
    private int id;


    @NotBlank
    private String name;
    private String author;



    public Book() {
        super();
    }

    public Book(int id, String name, String author) {
        super();
        this.id = id;
        this.name = name;
        this.author=author;

    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {

        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }



}

