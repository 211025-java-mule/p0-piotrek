public class Picture {

    private int fileSizeBytes;
    private String url;

    public int getFileSizeBytes() {
        return fileSizeBytes;
    }

    public void setFileSizeBytes(int fileSizeBytes) {
        this.fileSizeBytes = fileSizeBytes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "fileSizeBytes=" + fileSizeBytes +
                ", url='" + url + '\'' +
                '}';
    }
}
