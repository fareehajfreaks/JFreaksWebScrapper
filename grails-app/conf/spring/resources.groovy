import app.jf.ws.AppReview
import app.jf.ws.AppStore
import com.troutbird.pharmatics.cors.CorsFilter
import grails.rest.render.xml.XmlCollectionRenderer
import grails.rest.render.xml.XmlRenderer
import org.grails.plugins.restawesome.renderer.ApiCollectionRendererJson
import org.grails.plugins.restawesome.renderer.ApiRendererJson


// Place your Spring DSL code here
beans = {

    corsFilter(CorsFilter)

    appstoreRenderer(ApiRendererJson, AppStore) {
        label = "app"

    }

    appstoreCollectionRenderer(ApiCollectionRendererJson, AppStore) {
        label = "apps"
    }

    appreviewRenderer(ApiRendererJson, AppReview) {
        label = "review"
    }

    appreviewCollectionRenderer(ApiCollectionRendererJson, AppReview) {
        label = "reviews"
    }

   /* appstoreRenderer(XmlRenderer , AppStore)
            { //excludes =['id']
                //includes =['blog'] // can give multiple fields using includes dont know syntax

            }
    appstoreRenderer(XmlCollectionRenderer, AppStore)
            { //excludes =['id']
                //includes =['blog']

            }*/



  //  appreviewRenderer(app.jb.AppReviewJsonRenderer)

    /*appreviewRenderer(XmlRenderer , AppReview)
            { //excludes =['id']
                // can give multiple fields using includes dont know syntax

            }
    appreviewRenderer(XmlCollectionRenderer, AppReview)
            { //excludes =['id']
                // includes =['blog']

            }*/

}
