package com.kboxglobal.naydogdu.assignment.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class View {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.MERGE)
    private User viewer;
    @OneToOne(cascade = CascadeType.MERGE)
    private User viewee;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public View() {
    }

    public View(User viewer, User viewee) {
        this.viewer = viewer;
        this.viewee = viewee;
        this.date = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getViewer() {
        return viewer;
    }

    public void setViewer(User viewer) {
        this.viewer = viewer;
    }

    public User getViewee() {
        return viewee;
    }

    public void setViewee(User viewee) {
        this.viewee = viewee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        View view = (View) o;
        return getId() == view.getId() &&
                getViewer().equals(view.getViewer()) &&
                getViewee().equals(view.getViewee()) &&
                getDate().equals(view.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getViewer(), getViewee(), getDate());
    }
}
