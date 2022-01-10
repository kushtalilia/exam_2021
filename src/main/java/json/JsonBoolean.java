package json;

import java.security.cert.TrustAnchor;

/**
 * Created by Andrii_Rodionov on 1/4/2017.
 */
public class JsonBoolean extends Json {

    boolean item;

    public JsonBoolean(Boolean bool) {
        item = bool;
    }

    @Override
    public String toJson() {
        if (item){
            return "true";
        }else {
            return "false";
        }
    }
}
