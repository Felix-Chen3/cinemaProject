/**
 * @author Felix
 * @describe
 * @date 2020/6/25 12:16
 */
package felix.entity;

import java.util.ArrayList;

public class Movie {
    private int id;
    private String name;
    private String type;
    private String director;
    private ArrayList<String> protagonist;
    private String duration;
    private String detail;
    private double score;
    private ArrayList<String> labels;

    public Movie(String name, String type, String director, ArrayList<String> protagonist, String duration, String detail, double score) {
        this.name = name;
        this.type = type;
        this.director = director;
        this.protagonist = protagonist;
        this.duration = duration;
        this.detail = detail;
        this.score = score;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", director='" + director + '\'' +
                ", protagonist=" + protagonist +
                ", duration='" + duration + '\'' +
                ", detail='" + detail + '\'' +
                ", score=" + score +
                ", labels=" + labels +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public ArrayList<String> getProtagonist() {
        return protagonist;
    }

    public void setProtagonist(ArrayList<String> protagonist) {
        this.protagonist = protagonist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public ArrayList<String> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<String> labels) {
        this.labels = labels;
    }

    public Movie(String name, String type, String director, ArrayList<String> protagonist, String duration, String detail, double score, ArrayList<String> labels) {
        this.name = name;
        this.type = type;
        this.director = director;
        this.protagonist = protagonist;
        this.duration = duration;
        this.detail = detail;
        this.score = score;
        this.labels = labels;
    }
}
