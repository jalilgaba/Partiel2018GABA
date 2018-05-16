package partiel2018gaba.gaba.diiage.org;

/**
 * Created by jalil on 16/05/2018.
 */


public class Release {

    private String status;
    private String thumb;
    private String format;
    private String title;
    private String catno;
    private int year;
    private String resourceUrl;
    private String artist;
    private int id;

    /**
     * No args constructor for use in serialization
     *
     */
    public Release() {
    }

    /**
     *
     * @param id
     * @param catno
     * @param title
     * @param status
     * @param year
     * @param artist
     * @param format
     * @param resourceUrl
     * @param thumb
     */
    public Release(String status, String thumb, String format, String title, String catno, int year, String resourceUrl, String artist, int id) {
        super();
        this.status = status;
        this.thumb = thumb;
        this.format = format;
        this.title = title;
        this.catno = catno;
        this.year = year;
        this.resourceUrl = resourceUrl;
        this.artist = artist;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatno() {
        return catno;
    }

    public void setCatno(String catno) {
        this.catno = catno;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return title;
    }

}

