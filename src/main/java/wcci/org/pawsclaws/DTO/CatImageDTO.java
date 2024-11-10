package wcci.org.pawsclaws.DTO;

public class CatImageDTO {
    private String id; // Unique identifier for the cat image
    private String url; // URL of the cat image
    private int width; // Width of the image
    private int height; // Height of the image

    // Default constructor
    public CatImageDTO() {
    }

    // Getter for image ID
    public String getId() {
        return id;
    }

    // Setter for image ID
    public void setId(String id) {
        this.id = id;
    }

    // Getter for image URL
    public String getUrl() {
        return url;
    }

    // Setter for image URL
    public void setUrl(String url) {
        this.url = url;
    }

    // Getter for image width
    public int getWidth() {
        return width;
    }

    // Setter for image width
    public void setWidth(int width) {
        this.width = width;
    }

    // Getter for image height
    public int getHeight() {
        return height;
    }

    // Setter for image height
    public void setHeight(int height) {
        this.height = height;
    }
}
