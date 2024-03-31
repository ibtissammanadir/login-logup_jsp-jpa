package com.example.demo11.Beans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;
import java.util.Locale;

@ManagedBean(name = "language")
@SessionScoped
public class LanguageBean implements Serializable {

    private String locale = "fr";

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }


    public void changeLanguage(String language) {
        locale = language;
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }
}
