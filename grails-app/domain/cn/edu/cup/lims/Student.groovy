package cn.edu.cup.lims

import cn.edu.cup.common.DataExchangeInterface

class Student extends Person {

    String gradeName            //年级
    Major major                //专业

    static belongsTo = [supervisor: Teacher]

    static constraints = {
        code()
        name()
        personTitle()
        gradeName()
        major()
        supervisor(nullable: true)
    }

    @Override
    static List<String> dataSheetTitles() {
        def head = ["姓名", "学号", "类型", "年级", "专业", "导师"]
        return head
    }

    @Override
    Map importFromDataSheet(Object dataSheet) {
        println("开始导入：${dataSheet}")
        def result = ""
        if (dataSheet.size() > 5) {
            def n = dataSheet[0]
            def c = dataSheet[1]
            def t = dataSheet[2]
            def tt = PersonTitle.findByName(t)
            def g = dataSheet[3]
            def z = dataSheet[4]
            def m = Major.findByName(z)
            if (!m) {
                m = new Major(name: z)
                m.save(true)
            }
            def ds = Teacher.findByName(dataSheet[5])       //这个地方可能是一个BUG!!
            if (Student.findByCode(c)) {
                result += "${c} 重复了！"
            } else {
                if (tt) {
                    switch (t) {
                        case "本科":
                            name = n
                            code = c
                            personTitle = tt
                            gradeName = g
                            major = m
                            break
                        default:
                            if (ds) {
                                name = n
                                code = c
                                personTitle = tt
                                supervisor = ds
                                gradeName = g
                                major = m
                            } else {
                                name = n
                                code = c
                                personTitle = tt
                                gradeName = g
                                major = m
                            }
                    }
                } else {
                    result += "${t} 没有这个类型的学生！"
                }
            }
        } else {
            result += "列数不够！"
        }
        def model = [student: this, result: result]
        return model
    }

    @Override
    List exportToDataSheet() {
        return null
    }
}
