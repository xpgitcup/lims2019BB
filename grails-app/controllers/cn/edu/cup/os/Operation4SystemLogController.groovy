package cn.edu.cup.os

import cn.edu.cup.system.SystemLog
import cn.edu.cup.system.SystemLogController

class Operation4SystemLogController extends SystemLogController {

    /*
    * 删除超过某个时间的日志
    * */

    def deleteSystemLogOldThan() {
        println("${params}")
        Calendar rightNow = Calendar.getInstance();
        /*
        rightNow.setTime(dt);
        rightNow.add(Calendar.YEAR,-1);//日期减1年
        rightNow.add(Calendar.MONTH,3);//日期加3个月
        rightNow.add(Calendar.DAY_OF_YEAR,10);//日期加10天
        */
        def timeUnit = params.timeUnit
        def timeNumber = params.timeNumber
        def now = new Date()
        rightNow.setTime(now)
        def v
        switch (timeUnit) {
            case "second":
                rightNow.add(Calendar.SECOND, -timeNumber)
                break
            case "minute":
                rightNow.add(Calendar.MINUTE, -timeNumber)
                break
            case "hour":
                rightNow.add(Calendar.HOUR, -timeNumber)
                break
            case "day":
                rightNow.add(Calendar.DATE, -timeNumber)
                now.set(day: v)
                break
            case "month":
                rightNow.add(Calendar.MONTH, -timeNumber)
                break
        }
        SystemLog.executeQuery("delete where actionDate > ?", rightNow)
        redirect(action: 'index')
    }

    /*
    * 清理日志
    * */

    def clearSystemLog(params) {
        println("${params}")
        def clearType = params.clearType
        def k = Integer.parseInt(params.n)
        //println(clearType)
        switch (clearType) {
            case "keepLast":
                def maxId = SystemLog.last().id
                println("---清理日志：  ${maxId}")
                def kk = maxId - k
                def sqlResult = SystemLog.executeUpdate("delete SystemLog s where s.id < ${kk}")

                println("sql 执行结果： ${sqlResult}")
                break;
            default:
                println("清理日志。。。。")
                break
        }
        redirect(action: 'index')
    }

    def index() {}
}
