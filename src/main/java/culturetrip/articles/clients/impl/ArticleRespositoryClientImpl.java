package culturetrip.articles.clients.impl;

import culturetrip.articles.clients.ArticleRepositoryClient;
import culturetrip.articles.models.ArticleReference;

import java.util.concurrent.Future;

public class ArticleRespositoryClientImpl implements ArticleRepositoryClient{

    @Override
    public Future<ArticleReference> getArticleReferenceForId(String articleId) {
        //Implementation goes here
        return null;
    }
}
