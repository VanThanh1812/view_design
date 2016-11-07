package g.vanthanhbk.getpostgroupf.api_facebook;

import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONObject;

/**
 * Created by vanthanhbk on 21/09/2016.
 */
public class ControlAPIFacebook {
    private AccessToken accessToken;

    public ControlAPIFacebook(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public void getAllPostFromIdGroup (String group_id){


        new GraphRequest(AccessToken.getCurrentAccessToken(),group_id+"/feed",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback(){

                    @Override
                    public void onCompleted(GraphResponse response) {

                        JSONObject json =response.getJSONObject();
                        Log.d("controlclass",response.toString());
                        /*
                        * TODO: code get comment
                        * */
                    }
                }).executeAsync();
    }
}
