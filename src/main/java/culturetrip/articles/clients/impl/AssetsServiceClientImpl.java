package culturetrip.articles.clients.impl;

import culturetrip.articles.clients.AssetsServiceClient;
import culturetrip.articles.models.Image;
import culturetrip.articles.models.Video;

import java.util.concurrent.Future;

public class AssetsServiceClientImpl implements AssetsServiceClient {

    @Override
    public Future<Image> getImageById(String id) {
        //Implementation goes here
        return null;
    }

    @Override
    public Future<Video> getVideoById(String id) {
        //Implementation goes here
        return null;
    }
}
