package ir.taghizadeh.android_mvp.model.networkLayer;

import java.io.IOException;
import java.io.InputStream;

class LocalJsonLoader {

    static LocalJsonLoader loader = new LocalJsonLoader();

    String load(String file) {
        String json;
        try {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream(file);
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}