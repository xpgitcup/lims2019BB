package cn.edu.cup.lims

import cn.edu.cup.common.DataExchangeInterface

class Person implements DataExchangeInterface {

    String code
    String name

    static belongsTo = [personTitle: PersonTitle]

    static constraints = {
        code(unique: true)
        name()
    }

    String toString() {
        return "${code}.${name}"
    }

    @Override
    List<String> dataSheetTitles() {
        return null
    }

    @Override
    Map importFromDataSheet(Object dataSheet) {
        return null
    }

    @Override
    List exportToDataSheet() {
        return null
    }
}
