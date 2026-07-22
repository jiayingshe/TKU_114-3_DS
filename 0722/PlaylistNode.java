public class PlaylistNode {
    String id;
    String title;
    PlaylistNode next;
    public PlaylistNode(String id, String title) {
        this.id = (id != null) ? id.trim() : "";
        this.title = (title != null) ? title.trim() : "";
        this.next = null;
    }
}