package gg.noob.netty.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

public class Common {

    @Getter
    private static Common instance;
    @Getter
    private Gson gson;

    public Common() {
        instance = this;

        this.gson = new GsonBuilder()
                .serializeNulls()
                .create();

    }

}
