package pt.tecnico.mydrive.domain;
import org.joda.time.DateTime;

public class Link extends Link_Base {
    
    public Link() {
        super();
    }

    public Link(String name, String permission, Integer fileid, DateTime timestamp, User owner, String content) {
    	set_name(name);
    	set_permission (permission);
    	set_fileid(fileid);
    	set_timestamp(timestamp);
    	setOwner(owner);
        set_content(content);
    }
}
