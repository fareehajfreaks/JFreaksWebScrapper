import app.jf.ws.*
import grails.converters.JSON
import grails.util.Environment

class BootStrap {

    RunScrapperService runScrapperService

    def init = { servletContext ->


        def result = '################## running in UNCLEAR mode.'
        println "Application starting ... "

        createUser()
        switch (Environment.current) {
            case Environment.DEVELOPMENT:
                result = 'now running in DEV mode.'
                seedTestData()
                break;
            case Environment.TEST:
                result = 'now running in TEST mode.'
                break;
            case Environment.PRODUCTION:
                result = 'now running in PROD mode.'
                seedProdData()
                break;
        }
        println "current environment: $Environment.current"
        println "$result"

        marshalAppReview()
        marshalAppStore()

        runScrapperService.runReviewScrapper();

    }


    public void createUser()
    {
        def adminRole = new Role('ROLE_ADMIN').save(failOnError: true)
        def userRole = new Role('ROLE_USER').save(failOnError: true)

        def testUser1 = new User('his', 'his').save(failOnError: true)
        def testUser2 = new User('her', 'her').save(failOnError: true)

        UserRole.create testUser1, adminRole, true
        UserRole.create testUser2, adminRole,true

        assert User.count == 2;
        println "Finished loading $User.count user into database"
    }
    def marshalAppReview() {

        JSON.registerObjectMarshaller(AppReview){
            def ret = [:]
            ret['id'] = it.id
            ret['reviewTitle'] = it.reviewTitle
            ret['content'] = it.review
           // ret['apps'] = it.apps
            //or if you want the field of your own choice
            ret['appTitle']=it.apps.appTitle

            ret
        }
    }

    def marshalAppStore() {

        JSON.registerObjectMarshaller(AppStore){
            def ret = [:]
            ret['id'] = it.id
            ret['appTitle'] = it.appTitle
            ret['appUrl'] = it.getAppUrl
            ret['appReviewId'] = it.reviews.id
            ret['appReviewTitle'] = it.reviews.reviewTitle
            ret['appReviewContent'] = it.reviews.review

            ret
        }
    }
    def destroy = {
        println "Application shutting down... "
    }

    private void seedTestData() {


        if(!(AppStore.findByAppTitle("GoToMeeting"))) {
            println "Start loading apps and their respective urls into database"


            def app1 = new AppStore(appTitle: 'BigContact', g2CrowdUrl: 'https://www.g2crowd.com/products/bigcontacts/reviews', getAppUrl: 'https://www.getapp.com/customer-management-software/a/bigcontacts/reviews/', saasMaxUrl: 'none')
            assert app1.save(failOnError: true, flush: true, insert: true)
            app1.errors = null


            def app2 = new AppStore(appTitle: 'GoToMeeting', g2CrowdUrl: 'https://www.g2crowd.com/products/gotomeeting/reviews', getAppUrl: 'https://www.getapp.com/it-communications-software/a/gotomeeting/reviews/', saasMaxUrl: 'none');
            assert app2.save(failOnError: true, flush: true, insert: true)
            app2.errors = null

            assert AppStore.count == 2;
            println "Finished loading $AppStore.count apps into database"

            def review2 = new AppReview(apps: app2, reviewTitle: 'Testing Review!!!', review: ' just a sample review');
            assert review2.save(failOnError: true, flush: true, insert: true)
            review2.errors = null

            assert AppReview.count == 1;
            println "Finished loading $AppReview.count review into database"
        }



    }

}