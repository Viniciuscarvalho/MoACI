package aplicacoes.com.br.flowwater.parcelables;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by viniciuscarvalho on 28/11/15.
 */
public class User implements Parcelable {

    private int id;
    private String name;
    private String email;

    public User() {

    }

    private User(Parcel from) {
        setId(from.readInt());
        setName(from.readString());
        setEmail(from.readString());
    }

    public static final Parcelable.Creator<User>
            CREATOR = new Parcelable.Creator<User>() {

        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
        dest.writeString(getName());
        dest.writeString(getEmail());
    }

}
