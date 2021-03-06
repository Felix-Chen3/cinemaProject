/**
 * @author Felix
 * @describe
 * @date 2020/6/25 12:16
 */
package felix.entity;



import java.text.ParseException;
import java.util.Objects;

public class Movie {
    private int id;
    private String name;
    private String type;
    private String director;
    private String protagonist;
    private String duration;
    private String detail;
    private double score;
    private String labels;
    private boolean isReleased = true;

    public Movie(String name, String type, String director, String protagonist, String duration, String detail, double score, String labels, boolean isReleased) {
        this.name = name;
        this.type = type;
        this.director = director;
        this.protagonist = protagonist;
        this.duration = duration;
        this.detail = detail;
        this.score = score;
        this.labels = labels;
        this.isReleased = isReleased;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(name, movie.name) &&
                Objects.equals(director, movie.director);
    }

    public boolean isReleased() {
        return isReleased;
    }

    public void setReleased(boolean released) {
        isReleased = released;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, director);
    }

    public Movie(String name, String type, String director, String protagonist, String duration, String detail, double score, String labels)  {
        this.name = name;
        this.type = type;
        this.director = director;
        this.protagonist = protagonist;
        this.duration = duration;
        this.detail = detail;
        this.score = score;
        this.labels = labels;
    }

    public Movie(String name, String type, String director, String protagonist, String duration, String detail, double score)  {
        this.name = name;
        this.type = type;
        this.director = director;
        this.protagonist = protagonist;
        this.duration = duration;
        this.detail = detail;
        this.score = score;
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

    public String getProtagonist() {
        return protagonist;
    }

    public void setProtagonist(String protagonist) {
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

    public Movie() throws ParseException {
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

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "电影{" +
                "id=" + id +
                ", 片名(name)='" + name + '\'' +
                ", 类型(type)='" + type + '\'' +
                ", 导演(director)='" + director + '\'' +
                ", 主演(protagonist)=" + protagonist +
                ", 时长(duration)='" + duration + '\'' +
                ", 简介(detail)='" + detail + '\'' +
                ", 评分(score)=" + score +
                ", 标签(labels)=" + labels +
                '}';
    }

}
