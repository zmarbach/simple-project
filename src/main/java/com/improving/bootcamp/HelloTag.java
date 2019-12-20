package com.improving.bootcamp;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Random;

public class HelloTag extends SimpleTagSupport {
    private String name;

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        String css = "";
        int randomNum = new Random().nextInt(100) + 1;
        if(randomNum > 50) {
            css = "<style> h1 {color: red;} </style>";
        }
        else {
            css = "<style> h1 {color: green;} </style>";
        }
        out.println(css + " " + name);
    }

    public void setName(String name) {
        this.name = name;
    }
}
