package co.edu.eci.ieti.android.network;

import androidx.room.TypeConverter;

import co.edu.eci.ieti.android.network.data.User;

public class UserConverter {
    @TypeConverter
    public String userId(User user){
        return user.getEmail();
    }
    @TypeConverter
    public User toUser(String email){
        return new User();
    }
}
