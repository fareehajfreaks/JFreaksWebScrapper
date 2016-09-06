package jfreakswebscrapper

class UrlMappings {

    static mappings = {

      "/apps"(resources : 'appStore')

        "/reviews"(resources:'appReview')

        "/apps"(resources:'appStore')
                {
                    '/reviews'(resources: 'appReview')
                }

        "/reviews"(resources:'appReview') {
            "/apps"(resources:'appStore')
        }


        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
