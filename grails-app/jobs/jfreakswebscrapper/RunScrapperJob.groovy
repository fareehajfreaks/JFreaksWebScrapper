package jfreakswebscrapper

class RunScrapperJob {
    def runScrapperService
    /*static triggers = {
        // cron name: 'myTrigger', cronExpression: "0/5 0 * * * ?"
         simple repeatInterval: 60000l // execute job once in 5 seconds
     }*/

   static triggers = {
        cron name: 'myTrigger', cronExpression: "0 22 23 ? * THU"
    }
   def execute() {
        //runScrapperService.runReviewScrapper();

    }
}
