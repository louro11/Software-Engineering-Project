package pt.tecnico.mydrive.service.dto;

import org.joda.time.DateTime;
import pt.tecnico.mydrive.domain.User;

public class FileDto implements Comparable<FileDto> {

    private String _name;
    private String _permissions;
    private DateTime _timestamp;
    private User _owner;

    public FileDto(String name, String permissions, DateTime timestamp, User owner) {
        _name = name;
        _permissions = permissions;
        _timestamp = timestamp;
        _owner = owner;
    }



    public final String getName() {
        return _name;
    }

    public final String getPermissions() {
        return _permissions;
    }

    public final DateTime getTimeStamp() {
        return _timestamp;
    }

    public final User getOwner(){
        return _owner;
    }

    @Override
    public int compareTo(FileDto other) {
	      return getName().compareTo(other.getName());
    }
}
