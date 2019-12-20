package com.improving.bootcamp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.improving.bootcamp.api.JsonViews;

import javax.validation.constraints.NotEmpty;

@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy.class)
public class Book {
    @JsonView({JsonViews.SummaryView.class, JsonViews.DetailsView.class})
    @NotEmpty(message = "Enter a title pretty please...") //can customize the error message here
    private final String bookTitle;

    @JsonView({JsonViews.SummaryView.class, JsonViews.DetailsView.class})
    @NotEmpty(message = "Enter a author pretty please...")
    private final String bookAuthor;

    @JsonView({JsonViews.DetailsView.class})
    private float Price;


    @JsonCreator
    public Book(String bookTitle, String bookAuthor) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
    }

    @JsonGetter("thisIsATitle") //this becomes the new title on JSON object..allows for customization/flexibility if you really want to keep your Java method name as it is
    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public float getPrice() {
        return Price;
    }
}
