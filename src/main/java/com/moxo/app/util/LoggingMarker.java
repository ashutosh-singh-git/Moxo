package com.moxo.app.util;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public interface LoggingMarker {

    Marker INVALID_REQUEST_ERROR = MarkerFactory.getMarker("INVALID_REQUEST_ERROR");
    Marker ACTIVITY_POST_ERROR = MarkerFactory.getMarker("ACTIVITY_POST_ERROR");
    Marker ACTIVITY_NOT_FOUND = MarkerFactory.getMarker("ACTIVITY_NOT_FOUND");

    Marker PAW_PAGE_NOT_FOUND = MarkerFactory.getMarker("PAW_PAGE_NOT_FOUND");
    Marker FOSTER_NOT_FOUND = MarkerFactory.getMarker("FOSTER_NOT_FOUND");
    Marker FOSTER_UPDATE_ERROR = MarkerFactory.getMarker("FOSTER_UPDATE_ERROR");
    Marker USER_REGISTER_ERROR = MarkerFactory.getMarker("USER_REGISTER_ERROR");
}
