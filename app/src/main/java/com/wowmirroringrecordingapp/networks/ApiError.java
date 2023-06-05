package com.wowmirroringrecordingapp.networks;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wowmirroringrecordingapp.utilities.AppConstants;

import retrofit2.Response;

public class ApiError {
    private static String errormessage;

    public static String getApiError(Response<JsonObject> response, Context context) throws Exception {
        JsonObject errorObject = new JsonParser().parse(response.errorBody().string()).getAsJsonObject();
        //errormessage = context.getString(R.string.error_sometimeafter);
        if (errorObject != null && errorObject.has(AppConstants.MESSAGE)) {
            if (!(errorObject.get(AppConstants.MESSAGE).isJsonArray() || errorObject.get(AppConstants.MESSAGE).isJsonObject())) {
                errormessage = errorObject.get(AppConstants.MESSAGE).getAsString();
            } else {

            }

        }else if(errorObject !=null && errorObject.has(AppConstants.ERROR)){
            if(!(errorObject.get(AppConstants.ERROR_DESCRIPTION).isJsonArray()||
            errorObject.get(AppConstants.ERROR_DESCRIPTION).isJsonObject())){
                errormessage = errorObject.get(AppConstants.ERROR_DESCRIPTION).getAsString();
            }
        }
       // new ApiErrorDialogue(context, context.getString(R.string.error), errormessage , true).show();
        return errormessage;
    }

    public static String getArrayError(Response<JsonArray> response, Context context) throws Exception {
        JsonObject errorObject = new JsonParser().parse(response.errorBody().string()).getAsJsonObject();
        //errormessage = context.getString(R.string.error_sometimeafter);
        if (errorObject != null && errorObject.has(AppConstants.MESSAGE)) {
            if (!(errorObject.get(AppConstants.MESSAGE).isJsonArray() || errorObject.get(AppConstants.MESSAGE).isJsonObject())) {
                errormessage = errorObject.get(AppConstants.MESSAGE).getAsString();
            } else {

            }

        }else if(errorObject !=null && errorObject.has(AppConstants.ERROR)){
            if(!(errorObject.get(AppConstants.ERROR_DESCRIPTION).isJsonArray()||
                    errorObject.get(AppConstants.ERROR_DESCRIPTION).isJsonObject())){
                errormessage = errorObject.get(AppConstants.ERROR_DESCRIPTION).getAsString();
            }
        }
        // new ApiErrorDialogue(context, context.getString(R.string.error), errormessage , true).show();
        return errormessage;
    }
}
