package com.moxo.app.service;

import com.moxo.app.dto.PublisherDetails;

public interface FeedProcessor {

    void submit(PublisherDetails url);
}
