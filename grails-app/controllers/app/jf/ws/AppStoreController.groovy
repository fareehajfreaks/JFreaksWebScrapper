package app.jf.ws

import org.grails.plugins.restawesome.AwesomeRestfulController
import grails.plugin.springsecurity.annotation.Secured


@Secured(['ROLE_ADMIN'])
class AppStoreController extends AwesomeRestfulController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json']



    AppStoreController()
    {
        super (AppStore)
    }

    @Override
    protected AppStore queryForResource(Serializable id) {




        if (params.appReviewId != null) {
            def app = AppReview.findById(params.appReviewId)
            println(app.getAppsId())



            println("if of app store list")

            AppStore.findById(app.getAppsId())
        } else {
            println("else of app store list")
            AppStore.get(id)
        }



    }

    @Override
    protected List<AppStore> listAllResources(Map params) {



        if (params.appReviewId != null) {


            def app = AppReview.findById(params.appReviewId)
            println(app.getAppsId())

            println("if of app store list")

            AppStore.findAllById(app.getAppsId())as List
        } else {
            println("else of app store list")
            AppStore.list(params)
        }


    }



}
