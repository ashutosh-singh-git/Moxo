package com.moxo.app.util;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public interface LoggingMarker {

    Marker INVALID_REQUEST_ERROR = MarkerFactory.getMarker("INVALID_REQUEST_ERROR");

}
