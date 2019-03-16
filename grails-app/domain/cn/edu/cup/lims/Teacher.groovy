package cn.edu.cup.lims

class Teacher extends Person {

    static constraints = {
        code()
        name()
    }

    static hasMany = [students: Student]

    @Override
    static List<String> dataSheetTitles() {
        def head = ["姓名", "工号", "职称"]
        return head
    }

    @Override
    Map importFromDataSheet(Object dataSheet) {
        println("开始导入：${dataSheet}")
        def result = ""
        if (dataSheet.size() > 2) {
            def n = dataSheet[0]
            def c = dataSheet[1]
            def t = dataSheet[2]
            def tt = PersonTitle.findByName(t)
            if (Teacher.findByCode(c)) {
                result += "${c} 重复了！"
            } else {
                if (tt) {
                    name = n
                    code = c
                    personTitle = tt
                } else {
                    result += "${t} 职称找不到！"
                }
            }
        } else {
            result += "列数不够！"
        }
        def model = [teacher: this, result: result]
        return model
    }

}
