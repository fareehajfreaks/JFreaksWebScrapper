package app.jf.ws


import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements



class AppReview {

    String review
    String reviewTitle;

    static belongsTo = [apps:AppStore ]//,  if i delet app then all its  reviews will also get delete

    static constraints = {

        review type: 'text'
        review(maxSize:1000)
    }

    public void getReview(String title,String source){}
    public int getTotalReview(String res){}


}  // end of class AppReview

/**
 * GetAppreview class collecting review title and content from www.getapp.com
 */


class GetAppReview extends AppReview
{

    public void getReview(String title,String res)
    {

        String r_title=""
        String r_content=""


        Document doc = Jsoup.connect(res).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .timeout(1000*5) //it's in milliseconds, so this means 5 seconds.
        //.ignoreHttpErrors(true)
                .get();
        Elements p=doc.select("div.media-body");

        for (Element  e: p)


        {
            r_title=e.select("strong").first().text();
            r_content="";
            for(Element e1: e.select("p"))

            {

                if(e1.html().toString().length()==0)
                    continue;


                if((e1.text().contains("Rating")) || (e1.text().contains ("Time used")) ) {
                    //System.out.println("hi"+r_content);
                    break;
                }
                r_content+=e1.text();



            }

            if(r_content.length()==0)
                r_content="Review Not Available";
            else {
                r_content = r_content.replaceAll("'", "").toString();

            }
            r_title = r_title.replaceAll("'", "").toString();
            r_title=title+":"+r_title;



            System.out.println("title: "+r_title+"\ncontent:\n"+r_content);

            AppReview.withTransaction {

                def app1= AppStore.find("FROM AppStore ORDER BY id")
                new AppReview(apps:app1, reviewTitle: r_title,review: r_content).save();
                //RunScrapperService.saveReviews(r_title,r_content);
                // code here
            }





        }


    }// end of get ReviewsApp

    public int getTotalReview(String res)
    {
        // as normally each app has only 1 page review
        return 1;
    }


}//end of class

/**
 * G2CrowdReviews Class collecting review from www.g2Crowd.com
 */

class G2CrowdReviews extends AppReview
{

    public void getReview(String title,String res)
    {
        Document doc1 = Jsoup.connect(res).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .timeout(1000*5) //it's in milliseconds, so this means 5 seconds.
        //.ignoreHttpErrors(true)
                .get();


        Elements p1=doc1.select("div.column.medium-6 > a[href]");

        ArrayList<String> urls = new ArrayList<String>();

        int j=0;
        for(Element e:p1)
        {

            urls.add(e.attr("href"));

            j++;


        }

        def r_title="g2";
        for(int i=0;i<urls.size();i++)
        {
            doc1 = Jsoup.connect(urls.get(i)).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .timeout(1000*5) //it's in milliseconds, so this means 5 seconds.
            //.ignoreHttpErrors(true)
                    .get();
            System.out.println(urls.get(i));

            Elements e=doc1.select("div.survey-wrapper.group.tabs-default-body.tab-content-flat");
            def r_content="";

            int t=1;
            for(Element e1:e.select("p"))


            {


                if(t==1) {
                    r_title = e1.text();
                    t++;
                }


                else

                    r_content+=e1.text();

            }

            r_content=r_content.replaceAll("'","").toString();
            r_title=r_title.replaceAll("'","").toString();
            r_title=title+":"+r_title;
            System.out.println("title: "+r_title+"\ncontent:\n"+r_content);

            AppReview.withTransaction {

                new AppReview(reviewTitle: r_title,review: r_content).save();

            }



        } // end of for loop


    }// end of getReview function

    /**
     * In g2 Crowd there are multiple tabs containing reviews so this funtion will count all the reviews by selecting review number from the last tab
     * @param res
     * @return
     */

    public int getTotalReview(String res)
    {
        Document doc1 = Jsoup.connect(res).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .timeout(1000*5) //it's in milliseconds, so this means 5 seconds.
        //.ignoreHttpErrors(true)
                .get();

        Elements p1=doc1.select("ul.pagination.text-center").select("li.pagination__component.pagination__named-link").select("a");
        String s2="";
        for(Element e:p1)
        {

            s2=e.attr("href");
            s2= s2.replaceAll("[^0-9]", "");

            println(s2);
        }

        if(s2.length()!=0) {
            int i = Integer.parseInt(s2);
            return i;
        }

        else
            return 1;
    } // end of get Total reviews

}//end of class
