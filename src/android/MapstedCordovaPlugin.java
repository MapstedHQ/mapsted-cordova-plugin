package mapsted.cordova.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.res.Resources;
import androidx.appcompat.app.AppCompatActivity;



// Import statements for Mapsted functionality
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import com.mapsted.ui.MapstedMapUiApi;
import com.mapsted.ui.CustomParams;
import com.mapsted.ui.MapUiApi.MapUiInitCallback;
import com.mapsted.map.views.MapPanType;
import com.mapsted.map.models.layers.BaseMapStyle;
import com.mapsted.ui.MapstedMapUiApiProvider;
import android.widget.FrameLayout;
import com.mapsted.positioning.coreObjects.BuildingInfo;
import com.mapsted.positioning.SdkError;
import com.mapsted.positioning.SdkStatusUpdate;
import com.mapsted.map.MapApi;
import com.mapsted.ui.MapUiApi;
import com.mapsted.positioning.CoreApi;
import com.mapsted.ui.MapstedMapUiApi;
import com.mapsted.map.views.MapstedMapRange;
import java.util.stream.Collectors;
import android.widget.ProgressBar;

import com.mapsted.ui.map.MapstedMapActivity;
import android.os.Bundle;
import com.google.gson.Gson;

/**
 * This class are string called from JavaScript.
 */
public class MapstedCordovaPlugin extends CordovaPlugin {

     private static final String TAG = "MapstedCordovaPlugin";
     public static final String SET_CUSTOM_PARAMS_JSON = "Set_Custom_Params_Json";


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
       if (action.equals("launchMapActivity")) {
            this.launchMapActivity(callbackContext);
            return true;
        }

        return false;
    }


    private CustomParams getCustomParams(Context context, boolean enableSelection, boolean showOnLaunch) {
        return (CustomParams) CustomParams.newBuilder((AppCompatActivity) context)
        .setEnablePropertyListSelection(true)
        .setShowPropertyListOnMapLaunch(true)
        .build();
    }
    public void launchMapActivity(CallbackContext callback) {
        try {
        AppCompatActivity activity = (AppCompatActivity) cordova.getActivity();
        Intent intent = new Intent(activity, MapstedMapActivity.class);

        // Obtain CustomParams object with both properties set to true
        CustomParams customParams = getCustomParams(activity, true, true);

        // Convert CustomParams to JSON
        String customParamsJson = new Gson().toJson(customParams);

        // Create a bundle and add CustomParams JSON to it
        Bundle bundle = new Bundle();
        bundle.putString(SET_CUSTOM_PARAMS_JSON, customParamsJson);

        // Attach bundle to the intent
        intent.putExtras(bundle);

        // Start activity with the intent
        activity.startActivity(intent);

        // Notify success
        callback.success();
        } catch (Exception e) {
        // Notify error
        callback.error("Error launching map activity: " + e.getMessage());
        }
    }

}
