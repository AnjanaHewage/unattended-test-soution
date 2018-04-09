package culturetrip.articles.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import culturetrip.articles.clients.ArticleRepositoryClient;
import culturetrip.articles.clients.AssetsServiceClient;
import culturetrip.articles.clients.impl.ArticleRespositoryClientImpl;
import culturetrip.articles.clients.impl.AssetsServiceClientImpl;
import culturetrip.articles.models.ArticleReference;
import culturetrip.articles.models.Image;
import culturetrip.articles.models.Video;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ArticleApiService {

    private static final Logger LOG =  LoggerFactory.getLogger(ArticleApiService.class);

    private ArticleRepositoryClient articleRepositoryClient;

    private AssetsServiceClient assetsServiceClient;

    public ArticleApiService() {
        this.articleRepositoryClient = new ArticleRespositoryClientImpl();
        this.assetsServiceClient = new AssetsServiceClientImpl();
    }

    public ArticleReference getArticleDetils(String articleId) throws InterruptedException, ExecutionException{

        Future<ArticleReference> articleReferenceFuture = articleRepositoryClient.getArticleReferenceForId(articleId);

        if (!articleReferenceFuture.isDone()){
            LOG.info("Retrieving article details...");
            Thread.sleep(300);
        }
        return articleReferenceFuture.get();
    }

    public Image getImageDetails(String id) throws InterruptedException, ExecutionException{
        Future<Image> imageFuture = assetsServiceClient.getImageById(id);

        if (!imageFuture.isDone()){
            LOG.info("Retrieving image details...");
            Thread.sleep(300);
        }

        return imageFuture.get();
    }

    public Video getVideoDetails(String id) throws InterruptedException, ExecutionException{
        Future<Video> videoFuture = assetsServiceClient.getVideoById(id);

        if(!videoFuture.isDone()) {
            LOG.info("Retrieving video details...");
            Thread.sleep(300);
        }

        return videoFuture.get();
    }

}
