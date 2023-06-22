package pl.agntyp.boardgames;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Boardgame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastTimePlayed;
    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne(cascade = CascadeType.MERGE)
    private AgeRange ageRange;
    @ManyToOne(cascade = CascadeType.MERGE)
    private PlayerRange playerRange;
    @OneToMany
    @JoinColumn(name = "boardgame_id")
    private List<Stats> stats = new ArrayList<>();

    private boolean myFavorite = false;
    private boolean myOwn = false;
    private String imageFile;

    private int minTime;
    private int maxTime;

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public AgeRange getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(AgeRange ageRange) {
        this.ageRange = ageRange;
    }

    public PlayerRange getPlayerRange() {
        return playerRange;
    }

    public void setPlayerRange(PlayerRange playerRange) {
        this.playerRange = playerRange;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public boolean isMyFavorite() {
        return myFavorite;
    }

    public void setMyFavorite(boolean myFavorite) {
        this.myFavorite = myFavorite;
    }

    public boolean isMyOwn() {
        return myOwn;
    }

    public void setMyOwn(boolean myOwn) {
        this.myOwn = myOwn;
    }

    public List<Stats> getStats() {
        return stats;
    }

    public void setStats(List<Stats> stats) {
        this.stats = stats;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getLastTimePlayed() {
        return lastTimePlayed;
    }

    public void setLastTimePlayed(LocalDate lastTimePlayed) {
        this.lastTimePlayed = lastTimePlayed;
    }
}
