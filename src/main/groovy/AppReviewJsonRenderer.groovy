package app.jb

import app.jf.ws.AppReview
import grails.rest.render.AbstractRenderer
import grails.rest.render.RenderContext
import grails.web.mime.MimeType

import groovy.json.JsonBuilder


class AppReviewJsonRenderer extends AbstractRenderer<AppReview>

{

    AppReviewJsonRenderer()
    {
        super(AppReview,[MimeType.JSON, MimeType.TEXT_JSON] as MimeType[])
    }

    @Override

    void render(AppReview rev, RenderContext context)
    {
        context.contentType=MimeType.JSON.name
        Writer writer = context.getWriter()
        Map content = ["title":rev.reviewTitle, "app":["title":rev.apps.appTitle], "review":rev.review]
        JsonBuilder builder = new JsonBuilder(content)
        builder.writeTo(writer)
        writer.flush()


    }

}
