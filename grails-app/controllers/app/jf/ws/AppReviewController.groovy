package app.jf.ws



import org.grails.plugins.restawesome.AwesomeRestfulController
import grails.plugin.springsecurity.annotation.Secured


@Secured(['ROLE_ADMIN'])
class AppReviewController extends AwesomeRestfulController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json']


    AppReviewController()
    {
        super(AppReview)
    }


    @Override
    protected AppReview queryForResource(Serializable id) {



        if (params.appStoreId != null) {

            println("if of app review query")

            AppReview.findByAppsAndId(AppStore.load(params.appStoreId), id)
        } else {
            println("else of app review query")
            AppReview.get(id)
        }

    }



    @Override
    protected List<AppReview> listAllResources(Map params) {

        if (params.appStoreId != null) {

            println("if of app review list")

            AppReview.findAllByApps(AppStore.load(params.appStoreId), params)as List

        } else {
            println("else of app review list")
            AppReview.list(params)
        }

    }



}

/*  @Override
    def index(Integer max) {
         params.max = Math.min(max ?: 2, 100)
         def detail = params.detail ?: "complete"

         respond listAllResources(params), [detail:detail, paging:[totalCount: resource.count(),
                                                                   currentMax: params.max,
                                                                   curentOffset: params.offset ?: 0]]
     }*/
//http://localhost:8080/book/index?max=3
//http://localhost:8080/book/index?offset=1 skip 1 page
