package CompetitionPojo;

public class Competitions {

    private int id;
    private Area area;
    private String name;
    private String code;
    private String emblemUrl;
    private String plan;
    private CurrentSeason currentSeason;
    private int numberOfAvailableSeasons;
    private String lastUpdated;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmblemUrl() {
        return emblemUrl;
    }

    public void setEmblemUrl(String emblemUrl) {
        this.emblemUrl = emblemUrl;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public CurrentSeason getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(CurrentSeason currentSeason) {
        this.currentSeason = currentSeason;
    }

    public int getNumberOfAvailableSeasons() {
        return numberOfAvailableSeasons;
    }

    public void setNumberOfAvailableSeasons(int numberOfAvailableSeasons) {
        this.numberOfAvailableSeasons = numberOfAvailableSeasons;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
