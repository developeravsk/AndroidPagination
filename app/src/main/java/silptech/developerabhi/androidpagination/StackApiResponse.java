package silptech.developerabhi.androidpagination;


import java.util.List;

class Owner {
    public int reputation;
    public long user_id;
    public String user_type, profile_image, display_name, link;

}
class Item{
    public Owner owner;
    public boolean is_accepted;
    public int score;
    public long last_activity_date;
    public long creation_date;
    public long answer_id;
    public long question_id;
}
public class StackApiResponse {
    public List<Item> items;
    public boolean has_more;
    public int quota_max;
    public int quota_remaining;
}
