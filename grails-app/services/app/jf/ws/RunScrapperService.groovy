package app.jf.ws

import geb.Browser
import grails.transaction.Transactional
import static grails.async.Promises.task
import static grails.async.Promises.waitAll

@Transactional
class RunScrapperService {

    AppReview app



    def displayDateTime()
    {
        Date today = new Date();

        println("scheduler run at time "+today.toString())
        println("scheduler run at time "+today.toString())
    }

    def runReviewScrapper() {


        displayDateTime();
        Browser.drive {


            def app_url
            def app_title

            int num_urls = AppStore.count()

            println(num_urls);
            app=new GetAppReview();

            ArrayList<String>  urls=AppStore.getAppUrls('getAppUrl')
            ArrayList<String>  titles = AppStore.getAppTitles('appTitle')


            //  println(titles.size()+ " " +titles.get(0) + "  " + titles.get(1))

            def task0 = task {
                for(int j=0;j<1;j++)
                {

                    app_url=urls.get(j);
                    app_title=titles.get(j);
                    println(""+app_url+" "+app_title)
                    go app_url
                    app.getReview(app_title,app_url);
              //      Thread.sleep(7200000);
                }

            }
            //waitAll(task0);




//            app = new G2CrowdReviews();
//            urls=AppStore.getAppUrls('g2CrowdUrl')
//            // titles=App_Store.getAppTitles('appTitle');
//
//            def task1 = task {
//
//                // loop to process all the apps urls retrieved from datastore of g2crowd
//                for(int j=0;j<1;j++)
//                {
//                    app_url = urls.get(j);
//                    app_title=titles.get(j);
//                    println(""+app_url)
//                    go app_url
//
//                    int tot = app.getTotalReview(app_title,app_url);
//
//                    // loop to retrieve reviews one by one from the total of all the reviews of that particular app
//                    for (int i = 1; i <=tot; i++) {
//                        if (i == 1) // as first page of review has no page number
//                            app.getReview(app_title,app_url);
//                        else {
//                            def rev_url = app_url + "?page=" + i;
//                            go(rev_url);
//                            app.getReview(app_title,rev_url)
//                        }
//                    }//end of reviews for loop
//
//                    Thread.sleep(7200000)
//                }//end of for loop getting all the urls from g2Crowd
//
//
//
//            }// end of task1
//
//            waitAll(task1);

            println ("All Reviews Saved Successfully")


        }//end of Browser drive
    }// end of index function



}// end of service class
