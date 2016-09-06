package app.jf.ws

class AppStore {

    String saasMaxUrl
    String getAppUrl
    String g2CrowdUrl

    String appTitle

    static hasMany =[reviews : AppReview]



    public static List getAppUrls(def title)
    {
        def c = AppStore.createCriteria()
        def urls = c.list  {
            projections {                    //projection does the trick
                property(title)
            }
        }

        return urls;
    }

    public static List getAppTitles(def title)
    {
        def c = AppStore.createCriteria()
        def urls = c.list  {
            projections {                    //projection does the trick
                property(title)
            }


        }
        println("urls="+urls)
        return urls;
    }


}
