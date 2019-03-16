package cn.edu.cup.lims

import cn.edu.cup.common.DataExchangeInterface

class Major implements DataExchangeInterface{

    String name

    static constraints = {
    }

    String toString() {
        return name
    }

    @Override
    List<String> dataSheetTitles() {
        def head = ["专业名称"]
        return head
    }

    @Override
    Map importFromDataSheet(Object dataSheet) {
        println("开始导入：${dataSheet}")
        def result = ""
        if (dataSheet.size() > 0) {
            def n = dataSheet[0]
            if (Major.findByName(n)) {
                result += "${c} 重复了！"
            } else {
                name = n
            }
        } else {
            result += "列数不够！"
        }
        def model = [major: this, result: result]
        return model
    }

    @Override
    List exportToDataSheet() {
        return null
    }
}
