package org.openjfx.mynotesjavafx.model;

public class Note {
    private String header;

    private String note;

    public Note(String header, String note) {
        this.header = header;
        this.note = note;
    }

    public String getHeader() {
        return header;
    }

    public String getNote() {
        return note;
    }
}
